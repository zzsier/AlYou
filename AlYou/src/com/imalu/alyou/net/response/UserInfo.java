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
	public int getJifen(){
		try {
			return this.getJsonObject().getInt("Jifen");
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
	public String getRealName(){
		try {
			return this.getJsonObject().getString("RealName");
		} catch (JSONException e) {
			return "";
		}
		
	}
	public int getAge(){
		try {
			return this.getJsonObject().getInt("Age");
		} catch (JSONException e) {
			return 0;
		}
		
	}
	public String getSex(){
		try {
			return this.getJsonObject().getString("Sex");
		} catch (JSONException e) {
			return "";
		}
		
	}
	public String getLocus(){
		try {
			return this.getJsonObject().getString("Locus");
		} catch (JSONException e) {
			return "";
		}
		
	}
	public String getSocietyKey(){
		try {
			return this.getJsonObject().getString("SocietyKey");
		} catch (JSONException e) {
			return "";
		}
		
	}
	public String getJiaruGonghuiKey(){
		try {
			return this.getJsonObject().getString("JiaruGonghuiKey");
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
