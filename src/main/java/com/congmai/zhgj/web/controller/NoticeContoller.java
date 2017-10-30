package com.congmai.zhgj.web.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.activiti.engine.ActivitiException;
import org.activiti.engine.ActivitiObjectNotFoundException;
import org.activiti.engine.IdentityService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alibaba.fastjson.JSON;
import com.congmai.zhgj.core.feature.orm.mybatis.Page;
import com.congmai.zhgj.core.util.ApplicationUtils;
import com.congmai.zhgj.core.util.BeanUtils;
import com.congmai.zhgj.core.util.Constants;
import com.congmai.zhgj.core.util.DateUtil;
import com.congmai.zhgj.core.util.MessageConstants;
import com.congmai.zhgj.core.util.UserUtil;
import com.congmai.zhgj.web.model.BaseVO;
import com.congmai.zhgj.web.model.CommentVO;
import com.congmai.zhgj.web.model.Notice;
import com.congmai.zhgj.web.model.TakeDelivery;
import com.congmai.zhgj.web.model.TakeDeliveryParams;
import com.congmai.zhgj.web.model.User;
import com.congmai.zhgj.web.service.IProcessService;
import com.congmai.zhgj.web.service.NoticeService;
import com.congmai.zhgj.web.service.UserCompanyService;

@Controller
@RequestMapping("notice")
public class NoticeContoller {
	
	private Log logger = LogFactory.getLog(NoticeContoller.class);
	
	@Autowired
	private NoticeService noticeService;
	
	@Autowired
	private UserCompanyService userCompanyService;
	
	@Autowired
	protected RuntimeService runtimeService;
	
    @Autowired
    protected IdentityService identityService;
	@Autowired
	protected TaskService taskService;

	@Autowired
	private IProcessService processService;
	
	
	@RequestMapping("noticeManage")
	public String noticeManage() {
		
		return "notice/noticeManage";
	}
	
    /**
     * @Description (公告申请)
     * @param request
     * @return
     */
    @RequestMapping("noticeApply")
    public String noticeApply(HttpServletRequest request) {
    	return "notice/noticeApply";
    }
    
    /**
     * @Description (公告审批)
     * @param request
     * @return
     */
    @RequestMapping("noticeAudit")
    public String noticeAudit(HttpServletRequest request) {
    	return "notice/noticeAudit";
    }
    
