package com.imalu.alyou.domain;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.JSONException;

import com.imalu.alyou.net.response.GroupMemberResponse;

public class ConversationGroup {
	
	int groupID;
	String createKey;
	String groupName;
	String groupKey;
	List<GroupMember> memberlist;
	
	public void addMember(GroupMember member) {
		if(memberlist == null) {
			memberlist = new ArrayList<GroupMember>();
		}
		memberlist.add(member);
	}
	
	public List<String> getMemberNameList() {
		List<String> memberNameList = new ArrayList<String>();
		for (Iterator<GroupMember> iter = memberlist.iterator(); iter.hasNext();) {
			GroupMember groupMember = (GroupMember)iter.next();
			memberNameList.add(groupMember.getGroupname());
		}
		return memberNameList;
	}
	
	public void addMember(GroupMemberResponse memberRes) {
		if(memberlist == null) {
			memberlist = new ArrayList<GroupMember>();
		}
		GroupMember member = new GroupMember();
		member.setGroupkey(memberRes.getGroupKey());
		member.setGroupname(memberRes.getGroupName());
		member.setHeadpicUrl(memberRes.getHeadPicture());
		member.setHxname(memberRes.getHxName());
		member.setId(memberRes.getID());
		member.setKey(memberRes.getKey());
		member.setUserkey(memberRes.getUserKey());
		member.setUsername(memberRes.getUserName());
		memberlist.add(member);
	}
	
	public List<GroupMember> getMemberlist() {
		return memberlist;
	}
	public void setMemberlist(List<GroupMember> memberlist) {
		this.memberlist = memberlist;
	}
	public int getGroupID() {
		return groupID;
	}
	public void setGroupID(int groupID) {
		this.groupID = groupID;
	}
	public String getCreateKey() {
		return createKey;
	}
	public void setCreateKey(String createKey) {
		this.createKey = createKey;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getGroupKey() {
		return groupKey;
	}
	public void setGroupKey(String groupKey) {
		this.groupKey = groupKey;
	}
	
	@Override
	public String toString(){
		return "groupName:" + groupName;
	}
	
}
