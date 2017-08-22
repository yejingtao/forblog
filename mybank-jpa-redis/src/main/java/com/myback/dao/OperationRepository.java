package com.myback.dao;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import com.myback.entity.Operation;


public interface OperationRepository extends CrudRepository<Operation, Integer>{
	
	List<Operation> findByUserId(int userId);
}
