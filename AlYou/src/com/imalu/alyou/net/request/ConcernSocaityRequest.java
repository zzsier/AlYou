package com.imalu.alyou.net.request;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.imalu.alyou.net.NetObject;

public class ConcernSocaityRequest extends NetObject{
	private String yonghukey;
	
	
	public String getYonghuKey() {
		return this.yonghukey;
	}


	public void setYonghuKey(String yonghukey) {
		this.yonghukey = yonghukey;
	}


	public Map<String,String> getParams(){
		Map<String,String> map = new ConcurrentHashMap<String,String>();
		map.put("yonghukey", yonghukey);
		return map;
	}
}
