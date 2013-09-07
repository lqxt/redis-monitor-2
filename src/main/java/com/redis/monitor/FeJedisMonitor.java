package com.redis.monitor;

import redis.clients.jedis.Client;
import redis.clients.jedis.JedisMonitor;

public  class FeJedisMonitor extends JedisMonitor {
	
	private static final int sleepTime = 1000 * 10;
	private static final int ifNoDataWhenFree = 1000 * 10;
	
	private long beginTime = System.currentTimeMillis();
	
	public void proceed(Client client) {
        this.client = client;
        this.client.setTimeoutInfinite();
        do {
        	long nowTime = System.currentTimeMillis();
        	if ((nowTime - beginTime) > ifNoDataWhenFree) {
        		beginTime = nowTime;
        		try {
					Thread.sleep(sleepTime);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
        	}
            SocketMonitor.set(client);
        } while (client.isConnected());
    }
	 public  void onCommand(String command) {
		 
	 }
}
