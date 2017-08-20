package com.myback.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myback.dao.OperationRepository;
import com.myback.dao.UserRepository;
import com.myback.entity.Operation;
import com.myback.entity.User;
import com.myback.service.IUserService;

@Service
public class UserServiceImpl implements IUserService{
	
	@Autowired
	private OperationRepository operationRepository;
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public List<User> findUserByName(String name) {
		return userRepository.findByName(name);
	}

	@Override
	public List<Operation> findOperationByUserId(int userId) {
		return operationRepository.findByUserId(userId);
	}

	@Override
	public User createOrUpdate(User user,String description) {
		user = userRepository.save(user);
		Operation operation = new Operation(user.getId(),description);
		operationRepository.save(operation);
		return user;
	}

}
