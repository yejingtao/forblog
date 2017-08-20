package com.myback.entity;

/**
 * 账户的操作记录
 * @author yejingtao
 *
 */
public class Operation {

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