    /**
     * @Description (重新申请)
     * @param request
     * @return
     */
    @RequestMapping("noticeAdjustment")
    public String noticeAdjustment(HttpServletRequest request) {
    	return "notice/noticeAdjustment";
    }

	
    /**
     * @Description (获取列表数据)
     * @param request
     * @return
     */
    @RequestMapping(value="noticeList",method=RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Map<String,Object>> noticeList(Map<String, Object> map,HttpServletRequest request,Notice notice) {
    	//远程分页代码
    	/*try {
    		params = URLDecoder.decode(params, "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	 ObjectMapper objectMapper = new ObjectMapper();
    	 objectMapper.configure(org.codehaus.jackson.map.DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
  	   		DataTablesParams  dataTablesParams = null;
		   try {
			   JSONObject a = JSONObject.fromObject(params);
			   dataTablesParams = objectMapper.readValue(params,DataTablesParams.class);
			} catch (JsonParseException e) {
				System.out.println(this.getClass()+"---------"+ e.getMessage());
			} catch (JsonMappingException e) {
				System.out.println(this.getClass()+"---------"+ e.getMessage());
			} catch (IOException e) {
				System.out.println(this.getClass()+"---------"+ e.getMessage());
			} catch (Exception e) {
		    	System.out.println(this.getClass()+"---------"+ e.getMessage());
			}
		 company.setPageIndex(dataTablesParams.getStart());
		 company.setPageSize(dataTablesParams.getLength());*/
    	
    	notice.setPageIndex(0);
    	notice.setPageSize(-1);
		 //获取session中的user
		//User user = UserUtil.getUserFromSession();
		
		Page<Notice> notices = new Page<Notice>();
		//if(user !=null){
			
			//notice.setComIds(userCompanyService.getComIdsByUserId(String.valueOf(user.getUserId())));//获取用户的企业ID
		notices = noticeService.selectByPage(notice);
	    	//List<Company> companys = companyService.selectByPage(company).getResult();
			// 封装datatables数据返回到前台
		//}
    	
		Map<String,Object> pageMap = new HashMap<String,Object>();
		pageMap.put("draw", 1);
		pageMap.put("recordsTotal", notice==null?0:notices.getTotalCount());
		pageMap.put("recordsFiltered", notice==null?0:notices.getTotalCount());
		pageMap.put("data", notices.getResult());
		return new ResponseEntity<Map<String,Object>>(pageMap, HttpStatus.OK);
    }
    
    @RequestMapping(value="myNoticeList",method=RequestMethod.POST)
    @ResponseBody
    public Page<Notice> myNoticeList(Notice notice){
    	Page<Notice> page = new Page<Notice>();
    	try{
    		User user = UserUtil.getUserFromSession();
    		String companyType = userCompanyService.getUserComType(user.getUserId().toString());
    		page = this.noticeService.selectMyNoticeByPage(notice, user.getUserId().toString(), companyType);
    		if(CollectionUtils.isNotEmpty(page.getResult())){
    			for(Notice n : page.getResult()){
    				n.setRelaseDate(DateUtil.getInterval(DateUtil.format("yyyy-MM-dd HH:mm:ss",n.getUpdateTime())));
    			}
    		}
    	}catch(Exception e){
    		logger.warn(e.getMessage(), e);
    	}
    	return page;
    }
    
	  /**
     * @Description (公告新增页面)
     * @param request
     * @return
     */
    @RequestMapping("noticeAdd")
    public String noticeAdd(HttpServletRequest request) {
        return "notice/noticeAdd";
    }

    
    /**
     * @Description (保存)
     * @param request
     * @return
     */
    @RequestMapping(value="saveNotice")
    @ResponseBody
    public String saveNotice(Map<String, Object> map,Notice notice,HttpServletRequest request) {
    	String flag ="0"; //默认失败

        	try{
        		Subject currentUser = SecurityUtils.getSubject();
        		String currenLoginName = currentUser.getPrincipal().toString();//获取当前登录用户名
        		if(StringUtils.isEmpty(notice.getSerialNum())){
        			notice.setSerialNum(ApplicationUtils.random32UUID());
        			notice.setNoticeNum("NO"+ApplicationUtils.getFromNumber());
        			notice.setCreateTime(new Date());
        			notice.setCreator(currenLoginName);
        			notice.setUpdateTime(new Date());
        			notice.setUpdater(currenLoginName);
        			notice.setPublisher(currenLoginName);
        			notice.setDelFlg("0");
        			notice.setStatus("0");
        			noticeService.insert(notice);
        		}else{
        			notice.setUpdateTime(new Date());
        			notice.setUpdater(currenLoginName);
        			noticeService.update(notice);
        		}
        		
        		flag = "1";
        	}catch(Exception e){
        		logger.warn(e.getMessage(), e);
        	}
    	return flag;
    }
    
    
    /**
     * @Description (跳转至查看公告页面)
     * @param request
     * @return
     */
    @RequestMapping(value="noticeView")
    public String noticeView(Map<String, Object> map,String serialNum,HttpServletRequest request) {
    	
    	return "notice/noticeView";
    }
    
    /**
     * @Description (跳转至个人公告页面)
     * @param request
     * @return
     */
    @RequestMapping(value="myNotice")
    public String myNotice(Map<String, Object> map,HttpServletRequest request) {
    	return "notice/myNotice";
    }

    
    /**
     * @Description (查看公告)
     * @param request
     * @return
     */
    @RequestMapping(value="getNoticeInfo")
    @ResponseBody
    public Notice getNoticeInfo(String serialNum,HttpServletRequest request) {
    	Notice notice = null;
    	try{
    		if(StringUtils.isNotEmpty(serialNum)){
    			notice = noticeService.selectById(serialNum);
    			if(notice!=null){
    				notice.setRelaseDate(DateUtil.getInterval(DateUtil.format("yyyy-MM-dd HH:mm:ss",notice.getUpdateTime())));
    			}
    		}
    		
    	}catch(Exception e){
    		logger.warn(e.getMessage(),e);
    		return null;
    	}
    	return notice;
    }

    /**
     * @Description (删除公告信息)
     * @param request
     * @return
     */
    @RequestMapping(value="deleteNoticeBatch",method=RequestMethod.POST)
    @ResponseBody
    public String deleteNoticeBatch(Map<String, Object> map,@RequestBody String serialNums,HttpServletRequest request) {
    	String flag = "0"; //默认失败
    	try{
    		if(StringUtils.isNotEmpty(serialNums)){
    			List<String> serialNumArray  = Arrays.asList(serialNums.split(","));
    			noticeService.deleteBatch(serialNumArray,UserUtil.getUserFromSession().getUserName());
    		}
    	}catch(Exception e){
    		System.out.println(e.getMessage());
    		flag = "1";
    	}
    	return flag;
    }
    
    /**
     * @Description (删除公告信息)
     * @param request
     * @return
     */
    @RequestMapping(value="deleteMyNotice",method=RequestMethod.POST)
    @ResponseBody
    public String deleteMyNotice(Map<String, Object> map,@RequestBody String serialNum,HttpServletRequest request) {
    	String flag = "0"; //默认失败
    	try{
    		if(StringUtils.isNotEmpty(serialNum)){
    			noticeService.deleteNoticeShare(serialNum,UserUtil.getUserFromSession().getUserId().toString());
    		}
    	}catch(Exception e){
    		System.out.println(e.getMessage());
    		flag = "1";
    	}
    	return flag;
    }
    
    /**
     * @Description (删除公告信息)
     * @param request
     * @return
     */
    @RequestMapping(value="readMyNotice",method=RequestMethod.POST)
    @ResponseBody
    public String readMyNotice(Map<String, Object> map,@RequestBody String serialNum,HttpServletRequest request) {
    	String flag = "0"; //默认失败
    	try{
    		if(StringUtils.isNotEmpty(serialNum)){
    			noticeService.readNoticeShare(serialNum,UserUtil.getUserFromSession().getUserId().toString());
    		}
    	}catch(Exception e){
    		System.out.println(e.getMessage());
    		flag = "1";
    	}
    	return flag;
    }
    
    
    /**************************************************审批相关******************************************************/
    private String startNotice(Notice notice){
    	// 用来设置启动流程的人员ID，引擎会自动把用户ID保存到activiti:initiator中
        identityService.setAuthenticatedUserId(notice.getUserId().toString());
        Map<String, Object> variables = new HashMap<String, Object>();
        variables.put("entity", notice);
        //由userTask自动分配审批权限
//        if(vacation.getDays() <= 3){
//        	variables.put("auditGroup", "manager");
//        }else{
//        	variables.put("auditGroup", "director");
//        }
        String businessKey = notice.getBusinessKey();
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(Constants.NOTICE_KEY, businessKey, variables);
        String processInstanceId = processInstance.getId();
        notice.setProcessInstanceId(processInstanceId);
        this.noticeService.updateByPrimaryKeySelective(notice);
        logger.info("processInstanceId: "+processInstanceId);
        //最后要设置null，就是这么做，还没研究为什么
        this.identityService.setAuthenticatedUserId(null);
        return processInstanceId;
    }
    
    
    @RequestMapping(value="applyNotice",method=RequestMethod.POST)
    @ResponseBody
    public String applyNotice(Map<String, Object> map,Notice notice,HttpServletRequest request){
    	String flag = "0"; //默认失败
    	//String flag = confirmTakeDelivery(map, params, request);
    	//if("1".equals(flag)){
    		 try {
    			 
    			 	
    				User user = UserUtil.getUserFromSession();
    				//String reason = (String) map.get("reason");
    				//String serialNum = (String) map.get("serialNum");

    				notice.setUserId(user.getUserId());
    				notice.setUser_name(user.getUserName());
    				notice.setTitle(user.getUserName() + " 的公告申请");
    				notice.setBusinessType(Constants.NOTICE);
    				notice.setStatus(BaseVO.PENDING);
    				notice.setApplyDate(new Date());
    				notice.setBusinessKey(notice.getSerialNum());
    				//notice.setReason(reason);
    				
    			 String processInstanceId = startNotice(notice);
 //   	            message.setStatus(Boolean.TRUE);
//   				message.setMessage("请假流程已启动，流程ID：" + processInstanceId);
    			 logger.info("processInstanceId: " + processInstanceId);
    			 flag = "1";
    	        } catch (ActivitiException e) {
//    	        	message.setStatus(Boolean.FALSE);
    	            if (e.getMessage().indexOf("no processes deployed with key") != -1) {
    	                logger.warn("没有部署流程!", e);
//    	    			message.setMessage("没有部署流程，请联系系统管理员，在[流程定义]中部署相应流程文件！");
    	            } else {
    	                logger.error("启动公告流程失败：", e);
//    	                message.setMessage("启动请假流程失败，系统内部错误！");
    	            }
    	        	//flag = "0";
    	          //  throw e;
    	        } catch (Exception e) {
    	            logger.error("启动公告流程失败：", e);
//    	            message.setStatus(Boolean.FALSE);
//    	            message.setMessage("启动请假流程失败，系统内部错误！");
    	        	//flag = "0";
    	           // throw e;
    	        }
    	//}
    	return flag;
    }
    
	/**
	 * 审批请假流程
	 * 
	 * @param taskId
	 * @param model
	 * @return
	 * @throws NumberFormatException
	 * @throws Exception
	 */
	@RequestMapping(value = "/toApproval/{taskId}", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody Map<String, Object> toApproval(
			@PathVariable("taskId") String taskId)
			throws NumberFormatException, Exception {
		Task task = this.taskService.createTaskQuery().taskId(taskId)
				.singleResult();
		// 根据任务查询流程实例
		String processInstanceId = task.getProcessInstanceId();
		ProcessInstance pi = this.runtimeService.createProcessInstanceQuery()
				.processInstanceId(processInstanceId).singleResult();
		Notice notice = (Notice) this.runtimeService
				.getVariable(pi.getId(), "entity");
		notice.setTask(task);
		notice.setProcessInstanceId(processInstanceId);
		List<CommentVO> commentList = this.processService
				.getComments(processInstanceId);
		String taskDefinitionKey = task.getTaskDefinitionKey();
		logger.info("taskDefinitionKey: " + taskDefinitionKey);
		String result = null;
		if ("modifyApply".equals(taskDefinitionKey)) {
			result = "modify";
		} else {
			result = "audit";
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("actionType", result);
		map.put("notice", notice);
		map.put("commentList", commentList);
		return map;
	}

	@RequestMapping(value = "/complete", method = RequestMethod.POST, produces = "application/text;charset=UTF-8")
	@ResponseBody
	public String complete(@RequestParam("serialNum") String serialNum,
			@RequestParam("content") String content,
			@RequestParam("isPass") Boolean completeFlag,
			@RequestBody@RequestParam(value="params",required=false) String params,
			@RequestParam("taskId") String taskId,
			RedirectAttributes redirectAttributes,HttpServletRequest request) throws Exception {
		User user = UserUtil.getUserFromSession();
		String result = "";
		try {
		
			Map<String, Object> variables = new HashMap<String, Object>();
			variables.put("isPass", completeFlag);
		
			
			Task task = this.taskService.createTaskQuery().taskId(taskId)
					.singleResult();
			String taskDefinitionKey = task.getTaskDefinitionKey();
			Notice notice = null;
			if ("modifyApply".equals(taskDefinitionKey)) {
				
				
				if(!completeFlag){
					notice = this.noticeService.selectByPrimaryKey(serialNum);
					Notice baseNotice = (Notice) this.runtimeService
							.getVariable(notice.getProcessInstanceId(), "entity");
		        	baseNotice.setTitle(baseNotice.getUser_name()
							+ " 的公告申请已取消！");
					notice.setStatus(Notice.CANCEL);
		        	content = "取消申请";
		        	//result = "任务办理完成，已经取消您的请假申请！";
		        	variables.put("entity", baseNotice);
		        }else
		        {
//		        	String flag = confirmTakeDelivery(new HashMap<String, Object>(), params, request);
//					if(!"1".equals(flag)){
//						logger.warn("修改收货信息失败");
//						throw new Exception("修改收货信息失败");
//					}
		        	if(params!=null){
		        		this.noticeService.updateByPrimaryKeySelective(JSON.parseObject(params, Notice.class));
		        	}
					notice = this.noticeService.selectByPrimaryKey(serialNum);
					Notice baseNotice = (Notice) this.runtimeService
							.getVariable(notice.getProcessInstanceId(), "entity");
		        	baseNotice.setTitle(baseNotice.getUser_name()
							+ " 的公告重新申请！");
					notice.setStatus(BaseVO.PENDING);
					variables.put("entity", baseNotice);
		        	content = "重新申请";
		        }
			}else{
				notice = this.noticeService.selectByPrimaryKey(serialNum);
				Notice baseNotice = (Notice) this.runtimeService
						.getVariable(notice.getProcessInstanceId(), "entity");
				if (!completeFlag) {
					baseNotice.setTitle(baseNotice.getUser_name()
							+ " 的公告申请失败,需修改后重新提交！");
					notice.setStatus(BaseVO.APPROVAL_FAILED);
					variables.put("entity", baseNotice);
				}
			}
			// 完成任务
			this.processService.complete(taskId, content, user.getUserId()
					.toString(), variables);

			if (completeFlag) {
				// 此处需要修改，不能根据人来判断审批是否结束。应该根据流程实例id(processInstanceId)来判定。
				// 判断指定ID的实例是否存在，如果结果为空，则代表流程结束，实例已被删除(移到历史库中)
				ProcessInstance pi = this.runtimeService
						.createProcessInstanceQuery()
						.processInstanceId(notice.getProcessInstanceId())
						.singleResult();
				if (BeanUtils.isBlank(pi)) {
					notice.setStatus(Notice.COMPLETE);
					notice.setPublishTime(new Date());
				}
			}

			this.noticeService.updateByPrimaryKeySelective(notice);

			
			
			result = "任务办理完成！";
		} catch (ActivitiObjectNotFoundException e) {
			result = "此任务不存在，请联系管理员！";
			//throw e;
		} catch (ActivitiException e) {
			result = "此任务正在协办，您不能办理此任务！";
			//throw e;
		} catch (Exception e) {
			result = "任务办理失败，请联系管理员！";
			//throw e;
		}
		return result;
	}
	
    /**
     * @Description (未读公告数统计)
     * @param request
     * @return
     */
    @RequestMapping(value="newNoticeCount")
    @ResponseBody
    public String newNoticeCount(Map<String, Object> map,HttpServletRequest request) {
    	int flag = 0; //默认失败
    	try{
    		User user = UserUtil.getUserFromSession();
    		String companyType = userCompanyService.getUserComType(user.getUserId().toString());
    		if(user != null){
    			flag = noticeService.getUnReadNoticeCount(user.getUserId().toString(),companyType);
    		}
    	}catch(Exception e){
    		logger.warn(e.getMessage(), e);
    	}
    	return String.valueOf(flag);
    }
	
}
