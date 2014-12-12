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

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.easemob.EMCallBack;
import com.easemob.applib.controller.HXSDKHelper;
import com.easemob.chat.EMChatManager;
import com.easemob.chat.EMChatOptions;
import com.imalu.alyou.R;
import com.imalu.alyou.AlUApplication;

/**
 * 设置界面
 * 
 * @author Administrator
 * 
 */
public class MainPageFragment extends Fragment implements OnClickListener {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_mainpage, container, false);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		if(savedInstanceState != null && savedInstanceState.getBoolean("isConflict", false))
            return;
	
		((Button)getView().findViewById(R.id.btn_mainpage_job)).setOnClickListener(this);
		((Button)getView().findViewById(R.id.btn_mainpage_activity)).setOnClickListener(this);
		((Button)getView().findViewById(R.id.btn_mainpage_board)).setOnClickListener(this);
		((Button)getView().findViewById(R.id.btn_mainpage_clock)).setOnClickListener(this);
		((Button)getView().findViewById(R.id.btn_mainpage_map)).setOnClickListener(this);
	}

	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.btn_mainpage_clock:
				startActivity(new Intent(getActivity(), MainPageClockActivity.class));
				break;
			case R.id.btn_mainpage_board:
				startActivity(new Intent(getActivity(),BBSActivity.class));
				break;
			case R.id.btn_mainpage_job:
				break;
			case R.id.btn_mainpage_activity:
				startActivity( new Intent(getActivity(),EverydayActivity.class));
				break;
			case R.id.btn_mainpage_map:
				startActivity( new Intent( getActivity(),BaiduMapActivity.class));
				break;
				
			default:
				startActivity(new Intent(getActivity(), UnConstructActivity.class));
				break;
		}
	}
	
    @Override
    public void onSaveInstanceState(Bundle outState) {
        if(((MainActivity)getActivity()).isConflict)
            outState.putBoolean("isConflict", true);
        super.onSaveInstanceState(outState);
        
    }
}
