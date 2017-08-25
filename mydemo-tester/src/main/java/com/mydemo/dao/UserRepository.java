package com.mydemo.dao;


import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.mydemo.entity.SysUser;

public interface UserRepository{
	
	@Select("select id,username,password  from sysuser where username= #{userName}")
	@Results({
		@Result(property = "id", column = "id"),
		@Result(property = "userName", column = "username"),
		@Result(property = "password", column = "password"),
	})
	SysUser findByUserName(String userName);
	
	
	@Select("insert into sysuser(username,password) values(#{userName},#{password})")
	void save(SysUser user);
	
	@Select("delete from sysuser where id=#{id}")
	void delete(int id);
}
