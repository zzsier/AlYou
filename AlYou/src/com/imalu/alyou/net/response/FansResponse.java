package com.imalu.alyou.net.response;

import org.json.JSONException;

import com.imalu.alyou.net.NetObject;

public class FansResponse extends NetObject{
/*	[{"CreatedTime":"\/Date(1418137000923+0800)\/",
 * "ID":68,
 * "Key":"c2e27901-c214-4a80-87f2-6ab7d0662f9b",
 * "Age":0,
 * "ErWeiMa":"http:\/\/114.112.98.148\/upload\/ae027d98-d550-4340-b169-165df705782a.jpg",
 * "GameID":0,
 * "GexingQianming":null,
 * "HeadPicture":null,
 * "HxName":"c2e27901c2144a8087f26ab7d0662f9b",
 * "Jifen":0,
 * "Locus":null,
 * "LuntanDengluming":"1234567890123",
 * "LuntanGonghuiID":0,
 * "PengyouquanZuihouchakanShijian":null,
 * "Pwd":"96e79218965eb72c92a549dd5a330112",
 * "RealName":null,
 * "Server":null,
 * "SetFriCheck":1,
 * "Sex":0,
 * "ShifouJieshouTuisong":1,
 * "SocietyKey":null,
 * "Type":"user",
 * "UseRole":null,
 * "UserName":null,
 * "UserPhone":"1234567890123",
 * "UserStatus":1,
 * "Uuid":"966236da-7fb3-11e4-b3ff-896a41105ed5",
 * "YinsiShezhi":0,
 * "ZuobiaoX":0.000,
 * "ZuobiaoY":0.000,
 * "activated":1}]
 */
	
	
	
	public int getId() {
		try {
			return this.getJsonObject().getInt("ID");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
	return 0;
		}
	}
	public int getJiFen() {
		try {
			return this.getJsonObject().getInt("Jifen");
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
	public String getGexingQianming() {
		try {
			return this.getJsonObject().getString("GexingQianming");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return "";
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
	

}
