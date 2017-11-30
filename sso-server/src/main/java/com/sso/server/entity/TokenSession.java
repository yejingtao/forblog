package com.sso.server.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TokenSession implements Serializable{
	
	private static final long serialVersionUID = 9036064333285700087L;
	
	private String userName;
	
	private String token;
	
	private boolean tokenFlag;
	
	private long deadline;
	
	private List<String> addressList;
	
	public TokenSession(String token,String userName) {
		this.userName = userName;
		this.token=token;
		this.tokenFlag=false;//令牌默认无效
		this.deadline = System.currentTimeMillis()+10*60*1000;
		this.addressList=new ArrayList<String>();
	}
	
	public TokenSession() {
		super();
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public long getDeadline() {
		return deadline;
	}

	public void setDeadline(long deadline) {
		this.deadline = deadline;
	}

	public List<String> getAddressList() {
		return addressList;
	}

	public void setAddressList(List<String> addressList) {
		this.addressList = addressList;
	}

	public boolean isTokenFlag() {
		return tokenFlag;
	}

	public void setTokenFlag(boolean tokenFlag) {
		this.tokenFlag = tokenFlag;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	

}
