package com.imalu.alyou.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.imalu.alyou.R;

public class FindFriendDataActivity  extends BaseActivity{
	private TextView username_text;
	private TextView appid_text;
	private String username;
	private int id;
	private String key;
	private Button back_bt;



	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_personal_data_tianjia);
		init();
		setView();
		Intent intent= getIntent();
		username=intent.getStringExtra("username");
		id=intent.getIntExtra("id", 1);
		key=intent.getStringExtra("key");
		Log.e("USENAME",""+username);
		if(username==null){

			username_text.setText("未设置");
		}else{
			username_text.setText(username);

		}
		appid_text.setText(String.valueOf(id));

	}

	/*private void setListener() {
		// TODO Auto-generated method stub
		back_bt.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent  =new Intent(FindFriendDataActivity.this,
						FindFriendByIdActivity.class);
				startActivity(intent);
				finish();
			}
		});
	}*/

	private void setView() {
		// TODO Auto-generated method stub
		back_bt=(Button) findViewById(R.id.back_bt);
	}

	public void init(){
		username_text=(TextView) findViewById(R.id.username_find_text);
		appid_text=(TextView) findViewById(R.id.appid_find_text);
	}

	public void back(View view){
		finish();
	}	
}

