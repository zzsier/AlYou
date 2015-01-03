package com.imalu.alyou.net.response;

import org.json.JSONException;

import com.imalu.alyou.net.NetObject;

public class UserGroupResponse extends NetObject{
	
	public String getCreateKey(){
		try {
			return this.getJsonObject().getString("CreateKey");
		} catch (JSONException e) {
			return "";
		}
		
	}
	
	public String getGroupName(){
		try {
			return this.getJsonObject().getString("TempName");
		} catch (JSONException e) {
			return "";
		}
		
	}
	
	public String getGroupKey(){
		try {
			return this.getJsonObject().getString("Key");
		} catch (JSONException e) {
			return "";
		}
		
	}
	
	public int getGroupID(){
		try {
			return this.getJsonObject().getInt("ID");
		} catch (JSONException e) {
			return 0;
		}
		
	}
	

}