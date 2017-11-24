package com.code.controller;

import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController extends BaseCookieController{

	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String login(ModelMap modelMap, HttpServletRequest httpRequest, 
			String name, String password, String securityCode) {
		modelMap.put("returnResult", "error");
		//判断账号密码
		//此处略
		//判断验证码
		String securityCodeInRedis = securityCacheService.getCodeCache(getSessionId(httpRequest));
		if(securityCodeInRedis!=null && securityCode!=null) {
			if(Objects.equals(securityCodeInRedis.toUpperCase(), securityCode.toUpperCase())) {
				modelMap.put("returnResult", "Success");
			}else {
				modelMap.put("returnResult", "security code error");
			}
		}
		return "hello";
	}
	
	@RequestMapping("/index")
	public String index(HttpServletRequest httpRequest) {
		httpRequest.getSession(true);//产生sessionId
		return "register";
	}
}
