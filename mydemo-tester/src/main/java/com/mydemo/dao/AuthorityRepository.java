package com.mydemo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.mydemo.entity.SysAuthority;

public interface AuthorityRepository{
	
	@Select("select a.id,a.name,a.description from sysauthority a, r_user_authority ur where a.id=ur.authority_id and ur.user_id=#{userId}")
	@Results({
		@Result(property = "id", column = "id"),
		@Result(property = "name", column = "name"),
		@Result(property = "description", column = "description"),
	})
	List<SysAuthority> findByUserId(int userId);
}
