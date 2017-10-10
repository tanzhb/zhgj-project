package com.congmai.zhgj.web.model;

import java.util.Date;
import java.util.List;


public class AlertVO {

	public AlertVO() {
	}
	
	public AlertVO(String id, String type, String name) {
		super();
		this.id = id;
		this.type = type;
		this.name = name;
	}

	private String id;
	
	private String type;//
	/*supply 供应商，
	contactPeopl 联系人
	product 	产品
	material 物料清单
	enquiry 询价
	quotation 报价
	order 订单
	priceList 	定价
	deliver 交付
	statement 对账
	complain 抱怨
	systemNotice 系统公告
	companyNotice 公司公告
	buyNotice 客户公告
	system_message 系统消息-消息
	bussi_warn 业务提醒-消息
	returnGoods 退货单
	customerSurvey 信息调查
	calims 索赔
	all 所有
	*/
	
	private String name;//标题或名称
	
	private String obj_type;
	
	private String version;
	
	private Date createDate;
	
	private Date lastModifiedDate;
	
	private String creator_id;
	
	private String creator;
	
	private String operator_id;
	
	private String operator;
	
	private String ownCompany_id;
	
	private String ownCompany;
	
	private String targetCompany_id;
	
	private String targetCompany;
	
	private List<String> acl;
	
	private String action_url;//点击跳转的url
	
	private String serial_num;
	
	private String content;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getObj_type() {
		return obj_type;
	}

	public void setObj_type(String obj_type) {
		this.obj_type = obj_type;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	public String getCreator_id() {
		return creator_id;
	}

	public void setCreator_id(String creator_id) {
		this.creator_id = creator_id;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getOperator_id() {
		return operator_id;
	}

	public void setOperator_id(String operator_id) {
		this.operator_id = operator_id;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getOwnCompany_id() {
		return ownCompany_id;
	}

	public void setOwnCompany_id(String ownCompany_id) {
		this.ownCompany_id = ownCompany_id;
	}

	public String getOwnCompany() {
		return ownCompany;
	}

	public void setOwnCompany(String ownCompany) {
		this.ownCompany = ownCompany;
	}

	public String getTargetCompany_id() {
		return targetCompany_id;
	}

	public void setTargetCompany_id(String targetCompany_id) {
		this.targetCompany_id = targetCompany_id;
	}

	public String getTargetCompany() {
		return targetCompany;
	}

	public void setTargetCompany(String targetCompany) {
		this.targetCompany = targetCompany;
	}

	public List<String> getAcl() {
		return acl;
	}

	public void setAcl(List<String> acl) {
		this.acl = acl;
	}

	public String getAction_url() {
		return action_url;
	}

	public void setAction_url(String action_url) {
		this.action_url = action_url;
	}

	public String getSerial_num() {
		return serial_num;
	}

	public void setSerial_num(String serial_num) {
		this.serial_num = serial_num;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	
}
