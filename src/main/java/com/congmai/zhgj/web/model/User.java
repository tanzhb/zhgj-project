package com.congmai.zhgj.web.model;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.springframework.format.annotation.DateTimeFormat;

import com.congmai.zhgj.core.util.JsonDateSerializer;

/**
 * 
 * @ClassName User
 * @Description 用户模型
 * @author tanzb
 * @Date 2017年7月26日 下午2:52:03
 * @version 1.0.0
 */
public class User {
    private Long id;    
    private String loginName;//用户登录名
    private String userName;//用户姓名
    private String password;//密码
    private String idNumber;//身份证号码
    private String station;//用户岗位
    private String telphone;//电话号码
    private String email;//邮箱地址
    private String state;//状态
    
    private String delFlg;//删除标志（1是0否）
    private String creator;//创建人    
    private Date createTime;//创建时间
    private String updater;//更新人    
    private Date updateTime;//更新时间
    private String comments;//备注

    public User() {

    }

    
    public User(Long id, String loginName, String userName, String password,
			String idNumber, String station, String telphone, String email,
			String state, String delFlg, String creator, Date createTime,
			String updater, Date updateTime, String comments) {
		super();
		this.id = id;
		this.loginName = loginName;
		this.userName = userName;
		this.password = password;
		this.idNumber = idNumber;
		this.station = station;
		this.telphone = telphone;
		this.email = email;
		this.state = state;
		this.delFlg = delFlg;
		this.creator = creator;
		this.createTime = createTime;
		this.updater = updater;
		this.updateTime = updateTime;
		this.comments = comments;
	}
    
    public User(String loginName, String password){
    	this.loginName = loginName;
		this.password = password;
    }


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getLoginName() {
		return loginName;
	}


	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getIdNumber() {
		return idNumber;
	}


	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}


	public String getStation() {
		return station;
	}


	public void setStation(String station) {
		this.station = station;
	}


	public String getTelphone() {
		return telphone;
	}


	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getState() {
		return state;
	}


	public void setState(String state) {
		this.state = state;
	}


	public String getDelFlg() {
		return delFlg;
	}


	public void setDelFlg(String delFlg) {
		this.delFlg = delFlg;
	}


	public String getCreator() {
		return creator;
	}


	public void setCreator(String creator) {
		this.creator = creator;
	}

	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")  
	@JsonSerialize(using=JsonDateSerializer.class)  
	public Date getCreateTime() {
		return createTime;
	}


	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}


	public String getUpdater() {
		return updater;
	}


	public void setUpdater(String updater) {
		this.updater = updater;
	}

	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")  
	@JsonSerialize(using=JsonDateSerializer.class)  
	public Date getUpdateTime() {
		return updateTime;
	}


	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}


	public String getComments() {
		return comments;
	}


	public void setComments(String comments) {
		this.comments = comments;
	}
}