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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.easemob.EMCallBack;
import com.easemob.chat.EMChatManager;
import com.easemob.chat.EMContactManager;
import com.easemob.chat.EMGroupManager;
import com.imalu.alyou.R;
import com.easemob.util.EMLog;
import com.easemob.util.HanziToPinyin;
import com.imalu.alyou.Constant;
import com.imalu.alyou.AlUApplication;
import com.imalu.alyou.AlUHXSDKHelper;
import com.imalu.alyou.db.UserDao;
import com.imalu.alyou.domain.HXUser;
import com.imalu.alyou.domain.User;
import com.imalu.alyou.net.JsonHttpResponseHandler;
import com.imalu.alyou.net.NetManager;
import com.imalu.alyou.net.NetObject;
import com.imalu.alyou.net.request.LoginRequest;
import com.imalu.alyou.net.response.UserInfo;
import com.imalu.alyou.utils.CommonUtils;


/**
 * 登陆页面
 * 
 */
public class LoginActivity extends BaseActivity {
	public static final int REQUEST_CODE_SETNICK = 1;
	private EditText usernameEditText;
	private EditText passwordEditText;

	private boolean progressShow;
	private boolean autoLogin = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		if (AlUHXSDKHelper.getInstance().isLogined()) {
			autoLogin = true;
			startActivity(new Intent(LoginActivity.this, MainActivity.class));
			return;
		}
		
		setContentView(R.layout.activity_login);

		usernameEditText = (EditText) findViewById(R.id.username);
		passwordEditText = (EditText) findViewById(R.id.password);

		// 如果用户名改变，清空密码
		usernameEditText.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				passwordEditText.setText(null);
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {

			}

