package com.imalu.alyou.net.request;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.imalu.alyou.net.NetObject;

public class ShanchuRequest extends NetObject {
	private String userkey;
	private String xinqingkey;
	 
	public String getUserkey() {
		return userkey;
	}
	public void setUserkey(String userkey) {
		this.userkey = userkey;
	}

	public String getXinqingkey() {
		return xinqingkey;
	}

	public void setXinqingkey(String xinqingkey) {
		this.xinqingkey = xinqingkey;
	}

	public Map<String, String> getParams(){
		Map<String,String> map = new ConcurrentHashMap<String,String>();
		map.put("userkey", userkey);
		map.put("xinqingkey", xinqingkey);
		return map;
	}
}
