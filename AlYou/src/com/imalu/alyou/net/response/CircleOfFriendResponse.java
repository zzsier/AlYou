package com.imalu.alyou.net.response;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;

import com.imalu.alyou.domain.HuifuXinqingLM;
import com.imalu.alyou.domain.XinqingLM;
import com.imalu.alyou.net.NetObject;

public class CircleOfFriendResponse extends NetObject{
  
	public String getUserName() {
		try {
			return this.getJsonObject().getString("UserName");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return "";
		}
	}
	
	public String getCreatedTime() {
		try {
			return this.getJsonObject().getString("CreatedTime");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return "";
		}
	}
	public String getPhotoUrl1() {
		try {
			return this.getJsonObject().getString("PhotoUrl1");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return "";
		}
	}
	public String getPhotoUrl2() {
		try {
			return this.getJsonObject().getString("PhotoUrl2");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return "";
		}
	}
	public String getHeadPicture() {
		try {
			return this.getJsonObject().getString("HeadPicture");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return "";
		}
	}
	public String getPhotoUrl3() {
		try {
			return this.getJsonObject().getString("PhotoUrl3");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return "";
		}
	}
	public String getContent() {
		try {
			return this.getJsonObject().getString("Content");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return "";
		}
	}
	public int getDianzan() {
		try {
			return this.getJsonObject().getInt("Dianzan");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			return 0;
		}
	}
  
	public JSONArray getpinglunLMs(){
		try {
			return this.getJsonObject().getJSONArray("pinglunLMs");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			return getpinglunLMs();
		}
	}  
	
}
