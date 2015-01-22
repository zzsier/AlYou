package com.imalu.alyou.net.request;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.json.JSONObject;

import com.imalu.alyou.net.NetObject;

public class ChangePwdRequest extends NetObject{
	
	private String uid;
	private String oldPwd;
	private String newPwd;
	

	

	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getOldPwd() {
		return oldPwd;
	}
	public void setOldPwd(String oldPwd) {
		this.oldPwd = oldPwd;
	}
	public String getNewPwd() {
		return newPwd;
	}
	public void setNewPwd(String newPwd) {
		this.newPwd = newPwd;
	}
	public Map<String,String> getParams(){
		Map<String,String> map = new ConcurrentHashMap<String,String>();
		map.put("uid", uid);
		map.put("oldPwd", oldPwd);
		map.put("newPwd", newPwd);
		return map;
	}

}
