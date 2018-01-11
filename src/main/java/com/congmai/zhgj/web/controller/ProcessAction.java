package com.congmai.zhgj.web.controller;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipInputStream;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import org.activiti.bpmn.converter.BpmnXMLConverter;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.editor.constants.ModelDataJsonConstants;
import org.activiti.editor.language.json.converter.BpmnJsonConverter;
import org.activiti.engine.ActivitiObjectNotFoundException;
import org.activiti.engine.ActivitiTaskAlreadyClaimedException;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.activiti.engine.runtime.ProcessInstance;
import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.congmai.zhgj.core.util.Constants;
import com.congmai.zhgj.core.util.Page;
import com.congmai.zhgj.core.util.ProcessDefinitionCache;
import com.congmai.zhgj.core.util.UserUtil;
import com.congmai.zhgj.core.util.WorkflowUtils;
import com.congmai.zhgj.web.enums.StaticConst;
import com.congmai.zhgj.web.model.BaseVO;
import com.congmai.zhgj.web.model.Company;
import com.congmai.zhgj.web.model.HistoricTaskVO;
import com.congmai.zhgj.web.model.OrderInfo;
import com.congmai.zhgj.web.model.ProcessInstanceEntity;
import com.congmai.zhgj.web.model.User;
import com.congmai.zhgj.web.service.ActRuTaskService;
import com.congmai.zhgj.web.service.IProcessService;
import com.congmai.zhgj.web.service.OrderService;
import com.congmai.zhgj.web.service.ProcessBaseService;
import com.congmai.zhgj.web.service.UserService;
import com.congmai.zhgj.web.service.activiti.WorkflowDeployService;
import com.congmai.zhgj.web.service.activiti.WorkflowService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * 
 * @ClassName ProcessAction
 * @Description 流程控制类
 * @author tanzb
 * @Date 2017年8月28日 下午5:48:13
 * @version 1.0.0
 */
@Controller
@RequestMapping("/processAction")
public class ProcessAction {
	private static final Logger logger = Logger.getLogger(ProcessAction.class);
    
	@Autowired
	protected UserService userService;
    
    @Autowired
    protected WorkflowService traceService;

	@Autowired
	private IProcessService processService;
	
	@Autowired
	private RepositoryService repositoryService;
	
	@Autowired
	private WorkflowDeployService workflowProcessDefinitionService;
	@Resource
    private ProcessBaseService processBaseService;
	@Resource
    private OrderService  orderService;
	 @Autowired
	    private ActRuTaskService actRuTaskService ;
    
//	@Autowired
//	private RevokeTask revokeTaskService;
	
//	@Autowired
//	private ProcessEngine processEngine;
	
    /**
     * 显示流程图,带流程跟踪
     * @param processInstanceId
     * @param response
     * @throws Exception 
     */
    @RequestMapping(value = "/process/showDiagram/{processInstanceId}", method = RequestMethod.GET)
	public void showDiagram(@PathVariable("processInstanceId") String processInstanceId, HttpServletResponse response) throws Exception {
	        InputStream imageStream = this.processService.getDiagram(processInstanceId);
	        // 输出资源内容到相应对象
	        byte[] b = new byte[1024];
	        int len;
	        while ((len = imageStream.read(b, 0, 1024)) != -1) {
	            response.getOutputStream().write(b, 0, len);
	        }
	}
    
    /**
     * 显示图片通过部署id，不带流程跟踪(没有乱码问题)
     * @param processDefinitionId
     * @param resourceType	资源类型(xml|image)
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "/process/process-definition")
    public void loadByDeployment(@RequestParam("processDefinitionId") String processDefinitionId, @RequestParam("resourceType") String resourceType,
                                 HttpServletResponse response) throws Exception {
    	InputStream resourceAsStream = this.processService.getDiagramByProDefinitionId_noTrace(resourceType, processDefinitionId);
        byte[] b = new byte[1024];
        int len = -1;
        while ((len = resourceAsStream.read(b, 0, 1024)) != -1) {
            response.getOutputStream().write(b, 0, len);
        }
    }
    
    
    /**
     * 显示图片通过流程id，不带流程跟踪(没有乱码问题)
     *
     * @param resourceType      资源类型(xml|image)
     * @param processInstanceId 流程实例ID
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "/process/process-instance")
    public void loadByProcessInstance(@RequestParam("type") String resourceType, @RequestParam("pid") String processInstanceId, HttpServletResponse response)
            throws Exception {
        InputStream resourceAsStream = this.processService.getDiagramByProInstanceId_noTrace(resourceType, processInstanceId);
        byte[] b = new byte[1024];
        int len = -1;
        while ((len = resourceAsStream.read(b, 0, 1024)) != -1) {
            response.getOutputStream().write(b, 0, len);
        }
    }
    
    
    /**
     * 自定义流程跟踪信息-比较灵活(现在用的这个)
     *
     * @param processInstanceId
     * @return
     * @throws Exception
     */
//    @RequiresPermissions("user:process:trace")
    @RequestMapping(value = "/process/trace/{pid}")
    @ResponseBody
    public List<Map<String, Object>> traceProcess(@PathVariable("pid") String processInstanceId) throws Exception {
        List<Map<String, Object>> activityInfos = this.traceService.traceProcess(processInstanceId);
        return activityInfos;
    }
    
