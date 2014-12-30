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

import java.util.List;

import org.apache.http.Header;
import org.json.JSONArray;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.imalu.alyou.AlUApplication;
import com.imalu.alyou.Constant;
import com.imalu.alyou.R;
import com.imalu.alyou.adapter.FriendAdapter;
import com.imalu.alyou.domain.HXUser;
import com.imalu.alyou.net.JsonHttpResponseHandler;
import com.imalu.alyou.net.NetManager;
import com.imalu.alyou.net.request.SearchFriendRequest;
import com.imalu.alyou.net.response.FriendInfo;
import com.imalu.alyou.net.response.FriendListResponse;
import com.imalu.alyou.widget.Sidebar;
//import com.easemob.chat.EMContactManager;

/**
 * 通讯录-----好友列表
 * 
 */
public class FriendlistFragment extends Fragment {
	private FriendAdapter adapter;
	private List<HXUser> contactList;
	private ListView listView;
	FriendListResponse friendlist;
	private boolean hidden;
	private Sidebar sidebar;
	private InputMethodManager inputMethodManager;
	private List<String> blackList;
	private Button bt;
	private TextView add_friend;
	
	

	protected static final String TAG = "MainActivity";
	// 未读消息textview
	private TextView unreadLabel;
	// 未读通讯录textview
	private TextView unreadAddressLable;

	private Button[] mTabs;
	private FriendlistFragment contactListFragment;
	//private ChatHistoryFragment chatHistoryFragment;
	private ChatAllHistoryFragment chatHistoryFragment;
	private SettingsFragment settingFragment;
	private MainPageFragment mainpageFragment;
	private RelativeLayout relativeLayout;
	private Fragment[] fragments;
	private int index;
	private RelativeLayout[] tab_containers;
	// 当前fragment的index
	private int currentTabIndex;
	//private NewMessageBroadcastReceiver msgReceiver;
	// 账号在别处登录
	public boolean isConflict = false;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.contact_friend_list, container, false);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		//防止被T后，没点确定按钮然后按了home键，长期在后台又进app导致的crash
		if(savedInstanceState != null && savedInstanceState.getBoolean("isConflict", false))
		    return;
		inputMethodManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
		listView = (ListView) getView().findViewById(R.id.list);
		sidebar = (Sidebar) getView().findViewById(R.id.sidebar);
		relativeLayout=(RelativeLayout) getView().findViewById(R.id.add_friend_layout);
		sidebar.setListView(listView);
		sidebar.setVisibility(ViewGroup.GONE);
		relativeLayout.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			startActivity(new Intent(getActivity(),FindFriendActivity.class));	
			}
		});
//		//黑名单列表
//		//blackList = EMContactManager.getInstance().getBlackListUsernames();
//		contactList = new ArrayList<HXUser>();
//		// 获取设置contactlist
		getContactList();
