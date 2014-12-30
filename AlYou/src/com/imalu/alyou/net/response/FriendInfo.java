package com.imalu.alyou.net.response;

import org.json.JSONException;

import com.imalu.alyou.domain.User;
import com.imalu.alyou.net.NetObject;

public class FriendInfo extends NetObject{
	
	private int unreadMsgCount;
	private String header;

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public int getUnreadMsgCount() {
		return unreadMsgCount;
	}

	public void setUnreadMsgCount(int unreadMsgCount) {
		this.unreadMsgCount = unreadMsgCount;
	}

	@Override
	public int hashCode() {
		return 17 * getUserName().hashCode();
	}

	@Override
	public boolean equals(Object o) {
		if (o == null || !(o instanceof User)) {
			return false;
		}
		return getUserName().equals(((User) o).getUsername());
	}

	@Override
	public String toString() {
		return getUserName();
	}
	
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
	
	public String getHXName() {
		try {
			return this.getJsonObject().getString("HxName");
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
	public String getHeadPicture(){
		try {
			return this.getJsonObject().getString("HeadPicture");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			return "";
		}
	}
}
