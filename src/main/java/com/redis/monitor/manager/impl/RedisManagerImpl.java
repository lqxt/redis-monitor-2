package com.redis.monitor.manager.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.task.TaskExecutor;

import com.ibm.icu.text.SimpleDateFormat;
import com.redis.monitor.Constants;
import com.redis.monitor.RedisCacheThreadLocal;
import com.redis.monitor.RedisConfigXml;
import com.redis.monitor.RedisInfoDetail;
import com.redis.monitor.RedisJedisPool;
import com.redis.monitor.RedisServer;
import com.redis.monitor.json.FastJson;
import com.redis.monitor.manager.RedisManager;
import com.redis.monitor.redis.BasicRedisCacheServer;
import com.redis.monitor.web.controller.RedisPoolController;

public class RedisManagerImpl implements RedisManager {
	
	public static final Logger logger = LoggerFactory.getLogger(RedisManagerImpl.class);
	
	public List<RedisServer> redisServerList() {
		return null;
	}

	public List<RedisInfoDetail> getRedisInfo() {
		String info = getBasicRedisCacheServer().getRedisInfo();
		List<RedisInfoDetail> ridList = new ArrayList<RedisInfoDetail>();
		String[] strs = info.split("\n");
		RedisInfoDetail rif = null;
		for (int i = 0; i < strs.length; i++) {
			rif = new RedisInfoDetail();
			String s = strs[i];
			String[] str = s.split(":");
			String key = str[0];
			String value = str[1];
			String description = Constants.message.getString(key);
			rif.setKey(key);
			rif.setValue(value);
			rif.setDesctiption(description);
			ridList.add(rif);
		}
		return ridList;
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

	public BasicRedisCacheServer getBasicRedisCacheServer() {
		return RedisCacheThreadLocal.get();
	}
}
