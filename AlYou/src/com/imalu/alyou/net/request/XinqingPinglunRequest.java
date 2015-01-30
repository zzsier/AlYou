package com.imalu.alyou.net.request;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.imalu.alyou.net.NetObject;

public class XinqingPinglunRequest extends NetObject {
	private String pinglunrenkey;
	private String pinglunneirong;
	private String xinqingkey;
	
	public String getPinglunrenkey() {
		return pinglunrenkey;
	}

	public void setPinglunrenkey(String pinglunrenkey) {
		this.pinglunrenkey = pinglunrenkey;
	}

	public String getPinglunneirong() {
		return pinglunneirong;
	}

	public void setPinglunneirong(String pinglunneirong) {
		this.pinglunneirong = pinglunneirong;
	}

	public String getXinqingkey() {
		return xinqingkey;
	}

	public void setXinqingkey(String xinqingkey) {
		this.xinqingkey = xinqingkey;
	}

	public Map<String, String> getParams(){
		Map<String,String> map = new ConcurrentHashMap<String,String>();
		map.put("pinglunrenkey", pinglunrenkey);
		map.put("pinglunneirong", pinglunneirong );
		map.put("xinqingkey", xinqingkey);
		return map;
	}
}
