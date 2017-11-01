package com.yejingtao.elasticsearch.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.yejingtao.elasticsearch.entity.Entity;

/**
 * Entity ES操作类
 * @author yejingtao
 *
 */
public interface EntityRepository extends ElasticsearchRepository<Entity,Long>{

}
