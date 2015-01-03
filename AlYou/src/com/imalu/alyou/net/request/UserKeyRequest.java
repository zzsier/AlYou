package com.imalu.alyou.net.request;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.imalu.alyou.net.NetObject;

public class UserKeyRequest extends NetObject{
	private String userKey;
	
	
	public String getUserKey() {
		return this.userKey;
	}


	public void setUserKey(String userKey) {
		this.userKey = userKey;
	}


	public Map<String,String> getParams(){
		Map<String,String> map = new ConcurrentHashMap<String,String>();
		map.put("userKey", userKey);
		return map;
	}
}
