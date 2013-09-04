package com.redis.monitor;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;


public class SocketMonitor {
	
	private static Map<String, BlockingQueue<String>> map = new ConcurrentHashMap<String, BlockingQueue<String>>();
	
	public static void set(String data) {
		try {
			BlockingQueue<String> blockingQueue = map.get(RedisCacheThreadLocal.getUuid());
			if (blockingQueue == null) {
				blockingQueue = new LinkedBlockingQueue<String>();
				map.put(RedisCacheThreadLocal.getUuid(), blockingQueue);
			}
			blockingQueue.put(data);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static String getData() throws Exception {
		return map.get(RedisCacheThreadLocal.getUuid()).take();
	}
	
	public static void clear() {
		map.get(RedisCacheThreadLocal.getUuid()).clear();
	}
	
	public static void clearAll() {
		for (Iterator<String> itr = map.keySet().iterator(); itr.hasNext();) {
			String key = itr.next();
			BlockingQueue<String> queue = map.get(key);
			queue.clear();
		}
	}
}
