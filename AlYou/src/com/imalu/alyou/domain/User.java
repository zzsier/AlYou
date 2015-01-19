/**
 * Copyright (C) 2013-2014 EaseMob Technologies. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *     http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.imalu.alyou.domain;

//import com.easemob.chat.EMContact;  extends EMContact 

public class User {
	
	private String username;
	private String password = "cc55aed9-0d13-4097-a8cf-f8b1b882a91c";
	private String hxname = "cc55aed90d134097a8cff8b1b882a91c";
	private int id = 66;
	private String nickName;
	private String phoneNum;
	private String key = "cc55aed9-0d13-4097-a8cf-f8b1b882a91c";
	private int jifen;
	private String locus;
	private String sex;
	private String societykey;
	private String realname;
	private int age;
	private String jiarugonghuikey;
	private String huanxinid;
	
	//添加公会名
	private String societyname;
	
	public String getSocietyname() {
		return societyname;
	}
	public void setSocietyname(String societyname) {
		this.societyname = societyname;
	}
	
	
	
	
	public String getHuanxinid() {
		return huanxinid;
	}
	public void setHuanxinid(String huanxinid) {
		this.huanxinid = huanxinid;
	}
	public String getJiarugonghuikey() {
		return jiarugonghuikey;
	}
	public void setJiarugonghuikey(String jiarugonghuikey) {
		this.jiarugonghuikey = jiarugonghuikey;
	}
	public int getJifen() {
		return jifen;
	}
	public void setJifen(int jifen) {
		this.jifen = jifen;
	}
	public String getLocus() {
		return locus;
	}
	public void setLocus(String locus) {
		this.locus = locus;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getSocietykey() {
		return societykey;
	}
	public void setSocietykey(String societykey) {
		this.societykey = societykey;
	}
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getHxname() {
		return hxname;
	}
	public void setHxname(String hxname) {
		this.hxname = hxname;
	}
	
	
	
	
	
	private int unreadMsgCount;
	private String header;

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public int getUnreadMsgCount() {
		return unreadMsgCount;
	}

	public void setUnreadMsgCount(int unreadMsgCount) {
		this.unreadMsgCount = unreadMsgCount;
	}

	@Override
	public int hashCode() {
		return 17 * getUsername().hashCode();
	}

	@Override
	public boolean equals(Object o) {
		if (o == null || !(o instanceof User)) {
			return false;
		}
		return getUsername().equals(((User) o).getUsername());
	}

//	@Override
//	public String toString() {
//		return nick == null ? username : nick;
//	}
//	
}
