package com.imalu.alyou.net.request;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.imalu.alyou.net.NetObject;

public class VerifyFriendRequest extends NetObject{
	private String UID;
	private int SetFriCheck;
	public String getUID() {
		return UID;
	}
	public void setUID(String uID) {
		UID = uID;
	}
	public int getSetFriCheck() {
		return SetFriCheck;
	}
	public void setSetFriCheck(int setFriCheck) {
		SetFriCheck = setFriCheck;
	}
	public Map<String, String> getParams(){
		Map<String,String> map = new ConcurrentHashMap<String,String>();
		map.put("UID", UID);
		map.put("SetFriCheck", String.valueOf(SetFriCheck));
		return map;
	}
}
