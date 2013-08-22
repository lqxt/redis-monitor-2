package com.redis.monitor.web.controller;

import org.springframework.web.servlet.ModelAndView;

import com.redis.monitor.Constants;
import com.redis.monitor.manager.RedisManager;


public class BaseProfileController extends AbstractController {
	
	protected RedisManager redisManager;
	
	protected ModelAndView getJsonModelAndView() {
		return new ModelAndView(Constants.JSON_VIEW);
	}
	
	protected ModelAndView putModel(Object data) {
		ModelAndView mv = getJsonModelAndView();
		mv.addObject("data", data);
		return mv;
	}

	public RedisManager getRedisManager() {
		return redisManager;
	}

	public void setRedisManager(RedisManager redisManager) {
		this.redisManager = redisManager;
	}
}
