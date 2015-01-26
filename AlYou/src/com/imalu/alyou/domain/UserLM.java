package com.imalu.alyou.domain;


public class UserLM {
	private int Id;
	private String UserName;
	private String GexingQianming;
	private int Jifen;
	private String SocietyName;
	private String key;
	private String SocietyKey;
	
	public UserLM() {
		super();
	}
	
	public UserLM(int id, String userName, String gexingQianming, int jifen,
			String societyName) {
		super();
		Id = id;
		UserName = userName;
		GexingQianming = gexingQianming;
		Jifen = jifen;
		SocietyName = societyName;
	}

	/*public UserLM(int id, String userName, String gexingQianming, int jifen) {
		super();
		Id = id;
		UserName = userName;
		GexingQianming = gexingQianming;
		Jifen = jifen;
	}*/

	public int getId() {
		return Id;
	}
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getSocietyKey() {
		return SocietyKey;
	}

	public void setSocietyKey(String societyKey) {
		SocietyKey = societyKey;
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
	
	
	public int getJifen() {
		return Jifen;
	}
	public void setJifen(int jifen) {
		Jifen = jifen;
	}
	
	public String getSocietyName() {
		return SocietyName;
	}

	public void setSocietyName(String societyName) {
		SocietyName = societyName;
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}
	

}
