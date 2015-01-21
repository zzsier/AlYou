package com.imalu.alyou.net.request;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.imalu.alyou.net.NetObject;

public class ZanRequest extends NetObject {
	private String dianzanrenkey;
	private String xinqingkey;
	private String faburenkey;
	
	 
	 


	public String getDianzanrenkey() {
		return dianzanrenkey;
	}





	public void setDianzanrenkey(String dianzanrenkey) {
		this.dianzanrenkey = dianzanrenkey;
	}





	public String getXinqingkey() {
		return xinqingkey;
	}





	public void setXinqingkey(String xinqingkey) {
		this.xinqingkey = xinqingkey;
	}





	public String getFaburenkey() {
		return faburenkey;
	}





	public void setFaburenkey(String faburenkey) {
		this.faburenkey = faburenkey;
	}





	public Map<String, String> getParams(){
		Map<String,String> map = new ConcurrentHashMap<String,String>();
		map.put("dianzanrenkey", dianzanrenkey);
		map.put("xinqingkey", xinqingkey );
		map.put("faburenkey", faburenkey);
		return map;
	}
}
