package com.myback.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.myback.entity.User;

public interface UserRepository extends CrudRepository<User, Integer>{
	
	List<User> findByName(String name);
}
