package com.myback.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 账户的操作记录
 * @author yejingtao
 *
 */
@Entity
public class Operation implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5338631109043810893L;

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	private int userId;
	
	private String description;
	
	public Operation() {
		super();
	}
	
	public Operation(int userId,String description) {
		this.userId=userId;
		this.description=description;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	@Override
	public String toString() {
		return "Operation user_id:"+ userId +" description:"+description;
	}
}
