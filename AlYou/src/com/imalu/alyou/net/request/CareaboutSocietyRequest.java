package com.imalu.alyou.net.request;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.imalu.alyou.net.NetObject;

public class CareaboutSocietyRequest extends NetObject {
	private String guanzhuzhekey;
	private String gonghuikey; 
	public String getGuanzhuzekey() {
		return guanzhuzhekey;
	} 
	public void setGuanzhuzekey(String guanzhuzekey) {
		this.guanzhuzhekey = guanzhuzekey;
	} 
	public String getGonghuikey() {
		return gonghuikey;
	} 
	public void setGonghuikey(String gonghuikey) {
		this.gonghuikey = gonghuikey;
	} 
	public Map<String,String> getParams(){
		Map<String,String> map = new ConcurrentHashMap<String,String>();
		map.put("guanzhuzhekey",String.valueOf(guanzhuzhekey) );
		map.put("gonghuikey",String.valueOf(gonghuikey) );
		return map; 
	}		
 
}
