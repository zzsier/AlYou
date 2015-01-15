package com.imalu.alyou.net.request;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.imalu.alyou.net.NetObject;

public class CircleOfFriendRequest extends NetObject {
	private String userKey;
	private int pageSize;
	private int pageIndex;
	
	public String getUserKey() {
		return userKey;
	}

	public void setUserKey(String userKey) {
		this.userKey = userKey;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public Map<String, String> getParams(){
		Map<String,String> map = new ConcurrentHashMap<String,String>();
		map.put("userKey", userKey);
		map.put("pageSize", String.valueOf(pageSize));
		map.put("pageIndex", String.valueOf(pageIndex));
		return map;
	}
}
