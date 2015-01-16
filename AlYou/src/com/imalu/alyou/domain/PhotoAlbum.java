package com.imalu.alyou.domain;

public class PhotoAlbum {
	
	private String userkey;
	private String key;
	private String date;
	private String photoUrl;
	private String content;
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getPhotoUrl() {
		return photoUrl;
	}
	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getUserkey() {
		return userkey;
	}
	public void setUserkey(String userkey) {
		this.userkey = userkey;
	}
	
} 
