package com.congmai.zhgj.web.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @ClassName UserTask
 * @Description 用户任务表
 * @author tanzb
 * @Date 2017年8月24日 上午10:01:33
 * @version 1.0.0
 */
public class UserTask implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8889804050417208965L;

	private Integer id;
	
//	private String procDefId;		procDefId重新部署会改变的，保存这个id是无意义的
	
	private String procDefKey;	

	private String procDefName;		//请假申请

	private String taskDefKey;		
	
	private String taskName;		//人事审批
	
	private String taskType;		//1.assignee.受理人(唯一) 1.candidateUser候选人(多个) 2.candidateGroup候选组（多个）
	
	private String candidate_name; 	//人或候选人或组的名称
	
	private String candidate_ids;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getProcDefKey() {
		return procDefKey;
	}

	public void setProcDefKey(String procDefKey) {
		this.procDefKey = procDefKey;
	}

	public String getProcDefName() {
		return procDefName;
	}

	public void setProcDefName(String procDefName) {
		this.procDefName = procDefName;
	}

	public String getTaskDefKey() {
		return taskDefKey;
	}

	public void setTaskDefKey(String taskDefKey) {
		this.taskDefKey = taskDefKey;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getTaskType() {
		return taskType;
	}

	public void setTaskType(String taskType) {
		this.taskType = taskType;
	}

	public String getCandidate_name() {
		return candidate_name;
	}

	public void setCandidate_name(String candidate_name) {
		this.candidate_name = candidate_name;
	}

	public String getCandidate_ids() {
		return candidate_ids;
	}

	public void setCandidate_ids(String candidate_ids) {
		this.candidate_ids = candidate_ids;
	}
}
