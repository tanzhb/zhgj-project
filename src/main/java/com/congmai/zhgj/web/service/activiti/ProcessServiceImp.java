package com.congmai.zhgj.web.service.activiti;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.activiti.bpmn.BpmnAutoLayout;
import org.activiti.bpmn.model.ActivitiListener;
import org.activiti.bpmn.model.BaseElement;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.bpmn.model.EndEvent;
import org.activiti.bpmn.model.ExclusiveGateway;
import org.activiti.bpmn.model.Process;
import org.activiti.bpmn.model.SequenceFlow;
import org.activiti.bpmn.model.StartEvent;
import org.activiti.bpmn.model.UserTask;
import org.activiti.engine.ActivitiObjectNotFoundException;
import org.activiti.engine.HistoryService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricProcessInstanceQuery;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.history.HistoricTaskInstanceQuery;
import org.activiti.engine.history.HistoricVariableInstance;
import org.activiti.engine.impl.RepositoryServiceImpl;
import org.activiti.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.activiti.engine.impl.context.Context;
import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.persistence.entity.TaskEntity;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.runtime.ProcessInstanceQuery;
import org.activiti.engine.task.Comment;
import org.activiti.engine.task.DelegationState;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.activiti.image.ProcessDiagramGenerator;
import org.activiti.spring.ProcessEngineFactoryBean;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.congmai.zhgj.core.util.BeanUtils;
import com.congmai.zhgj.core.util.Constants;
import com.congmai.zhgj.log.annotation.OperationLog;
import com.congmai.zhgj.web.activiti.processTask.taskCommand.DeleteActiveTaskCmd;
import com.congmai.zhgj.web.activiti.processTask.taskCommand.RevokeTaskCmd;
import com.congmai.zhgj.web.activiti.processTask.taskCommand.StartActivityCmd;
import com.congmai.zhgj.web.enums.StaticConst;
import com.congmai.zhgj.web.model.BaseVO;
import com.congmai.zhgj.web.model.CommentVO;
import com.congmai.zhgj.web.model.ContractVO;
import com.congmai.zhgj.web.model.Delivery;
import com.congmai.zhgj.web.model.DeliveryVO;
import com.congmai.zhgj.web.model.HistoricTaskVO;
import com.congmai.zhgj.web.model.Invoice;
import com.congmai.zhgj.web.model.OrderInfo;
import com.congmai.zhgj.web.model.PaymentRecord;
import com.congmai.zhgj.web.model.PriceList;
import com.congmai.zhgj.web.model.ProcurementPlan;
import com.congmai.zhgj.web.model.User;
import com.congmai.zhgj.web.model.Vacation;
import com.congmai.zhgj.web.service.DeliveryService;
import com.congmai.zhgj.web.service.IProcessService;
import com.congmai.zhgj.web.service.IVacationService;
import com.congmai.zhgj.web.service.OrderService;
import com.congmai.zhgj.web.service.PayService;
import com.congmai.zhgj.web.service.ProcessBaseService;
import com.congmai.zhgj.web.service.UserService;

/**
 * 
 * @ClassName ProcessServiceImp
 * @Description 流程相关Service
 * @author tanzb
 * @Date 2017年8月23日 下午6:32:04
 * @version 1.0.0
 */
@Service
public class ProcessServiceImp implements IProcessService{

	private static final Logger logger = Logger.getLogger(ProcessServiceImp.class);
	
	@Autowired
	protected RuntimeService runtimeService;
	
    @Autowired
    protected IdentityService identityService;
    
    @Autowired
    protected TaskService taskService;
    
    @Autowired
    protected RepositoryService repositoryService;
    
    @Autowired
    protected HistoryService historyService;
    
	@Autowired
	protected UserService userService;
	
    @Autowired
    ProcessEngineFactoryBean processEngineFactory;

    @Autowired
    ProcessEngineConfiguration processEngineConfiguration;
    
    @Autowired
    protected WorkflowService workflowService;
    
	@Autowired
	private IVacationService vacationService;
	
	@Autowired
	private ProcessEngine processEngine;
	
	@Autowired
    private ProcessBaseService processBaseService;
	
	/**
	 * 应付款service
	 */
	@Autowired
	private PayService payService;
	
	
	@Autowired
	private DeliveryService deliveryService;
	
	
    /**
     * 查询代办任务
     * @param user
     * @param model
     * @return
     */
	@Override
    public List<BaseVO> findTodoTask(User user){
		//taskCandidateOrAssigned查询某个人的待办任务，包含已签收、候选任务<候选人范围和候选组范围>
		TaskQuery taskQuery = this.taskService.createTaskQuery().taskCandidateOrAssigned(user.getUserId().toString());
		List<Task> tasks = taskQuery.orderByTaskCreateTime().desc().list();
		List<BaseVO> taskList = getBaseVOList(tasks);
		return taskList;
    } 

