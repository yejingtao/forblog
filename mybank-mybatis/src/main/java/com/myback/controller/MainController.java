package com.myback.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.myback.entity.Operation;
import com.myback.entity.User;
import com.myback.service.IUserService;

@Controller
@RequestMapping("/user")
public class MainController {
	
	@Autowired
	private IUserService userService;
	
	@RequestMapping("/hello")
	public String hello() {
		return "hello";
	}
	
	/**
	 * 创建用户
	 * @param user
	 * @return
	 */
	@RequestMapping(value="/register",method=RequestMethod.GET)
	public String register(ModelMap modelMap,String userName) {
		if(StringUtils.isEmpty(userName)) {
			//没有用户名，跳转到注册页面
			return "register";
		}else {
			List<User> userList = userService.findUserByName(userName);
			if(userList!=null && userList.size()>0) {
				modelMap.addAttribute("thisUser", userList.get(0));
				modelMap.addAttribute("thisOperations", userService.findOperationByUserId(userList.get(0).getId()));
				return "myuser";
			}else {
				return "register";
			}
		}
		
	}
	
	/**
	 * 创建用户
	 * @param users
	 * @return
	 */
	@RequestMapping(value="/saveUser",method=RequestMethod.POST)
	public String create(ModelMap modelMap,User user) {
		String description = user.getId()>0?("update money into "+user.getCurrent()):"register a new user";
		userService.createOrUpdate(user,description);
		List<User> thisUserList = userService.findUserByName(user.getName());
		modelMap.addAttribute("thisUser", thisUserList.get(0));
		modelMap.addAttribute("thisOperations", userService.findOperationByUserId(thisUserList.get(0).getId()));
		return "myuser";
	}
}
