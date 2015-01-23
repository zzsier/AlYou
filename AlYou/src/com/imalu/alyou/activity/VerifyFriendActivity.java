package com.imalu.alyou.activity;

import org.apache.http.Header;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

import com.imalu.alyou.AlUApplication;
import com.imalu.alyou.R;
import com.imalu.alyou.net.JsonHttpResponseHandler;
import com.imalu.alyou.net.NetManager;
import com.imalu.alyou.net.request.VerifyFriendRequest;
import com.imalu.alyou.net.response.VerifyFriendResponse;

public class VerifyFriendActivity extends Activity {

	private RadioGroup radioGroup;
	private RadioButton radioAddAll,radioVerify,radioAddNo;
	private TextView tvSubmit;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_verifyfriend);
		radioGroup = (RadioGroup)findViewById(R.id.radioGroup_verifyfriend);
		radioAddAll = (RadioButton)findViewById(R.id.radio_add_anyone);
		radioVerify = (RadioButton)findViewById(R.id.radio_add_verify_msg);
		radioAddNo = (RadioButton)findViewById(R.id.radio_refuse_anyone);

		tvSubmit = (TextView)findViewById(R.id.tv_submit);
		
		radioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				switch (checkedId) {
				case R.id.radio_add_anyone:
					radioAddAll.setChecked(true);
					radioVerify.setChecked(false);
					radioAddNo.setChecked(false);
					verifyFriend(0);
					break;
				case R.id.radio_add_verify_msg:
					radioAddAll.setChecked(false);
					radioVerify.setChecked(true);
					radioAddNo.setChecked(false);
					verifyFriend(1);
					break;
				case R.id.radio_refuse_anyone:
					radioAddAll.setChecked(false);
					radioVerify.setChecked(false);
					radioAddNo.setChecked(true);
					verifyFriend(2);
					break;

				default:
					break;
				}
				//				if(checkedId == radioAddAll.getId())
				//				{
				//					verifyFriend(0);
				//				}
				//				if(checkedId == radioVerify.getId())
				//				{
				//					verifyFriend(1);
				//				}
				//				if(checkedId == radioAddNo.getId())
				//				{
				//					verifyFriend(2);
				//				}
			}
		});

		tvSubmit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				ProgressBar bar = new ProgressBar(VerifyFriendActivity.this);
				bar.setVisibility(View.VISIBLE);
				bar.setMax(100);
				
				AlertDialog.Builder builder = new Builder(VerifyFriendActivity.this);

				builder.setMessage("设置成功");

				builder.setTitle("提示");
				builder.setPositiveButton("确定", new AlertDialog.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
					}
				});
				builder.create().show();
			}
		});
	}
	public void verifyFriend(int s)
	{
		VerifyFriendRequest verifyFriendRequest = new VerifyFriendRequest();
		verifyFriendRequest.setUID(AlUApplication.getMyInfo().getPhoneNum());
		verifyFriendRequest.setSetFriCheck(s);
		NetManager.execute(NetManager.VERIFY_FRIEND, verifyFriendRequest, new JsonHttpResponseHandler(){
			@Override
			public void onSuccess(int statusCode, Header[] headers,
					JSONObject response) {
				// TODO Auto-generated method stub
				super.onSuccess(statusCode, headers, response);
				VerifyFriendResponse verifyFriendResponse = new VerifyFriendResponse();
				verifyFriendResponse.setJsonObject(response);
			}		
		});
	}
	public void back(View view)
	{
		finish();
	}
}
