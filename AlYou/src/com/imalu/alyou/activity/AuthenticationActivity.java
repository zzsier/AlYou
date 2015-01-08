package com.imalu.alyou.activity;


import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

import com.imalu.alyou.AlUApplication;
import com.imalu.alyou.R;
import com.imalu.alyou.net.JsonHttpResponseHandler;
import com.imalu.alyou.net.NetManager;
import com.imalu.alyou.net.request.JoinSocaityRequest;
import com.imalu.alyou.net.response.UpdateUserdataResponse;

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
 * 验证信息
 * @author P8P67LE
 *
 */

public class AuthenticationActivity extends BaseActivity{
	
	private EditText text;
	private Button button;
	
	private String inserttext=null;
	private String userkey;
	private String sociatykey;
	private JoinSocaityRequest joinSocaityRequest;
	private Boolean flag;
	private String info;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_authentication_msg);	
		text=(EditText) findViewById(R.id.auth_msg_et);
		userkey=AlUApplication.getMyInfo().getKey();
		sociatykey=AlUApplication.getMyInfo().getSocietykey();
		Log.e("JOIN",""+userkey+"--"+sociatykey );
		joinSocaityRequest= new JoinSocaityRequest();
		joinSocaityRequest.setSocietyKey(sociatykey);
		joinSocaityRequest.setUserKey(userkey);
	}

	/**
	 * 发送验证信息
	 */
	public void send(View view){
		inserttext=text.getText().toString();
		if(inserttext.length()==0){
			Toast.makeText(this, "请输入验证信息！", Toast.LENGTH_SHORT).show();
		}else {
			join();
		
			//Toast.makeText(AuthenticationActivity.this, "发送失败，请检查网络。", Toast.LENGTH_SHORT).show();		
		}
		
	}
	/**
	 * 加入
	 */
	public void join(){
		NetManager.execute(NetManager.JOIN_SOCIATY_REQUEST_OPERATION, 
				joinSocaityRequest, new JsonHttpResponseHandler(){
			@Override
			public void onSuccess(int statusCode, Header[] headers,
					JSONObject response) {
				// TODO Auto-generated method stub
				super.onSuccess(statusCode, headers, response);
				jixi(response);
				/*UpdateUserdataResponse userdataResponse=new UpdateUserdataResponse();
				userdataResponse.setJsonObject(response);
				try {
					flag=userdataResponse.getCode();
					info=userdataResponse.getInfo();

					Log.i("FLAG",""+flag);
					Log.i("INFO",""+ info);
					if("true".equals(flag)){
						Toast.makeText(AuthenticationActivity.this,""+ info,Toast.LENGTH_LONG).show();

					}else{

						Toast.makeText(AuthenticationActivity.this, ""+info,Toast.LENGTH_LONG).show();
					}
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}*/
			}
			
			@Override
					public void onFailure(int statusCode, Header[] headers,
							Throwable throwable, JSONObject errorResponse) {
						// TODO Auto-generated method stub
						super.onFailure(statusCode, headers, throwable, errorResponse);
						jixi(errorResponse);
						//Toast.makeText(AuthenticationActivity.this, "发送失败，请检查网络。", Toast.LENGTH_SHORT).show();		
			}
			
		});
		
		
		
		
	}
	
	/**
	 * 
	 * 解析返回数据
	 */
	public void jixi(JSONObject response){
		UpdateUserdataResponse userdataResponse=new UpdateUserdataResponse();
		userdataResponse.setJsonObject(response);
		try {
			flag=userdataResponse.getCode();
			info=userdataResponse.getInfo();

			Log.i("FLAG",""+flag);
			Log.i("INFO",""+ info);
			if("true".equals(flag)){
				Toast.makeText(AuthenticationActivity.this,""+ info,Toast.LENGTH_LONG).show();
			}else{
				Toast.makeText(AuthenticationActivity.this, ""+info,Toast.LENGTH_LONG).show();
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
