package com.imalu.alyou.net.response;

import org.json.JSONException;

import com.imalu.alyou.net.NetObject;

public class AssociationSearchResponse extends NetObject{
	 
	public int getId() {
		try {
			return this.getJsonObject().getInt("Id");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
	return 0;
		}
	}
	public int getJifen() {
		try {
			return this.getJsonObject().getInt("jifen");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
	return 0;
		}
	}
	public String getSocietyName() {
		try {
			return this.getJsonObject().getString("SocietyName");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return "";
		}
	}
	public String getSocietySummary() {
		try {
			return this.getJsonObject().getString("SocietySummary");
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

}
