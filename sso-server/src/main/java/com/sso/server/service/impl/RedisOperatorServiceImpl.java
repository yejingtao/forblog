package com.sso.server.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Service;

import com.sso.server.entity.TokenSession;
import com.sso.server.service.RedisOperatorService;

@Service
public class RedisOperatorServiceImpl implements RedisOperatorService{
	
	@Autowired
	private RedisTemplate<String,String> redisTemplate;
	
	public static final String USER_KEY = "userMap";
	
	public static final String TOKEN_KEY = "tokenMap";

	@Override
	public void putUserInfo(String userName, String token) {
		HashOperations<String, String, String> hashUserOp = redisTemplate.opsForHash();
		RedisSerializer<?> redisSerializer = new StringRedisSerializer();
		redisSerializer = new StringRedisSerializer();
		//redisSerializer = new Jackson2JsonRedisSerializer<>(TokenSession.class);
		redisTemplate.setHashValueSerializer(redisSerializer);
		hashUserOp.put(USER_KEY,userName,token);
	}
	
	@Override
	public void deleteUserInfo(String userName) {
		HashOperations<String, String, String> hashUserOp = redisTemplate.opsForHash();
		RedisSerializer<?> redisSerializer = new StringRedisSerializer();
		redisSerializer = new StringRedisSerializer();
		//redisSerializer = new Jackson2JsonRedisSerializer<>(TokenSession.class);
		redisTemplate.setHashValueSerializer(redisSerializer);
		hashUserOp.delete(USER_KEY,userName);
	}

	@Override
	public void putTokenInfo(String tokenKey, TokenSession tokenSession) {
		HashOperations<String, String, TokenSession> hashTokenOp = redisTemplate.opsForHash();
		RedisSerializer<?> redisSerializer = new StringRedisSerializer();
		redisSerializer = new JdkSerializationRedisSerializer();
		//redisSerializer = new Jackson2JsonRedisSerializer<>(TokenSession.class);
		redisTemplate.setHashValueSerializer(redisSerializer);
		hashTokenOp.put(TOKEN_KEY,tokenKey,tokenSession);
		
	}
	
	@Override
	public void deleteTokenInfo(String tokenKey) {
		HashOperations<String, String, TokenSession> hashTokenOp = redisTemplate.opsForHash();
		RedisSerializer<?> redisSerializer = new StringRedisSerializer();
		redisSerializer = new JdkSerializationRedisSerializer();
		//redisSerializer = new Jackson2JsonRedisSerializer<>(TokenSession.class);
		redisTemplate.setHashValueSerializer(redisSerializer);
		hashTokenOp.delete(TOKEN_KEY,tokenKey);
		
	}

	@Override
	public String getUserInfo(String userName) {
		HashOperations<String, String, String> hashUserOp = redisTemplate.opsForHash();
		RedisSerializer<?> redisSerializer = new StringRedisSerializer();
		redisSerializer = new StringRedisSerializer();
		//redisSerializer = new Jackson2JsonRedisSerializer<>(TokenSession.class);
		redisTemplate.setHashValueSerializer(redisSerializer);
		return hashUserOp.get(USER_KEY, userName); 
	}

	@Override
	public TokenSession getTokenInfo(String tokenKey) {
		HashOperations<String, String, TokenSession> hashTokenOp = redisTemplate.opsForHash();
		RedisSerializer<?> redisSerializer = new StringRedisSerializer();
		redisSerializer = new JdkSerializationRedisSerializer();
		//redisSerializer = new Jackson2JsonRedisSerializer<>(TokenSession.class);
		redisTemplate.setHashValueSerializer(redisSerializer);
		return hashTokenOp.get(TOKEN_KEY,tokenKey);
	}

}
