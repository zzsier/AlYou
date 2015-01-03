package com.imalu.alyou.domain;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import android.util.Log;

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
	
	public Boolean existUserbyKey(String userkey) {
		Log.e("existUserbyKey", "User Key: "+userkey);
		Log.e("existUserbyKey", "Friend list size: "+friendlist.size());
		if(userkey == null) {
			return false;
		}
		for (Iterator<Friend> iter = friendlist.iterator(); iter.hasNext();) {
			Friend friend = (Friend)iter.next();
			Log.e("existUserbyKey", "Friend Key: "+friend.getKey());
			if( friend.getKey().equals(userkey) ) {
				return true;
			}
		}
		
		return false;
	}

}
