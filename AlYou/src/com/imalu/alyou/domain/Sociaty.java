package com.imalu.alyou.domain;

public class Sociaty {
	private int id ;
	private String key;
	private int jifen;
	private String societysummary;
	private String societyname;
	private int type;
	private int total;
	

	
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public int getJifen() {
		return jifen;
	}
	public void setJifen(int jifen) {
		this.jifen = jifen;
	}
	public String getSocietysummary() {
		return societysummary;
	}
	public void setSocietysummary(String societysummary) {
		this.societysummary = societysummary;
	}
	public String getSocietyname() {
		return societyname;
	}
	public void setSocietyname(String societyname) {
		this.societyname = societyname;
	}
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	};
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	};
	@Override
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		return super.equals(o);
	}
	
	


}
