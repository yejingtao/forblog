package com.myback.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.myback.entity.Operation;
import com.myback.entity.User;
import com.myback.service.IUserService;

@Controller
@RequestMapping("/user")
public class MainController {
	
	public static final String REDIS_KEY = "userMap";
	
	@Autowired
	private RedisTemplate<String,String> redisTemplate;
	
	@Autowired
	private IUserService userService;
	
	@RequestMapping("/hello")
	public String hello() {
		return "hello";
	}
	
	
	
	@RequestMapping(value="/find",method=RequestMethod.GET)
	@ResponseBody
	public String findUserInfo(@NotNull String name) {
		StringBuffer returnSb = new StringBuffer();
		List<User> userList = userService.findUserByName(name);
		returnSb.append("User: " + userList.get(0).toString() + "\\r\\n");
		//这里是系统缓存的用法
		HashOperations<String, String, String> hashOp = redisTemplate.opsForHash();
		String userLatestModifyTime = hashOp.get(REDIS_KEY, userList.get(0).getName());//user latest modify time
		returnSb.append("User latest modify time: " + userLatestModifyTime + "\\r\\n");
		returnSb.append("Operation: ");
		List<Operation> operationList = userService.findOperationByUserId(userList.get(0).getId());
		if(operationList!=null && operationList.size()>0) {
			operationList.forEach(o->returnSb.append(o));
		}
		
		return returnSb.toString();
		
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
	 * @param user
	 * @return
	 */
	@RequestMapping(value="/saveUser",method=RequestMethod.POST)
	public String create(ModelMap modelMap,User user) {
		String description = user.getId()>0?("update money into "+user.getCurrent()):"register a new user";
		User thisUser = userService.createOrUpdate(user,description);
		modelMap.addAttribute("thisUser", thisUser);
		modelMap.addAttribute("thisOperations", userService.findOperationByUserId(thisUser.getId()));
		//这里是系统缓存的用法
		HashOperations<String, String, String> hashOp = redisTemplate.opsForHash();
		hashOp.put(REDIS_KEY, thisUser.getName(), (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(new Date()));//user latest modify time
		return "myuser";
	}
}
