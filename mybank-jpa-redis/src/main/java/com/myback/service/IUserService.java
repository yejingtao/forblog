package com.myback.service;

import java.util.List;

import com.myback.entity.Operation;
import com.myback.entity.User;

public interface IUserService {
	
	/**
	 * 根据账户查找用户
	 * @param name
	 * @return
	 */
	List<User> findUserByName(String name);
	
	/**
	 * 根据用户查找操作记录
	 * @param userId
	 * @return
	 */
	List<Operation> findOperationByUserId(int userId);
	
	/**
	 * 创建用户/修改用户
	 * @param user
	 */
	User createOrUpdate(User user,String description);
}
