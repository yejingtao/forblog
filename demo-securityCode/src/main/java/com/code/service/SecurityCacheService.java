package com.code.service;

public interface SecurityCacheService {
	
	void setCodeCache(String sessionID, String securityCode);
	
	String getCodeCache(String sessionID);
}
