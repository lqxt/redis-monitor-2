<<<<<<< HEAD
package com.redis.monitor.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.redis.monitor.RedisInfoDetail;
import com.redis.monitor.RedisJedisPool;
import com.redis.monitor.RedisServer;
import com.redis.monitor.entity.Operate;

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
		
		mv.addObject("host",RedisJedisPool.getRedisServer().getHost());
		mv.addObject("port", RedisJedisPool.getRedisServer().getPort());
		
		//TODO redis memery加载
		
		//TODO redis keys加载
		
		//TODO redis slave log加载
		List<Operate> opList = redisManager.findAllOperateDetail();
		mv.addObject("opList", opList);
		
		mv.setViewName("index");
		return mv;
	}
	
	
	
	

}
=======
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
>>>>>>> branch 'master' of https://github.com/litiebiao2012/redis-monitor.git
