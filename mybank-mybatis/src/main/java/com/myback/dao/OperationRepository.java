package com.myback.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import com.myback.entity.Operation;


public interface OperationRepository{
	
	@Select("select id,user_id,description  from operation where user_id= #{userId}")
	@Results({
		@Result(property = "id", column = "id"),
		@Result(property = "userId", column = "user_id"),
		@Result(property = "description", column = "description"),
	})
	List<Operation> findByUserId(int userId);
	
	@Insert("insert into operation(user_id,description) "
			+ "values(#{userId},#{description})")
	void save(Operation operation); 
	
}
