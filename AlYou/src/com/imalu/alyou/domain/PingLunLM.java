package com.imalu.alyou.domain;

public class PingLunLM {
private  String HuifurenName; 
private  String Content;

public String getHuifurenName() {
	return HuifurenName;
}
public void setHuifurenName(String huifurenName) {
	 HuifurenName = huifurenName;
}
public String getContent() {
	return Content;
}
public void setContent(String content) {
	Content = content;
} 
@Override
public boolean equals(Object o) {
	// TODO Auto-generated method stub
	return super.equals(o);
}
@Override
public String toString() {
	return "HuifuXinqingLM [HuifurenName=" + HuifurenName + ", Content="
			+ Content + "]";
}
   
}
