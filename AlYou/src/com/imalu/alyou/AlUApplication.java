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
package com.imalu.alyou;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.baidu.mapapi.BMapManager;
import com.easemob.EMCallBack;
import com.imalu.alyou.db.gen.DaoMaster;
import com.imalu.alyou.db.gen.DaoMaster.OpenHelper;
import com.imalu.alyou.db.gen.DaoSession;
import com.imalu.alyou.domain.ConversationGroup;
import com.imalu.alyou.domain.ConversationGroups;
import com.imalu.alyou.domain.Friend;
import com.imalu.alyou.domain.Friends;
import com.imalu.alyou.domain.HXUser;
import com.imalu.alyou.domain.User;
import com.imalu.alyou.domain.UserLM;
import com.imalu.alyou.net.response.FriendInfo;
import com.imalu.alyou.net.response.FriendListResponse;
import com.imalu.alyou.net.response.GroupListResponse;
import com.imalu.alyou.net.response.GroupMemberResponse;
import com.imalu.alyou.net.response.MemberListResponse;
import com.imalu.alyou.net.response.PersonalInfoResponse;
import com.imalu.alyou.net.response.UserGroupResponse;
import com.imalu.alyou.net.response.UserInfo;

public class AlUApplication extends Application {

	public static Context applicationContext;
	private static AlUApplication instance;
	// login user name
	public final String PREF_USERNAME = "username";
	
	private static DaoMaster daoMaster;
    private static DaoSession daoSession;
    
    private static User myinfo;
    private static UserLM userfans;
    
    
    private static Friends friends;
    
    private static ConversationGroups groups;
	
	/**
	 * 当前用户nickname,为了苹果推送不是userid而是昵称
	 */
	public static String currentUserNick = "";
	public static AlUHXSDKHelper hxSDKHelper = new AlUHXSDKHelper();

	@Override
	public void onCreate() {
		super.onCreate();
        applicationContext = this;
        instance = this;

        /**
         * this function will initialize the HuanXin SDK
         * 
         * @return boolean true if caller can continue to call HuanXin related APIs after calling onInit, otherwise false.
         * 
         * 环信初始化SDK帮助函数
         * 返回true如果正确初始化，否则false，如果返回为false，请在后续的调用中不要调用任何和环信相关的代码
         * 
         * for example:
         * 例子：
         * 
         * public class DemoHXSDKHelper extends HXSDKHelper
         * 
         * HXHelper = new DemoHXSDKHelper();
         * if(HXHelper.onInit(context)){
         *     // do HuanXin related work
         * }
         */
        hxSDKHelper.onInit(applicationContext);
	}
	
	public static User getMyInfo(){
		if (myinfo == null) {
            myinfo = new User();
        }
        return myinfo;
	}
	
	
	public static UserLM getUserfans() {
		if (userfans == null) {
            userfans = new UserLM();
        }
		return userfans;
	}
	/*
	public static void setUserfans(PersonalInfoResponse personal) {
		if (userfans== null) {
			userfans  = new UserLM();
	    }
	}*/
	public static Friends getFriends(){
		if (friends == null) {
            friends = new Friends();
        }
        return friends;
	}
	
	public static ConversationGroups getGroups() {
		if (groups == null) {
			groups = new ConversationGroups();
        }
        return groups;
	}
	
	public static void setFriends(FriendListResponse friendlist) {
		if (friends == null) {
            friends = new Friends();
        }
		for (Iterator<FriendInfo> iter = friendlist.getFriendList().iterator(); iter.hasNext();) {
			FriendInfo friendInfo = (FriendInfo)iter.next();
			Friend friend = new Friend();
			friend.setHxname(friendInfo.getHXName());
			friend.setId(friendInfo.getId());
			friend.setUsername(friendInfo.getUserName());
			friend.setHeadpicUrl(friendInfo.getHeadPicture());
			friend.setKey(friendInfo.getUserKey());
			friends.addFriend(friend);
		}
	}
	