    /**
     * 读取已结束中的流程(admin查看)
     *
     * @return
     */
    @Override
    public List<BaseVO> findFinishedProcessInstances() {
        HistoricProcessInstanceQuery historQuery = historyService.createHistoricProcessInstanceQuery().finished();
    	
		List<HistoricProcessInstance> list = historQuery.orderByProcessInstanceEndTime().desc().list();
		List<BaseVO> processList = new ArrayList<BaseVO>();
		
		for (HistoricProcessInstance historicProcessInstance : list) {
			String processInstanceId = historicProcessInstance.getId();
			List<HistoricVariableInstance> listVar = this.historyService.createHistoricVariableInstanceQuery().processInstanceId(processInstanceId).list();
			for(HistoricVariableInstance var : listVar){
				if("serializable".equals(var.getVariableTypeName()) && "entity".equals(var.getVariableName())){
					BaseVO base = (BaseVO) var.getValue();
					base.setHistoricProcessInstance(historicProcessInstance);
					base.setProcessDefinition(getProcessDefinition(historicProcessInstance.getProcessDefinitionId()));
					processList.add(base);
					break;
				}
			}
		}
		
        return processList;
    }
	
    /**
     * 各个审批人员查看自己完成的任务
     * @param model
     * @return
     * @throws Exception
     */
	@Override
	public List<BaseVO> findFinishedTaskInstances(User user) throws Exception {
		HistoricTaskInstanceQuery historQuery = historyService.createHistoricTaskInstanceQuery().taskAssignee(user.getUserId().toString()).finished();
    	List<HistoricTaskInstance> list = historQuery.orderByHistoricTaskInstanceEndTime().desc().list();
    	List<BaseVO> taskList = new ArrayList<BaseVO>();
    	
    	for(HistoricTaskInstance historicTaskInstance : list){
    		String processInstanceId = historicTaskInstance.getProcessInstanceId();
    		List<HistoricVariableInstance> listVar = this.historyService.createHistoricVariableInstanceQuery().processInstanceId(processInstanceId).list();
			for(HistoricVariableInstance var : listVar){
				if("serializable".equals(var.getVariableTypeName()) && "entity".equals(var.getVariableName())){
					BaseVO base = (BaseVO) var.getValue();
					base.setHistoricTaskInstance(historicTaskInstance);
					base.setProcessDefinition(getProcessDefinition(historicTaskInstance.getProcessDefinitionId()));
					taskList.add(base);
					break;
				}
			}
    	}
		return taskList;
	}
    
