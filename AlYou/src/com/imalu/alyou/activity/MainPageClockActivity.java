/**
 * Copyright (C) 2013-2014 EaseMob Technologies. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *     http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.imalu.alyou.activity;

import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

import com.imalu.alyou.AlUApplication;
import com.imalu.alyou.Constant;
import com.imalu.alyou.R;
import com.imalu.alyou.adapter.ClockAdapter;
import com.imalu.alyou.adapter.FriendAdapter;
import com.imalu.alyou.adapter.ClockAdapter.ClockViewHolder;
import com.imalu.alyou.db.ClockDbService;
import com.imalu.alyou.db.gen.Clock;
import com.imalu.alyou.domain.HXUser;

 

public class MainPageClockActivity extends BaseActivity{
	private Button addclock;
	private ClockAdapter adapter;
	private ListView listView;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mainpage_clock);
		listView = (ListView) findViewById(R.id.list);
		addclock = (Button)findViewById(R.id.addclock);
		addclock.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View view) {
				startActivity(new Intent(MainPageClockActivity.this, AddClockActivity.class));
				finish();
			}
		});
		showListView();
	}
	
	private void showListView() {
		adapter = new ClockAdapter(this, R.layout.row_clock, 
				ClockDbService.getInstance(AlUApplication.applicationContext).loadAllClock());

		listView.setAdapter(adapter);
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				ClockViewHolder holder = (ClockViewHolder) view.getTag();
				holder.startClock();
			}
		});

		registerForContextMenu(listView);
	}


	public void back(View v) {
		finish();
	}
	
}
