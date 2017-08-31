package com.feign.consumer.service;

import org.springframework.cloud.netflix.feign.FeignClient;

import com.yejingtao.feign.api.HelloService;

@FeignClient(name="demo-feign-serviceimpl", fallback=FeignServiceFallback.class)
public interface FeignService extends HelloService{

}
