package com.imalu.alyou.activity;


import com.imalu.alyou.R;

import android.app.ProgressDialog;
import android.net.http.SslError;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * 捐助记录
 * @author P8P67LE
 *
 */

public class SystemSettingsActivity extends BaseActivity{
	/*private WebView BBS_webView;
	private ProgressDialog progressDialog;*/
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_system_settings);	
		
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