    /**
     * 以下方法是对应easyui的写法
     * 
     * @author ZML
     */
    
    /**
     * 跳转待办任务、已完成任务页面
     * @return
     */
    @RequestMapping(value = "/userTaskList")
    public String userTaskList(){
    	return "task/list_task";
    }
    

    /**
	 * 查询待办任务
	 * @param session
	 * @param redirectAttributes
	 * @param model
	 * @return
	 * @throws Exception
	 */
//	@RequiresPermissions("user:task:todoTask")
	@RequestMapping(value = "/todoTask/{businessType}", method = {RequestMethod.GET})
	@ResponseBody
	public ResponseEntity<Map<String,Object>> todoTask(@PathVariable("businessType") String businessType, Map<String, Object> mp,HttpServletRequest request) throws Exception{
		String userId = null;
		try {
			userId = UserUtil.getUserFromSession().getUserId().toString();
		} catch (Exception e) {
			System.out.println("Session已失效！");
			e.printStackTrace();
		}
		
		
		User user = this.userService.selectById(new Integer(userId));
		List<BaseVO> taskList = this.processService.findTodoTask(user);
		List<Object> jsonList=new ArrayList<Object>(); 
		for(BaseVO base : taskList){
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("businessType", base.getBusinessType());
			map.put("userName", base.getUser_name());
			map.put("title", base.getTitle());
			map.put("taskId", base.getTask().getId());
			map.put("taskName", base.getTask().getName());
			map.put("createTime", base.getTask().getCreateTime());
			if("buyOrder".equals(businessType)||"saleOrder".equals(businessType)){
				OrderInfo o=orderService.selectById(base.getBusinessKey());//获取订单详情
				map.put("num", o==null?"":o.getOrderNum());
				if("buyOrder".equals(businessType)){
					map.put("comName", o==null?"":o.getSupplyName());
				}else {
					map.put("comName", o==null?"":o.getBuyName());
				}
				
			}
			if("d".equals(businessType)||"saleOrder".equals(businessType)){
				OrderInfo o=orderService.selectById(base.getBusinessKey());//获取订单详情
				map.put("num", o==null?"":o.getOrderNum());
				if("buyOrder".equals(businessType)){
					map.put("comName", o==null?"":o.getSupplyName());
				}else {
					map.put("comName", o==null?"":o.getBuyName());
				}
				
			}
			String assign = base.getTask().getAssignee();
			if(assign != null){
				User u = this.userService.selectById(new Integer(assign));
				assign = u.getUserName();
			}else assign = "";
			String owner = base.getTask().getOwner();
			if(owner != null){
				User u = this.userService.selectById(new Integer(owner));
				owner = u.getUserName();
			}else owner = "";
			
			map.put("assign", assign);
			map.put("owner", owner);
			map.put("taskDefinitionKey", base.getTask().getTaskDefinitionKey());
			map.put("processInstanceId", base.getProcessInstance().getId());
			map.put("processDefinitionId", base.getProcessInstance().getProcessDefinitionId());
			map.put("processDefinitionKey", base.getProcessDefinition().getKey());	//任务跳转用
			map.put("supended", base.getProcessInstance().isSuspended());
			map.put("version", base.getProcessDefinition().getVersion());
			if(!"All".equals(businessType)){
				if(businessType.equals(base.getBusinessType())){//根据流程类型添加到待办事项
					jsonList.add(map);
				}
			}else jsonList.add(map);//进入首页时，所有流程类型添加到待办事项
			
			
		}
//		Thread.sleep(1000000);
		Map<String,Object> pageMap = new HashMap<String,Object>();
		pageMap.put("draw", 1);
		pageMap.put("recordsTotal", jsonList==null?0:jsonList.size());
		pageMap.put("recordsFiltered", jsonList==null?0:jsonList.size());
		pageMap.put("data", jsonList);
		
		return new ResponseEntity<Map<String,Object>>(pageMap, HttpStatus.OK);
	}
	
