package com.imalu.alyou.activity;


import com.imalu.alyou.R;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.http.SslError;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * 系统设置
 * @author P8P67LE
 *
 */

public class DonorRecordsShowMessageActivity extends BaseActivity{
	
	private EditText text;
	private Button button;
	
	private String inserttext;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_donor_records_show_msg);	
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
