package com.cookie.domain;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class ServiceDemoApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(ServiceDemoApplication.class, args);
	}
	
	
	@Value("${cookie.value}")
    String cookieValue;
	
    @RequestMapping("/setCookie")
    public String home(HttpServletResponse response,HttpServletRequest request) {
    	Cookie cookie = new Cookie("testName", cookieValue);
    	//cookie.setDomain("peer2");
    	response.addCookie(cookie);
    	//request.getSession().setAttribute("sessionID", "haha");
        return "Success";
    }
    
    @RequestMapping("/getCookie")
    public String home(HttpServletRequest request) {
    	StringBuffer sb = new StringBuffer();
    	Cookie[] cookies = request.getCookies();
    	if(cookies!=null) {
    		for(Cookie cookie : cookies) {
    			sb.append(cookie.getName());
    			sb.append(",");
    			sb.append(cookie.getValue());
    			sb.append(";");
    		}
    	}
        return sb.length()==0?"Empty":sb.toString();
    }
}
