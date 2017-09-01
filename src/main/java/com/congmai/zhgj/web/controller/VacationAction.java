package com.congmai.zhgj.web.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.ezmorph.object.DateMorpher;
import net.sf.json.JSONObject;
import net.sf.json.util.JSONUtils;

import org.activiti.engine.ActivitiException;
import org.activiti.engine.ActivitiObjectNotFoundException;
import org.activiti.engine.IdentityService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.apache.log4j.Logger;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.congmai.zhgj.core.util.BeanUtils;
import com.congmai.zhgj.core.util.UserUtil;
import com.congmai.zhgj.web.model.BaseVO;
import com.congmai.zhgj.web.model.CommentVO;
import com.congmai.zhgj.web.model.User;
import com.congmai.zhgj.web.model.Vacation;
import com.congmai.zhgj.web.service.IProcessService;
import com.congmai.zhgj.web.service.IVacationService;

/**
 * 
 * @ClassName VacationAction
 * @Description 请假控制类,没有用动态任务分配
 * @author tanzb
 * @Date 2017年8月24日 上午10:37:57
 * @version 1.0.0
 */
@Controller
@RequestMapping("/vacationAction")
public class VacationAction {
	private static final Logger logger = Logger.getLogger(VacationAction.class);
	
	@Autowired
	private IVacationService vacationService;
	
	@Autowired
	protected RuntimeService runtimeService;
	
    @Autowired
    protected IdentityService identityService;
    
    @Autowired
    protected TaskService taskService;
    
	@Autowired
	private IProcessService processService;
	
	/**
	 * 查询某人的所有请假申请
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("user:vacation:list")
	@RequestMapping("/toList_page")
	public String toList(Model model) throws Exception{
		User user = UserUtil.getUserFromSession();
		List<Vacation> list = this.vacationService.toList(user.getUserId());
//		Pagination pagination = PaginationThreadUtils.get();
//		model.addAttribute("page", pagination.getPageStr());
//		model.addAttribute("vacationList", list);
		return "vacation/list_vacation";
	}	
	
	/**
	 * 跳转添加页面
	 * @param model
	 * @return
	 */
	@RequiresPermissions("user:vacation:toAdd")
	@RequestMapping(value = "/toAdd")
	public String toAdd(){
		return "vacation/add_vacation";
	}	
	
	/**
     * 添加并启动请假流程
     *@RequiresPermissions("user:vacation:doAdd")
     * @param leave
     */	
    @RequestMapping(value = "/doAdd", method = RequestMethod.POST)
    @ResponseBody
	public String doAdd(@RequestBody  JSONObject entity) throws Exception{
        User user = UserUtil.getUserFromSession();
        
        JSONObject jsonVacation = JSONObject.fromObject(entity); 
        String[] dateFormats = new String[] {"yyyy-MM-dd"}; 
        JSONUtils.getMorpherRegistry().registerMorpher(new DateMorpher(dateFormats));         
        Vacation vacation = (Vacation) JSONObject.toBean(jsonVacation,Vacation.class);//序列化对象需要转化
//        Message message = new Message();
        vacation.setUserId(user.getUserId());
        vacation.setUser_name(user.getUserName());
        vacation.setTitle(user.getUserName()+" 的请假申请");
        vacation.setBusinessType(BaseVO.VACATION); 			//业务类型：请假申请
        vacation.setStatus(BaseVO.PENDING);					//审批中
        vacation.setApplyDate(new Date());
        this.vacationService.doAdd(vacation);
        String businessKey = vacation.getId().toString();
        vacation.setBusinessKey(businessKey);
        try {
        	String processInstanceId = this.processService.startVacation(vacation);
//            message.setStatus(Boolean.TRUE);
//			message.setMessage("请假流程已启动，流程ID：" + processInstanceId);
            logger.info("processInstanceId: "+processInstanceId);
        } catch (ActivitiException e) {
//        	message.setStatus(Boolean.FALSE);
            if (e.getMessage().indexOf("no processes deployed with key") != -1) {
                logger.warn("没有部署流程!", e);
//    			message.setMessage("没有部署流程，请联系系统管理员，在[流程定义]中部署相应流程文件！");
            } else {
                logger.error("启动请假流程失败：", e);
//                message.setMessage("启动请假流程失败，系统内部错误！");
            }
            throw e;
        } catch (Exception e) {
            logger.error("启动请假流程失败：", e);
//            message.setStatus(Boolean.FALSE);
//            message.setMessage("启动请假流程失败，系统内部错误！");
            throw e;
        }
		return null;
	}
	