//		// 设置adapter
//		adapter = new ContactAdapter(getActivity(), R.layout.row_contact, contactList, sidebar);
//		listView.setAdapter(adapter);
//		listView.setOnItemClickListener(new OnItemClickListener() {
//
//			@Override
//			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//				String username = adapter.getItem(position).getUsername();
//				if (Constant.NEW_FRIENDS_USERNAME.equals(username)) {
//					// 进入申请与通知页面
//					HXUser user = AlUApplication.getInstance().getContactList().get(Constant.NEW_FRIENDS_USERNAME);
//					user.setUnreadMsgCount(0);
//					startActivity(new Intent(getActivity(), NewFriendsMsgActivity.class));
//				} else if (Constant.GROUP_USERNAME.equals(username)) {
//					// 进入群聊列表页面
//					startActivity(new Intent(getActivity(), GroupsActivity.class));
//				} else {
//					// demo中直接进入聊天页面，实际一般是进入用户详情页
//					startActivity(new Intent(getActivity(), ChatActivity.class).putExtra("userId", adapter.getItem(position).getUsername()));
//				}
//			}
//		});
//		listView.setOnTouchListener(new OnTouchListener() {
//
//			@Override
//			public boolean onTouch(View v, MotionEvent event) {
//				// 隐藏软键盘
//				if (getActivity().getWindow().getAttributes().softInputMode != WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN) {
//					if (getActivity().getCurrentFocus() != null)
//						inputMethodManager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(),
//								InputMethodManager.HIDE_NOT_ALWAYS);
//				}
//				return false;
//			}
//		});
//
//		ImageView addContactView = (ImageView) getView().findViewById(R.id.iv_new_contact);
//		// 进入添加好友页
//		addContactView.setOnClickListener(new OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				startActivity(new Intent(getActivity(), AddContactActivity.class));
//			}
//		});
//		registerForContextMenu(listView);
//		setView();
//		setLisetener();
		
	}
	



	private void showListView() {
		adapter = new FriendAdapter(getActivity(), R.layout.row_contact, R.layout.row_contact2, friendlist.getFriendList(), sidebar);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(new OnItemClickListener() {
//
//			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				String username = adapter.getItem(position).getUserName();
				if (Constant.NEW_FRIENDS_USERNAME.equals(username)) {
					// 进入申请与通知页面
					HXUser user = AlUApplication.getInstance().getContactList().get(Constant.NEW_FRIENDS_USERNAME);
					user.setUnreadMsgCount(0);
					startActivity(new Intent(getActivity(), NewFriendsMsgActivity.class));
				} else if (Constant.GROUP_USERNAME.equals(username)) {
					// 进入群聊列表页面
					startActivity(new Intent(getActivity(), GroupsActivity.class));
				} else {
					// demo中直接进入聊天页面，实际一般是进入用户详情页
					startActivity(new Intent(getActivity(), ChatActivity.class).putExtra("userId", adapter.getItem(position).getHXName()));
				}
			}
		});
		
		listView.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// 隐藏软键盘
				if (getActivity().getWindow().getAttributes().softInputMode != WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN) {
					if (getActivity().getCurrentFocus() != null)
						inputMethodManager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(),
								InputMethodManager.HIDE_NOT_ALWAYS);
				}
				return false;
			}
		});
//
	TextView addContactView = (TextView) getView().findViewById(R.id.add_friend);
