package com.myback.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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
	@Cacheable(value="userInfo", key="#name")
	public List<User> findUserByName(String name) {
		return userRepository.findByName(name);
	}

	@Override
	@Cacheable(value="operationInfo", key="#userId")
	public List<Operation> findOperationByUserId(int userId) {
		return operationRepository.findByUserId(userId);
	}

	@Override
	@Caching(evict = { @CacheEvict(value="userInfo", key="#user.name"),
			@CacheEvict(value="operationInfo", key="#user.id")})
	@Transactional(isolation = Isolation.DEFAULT,propagation = Propagation.REQUIRED)
	//@Transactional
	public User createOrUpdate(User user,String description) {
		user = userRepository.save(user);
		Operation operation = new Operation(user.getId(),description);
		operationRepository.save(operation);
		return user;
	}

}
