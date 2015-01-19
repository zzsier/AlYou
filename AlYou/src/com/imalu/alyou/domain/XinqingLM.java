
package com.imalu.alyou.domain;

import java.util.ArrayList;

public class XinqingLM {
private  String  HeadPicture  ;
private  String  UserName  ;
private  String  Content  ;
private  int  Dianzan  ;
private  int  ShifouDianzan  ;
private  String  PhotoUrl1  ;
private  String  PhotoUrl2  ;
private  String  PhotoUrl3  ;
private  String CreatedTime;
private String xingqingkey;
private  ArrayList<PingLunLM>  pinglunLMs  ;


public ArrayList<PingLunLM> getPinglunLMs() {
	return pinglunLMs;
}
public void setPinglunLMs(ArrayList<PingLunLM> pinglunLMs) {
	this.pinglunLMs = pinglunLMs;
}
public String getXingqingkey() {
	return xingqingkey;
}
public void setXingqingkey(String xingqingkey) {
	this.xingqingkey = xingqingkey;
}




public String getHeadPicture() {
	return HeadPicture;
}
public void setHeadPicture(String headPicture) {
	HeadPicture = headPicture;
}
public String getUserName() {
	return UserName;
}
public void setUserName(String userName) {
	UserName = userName;
}
public String getContent() {
	return Content;
}
public void setContent(String content) {
	Content = content;
}
public int getDianzan() {
	return Dianzan;
}
public void setDianzan(int dianzan) {
	Dianzan = dianzan;
}
public int getShifouDianzan() {
	return ShifouDianzan;
}
public void setShifouDianzan(int shifouDianzan) {
	ShifouDianzan = shifouDianzan;
}
public String getPhotoUrl1() {
	return PhotoUrl1;
}
public void setPhotoUrl1(String photoUrl1) {
	PhotoUrl1 = photoUrl1;
}
public String getPhotoUrl2() {
	return PhotoUrl2;
}
public void setPhotoUrl2(String photoUrl2) {
	PhotoUrl2 = photoUrl2;
}
public String getPhotoUrl3() {
	return PhotoUrl3;
}
public void setPhotoUrl3(String photoUrl3) {
	PhotoUrl3 = photoUrl3;
}
public String getCreatedTime() {
	return CreatedTime;
}
public void setCreatedTime(String createdTime) {
	CreatedTime = createdTime;
}

@Override
public String toString() {
	return "XinqingLM [HeadPicture=" + HeadPicture + ", UserName=" + UserName
			+ ", Content=" + Content + ", Dianzan=" + Dianzan
			+ ", ShifouDianzan=" + ShifouDianzan + ", PhotoUrl1=" + PhotoUrl1
			+ ", PhotoUrl2=" + PhotoUrl2 + ", PhotoUrl3=" + PhotoUrl3
			+ ", CreatedTime=" + CreatedTime + ", pinglunLMs=" + pinglunLMs
			+ "]";
}
 
}
