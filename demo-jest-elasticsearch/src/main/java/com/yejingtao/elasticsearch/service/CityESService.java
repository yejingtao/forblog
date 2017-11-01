package com.yejingtao.elasticsearch.service;

import java.util.List;
import com.yejingtao.elasticsearch.entity.Entity;

public interface CityESService {
	
	void saveEntity(Entity entity);
	
	void saveEntity(List<Entity> entityList);
	
	List<Entity> searchEntity(String searchContent);
}