    /**
     * 将Task集合转为BaseVO集合
     * @param tasks
     * @return
     */
    protected List<BaseVO> getBaseVOList(List<Task> tasks) {
    	List<BaseVO> taskList = new ArrayList<BaseVO>();
        for (Task task : tasks) {
        	String processInstanceId = task.getProcessInstanceId();
            ProcessInstance processInstance = this.runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).active().singleResult();
            if(BeanUtils.isBlank(processInstance)){
            	//如果有挂起的流程则continue
            	continue;
            }
            //获取当前流程下的key为entity的variable
            BaseVO base = (BaseVO) this.runtimeService.getVariable(processInstance.getId(), "entity");
            base.setTask(task);
            base.setProcessInstance(processInstance);
            base.setProcessDefinition(getProcessDefinition(processInstance.getProcessDefinitionId()));
            taskList.add(base);
        }
    	return taskList;
    }
    
    /**
     * 查询流程定义对象
     *
     * @param processDefinitionId 流程定义ID
     * @return
     */
    protected ProcessDefinition getProcessDefinition(String processDefinitionId) {
        ProcessDefinition processDefinition = this.repositoryService.createProcessDefinitionQuery().processDefinitionId(processDefinitionId).singleResult();
//        logger.info(processDefinition.getVersion());
        return processDefinition;
    }
    
    /**
     * 签收任务
     * @param user
     * @param taskId
     */
	@Override
    public void doClaim(User user, String taskId){
    	this.identityService.setAuthenticatedUserId(user.getUserId().toString());
        this.taskService.claim(taskId, user.getUserId().toString());
    }
	
    /**
     * 委派任务
     */
	@Override
	public void doDelegateTask(String userId, String taskId) throws Exception {
		//API: If no owner is set on the task, the owner is set to the current assignee of the task.
		//OWNER_（委托人）：受理人委托其他人操作该TASK的时候，受理人就成了委托人OWNER_，其他人就成了受理人ASSIGNEE_
		//assignee容易理解，主要是owner字段容易误解，owner字段就是用于受理人委托别人操作的时候运用的字段
		this.taskService.delegateTask(taskId, userId);
	}
	
	/**
	 * 转办任务
	 */
	@Override
	public void doTransferTask(String userId, String taskId) throws Exception {
		Task task = this.taskService.createTaskQuery().taskId(taskId).singleResult();
		if(task != null){
			String assign = task.getAssignee();
			this.taskService.setAssignee(taskId, userId);
			this.taskService.setOwner(taskId, assign);
		}else{
			throw new ActivitiObjectNotFoundException("任务不存在！", this.getClass());
		}
	}
	
	/**
	 * 获取评论
	 * @param processInstanceId
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@Override
    public List<CommentVO> getComments(String processInstanceId) throws Exception{
		// 查询一个任务所在流程的全部评论
		List<Comment> comments = this.taskService.getProcessInstanceComments(processInstanceId);
		List<CommentVO> commnetList = new ArrayList<CommentVO>();
		for(Comment comment : comments){
			User user = this.userService.selectById(new Integer(comment.getUserId()));
			CommentVO vo = new CommentVO();
			vo.setContent(comment.getFullMessage());
			vo.setTime(comment.getTime());
			vo.setUserName(user.getUserName());
			vo.setPosition(user.getPosition());
			commnetList.add(vo);
		}
    	return commnetList;
    }
    
    
    /**
     * 显示流程图,带流程跟踪
     * @param processInstanceId
     * @return
     */
    @Override
    public InputStream getDiagram(String processInstanceId){
    	ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
        BpmnModel bpmnModel = repositoryService.getBpmnModel(processInstance.getProcessDefinitionId());
        List<String> activeActivityIds = runtimeService.getActiveActivityIds(processInstanceId);
        // 不使用spring请使用下面的两行代码
//    	ProcessEngineImpl defaultProcessEngine = (ProcessEngineImpl) ProcessEngines.getDefaultProcessEngine();
//    	Context.setProcessEngineConfiguration(defaultProcessEngine.getProcessEngineConfiguration());

        // 使用spring注入引擎请使用下面的这行代码
        processEngineConfiguration = processEngineFactory.getProcessEngineConfiguration();
        Context.setProcessEngineConfiguration((ProcessEngineConfigurationImpl) processEngineConfiguration);

        //通过引擎生成png图片，并标记当前节点,并把当前节点用红色边框标记出来，弊端和直接部署流程文件生成的图片问题一样-乱码！。
        ProcessDiagramGenerator diagramGenerator = processEngineConfiguration.getProcessDiagramGenerator();
        InputStream imageStream = diagramGenerator.generateDiagram(bpmnModel, "png", activeActivityIds);
    	return imageStream;
    }
    
    /**
     * 显示图片-通过流程ID，，不带流程跟踪(没有乱码问题)
     * @param resourceType
     * @param processInstanceId
     * @return
     */
    @Override
    public InputStream getDiagramByProInstanceId_noTrace(String resourceType, String processInstanceId){
    	
        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionId(processInstance.getProcessDefinitionId())
                .singleResult();

        String resourceName = "";
        if (resourceType.equals("png") || resourceType.equals("image")) {
            resourceName = processDefinition.getDiagramResourceName();
        } else if (resourceType.equals("xml")) {
            resourceName = processDefinition.getResourceName();
        }
        InputStream resourceAsStream = repositoryService.getResourceAsStream(processDefinition.getDeploymentId(), resourceName);
        return resourceAsStream;
    }
    
    /**
     * 显示图片-通过部署ID，不带流程跟踪(没有乱码啊问题)
     * @param resourceType
     * @param processInstanceId
     * @return
     * @throws Exception
     */
	@Override
	public InputStream getDiagramByProDefinitionId_noTrace(String resourceType,
			String processDefinitionId) throws Exception {
		ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionId(processDefinitionId).singleResult();
        String resourceName = "";
        if (resourceType.equals("png") || resourceType.equals("image")) {
            resourceName = processDefinition.getDiagramResourceName();
        } else if (resourceType.equals("xml")) {
            resourceName = processDefinition.getResourceName();
        }
        InputStream resourceAsStream = repositoryService.getResourceAsStream(processDefinition.getDeploymentId(), resourceName);
        return resourceAsStream;
	}

    /**
     * 查看正在运行的请假流程
     * @param user
     * @return
     * @throws Exception
     */
    @Override
    public List<BaseVO> listRuningVacation(User user) throws Exception{
//    	List<Vacation> listVacation = this.vacationService.findByStatus(user.getId(), BaseVO.PENDING, page);
    	List<Vacation> listVacation = null;
		List<BaseVO> result = new ArrayList<BaseVO>();
		if(listVacation != null ){
			for (Vacation vac : listVacation) {
				if(vac.getProcessInstanceId() == null){
					continue;
				}
				// 查询流程实例
				ProcessInstance pi = this.runtimeService
						.createProcessInstanceQuery()
						.processInstanceId(vac.getProcessInstanceId())
						.singleResult();
				Task task = this.taskService.createTaskQuery().processInstanceId(vac.getProcessInstanceId()).singleResult();
				if (pi != null) {
					// 查询流程参数
					BaseVO base = (BaseVO) this.runtimeService.getVariable(pi.getId(), "entity");
					base.setTask(task);
		            base.setProcessInstance(pi);
		            base.setProcessDefinition(getProcessDefinition(pi.getProcessDefinitionId()));
					
					result.add(base);
				}
			}
		}
		return result;
    }
    

    


	@Override
	public String startVacation(Vacation vacation) throws Exception {
		// 用来设置启动流程的人员ID，引擎会自动把用户ID保存到activiti:initiator中
        identityService.setAuthenticatedUserId(vacation.getUserId().toString());
        Map<String, Object> variables = new HashMap<String, Object>();
        variables.put("entity", vacation);
        //由userTask自动分配审批权限
//        if(vacation.getDays() <= 3){
//        	variables.put("auditGroup", "manager");
//        }else{
//        	variables.put("auditGroup", "director");
//        }
        String businessKey = vacation.getBusinessKey();
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(Constants.VACATION, businessKey, variables);
        String processInstanceId = processInstance.getId();
        vacation.setProcessInstanceId(processInstanceId);
        this.vacationService.doUpdate(vacation);

        logger.info("processInstanceId: "+processInstanceId);
        //最后要设置null，就是这么做，还没研究为什么
        this.identityService.setAuthenticatedUserId(null);
        return processInstanceId;
	}
	
	/**
	 * 启动应付款流程
	 */
	@Override
	public String startAccountPayable(PaymentRecord paymentRecord) throws Exception {
		// 用来设置启动流程的人员ID，引擎会自动把用户ID保存到activiti:initiator中
        identityService.setAuthenticatedUserId(paymentRecord.getUserId().toString());
        Map<String, Object> variables = new HashMap<String, Object>();
        variables.put("entity", paymentRecord);
        String businessKey = paymentRecord.getBusinessKey();
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(Constants.ACCOUNTPAYABLE, businessKey, variables);
        String processInstanceId = processInstance.getId();
        paymentRecord.setProcessInstanceId(processInstanceId);
        this.payService.updatePaymentRecord(paymentRecord);;

        logger.info("processInstanceId: "+processInstanceId);
        //最后要设置null，就是这么做，还没研究为什么
        this.identityService.setAuthenticatedUserId(null);
        return processInstanceId;
	}
	
	@Override
	public String startAccountDeliveryable(DeliveryVO deliveryVO) throws Exception {
		// 用来设置启动流程的人员ID，引擎会自动把用户ID保存到activiti:initiator中
        identityService.setAuthenticatedUserId(deliveryVO.getUserId().toString());
        Map<String, Object> variables = new HashMap<String, Object>();
        variables.put("entity", deliveryVO);
        String businessKey = deliveryVO.getBusinessKey();
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(Constants.ACCOUNTDELIVERYABLE, businessKey, variables);
        String processInstanceId = processInstance.getId();
        deliveryVO.setProcessInstanceId(processInstanceId);
        this.deliveryService.updateBasicInfo(deliveryVO);

        logger.info("processInstanceId: "+processInstanceId);
        //最后要设置null，就是这么做，还没研究为什么
        this.identityService.setAuthenticatedUserId(null);
        return processInstanceId;
	}

	/**
	 * 完成任务
	 */
	@Override
	public String complete(String taskId, String content, String userid, Map<String, Object> variables) throws Exception{
		Task task = this.taskService.createTaskQuery().taskId(taskId).singleResult();
		// 根据任务查询流程实例
    	String processInstanceId = task.getProcessInstanceId();
    	ProcessInstance pi = this.runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
		//评论人的id  一定要写，不然查看的时候会报错，没有用户
    	this.identityService.setAuthenticatedUserId(userid);
		// 添加评论--意见为空时，默认“同意”。
    	
    	if(content != null){
    		if(content == ""){
    			if((Boolean)variables.get("isPass")){
    				content = "同意";
    			}else
    				content = "不同意";
        	}
    		this.taskService.addComment(taskId, pi.getId(), content);
    	}
    	
		// 完成委派任务
    	if(DelegationState.PENDING == task.getDelegationState()){
    		this.taskService.resolveTask(taskId, variables);
    		return task.getTaskDefinitionKey();
    	}
    	//正常完成任务
		this.taskService.complete(taskId, variables);
		
		return task.getTaskDefinitionKey();
	}

	@Override
	public List<ProcessInstance> listRuningProcess() throws Exception {
		
		ProcessInstanceQuery processInstanceQuery = runtimeService.createProcessInstanceQuery();
		List<ProcessInstance> list = processInstanceQuery.orderByProcessInstanceId().desc().list();
		return list;
	}

	@Override
	public void activateProcessInstance(String processInstanceId)
			throws Exception {
		runtimeService.activateProcessInstanceById(processInstanceId);
	}

	@Override
	public void suspendProcessInstance(String processInstanceId)
			throws Exception {
		runtimeService.suspendProcessInstanceById(processInstanceId);
	}

	/**
	 * 撤回任务
	 */
	@Override
	public Integer revoke(String historyTaskId, String processInstanceId) throws Exception {
		Command<Integer> cmd = new RevokeTaskCmd(historyTaskId, processInstanceId, this.runtimeService, this.workflowService, this.historyService );
		Integer revokeFlag = this.processEngine.getManagementService().executeCommand(cmd);
		return revokeFlag;
	}

	/**
	 * 跳转（包括回退和向前）至指定活动节点
	 */
	@Override
	public void moveTo(String currentTaskId, String targetTaskDefinitionKey)
			throws Exception {
		TaskEntity taskEntity = (TaskEntity) this.taskService.createTaskQuery().taskId(currentTaskId).singleResult();
		moveTo(taskEntity, targetTaskDefinitionKey);
		
	}

	/**
	 * 跳转（包括回退和向前）至指定活动节点
	 */
	@Override
	public void moveTo(TaskEntity currentTaskEntity,
			String targetTaskDefinitionKey) throws Exception {
		ProcessDefinitionEntity pde = (ProcessDefinitionEntity) ((RepositoryServiceImpl)this.repositoryService).getDeployedProcessDefinition(currentTaskEntity.getProcessDefinitionId()); 
		ActivityImpl activity = (ActivityImpl) pde.findActivity(targetTaskDefinitionKey);

		moveTo(currentTaskEntity, activity);
	}
	
	private void moveTo(TaskEntity currentTaskEntity, ActivityImpl activity)
	{
		Command<Void> deleteCmd = new DeleteActiveTaskCmd(currentTaskEntity, "jump", true);
		Command<Void> StartCmd = new StartActivityCmd(currentTaskEntity.getExecutionId(), activity);
		this.processEngine.getManagementService().executeCommand(deleteCmd);
		this.processEngine.getManagementService().executeCommand(StartCmd);
	}

	@Override
	public void addProcessByDynamic() throws Exception {
		 /*this.repositoryService.deleteDeployment("5003", true);
		 this.repositoryService.deleteDeployment("5007", true);*/
		
		final String PROCESSID ="process_test_1";
		BpmnModel model = new BpmnModel();
		Process process = new Process();
		
		process.setId(PROCESSID);
		process.setName("动态流程测试");
		
		process.addFlowElement(createStartEvent());
		process.addFlowElement(createUserTask("userTask1", "用户任务1"));
		process.addFlowElement(createExclusiveGateway("gateway1"));
		process.addFlowElement(createUserTask("userTask2", "用户任务2"));
		process.addFlowElement(createExclusiveGateway("gateway2"));
		process.addFlowElement(createUserTask("userTask3", "用户任务3"));
		process.addFlowElement(createEndEvent());
		
		process.addFlowElement(createSequenceFlow("startEvent", "userTask1", "flow1", "", ""));
		process.addFlowElement(createSequenceFlow("userTask1", "gateway1", "flow2", "", ""));
		process.addFlowElement(createSequenceFlow("gateway1", "userTask2", "flow3", "同意", "${isPass}"));
		process.addFlowElement(createSequenceFlow("gateway1", "userTask3", "flow4", "不同意", "${!isPass}"));
		process.addFlowElement(createSequenceFlow("userTask2", "endEvent", "flow5", "", ""));
		process.addFlowElement(createSequenceFlow("userTask3", "gateway2", "flow6", "", ""));
		process.addFlowElement(createSequenceFlow("gateway2", "userTask1", "flow7", "同意", "${reApply}"));
		process.addFlowElement(createSequenceFlow("gateway2", "endEvent", "flow8", "结束", "${!reApply}"));
				
		model.addProcess(process);
		
		// 生成流程图片信息
		BpmnAutoLayout bpmnAutoLayout = new BpmnAutoLayout(model);
		bpmnAutoLayout.execute();
		
		// 部署流程
		Deployment deployment = this.repositoryService.createDeployment().addBpmnModel(PROCESSID+".bpmn", model).name("动态流程测试").deploy();
		
		/*// 启动流程
		ProcessInstance processInstance = this.runtimeService.startProcessInstanceByKey(PROCESSID); 
		
		// 导出流程图片
		InputStream processDiagram = this.repositoryService.getProcessDiagram(processInstance.getProcessDefinitionId());  
		FileUtils.copyInputStreamToFile(processDiagram, new File("D:/deployments/"+PROCESSID+".png"));  
		
		// 导出流程文件(BPMN xml)
		InputStream processBpmn = this.repositoryService.getResourceAsStream(deployment.getId(), PROCESSID+".bpmn");  
		FileUtils.copyInputStreamToFile(processBpmn,new File("D:/deployments/"+PROCESSID+".bpmn"));*/
		
	}

	/**
	 * 创建开始节点
	 * @return
	 */
	protected static StartEvent createStartEvent() {
		StartEvent startEvent = new StartEvent();
		startEvent.setId("startEvent");
		startEvent.setName("start");
		startEvent.setInitiator("startUserId");
		return startEvent;
	}
	
	/**
	 * 创建结束节点
	 * @return
	 */
	protected static EndEvent createEndEvent() {
		EndEvent endEvent = new EndEvent();
		endEvent.setId("endEvent");
		endEvent.setName("end");
		return endEvent;
	}
	
	/**
	 * 创建用户任务节点
	 * @param id
	 * @param name
	 * @return
	 */
	protected static UserTask createUserTask(String id, String name) {
		List<ActivitiListener> taskListeners = new ArrayList<ActivitiListener>();
		ActivitiListener listener = new ActivitiListener();
		listener.setId("");
		listener.setEvent("create");
		listener.setImplementationType("delegateExpression");
		listener.setImplementation("${userTaskListener}");
		taskListeners.add(listener);
		
		UserTask userTask = new UserTask();
		userTask.setId(id);
		userTask.setName(name);
		userTask.setTaskListeners(taskListeners);
		userTask.setDocumentation("");		// 说明
		return userTask;
	}
	
	/**
	 * 创建节点间的连线
	 * @param from
	 * @param to
	 * @param id
	 * @param name
	 * @param conditionExpression
	 * @return
	 */
	protected static SequenceFlow createSequenceFlow(String from, String to,String id,String name,String conditionExpression) {
		SequenceFlow flow = new SequenceFlow();
		flow.setId(id);
		flow.setName(name);
		flow.setSourceRef(from);
		flow.setTargetRef(to);
		if(StringUtils.isNotBlank(conditionExpression)) {
			flow.setConditionExpression(conditionExpression);
		}
		return flow;
	}
	
	/**
	 * 创建排他网关
	 * @param id
	 * @return
	 */
	protected static ExclusiveGateway createExclusiveGateway(String id) {
		ExclusiveGateway gateway = new ExclusiveGateway();
		gateway.setId(id);
		return gateway;
	}

	@Override
	@OperationLog(operateType = "app" ,operationDesc = "申请" ,objectSerial= "{serialNum}")
	public String startBuyOrderInfo(OrderInfo orderInfo) {

		// 用来设置启动流程的人员ID，引擎会自动把用户ID保存到activiti:initiator中
        identityService.setAuthenticatedUserId(orderInfo.getUserId().toString());
        Map<String, Object> variables = new HashMap<String, Object>();
        variables.put("entity", orderInfo);

        String businessKey = orderInfo.getBusinessKey();
        ProcessInstance processInstance ;
        if (StaticConst.getInfo("waimao").equals(orderInfo.getTradeType())&&StaticConst.getInfo("dailiBuy").equals(orderInfo.getOrderType())) {//委托采购内贸&&!"61fee61b003c4c258f1704c5be249620".equals(orderInfo.getBuyComId())
//        	processInstance = runtimeService.startProcessInstanceByKey(Constants.WTBUYORDER, businessKey, variables);
        	processInstance = runtimeService.startProcessInstanceByKey(Constants.ZZSALEORDERIN, businessKey, variables);
		}  /*else if (!StaticConst.getInfo("waimao").equals(orderInfo.getTradeType())&&StaticConst.getInfo("dailiSale").equals(orderInfo.getOrderType())) {//委托销售内贸&&!"61fee61b003c4c258f1704c5be249620".equals(orderInfo.getBuyComId())
        	processInstance = runtimeService.startProcessInstanceByKey(Constants.ZZSALEORDERIN, businessKey, variables);
		}*/else if (StaticConst.getInfo("zizhuBuy").equals(orderInfo.getOrderType())) {//自主采购订单
			processInstance = runtimeService.startProcessInstanceByKey(Constants.ZZSALEORDERIN, businessKey, variables);
//			processInstance = runtimeService.startProcessInstanceByKey(Constants.ZZBUYORDER, businessKey, variables);
		}else if (StaticConst.getInfo("waimao").equals(orderInfo.getTradeType())&&!StaticConst.getInfo("zizhuBuy").equals(orderInfo.getOrderType())) {
        	if("4a6d7471644248dbb057298d141413ee".equals(orderInfo.getSupplyComId()))//如果是FT公司，走特殊流程
        	{
        		processInstance = runtimeService.startProcessInstanceByKey(Constants.FT_BUY_ORDER, businessKey, variables);
        	}else{
        		processInstance = runtimeService.startProcessInstanceByKey(Constants.FOREIGN_TRADE_ORDER, businessKey, variables);
        	}
        	
		}else{
			processInstance = runtimeService.startProcessInstanceByKey(Constants.BUYORDER, businessKey, variables);
		}
		String processInstanceId = processInstance.getId();
        orderInfo.setProcessInstanceId(processInstanceId);
        this.processBaseService.update(orderInfo);

        logger.info("processInstanceId: "+processInstanceId);
        //最后要设置null，就是这么做，还没研究为什么
        this.identityService.setAuthenticatedUserId(null);
        return processInstanceId;
	
	}
	
	@Override
	@OperationLog(operateType = "app" ,operationDesc = "申请" ,objectSerial= "{serialNum}")
	public String startSaleOrderInfo(OrderInfo orderInfo) {

		// 用来设置启动流程的人员ID，引擎会自动把用户ID保存到activiti:initiator中
        identityService.setAuthenticatedUserId(orderInfo.getUserId().toString());
        Map<String, Object> variables = new HashMap<String, Object>();
        variables.put("entity", orderInfo);

        String businessKey = orderInfo.getBusinessKey();
        ProcessInstance processInstance=null;
        if (!StaticConst.getInfo("waimao").equals(orderInfo.getTradeType())&&StaticConst.getInfo("dailiSale").equals(orderInfo.getOrderType())&&!"61fee61b003c4c258f1704c5be249620".equals(orderInfo.getBuyComId())) {//委托销售内贸&&!"61fee61b003c4c258f1704c5be249620".equals(orderInfo.getBuyComId())
        	processInstance = runtimeService.startProcessInstanceByKey(Constants.WTSALEORDER, businessKey, variables);
//        	processInstance = runtimeService.startProcessInstanceByKey(Constants.ZZSALEORDERIN, businessKey, variables);
		}else if (!StaticConst.getInfo("waimao").equals(orderInfo.getTradeType())&&StaticConst.getInfo("dailiSale").equals(orderInfo.getOrderType())&&"61fee61b003c4c258f1704c5be249620".equals(orderInfo.getBuyComId())) {//委托销售内贸&&!"61fee61b003c4c258f1704c5be249620".equals(orderInfo.getBuyComId()) {//自主销售内贸
        	processInstance = runtimeService.startProcessInstanceByKey(Constants.ZZSALEORDERIN, businessKey, variables);
		} else if (StaticConst.getInfo("neimao").equals(orderInfo.getTradeType())&&StaticConst.getInfo("zizhuSale").equals(orderInfo.getOrderType())) {//自主销售内贸
        	processInstance = runtimeService.startProcessInstanceByKey(Constants.ZZSALEORDERIN, businessKey, variables);
		}else{
			processInstance = runtimeService.startProcessInstanceByKey(Constants.SALEORDER, businessKey, variables);
		}
        String processInstanceId = processInstance.getId();
        orderInfo.setProcessInstanceId(processInstanceId);
        this.processBaseService.update(orderInfo);

        logger.info("processInstanceId: "+processInstanceId);
        //最后要设置null，就是这么做，还没研究为什么
        this.identityService.setAuthenticatedUserId(null);
        return processInstanceId;
	
	}

	@Override
	@OperationLog(operateType = "app" ,operationDesc = "申请" ,objectSerial= "{serialNum}")
	public String startPriceList(PriceList priceList) {
		// 用来设置启动流程的人员ID，引擎会自动把用户ID保存到activiti:initiator中
        identityService.setAuthenticatedUserId(priceList.getUserId().toString());
        Map<String, Object> variables = new HashMap<String, Object>();
        variables.put("entity", priceList);
        ProcessInstance processInstance=null;
        String businessKey = priceList.getBusinessKey();
        if("buyPrice".equals(priceList.getPriceType())){
        	processInstance = runtimeService.startProcessInstanceByKey(Constants.BUYPRICE_KEY, businessKey, variables);
        }else if("salePrice".equals(priceList.getPriceType())){
        	processInstance = runtimeService.startProcessInstanceByKey(Constants.SALEPRICE_KEY, businessKey, variables);
        }
     
        String processInstanceId = processInstance.getId();
        priceList.setProcessInstanceId(processInstanceId);
        this.processBaseService.update(priceList);

        logger.info("processInstanceId: "+processInstanceId);
        //最后要设置null，就是这么做，还没研究为什么
        this.identityService.setAuthenticatedUserId(null);
        return processInstanceId;
	}

	@Override
	@OperationLog(operateType = "app" ,operationDesc = "申请" ,objectSerial= "{serialNum}")
	public String startInvoice(Invoice invoice) {
		// 用来设置启动流程的人员ID，引擎会自动把用户ID保存到activiti:initiator中
        identityService.setAuthenticatedUserId(invoice.getUserId().toString());
        Map<String, Object> variables = new HashMap<String, Object>();
        variables.put("entity", invoice);
        ProcessInstance processInstance=null;
        String businessKey = invoice.getBusinessKey();
       processInstance = runtimeService.startProcessInstanceByKey(Constants.OUTINVOICE_KEY, businessKey, variables);
        String processInstanceId = processInstance.getId();
        invoice.setProcessInstanceId(processInstanceId);
        this.processBaseService.update(invoice);

        logger.info("processInstanceId: "+processInstanceId);
        //最后要设置null，就是这么做，还没研究为什么
        this.identityService.setAuthenticatedUserId(null);
        return processInstanceId;
	}

	@Override
	public String startBuyFramerProcess(ContractVO contract) {
		// 用来设置启动流程的人员ID，引擎会自动把用户ID保存到activiti:initiator中
        identityService.setAuthenticatedUserId(contract.getUserId().toString());
        Map<String, Object> variables = new HashMap<String, Object>();
        variables.put("entity", contract);

        String businessKey = contract.getBusinessKey();
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(Constants.BUYFRAME, businessKey, variables);
		String processInstanceId = processInstance.getId();
		contract.setProcessInstanceId(processInstanceId);
        this.processBaseService.update(contract);

        logger.info("processInstanceId: "+processInstanceId);
        //最后要设置null，就是这么做，还没研究为什么
        this.identityService.setAuthenticatedUserId(null);
        return processInstanceId;
	}

	@Override
	public String startSaleFramerProcess(ContractVO contract) {
		// 用来设置启动流程的人员ID，引擎会自动把用户ID保存到activiti:initiator中
        identityService.setAuthenticatedUserId(contract.getUserId().toString());
        Map<String, Object> variables = new HashMap<String, Object>();
        variables.put("entity", contract);

        String businessKey = contract.getBusinessKey();
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(Constants.SALEFRAME, businessKey, variables);
		String processInstanceId = processInstance.getId();
		contract.setProcessInstanceId(processInstanceId);
        this.processBaseService.update(contract);

        logger.info("processInstanceId: "+processInstanceId);
        //最后要设置null，就是这么做，还没研究为什么
        this.identityService.setAuthenticatedUserId(null);
        return processInstanceId;
	}

	@Override
	public List<BaseVO> findFinishedTaskInstancesDiy(User user, String businessType) {

//		HistoricTaskInstanceQuery historQuery = historyService.createHistoricTaskInstanceQuery().taskAssignee(user.getUserId().toString()).finished();
//    	List<HistoricTaskInstance> list = historQuery.orderByHistoricTaskInstanceEndTime().desc().list();
		
		Map map = new HashMap<String, String>();
		map.put("userId", user.getUserId());
//		map.put("businessType", businessType); businessType与ProcessDefinition 需一致
		
    	List<HistoricTaskVO> list = this.processBaseService.findFinishedTaskInstancesDiy(map);
    	List<BaseVO> taskList = new ArrayList<BaseVO>();
    	for(HistoricTaskVO hst : list){
    		String processInstanceId = hst.getProcessInstanceId();
    		List<HistoricVariableInstance> listVar = this.historyService.createHistoricVariableInstanceQuery().processInstanceId(processInstanceId).list();
			for(HistoricVariableInstance var : listVar){
				if("serializable".equals(var.getVariableTypeName()) && "entity".equals(var.getVariableName())){
					BaseVO base = (BaseVO) var.getValue();
					base.setHistoricTaskVO(hst);
					base.setProcessDefinition(getProcessDefinition(hst.getProcessDefId()));
					taskList.add(base);
					break;
				}
			}
    	}
		return taskList;
	
	}
