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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.Header;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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
 * 注册页1
 * 
 */
public class RegisterActivity_first extends BaseActivity {

	private EditText phoneNumberEditText;
	private Button nextButton;
	private Boolean isPhone;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register_first);

		phoneNumberEditText = (EditText) findViewById(R.id.phone_number);
		nextButton=(Button) findViewById(R.id.next_bt);
	}

	/**
	 * 注册手机验证
	 * 
	 * @param view
	 */
	public void next(View view) {

		final String phone = phoneNumberEditText.getText().toString().trim();
		isPhone=isMobileNum(phone);
		Log.i("BBBBBBBB", ""+isPhone);
		if (TextUtils.isEmpty(phone)) {
			Toast.makeText(this, "手机号不能为空！", Toast.LENGTH_SHORT).show();
			phoneNumberEditText.requestFocus();
			return;
		} else if (isPhone==false) {
			Toast.makeText(this, "格式不正确！", Toast.LENGTH_SHORT).show();
			phoneNumberEditText.requestFocus();
			return;
		} 
		Intent intent = new Intent(RegisterActivity_first.this,RegisterActivity_second.class);
		intent.putExtra("phone", phone);
		startActivity(intent);
		finish();
	}

	public void back(View view) {
		finish();
	}
	public  boolean isMobileNum(String mobiles) {  
		Pattern p = Pattern.compile("^(1(([357][0-9])|(47)|[8][01236789]))\\d{8}$");
		Matcher m = p.matcher(mobiles);   
		return m.matches(); 
	}

}

