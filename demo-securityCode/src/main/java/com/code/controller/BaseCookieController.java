package com.code.controller;

import java.util.Objects;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.code.service.SecurityCacheService;


public class BaseCookieController {
	
	public static final String JSESSIONID = "JSESSIONID";
	public static final String SPLIT = "-";
	
	@Autowired
	protected SecurityCacheService securityCacheService;
	
	protected String getSessionId(HttpServletRequest httpRequest) {
		Cookie[] cookies = httpRequest.getCookies();
		if(cookies!=null) {
			for(Cookie cookie : cookies) {
				if(Objects.equals(JSESSIONID, cookie.getName())) {
					return JSESSIONID+cookie.getValue();
				}
			}
		}
		return null;
	}
}
