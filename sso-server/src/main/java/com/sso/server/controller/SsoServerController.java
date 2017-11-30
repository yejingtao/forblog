package com.sso.server.controller;


import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.sso.server.service.AuthSessionService;

@Controller
public class SsoServerController {
	
	@Autowired
    private RestTemplate restTemplate;
	
	@Autowired
	private AuthSessionService authSessionService;
	
	@RequestMapping("/index")
	public String firstCheck(HttpServletRequest request) {
		String originalUrl = request.getParameter("originalUrl");
		String ssoUser = request.getParameter("ssoUser");
		String token = null;
		boolean loginFlag = false;
		if(ssoUser!=null && ssoUser.trim().length()>0) {
			//对用户先判断是否已经登陆过
			token = authSessionService.getUserToken(ssoUser);
			if(token!=null) {
				loginFlag = true;
			}
		}
		if(loginFlag) {
			//判断如果用户已经在SSO-Server认证过，直接发送token
			if(tokenTrans(request,originalUrl,ssoUser,token)) {
				if(originalUrl!=null) {
					if(originalUrl.contains("?")) {
						originalUrl = originalUrl + "&ssoUser="+ssoUser;
					}else {
						originalUrl = originalUrl + "?ssoUser="+ssoUser;
					}
				}
			}
			return "redirect:"+originalUrl;
		}else {
			//需要替换成专业点的路径,自己登陆下了
			return "redirect:/loginPage?originalUrl="+request.getParameter("originalUrl");
		}
	}
	
	//登陆界面，返回的是页面地址
	@RequestMapping("/loginPage")
	public String index(HttpServletRequest request) {
		if(request.getParameter("originalUrl")!=null) {
			request.setAttribute("originalUrl", request.getParameter("originalUrl"));
		}
		return "loginIndex";
	}
	
	private boolean tokenTrans(HttpServletRequest request, String originalUrl,String userName, String token) {
		String[] paths = originalUrl.split("/");
		String shortAppServerUrl = paths[2];
		String returnUrl = "http://"+shortAppServerUrl+"/receiveToken?ssoToken="+token+"&userName="+userName;
		//http://peer1:8088/receiveToken?ssoToken=80414bcb-a71d-48c8-bfee-098a303324d4&userName=xixi
		return "success".equals(restTemplate.getForObject(returnUrl, String.class));
		
	}
	
	//登陆逻辑,返回的是令牌
	@RequestMapping(value="/doLogin",method=RequestMethod.POST)
	public String login(HttpServletRequest request, HttpServletResponse response,
			String userName, String password, String originalUrl) {
		if(authSessionService.verify(userName,password)) {
			String token = authSessionService.cacheSession(userName);
			if(tokenTrans(request,originalUrl,userName,token)) {
				//跳转到提示成功的页面
				request.setAttribute("helloName", userName);
				if(originalUrl!=null) {
					if(originalUrl.contains("?")) {
						originalUrl = originalUrl + "&ssoUser="+userName;
					}else {
						originalUrl = originalUrl + "?ssoUser="+userName;
					}
					request.setAttribute("originalUrl", originalUrl);
				}
			}
			return "hello";//TO-DO 三秒跳转
		}
		//验证不通过，重新来吧
		if(originalUrl!=null) {
			request.setAttribute("originalUrl", originalUrl);
		}
		return "loginIndex";
	}
	
	//校验token并注册地址
	@RequestMapping(value="/varifyToken",method=RequestMethod.GET)
	@ResponseBody
	public String varifyToken(String token, String address) {
		return String.valueOf(authSessionService.checkAndAddAddress(token, address));
	}
	
	@RequestMapping(value="/logoutByUser",method=RequestMethod.GET)
	@ResponseBody
	public String logoutByUser(String userName) {
		String ssoToken = authSessionService.getUserToken(userName);
		if(ssoToken!=null) {
			List<String> addressList = authSessionService.logoutByUser(userName);
			if(addressList!=null) {
				addressList.stream().forEach(s -> sendLogout2Client(s,ssoToken));
			}
		}
		return "Done";
	}
	
	@RequestMapping(value="/logoutByToken",method=RequestMethod.GET)
	@ResponseBody
	public String logoutByToken(String ssoToken) {
		List<String> addressList = authSessionService.logoutByToken(ssoToken);
		if(addressList!=null) {
			addressList.stream().forEach(s -> sendLogout2Client(s,ssoToken));
		}
		return "logout";
	}
	
	private void sendLogout2Client(String address,String ssoToken) {
		String returnUrl = "http://"+address+"/ssoDeleteToken?ssoToken="+ssoToken;
		try {
			restTemplate.getForObject(returnUrl, String.class);
		}catch(Exception e) {
			//Log and do nothing
		}
	}
}