	/**
	 * 
	 * @Description 路由跳转到有审批页面时，加载待办条数
	 * @param businessType
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getTodoTaskSize/{businessType}", method = {RequestMethod.GET})
	@ResponseBody
	public String getTodoTaskSize(@PathVariable("businessType") String businessType) throws Exception{
		int taskListSize = 0;
		try {
			String userId = UserUtil.getUserFromSession().getUserId().toString();
			User user = this.userService.selectById(new Integer(userId));
			List<BaseVO> taskList = this.processService.findTodoTask(user);
			
			if(taskList != null && taskList.size() > 0){
				if("All".equals(businessType)){//首页显示所有待办流程
					taskListSize = taskList.size();
				}else{//根据流程不同加以统计
					for(BaseVO base : taskList){
						if(businessType.equals(base.getBusinessType())){
							taskListSize ++;
						}
					}
				}
			}
		} catch (Exception e) {
			System.out.println("Session已失效！");
			e.printStackTrace();
		}
		
		
//		Thread.sleep(10000);
		return String.valueOf(taskListSize);
	}
    
	
    /**
     * 查看已完成任务列表
     *
     * @return
     * @throws Exception 
     */
//    @RequiresPermissions("user:process:finished")
    @RequestMapping(value = "/endTask/{businessType}")
    @ResponseBody
    public ResponseEntity<Map<String,Object>> findFinishedTaskInstances(@PathVariable("businessType") String businessType) throws Exception {
    	User user = UserUtil.getUserFromSession();
//    	if("All".equals(businessType))businessType=null;
    	List<BaseVO> taskList = this.processService.findFinishedTaskInstancesDiy(user,businessType);
    	List<Object> jsonList=new ArrayList<Object>(); 
    	for(BaseVO base : taskList){
    		Map<String, Object> map = new HashMap<String, Object>();
    		map.put("businessType", base.getBusinessType());
    		map.put("userName", base.getUser_name());
    		map.put("title", base.getTitle());
    		map.put("taskId", base.getHistoricTaskVO().getTaskId());
    		map.put("processInstanceId", base.getHistoricTaskVO().getProcessInstanceId());
    		map.put("startTime", base.getHistoricTaskVO().getStartTime());
    		map.put("claimTime", base.getHistoricTaskVO().getClaimTime());
    		map.put("endTime", base.getHistoricTaskVO().getEndTime());
    		map.put("deleteReason", base.getHistoricTaskVO().getDeleteReason());
    		User auditUser = actRuTaskService.getAuditUserByProcessInstanceId(base.getHistoricTaskVO().getProcessInstanceId());//获取接收者
    		map.put("currentPointUserName", auditUser==null?null:auditUser.getUserName());//审批节点显示
    		if(base.getProcessDefinition()!=null){
    			map.put("version", base.getProcessDefinition().getVersion());
    		}
    		if("buyOrder".equals(businessType)||"saleOrder".equals(businessType)){
				OrderInfo o=orderService.selectById(base.getBusinessKey());//获取订单详情
				map.put("num", o==null?"":o.getOrderNum());
				map.put("serialNum", o==null?"":o.getSerialNum());
				if("buyOrder".equals(businessType)){
					map.put("comName", o==null?"":o.getSupplyName());
				}else {
					map.put("comName", o==null?"":o.getBuyName());
				}
				
			}
    		
//    		jsonList.add(map);
    		if(!"All".equals(businessType)){
				if(businessType.equals(base.getBusinessType())){//根据流程类型添加到已办事项
					jsonList.add(map);
				}
			}else jsonList.add(map);//进入首页时，所有流程类型添加到已办事项
    	}
    	
    	Map<String,Object> pageMap = new HashMap<String,Object>();
		pageMap.put("draw", 1);
		pageMap.put("recordsTotal", jsonList==null?0:jsonList.size());
		pageMap.put("recordsFiltered", jsonList==null?0:jsonList.size());
		pageMap.put("data", jsonList);
    	
		return new ResponseEntity<Map<String,Object>>(pageMap, HttpStatus.OK);
    }
    