	public static void setGroups(GroupListResponse grouplist) {
		if (groups == null) {
			groups = new ConversationGroups();
        }
		for (Iterator<UserGroupResponse> iter = grouplist.getGroupList().iterator(); iter.hasNext();) {
			UserGroupResponse usergroup = (UserGroupResponse)iter.next();
			ConversationGroup group = new ConversationGroup();
			group.setGroupID(usergroup.getGroupID());
			group.setCreateKey(usergroup.getCreateKey());
			group.setGroupKey(usergroup.getGroupKey());
			group.setGroupName(usergroup.getGroupName());
			groups.addConversationGroup(group);
			Log.e("groupsActivity", group.toString());
		}
	}
	
	public static void setGroupMemberList(MemberListResponse memberlist) {
		if (groups == null) {
			groups = new ConversationGroups();
        }
		
		for (Iterator<GroupMemberResponse> iter = memberlist.getMemberList().iterator(); iter.hasNext();) {
			GroupMemberResponse groupMemberRes = (GroupMemberResponse)iter.next();
			ConversationGroup group = groups.getGroupByKey(groupMemberRes.getGroupKey());
			group.addMember(groupMemberRes);
		}
	}
	
	/** 
     * 取得DaoMaster 
     *  
     * @param context 
     * @return 
     */
    public static DaoMaster getDaoMaster(Context context) {
        if (daoMaster == null) {
            OpenHelper helper = new DaoMaster.DevOpenHelper(context,Constant.DB_NAME, null);
            daoMaster = new DaoMaster(helper.getWritableDatabase());
        }
        return daoMaster;
    }
      
    /** 
     * 取得DaoSession 
     *  
     * @param context 
     * @return 
     */  
    public static DaoSession getDaoSession(Context context) {
        if (daoSession == null) {
            if (daoMaster == null) {
                daoMaster = getDaoMaster(context);
            }
            daoSession = daoMaster.newSession();
        }
        return daoSession;  
    }

	public static AlUApplication getInstance() {
		return instance;
	}
 
	/**
	 * 获取内存中好友user list
	 *
	 * @return
	 */
	public Map<String, HXUser> getContactList() {
	    return hxSDKHelper.getContactList();
	}

	/**
	 * 设置好友user list到内存中
	 *
	 * @param contactList
	 */
	public void setContactList(Map<String, HXUser> contactList) {
	    hxSDKHelper.setContactList(contactList);
	}

	/**
	 * 获取当前登陆用户名
	 *
	 * @return
	 */
	public String getUserName() {
	    return hxSDKHelper.getHXId();
	}

	/**
	 * 获取密码
	 *
	 * @return
	 */
	public String getPassword() {
		return hxSDKHelper.getPassword();
	}

	/**
	 * 设置用户名
	 *
	 * @param user
	 */
	public void setUserName(String username) {
	    hxSDKHelper.setHXId(username);
	}

	/**
	 * 设置密码 下面的实例代码 只是demo，实际的应用中需要加password 加密后存入 preference 环信sdk
	 * 内部的自动登录需要的密码，已经加密存储了
	 *
	 * @param pwd
	 */
	public void setPassword(String pwd) {
	    hxSDKHelper.setPassword(pwd);
	}
	
	public boolean isLogined() {

		if((getMyInfo().getUsername() == null 
				|| getMyInfo().getUsername().isEmpty())
				&& ( getMyInfo().getPassword() == null
				|| getMyInfo().getPassword().isEmpty()) ){
            return false;
        }
        
        return true;
	}

	/**
	 * 退出登录,清空数据
	 */
	public void logout(final EMCallBack emCallBack) {
		// 先调用sdk logout，在清理app中自己的数据
		getMyInfo().setUsername("");
		getMyInfo().setPassword("");
	    hxSDKHelper.logout(emCallBack);
	}
	
	public static void logout() {
		// 先调用sdk logout，在清理app中自己的数据
		getMyInfo().setUsername("");
		getMyInfo().setPassword("");
	}
}
