package com.imalu.alyou.net.request;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.imalu.alyou.net.NetObject;
/**
 * 关注
 * @author P8P67LE
 *
 */
public class OutSocaityRequest extends NetObject{
	private String gonghuikey  ;
	private String userKey;
	
	public String getUserKey() {
		return this.userKey;
	}


	public void setUserKey(String userKey) {
		this.userKey = userKey;
	}
	
	



	public String getGonghuikey() {
		return gonghuikey;
	}


	public void setGonghuikey(String gonghuikey) {
		this.gonghuikey = gonghuikey;
	}


	public Map<String,String> getParams(){
		Map<String,String> map = new ConcurrentHashMap<String,String>();
		map.put("userKey", userKey);
		map.put("gonghuikey", gonghuikey);
		return map;
	}
}
