package com.yejingtao.elasticsearch.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.yejingtao.elasticsearch.entity.Entity;
import com.yejingtao.elasticsearch.service.CityESService;

@RestController
@RequestMapping("/entityController")
public class EntityController {
	
	
	@Autowired
	CityESService cityESService;
	
	@RequestMapping(value="/save", method=RequestMethod.GET)
	public String save(long id, String name) {
		if(id>0 && StringUtils.isNotEmpty(name)) {
			Entity newEntity = new Entity(id,name);
			List<Entity> addList = new ArrayList<Entity>();
			addList.add(newEntity);
			cityESService.saveEntity(addList);
			return "OK";
		}else {
			return "Bad input value";
		}
	}
	
	@RequestMapping(value="/search", method=RequestMethod.GET)
	public List<Entity> save(String name) {
		List<Entity> entityList = null;
		if(StringUtils.isNotEmpty(name)) {
			entityList = cityESService.searchEntity(name);
		}
		return entityList;
	}
}
