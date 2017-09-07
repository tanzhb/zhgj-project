package com.congmai.zhgj.web.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @ClassName GroupAndResource
 * @Description 组与资源关联表
 * @author tanzb
 * @Date 2017年8月23日 上午10:31:17
 * @version 1.0.0
 */

@Entity
@Table(name = "T_GROUP_RESOURCE")
public class GroupAndResource implements Serializable {

	private static final long serialVersionUID = 5606409519598928351L;

	@Id
    @GeneratedValue
    private Integer id;

    @Column(name = "group_id")
    private Integer groupId;

    @Column(name = "resource_id")
    private Integer resourceId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getGroupId() {
		return groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	public Integer getResourceId() {
		return resourceId;
	}

	public void setResourceId(Integer resourceId) {
		this.resourceId = resourceId;
	}
    
}
