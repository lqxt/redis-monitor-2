package com.redis.monitor.web.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class KeysController extends BaseProfileController{

	@RequestMapping("/keys.htm")
	public ModelAndView keys(){
		ModelAndView mv = new ModelAndView("keys") ;
		return mv ;
	}
	
	@RequestMapping("/keys/getByPattern.htm")
	public ModelAndView getByPattern(@RequestParam String uuid , @RequestParam(required=false) String patternKey){
		try {
			Set<String> keySet = redisManager.getKeysByPattern(uuid, patternKey) ;
			
			return putModel(keySet) ;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null ; 
	}
	
	@RequestMapping("/keys/value.htm")
	public ModelAndView value(@RequestParam String key , @RequestParam String type){
		Map<String , Object> res = new HashMap<String , Object>() ;
		res.put("status", 0) ;
		try{
		} catch(Exception e ) {
			res.put("status", 1) ;
			res.put("message", "停止监控时发生错误") ;
		}
		return putModel(res) ;
	}
}
