package com.imalu.alyou.activity;

import android.os.Bundle;
import android.view.View;

import com.imalu.alyou.R;

public class AboutUsActivity extends BaseActivity{
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_about_us);	
		
	}
	public void back(View view)
	{
		finish();
	}
}
