package com.imalu.alyou.activity;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

import com.imalu.alyou.AlUApplication;
import com.imalu.alyou.R;
import com.imalu.alyou.db.ClockDbService;
import com.imalu.alyou.db.UserDbService;
import com.imalu.alyou.db.gen.Clock;
import com.imalu.alyou.net.JsonHttpResponseHandler;
import com.imalu.alyou.net.NetManager;
import com.imalu.alyou.net.request.ChangePwdRequest;
import com.imalu.alyou.net.response.RegisterResponse;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ChangePwdActivity extends BaseActivity{
	private EditText oldpwd_et;
	private EditText newpwd_et;
	private EditText confirmpwd_et;
	private Button confirmbt;
	private String oldpwd;
	private String newpwd;
	private String confirmpwd;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_change_pwd);
		init();
		
	
		 
	}


	private void init() {
		// TODO Auto-generated method stub
		oldpwd_et=(EditText) findViewById(R.id.old_pwd_et);
		newpwd_et=(EditText) findViewById(R.id.new_pwd_et);
		confirmpwd_et=(EditText) findViewById(R.id.confirm_pwd_et);
		confirmbt=(Button) findViewById(R.id.confirm_bt);
	}

	public void confirm(View view){
		oldpwd=oldpwd_et.getText().toString();
		newpwd=newpwd_et.getText().toString();
		confirmpwd=confirmpwd_et.getText().toString();
		if(!oldpwd.equals(null)&&!newpwd.equals(null)&&!confirmpwd.equals(null)){
		
		
			if(oldpwd.equals(newpwd)){
				Toast.makeText(ChangePwdActivity.this, "不能与原密码相同！", Toast.LENGTH_SHORT).show();
			}else if(!newpwd.equals(newpwd)){
				Toast.makeText(ChangePwdActivity.this, "确认密码错误！", Toast.LENGTH_SHORT).show();
			}else{
				changepwd();
				
			}
		}else{
			
			Toast.makeText(ChangePwdActivity.this, "密码不能为空！", Toast.LENGTH_SHORT).show();
		}
	}
	//修改密码请求
	public void changepwd(){
		ChangePwdRequest  pwdRequest= new ChangePwdRequest();
		pwdRequest.setUid(AlUApplication.getMyInfo().getPhoneNum());
	pwdRequest.setOldPwd(oldpwd);
	pwdRequest.setNewPwd(newpwd);
		NetManager.execute(NetManager.CHANGE_PWD_REQUEST_OPERATION, pwdRequest, new JsonHttpResponseHandler(){
			
			@Override
			public void onSuccess(int statusCode, Header[] headers,
					JSONObject response) {
				// TODO Auto-generated method stub
				super.onSuccess(statusCode, headers, response);
				RegisterResponse registerResponse= new RegisterResponse();
				registerResponse.setJsonObject(response);
				try {
					Toast.makeText(ChangePwdActivity.this, registerResponse.getInfo(), Toast.LENGTH_SHORT).show();
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				
			}
			
			
			
		});
		
		
	}

	public void back(View v) {
		finish();
	}

}
