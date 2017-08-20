package com.myback.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import com.myback.entity.User;


public interface UserRepository {
	
	@Select("select id,name,current  from user where name= #{name}")
	@Results({
		@Result(property = "id", column = "id"),
		@Result(property = "name", column = "name"),
		@Result(property = "current", column = "current"),
	})
	List<User> findByName(String name);
	
	@Insert("insert into user(name,current) "
			+ "values(#{name},#{current})")
	void save(User user); 
	
	@Update("update user set name= #{name}, "
			+ "current= #{current}  where"
			+ " id = #{id}")
	void update(User user);
}
