package com.imalu.alyou.net.request;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.imalu.alyou.net.NetObject;
/**
 * 关注
 * @author P8P67LE
 *
 */
public class JoinSocaityRequest extends NetObject{
	private String societykey  ;
	private String userKey;
	
	public String getUserKey() {
		return this.userKey;
	}


	public void setUserKey(String userKey) {
		this.userKey = userKey;
	}
	
	public String getSocietyKey () {
		return societykey;
	}


	public void setSocietyKey (String societykey) {
		this.societykey = societykey;
	}



	public Map<String,String> getParams(){
		Map<String,String> map = new ConcurrentHashMap<String,String>();
		map.put("userKey", userKey);
		map.put("societykey", societykey);
		return map;
	}
}
