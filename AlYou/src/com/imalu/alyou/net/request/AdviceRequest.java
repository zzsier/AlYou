package com.imalu.alyou.net.request;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.imalu.alyou.net.NetObject;

public class AdviceRequest extends NetObject{
	private String UserKey;
	private String Phone;
	private String PingjiaNeirong;
	public String getUserKey() {
		return UserKey;
	}
	public void setUserKey(String userKey) {
		UserKey = userKey;
	}
	public String getPhone() {
		return Phone;
	}
	public void setPhone(String phone) {
		Phone = phone;
	}
	public String getPingjiaNeirong() {
		return PingjiaNeirong;
	}
	public void setPingjiaNeirong(String pingjiaNeirong) {
		PingjiaNeirong = pingjiaNeirong;
	}
	public Map<String, String> getParams(){
		Map<String,String> map = new ConcurrentHashMap<String,String>();
		map.put("UserKey", UserKey);
		map.put("Phone", Phone);
		map.put("PingjiaNeirong",PingjiaNeirong);
		return map;
	}
}
