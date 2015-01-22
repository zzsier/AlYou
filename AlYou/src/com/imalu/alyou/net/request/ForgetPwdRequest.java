package com.imalu.alyou.net.request;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.json.JSONObject;

import com.imalu.alyou.net.NetObject;

public class ForgetPwdRequest extends NetObject{
	
	
	private String uid;
	private String repwd;
	

	

	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	
	public String getRepwd() {
		return repwd;
	}
	public void setRepwd(String repwd) {
		this.repwd = repwd;
	}
	public Map<String,String> getParams(){
		Map<String,String> map = new ConcurrentHashMap<String,String>();
		map.put("uid", uid);
		map.put("repwd", repwd);
		return map;
	}

}
