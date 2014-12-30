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

public class DonorRecordsActivity extends BaseActivity{
	
	private EditText text;
	private Button button;
	
	private String inserttext=null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_donor_records);	
		text=(EditText) findViewById(R.id.donor_records_et);
	}

	/**
	 * 捐助
	 */
	public void donor(View view){
		Intent intent= new Intent(this,DonorRecordsSuccessActivity.class);
		inserttext=text.getText().toString();
		if(inserttext.length()==0){
			Toast.makeText(this, "请输入捐助积分！", Toast.LENGTH_LONG).show();
		}else {
			intent.putExtra("text", inserttext);
			startActivity(intent);
		}
		
	}
	/**
	 * 捐助记录
	 */
	public void donortext(View view){
		startActivity(new Intent(this,DonorRecordsShowMessageActivity.class));
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
