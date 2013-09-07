package com.redis.monitor.web.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.redis.monitor.RedisCacheThreadLocal;
import com.redis.monitor.RedisJedisPool;
import com.redis.monitor.SocketMonitor;
import com.redis.monitor.redis.RedisCacheServer;
import com.redis.monitor.web.controller.BaseProfileController;

public class ServerInteceptor extends HandlerInterceptorAdapter {
	public static final Logger logger = LoggerFactory.getLogger(ServerInteceptor.class);
	
	public static final String change_redis_uri = "change.htm";
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		//TODO 根据uuid切换到对应的redisManager上
		   String uuid = request.getParameter("uuid");
			if (uuid == null || uuid.equals("")) {
				Cookie[] cookies = request.getCookies();
				if (cookies != null && cookies.length > 0) {
					for (Cookie cookie : cookies) {
						String name = cookie.getName();
						if (name.equals("uuid")) {
							uuid = cookie.getValue();
							break;
						}
					}
		
			
			if (uuid == null || uuid.equals("")) {
				response.sendRedirect("/welcome.html");
				return false;
			}
			
		if (handler instanceof BaseProfileController) {
			RedisCacheThreadLocal.set(uuid);
			String ping = RedisJedisPool.getRedisCacheServer().ping();
			if (!ping.equals("PONG")) {
				response.sendRedirect("/welcome.html");
				return false;
            } 
			logger.info("choice redis server :{}",RedisJedisPool.getRedisServer(uuid));
		}
		return super.preHandle(request, response, handler);
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		Cookie cookie = new Cookie("uuid", RedisCacheThreadLocal.getUuid());
		response.addCookie(cookie);
		//TODO 清除上下文
		RedisCacheThreadLocal.remove();
		super.afterCompletion(request, response, handler, ex);
	}
}
