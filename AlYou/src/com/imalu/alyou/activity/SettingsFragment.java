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
import com.imalu.alyou.R.layout;

/**
 * 设置界面
 * 
 * @author Administrator
 * 
 */
public class SettingsFragment extends Fragment implements OnClickListener {

	/**
	 * 设置新消息通知布局
	 */
	private RelativeLayout rl_switch_notification;
	/**
	 * 设置声音布局
	 */
	private RelativeLayout rl_switch_sound;
	/**
	 * 设置震动布局
	 */
	private RelativeLayout rl_switch_vibrate;
	/**
	 * 设置扬声器布局
	 */
	private RelativeLayout rl_switch_speaker;

	/**
	 * 打开新消息通知imageView
	 */
	private ImageView iv_switch_open_notification;
	/**
	 * 关闭新消息通知imageview
	 */
	private ImageView iv_switch_close_notification;
	/**
	 * 打开声音提示imageview
	 */
	private ImageView iv_switch_open_sound;
	/**
	 * 关闭声音提示imageview
	 */
	private ImageView iv_switch_close_sound;
	/**
	 * 打开消息震动提示
	 */
	private ImageView iv_switch_open_vibrate;
	/**
	 * 关闭消息震动提示
	 */
	private ImageView iv_switch_close_vibrate;
	/**
	 * 打开扬声器播放语音
	 */
	private ImageView iv_switch_open_speaker;
	/**
	 * 关闭扬声器播放语音
	 */
	private ImageView iv_switch_close_speaker;

	/**
	 * 声音和震动中间的那条线
	 */
	private TextView textview1, textview2;

	private LinearLayout blacklistContainer;
	
	/**
	 * 退出按钮
	 */
	private Button logoutBtn;
	private Button mySettingBtn;

	//private EMChatOptions chatOptions;
 
	/**
	 * 诊断
	 */
	private LinearLayout llDiagnose;
	
	/**
	 * 个人中心——个人设置
	 */
	private LinearLayout personalcenter;
	private TextView textView;
	private String username;
	/**
	 * 捐助设置
	 */
	private RelativeLayout donorrecordslayout;
	/**
	 * 系统设置 
	 */
	private RelativeLayout systemsettingslayout;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_conversation_settings, container, false);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
//		if(savedInstanceState != null && savedInstanceState.getBoolean("isConflict", false))
//            return;
//		rl_switch_notification = (RelativeLayout) getView().findViewById(R.id.rl_switch_notification);
//		rl_switch_sound = (RelativeLayout) getView().findViewById(R.id.rl_switch_sound);
//		rl_switch_vibrate = (RelativeLayout) getView().findViewById(R.id.rl_switch_vibrate);
//		rl_switch_speaker = (RelativeLayout) getView().findViewById(R.id.rl_switch_speaker);
//
//		iv_switch_open_notification = (ImageView) getView().findViewById(R.id.iv_switch_open_notification);
//		iv_switch_close_notification = (ImageView) getView().findViewById(R.id.iv_switch_close_notification);
//		iv_switch_open_sound = (ImageView) getView().findViewById(R.id.iv_switch_open_sound);
//		iv_switch_close_sound = (ImageView) getView().findViewById(R.id.iv_switch_close_sound);
//		iv_switch_open_vibrate = (ImageView) getView().findViewById(R.id.iv_switch_open_vibrate);
//		iv_switch_close_vibrate = (ImageView) getView().findViewById(R.id.iv_switch_close_vibrate);
//		iv_switch_open_speaker = (ImageView) getView().findViewById(R.id.iv_switch_open_speaker);
//		iv_switch_close_speaker = (ImageView) getView().findViewById(R.id.iv_switch_close_speaker);
	//	logoutBtn = (Button) getView().findViewById(R.id.btn_logout);
	//	mySettingBtn = (Button) getView().findViewById(R.id.mySetting);
//		if(!TextUtils.isEmpty(EMChatManager.getInstance().getCurrentUser())){
//			logoutBtn.setText(getString(R.string.button_logout) + "(" + EMChatManager.getInstance().getCurrentUser() + ")");
//		}
//
//		textview1 = (TextView) getView().findViewById(R.id.textview1);
//		textview2 = (TextView) getView().findViewById(R.id.textview2);
//		
//		blacklistContainer = (LinearLayout) getView().findViewById(R.id.ll_black_list);
//		llDiagnose=(LinearLayout) getView().findViewById(R.id.ll_diagnose);
//		blacklistContainer.setOnClickListener(this);
//		rl_switch_notification.setOnClickListener(this);
//		rl_switch_sound.setOnClickListener(this);
//		rl_switch_vibrate.setOnClickListener(this);
//		rl_switch_speaker.setOnClickListener(this);
		//logoutBtn.setOnClickListener(this);
		//mySettingBtn.setOnClickListener(this);
		//llDiagnose.setOnClickListener(this);