			@Override
			public void afterTextChanged(Editable s) {

			}
		});

	}

	/**
	 * 登陆
	 * 
	 * @param view
	 */
	public void login(View view) {
		if (!CommonUtils.isNetWorkConnected(this)) {
			Toast.makeText(this, R.string.network_isnot_available, Toast.LENGTH_SHORT).show();
			return;
		}

		final String username = usernameEditText.getText().toString();
		final String password = passwordEditText.getText().toString();
		//AlUApplication.currentUserNick = usernameEditText.getText().toString();
		
		if (!TextUtils.isEmpty(username) && !TextUtils.isEmpty(password)) {
			
			progressShow = true;
			final ProgressDialog pd = new ProgressDialog(LoginActivity.this);
			pd.setCanceledOnTouchOutside(false);
			pd.setOnCancelListener(new OnCancelListener() {

				@Override
				public void onCancel(DialogInterface dialog) {
					progressShow = false;
				}
			});
			pd.setMessage("正在登陆...");
			pd.show();
			
			LoginRequest loginReq = new LoginRequest();
			loginReq.setUsername(username);
			loginReq.setPassword(password);
		
			NetManager.execute(NetManager.LOGIN_REQUEST_OPERATION, loginReq, new JsonHttpResponseHandler() {
				@Override
				public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
					
					try {
						
						pd.dismiss();
						Log.e("login_check", " response:"+response.toString());
						
						UserInfo userInfo = new UserInfo();
						userInfo.setJsonObject(response);			
						
						AlUApplication.getMyInfo().setPhoneNum(username);
						AlUApplication.getMyInfo().setPassword(password);
						AlUApplication.getMyInfo().setHxname(userInfo.getHXName());
						AlUApplication.getMyInfo().setId(userInfo.getID());
						AlUApplication.getMyInfo().setUsername(userInfo.getUserName());
						AlUApplication.getMyInfo().setKey(userInfo.getKey());
						// 登陆成功，保存用户名密码
						AlUApplication.getInstance().setUserName(username);
						AlUApplication.getInstance().setPassword(password);
						
						/*
						runOnUiThread(new Runnable() {
							public void run() {
								pd.setMessage("请等待...");
							}
						});
						*/
						
						EMChatManager.getInstance().login(userInfo.getHXName(), userInfo.getHXPwd(), new EMCallBack() {
							
						    @Override
						    public void onSuccess() {
						    	EMChatManager.getInstance().loadAllConversations();
						    }
							
						    @Override
						    public void onProgress(int progress, String status) {
						    // TODO Auto-generated method stub
						    }
								
						    @Override
						    public void onError(int code, String message) {
						    // TODO Auto-generated method stub
						    }
						});
						
						// ** 第一次登录或者之前logout后，加载所有本地群和回话
						// ** manually load all local groups and
						// conversations in case we are auto login
						//EMGroupManager.getInstance().loadAllGroups();
						
						
						// demo中简单的处理成每次登陆都去获取好友username，开发者自己根据情况而定
						/*List<String> usernames = EMContactManager.getInstance().getContactUserNames();
						EMLog.d("roster", "contacts size: " + usernames.size());
						
						for (String username : usernames) {
							User user = new User();
							user.setUsername(username);
							setUserHearder(username, user);
							userlist.put(username, user);
						}*/
						/*Map<String, User> userlist = new HashMap<String, User>();
						// 添加user"申请与通知"
						User newFriends = new User();
						newFriends.setUsername(Constant.NEW_FRIENDS_USERNAME);
						newFriends.setNick("申请与通知");
						newFriends.setHeader("");
						userlist.put(Constant.NEW_FRIENDS_USERNAME, newFriends);
						// 添加"群聊"
						User groupUser = new User();
						groupUser.setUsername(Constant.GROUP_USERNAME);
						groupUser.setNick("群聊");
						groupUser.setHeader("");
						userlist.put(Constant.GROUP_USERNAME, groupUser);*/
						
						// 存入内存
						//AlUApplication.getInstance().setContactList(userlist);
						// 存入db
						//UserDao dao = new UserDao(LoginActivity.this);
						//List<User> users = new ArrayList<User>(userlist.values());
						//dao.saveContactList(users);
						
						// 获取群聊列表(群聊里只有groupid和groupname等简单信息，不包含members),sdk会把群组存入到内存和db中
						//EMGroupManager.getInstance().getGroupsFromServer();*/
					} catch (Exception e) {
						e.printStackTrace();
					}
					//更新当前用户的nickname 此方法的作用是在ios离线推送时能够显示用户nick
					//boolean updatenick = EMChatManager.getInstance().updateCurrentUserNick(AlUApplication.currentUserNick);
					//if (!updatenick) {
					//	EMLog.e("LoginActivity", "update current user nick fail");
					//}
					
					//if (!LoginActivity.this.isFinishing())
					//pd.dismiss();
					// 进入主页面
					startActivity(new Intent(LoginActivity.this, MainActivity.class));
					finish();
				}
				
				@Override
				public void onFailure(int statusCode, Header[] headers, final String responseString, Throwable throwable) {
					if (!progressShow) {
						return;
					}
					pd.dismiss();
					runOnUiThread(new Runnable() {
						public void run() {
							//pd.dismiss();
							//Toast.makeText(getApplicationContext(), "登录失败: " + responseString, 0).show();
							
						}
					});
				}
			});
		}
	}

	
	/*Intent intent = new Intent(LoginActivity.this, com.imalu.alyou.activity.AlertDialog.class);
	intent.putExtra("editTextShow", true);
	intent.putExtra("titleIsCancel", true);
	intent.putExtra("msg", "请设置当前用户的昵称\n为了ios离线推送不是userid而是nick，详情见注释");
	startActivityForResult(intent, REQUEST_CODE_SETNICK);*/
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == RESULT_OK) {
			if (requestCode == REQUEST_CODE_SETNICK) {


			}

		}
	}

	/**
	 * 注册
	 * 
	 * @param view
	 */
	public void register(View view) {
		startActivityForResult(new Intent(this, RegisterActivity_first.class), 0);
	}

	@Override
	protected void onResume() {
		super.onResume();
//		if (autoLogin) {
//			return;
//		}
//
//		if (AlUApplication.getInstance().getUserName() != null) {
//			usernameEditText.setText(AlUApplication.getInstance().getUserName());
//		}
	}

	/**
	 * 设置hearder属性，方便通讯中对联系人按header分类显示，以及通过右侧ABCD...字母栏快速定位联系人
	 * 
	 * @param username
	 * @param user
	 */
	protected void setUserHearder(String username, HXUser user) {
		String headerName = null;
		if (!TextUtils.isEmpty(user.getNick())) {
			headerName = user.getNick();
		} else {
			headerName = user.getUsername();
		}
		if (username.equals(Constant.NEW_FRIENDS_USERNAME)) {
			user.setHeader("");
		} else if (Character.isDigit(headerName.charAt(0))) {
			user.setHeader("#");
		} else {
			user.setHeader(HanziToPinyin.getInstance().get(headerName.substring(0, 1)).get(0).target.substring(0, 1).toUpperCase());
			char header = user.getHeader().toLowerCase().charAt(0);
			if (header < 'a' || header > 'z') {
				user.setHeader("#");
			}
		}
	}
}
