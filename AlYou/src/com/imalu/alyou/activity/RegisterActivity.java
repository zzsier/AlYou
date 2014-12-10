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

import org.apache.http.Header;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.easemob.EMError;
import com.easemob.chat.EMChatManager;
import com.imalu.alyou.R;
import com.easemob.exceptions.EaseMobException;
import com.imalu.alyou.AlUApplication;
import com.imalu.alyou.net.JsonHttpResponseHandler;
import com.imalu.alyou.net.NetManager;
import com.imalu.alyou.net.NetObject;
import com.imalu.alyou.net.request.LoginRequest;
import com.imalu.alyou.net.request.RegisterRequest;
import com.imalu.alyou.net.response.RegisterResponse;

/**
 * 注册页
 * 
 */
public class RegisterActivity extends BaseActivity {
	private EditText userNameEditText;
	private EditText passwordEditText;
	private EditText confirmPwdEditText;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		userNameEditText = (EditText) findViewById(R.id.username);
		passwordEditText = (EditText) findViewById(R.id.password);
		confirmPwdEditText = (EditText) findViewById(R.id.confirm_password);
	}

	/**
	 * 注册
	 * 
	 * @param view
	 */
	public void register(View view) {
		final String username = userNameEditText.getText().toString().trim();
		final String pwd = passwordEditText.getText().toString().trim();
		String confirm_pwd = confirmPwdEditText.getText().toString().trim();
		if (TextUtils.isEmpty(username)) {
			Toast.makeText(this, "用户名不能为空！", Toast.LENGTH_SHORT).show();
			userNameEditText.requestFocus();
			return;
		} else if (TextUtils.isEmpty(pwd)) {
			Toast.makeText(this, "密码不能为空！", Toast.LENGTH_SHORT).show();
			passwordEditText.requestFocus();
			return;
		} else if (TextUtils.isEmpty(confirm_pwd)) {
			Toast.makeText(this, "确认密码不能为空！", Toast.LENGTH_SHORT).show();
			confirmPwdEditText.requestFocus();
			return;
		} else if (!pwd.equals(confirm_pwd)) {
			Toast.makeText(this, "两次输入的密码不一致，请重新输入！", Toast.LENGTH_SHORT).show();
			return;
		}

		RegisterRequest registerReq = new RegisterRequest();
		registerReq.setUsername(username);
		registerReq.setPassword(pwd);
	
		NetManager.post(NetManager.REGISTER_REQUEST_OPERATION, registerReq, new JsonHttpResponseHandler() {
			@Override
			public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
				//pd.dismiss();
				Log.e("login_check", " response:"+response.toString());
				//Toast.makeText(getApplicationContext(), "登录失败: " + , 0).show();
				
				//RegisterResponse regResp = (RegisterResponse)response;
				
				// 登陆成功，保存用户名密码
				//AlUApplication.getInstance().setUserName(username);
				//AlUApplication.getInstance().setPassword(password);
				
				/*runOnUiThread(new Runnable() {
					public void run() {
						pd.setMessage("正在获取好友和群聊列表...");
					}
				});*/
				try {
					
					RegisterResponse regResp = new RegisterResponse();
					regResp.setJsonObject(response);
					
					if( regResp.getCode() ) {
						Toast.makeText(getApplicationContext(), "注册成功", Toast.LENGTH_SHORT).show();
					} else {
						Toast.makeText(getApplicationContext(), "注册失败: "+regResp.getInfo(), Toast.LENGTH_SHORT).show();
					}
					// ** 第一次登录或者之前logout后，加载所有本地群和回话
					// ** manually load all local groups and
					// conversations in case we are auto login
					//EMGroupManager.getInstance().loadAllGroups();
					//EMChatManager.getInstance().loadAllConversations();
					
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
					Toast.makeText(getApplicationContext(), "注册失败: "+e.getMessage(), Toast.LENGTH_SHORT).show();
					//e.printStackTrace();
				}
				
				Log.e("login_check", "1111111111111111111");
				//更新当前用户的nickname 此方法的作用是在ios离线推送时能够显示用户nick
				//boolean updatenick = EMChatManager.getInstance().updateCurrentUserNick(AlUApplication.currentUserNick);
				//if (!updatenick) {
				//	EMLog.e("LoginActivity", "update current user nick fail");
				//}
				
				//if (!LoginActivity.this.isFinishing())
				//pd.dismiss();
				// 进入主页面
				//startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
				finish();
			}
			
			@Override
			public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject jsonObj) {
				Log.e("register", throwable.getMessage());
				finish();
			}
			
			@Override
			public void onFailure(int statusCode, Header[] headers, final String responseString, Throwable throwable) {
				runOnUiThread(new Runnable() {
					public void run() {
						//pd.dismiss();
						//Toast.makeText(getApplicationContext(), "注册失败: " + responseString, 0).show();
						Log.e("register", responseString);
						finish();
					}
				});
			}
		});
		
		/*
		if (!TextUtils.isEmpty(username) && !TextUtils.isEmpty(pwd)) {
			final ProgressDialog pd = new ProgressDialog(this);
			pd.setMessage("正在注册...");
			pd.show();
			new Thread(new Runnable() {
				public void run() {
					try {
						// 调用sdk注册方法
						EMChatManager.getInstance().createAccountOnServer(username, pwd);
						runOnUiThread(new Runnable() {
							public void run() {
								if (!RegisterActivity.this.isFinishing())
									pd.dismiss();
								// 保存用户名
								AlUApplication.getInstance().setUserName(username);
								Toast.makeText(getApplicationContext(), "注册成功", 0).show();
								finish();
							}
						});
					} catch (final EaseMobException e) {
						runOnUiThread(new Runnable() {
							public void run() {
								if (!RegisterActivity.this.isFinishing())
									pd.dismiss();
								int errorCode=e.getErrorCode();
								if(errorCode==EMError.NONETWORK_ERROR){
									Toast.makeText(getApplicationContext(), "网络异常，请检查网络！", Toast.LENGTH_SHORT).show();
								}else if(errorCode==EMError.USER_ALREADY_EXISTS){
									Toast.makeText(getApplicationContext(), "用户已存在！", Toast.LENGTH_SHORT).show();
								}else if(errorCode==EMError.UNAUTHORIZED){
									Toast.makeText(getApplicationContext(), "注册失败，无权限！", Toast.LENGTH_SHORT).show();
								}else{
									Toast.makeText(getApplicationContext(), "注册失败: " + e.getMessage(), Toast.LENGTH_SHORT).show();
								}
							}
						});
					}
				}
			}).start();

		}
		*/
	}

	public void back(View view) {
		finish();
	}

}
