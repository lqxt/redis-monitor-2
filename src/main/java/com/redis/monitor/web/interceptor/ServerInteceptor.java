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
import com.redis.monitor.web.controller.BaseProfileController;

public class ServerInteceptor extends HandlerInterceptorAdapter {
	public static final Logger logger = LoggerFactory.getLogger(ServerInteceptor.class);
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		//TODO 根据uuid切换到对应的redisManager上
		
		Cookie[] cookies = request.getCookies();
		String uuid = "";
		if (cookies != null && cookies.length > 0) {
			for (Cookie cookie : cookies) {
				String name = cookie.getName();
				if (name.equals("uuid")) {
					uuid = cookie.getValue();
					break;
				}
			}
		}
		if (handler instanceof BaseProfileController) {
			logger.info("choice redis server :{}",RedisJedisPool.getRedisServer(uuid));
			RedisCacheThreadLocal.set(uuid);
		}
		return super.preHandle(request, response, handler);
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		//TODO 清除上下文
		RedisCacheThreadLocal.remove();
		super.afterCompletion(request, response, handler, ex);
	}
}
