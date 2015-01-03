package com.imalu.alyou.net.response;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import android.util.Log;

import com.imalu.alyou.net.NetObject;

public class MemberListResponse extends NetObject{

	private List<GroupMemberResponse> memberlist;
	
	public void setJsonObject(JSONArray jsonArr)
	{
		Log.e("MemberListResponse", jsonArr.toString());
		memberlist = new ArrayList<GroupMemberResponse>();
		
		try {
			for(int i = 0; i < jsonArr.length(); i++)
			{
				JSONObject jsonObject = new JSONObject();
				jsonObject = jsonArr.getJSONObject(i);
				GroupMemberResponse groupmember = new GroupMemberResponse();
				groupmember.setJsonObject(jsonObject);
				memberlist.add(groupmember);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<GroupMemberResponse> getMemberList(){
		return this.memberlist;
	}
}
