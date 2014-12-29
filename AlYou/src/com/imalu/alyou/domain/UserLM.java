package com.imalu.alyou.domain;


public class UserLM {
	private int Id;
	private String UserName;
	private String GexingQianming;
	public UserLM() {
		super();
	}
	public UserLM(String userName, String gexingQianming,int id) {
		super();
		UserName = userName;
		GexingQianming = gexingQianming;
		Id=id;
	}
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getUserName() {
		return UserName;
	}
	public void setUserName(String userName) {
		UserName = userName;
	}
	public String getGexingQianming() {
		return GexingQianming;
	}
	public void setGexingQianming(String gexingQianming) {
		GexingQianming = gexingQianming;
	}
	@Override
	public int hashCode() {
		return super.hashCode();
	}
	

}
