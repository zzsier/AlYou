package com.imalu.alyou.net.request;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.imalu.alyou.net.NetObject;

public class AssociationSearchRequest extends NetObject{
	private String SocietyName;

	public String getSocietyName() {
		return SocietyName;
	}

	public void setSocietyName(String societyName) {
		this.SocietyName = societyName;
	}
	public Map<String,String> getParams(){
		Map<String,String> map = new ConcurrentHashMap<String,String>();
		map.put("SocietyName", SocietyName);
		return map;
		
		
	}
}
