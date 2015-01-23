package com.imalu.alyou.activity;


import org.apache.http.Header;
import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.imalu.alyou.AlUApplication;
import com.imalu.alyou.R;
import com.imalu.alyou.net.JsonHttpResponseHandler;
import com.imalu.alyou.net.NetManager;
import com.imalu.alyou.net.request.MsgSendRequest;
import com.imalu.alyou.net.response.MsgSendResponse;

/**
 * 捐助记录
 * @author P8P67LE
 *
 */
public class SystemSettingsActivity extends BaseActivity{
	private Switch SwitchMsgSend;
	int status;
	private TextView tvFriendVarify,tvVersionTest,tvAdvice,//评价是去应用商店评价
						tvUs,tvFanhui;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_system_settings);	
		initViews();
		setListeners();
	}
	private void setListeners() {
		tvUs.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(SystemSettingsActivity.this,AboutUsActivity.class);
				startActivity(intent);
				
			}
		});
		tvFanhui.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(SystemSettingsActivity.this,AdviceActivity.class);
				startActivity(intent);				
			}
		});
		tvVersionTest.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
//				Intent intent = new Intent(SystemSettingsActivity.this,VersionUpdateActivity.class);
//				startActivity(intent);	
				Toast.makeText(SystemSettingsActivity.this,"暂无版本更新", 1).show();
			}
		});
		SwitchMsgSend.setOnCheckedChangeListener(new OnCheckedChangeListener() {			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if(isChecked == true)
				{
					msgSend(1);
					isChecked = false;
				}else
				{
					msgSend(0);
					isChecked = true;
				}
			}
		});
		tvFriendVarify.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(SystemSettingsActivity.this,VerifyFriendActivity.class);
				startActivity(intent);				
			}
		});
	}
	public void msgSend(final int status)
	{
		MsgSendRequest msgSendRequest = new MsgSendRequest();
		msgSendRequest.setUserkey(AlUApplication.getMyInfo().getKey());
		Log.i("个人资料",AlUApplication.getMyInfo().getKey());
		msgSendRequest.setStatus(status);
		Log.i("///////",""+status);
		NetManager.execute(NetManager.SYSTEM_SETTING_MSG_SENG, msgSendRequest, new JsonHttpResponseHandler(){
			@Override
			public void onSuccess(int statusCode, Header[] headers,
					JSONObject response) {
				// TODO Auto-generated method stub
				super.onSuccess(statusCode, headers, response);
				MsgSendResponse msgSendResponse = new MsgSendResponse();
				msgSendResponse.setJsonObject(response);
				if(status == 1)
				{
					//if(msgSendResponse.getCode() == true) {
						Toast.makeText(SystemSettingsActivity.this, "打开消息推送",0).show();
					//} else {
					////	Toast.makeText(SystemSettingsActivity.this, "关闭消息推送",0).show();
					//}
				}else if(status == 0)
				{
					Toast.makeText(SystemSettingsActivity.this, "关闭消息推送",0).show();
				}
			}
		});
	}

	private void initViews() {
		SwitchMsgSend = (Switch)findViewById(R.id.switch_message_send);
		tvFriendVarify = (TextView)findViewById(R.id.tv_friend_varify);
		tvVersionTest = (TextView)findViewById(R.id.tv_version_test);
		tvAdvice = (TextView)findViewById(R.id.settings_advice_tv);
		tvUs = (TextView)findViewById(R.id.system_settings_about_us);
		tvFanhui = (TextView)findViewById(R.id.system_setting_yijianfankui);
	}
	/**
	 * 返回
	 * @param view
	 */
	public void back(View view) {
		finish();
	}	
}
