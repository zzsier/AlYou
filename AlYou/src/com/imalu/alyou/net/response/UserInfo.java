package com.imalu.alyou.net.response;

import org.json.JSONException;
import org.json.JSONObject;

import com.imalu.alyou.net.NetObject;

public class UserInfo extends NetObject{
	
	public int getID(){
		try {
			return this.getJsonObject().getInt("ID");
		} catch (JSONException e) {
			return 0;
		}
		
	}
	
	public String getHXName(){
		try {
			return this.getJsonObject().getString("HxName");
		} catch (JSONException e) {
			return "";
		}
		
	}
	
	public String getHXPwd(){
		try {
			return this.getJsonObject().getString("Key");
		} catch (JSONException e) {
			return "";
		}
		
	}
	public String getKey(){
		try {
			return this.getJsonObject().getString("Key");
		} catch (JSONException e) {
			return "";
		}
		
	}
	public String getUserName(){
		try {
			return this.getJsonObject().getString("UserName");
		} catch (JSONException e) {
			return "";
		}
		
	}
	

}
