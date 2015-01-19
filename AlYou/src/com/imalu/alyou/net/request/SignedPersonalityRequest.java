package com.imalu.alyou.net.request;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.imalu.alyou.net.NetObject;

public class SignedPersonalityRequest  extends NetObject{
	private String  userkey;
	private String  gexingqianming;
	public String getUserkey() {
		return userkey;
	}
	public void setUserkey(String userkey) {
		this.userkey = userkey;
	}
	public String getGexingqianming() {
		return gexingqianming;
	}
	public void setGexingqianming(String gexingqianming) {
		this.gexingqianming = gexingqianming;
	}
	public Map<String,String> getParams(){
		Map<String,String> map = new ConcurrentHashMap<String,String>();
		map.put("userkey",String.valueOf(userkey) );
		map.put("gexingqianming",String.valueOf(gexingqianming) );
		return map; 
	}		
 
}
