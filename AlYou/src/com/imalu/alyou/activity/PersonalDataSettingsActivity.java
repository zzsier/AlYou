package com.imalu.alyou.activity;




import java.util.ArrayList;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.imalu.alyou.AlUApplication;
import com.imalu.alyou.R;
import com.imalu.alyou.domain.Sociaty;
import com.imalu.alyou.net.AsyncHttpResponseHandler;
import com.imalu.alyou.net.JsonHttpResponseHandler;
import com.imalu.alyou.net.NetManager;
import com.imalu.alyou.net.request.GetSocaityRequest;
import com.imalu.alyou.net.request.OutSocaityRequest;
import com.imalu.alyou.net.request.UpdateUserdataRequest;
import com.imalu.alyou.net.request.UserKeyRequest;
import com.imalu.alyou.net.response.SociatyResponse;
import com.imalu.alyou.net.response.UpdateUserdataResponse;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
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
	private Button apply_bt;
	private Boolean flag;
	private String info;
	private String key;
	private ArrayList<Sociaty> residentsociaties;
	private UserKeyRequest userKeyRequest;
	private TextView sociaty_name;
	private String socaitykey;
	private AlertDialog.Builder builder;
	private OutSocaityRequest outSocaityRequest;
	private AlertDialog	alert;

	private String gonghuikey;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_personal_data_settings);	
		init();
		key=AlUApplication.getMyInfo().getKey();

		socaitykey=AlUApplication.getMyInfo().getSocietykey();


		Log.e("socaitykey",""+socaitykey);
		Log.e("KEY", ""+key+socaitykey);

		username_et.setText(AlUApplication.getMyInfo().getUsername());
		realname_et.setText(AlUApplication.getMyInfo().getRealname());
		age_et.setText(String.valueOf(AlUApplication.getMyInfo().getAge()));
		locus_et.setText(AlUApplication.getMyInfo().getLocus());
		app_text.setText(String.valueOf(AlUApplication.getMyInfo().getId()));
		jifen_text.setText(String.valueOf(AlUApplication.getMyInfo().getJifen()));
		resident();

		builder= new AlertDialog.Builder(PersonalDataSettingsActivity.this);

		apply_bt.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if(apply_bt.getText().toString()=="退出"){
					alert=tishi();
				 alert.show();
				 AlUApplication.getMyInfo().setHuanxinid(null);
				}else{
					if("null".equals(socaitykey)){

						Toast.makeText(PersonalDataSettingsActivity.this, "亲，未绑定公会，请先绑定！", Toast.LENGTH_SHORT).show();
					}else{

						startActivity(new Intent(PersonalDataSettingsActivity.this,SociatyDataActivity.class));
					}
				}

			}
		});
		
	}


	public void init(){
		username_et=(EditText) findViewById(R.id.usename_edittext);
		realname_et=(EditText) findViewById(R.id.realname_edittext);
		age_et=(EditText) findViewById(R.id.age_edittext);
		locus_et=(EditText) findViewById(R.id.address_edittext);
		app_text=(TextView) findViewById(R.id.appid_textview);
		jifen_text=(TextView) findViewById(R.id.jifen_textview);
		save_bt=(Button) findViewById(R.id.save_bt);
		apply_bt=(Button) findViewById(R.id.bangding_bt);
		sociaty_name=(TextView) findViewById(R.id.sociaty_name_textview);
	}
	/**
	 * 跳转申请界面
	 */
	





	

	/**
	 * 保存
	 */
	public void save(View view){

		// 执行耗时操作  
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

					Log.i("FLAG",""+flag);
					Log.i("INFO",""+ info);
					if("true".equals(flag)){
						Toast.makeText(PersonalDataSettingsActivity.this,""+ info,Toast.LENGTH_LONG).show();

					}else{

						Toast.makeText(PersonalDataSettingsActivity.this, ""+info,Toast.LENGTH_LONG).show();
					}
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		AlUApplication.getMyInfo().setRealname(realname_et.getText().toString());
		AlUApplication.getMyInfo().setLocus(locus_et.getText().toString());
		AlUApplication.getMyInfo().setAge(Integer.parseInt(age_et.getText().toString()));
		AlUApplication.getMyInfo().setUsername(username_et.getText().toString());

	}

	public void resident(){
		Log.e("!!!!resident!!!!!!", "**********");
		userKeyRequest= new UserKeyRequest();
		userKeyRequest.setUserKey(key);
		residentsociaties= new ArrayList<Sociaty>();
		NetManager.execute(NetManager.RESIDENT_ASSOCIATION_REQUEST_OPERATION, userKeyRequest, new JsonHttpResponseHandler(){
			@Override
			public void onSuccess(int statusCode, Header[] headers,
					JSONArray response) {
				// TODO Auto-generated method stub
				super.onSuccess(statusCode, headers, response);
				try {
					getPopularJsonObj(response,residentsociaties);
					
					Log.e("popularsociaties",""+ residentsociaties.toString());
					if(residentsociaties.size()==0){
						sociaty_name.setText("");
						apply_bt.setText("申请");
					}else{
						sociaty_name.setText(residentsociaties.get(0).getSocietyname());
						gonghuikey=residentsociaties.get(0).getKey();
						apply_bt.setText("退出");
					}
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}
	//遍历jsonarray
	public void getPopularJsonObj(JSONArray array,ArrayList<Sociaty> sociaties ) throws JSONException{

		SociatyResponse soc= new SociatyResponse();
		for(int i=0;i<array.length();i++){
			JSONObject jsonObject= new JSONObject();
			jsonObject= array.getJSONObject(i);
			soc.setJsonObject(jsonObject);
			Sociaty sociaty= new Sociaty();
			sociaty.setId(soc.getId());
			sociaty.setJifen(soc.getJifen());
			sociaty.setKey(soc.getKey());
			sociaty.setSocietyname(soc.getSocietyName());
			sociaty.setSocietysummary(soc.getSocietySummary());
			sociaties.add(sociaty);
		}
	}

	/**
	 *退出公会并解绑
	 * 
	 */
	public void out(){
		outSocaityRequest=new OutSocaityRequest();
		outSocaityRequest.setGonghuikey(gonghuikey);
		outSocaityRequest.setUserKey(key);
		NetManager.execute(NetManager.OUT_SOCIATY_REQUEST_OPERATION, outSocaityRequest, new JsonHttpResponseHandler(){
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

					Log.i("FLAG",""+flag);
					Log.i("INFO",""+ info);
					if("true".equals(flag)){
						Toast.makeText(PersonalDataSettingsActivity.this,""+ info,Toast.LENGTH_LONG).show();

					}else{

						Toast.makeText(PersonalDataSettingsActivity.this, ""+info,Toast.LENGTH_LONG).show();
					}
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});




	}
/**
 * 提示退出
 */
	public AlertDialog tishi(){
		
		builder.setTitle("提示")
		.setMessage("你确定要退出公会?")
		.setCancelable(false)  
		.setPositiveButton("确定", new DialogInterface.OnClickListener() {  
			public void onClick(DialogInterface dialog, int id) {  
				out();
				dialog.dismiss();
			}  
		})  
		.setNegativeButton("取消", new DialogInterface.OnClickListener() {  
			public void onClick(DialogInterface dialog, int id) {  
				dialog.dismiss();  
			}  
		});  

		AlertDialog	alert1=builder.create(); 
		return alert1;
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
