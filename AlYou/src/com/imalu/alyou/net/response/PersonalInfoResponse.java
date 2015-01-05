package com.imalu.alyou.net.response;

import org.json.JSONException;

import com.imalu.alyou.net.NetObject;

public class PersonalInfoResponse extends NetObject {
	public int getId() {
		try {
			return this.getJsonObject().getInt("id");
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

	public int getJifen() {
		try {
			return this.getJsonObject().getInt("Jifen");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}
	public String getSocietyName() {
		try {
			return this.getJsonObject().getString("SocietyName");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		}
	}
	
}
