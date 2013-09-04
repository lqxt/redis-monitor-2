package com.redis.monitor.manager.impl;

import java.util.List;
import java.util.Map;

import com.redis.monitor.RedisCacheThreadLocal;
import com.redis.monitor.RedisConfigXml;
import com.redis.monitor.RedisInfoDetail;
import com.redis.monitor.RedisServer;
import com.redis.monitor.manager.RedisManager;
import com.redis.monitor.redis.BasicRedisCacheServer;

public class RedisManagerImpl implements RedisManager {

	public List<RedisServer> redisServerList() {
		return null;
	}

	public RedisInfoDetail getRedisInfo() {
		return null;
	}

	public RedisConfigXml getRedisConfigXmlDetail() {
		return null;
	}

	public Map<String, String> getRedisConfigByPattern(String pattern) {
		return null;
	}

	public String configRedisConfigXml(String key, String value) {
		return null;
	}

	public Long getRedisDbSize() {
		return null;
	}

	public String ping() {
		return null;
	}

	public String flushAll() {
		return null;
	}

	public String flushDb() {
		return null;
	}
	
	public void saveKeyData() {
		
	}
	
	public void saveMemeryData() {
		
	}
	
	public void saveOperateData() {
		
	}

	public BasicRedisCacheServer getBasicRedisCacheServer() {
		return RedisCacheThreadLocal.get();
	}
}
