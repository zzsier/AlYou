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
 * 注册页2
 * 
 */
public class RegisterActivity_second extends BaseActivity {

	private EditText identifying_codeEditText;//验证码
	private Button nextButton;
	private Button identifying_codeButton;//获取验证码按钮
	private String phone;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register_second);

		identifying_codeEditText = (EditText) findViewById(R.id.identifying_code_edit);
		nextButton=(Button) findViewById(R.id.code_next_bt);
		identifying_codeButton=(Button) findViewById(R.id.identifying_code_bt);
		Intent intent= getIntent();
		phone=intent.getStringExtra("phone");
	}

	/**
	 * 填写验证码
	 * 
	 * @param view
	 */
	public void next(View view) {

		final String  identifying_code= identifying_codeEditText.getText().toString().trim();

		if (TextUtils.isEmpty(identifying_code)) {
			Toast.makeText(this, "验证码不能为空！", Toast.LENGTH_SHORT).show();
			identifying_codeEditText.requestFocus();
			return;
		} else if (identifying_code.length()!=6) {
			Toast.makeText(this, "验证码格式不正确！", Toast.LENGTH_SHORT).show();
			identifying_codeEditText.requestFocus();
			return;
		} 
		Intent intent = new Intent(RegisterActivity_second.this,RegisterActivity.class);
		intent.putExtra("phone", phone);
		startActivity(intent);
		finish();
	}

	public void back(View view) {
		finish();
	}


}

