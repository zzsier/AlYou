package com.imalu.alyou.domain;

public class ConsernPerson {
	private int id;
	private String username;
	private int jifen;
	private String societykey;
	private String headpicture;
	private String key;
	
	
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getHeadpicture() {
		return headpicture;
	}
	public void setHeadpicture(String headpicture) {
		this.headpicture = headpicture;
	}
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
	public int getJifen() {
		return jifen;
	}
	public void setJifen(int jifen) {
		this.jifen = jifen;
	}
	public String getSocietykey() {
		return societykey;
	}
	public void setSocietykey(String societykey) {
		this.societykey = societykey;
	}
@Override
public int hashCode() {
	// TODO Auto-generated method stub
	return super.hashCode();
}


}
