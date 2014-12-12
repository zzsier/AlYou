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
 * 实现论坛的链接
 * @author P8P67LE
 *
 */

public class EverydayActivity extends BaseActivity{
	private WebView BBS_webView;
	private ProgressDialog progressDialog;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mainpage_activity);	
		//初始化控件
		init();
		//控件的监听事件
		listen();
	}
	/**
	 * 设置webview属性
	 */
	private void listen() {
		// TODO Auto-generated method stub
		//支持JavaScript脚本语言
		BBS_webView.getSettings().setJavaScriptEnabled(true);
		BBS_webView.loadUrl("http://114.112.98.147/forum.php?gid=45");
		//设置视图
		if(BBS_webView!=null){
			BBS_webView.setWebViewClient(new WebViewClient(){
				//设置在当前页面跳转
				@Override
				public boolean shouldOverrideUrlLoading(WebView view, String url) {
					// TODO Auto-generated method stub
					view.loadUrl(url);
					return true;
				}
				//加载完成销毁弹框progressDialog
				@Override
				public void onPageFinished(WebView view, String url) {
					// TODO Auto-generated method stub
					super.onPageFinished(view, url);
					progressDialog.dismiss();
				}
				//可以接收处理http请求
				@Override
				public void onReceivedSslError(WebView view,
						SslErrorHandler handler, SslError error) {
					// TODO Auto-generated method stub
					handler.proceed();
				}
			});
		}
	}
	//论坛加载提示
	public void showMessage(){
		progressDialog = new ProgressDialog(this);
		progressDialog.setCanceledOnTouchOutside(false);
		progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		progressDialog.setMessage("正在加载论坛...");
		progressDialog.show();
	}


	//初始化控件
	private void init() {
		// TODO Auto-generated method stub
		BBS_webView=(WebView) findViewById(R.id.bbs_webView);
		showMessage();
	}

	//设置回退
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		//处理返回键，网页返回问题
		Log.i("info",BBS_webView.canGoBack() +"");
		if(BBS_webView.canGoBack() && keyCode == KeyEvent.KEYCODE_BACK){
			BBS_webView.goBack(); //goBack()表示返回webView的上一页面
			return true;
		}
		finish();
		return false;
	}
}
