package com.imalu.alyou.net.response;

import com.imalu.alyou.net.NetObject;

public class MoodCommentResponse extends NetObject {
	public String getHuifurenName() {
		try {
			return this.getJsonObject().getString("HuifurenName");
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
}
