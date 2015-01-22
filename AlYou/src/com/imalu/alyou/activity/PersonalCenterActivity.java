package com.imalu.alyou.activity;


import org.w3c.dom.Text;

import com.easemob.EMCallBack;
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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
	private TextView jifen;
	private ImageView userphoto;
	private TextView lol;
	private TextView wow;
	private String app_id;
	private String name;
	private String jifen_number;
	private LinearLayout friendsCircle;
	private Button changePwd;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_personal_center);	
	username=(TextView) findViewById(R.id.username_text);
	appnum=(TextView) findViewById(R.id.app_text);
	userphoto=(ImageView) findViewById(R.id.image_personal_data_photo);
	jifen=(TextView) findViewById(R.id.jifen_text);
	lol=(TextView) findViewById(R.id.lol_text);
	wow=(TextView) findViewById(R.id.wow_text);
	changePwd=(Button) findViewById(R.id.change_pwd_bt);
	friendsCircle = (LinearLayout)findViewById(R.id.friendsCircle);
	 app_id=String.valueOf(AlUApplication.getMyInfo().getId());
	 name=AlUApplication.getMyInfo().getUsername();
	 jifen_number=String.valueOf(AlUApplication.getMyInfo().getJifen());
	Log.i("NAME", ""+name);
	if(name==null){
		username.setText("未设置");
		
	}else{
		
		username.setText(name);
	}
	appnum.setText(app_id);
	jifen.setText(jifen_number);
	changePwd.setOnClickListener(new OnClickListener() {

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			startActivity(new Intent(PersonalCenterActivity.this,ChangePwdActivity.class));	
		}
	});

	friendsCircle.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			Intent intent=  new  Intent();
			intent.setClass(PersonalCenterActivity.this, PhotoAlbumActivity.class);
			startActivity(intent);
			
		}
	});
	userphoto.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			startActivity(new Intent(PersonalCenterActivity.this,PersonalDataSettingsActivity.class));
		}
	});
	
	lol.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			Intent   intent1= new Intent(PersonalCenterActivity.this,GameDataActivity.class);
			intent1.putExtra("flag", 1);	
			startActivity(intent1);
		}
	});
	wow.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			Intent   intent2= new Intent(PersonalCenterActivity.this,GameDataActivity.class);
			intent2.putExtra("flag", 2);
			startActivity(intent2);
		}
	});
	
	}
	

/**
	 * 退出登录
	 * 
	 */
	public void out(View view){
		//	EMChatManager.getInstance().logout();
		//	ActivityManager am = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
		//   am.restartPackage(getPackageName());
		
		AlUApplication.getInstance().logout(new EMCallBack() {
			
			@Override
			public void onSuccess() {
				// TODO Auto-generated method stub
				AlUApplication.logout();
				Intent logoutIntent = new Intent(PersonalCenterActivity.this, LoginActivity.class);
                logoutIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(logoutIntent);
				//Toast.makeText(PersonalCenterActivity.this, "onSuccess", Toast.LENGTH_SHORT).show();
			Log.e("onSuccess", "onSuccess");
			
			}
			
			@Override
			public void onProgress(int arg0, String arg1) {
				// TODO Auto-generated method stub
			//	Toast.makeText(PersonalCenterActivity.this, "onProgress", Toast.LENGTH_SHORT).show();
				Log.e("onProgress", "onProgress");
			}
			
			@Override
			public void onError(int arg0, String arg1) {
				// TODO Auto-generated method stub
			//	Toast.makeText(PersonalCenterActivity.this, "onError", Toast.LENGTH_SHORT).show();
				Log.e("onError", "onError");
			}
		});
	
	}
	
	
	
	
@Override
protected void onResume() {
	// TODO Auto-generated method stub
	super.onResume();
	 name=AlUApplication.getMyInfo().getUsername();
	 if(name==null){
			username.setText("未设置");
			
		}else{
			
			username.setText(name);
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
