package com.imalu.alyou.net.response;

import org.json.JSONException;

import com.imalu.alyou.net.NetObject;

public class ConsernResponse extends NetObject{

	
	public int getId() {
		try {
			return this.getJsonObject().getInt("ID");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
	return 0;
		}
	}
	public String getUserName() {
		try {
			return this.getJsonObject().getString("UserName");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return "";
		}
	}
	public int getJifen(){
		try {
			return this.getJsonObject().getInt("Jifen");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return 0;
		}
	}
	public String getSocietyKey() {
		try {
			return this.getJsonObject().getString("SocietyKey");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return "";
		}
	}
	public String getKey() {
		try {
			return this.getJsonObject().getString("Key");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return "";
		}
	}
	public String getHeadPicture(){
		try {
			return this.getJsonObject().getString("HeadPicture");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			return "";
		}
	}
	public String getGexingQianming() {
		try {
			return this.getJsonObject().getString("GexingQianming");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return "";
		}
		}
	}