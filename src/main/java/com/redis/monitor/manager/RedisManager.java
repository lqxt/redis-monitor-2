<<<<<<< HEAD
package com.redis.monitor.manager;

import java.util.List;
import java.util.Map;
import java.util.Set;

import redis.clients.util.Slowlog;

import com.redis.monitor.RedisConfig; 
import com.redis.monitor.RedisInfoDetail;
import com.redis.monitor.RedisServer;
import com.redis.monitor.entity.Operate;

public interface RedisManager {
	
	public List<RedisServer> redisServerList();
	
	public List<RedisInfoDetail> getRedisInfo();
	
	public List<RedisConfig> getRedisConfigXmlDetail();
	
	public Map<String,String> getRedisConfigByPattern(String pattern);
	
	public String configRedisConfigXml(String key,String value);
	
	public Long getRedisDbSize();
	
	public String ping();
	
	public String flushAll();
	
	public String flushDb();
	
	public List<Operate> findAllOperateDetail();
	
	public void startMonitor(String uuid);
	
	public void stopMonitor(String uuid) ;
	
	public Set<String> getKeysByPattern(String uuid , String patternKey) ;
}
=======
package com.redis.monitor.manager;

import java.util.List;
import java.util.Map;

import redis.clients.util.Slowlog;

import com.redis.monitor.RedisConfig;
import com.redis.monitor.RedisInfoDetail;
import com.redis.monitor.RedisServer;
import com.redis.monitor.entity.Operate;

public interface RedisManager {
	
	public List<RedisServer> redisServerList();
	
	public List<RedisInfoDetail> getRedisInfo();
	
	public List<RedisConfig> getRedisConfigXmlDetail();
	
	public Map<String,String> getRedisConfigByPattern(String pattern);
	
	public String configRedisConfigXml(String key,String value);
	
	public Long getRedisDbSize();
	
	public String ping();
	
	public String flushAll();
	
	public String flushDb();
	
	public List<Operate> findAllOperateDetail();
	
	public void startMonitor(String uuid);
	
	public void stopMonitor(String uuid) ;
	
}
>>>>>>> branch 'master' of git@github.com:litiebiao2012/redis-monitor.git

