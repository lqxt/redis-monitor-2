package com.redis.monitor.server.jetty;




import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

import com.redis.monitor.Constants;

public class JettyServer {
	public static void main(String[] args) throws Exception {
		   String webapp = Constants.WEB_APP;//声明项目所在的目录
	       Server server = new Server(8080);                                //声明端口
	       WebAppContext context = new WebAppContext();                   //声明上下文对象
	       context.setDescriptor(webapp + "/WEB-INF/web.xml");                //指定web.xml文件，可选
	       context.setResourceBase(webapp);                               //设置项目路径
	       context.setContextPath("/");                                   //设置上下文根，可以任意的值
	       context.setWelcomeFiles(new String[]{"main.jsp"});             //设置欢迎页面
	       server.setHandler(context);                                    //设置句柄
	       server.start();                                                //启动
	       server.join();
	}
}
