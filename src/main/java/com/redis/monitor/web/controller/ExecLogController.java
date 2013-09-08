package com.redis.monitor.web.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.redis.monitor.entity.Operate;

@Controller
public class ExecLogController extends BaseProfileController{

	@RequestMapping("/execLog.htm")
	public ModelAndView log(){
		ModelAndView mv = new ModelAndView("execLog") ;
		List<Operate> list = redisManager.findAllOperateDetail() ;
		return mv ;
	}
}
