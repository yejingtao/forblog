package com.yejingtao.elasticsearch.service.impl;

import java.io.IOException;
import java.util.List;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yejingtao.elasticsearch.entity.Entity;
import com.yejingtao.elasticsearch.service.CityESService;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestResult;
import io.searchbox.core.Bulk;
import io.searchbox.core.Index;
import io.searchbox.core.Search;

@Service
public class CityESServiceImpl implements CityESService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CityESServiceImpl.class);
	
	@Autowired
	private JestClient jestClient;
	
	@Override
	public void saveEntity(Entity entity) {
		Index index = new Index.Builder(entity).index(Entity.INDEX_NAME).type(Entity.TYPE).build();
		try {
			jestClient.execute(index);
			LOGGER.info("ES 插入完成");
		} catch (IOException e) {
			e.printStackTrace();
			LOGGER.error(e.getMessage());
		}
	}
	
	
	/**
	 * 批量保存内容到ES
	 */
	@Override
	public void saveEntity(List<Entity> entityList) {
		Bulk.Builder bulk = new Bulk.Builder();
		for(Entity entity : entityList) {
			Index index = new Index.Builder(entity).index(Entity.INDEX_NAME).type(Entity.TYPE).build();
			bulk.addAction(index);
		}		
		try {
			jestClient.execute(bulk.build());
			LOGGER.info("ES 插入完成");
		} catch (IOException e) {
			e.printStackTrace();
			LOGGER.error(e.getMessage());
		}
	}
	
	/**
	 * 在ES中搜索内容
	 */
	@Override
	public List<Entity> searchEntity(String searchContent){
		SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
		//searchSourceBuilder.query(QueryBuilders.queryStringQuery(searchContent));
		//searchSourceBuilder.field("name");
		searchSourceBuilder.query(QueryBuilders.matchQuery("name",searchContent));
		Search search = new Search.Builder(searchSourceBuilder.toString())
				.addIndex(Entity.INDEX_NAME).addType(Entity.TYPE).build();
		try {
			JestResult result = jestClient.execute(search);
			return result.getSourceAsObjectList(Entity.class);
		} catch (IOException e) {
			LOGGER.error(e.getMessage());
			e.printStackTrace();
		}
		return null;		
	}
}
