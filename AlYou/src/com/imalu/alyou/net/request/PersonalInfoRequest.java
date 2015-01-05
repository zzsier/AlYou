package com.imalu.alyou.net.request;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.imalu.alyou.net.NetObject;

public class PersonalInfoRequest  extends NetObject{
	private String userkey;
	private String beichayonghukey;
	private int xiangcepage;
	private int xiangcesize;
	public String getUserkey() {
		return userkey;
	}
	public void setUserkey(String userkey) {
		this.userkey = userkey;
	}
	public String getBeichayonghukey() {
		return beichayonghukey;
	}
	public void setBeichayonghukey(String beichayonghukey) {
		this.beichayonghukey = beichayonghukey;
	}
	public int getXiangcepage() {
		return xiangcepage;
	}
	public void setXiangcepage(int xiangcepage) {
		this.xiangcepage = xiangcepage;
	}
	public int getXiangcesize() {
		return xiangcesize;
	}
	public void setXiangcesize(int xiangcesize) {
		this.xiangcesize = xiangcesize;
	}
	public Map<String,String> getParams(){
		Map<String,String> map = new ConcurrentHashMap<String,String>();
		map.put("userkey", userkey);
		map.put("beichayonghukey", beichayonghukey);
		map.put("xiangcepage",String.valueOf(xiangcepage));
		map.put("xiangcesize", String.valueOf(xiangcesize));
		return map;
		
		
	}
	

}