    /**
     * 
     * @Description 路由跳转到有审批页面时，加载已办条数
     * @param businessType
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getEndTaskSize/{businessType}")
    @ResponseBody
    public String getEndTaskSize(@PathVariable("businessType") String businessType) throws Exception {
    	User user = UserUtil.getUserFromSession();
    	List<BaseVO> taskList = this.processService.findFinishedTaskInstances(user);
    	
    	int taskListSize = 0;
    	if(taskList != null && taskList.size() > 0){
			if("All".equals(businessType)){//首页显示所有待办流程
				taskListSize = taskList.size();
			}else{//根据流程不同加以统计
				for(BaseVO base : taskList){
					if(businessType.equals(base.getBusinessType())){
						taskListSize ++;
					}
				}
			}
		}
		
		
		return String.valueOf(taskListSize);
    }
    
	/**
	 * 签收任务
	 * @return
	 */
//	@RequiresPermissions("user:task:claim")
	@RequestMapping(value = "/claim/{taskId}", method = RequestMethod.POST, produces = "application/text;charset=UTF-8")
	@ResponseBody
	public String claim(@PathVariable("taskId") String taskId) {
		String result = "";
		try {
			User user = UserUtil.getUserFromSession();
			this.processService.doClaim(user, taskId);
			result = "任务签收成功！";
		}catch (ActivitiObjectNotFoundException e){
			result = "此任务不存在！任务签收失败！";
		}catch (ActivitiTaskAlreadyClaimedException e) {
			result = "此任务已被其他组成员签收！请刷新页面重新查看！";
		}catch (Exception e) {
			result = "任务签收失败！请联系管理员！";
		} 
        return result;
	}
    
	/**
	 * 委派任务
	 * 委派也是代办、协办，你领导接到一个任务，让你代办，你办理完成后任务还是回归到你的领导，事情是你做的，功劳是你领导的，这就是代办。
	 * 所以代办人完成任务后，任务还会回到原执行人，流程不会发生变化。
	 * @param taskId	代办人
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "/process/delegateTask/{taskId}")
	@ResponseBody
	public String delegateTask(@PathVariable("taskId") String taskId, @RequestParam("userId") String userId){
		String result = "";
		try {
			this.processService.doDelegateTask(userId, taskId);
			result = "委派任务成功！";
		} catch (ActivitiObjectNotFoundException e){
			result = "此任务不存在！委派任务失败！";
		} catch (Exception e) {
			result = "委派任务失败，系统错误！";
		}
		return result;
	}
	
	/**
	 * 转办任务，办理完成后，流程会继续向下走。
	 * @param taskId
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "/process/transferTask/{taskId}")
	@ResponseBody
	public String transferTask(@PathVariable("taskId") String taskId, @RequestParam("userId") String userId){
		String result = "";
		try {
			this.processService.doTransferTask(userId, taskId);
			result = "转办任务成功！";
		} catch (ActivitiObjectNotFoundException e){
			result = "此任务不存在！委派任务失败！";
		} catch (Exception e) {
			result = "转办任务失败，系统错误！";
		}
		return result;
	}
	
	/**
	 * 撤销任务
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/process/revoke/{taskId}/{processInstanceId}", method = RequestMethod.POST, produces = "application/text;charset=UTF-8")
	@ResponseBody
	public String revoke(@PathVariable("taskId") String taskId, @PathVariable("processInstanceId") String processInstanceId) throws Exception{
		String result = "";
		
		try {
			Integer revokeFlag = this.processService.revoke(taskId, processInstanceId);
//			Integer revokeFlag = this.revokeTaskService.revoke(taskId, processInstanceId);
//			Command<Integer> cmd = new RevokeTask(taskId, processInstanceId);
//			Integer revokeFlag = this.processEngine.getManagementService().executeCommand(cmd);
			
			
			if(revokeFlag == 0){
				HistoricTaskVO historicTaskVO = new HistoricTaskVO();
				historicTaskVO.setTaskId(taskId);
				historicTaskVO.setDeleteReason(StaticConst.getInfo("chexiaoApply"));//撤销	
				processBaseService.updateHistoricTask(historicTaskVO);
				result = "撤销任务成功！";
			}else if(revokeFlag == 1){
				result = "撤销任务失败 - [ 此审批流程已结束! ]";
			}else if(revokeFlag == 2){
				result = "撤销任务失败 - [ 下一结点已经通过,不能撤销! ]";
			}
		} catch (Exception e) {
			result = "撤销任务失败 - [ 内部错误！]";
			throw e;
		}
		return result;
	}
	
    /**
     * 跳转流程管理页面
     * @return
     * @throws Exception
     */
    @RequestMapping("/process/toListProcessManager")
    public String toListProcessRunning() throws Exception{
    	return "workflow/list_process_manager";
    }
    
