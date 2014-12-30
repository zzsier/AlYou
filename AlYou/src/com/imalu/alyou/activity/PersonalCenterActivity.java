package com.imalu.alyou.activity;


import org.w3c.dom.Text;

import com.imalu.alyou.AlUApplication;
import com.imalu.alyou.R;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.http.SslError;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * 
 * @author P8P67LE
 *
 */

public class PersonalCenterActivity extends BaseActivity{
	/*private WebView BBS_webView;
	private ProgressDialog progressDialog;*/
	private TextView username;
	private TextView appnum;
	private ImageView userphoto;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_personal_center);	
	username=(TextView) findViewById(R.id.username_text);
	appnum=(TextView) findViewById(R.id.app_text);
	userphoto=(ImageView) findViewById(R.id.image_personal_data_photo);
	
	String app_id=String.valueOf(AlUApplication.getMyInfo().getId());
	String name=AlUApplication.getMyInfo().getUsername();
	if(name.equals("null")){
		username.setText("未设置");
		
	}else{
		
		username.setText(name);
	}
	appnum.setText(app_id);
	
	userphoto.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			startActivity(new Intent(PersonalCenterActivity.this,PersonalDataSettingsActivity.class));
		}
	});
	
	}
	/**
	 * 返回
	 * 
	 * @param view
	 */
	public void back(View view) {
		finish();
	}
}