//		// 进入添加好友页
		addContactView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(getActivity(), AddContactActivity.class));
			}
		});
		registerForContextMenu(listView);
	}

	

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
		// 长按前两个不弹menu
		if (((AdapterContextMenuInfo) menuInfo).position > 2) {
			getActivity().getMenuInflater().inflate(R.menu.context_contact_list, menu);
		}
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		if (item.getItemId() == R.id.delete_contact) {
			FriendInfo tobeDeleteUser = adapter.getItem(((AdapterContextMenuInfo) item.getMenuInfo()).position);
			// 删除此联系人
//			deleteContact(tobeDeleteUser);
			// 删除相关的邀请消息
//			InviteMessgeDao dao = new InviteMessgeDao(getActivity());
//			dao.deleteMessage(tobeDeleteUser.getUsername());
			return true;
		}else if(item.getItemId() == R.id.add_to_blacklist){
			FriendInfo user = adapter.getItem(((AdapterContextMenuInfo) item.getMenuInfo()).position);
//			moveToBlacklist(user.getUsername());
			return true;
		}
		return super.onContextItemSelected(item);
	}

	@Override
	public void onHiddenChanged(boolean hidden) {
		super.onHiddenChanged(hidden);
		this.hidden = hidden;
		if (!hidden) {
			//refresh();
		}
	}

	@Override
	public void onResume() {
		super.onResume();
		if (!hidden) {
			//refresh();
		}
	}

	/**
	 * 删除联系人
	 * 
	 * @param toDeleteUser
	 */
	public void deleteContact(final HXUser tobeDeleteUser) {
		final ProgressDialog pd = new ProgressDialog(getActivity());
		pd.setMessage("正在删除...");
		pd.setCanceledOnTouchOutside(false);
		pd.show();
//		new Thread(new Runnable() {
//			public void run() {
//				try {
//					//EMContactManager.getInstance().deleteContact(tobeDeleteUser.getUsername());
//					// 删除db和内存中此用户的数据
//					UserDao dao = new UserDao(getActivity());
//					dao.deleteContact(tobeDeleteUser.getUsername());
//					AlUApplication.getInstance().getContactList().remove(tobeDeleteUser.getUsername());
//					getActivity().runOnUiThread(new Runnable() {
//						public void run() {
//							pd.dismiss();
////							adapter.remove(tobeDeleteUser);
//							adapter.notifyDataSetChanged();
//
//						}
//					});
//				} catch (final Exception e) {
//					getActivity().runOnUiThread(new Runnable() {
//						public void run() {
//							pd.dismiss();
//							Toast.makeText(getActivity(), "删除失败: " + e.getMessage(), 1).show();
//						}
//					});
//
//				}
//
//			}
//		}).start();

	}

	/**
	 * 把user移入到黑名单
	 */
	private void moveToBlacklist(final String username){
		final ProgressDialog pd = new ProgressDialog(getActivity());
		pd.setMessage("正在移入黑名单...");
		pd.setCanceledOnTouchOutside(false);
		pd.show();
		new Thread(new Runnable() {
			public void run() {
				//try {
					//加入到黑名单
					//EMContactManager.getInstance().addUserToBlackList(username,false);
					getActivity().runOnUiThread(new Runnable() {
						public void run() {
							pd.dismiss();
							Toast.makeText(getActivity(), "移入黑名单成功", 0).show();
							//refresh();
						}
					});
				//} 
//				catch (EaseMobException e) {
//					e.printStackTrace();
//					getActivity().runOnUiThread(new Runnable() {
//						public void run() {
//							pd.dismiss();
//							Toast.makeText(getActivity(), "移入黑名单失败", 0).show();
//						}
//					});
//				}
			}
		}).start();
		
	}
	
	// 刷新ui
	public void refresh() {
		try {
			// 可能会在子线程中调到这方法
			getActivity().runOnUiThread(new Runnable() {
				public void run() {
					getContactList();
					adapter.notifyDataSetChanged();

				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取联系人列表，并过滤掉黑名单和排序
	 */
	private void getContactList() {
		
		SearchFriendRequest searchFriendReq= new SearchFriendRequest();
		searchFriendReq.setUserKey(AlUApplication.getMyInfo().getKey());
		
		friendlist = new FriendListResponse();
		
		NetManager.execute(NetManager.MY_FRIEND_OPERATION, searchFriendReq, new JsonHttpResponseHandler() {
			@Override
			public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
				Log.e("FriendListFragament", response.toString());
				super.onSuccess(statusCode, headers, response);
				friendlist.setJsonObject(response);
				AlUApplication.setFriends(friendlist);
				showListView();
			}
		});
		
//		contactList.clear();
		//获取本地好友列表
//		Map<String, HXUser> users = AlUApplication.getInstance().getContactList();
//		Iterator<Entry<String, HXUser>> iterator = users.entrySet().iterator();
//		while (iterator.hasNext()) {
//			Entry<String, HXUser> entry = iterator.next();
//			if (!entry.getKey().equals(Constant.NEW_FRIENDS_USERNAME) && !entry.getKey().equals(Constant.GROUP_USERNAME)
//					&& !blackList.contains(entry.getKey()))
//				contactList.add(entry.getValue());
//		}
		
		// 排序
//		Collections.sort(contactList, new Comparator<HXUser>() {
//
//			@Override
//			public int compare(HXUser lhs, HXUser rhs) {
//				return lhs.getUsername().compareTo(rhs.getUsername());
//			}
//		});

		// 加入"申请与通知"和"群聊"
//		contactList.add(0, users.get(Constant.GROUP_USERNAME));
		// 把"申请与通知"添加到首位
//		contactList.add(0, users.get(Constant.NEW_FRIENDS_USERNAME));
	}
	
	@Override
	public void onSaveInstanceState(Bundle outState) {
	    if(((MainActivity)getActivity()).isConflict)
	        outState.putBoolean("isConflict", true);
	    super.onSaveInstanceState(outState);
	    
	}
}
