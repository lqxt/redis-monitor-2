package com.redis.monitor.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.ListUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
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
		boolean isOk = false ;
		
		if (!isOk) {
			String value = redisManager.get(key) ;
			if (StringUtils.isNotBlank(value)) {
				res.put("type", "string");
				res.put("value", value);
				isOk = true ;
			}
		}
		if (!isOk) {
			Map<String , String> map = redisManager.getMap(key) ;
			if (MapUtils.isNotEmpty(map)) {
				res.put("type", "map");
				res.put("value", map);
				isOk = true ;
			}
		}
		if (!isOk) {
			List<String> list = redisManager.getList(key) ;
			if (list != null && list.size() > 0 ) {
				res.put("type", "list");
				res.put("value", list);
				isOk = true ;
			}
		}
		if (!isOk) {
			Set<String> set = redisManager.getSet(key) ;
			if (set != null && set.size() > 0 ) {
				res.put("type", "set");
				res.put("value", set);
				isOk = true ;
			}
		}
		
		try{
		} catch(Exception e ) {
			res.put("status", 1) ;
			res.put("message", "查询出错") ;
		}
		return putModel(res) ;
	}
}
