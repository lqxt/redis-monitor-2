package com.redis.monitor;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;

import redis.clients.jedis.Client;


public class SocketMonitor {
	
	private static final Map<String, BlockingQueue<String>> map = new ConcurrentHashMap<String, BlockingQueue<String>>();
	private static final Map<String,Client> clientMap = new ConcurrentHashMap<String, Client>();
	
	
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
	
	public static void set(final Client client , final String uuid) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					BlockingQueue<String> blockingQueue = map.get(uuid);
					if (!clientMap.containsKey(uuid))  clientMap.put(uuid, client);
					if (blockingQueue == null) {
						blockingQueue = new LinkedBlockingQueue<String>();
						map.put(uuid, blockingQueue);
					}
					blockingQueue.put(client.getBulkReply());
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start(); 
		
	}
	
	public static void set(final Client client) {
		final String uuid = RedisCacheThreadLocal.getUuid();
		BlockingQueue<String> blockingQueue = map.get(uuid);
		if (!clientMap.containsKey(uuid))  clientMap.put(uuid, client);
		boolean flag = false;
		if (blockingQueue == null) {
			flag = true;
			blockingQueue = new LinkedBlockingQueue<String>();
			map.put(uuid, blockingQueue);
		}
		
		if (flag) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						System.out.println("###:" + client.getBulkReply());
						map.get(uuid).put(client.getBulkReply());
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
				}
			}).start(); 
		}
	}
		
	
	public static void disconnectClient() {
		Client client = clientMap.get(RedisCacheThreadLocal.getUuid());
		if (client != null) {
			client.disconnect();
			clientMap.remove(RedisCacheThreadLocal.getUuid());
		}
	}
	
	public static void disconnectClient(String uuid) {
		
		Client client = clientMap.get(uuid) ;
		if (client != null) {
			client.disconnect();
			clientMap.remove(RedisCacheThreadLocal.getUuid());
		}
	}
	
	public static String getData() throws Exception {
		return map.get(RedisCacheThreadLocal.getUuid()).take();
	}
	
	public static List<String> getData(String uuid ) throws Exception {
		List<String> resList = new ArrayList<String>() ;
		Queue<String> queue = map.get(RedisJedisPool.getRedisServer(uuid));
		while(true){
			String obj = queue.poll() ;
			if(obj == null || resList.size() > 100 ) {
				break ;
			}
			resList.add(obj) ;
		}
		return resList ;
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
