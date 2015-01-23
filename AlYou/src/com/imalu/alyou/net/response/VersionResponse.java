package com.imalu.alyou.net.response;

import com.imalu.alyou.net.NetObject;

public class VersionResponse extends NetObject{
	public String getBanbenHao() {
		try {
			return this.getJsonObject().getString("BanbenHao");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return ""; 
		}
	}
	public String getAppURL() {
		try {
			return this.getJsonObject().getString("AppURL");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return ""; 
		}
	}
	public String getBanbenShuoming() {
		try {
			return this.getJsonObject().getString("BanbenShuoming");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return ""; 
		}
	}

}
