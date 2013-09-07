package com.redis.monitor.manager;

import java.util.List;
import java.util.Map;

import redis.clients.util.Slowlog;

import com.redis.monitor.RedisConfigXml;
import com.redis.monitor.RedisInfoDetail;
import com.redis.monitor.RedisServer;

public interface RedisManager {
	
	public List<RedisServer> redisServerList();
	
	public List<RedisInfoDetail> getRedisInfo();
	
	public RedisConfigXml getRedisConfigXmlDetail();
	
	public Map<String,String> getRedisConfigByPattern(String pattern);
	
	public String configRedisConfigXml(String key,String value);
	
	public Long getRedisDbSize();
	
	public String ping();
	
	public String flushAll();
	
	public String flushDb();
	
	
	
}