//		chatOptions = EMChatManager.getInstance().getChatOptions();
//		if (chatOptions.getNotificationEnable()) {
//			iv_switch_open_notification.setVisibility(View.VISIBLE);
//			iv_switch_close_notification.setVisibility(View.INVISIBLE);
//		} else {
//			iv_switch_open_notification.setVisibility(View.INVISIBLE);
//			iv_switch_close_notification.setVisibility(View.VISIBLE);
//		}
//		if (chatOptions.getNoticedBySound()) {
//			iv_switch_open_sound.setVisibility(View.VISIBLE);
//			iv_switch_close_sound.setVisibility(View.INVISIBLE);
//		} else {
//			iv_switch_open_sound.setVisibility(View.INVISIBLE);
//			iv_switch_close_sound.setVisibility(View.VISIBLE);
//		}
//		if (chatOptions.getNoticedByVibrate()) {
//			iv_switch_open_vibrate.setVisibility(View.VISIBLE);
//			iv_switch_close_vibrate.setVisibility(View.INVISIBLE);
//		} else {
//			iv_switch_open_vibrate.setVisibility(View.INVISIBLE);
//			iv_switch_close_vibrate.setVisibility(View.VISIBLE);
//		}

//		if (chatOptions.getUseSpeaker()) {
//			iv_switch_open_speaker.setVisibility(View.VISIBLE);
//			iv_switch_close_speaker.setVisibility(View.INVISIBLE);
//		} else {
//			iv_switch_open_speaker.setVisibility(View.INVISIBLE);
//			iv_switch_close_speaker.setVisibility(View.VISIBLE);
//		}

		
		personalcenter =(LinearLayout) getView().findViewById(R.id.personal_center_layout);
		donorrecordslayout=(RelativeLayout) getView().findViewById(R.id.donor_records_layout);
		systemsettingslayout=(RelativeLayout) getView().findViewById(R.id.system_settings_layout);
		textView =(TextView) getView().findViewById(R.id.username_textview);
		username=AlUApplication.getMyInfo().getUsername();
		if(username.equals("null")){
			textView.setText("未设置");
		}else{
			textView.setText(username);
		}
		
		personalcenter.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				startActivity(new Intent(getActivity(),PersonalCenterActivity.class));
				
			}
		});
		
		donorrecordslayout.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				startActivity(new Intent(getActivity(),DonorRecordsActivity.class));
			}
		});
		systemsettingslayout.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				startActivity(new Intent(getActivity(),SystemSettingsActivity.class));
			}
		});
	}

	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
