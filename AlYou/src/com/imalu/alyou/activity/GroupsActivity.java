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
package com.imalu.alyou.activity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.easemob.EMCallBack;
import com.easemob.chat.EMChatManager;
import com.easemob.chat.EMGroup;
import com.easemob.chat.EMGroupManager;
import com.imalu.alyou.AlUApplication;
import com.imalu.alyou.R;
import com.imalu.alyou.adapter.GroupAdapter;
import com.imalu.alyou.db.gen.User;
import com.imalu.alyou.domain.Friend;
import com.imalu.alyou.domain.Friends;
import com.imalu.alyou.domain.ConversationGroup;
import com.imalu.alyou.net.JsonHttpResponseHandler;
import com.imalu.alyou.net.NetManager;
import com.imalu.alyou.net.request.GroupKeyRequest;
import com.imalu.alyou.net.request.LoginRequest;
import com.imalu.alyou.net.request.UserKeyRequest;
import com.imalu.alyou.net.response.FriendInfo;
import com.imalu.alyou.net.response.GroupListResponse;
import com.imalu.alyou.net.response.GroupMemberResponse;
import com.imalu.alyou.net.response.MemberListResponse;
import com.imalu.alyou.net.response.UserGroupResponse;
import com.imalu.alyou.net.response.UserInfo;

public class GroupsActivity extends BaseActivity {
	private ListView groupListView;
	private GroupAdapter groupAdapter;
	private InputMethodManager inputMethodManager;
	public static GroupsActivity instance;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_groups);
		
		instance = this;
		inputMethodManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
		
		UserKeyRequest userKeyReq = new UserKeyRequest();
		userKeyReq.setUserKey(AlUApplication.getMyInfo().getKey());
		
		NetManager.execute(NetManager.GROUPS_REQUEST_OPERATION, userKeyReq, new JsonHttpResponseHandler() {
			@Override
			public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
				try {
					GroupListResponse groups = new GroupListResponse();
					groups.setJsonObject(response);
					AlUApplication.setGroups(groups);
					
					for (Iterator<ConversationGroup> iter = AlUApplication.getGroups().getConversationGroupList().iterator(); iter.hasNext();) {
						ConversationGroup group = (ConversationGroup)iter.next();
						GroupKeyRequest groupKeyReq = new GroupKeyRequest();
						groupKeyReq.setGroupKey(group.getGroupKey());
						
						NetManager.execute(NetManager.GROUP_MEMBER_REQUEST_OPERATION, groupKeyReq, new JsonHttpResponseHandler() {
							@Override
							public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
								try {
									MemberListResponse memberlistRes = new MemberListResponse();
									memberlistRes.setJsonObject(response);
									AlUApplication.setGroupMemberList(memberlistRes);
								} catch (Exception e) {
									e.printStackTrace();
								}
							}
						});
					}
					
					generateList();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void generateList() {
//		grouplist =	EMGroupManager.getInstance().getAllGroups();
		groupListView = (ListView)findViewById(R.id.list);
		groupAdapter = new GroupAdapter(this, R.layout.row_group, AlUApplication.getGroups().getConversationGroupList());
		groupListView.setAdapter(groupAdapter);
		groupListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//				if (position == groupAdapter.getCount() - 1) {
//					//新建群聊
//					startActivityForResult(new Intent(GroupsActivity.this, NewGroupActivity.class), 0);
//				} else {
					
					//进入群聊
					Intent intent = new Intent(GroupsActivity.this, ChatActivity.class);
					// it is group chat
					intent.putExtra("chatType", ChatActivity.CHATTYPE_GROUP);
					intent.putExtra("groupId", groupAdapter.getItem(position).getGroupKey());
					intent.putExtra("groupName", groupAdapter.getItem(position).getGroupName());
					startActivityForResult(intent, 0);
//				}
			}

		});
		groupListView.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if (getWindow().getAttributes().softInputMode != WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN) {
					if (getCurrentFocus() != null)
						inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
								InputMethodManager.HIDE_NOT_ALWAYS);
				}
				return false;
			}
		});
		
		ImageView addContactView = (ImageView) findViewById(R.id.iv_new_group);
		addContactView.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(GroupsActivity.this, NewGroupActivity.class));
			}
		});
	}
	
	/**
	 * 进入公开群聊列表
	 */
	public void onPublicGroups(View view){
		startActivity(new Intent(this, PublicGroupsActivity.class));
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
	}
	
	@Override
	public void onResume() {
		super.onResume();
//		grouplist = EMGroupManager.getInstance().getAllGroups();
//		groupAdapter = new GroupAdapter(this, 1, grouplist);
//		groupListView.setAdapter(groupAdapter);
//		groupAdapter.notifyDataSetChanged();
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		instance = null;
	}
	
	/**
	 * 返回
	 * @param view
	 */
	public void back(View view){
		finish();
	}
}
