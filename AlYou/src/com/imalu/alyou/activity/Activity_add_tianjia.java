package com.imalu.alyou.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.imalu.alyou.R;

public class Activity_add_tianjia extends BaseActivity{
	private Button bt;
//	private TextView tv16;
	 	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_friend);
		setview();
		setListener();
		}
	private void setview() {
		// TODO Auto-generated method stub
		bt = (Button) findViewById(R.id.button_sou);
//		tv16 = (TextView) findViewById(R.id.textView16);
	}
	private void setListener() {
		// TODO Auto-generated method stub
		bt.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
			Intent intent = new Intent
					(Activity_add_tianjia.this,Activity_add_findhaoma.class);	
			startActivity(intent);
			}
		});
//		tv16.setOnClickListener(new OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				Intent intent = new Intent
//						(Activity_add_tianjia.this,FriendlistFragment.class);	
//				startActivity(intent);	
//				finish();
//		
//			}
//		});
	}
	 
	 
}