    /**
     * 管理运行中的流程
     * @param model
     * @return
     * @throws Exception
     */
//    @RequiresPermissions("admin:process:*")
    @RequestMapping(value="/process/runningProcess")
    @ResponseBody
    public List<ProcessInstanceEntity> listRuningProcess(
    			@RequestParam(value = "page", required = false) Integer page,
			  	@RequestParam(value = "rows", required = false) Integer rows) throws Exception{
    	List<ProcessInstance> list = this.processService.listRuningProcess();
    	List<ProcessInstanceEntity> pieList = new ArrayList<ProcessInstanceEntity>();
    	for(ProcessInstance processInstance : list){
    		ProcessInstanceEntity pie = new ProcessInstanceEntity();
    		pie.setId(processInstance.getId());
    		pie.setProcessInstanceId(processInstance.getProcessInstanceId());
    		pie.setProcessDefinitionId(processInstance.getProcessDefinitionId());
    		pie.setActivityId(processInstance.getActivityId());
    		pie.setSuspended(processInstance.isSuspended());
    		
    		ProcessDefinitionCache.setRepositoryService(this.repositoryService);
    		String taskName = ProcessDefinitionCache.getActivityName(processInstance.getProcessDefinitionId(), processInstance.getActivityId());
    		pie.setTaskName(taskName);
    		pieList.add(pie);
    		
    	}
    	return pieList;
    }
    
    /**
     * 管理已结束的流程
     *
     * @return
     * @throws Exception 
     */
//    @RequiresPermissions("admin:process:*")
    @RequestMapping(value = "/process/finishedProcess")
    @ResponseBody
    public List<Object> findFinishedProcessInstances(
    		@RequestParam(value = "page", required = false) Integer page,
    		@RequestParam(value = "rows", required = false) Integer rows) throws Exception {
    	List<Object> jsonList=new ArrayList<Object>(); 
    	List<BaseVO> processList = this.processService.findFinishedProcessInstances();
    	for(BaseVO base : processList){
    		Map<String, Object> map=new HashMap<String, Object>();
    		map.put("businessType", base.getBusinessType());
    		map.put("user_name", base.getUser_name());
    		map.put("title", base.getTitle());
    		map.put("startTime", base.getHistoricProcessInstance().getStartTime());
    		map.put("endTime", base.getHistoricProcessInstance().getEndTime());
    		map.put("deleteReason", base.getHistoricProcessInstance().getDeleteReason());
    		map.put("version", base.getProcessDefinition().getVersion());
    		jsonList.add(map);
    	}
    	return jsonList;
    }
    
    /**
     * 跳转流程定义页面
     * @return
     * @throws Exception
     */
    @RequestMapping("/process/toListProcessInstance")
    public String toListProcess() throws Exception{
    	return "workflow/list_process_instance";
    }
    
