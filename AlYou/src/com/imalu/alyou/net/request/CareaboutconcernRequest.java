package com.imalu.alyou.net.request;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.imalu.alyou.net.NetObject;

public class CareaboutconcernRequest extends NetObject {
	private String guanzhurenkey;
	private String beiguanzhurenkey;

	public String getGuanzhurenkey() {
		return guanzhurenkey;
	}

	public void setGuanzhurenkey(String guanzhurenkey) {
		this.guanzhurenkey = guanzhurenkey;
	}

	public String getBeiguanzhurenkey() {
		return beiguanzhurenkey;
	}

	public void setBeiguanzhurenkey(String beiguanzhurenkey) {
		this.beiguanzhurenkey = beiguanzhurenkey;
	}

	 

	public Map<String,String> getParams(){
		Map<String,String> map = new ConcurrentHashMap<String,String>();
		map.put("guanzhurenkey",String.valueOf(guanzhurenkey) );
		map.put("beiguanzhurenkey",String.valueOf(beiguanzhurenkey) );
		return map;
		
	}		

	
}
