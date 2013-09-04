package com.redis.monitor;

import redis.clients.jedis.Client;
import redis.clients.jedis.JedisMonitor;

public  class FeJedisMonitor extends JedisMonitor {
	
	public void proceed(Client client) {
        this.client = client;
        this.client.setTimeoutInfinite();
        do {
            String command = client.getBulkReply();
            SocketMonitor.set(command);
        } while (client.isConnected());
    }
	 public  void onCommand(String command) {
		 
	 }
}
