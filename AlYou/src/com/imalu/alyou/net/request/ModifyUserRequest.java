package com.imalu.alyou.net.request;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.json.JSONObject;

import com.imalu.alyou.net.NetObject;

public class ModifyUserRequest extends NetObject{
	
	private String name;
	private String realname;
	private String phoneNum;
	private String password;

	
	public String getPhoneNum() {
		return phoneNum;
	}


	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getRealname() {
		return realname;
	}


	public void setRealname(String realname) {
		this.realname = realname;
	}


	public Map<String,String> getParams(){
		Map<String,String> map = new ConcurrentHashMap<String,String>();
		map.put("name", name);
		map.put("realname", realname);
		map.put("uPhone", phoneNum);
		map.put("pwd", password);
		return map;
	}

}
