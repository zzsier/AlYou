package com.imalu.alyou.net.request;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.imalu.alyou.net.NetObject;
/**
 * 关注
 * @author P8P67LE
 *
 */
public class GetSocaityRequest extends NetObject{
	private String societykey  ;
	
	
	public String getSocietyKey () {
		return societykey;
	}


	public void setSocietyKey (String societykey) {
		this.societykey = societykey;
	}


	public Map<String,String> getParams(){
		Map<String,String> map = new ConcurrentHashMap<String,String>();
		map.put("societykey", societykey);
		return map;
	}
}
