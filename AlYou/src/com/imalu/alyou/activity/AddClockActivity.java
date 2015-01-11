package com.imalu.alyou.activity;

import com.imalu.alyou.AlUApplication;
import com.imalu.alyou.R;
import com.imalu.alyou.db.ClockDbService;
import com.imalu.alyou.db.UserDbService;
import com.imalu.alyou.db.gen.Clock;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class AddClockActivity extends BaseActivity{
	private Button saveClock;
	private Button cancel;
	private EditText clockname;
	private EditText clocktimer;
	private EditText clocktimer1;
	private EditText clocktimer2;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_clock);

		saveClock = (Button) findViewById(R.id.saveClock);
		cancel = (Button) findViewById(R.id.cancel);
		
		clockname = (EditText) findViewById(R.id.clockname);
		clocktimer = (EditText) findViewById(R.id.clocktimer);
		clocktimer1 = (EditText) findViewById(R.id.clocktimer1);
		clocktimer2 = (EditText) findViewById(R.id.clocktimer2);
		
		cancel.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		
		saveClock.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Clock clock = new Clock();
				short type = 1;
				short order = 0;
				clock.setType(type);
				clock.setClock1(Integer.parseInt(clocktimer1.getText().toString()));
				clock.setClock2(Integer.parseInt(clocktimer2.getText().toString()));
				clock.setClock3(Integer.parseInt(clocktimer.getText().toString()));
				clock.setDescription(clockname.getText().toString());
				clock.setOrder(order);
				clock.setReadonly(false);
				
				ClockDbService.getInstance(AddClockActivity.this).saveClock(clock);
				finish();
				
			}
		});
		 
	}


	public void back(View v) {
		finish();
	}

}