//发货计划
	@Override
	public String startDeliveryPlanProcess(DeliveryVO deliveryVO) {
		// 用来设置启动流程的人员ID，引擎会自动把用户ID保存到activiti:initiator中
        identityService.setAuthenticatedUserId(deliveryVO.getUserId().toString());
        Map<String, Object> variables = new HashMap<String, Object>();
        variables.put("entity", deliveryVO);

        String businessKey = deliveryVO.getBusinessKey();
        ProcessInstance processInstance;
       /* if(StaticConst.getInfo("maoyifahuo").equals(deliveryVO.getDeliverType())){
        	processInstance = runtimeService.startProcessInstanceByKey(Constants.DELIVERY_KEY, businessKey, variables);
        }else*/ if(StaticConst.getInfo("beforewithoutcontractdelivery").equals(deliveryVO.getDeliverType())){
        	processInstance = runtimeService.startProcessInstanceByKey(Constants.BEFOREWITHOUTCONTRACTDELIVERY, businessKey, variables);
        }else {
        	processInstance = runtimeService.startProcessInstanceByKey(Constants.AFTERWITHOUTCONTRACTDELIVERY, businessKey, variables);
        }
		String processInstanceId = processInstance.getId();
		deliveryVO.setProcessInstanceId(processInstanceId);
        this.processBaseService.update(deliveryVO);

        logger.info("processInstanceId: "+processInstanceId);
        //最后要设置null，就是这么做，还没研究为什么
        this.identityService.setAuthenticatedUserId(null);
        return processInstanceId;
	}
	//采购计划
	@Override
	@OperationLog(operateType = "app" ,operationDesc = "申请" ,objectSerial= "{serialNum}")
	public String startProcurementPlanInfo( ProcurementPlan procurementPlan ) {

		// 用来设置启动流程的人员ID，引擎会自动把用户ID保存到activiti:initiator中
        identityService.setAuthenticatedUserId(procurementPlan.getUserId().toString());
        Map<String, Object> variables = new HashMap<String, Object>();
        variables.put("entity", procurementPlan);

        String businessKey = procurementPlan.getBusinessKey();
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(Constants.PROCUREMENTPLAN, businessKey, variables);
		String processInstanceId = processInstance.getId();
		procurementPlan.setProcessInstanceId(processInstanceId);
        this.processBaseService.update(procurementPlan);

        logger.info("processInstanceId: "+processInstanceId);
        //最后要设置null，就是这么做，还没研究为什么
        this.identityService.setAuthenticatedUserId(null);
        return processInstanceId;
	
	}
}
