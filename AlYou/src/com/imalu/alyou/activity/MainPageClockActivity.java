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

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.imalu.alyou.R;

 

public class MainPageClockActivity extends BaseActivity{
	private Button btCome;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mainpage_clock);
		setview();
		setListener();
		 
	}
	
	 
	private void setListener() {
		// TODO Auto-generated method stub
		btCome.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent =  new Intent(
						MainPageClockActivity.this,
						AddClockActivity.class);		
						startActivity(intent);
			}
		});
	}


	private void setview() {
		// TODO Auto-generated method stub
		btCome = (Button) findViewById(R.id.button1);
	}


	public void back(View v) {
		finish();
	}
	
}
