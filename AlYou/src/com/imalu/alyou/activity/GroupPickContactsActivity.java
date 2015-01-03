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
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SectionIndexer;
import android.widget.TextView;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ListView;

import com.easemob.chat.EMGroup;
import com.easemob.chat.EMGroupManager;
import com.imalu.alyou.R;
import com.imalu.alyou.Constant;
import com.imalu.alyou.AlUApplication;
import com.imalu.alyou.adapter.ContactAdapter;
import com.imalu.alyou.domain.Friend;
import com.imalu.alyou.domain.GroupMember;
import com.imalu.alyou.domain.HXUser;
import com.imalu.alyou.domain.User;
import com.imalu.alyou.widget.Sidebar;

public class GroupPickContactsActivity extends BaseActivity {
	private ListView listView;
	/** 是否为一个新建的群组 */
	protected boolean isCreatingNewGroup;
	/** 是否为单选 */
	private boolean isSignleChecked;
	private PickContactAdapter contactAdapter;
	/** group中一开始就有的成员 */
	private List<String> exitingMembers;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_group_pick_contacts);

		// String groupName = getIntent().getStringExtra("groupName");
		String groupId = getIntent().getStringExtra("groupId");
		if (groupId == null) {// 创建群组
			isCreatingNewGroup = true;
		} else {
			// 获取此群组的成员列表
//			EMGroup group = EMGroupManager.getInstance().getGroup(groupId);
//			exitingMembers = group.getMembers();
			exitingMembers = AlUApplication.getGroups().getGroupByKey(groupId).getMemberNameList();
		}
		if(exitingMembers == null)
			exitingMembers = new ArrayList<String>();
		
		final List<Friend> friendlist = AlUApplication.getFriends().getFriendList();
		// 获取好友列表
//		final List<HXUser> alluserList = new ArrayList<HXUser>();
//		for (HXUser user : AlUApplication.getInstance().getContactList().values()) {
//			if (!user.getUsername().equals(Constant.NEW_FRIENDS_USERNAME) & !user.getUsername().equals(Constant.GROUP_USERNAME))
//				alluserList.add(user);
//		}
		// 对list进行排序
//		Collections.sort(alluserList, new Comparator<HXUser>() {
//			@Override
//			public int compare(HXUser lhs, HXUser rhs) {
//				return (lhs.getUsername().compareTo(rhs.getUsername()));
//
//			}
//		});

		Collections.sort(friendlist, new Comparator<Friend>() {
		@Override
		public int compare(Friend lhs, Friend rhs) {
			return (lhs.getUsername().compareTo(rhs.getUsername()));

		}
		});
		
		listView = (ListView) findViewById(R.id.list);
//		contactAdapter = new PickContactAdapter(this, R.layout.row_contact_with_checkbox, alluserList);
		contactAdapter = new PickContactAdapter(this, R.layout.row_contact_with_checkbox, friendlist);
		listView.setAdapter(contactAdapter);
		((Sidebar) findViewById(R.id.sidebar)).setListView(listView);
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				CheckBox checkBox = (CheckBox) view.findViewById(R.id.checkbox);
				checkBox.toggle();

			}
		});
	}

	/**
	 * 确认选择的members
	 * 
	 * @param v
	 */
	public void save(View v) {
		setResult(RESULT_OK, new Intent().putExtra("newmembers", getToBeAddMembers().toArray(new String[0])));
		finish();
	}

	/**
	 * 获取要被添加的成员
	 * 
	 * @return
	 */
	private List<String> getToBeAddMembers() {
		List<String> members = new ArrayList<String>();
		int length = contactAdapter.isCheckedArray.length;
		for (int i = 0; i < length; i++) {
			String username = contactAdapter.getItem(i + 1).getUsername();
			if (contactAdapter.isCheckedArray[i] && !exitingMembers.contains(username)) {
				members.add(username);
			}
		}

		return members;
	}

	/**
	 * adapter
	 */
	private class PickContactAdapter extends ArrayAdapter<Friend>  implements SectionIndexer{

		private boolean[] isCheckedArray;
		private LayoutInflater layoutInflater;
		private EditText query;
		private ImageButton clearSearch;
		private SparseIntArray positionOfSection;
		private SparseIntArray sectionOfPosition;
		private Sidebar sidebar;
		private int res;
		
		@Override
		public Object[] getSections() {
			positionOfSection = new SparseIntArray();
			sectionOfPosition = new SparseIntArray();
			int count = getCount();
			List<String> list = new ArrayList<String>();
			list.add(getContext().getString(R.string.search_header));
			positionOfSection.put(0, 0);
			sectionOfPosition.put(0, 0);
			for (int i = 1; i < count; i++) {

//				String letter = getItem(i).getHeader();
//				System.err.println("contactadapter getsection getHeader:" + letter + " name:" + getItem(i).getUsername());
				int section = list.size() - 1;
//				if (list.get(section) != null && !list.get(section).equals(letter)) {
				if (list.get(section) != null) {
//					list.add(letter);
					section++;
					positionOfSection.put(section, i);
				}
				sectionOfPosition.put(i, section);
			}
			return list.toArray(new String[list.size()]);
		}

		public PickContactAdapter(Context context, int resource, List<Friend> friends) {
			super(context, resource, friends);
			this.res = resource;
			this.sidebar=sidebar;
			layoutInflater = LayoutInflater.from(context);
			isCheckedArray = new boolean[friends.size()];
		}
		
		public int getPositionForSection(int section) {
			return positionOfSection.get(section);
		}

		public int getSectionForPosition(int position) {
			return sectionOfPosition.get(position);
		}

		@Override
		public View getView(final int position, View convertView, ViewGroup parent) {
//			View view = super.getView(position, convertView, parent);
			if(convertView == null){
				convertView = layoutInflater.inflate(res, null);
			}
			if (position > 0) {
				
				ImageView avatar = (ImageView) convertView.findViewById(R.id.avatar);
				TextView unreadMsgView = (TextView) convertView.findViewById(R.id.unread_msg_number);
				TextView nameTextview = (TextView) convertView.findViewById(R.id.name);
				TextView tvHeader = (TextView) convertView.findViewById(R.id.header);
				
				final String username = getItem(position).getUsername();
				nameTextview.setText(username);
				avatar.setImageResource(R.drawable.default_avatar);
				// 选择框checkbox
				final CheckBox checkBox = (CheckBox) convertView.findViewById(R.id.checkbox);
				if(exitingMembers != null && exitingMembers.contains(username)){
					checkBox.setButtonDrawable(R.drawable.checkbox_bg_gray_selector);
				}else{
					checkBox.setButtonDrawable(R.drawable.checkbox_bg_selector);
				}
				if (checkBox != null) {
					// checkBox.setOnCheckedChangeListener(null);

					checkBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
						@Override
						public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
							// 群组中原来的成员一直设为选中状态
							if (exitingMembers.contains(username)) {
									isChecked = true;
									checkBox.setChecked(true);
							}
							isCheckedArray[position - 1] = isChecked;
							//如果是单选模式
							if (isSignleChecked && isChecked) {
								for (int i = 0; i < isCheckedArray.length; i++) {
									if (i != position - 1) {
										isCheckedArray[i] = false;
									}
								}
								contactAdapter.notifyDataSetChanged();
							}
							
						}
					});
					// 群组中原来的成员一直设为选中状态
					if (exitingMembers.contains(username)) {
							checkBox.setChecked(true);
							isCheckedArray[position - 1] = true;
					} else {
						checkBox.setChecked(isCheckedArray[position - 1]);
					}
				}
			}
			return convertView;
		}
	}

	public void back(View view){
		finish();
	}
	
}
