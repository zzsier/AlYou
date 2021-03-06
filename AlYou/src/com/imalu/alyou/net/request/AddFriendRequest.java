package com.imalu.alyou.net.request;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.imalu.alyou.net.NetObject;

public class AddFriendRequest extends NetObject{
	
	private String userkey;
	private String friendkey;
	
	public String getUserkey() {
		return userkey;
	}

	public void setUserkey(String userkey) {
		this.userkey = userkey;
	}

	public String getFriendkey() {
		return friendkey;
	}

	public void setFriendkey(String friendkey) {
		this.friendkey = friendkey;
	}

	public Map<String,String> getParams(){
		Map<String,String> map = new ConcurrentHashMap<String,String>();
		map.put("userkey", userkey);
		map.put("friUserKey", friendkey);
		return map;
	}

}
