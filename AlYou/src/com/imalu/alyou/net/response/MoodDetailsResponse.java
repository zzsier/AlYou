package com.imalu.alyou.net.response;

import com.imalu.alyou.net.NetObject;
/*[{"CreatedTime":"\/Date(1421309851067+0800)\/",
 * "ID":80,"
 * Key":"dba021ed-f3ba-4b18-9a72-0ea030327a73",
 * "Content":"还能不能行了？",
 * "Dianzan":0,
 * "DianzanShuliang":0,
 * "PhotoUrl1":null,
 * "PhotoUrl2":null,
 * "PhotoUrl3":null,
 * "PhotoUrl4":null,
 * "PhotoUrl5":null,
 * "Pwd":"111111",
 * "State":0,"
 * UserKey":"5e1df8c0-721e-4cf9-abc0-6a1f6907f4b4",
 * "HeadPicture":null,
 * "ShifouDianzan":0,
 * "UserName":null,
 * "UserPhone":null,
 * "huifulist":[]},
 * {"CreatedTime":"\/Date(1421309850910+0800)\/","ID":79,"Key":"79eae972-150b-4955-842d-c25f9a3490b6","Content":"还能不能行了？","Dianzan":0,"DianzanShuliang":0,"PhotoUrl1":null,"PhotoUrl2":null,"PhotoUrl3":null,"PhotoUrl4":null,"PhotoUrl5":null,"Pwd":"111111","State":0,"UserKey":"5e1df8c0-721e-4cf9-abc0-6a1f6907f4b4","HeadPicture":null,"ShifouDianzan":0,"UserName":null,"UserPhone":null,"huifulist":[]},{"CreatedTime":"\/Date(1421309850600+0800)\/","ID":78,"Key":"1df947f2-444a-4c0e-9159-3735f75473bc","Content":"还能不能行了？","Dianzan":0,"DianzanShuliang":0,"PhotoUrl1":null,"PhotoUrl2":null,"PhotoUrl3":null,"PhotoUrl4":null,"PhotoUrl5":null,"Pwd":"111111","State":0,"UserKey":"5e1df8c0-721e-4cf9-abc0-6a1f6907f4b4","HeadPicture":null,"ShifouDianzan":0,"UserName":null,"UserPhone":null,"huifulist":[]}]*/
public class MoodDetailsResponse extends NetObject{
	public String getCreateTime() {
		try {
			return this.getJsonObject().getString("CreatedTime");
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
	public String getPhotoUrl3() {
		try {
			return this.getJsonObject().getString("PhotoUrl3");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return "";
		}
	}
	public String getPhotoUrl4() {
		try {
			return this.getJsonObject().getString("PhotoUrl4");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return "";
		}
	}
	public String getPhotoUrl5() {
		try {
			return this.getJsonObject().getString("PhotoUrl5");
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
	public int getDianzan() {
		try {
			return this.getJsonObject().getInt("Dianzan");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return 0;
		}
	}
}
