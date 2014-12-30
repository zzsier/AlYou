package com.imalu.alyou.domain;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Friends {
	
	private List<Friend> friendlist;
	
	public Friends() {
		if (friendlist == null) {
            friendlist = new ArrayList<Friend>();
        }
	}
	
	public void addFriend(Friend friend){
		if (friendlist == null) {
            friendlist = new ArrayList<Friend>();
        }
		friendlist.add(friend);
	}
	
	public List<Friend> getFriendList(){
		if (friendlist == null) {
            friendlist = new ArrayList<Friend>();
        }
		return friendlist;
	}
	
	public String getUserNameByHXName(String hxname) {	
		for (Iterator<Friend> iter = friendlist.iterator(); iter.hasNext();) {
			Friend friend = (Friend)iter.next();
			if( friend.getHxname().equals(hxname) ) {
				return friend.getUsername();
			}
		}
		
		return "";
	}

}
