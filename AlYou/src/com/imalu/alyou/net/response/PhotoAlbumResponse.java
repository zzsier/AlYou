package com.imalu.alyou.net.response;

import com.imalu.alyou.net.NetObject;

public class PhotoAlbumResponse extends NetObject{
	/*[{"CreatedTime":"\/Date(1421142096577+0800)\/",
	 * "ID":55,
	 * "Key":"14809c2e-06b2-48e9-b3b2-162a04ee1e36",
	 * "Content":"",
	 * "Dianzan":0,
	 * "DianzanShuliang":0,
	 * "PhotoUrl1":"C:\/Users\/P8P67LE\/Desktop\/1.jpg",
	 * "PhotoUrl2":null,
	 * "PhotoUrl3":null,
	 * "PhotoUrl4":null,
	 * "PhotoUrl5":null,
	 * "Pwd":"111111",
	 * "State":0,
	 * "UserKey":"5e1df8c0-721e-4cf9-abc0-6a1f6907f4b4",
	 * "HeadPicture":"null"
	 * ,"ShifouDianzan":0,
	 * "UserName":"不错嘛？!～",
	 * "UserPhone":"1234567890",
	 * "huifulist":[]}]
	 */
	public String getDate() {
		try {
			return this.getJsonObject().getString("CreatedTime");
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
	public String getUserKey() {
		try {
			return this.getJsonObject().getString("UserKey");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return "";
		}
	}
	public String getPhotoUrl() {
		try {
			return this.getJsonObject().getString("HeadPicture");
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
	public String getUser() {
		try {
			return this.getJsonObject().getString("UserName");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return "";
		}
	}
	public int getDianZan() {
		try {
			return this.getJsonObject().getInt("DianZan");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return 0;
		}
	}
}
