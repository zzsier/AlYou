package com.imalu.alyou.net;

import java.util.Map;

import org.json.JSONObject;

public class NetObject {
	
	private JSONObject jsonObj;
	
	public void setJsonObject(JSONObject jsonObj)
	{
		this.jsonObj = jsonObj;
	}
	
	public JSONObject getJsonObject()
	{
		return jsonObj;
	}
	
	public Map<String,String> getParams()
	{
		return null;
	}

}
