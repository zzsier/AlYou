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
package com.imalu.alyou.adapter;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SectionIndexer;
import android.widget.TextView;

import com.imalu.alyou.R;
import com.imalu.alyou.Constant;
import com.imalu.alyou.db.gen.Clock;
import com.imalu.alyou.domain.Friend;
import com.imalu.alyou.domain.HXUser;
import com.imalu.alyou.domain.User;
import com.imalu.alyou.widget.Sidebar;

/**
 * 简单的好友Adapter实现
 *
 */
public class ClockAdapter extends ArrayAdapter<Clock> {
	
	private LayoutInflater layoutInflater;
	private EditText query;
	private int res;

	public ClockAdapter(Context context, int resource, List<Clock> objects) {
		super(context, resource, objects);
		this.res = resource;
		layoutInflater = LayoutInflater.from(context);
		
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		if(convertView == null){
			convertView = layoutInflater.inflate(res, null);
		}
		
		ClockViewHolder holder = (ClockViewHolder) convertView.getTag();
		
		Clock clock = getItem(position);	
		if(clock == null) {
			Log.d("ContactAdapter", position + "");
		}
		
		if (holder == null) {
			holder = new ClockViewHolder();
			holder.mProgress = (ProgressBar) convertView.findViewById(R.id.progress_bar);
			holder.clockname = (TextView) convertView.findViewById(R.id.clockname);
			holder.clock3 = clock.getClock3();
			holder.clock2 = clock.getClock2();
			holder.clock1 = clock.getClock1();
			
			holder.initClock();
			 
			holder.clockname.setText(clock.getDescription());
			convertView.setTag(holder);
		}

		return convertView;
	}
	
	public static class ClockViewHolder {
		
		TextView clockname;
		ProgressBar mProgress;
	    CountDownTimer cdtimer;
	    int clock3;
	    int clock2;
	    int clock1;
	    long now;
	    
	    Handler mHandler = new Handler();
	    
	    public void initClock() {
	    	cdtimer = new CountDownTimer(clock3*1000, 1000) {
			     public void onTick(long millisUntilFinished) {
			    	 now = millisUntilFinished/1000;
			    	 Log.e("StartClock", " now time is "+now);
			    	 mHandler.post(new Runnable() {
			    		 public void run() {
			    			 int mProgressStatus = Long.valueOf(now*100/clock3).intValue();
			    			 Log.e("StartClock", "progress status is "+mProgressStatus+" now time is "+now);
			    			 mProgress.setProgress(mProgressStatus);
			    		 }
			    	 });
			         
			     }
			     public void onFinish() {
//			         mTextField.setText("done!");
			     }
			 };
	    }
	    
	    public void startClock() {
	    	Log.e("StartClock", "clock name is "+clockname);
	    	cdtimer.start();
	    }
	    
	    
	}

}
