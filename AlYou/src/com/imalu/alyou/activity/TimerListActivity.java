package com.imalu.alyou.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.imalu.alyou.R;
import com.imalu.alyou.db.gen.Clock;

public class TimerListActivity extends BaseActivity{
	private TextView tvCreateEdit,tvPrestallEdit;
	private TextView preTime1,preTime2,preTime3,edit1,edit2,edit3;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_timer_list);
		initViews();
		setListeners();
		Intent intent = getIntent();
		String name = intent.getStringExtra("name");
		preTime1.setText(name);
		Clock clock = new Clock();
		clock.setDescription(name);
	}

	private void setListeners() {
		edit3.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
		});
		tvCreateEdit.setOnClickListener(new OnClickListener(){
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(TimerListActivity.this, AddClockActivity.class));
			}
		});
		tvPrestallEdit.setOnClickListener(new OnClickListener() {
			 
			@Override
			public void onClick(View v) {
				startActivity(new Intent(TimerListActivity.this, PrestallClockActivity.class));
				
			}
		});
	}

	private void initViews() {
		tvCreateEdit = (TextView)findViewById(R.id.edit_tv_timer_list2);
		tvPrestallEdit = (TextView)findViewById(R.id.edit_tv_timer_list1);
		preTime1 = (TextView)findViewById(R.id.timerlist_tv1 );
		preTime2 = (TextView)findViewById(R.id.timerlist_tv2 );
		preTime3 = (TextView)findViewById(R.id.timerlist_tv3);
		
		edit1 = (TextView)findViewById(R.id.edit_timerlist_tv1);
		edit2 = (TextView)findViewById(R.id.edit_timerlist_tv2);
		edit3 = (TextView)findViewById(R.id.edit_timerlist_tv3);
	}
	public void back(View v) {
		finish();
	}
}
