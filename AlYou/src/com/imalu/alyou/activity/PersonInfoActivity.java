package com.imalu.alyou.activity;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.imalu.alyou.AlUApplication;
import com.imalu.alyou.R;
import com.imalu.alyou.net.JsonHttpResponseHandler;
import com.imalu.alyou.net.NetManager;
import com.imalu.alyou.net.request.GuanzhuRequest;
import com.imalu.alyou.net.response.RegisterResponse;

public class PersonInfoActivity extends BaseActivity{
	private Button btBack;
	private Button btSendMsg;
	private Button btGuanzhu;
	private TextView tvUserName;
	private TextView tvApp;
	private TextView tvJiFen;
	private TextView tvGongHui;
	private TextView tvGame1;
	private TextView tvGame2;
	private ImageView imgHeadImg;
	private ImageView imgEnter;
	private String name;
	private String jifen;
	private String id;
	private String societykey;
	private int flag;
	private String beiguanzhurenkey;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_personal_data);

		Intent intent=getIntent();
		name=intent.getStringExtra("username");
		jifen=String.valueOf(intent.getIntExtra("jifen", 0));
		id=String.valueOf(intent.getIntExtra("id", 0));
		flag=intent.getIntExtra("flag", 0);
		societykey=intent.getStringExtra("societykey");
		beiguanzhurenkey=intent.getStringExtra("key");
		initView();
		setListener();
		if(name==null){
			tvUserName.setText("未设置");

		}else{

			tvUserName.setText(name);
		}
		tvApp.setText(id);
		tvJiFen.setText(jifen);
		if(societykey.equals("null")){
			tvGongHui.setText("未加入");

		}else{

			//tvGongHui.setText(gonghui);
		}

	}
	private void setListener() {
		// TODO Auto-generated method stub
		btGuanzhu.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				String  btText = btGuanzhu.getText().toString();
				if ("关注".equals(btText)) {
					guanzhu();
					btGuanzhu.setText("取消关注");
				} else if("取消关注".equals(btText)){
					quxiaoguanzhu();
					btGuanzhu.setText("关注");
				}
			}
		});
	}
	private void initView() {
		btBack=(Button) findViewById(R.id.back_bt);
		btGuanzhu=(Button) findViewById(R.id.person_data_guanzhu_bt);
		btSendMsg=(Button) findViewById(R.id.button1);
		tvUserName=(TextView) findViewById(R.id.textView2);
		tvApp=(TextView) findViewById(R.id.textView4);
		tvJiFen=(TextView) findViewById(R.id.textView6);
		tvGongHui=(TextView) findViewById(R.id.textView8);
		tvGame1=(TextView) findViewById(R.id.textView12);
		tvGame2=(TextView) findViewById(R.id.textView11);
		imgHeadImg=(ImageView) findViewById(R.id.image_personal_data_photo);
		imgEnter=(ImageView) findViewById(R.id.imageView3);
		if(flag==1){
			btGuanzhu.setText("取消关注");
		}else{
			btGuanzhu.setText("关注");
		}

	}

	/**
	 * 关注
	 */
	public void guanzhu(){
		GuanzhuRequest guanzhuRequest= new GuanzhuRequest();
		guanzhuRequest.setGuanzhurenkey(AlUApplication.getMyInfo().getKey());
		Log.e("beiguanzhurenkey", ""+beiguanzhurenkey);
		guanzhuRequest.setBeiguanzhurenkey(beiguanzhurenkey);
		NetManager.execute(NetManager.CARE_ABOUT_CONCERN, guanzhuRequest, new JsonHttpResponseHandler(){

			@Override
			public void onSuccess(int statusCode, Header[] headers,
					JSONObject response) {
				// TODO Auto-generated method stub
				super.onSuccess(statusCode, headers, response);
				RegisterResponse registerResponse= new RegisterResponse();
				registerResponse.setJsonObject(response);
				try {
					Toast.makeText(PersonInfoActivity.this, ""+registerResponse.getInfo(), Toast.LENGTH_SHORT).show();
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
			@Override
			public void onFailure(int statusCode, Header[] headers,
					Throwable throwable, JSONObject errorResponse) {
				// TODO Auto-generated method stub
				super.onFailure(statusCode, headers, throwable, errorResponse);
				Toast.makeText(PersonInfoActivity.this, "关注失败，请检查网络。。。", Toast.LENGTH_SHORT).show();

			}

		});

	}

	/**
	 * 取消关注
	 */

	public void quxiaoguanzhu(){
		GuanzhuRequest guanzhuRequest= new GuanzhuRequest();
		guanzhuRequest.setGuanzhurenkey(AlUApplication.getMyInfo().getKey());
		guanzhuRequest.setBeiguanzhurenkey(beiguanzhurenkey);
		NetManager.execute(NetManager.CANCEL_CARE_ABOUT_CONCERN, guanzhuRequest, new JsonHttpResponseHandler(){

			@Override
			public void onSuccess(int statusCode, Header[] headers,
					JSONObject response) {
				// TODO Auto-generated method stub
				super.onSuccess(statusCode, headers, response);
				RegisterResponse registerResponse= new RegisterResponse();
				registerResponse.setJsonObject(response);
				try {
					Toast.makeText(PersonInfoActivity.this, ""+registerResponse.getInfo(), Toast.LENGTH_SHORT).show();
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
			@Override
			public void onFailure(int statusCode, Header[] headers,
					Throwable throwable, JSONObject errorResponse) {
				// TODO Auto-generated method stub
				super.onFailure(statusCode, headers, throwable, errorResponse);
				Toast.makeText(PersonInfoActivity.this, "关注失败，请检查网络。。。", Toast.LENGTH_SHORT).show();

			}

		});

	}	




	public void back(View view) {
		finish();
	}

}
