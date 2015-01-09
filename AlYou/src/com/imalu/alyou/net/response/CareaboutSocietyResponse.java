package com.imalu.alyou.net.response;
 
import org.json.JSONException;

import com.imalu.alyou.net.NetObject;

public class CareaboutSocietyResponse  extends  NetObject{

	public boolean getCode() throws JSONException{
		return this.getJsonObject().getBoolean("BOOL");
	}
	
	public String getInfo() throws JSONException{
		return this.getJsonObject().getString("Info");
	}
	 
}
