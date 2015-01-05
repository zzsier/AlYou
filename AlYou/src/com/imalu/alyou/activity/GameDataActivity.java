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

public class GameDataActivity extends BaseActivity{
private int flag;
	private TextView gametitle;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game_data);	
		gametitle=(TextView) findViewById(R.id.game_title);
		Intent intent=getIntent();
		flag=intent.getIntExtra("flag", 0);
	if(flag==1){
		gametitle.setText("召唤师");
	}else{
		gametitle.setText("英雄榜");
	}
	
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
