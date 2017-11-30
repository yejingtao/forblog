package com.sso.client.plugin.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.sso.client.plugin.service.UserAccessService;

@Controller
public class SsoClientController {
	
	@Autowired
    private RestTemplate restTemplate;
	
	@Value("${sso.server.url}")
	String ssoServerPath;
	
	@Autowired
	private UserAccessService userAccessService;
	
	@RequestMapping("/receiveToken")
	@ResponseBody
	public String receiveToken(HttpServletRequest request, String ssoToken,String userName) {
		if(ssoToken!=null && ssoToken.toString().trim().length()>0) {
			String realUrl = request.getRequestURL().toString();
			String[] paths = realUrl.split("/");
			String realUrlUrls = paths[2];
			String returnUrl = ssoServerPath+"/varifyToken?address="+realUrlUrls+"&token="+ssoToken;
			//http://peer2:8089/varifyToken?address=peer1:8088&token=c2ce29be-5adb-4aaf-82cc-2ba24330176e
			String resultStr =  restTemplate.getForObject(returnUrl, String.class);
			if("true".equals(resultStr)) {
				//创建局部会话，保存用户状态为已登陆
				userAccessService.putUserStatus(userName, ssoToken);
				return "success";
			}
		}
		return "error";
	}
	
	@RequestMapping("/ssoLogout")
	@ResponseBody
	public String ssoLogout(String userName) {
		String userToken = userAccessService.getUserToken(userName);
		if(userToken!=null) {
			String returnUrl = ssoServerPath+"/logoutByToken?ssoToken="+userToken;
			return restTemplate.getForObject(returnUrl, String.class);
		}
		return "None Token";
	}
	
	@RequestMapping("/ssoDeleteToken")
	@ResponseBody
	public String ssoDeleteToken(String ssoToken) {
		userAccessService.deleteToken(ssoToken);
		return "success";
	}
}
