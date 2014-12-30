package com.imalu.alyou.activity;

import java.util.ArrayList;

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
import android.widget.EditText;
import android.widget.TextView;

import com.imalu.alyou.AlUApplication;
import com.imalu.alyou.R;
import com.imalu.alyou.R.integer;
import com.imalu.alyou.domain.Friend;
import com.imalu.alyou.net.JsonHttpResponseHandler;
import com.imalu.alyou.net.NetManager;
import com.imalu.alyou.net.request.FindFriendRequest;
import com.imalu.alyou.net.response.FindFriendResponse;

public class FindFriendByIdActivity extends BaseActivity{
	private  EditText  et; 
	private  TextView  tv; 
	private Button bt;
	private 	int id;
	private Friend friend;
	private String username;
	private String key;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_findhaoma);
		setview();
		setListener();
	}

	private void setview() {
		// TODO Auto-generated method stub
		et = (EditText) findViewById(R.id.editText_souhaoma);
		bt = (Button) findViewById(R.id.button_sousuo);
	}

	private void setListener() {
		bt.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				id=Integer.parseInt(et.getText().toString());
				Log.e("id", ""+id);
				friend= new Friend();
				FriendFound();
				Intent intent = new Intent(
						FindFriendByIdActivity.this,
						FindFriendDataActivity.class);
				Log.e("NAME", ""+friend.getUsername());
				intent.putExtra("username", username);
				intent.putExtra("id", id);
				intent.putExtra("key",key);
				startActivity(intent); 
				finish();
 
			}
		});
	
	}
 
 

	public void  FriendFound(){
		FindFriendRequest  FriendReq= new FindFriendRequest();
		FriendReq.setID(id);
		NetManager.execute(NetManager.ADDFRIEND_FRIEND_REQUEST_OPERATION, FriendReq, new JsonHttpResponseHandler(){
			@Override
			public void onSuccess(int statusCode, Header[] headers,
					JSONObject response) {
				// TODO Auto-generated method stub
				super.onSuccess(statusCode, headers, response);
				FindFriendResponse friendResponse= new FindFriendResponse();
				
				friendResponse.setJsonObject(response);
				Log.e("ID",""+friendResponse.getId());
				id=friendResponse.getId();
				username=friendResponse.getUserName();
				key=friendResponse.getKey();
				friend.setId(friendResponse.getId());
				friend.setUsername(friendResponse.getUserName());
				friend.setKey(friendResponse.getKey());
			}
		});

	}
	
	
	
	
public void back(View view){
	finish();
}	
}
