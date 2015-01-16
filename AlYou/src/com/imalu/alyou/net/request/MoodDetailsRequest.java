package com.imalu.alyou.net.request;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.imalu.alyou.net.NetObject;

public class MoodDetailsRequest extends NetObject{
	private String own;
	private String other;
	private int pagesize;
	private int pageindex;
	public String getOwn() {
		return own;
	}
	public void setOwn(String own) {
		this.own = own;
	}
	public String getOther() {
		return other;
	}
	public void setOther(String other) {
		this.other = other;
	}
	public int getPagesize() {
		return pagesize;
	}
	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
	}
	public int getPageindex() {
		return pageindex;
	}
	public void setPageindex(int pageindex) {
		this.pageindex = pageindex;
	}
	public Map<String, String> getParams(){
		Map<String,String> map = new ConcurrentHashMap<String,String>();
		map.put("own", own);
		map.put("other", other);
		map.put("pageSize", String.valueOf(pagesize));
		map.put("pageIndex", String.valueOf(pageindex));
		return map;
	}
}
