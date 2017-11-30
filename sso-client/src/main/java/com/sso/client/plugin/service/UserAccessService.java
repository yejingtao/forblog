package com.sso.client.plugin.service;

public interface UserAccessService {
	
	String getUserToken(String user);
	
	void putUserStatus(String user, String flag);
	
	void deleteToken(String user);
}
