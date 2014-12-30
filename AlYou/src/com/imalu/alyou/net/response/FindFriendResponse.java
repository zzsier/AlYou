package com.imalu.alyou.net.response;

import org.json.JSONException;

import com.imalu.alyou.net.NetObject;

public class FindFriendResponse  extends  NetObject{

	public String getHeadPicture() {
		try {
			return this.getJsonObject().getString("HeadPicture");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return "";
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
	public String getKey() {
		try {
			return this.getJsonObject().getString("key");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return "";
		}
	}
	public int getJiFen() {
		try {
			return this.getJsonObject().getInt("JiFen");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			return 0;
		}
	}
	public int getLuntanGonghuiID() {
		try {
			return this.getJsonObject().getInt("LuntanGonghuiID");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			return 0;
		}
	}
	public int getId() {
		try {
			return this.getJsonObject().getInt("ID");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
	return 0;
		}
	}
	

	
}
