package com.redis.monitor;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;

import redis.clients.jedis.Client;


public class SocketMonitor {
	
	private static Map<String, BlockingQueue<String>> map = new ConcurrentHashMap<String, BlockingQueue<String>>();
	private static Map<String,Client> clientMap = new ConcurrentHashMap<String, Client>();
	
	
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
	
	public static void set(Client client) {
		try {
			BlockingQueue<String> blockingQueue = map.get(RedisCacheThreadLocal.getUuid());
			if (blockingQueue == null) {
				blockingQueue = new LinkedBlockingQueue<String>();
				map.put(RedisCacheThreadLocal.getUuid(), blockingQueue);
			}
			blockingQueue.put(client.getBulkReply());
			
			if (!clientMap.containsKey(RedisCacheThreadLocal.getUuid()))  clientMap.put(RedisCacheThreadLocal.getUuid(), client);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void disconnectClient() {
		Client client = clientMap.get(RedisCacheThreadLocal.getUuid());
		if (client != null) {
			client.disconnect();
			clientMap.remove(RedisCacheThreadLocal.getUuid());
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
