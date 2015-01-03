package com.imalu.alyou.domain;

//{"CreatedTime":"\/Date(1420124006027+0800)\/",
//"ID":2,
//"Key":"db9b9780-69e6-4bd3-a2fb-22723db26e32",
//"ForumKey":"e5fd141e-2769-499c-9224-1dba63787093",
//"UserKey":"c1903a8b-89f5-4375-9e10-2dc476dd981d",
//"HeadPicture":null,
//"HxName":"c1903a8b89f543759e102dc476dd981d",
//"TempName":"test",
//"UserName":"杨",
//"Uuid":"5244f70a-7ba1-11e4-8fb5-bfbbd77cd152"
//	}

public class GroupMember {
	
	private int id;
	private String username;
	private String hxname;
	private String headpicUrl;
	private String key;
	private String groupkey;
	private String userkey;
	private String groupname;
	private String uuid;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getHxname() {
		return hxname;
	}
	public void setHxname(String hxname) {
		this.hxname = hxname;
	}
	public String getHeadpicUrl() {
		return headpicUrl;
	}
	public void setHeadpicUrl(String headpicUrl) {
		this.headpicUrl = headpicUrl;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getGroupkey() {
		return groupkey;
	}
	public void setGroupkey(String groupkey) {
		this.groupkey = groupkey;
	}
	public String getUserkey() {
		return userkey;
	}
	public void setUserkey(String userkey) {
		this.userkey = userkey;
	}
	public String getGroupname() {
		return groupname;
	}
	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	
	
}
