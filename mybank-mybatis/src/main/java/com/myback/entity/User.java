package com.myback.entity;

/**
 * 用户信息
 * @author yejingtao
 *
 */

public class User {
	

	private int id;
	
	private String name;
	
	private double current;
	
	public User(String name, double current) {
		this.name=name;
		this.current = current;
	}
	
	public User() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getCurrent() {
		return current;
	}

	public void setCurrent(double current) {
		this.current = current;
	}
	
	@Override
	public String toString() {
		return "User name:"+name+" current"+current;
	}
	
}
