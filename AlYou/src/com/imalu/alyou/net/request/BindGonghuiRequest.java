package com.imalu.alyou.net.request;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.imalu.alyou.net.NetObject;

public class BindGonghuiRequest extends NetObject {
	private String UID;
	private String SocietyKey;
 
 
	public String getUID() {
		return UID;
	}


	public void setUID(String uID) {
		this.UID = uID;
	}


	public String getSocietyKey() {
		return SocietyKey;
	}


	public void setSocietyKey(String societyKey) {
		this.SocietyKey = societyKey;
	}


	public Map<String,String> getParams(){
		Map<String,String> map = new ConcurrentHashMap<String,String>();
		map.put("UID",String.valueOf(UID) );
		map.put("SocietyKey",String.valueOf(SocietyKey) );
		return map;
		
	}		

	
}
