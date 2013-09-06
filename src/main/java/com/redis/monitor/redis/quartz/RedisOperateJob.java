package com.redis.monitor.redis.quartz;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import redis.clients.util.Slowlog;

import com.redis.monitor.redis.BasicRedisCacheServer;

public class RedisOperateJob extends AbstractRedisJob {

	@Override
	protected Map<String,Object> getSaveData(BasicRedisCacheServer redisCacheServer) {
		List<Slowlog> list = redisCacheServer.slowlogs();
		Map<String,Object> map  = null;
		if (list != null && list.size() > 0) {
			map = new HashMap<String, Object>(); 
			for (Slowlog sl : list) {
				map.put("id", sl.getId());
				map.put("create_time", getDateStr());
				map.put("execute_time", getDateStr(sl.getTimeStamp()));
				map.put("used_time", sl.getExecutionTime() + "μs");
				map.put("args", sl.getArgs());
			}
		} 
		return map;
		
		
	}


}
