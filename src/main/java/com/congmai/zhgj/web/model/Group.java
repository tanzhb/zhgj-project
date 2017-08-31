package com.congmai.zhgj.web.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIgnore;
/**
 * 
 * @ClassName Group
 * @Description 组表
 * @author tanzb
 * @Date 2017年8月23日 上午10:31:41
 * @version 1.0.0
 */
@Entity
@Table(name = "T_GROUP")
public class Group implements Serializable{

	private static final long serialVersionUID = -4469236450461851881L;

	private Integer id;
	
	private String name;
	
	private String type;
    
	public Group(){
		
	}
	
	

	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getType() {
		return type;
	}



	public void setType(String type) {
		this.type = type;
	}



	public static long getSerialversionuid() {
		return serialVersionUID;
	}	
}