//		case R.id.rl_switch_notification:
//			if (iv_switch_open_notification.getVisibility() == View.VISIBLE) {
//				iv_switch_open_notification.setVisibility(View.INVISIBLE);
//				iv_switch_close_notification.setVisibility(View.VISIBLE);
//				rl_switch_sound.setVisibility(View.GONE);
//				rl_switch_vibrate.setVisibility(View.GONE);
//				textview1.setVisibility(View.GONE);
//				textview2.setVisibility(View.GONE);
//				//chatOptions.setNotificationEnable(false);
//				//EMChatManager.getInstance().setChatOptions(chatOptions);
//
//				HXSDKHelper.getInstance().getModel().setSettingMsgNotification(false);
//			} else {
//				iv_switch_open_notification.setVisibility(View.VISIBLE);
//				iv_switch_close_notification.setVisibility(View.INVISIBLE);
//				rl_switch_sound.setVisibility(View.VISIBLE);
//				rl_switch_vibrate.setVisibility(View.VISIBLE);
//				textview1.setVisibility(View.VISIBLE);
//				textview2.setVisibility(View.VISIBLE);
//				//chatOptions.setNotificationEnable(true);
//				//EMChatManager.getInstance().setChatOptions(chatOptions);
//				HXSDKHelper.getInstance().getModel().setSettingMsgNotification(true);
//			}
//			break;
//		case R.id.rl_switch_sound:
//			if (iv_switch_open_sound.getVisibility() == View.VISIBLE) {
//				iv_switch_open_sound.setVisibility(View.INVISIBLE);
//				iv_switch_close_sound.setVisibility(View.VISIBLE);
//				//chatOptions.setNoticeBySound(false);
//				EMChatManager.getInstance().setChatOptions(chatOptions);
//				HXSDKHelper.getInstance().getModel().setSettingMsgSound(false);
//			} else {
//				iv_switch_open_sound.setVisibility(View.VISIBLE);
//				iv_switch_close_sound.setVisibility(View.INVISIBLE);
//				//chatOptions.setNoticeBySound(true);
//				EMChatManager.getInstance().setChatOptions(chatOptions);
//				HXSDKHelper.getInstance().getModel().setSettingMsgSound(true);
//			}
//			break;
//		case R.id.rl_switch_vibrate:
//			if (iv_switch_open_vibrate.getVisibility() == View.VISIBLE) {
//				iv_switch_open_vibrate.setVisibility(View.INVISIBLE);
//				iv_switch_close_vibrate.setVisibility(View.VISIBLE);
//				chatOptions.setNoticedByVibrate(false);
//				EMChatManager.getInstance().setChatOptions(chatOptions);
//				HXSDKHelper.getInstance().getModel().setSettingMsgVibrate(false);
//			} else {
//				iv_switch_open_vibrate.setVisibility(View.VISIBLE);
//				iv_switch_close_vibrate.setVisibility(View.INVISIBLE);
//				chatOptions.setNoticedByVibrate(true);
//				EMChatManager.getInstance().setChatOptions(chatOptions);
//				HXSDKHelper.getInstance().getModel().setSettingMsgVibrate(true);
//			}
//			break;
//		case R.id.rl_switch_speaker:
//			if (iv_switch_open_speaker.getVisibility() == View.VISIBLE) {
//				iv_switch_open_speaker.setVisibility(View.INVISIBLE);
//				iv_switch_close_speaker.setVisibility(View.VISIBLE);
//				chatOptions.setUseSpeaker(false);
//				EMChatManager.getInstance().setChatOptions(chatOptions);
//				HXSDKHelper.getInstance().getModel().setSettingMsgSpeaker(false);
//			} else {
//				iv_switch_open_speaker.setVisibility(View.VISIBLE);
//				iv_switch_close_speaker.setVisibility(View.INVISIBLE);
//				chatOptions.setUseSpeaker(true);
//				EMChatManager.getInstance().setChatOptions(chatOptions);
//				HXSDKHelper.getInstance().getModel().setSettingMsgVibrate(true);
//			}
//			break;
	//	case R.id.btn_logout:
		//	logout();
			//break;
		//case R.id.mySetting:
			//startActivity(new Intent(getActivity(), MySettingActivity.class));
			//break;
//		case R.id.ll_black_list:
//			startActivity(new Intent(getActivity(), BlacklistActivity.class));
//			break;
//		case R.id.ll_diagnose:
//			startActivity(new Intent(getActivity(), DiagnoseActivity.class));
//			break;
		case R.id.personal_center_layout:
				startActivity(new Intent(getActivity(),PersonalCenterActivity.class));
		case R.id.donor_records_layout:
			startActivity(new Intent(getActivity(),DonorRecordsActivity.class));
		case R.id.system_settings_layout:
			startActivity(new Intent(getActivity(),SystemSettingsActivity.class));
		
		default:
			break;
		}

	}

	void logout() {
		final ProgressDialog pd = new ProgressDialog(getActivity());
		pd.setMessage("正在退出登陆..");
		pd.setCanceledOnTouchOutside(false);
		pd.show();
		AlUApplication.getInstance().logout(new EMCallBack() {
			
			@Override
			public void onSuccess() {
				getActivity().runOnUiThread(new Runnable() {
					public void run() {
						pd.dismiss();
						// 重新显示登陆页面
						((MainActivity) getActivity()).finish();
						startActivity(new Intent(getActivity(), LoginActivity.class));
						
					}
				});
			}
			
			@Override
			public void onProgress(int progress, String status) {
				
			}
			
			@Override
			public void onError(int code, String message) {
				
			}
		});
	}

	
    @Override
    public void onSaveInstanceState(Bundle outState) {
        if(((MainActivity)getActivity()).isConflict)
            outState.putBoolean("isConflict", true);
        super.onSaveInstanceState(outState);
        
    }
}
