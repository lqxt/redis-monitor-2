package com.redis.monitor.server.jetty;


import java.io.File;
import java.io.FileInputStream;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.xml.XmlConfiguration;

public class JettyServer {
	public static void main(String[] args) throws Exception {
		//D:\\work-space\\sourcecode-workspace\\redis-monitor\\src\\main\\webapp
		File f = new File("D:\\work-space\\sourcecode-workspace\\redis-monitor\\jetty\\jetty.xml");
		System.out.println(f.exists());
		System.out.println(f.toString());
		
		Server server = new Server();  
	    XmlConfiguration configuration = new XmlConfiguration(  
	    new FileInputStream(  
	        f.getPath())); //指定自定义的jetty.xml路径  
	    configuration.configure(server);  
	    server.start();  
	}
}
