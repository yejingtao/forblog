package com.yejingtao.elasticsearch.service;

import java.util.List;
import com.yejingtao.elasticsearch.entity.Entity;

public interface CityESService {
	
	Long saveEntity(Entity entity);
	
	List<Entity> searchEntity(int pageNumber, int pageSize, String searchContent);
}