	/**
     * 审批请假流程
     * @param taskId
     * @param model
     * @return
     * @throws NumberFormatException
     * @throws Exception
     */
//	@RequiresPermissions("user:vacation:toApproval") 	//*代表 经理、总监、人力
    @RequestMapping(value = "/toApproval/{taskId}", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody Map<String, Object> toApproval(@PathVariable("taskId") String taskId) throws NumberFormatException, Exception{
    	Task task = this.taskService.createTaskQuery().taskId(taskId).singleResult();
		// 根据任务查询流程实例
    	String processInstanceId = task.getProcessInstanceId();
		ProcessInstance pi = this.runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
		Vacation vacation = (Vacation) this.runtimeService.getVariable(pi.getId(), "entity");
		vacation.setTask(task);
		vacation.setProcessInstanceId(processInstanceId);
		List<CommentVO> commentList = this.processService.getComments(processInstanceId);
		String taskDefinitionKey = task.getTaskDefinitionKey();
		logger.info("taskDefinitionKey: "+taskDefinitionKey);
		String result = null;
		if("modifyApply".equals(taskDefinitionKey)){
			result = "modify";
		}else{
			result = "audit";
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("actionType", result);
		map.put("vacation", vacation);
		map.put("commentList", commentList);
    	return map;
    }
	
	
    /**
     * 完成任务
     * @param content
     * @param completeFlag
     * @param taskId
     * @param redirectAttributes
     * @param session
     * @return
     * @throws Exception
     */
//	@RequiresPermissions("user:vacation:complate")  //数据库中权限字符串为user:*:complate， 通配符*匹配到vacation所以有权限操作 
    @RequestMapping(value = "/complate/{taskId}", method = RequestMethod.POST, produces = "application/text;charset=UTF-8")
	@ResponseBody
    public String complate(
    		@RequestParam("vacationId") Integer vacationId,
    		@RequestParam("content") String content,
    		@RequestParam("completeFlag") Boolean completeFlag,
    		@PathVariable("taskId") String taskId, 
    		RedirectAttributes redirectAttributes) throws Exception{
    	User user = UserUtil.getUserFromSession();
    	String result = "";
    	try {
    		Vacation vacation = this.vacationService.findById(vacationId);
            Vacation baseVacation = (Vacation) this.runtimeService.getVariable(vacation.getProcessInstanceId(), "entity");
    		Map<String, Object> variables = new HashMap<String, Object>();
    		variables.put("isPass", completeFlag);
    		if(!completeFlag){
    			baseVacation.setTitle(baseVacation.getUser_name()+" 的请假申请失败,需修改后重新提交！");
    			vacation.setStatus(BaseVO.APPROVAL_FAILED);
    			variables.put("entity", baseVacation);
    		}
    		// 完成任务
    		this.processService.complete(taskId, content, user.getUserId().toString(), variables);
    		
    		if(completeFlag){
    			//此处需要修改，不能根据人来判断审批是否结束。应该根据流程实例id(processInstanceId)来判定。
    			//判断指定ID的实例是否存在，如果结果为空，则代表流程结束，实例已被删除(移到历史库中)
    			ProcessInstance pi = this.runtimeService.createProcessInstanceQuery().processInstanceId(vacation.getProcessInstanceId()).singleResult();
    			if(BeanUtils.isBlank(pi)){
    				vacation.setStatus(BaseVO.APPROVAL_SUCCESS);
    			}
    		}
    		
    		this.vacationService.doUpdate(vacation);
    		
    		result = "任务办理完成！";
		} catch (ActivitiObjectNotFoundException e) {
			result = "此任务不存在，请联系管理员！";
			throw e;
		} catch (ActivitiException e) {
			result = "此任务正在协办，您不能办理此任务！";
			throw e;
		} catch (Exception e) {
			result = "任务办理失败，请联系管理员！";
			throw e;
		}
		return result;
    }
	
	
	/**
	 * 调整请假申请
	 * @param vacation
	 * @param taskId
	 * @param processInstanceId
	 * @param reApply
	 * @param session
	 * @return
	 * @throws Exception
	 */
//	@RequiresPermissions("user:vacation:modify")
	@RequestMapping(value = "/modifyVacation/{taskId}", method = RequestMethod.POST, produces = "application/text;charset=UTF-8")
	@ResponseBody
	public String modifyVacation(
			@PathVariable("taskId") String taskId,
			@RequestParam("processInstanceId") String processInstanceId,
			@RequestParam("reApply") Boolean reApply,
			@RequestParam("vacationId") Integer vacationId,
			@RequestParam("beginDate") String beginDate,
			@RequestParam("endDate") String endDate,
			@RequestParam("days") String days,
			@RequestParam("vacationType") Integer vacationType,
			@RequestParam("reason") String reason) throws Exception{
		String result = "";
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");//小写的mm表示的是分钟 
		User user = UserUtil.getUserFromSession();
		Vacation vacation = new Vacation();
		vacation.setId(vacationId);
		vacation.setBeginDate(sdf.parse(beginDate));
		vacation.setEndDate(sdf.parse(endDate));
		vacation.setVacationType(vacationType);
		vacation.setReason(reason);
		vacation.setDays(Integer.parseInt(days));
		
        Map<String, Object> variables = new HashMap<String, Object>();
        vacation.setUserId(user.getUserId());
        vacation.setUser_name(user.getUserName());
        vacation.setBusinessType(BaseVO.VACATION);
        vacation.setApplyDate(new Date());
        vacation.setBusinessKey(vacationId.toString());
        vacation.setProcessInstanceId(processInstanceId);
        String content = "";
        if(reApply){
        	//修改请假申请
	        vacation.setTitle(user.getUserName()+" 的请假申请！");
	        vacation.setStatus(BaseVO.PENDING);
	        content = "重新申请";
	        //由userTask自动分配审批权限
//	        if(vacation.getDays() <= 3){
//            	variables.put("auditGroup", "manager");
//            }else{
//            	variables.put("auditGroup", "director");
//            }
//	        message.setStatus(Boolean.TRUE);
//			message.setMessage("任务办理完成，请假申请已重新提交！");
	        result = "任务办理完成，请假申请已重新提交！";
        }else{
        	vacation.setTitle(user.getUserName()+" 的请假申请已取消！");
        	vacation.setStatus(BaseVO.APPROVAL_FAILED);
        	content = "取消申请";
        	result = "任务办理完成，已经取消您的请假申请！";
//	        message.setStatus(Boolean.TRUE);
//			message.setMessage("任务办理完成，已经取消您的请假申请！");
        }
        try {
			this.vacationService.doUpdate(vacation);
			variables.put("entity", vacation);
			variables.put("reApply", reApply);
			this.processService.complete(taskId, content, user.getUserId().toString(), variables);
			
		} catch (ActivitiObjectNotFoundException e) {
//			message.setStatus(Boolean.FALSE);
//			message.setMessage("此任务不存在，请联系管理员！");
			result = "此任务不存在，请联系管理员！";
			throw e;
		} catch (ActivitiException e) {
//			message.setStatus(Boolean.FALSE);
//			message.setMessage("此任务正在协办，您不能办理此任务！");
			result = "此任务正在协办，您不能办理此任务！";
			throw e;
		} catch (Exception e) {
//			message.setStatus(Boolean.FALSE);
//			message.setMessage("任务办理失败，请联系管理员！");
			result = "任务办理失败，请联系管理员！";
			throw e;
		}
		
    	return result;
    }
	
	/**
	 * 详细信息
	 * @param id
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("user:vacation:details")
	@RequestMapping(value="/details/{id}")
	public String details(@PathVariable("id") Integer id, Model model) throws Exception{
		Vacation vacation = this.vacationService.findById(id);
		model.addAttribute("vacation", vacation);
		return "/vacation/details_vacation";
	}
	
}
