package com.imalu.alyou.net.request;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.imalu.alyou.net.NetObject;

public class MoodCommentRequest extends NetObject {
private String Xinqingkey;

public String getXinqingkey() {
	return Xinqingkey;
}

public void setXinqingkey(String xinqingkey) {
	this.Xinqingkey = xinqingkey;
}

public Map<String,String> getParams(){
	Map<String,String> map = new ConcurrentHashMap<String,String>();
	map.put("Xinqingkey", Xinqingkey);
	return map;
}

}
