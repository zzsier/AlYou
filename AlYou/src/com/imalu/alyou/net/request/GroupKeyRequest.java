package com.imalu.alyou.net.request;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.imalu.alyou.net.NetObject;

public class GroupKeyRequest extends NetObject{
	private String groupKey;
	
	
	public String getGroupKey() {
		return this.groupKey;
	}


	public void setGroupKey(String groupKey) {
		this.groupKey = groupKey;
	}


	public Map<String,String> getParams(){
		Map<String,String> map = new ConcurrentHashMap<String,String>();
		map.put("forumKey", groupKey);
		return map;
	}
}
