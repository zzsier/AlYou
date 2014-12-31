package com.imalu.alyou.activity;




import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

import com.imalu.alyou.AlUApplication;
import com.imalu.alyou.R;
import com.imalu.alyou.net.AsyncHttpResponseHandler;
import com.imalu.alyou.net.JsonHttpResponseHandler;
import com.imalu.alyou.net.NetManager;
import com.imalu.alyou.net.request.UpdateUserdataRequest;
import com.imalu.alyou.net.response.UpdateUserdataResponse;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 个人资料设置
 * @author P8P67LE
 *
 */

public class PersonalDataSettingsActivity extends BaseActivity{
	private EditText username_et;
	private EditText realname_et;
	private EditText age_et;
	private EditText locus_et;
	private TextView app_text;
	private TextView jifen_text;
	private Button save_bt;
	private Boolean flag;
	private String info;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_personal_data_settings);	
		init();
		username_et.setText(AlUApplication.getMyInfo().getUsername());
		realname_et.setText(AlUApplication.getMyInfo().getRealname());
		age_et.setText(String.valueOf(AlUApplication.getMyInfo().getAge()));
		locus_et.setText(AlUApplication.getMyInfo().getLocus());
		app_text.setText(String.valueOf(AlUApplication.getMyInfo().getId()));
		jifen_text.setText(String.valueOf(AlUApplication.getMyInfo().getJifen()));

	}


	public void init(){
		username_et=(EditText) findViewById(R.id.usename_edittext);
		realname_et=(EditText) findViewById(R.id.realname_edittext);
		age_et=(EditText) findViewById(R.id.age_edittext);
		locus_et=(EditText) findViewById(R.id.address_edittext);
		app_text=(TextView) findViewById(R.id.appid_textview);
		jifen_text=(TextView) findViewById(R.id.jifen_textview);
		save_bt=(Button) findViewById(R.id.save_bt);
	}

	/**
	 * 保存
	 */
	public void save(View view){
		String username=username_et.getText().toString();
		String realname=realname_et.getText().toString();
		int age=Integer.parseInt(age_et.getText().toString());
		String locus=locus_et.getText().toString();

		UpdateUserdataRequest UpdateReq=new UpdateUserdataRequest();
		UpdateReq.setAge(age);
		UpdateReq.setLocus(locus);
		UpdateReq.setName(username);
		UpdateReq.setRealname(realname);
		UpdateReq.setHeadpicture("null");
		UpdateReq.setUphone(AlUApplication.getMyInfo().getPhoneNum());
		UpdateReq.setPwd(AlUApplication.getMyInfo().getPassword());
		UpdateReq.setSex(1);


		NetManager.execute(NetManager.UPDATE_USERDATA_REQUEST_OPERATION,UpdateReq,new JsonHttpResponseHandler(){
//此方法不能被重写。。。。
		@Override
		public void onSuccess(int statusCode, Header[] headers,
				JSONObject response) {
			// TODO Auto-generated method stub
			super.onSuccess(statusCode, headers, response);
			UpdateUserdataResponse userdataResponse=new UpdateUserdataResponse();
			userdataResponse.setJsonObject(response);
			try {
				flag=userdataResponse.getCode();
				info=userdataResponse.getInfo();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}


		});
		Log.i("FLAG",""+flag);
		Log.i("INFO",""+ info);
		
		
		
		if("true".equals(flag)){
			Toast.makeText(this,""+ info,Toast.LENGTH_LONG).show();

		}else{

			Toast.makeText(this, ""+info,Toast.LENGTH_LONG).show();
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
