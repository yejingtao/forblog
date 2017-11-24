package com.code.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.code.util.SecurityCodeUtil;
import com.code.util.SecurityImageSupport;

@Controller
public class SecurityCodeController extends BaseCookieController{
	
	@RequestMapping("/security")
	public ResponseEntity<byte[]> securityCode(HttpServletRequest httpRequest) {
		//获取验证码文本
		String securityCode = SecurityCodeUtil.getSecurityCode();
		//Redis缓存验证码信息
		securityCacheService.setCodeCache(getSessionId(httpRequest), securityCode);
		byte[] bytes = SecurityImageSupport.getImageAsByte(securityCode);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.IMAGE_JPEG);
		return new ResponseEntity<byte[]>(bytes, headers,HttpStatus.OK);
	}
}
