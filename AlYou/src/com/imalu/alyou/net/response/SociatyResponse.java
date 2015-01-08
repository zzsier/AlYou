package com.imalu.alyou.net.response;

import org.json.JSONException;

import com.imalu.alyou.domain.User;
import com.imalu.alyou.net.NetObject;

public class SociatyResponse extends NetObject{
	/*
	 {"CreatedTime":"\/Date(1417498071300+0800)\/",
	 "ID":13,
	 "Key":"a2b46d03-a903-4690-95e1-35be60499cde",
	 "CommanderUserKey":null,
	 "CreaterLuntanDengluming":null,
	 "CreaterUserKey":"8ae01ff0-e625-4169-8e28-208679f636c3",
	 "Galleryful":200,
	 "Gonghuirenshu":0,
	 "HuanxinID":"1417498063635554",
	 "Jifen":0.000,
	 "LuntanGonghuiID":109,
	 "MapX":"0",
	 "MapY":"0",
	 "Portrait":"6",
	 "ShimingrenzhengPic":null,
	 "SocietyName":"erq",
	 "SocietyRank":0,
	 "SocietyStatues":false,
	 "SocietySummary":"iiiiiiiiiiiiiiiiiiiiiiiiii",
	 "SocietyType":0,"SpaceUrl":"1",
	 "ZhengjianPic":null,
	 "ChangzhuCount":0,
	 "CommanderUserName":null
	 "CreaterUserName":null,
	 "ShifouTuisong":0,
	 "ShifoushiChangzhuHuiyuan":0,
	 "ShifoushiHuiyuan":0,
 	 "Total":0,
	 "Type":4,
	 "Weidutuisongshu":0,
	 "ZhanghuList":[]}]
	 */
	
	public int getSocietyType() {
		try {
			return this.getJsonObject().getInt("SocietyType");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			return 0;
		}
	}
	public int getTotal() {
		try {
			return this.getJsonObject().getInt("Total");
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
	public String getKey() {
		try {
			return this.getJsonObject().getString("Key");
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

	public int getJifen(){
		try {
			return this.getJsonObject().getInt("Jifen");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return 0;
		}
	}

	public String Portrait(){
		try {
			return this.getJsonObject().getString("Portrait");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			return "";
		}
	}
	public String getSocietyName(){
		try {
			return this.getJsonObject().getString("SocietyName");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			return "";
		}
	}
}
