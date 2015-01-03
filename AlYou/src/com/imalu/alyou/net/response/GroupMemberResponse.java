package com.imalu.alyou.net.response;

import org.json.JSONException;

import com.imalu.alyou.net.NetObject;
//{"CreatedTime":"\/Date(1420124006027+0800)\/",
//"ID":2,
//"Key":"db9b9780-69e6-4bd3-a2fb-22723db26e32",
//"ForumKey":"e5fd141e-2769-499c-9224-1dba63787093",
//"UserKey":"c1903a8b-89f5-4375-9e10-2dc476dd981d",
//"HeadPicture":null,
//"HxName":"c1903a8b89f543759e102dc476dd981d",
//"TempName":"test",
//"UserName":"杨",
//"Uuid":"5244f70a-7ba1-11e4-8fb5-bfbbd77cd152"
//	}
public class GroupMemberResponse extends NetObject{
	
	public String getKey(){
		try {
			return this.getJsonObject().getString("Key");
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
			return this.getJsonObject().getString("ForumKey");
		} catch (JSONException e) {
			return "";
		}
		
	}
	
	public String getUserKey(){
		try {
			return this.getJsonObject().getString("UserKey");
		} catch (JSONException e) {
			return "";
		}
		
	}
	
	public String getHeadPicture(){
		try {
			return this.getJsonObject().getString("HeadPicture");
		} catch (JSONException e) {
			return "";
		}
		
	}
	
	public String getHxName(){
		try {
			return this.getJsonObject().getString("HxName");
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
	
	public int getID(){
		try {
			return this.getJsonObject().getInt("ID");
		} catch (JSONException e) {
			return 0;
		}
		
	}
	

}