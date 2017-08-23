package com.mydemo.controller;

import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {

	@RequestMapping("/forRoleA")
	@ResponseBody
	@PreAuthorize("hasAuthority('authority_a')")
	public String roleTestMothedA() {
		return "Role A Successful";
	}
	
	@RequestMapping("/forRoleB")
	@ResponseBody
	@PreAuthorize("hasAuthority('authority_b')")
	public String roleTestMothedB() {
		return "Role B Successful";
	}
	
	@RequestMapping("/hello")
	@ResponseBody
	public String hello() {
		return "hello world";
	}
	
	/**
	@RequestMapping("/login")
    public String login(){
        return "login";
    }
    */
}