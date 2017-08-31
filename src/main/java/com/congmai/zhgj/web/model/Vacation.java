package com.congmai.zhgj.web.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @ClassName Vacation
 * @Description 请假
 * @author tanzb
 * @Date 2017年8月23日 下午5:50:02
 * @version 1.0.0
 */
public class Vacation extends BaseVO implements Serializable{	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1495795296316800235L;
	// 带薪假
	public final static int TYPE_PAID = 0;	
	// 病假
	public final static int TYPE_SICK = 1;	
	// 事假
	public final static int TYPE_MATTER = 2;
		

	private Integer id;

	// 休假的工作日
	private Integer days;
	
	// 请假开始日期
	private Date beginDate;
	
	// 请假结束日期
	private Date endDate;
	
	// 申请日期
	private Date applyDate;
	
	// 休假类型
	private Integer vacationType;
	
	//原因
	private String reason;
	
	// 对应的流程实例id
	private String processInstanceId;
	
	// 用户id
	private Integer userId;
	
	//审批状态
	private String status;
	
	public Vacation(){
		
	}
	
	
	

	public Vacation(Integer id, Integer days, Date beginDate, Date endDate,
			Date applyDate, Integer vacationType, String reason, String processInstanceId,
			Integer userId, String status) {
		this.id = id;
		this.days = days;
		this.beginDate = beginDate;
		this.endDate = endDate;
		this.applyDate = applyDate;
		this.vacationType = vacationType;
		this.reason = reason;
		this.processInstanceId = processInstanceId;
		this.userId = userId;
		this.status = status;
	}




	public Integer getId() {
		return id;
	}




	public void setId(Integer id) {
		this.id = id;
	}



	public Date getBeginDate() {
		return beginDate;
	}




	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}




	public Date getEndDate() {
		return endDate;
	}




	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}




	public Date getApplyDate() {
		return applyDate;
	}




	public void setApplyDate(Date applyDate) {
		this.applyDate = applyDate;
	}





	public String getReason() {
		return reason;
	}




	public void setReason(String reason) {
		this.reason = reason;
	}






	public Integer getUserId() {
		return userId;
	}




	public void setUserId(Integer userId) {
		this.userId = userId;
	}




	public String getStatus() {
		return status;
	}




	public void setStatus(String status) {
		this.status = status;
	}




	public static long getSerialversionuid() {
		return serialVersionUID;
	}




	public Integer getDays() {
		return days;
	}




	public void setDays(Integer days) {
		this.days = days;
	}




	public Integer getVacationType() {
		return vacationType;
	}




	public void setVacationType(Integer vacationType) {
		this.vacationType = vacationType;
	}




	public String getProcessInstanceId() {
		return processInstanceId;
	}




	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}
}
