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
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnCancelListener;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.easemob.chat.EMChatManager;
import com.imalu.alyou.AlUApplication;
import com.imalu.alyou.R;
import com.imalu.alyou.net.JsonHttpResponseHandler;
import com.imalu.alyou.net.NetManager;
import com.imalu.alyou.net.request.LoginRequest;
import com.imalu.alyou.net.request.ModifyUserRequest;
import com.imalu.alyou.net.response.OperationResponse;
import com.imalu.alyou.net.response.UserInfo;
import com.imalu.alyou.utils.CommonUtils;

public class MySettingActivity extends BaseActivity{
	
	private EditText nickname;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_setting);	
	}
	
	public void back(View v) {
		finish();
	}
	
	public void modifySetting(View v){
		
		final ProgressDialog pd = new ProgressDialog(MySettingActivity.this);
		pd.setCanceledOnTouchOutside(false);
		
		nickname = (EditText) findViewById(R.id.nickname);
		
		final String name = nickname.getText().toString();
		
		ModifyUserRequest userInfo = new ModifyUserRequest();
		userInfo.setName(name);
		userInfo.setRealname(name);
		userInfo.setPhoneNum(AlUApplication.getMyInfo().getPhoneNum());
		userInfo.setPassword(AlUApplication.getMyInfo().getPassword());
		
		NetManager.execute(NetManager.MODIFY_USER_REQUEST_OPERATION, userInfo, new JsonHttpResponseHandler() {
			@Override
			public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
				
				try {
					
					pd.dismiss();
					Log.e("login_check", " response:"+response.toString());
					
					OperationResponse operationRes = new OperationResponse();
					operationRes.setJsonObject(response);			

					AlUApplication.getMyInfo().setUsername(name);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				finish();
			}
			
			@Override
			public void onFailure(int statusCode, Header[] headers, final String responseString, Throwable throwable) {

				runOnUiThread(new Runnable() {
					public void run() {
						//pd.dismiss();
						Toast.makeText(getApplicationContext(), "登录失败: " + responseString, 0).show();
						
					}
				});
			}
		});

	}
}
