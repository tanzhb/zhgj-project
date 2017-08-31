/**
 * Project Name:SpringOA
 * File Name:User.java
 * Package Name:com.zml.oa.entity
 * Date:2014-11-8下午11:12:48
 *
 */
package com.congmai.zhgj.web.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @ClassName User
 * @Description 用户表
 * @author tanzb
 * @Date 2017年8月23日 上午9:37:42
 * @version 1.0.0
 */

public class User implements Serializable{

	private static final long serialVersionUID = -6662232329895785824L;

	private Integer userId;
	
	private String userName;
	
	private String userPwd;
	
	private String userSalt; // 加密密码的盐

	private Date regDate;

    private Integer locked;	

    private Integer groupId;
    
	public User(){
		
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public String getUserSalt() {
		return userSalt;
	}

	public void setUserSalt(String userSalt) {
		this.userSalt = userSalt;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public Integer getLocked() {
		return locked;
	}

	public void setLocked(Integer locked) {
		this.locked = locked;
	}

	public Integer getGroupId() {
		return groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}
	
    public String getCredentialsSalt() {
        return userName + userSalt;
    }

	public static long getSerialversionuid() {
		return serialVersionUID;
	}	
}