    /**
     * 流程定义的加载
     * @param page
     * @param rows
     * @return
     * @throws Exception
     */
	@RequestMapping("/process/listProcess")
	@ResponseBody
    public List<com.congmai.zhgj.web.model.ProcessDefinitionEntity> listProcess(
    		@RequestParam(value = "page", required = false) Integer page,
    		@RequestParam(value = "rows", required = false) Integer rows) throws Exception{
    	ProcessDefinitionQuery processDefinitionQuery = repositoryService.createProcessDefinitionQuery().orderByDeploymentId().desc();
    	Page<Object[]> p = new Page<Object[]>(page, rows);
    	int[] pageParams = p.getPageParams(processDefinitionQuery.list().size());
    	List<ProcessDefinition> processDefinitionList = processDefinitionQuery.listPage(pageParams[0], pageParams[1]);
    	
    	List<com.congmai.zhgj.web.model.ProcessDefinitionEntity> pdList = new  ArrayList<com.congmai.zhgj.web.model.ProcessDefinitionEntity>();
    	for (ProcessDefinition processDefinition : processDefinitionList) {
    		com.congmai.zhgj.web.model.ProcessDefinitionEntity pd = new com.congmai.zhgj.web.model.ProcessDefinitionEntity();
            String deploymentId = processDefinition.getDeploymentId();
            Deployment deployment = repositoryService.createDeploymentQuery().deploymentId(deploymentId).singleResult();
            //封装到ProcessDefinitionEntity中
            pd.setId(processDefinition.getId());
            pd.setName(processDefinition.getName());
            pd.setKey(processDefinition.getKey());
            pd.setDeploymentId(processDefinition.getDeploymentId());
            pd.setVersion(processDefinition.getVersion());
            pd.setResourceName(processDefinition.getResourceName());
            pd.setDiagramResourceName(processDefinition.getDiagramResourceName());
            pd.setDeploymentTime(deployment.getDeploymentTime());
            pd.setSuspended(processDefinition.isSuspended());
            pdList.add(pd);
        }
    	return pdList;
    }
	
	
    /**
     * 激活、挂起流程定义-根据processDefinitionId
     * @param status
     * @param processInstanceId
     * @param redirectAttributes
     * @return
     * @throws Exception
     */
//    @RequiresPermissions("admin:process:suspend,active")
    @RequestMapping(value = "/process/updateProcessStatusByProDefinitionId")
    @ResponseBody
    public String updateProcessStatusByProDefinitionId(
    		@RequestParam("status") String status,
    		@RequestParam("processDefinitionId") String processDefinitionId) throws Exception{
    	//如果用/{status}/{processDefinitionId} rest风格，@PathVariable获取的processDefinitionId 为com.zml.oa,实际是com.zml.oa.vacation:1:32529.难道是BUG?
    	String result = "";
    	if (status.equals("active")) {
            repositoryService.activateProcessDefinitionById(processDefinitionId, true, null);
            result = "已激活ID为[" + processDefinitionId + "]的流程定义。";
        } else if (status.equals("suspend")) {
            repositoryService.suspendProcessDefinitionById(processDefinitionId, true, null);
            result = "已挂起ID为[" + processDefinitionId + "]的流程定义。";
        }
    	return result;
    }
    
    /**
     * 激活、挂起流程实例-根据processInstanceId
     * @param status
     * @param processInstanceId
     * @param redirectAttributes
     * @return
     * @throws Exception
     */
//    @RequiresPermissions("admin:process:suspend,active")
    @RequestMapping(value = "/process/updateProcessStatusByProInstanceId/{status}/{processInstanceId}")
    @ResponseBody
    public String updateProcessStatusByProInstanceId(
    		@PathVariable("status") String status, 
    		@PathVariable("processInstanceId") String processInstanceId,
            RedirectAttributes redirectAttributes) throws Exception{
    	String result = "";
    	if (status.equals("active")) {
    		this.processService.activateProcessInstance(processInstanceId);
//          redirectAttributes.addFlashAttribute("message", "已激活ID为[ " + processInstanceId + " ]的流程实例。");
    		result = "已激活ID为[" + processInstanceId + "]的流程实例。";
    	} else if (status.equals("suspend")) {
        	this.processService.suspendProcessInstance(processInstanceId);
//          redirectAttributes.addFlashAttribute("message", "已挂起ID为[ " + processInstanceId + " ]的流程实例。");
        	result = "已挂起ID为[" + processInstanceId + "]的流程实例。";
    	}
    	return result;
    }
    
    /**
     * 部署全部流程
     *
     * @return
     * @throws Exception
     */
//    @RequiresPermissions("admin:process:*")
    @RequestMapping(value = "/process/redeploy/all")
    @ResponseBody
    public String redeployAll(@Value("#{APP_PROPERTIES['export.diagram.path']}") String exportDir, 
				    		HttpServletResponse response) throws Exception {
    	try {
    		List<Deployment> deploymentList = this.repositoryService.createDeploymentQuery().list();
    		//删除现有所有流程实例
    		for(Deployment deployment : deploymentList){
    			String deploymentId = deployment.getId();
    			this.repositoryService.deleteDeployment(deploymentId, true);
    		}
    		//重新部署全部流程实例
    		//方法一：通过classpath/deploy目录下的.zip或.bar文件部署
    		workflowProcessDefinitionService.deployAllFromClasspath(exportDir);
    		
    		//方法二：通过classpath/bpmn下的流程描述文件部署-流程图错乱，一直提倡用打包部署没有任何问题。
//        	workflowProcessDefinitionService.redeployBpmn(exportDir);

    		return "已重新部署全部流程！";
		} catch (Exception e) {
			return "重新部署流程失败！";
		}
    }
    
