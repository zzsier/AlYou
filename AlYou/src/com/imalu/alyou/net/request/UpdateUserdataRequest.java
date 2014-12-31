package com.imalu.alyou.net.request;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.imalu.alyou.net.NetObject;
/**
 * 关注
 * @author P8P67LE
 *
 */
public class UpdateUserdataRequest extends NetObject{
	private String uphone ;
	private String pwd;
	private String headpicture;
	private String name;
	private String realname;
	private int sex;
	private int age;
	private String locus;
	
	
	
	
	
	public String getUphone() {
		return uphone;
	}


	public void setUphone(String uphone) {
		this.uphone = uphone;
	}


	public String getPwd() {
		return pwd;
	}


	public void setPwd(String pwd) {
		this.pwd = pwd;
	}


	public String getHeadpicture() {
		return headpicture;
	}


	public void setHeadpicture(String headpicture) {
		this.headpicture = headpicture;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getRealname() {
		return realname;
	}


	public void setRealname(String realname) {
		this.realname = realname;
	}


	public int getSex() {
		return sex;
	}


	public void setSex(int sex) {
		this.sex = sex;
	}


	public int getAge() {
		return age;
	}


	public void setAge(int age) {
		this.age = age;
	}


	public String getLocus() {
		return locus;
	}


	public void setLocus(String locus) {
		this.locus = locus;
	}




	public Map<String,String> getParams(){
		Map<String,String> map = new ConcurrentHashMap<String,String>();
		map.put("uphone", uphone);
		map.put("pwd", pwd);
		map.put("headpicture", headpicture);
		map.put("name", name);
		map.put("realname", realname);
		map.put("sex", String.valueOf(sex));
		map.put("age", String.valueOf(age));
		map.put("locus", locus);
		return map;
	}
}
