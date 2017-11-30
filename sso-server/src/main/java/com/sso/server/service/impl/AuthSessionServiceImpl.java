package com.sso.server.service.impl;

import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sso.server.entity.TokenSession;
import com.sso.server.service.AuthSessionService;
import com.sso.server.service.RedisOperatorService;

@Service
public class AuthSessionServiceImpl implements AuthSessionService{
		
	@Autowired
	private RedisOperatorService redisOperatorService;

	@Override
	public boolean verify(String userName, String password) {
		// 根据自己数据库数据来校验
		return true;
	}

	@Override
	public String cacheSession(String userName) {
		//创建token
		String token = UUID.randomUUID().toString();
		redisOperatorService.putUserInfo(userName, token);
		TokenSession tokenSession = new TokenSession(token,userName); 
		redisOperatorService.putTokenInfo(token, tokenSession);
		return token;
	}

	@Override
	public boolean checkAndAddAddress(String token, String address) {
		TokenSession tokenSession = redisOperatorService.getTokenInfo(token);
		if(tokenSession!=null) {
			tokenSession.getAddressList().add(address);
			tokenSession.setTokenFlag(true);
			redisOperatorService.putTokenInfo(token, tokenSession);
			return true;
		}
		return false;
	}

	@Override
	public boolean checkUserLoginStatus(String userName,String address) {
		boolean flag = false;
		String token = redisOperatorService.getUserInfo(userName);
		if(token!=null) {
			TokenSession tokenSession = redisOperatorService.getTokenInfo(token);
			if(tokenSession!=null) {
				if(tokenSession.getAddressList().contains(address)) {
					flag =  true;
				}
			}
		}
		return flag;
	}

	@Override
	public String getUserToken(String userName) {
		String token = redisOperatorService.getUserInfo(userName);
		if(token==null) {
			return null;
		}else {
			if(redisOperatorService.getTokenInfo(token)!=null) {
				return token;
			}else {
				return null;
			}
			
		}
	}

	@Override
	public List<String> logoutByUser(String userName) {
		String ssoToken = redisOperatorService.getUserInfo(userName);
		redisOperatorService.deleteUserInfo(userName);
		if(ssoToken!=null) {
			logoutByToken(ssoToken);
		}
		return null;
	}

	@Override
	public List<String> logoutByToken(String ssoToken) {
		if(ssoToken!=null) {
			TokenSession tokenSession = redisOperatorService.getTokenInfo(ssoToken);
			if(tokenSession!=null) {
				redisOperatorService.deleteTokenInfo(ssoToken);
				return tokenSession.getAddressList();
			}
		}
		return null;
	}
	
}