    /**
     * 部署单个流程
     *
     * @return
     * @throws Exception
     */
//    @RequiresPermissions("admin:process:*")
    @RequestMapping(value = "/process/redeploy/single")
    @ResponseBody
    public String redeploySingle(@Value("#{APP_PROPERTIES['export.diagram.path']}") String exportDir,
    							@RequestParam("resourceName") String resourceName,
    							@RequestParam(value = "diagramResourceName", required = false) String diagramResourceName,
    							@RequestParam("deploymentId") String deploymentId) throws Exception {
    	String result = "";
        try {
        	this.repositoryService.deleteDeployment(deploymentId, true);
        	//方法一：通过classpath/deploy目录下的.zip或.bar文件部署
        	String processKey = resourceName.substring(0, resourceName.indexOf('.'))+".zip";
        	this.workflowProcessDefinitionService.redeploySingleFrom(exportDir, processKey);
        	//方法二：通过classpath/bpmn下的流程描述文件部署--流程图错乱，一直提倡用打包部署没有任何问题。
//        	workflowProcessDefinitionService.redeployBpmn(exportDir, resourceName,diagramResourceName);
        	result = "已重新部署选定流程！";
		} catch (Exception e) {
			result = "部署选定流程失败！";
			throw e;
		}
        return result;
    }
    
    /**
     * 导入部署
     * --@Value用于将一个SpEL表达式结果映射到到功能处理方法的参数上。
     * @RequestParam(value = "file", required = false) required = false时可以不用传递这个参数，默认为true
     * @param exportDir
     * @param file
     * @return
     */
//    @RequiresPermissions("admin:process:*")
    @RequestMapping(value = "/process/deploy")
    @ResponseBody
    public String deploy(@Value("#{APP_PROPERTIES['export.diagram.path']}") String exportDir, 
    					  @RequestParam(value = "file", required = false) MultipartFile file) {
    	//@Value("${export.diagram.path}")
    	//@Value("#{APP_PROPERTIES['export.diagram.path']}") String exportDir,
    	String result = "";
        String fileName = file.getOriginalFilename();
        try {
            InputStream fileInputStream = file.getInputStream();
            Deployment deployment = null;

            String extension = FilenameUtils.getExtension(fileName);
            if (extension.equals("zip") || extension.equals("bar")) {
                ZipInputStream zip = new ZipInputStream(fileInputStream);
                deployment = this.repositoryService.createDeployment().addZipInputStream(zip).deploy();
            } else {
                deployment = this.repositoryService.createDeployment().addInputStream(fileName, fileInputStream).deploy();
            }

            List<ProcessDefinition> list = this.repositoryService.createProcessDefinitionQuery().deploymentId(deployment.getId()).list();

            for (ProcessDefinition processDefinition : list) {
                WorkflowUtils.exportDiagramToFile(this.repositoryService, processDefinition, exportDir);
            }
            result = "流程部署成功！";
        } catch (Exception e) {
        	result = "流程部署失败！";
            logger.error("error on deploy process, because of file input stream", e);
        }

        return result;
    }
    
