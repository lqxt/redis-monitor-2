package com.redis.monitor.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.redis.monitor.RedisInfoDetail;
import com.redis.monitor.RedisServer;

@Controller
public class HomeController  extends BaseProfileController {
	
	@RequestMapping(value="/index.htm",method=RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView();
		//TODO redis列表加载
		List<RedisServer> rsList = redisManager.redisServerList();
		mv.addObject("redisServerList", rsList);
		
		//TODO redis info信息加载
		List<RedisInfoDetail> rifList = redisManager.getRedisInfo();
		mv.addObject("rifList", rifList);
		
		//TODO redis memery加载
		
		//TODO redis keys加载
		
		//TODO redis slave log加载
		
		mv.setViewName("index");
		return mv;
	}
	
	
	
	

}
