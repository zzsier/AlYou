package com.imalu.alyou.activity;

import org.apache.http.Header;
import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.easemob.EMCallBack;
import com.easemob.chat.EMChatManager;
import com.imalu.alyou.AlUApplication;
import com.imalu.alyou.R;
import com.imalu.alyou.db.gen.User;
import com.imalu.alyou.domain.Friend;
import com.imalu.alyou.net.JsonHttpResponseHandler;
import com.imalu.alyou.net.NetManager;
import com.imalu.alyou.net.request.AddFriendRequest;
import com.imalu.alyou.net.response.UserInfo;

public class FindFriendDataActivity  extends BaseActivity{
	private TextView username_text;
	private TextView appid_text;
	private String username;
	private String headpic;
	private String hxname;
	private int id;
	private String key;
	private Button back_bt;
	private Button guanzhu_bt;
	private Button btn_addFriend;



	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_personal_data_tianjia);
		init();
		setView();
		setListener();
		Intent intent= getIntent();
		username=intent.getStringExtra("username");
		id=intent.getIntExtra("id", 1);
		key=intent.getStringExtra("key");
		hxname = intent.getStringExtra("hxname");
		headpic = intent.getStringExtra("headpic");
		
		Log.e("USENAME",""+username);
		if(username==null){

			username_text.setText("未设置");
		}else{
			username_text.setText(username);

		}
		appid_text.setText(String.valueOf(id));
		btn_addFriend=(Button) findViewById(R.id.btn_addFriend);
		
		if( AlUApplication.getFriends().existUserbyKey(key) ) {
			btn_addFriend.setVisibility(View.GONE);
		}
	}

	 private void setListener() {
		// TODO Auto-generated method stub
		 guanzhu_bt.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				 String btText = guanzhu_bt.getText().toString();
				 if ("关注".equals(btText)) {
					guanzhu_bt.setText("取消关注");
				} else if("取消关注".equals(btText)){
					guanzhu_bt.setText("关注");
				}
			}
		});
	} 
	 
	public void addFriend(View view) {
		
		AddFriendRequest addFriendReq = new AddFriendRequest();
		addFriendReq.setUserkey(AlUApplication.getMyInfo().getKey());
		addFriendReq.setFriendkey(key);
		
		NetManager.execute(NetManager.ADD_FRIEND_REQUEST_OPERATION, addFriendReq, new JsonHttpResponseHandler() {
			@Override
			public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
				
				try {
					Friend friend = new Friend();
					friend.setUsername(username);
					friend.setId(id);
					friend.setKey(key);
					friend.setHxname(hxname);
					friend.setHeadpicUrl(headpic);
					AlUApplication.getFriends().addFriend(friend);
					btn_addFriend.setVisibility(View.GONE);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private void setView() {
		// TODO Auto-generated method stub
		back_bt=(Button) findViewById(R.id.back_bt);
		guanzhu_bt=(Button) findViewById(R.id.guanzhu_bt);
		
	}

	public void init(){
		username_text=(TextView) findViewById(R.id.username_find_text);
		appid_text=(TextView) findViewById(R.id.appid_find_text);
	}

	public void back(View view){
		finish();
	}	
}