    /**
     * 删除部署的流程，级联删除流程实例 true。
     * 不管是否指定级联删除，部署的相关数据均会被删除，这些数据包括流程定义的身份数据（IdentityLink）、流程定义数据（ProcessDefinition）、流程资源（Resource）
     * 部署数据（Deployment）。
     * 如果设置级联(true)，则会删除流程实例数据（ProcessInstance）,其中流程实例也包括流程任务（Task）与流程实例的历史数据；如果设置flase 将不会级联删除。
     * 如果数据库中已经存在流程实例数据，那么将会删除失败，因为在删除流程定义时，流程定义数据的ID已经被流程实例的相关数据所引用。
     *
     * @param deploymentId 流程部署ID
     */
    @RequestMapping(value = "/process/delete")
    @ResponseBody
    public String delete(@RequestParam("deploymentId") String deploymentId) {
    	this.repositoryService.deleteDeployment(deploymentId, true);
        return  "删除成功！";
    }
    
    
    /**
     * 转换为model
     * @param processDefinitionId
     * @return
     * @throws UnsupportedEncodingException
     * @throws XMLStreamException
     */
//    @RequiresPermissions("admin:process:*")
    @RequestMapping(value = "/process/convert_to_model")
    @ResponseBody
    public String convertToModel(@RequestParam("processDefinitionId") String processDefinitionId)
            throws UnsupportedEncodingException, XMLStreamException {
    	ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
                .processDefinitionId(processDefinitionId).singleResult();
        InputStream bpmnStream = repositoryService.getResourceAsStream(processDefinition.getDeploymentId(),
                processDefinition.getResourceName());
        XMLInputFactory xif = XMLInputFactory.newInstance();
        InputStreamReader in = new InputStreamReader(bpmnStream, "UTF-8");
        XMLStreamReader xtr = xif.createXMLStreamReader(in);
        BpmnModel bpmnModel = new BpmnXMLConverter().convertToBpmnModel(xtr);

        BpmnJsonConverter converter = new BpmnJsonConverter();
        com.fasterxml.jackson.databind.node.ObjectNode modelNode = converter.convertToJson(bpmnModel);
        org.activiti.engine.repository.Model modelData = repositoryService.newModel();
        modelData.setKey(processDefinition.getKey());
        modelData.setName(processDefinition.getResourceName());
        modelData.setCategory(processDefinition.getDeploymentId());

        ObjectNode modelObjectNode = new ObjectMapper().createObjectNode();
        modelObjectNode.put(ModelDataJsonConstants.MODEL_NAME, processDefinition.getName());
        modelObjectNode.put(ModelDataJsonConstants.MODEL_REVISION, 1);
        modelObjectNode.put(ModelDataJsonConstants.MODEL_DESCRIPTION, processDefinition.getDescription());
        modelData.setMetaInfo(modelObjectNode.toString());

        repositoryService.saveModel(modelData);

        repositoryService.addModelEditorSource(modelData.getId(), modelNode.toString().getBytes("utf-8"));

        return "转换成功！请到[ 流程设计模型 ]菜单中查看！";
    }
//    @RequiresPermissions("user:listApply")
    @RequestMapping(value = "/process/toListApply")
    public String toListApply(){
    	return "apply/list_apply";
    }
    
    /**
     * 用户查看请假申请、薪资调整申请、报销申请 - easyui
     * @param page
     * @param rows
     * @param businessType
     * @param session
     * @return
     * @throws Exception
     */
   // @RequiresPermissions("user:*:list") //process:vacation,salary,expense:running
    @RequestMapping(value="/process/runingProcessInstance/{businessType}/list")
    @ResponseBody
    public List<Object> getRuningProcessInstance(
    		@PathVariable("businessType") String businessType) throws Exception{
    	User user = UserUtil.getUserFromSession();
    	List<BaseVO> baseVO = new ArrayList<BaseVO>();
    	if(BaseVO.VACATION.equals(businessType)){
    		//请假
    		baseVO = this.processService.listRuningVacation(user);
    	}
    	List<Object> jsonList=new ArrayList<Object>(); 
    	for(BaseVO base : baseVO){
    		Map<String, Object> map=new HashMap<String, Object>();
    		map.put("taskName", base.getTask().getName());
    		map.put("taskCreateTime", base.getTask().getCreateTime());
    		map.put("userName", base.getUser_name());
    		map.put("title", base.getTitle());
    		map.put("pd_version", base.getProcessDefinition().getVersion());
    		map.put("pi_id", base.getProcessInstance().getId());
    		map.put("pi_processDefinitionId", base.getProcessInstance().getProcessDefinitionId());
    		map.put("pi_suspended", base.getProcessInstance().isSuspended());
    		map.put("businessType", base.getBusinessType());
    		map.put("businessKey", base.getBusinessKey());
    		jsonList.add(map);
    	}
    	return jsonList;
    }
    
    /**
     * 任务跳转（包括回退和向前）至指定活动节点
     * @param currentTaskId
     * @param targetTaskDefinitionKey
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/process/jumpTask")
    @ResponseBody
    public String jumpTargetTask(@RequestParam("taskId") String currentTaskId, @RequestParam("taskDefinitionKey") String targetTaskDefinitionKey) throws Exception{
    	String result = "";
    	try {
			this.processService.moveTo(currentTaskId, targetTaskDefinitionKey);
			result = "任务跳转成功！";
		} catch (Exception e) {
			result = "任务跳转失败！";
		}
    	return result;
    }
    
    /**
     * 测试 动态添加流程信息
     * @throws Exception
     */
    @RequestMapping(value = "/addProcessByDynamic")
    public void addProcessByDynamic() throws Exception {
    	this.processService.addProcessByDynamic();
    }
}
