package com.imalu.alyou.net.response;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.imalu.alyou.domain.ConsernPerson;
import com.imalu.alyou.net.NetObject;

public class FriendListResponse extends NetObject{

	private List<FriendInfo> friendList;
	
	public void setJsonObject(JSONArray jsonArr)
	{
		Log.e("FriendList", jsonArr.toString());
		friendList = new ArrayList<FriendInfo>();
		
		try {
			for(int i = 0; i < jsonArr.length(); i++)
			{
				JSONObject jsonObject = new JSONObject();
				jsonObject = jsonArr.getJSONObject(i);
				FriendInfo friend = new FriendInfo();
				friend.setJsonObject(jsonObject);
				friendList.add(friend);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<FriendInfo> getFriendList(){
		return this.friendList;
	}
}