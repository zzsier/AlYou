package com.imalu.alyou.net.request;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.imalu.alyou.net.NetObject;
/**
 * 关注
 * @author P8P67LE
 *
 */
public class ConcernRequest extends NetObject{
	private String userkey;
	
	
	public String getUserkey() {
		return userkey;
	}


	public void setUserkey(String userkey) {
		this.userkey = userkey;
	}


	public Map<String,String> getParams(){
		Map<String,String> map = new ConcurrentHashMap<String,String>();
		map.put("userkey", userkey);
		return map;
	}
}
