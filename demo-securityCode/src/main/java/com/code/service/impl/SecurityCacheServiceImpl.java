package com.code.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.code.service.SecurityCacheService;

@Service
public class SecurityCacheServiceImpl implements SecurityCacheService{
	
	public static final String REDIS_KEY = "sessionMap";
	
	@Autowired
	private RedisTemplate<String,String> redisTemplate;
	
	@Override
	public void setCodeCache(String sessionID, String securityCode) {
		HashOperations<String, String, String> hashOp = redisTemplate.opsForHash();
		hashOp.put(REDIS_KEY,sessionID,securityCode);
	}

	@Override
	public String getCodeCache(String sessionID) {
		HashOperations<String, String, String> hashOp = redisTemplate.opsForHash();
		return hashOp.get(REDIS_KEY, sessionID);
	}

}
