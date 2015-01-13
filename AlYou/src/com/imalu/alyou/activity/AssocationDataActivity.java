package com.imalu.alyou.activity;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.imalu.alyou.AlUApplication;
import com.imalu.alyou.R;
import com.imalu.alyou.net.JsonHttpResponseHandler;
import com.imalu.alyou.net.NetManager;
import com.imalu.alyou.net.request.BindGonghuiRequest;
import com.imalu.alyou.net.request.CareaboutSocietyRequest;
import com.imalu.alyou.net.response.BingGonghuiResponse;
import com.imalu.alyou.net.response.CareaboutSocietyResponse;

public class AssocationDataActivity  extends BaseActivity{
	
	private TextView SocietyNametv2;
	private TextView Idtv;
	private TextView JiFentv;
	private TextView SocietySummary_text;
	private TextView Gonghuirenshu;
	private String SocietyName;
	private String SocietySummary;
	private String SocietyKey;
	private String Key;
	private int Id;
	private Button bangdingbt;
	private Button guanzhubt;
	private int JiFen;
	private Boolean flag;
	private String info;
	 private Intent intent;
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_assocationdata);
		//getValue(); 
		setView();
		setText();
		setListener();
		}
	private void setText() {
		// TODO Auto-generated method stub
		intent  =getIntent();
		SocietyNametv2.setText(intent.getStringExtra("SocietyName"));
		Idtv.setText(String.valueOf(intent.getIntExtra("Id", 0)));
		JiFentv.setText(String.valueOf(intent.getIntExtra("JiFen", 0)));
		Gonghuirenshu.setText(String.valueOf(intent.getIntExtra("Gonghuirenshu", 0)));
		SocietySummary_text.setText(intent.getStringExtra("SocietySummary"));
		
		if(intent.getIntExtra("Type", 0)==1||intent.getIntExtra("Type", 0)==2){
			bangdingbt.setText("取消绑定");
		}else{
			
			
			
		}
	}
/*	private void getValue() {
		// TODO Auto-generated method stub
		
		SocietyName  = intent.getStringExtra("SocietyName");
		//Log.i("name..................", ""+SocietyName);	
		SocietySummary  = intent.getStringExtra("SocietySummary");
		//Log.i("Summary..................", ""+SocietySummary);		
		SocietyKey  = intent.getStringExtra("SocietyKey");
		//Log.i("SKey..................", ""+SocietyKey);	
		Key  = intent.getStringExtra("Key");
		//Log.i("Key..................", ""+Key);	
		Id  = intent.getIntExtra("Id", 1);
		//Log.i("Id..................", ""+Id);	
		JiFen= intent.getIntExtra("JiFen", 1);
		//Log.i("JiFen..................", ""+JiFen);
	   
	}*/
	private void setView() {
		// TODO Auto-generated method stub
		SocietyNametv2 =  (TextView) findViewById(R.id.SocietyNametv2);
		Idtv = (TextView) findViewById(R.id.Idtv);
		JiFentv = (TextView) findViewById(R.id.JiFentv);
		SocietySummary_text=(TextView) findViewById(R.id.sociaty_summary_text);
		Gonghuirenshu=(TextView) findViewById(R.id.sociaty_total_textview);
		guanzhubt=(Button) findViewById(R.id.guanzhubt);
		bangdingbt=(Button) findViewById(R.id.bangdingbt);
	}
	private void BindSociaty(){
		BindGonghuiRequest bindGonghuiReq = new BindGonghuiRequest();
		bindGonghuiReq.setUID(AlUApplication.getMyInfo().getPhoneNum());
		bindGonghuiReq.setSocietyKey(intent.getStringExtra("Key"));
		AlUApplication.getMyInfo().setSocietykey(intent.getStringExtra("Key"));
		  NetManager.execute(NetManager.BINDSOCIETY,
				  bindGonghuiReq,new JsonHttpResponseHandler(){
			  @Override
			public void onSuccess(int statusCode, Header[] headers,
					JSONObject response) {
				// TODO Auto-generated method stub
				super.onSuccess(statusCode, headers, response);
			BingGonghuiResponse bingGonghuiResp=new BingGonghuiResponse();
			bingGonghuiResp.setJsonObject(response);
			try {
				flag = bingGonghuiResp.getCode();
				info = bingGonghuiResp.getInfo();
				log();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			  }
			  
		  });
		 
	}
	private void quxiaoBindSociaty(){
		BindGonghuiRequest bindGonghuiReq = new BindGonghuiRequest();
		bindGonghuiReq.setUID(AlUApplication.getMyInfo().getPhoneNum());
		bindGonghuiReq.setSocietyKey("");
		  NetManager.execute(NetManager.BINDSOCIETY,
				  bindGonghuiReq,new JsonHttpResponseHandler(){
			  @Override
			public void onSuccess(int statusCode, Header[] headers,
					JSONObject response) {
				// TODO Auto-generated method stub
				super.onSuccess(statusCode, headers, response);
			BingGonghuiResponse bingGonghuiResp=new BingGonghuiResponse();
			bingGonghuiResp.setJsonObject(response);
			AlUApplication.getMyInfo().setSocietykey(null);
			try {
				flag = bingGonghuiResp.getCode();
				info = bingGonghuiResp.getInfo();
				log();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			  }
		  });
		 
	}
	protected void log() {
		// TODO Auto-generated method stub
		Log.i("FLAG---------------",""+flag);
		Log.i("INFO---------------",""+ info);
	}
	private void setListener() {
		guanzhubt.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				String	s = guanzhubt.getText().toString();
				Log.i(" s..................", ""+s);
					if ("关注".equals(s)) {
					guanzhubt.setText("取消关注");
					gaunzhu();
					 
					}else if("取消关注".equals( s)) {	
						guanzhubt.setText("关注");
						quxiaoguanzhu();
					}
			}
		});
		
		// TODO Auto-generated method stub
		bangdingbt.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
			String	tos = bangdingbt.getText().toString();
			Log.i("tos..................", ""+tos);
				if ("绑定公会".equals(tos)) {
				bangdingbt.setText("取消绑定");
				BindSociaty();
				
				}else if("取消绑定".equals(tos)) {	
					bangdingbt.setText("绑定公会");
					quxiaoBindSociaty();
				
				}
			}
		});
	
	}
	protected void quxiaoguanzhu() {
		// TODO Auto-generated method stub
		CareaboutSocietyRequest careaboutSocietyReq = new CareaboutSocietyRequest();
		careaboutSocietyReq.setGuanzhuzekey(AlUApplication.getMyInfo().getKey());
		Log.i(" Guanzhuzekey..................", ""+AlUApplication.getMyInfo().getKey());
		careaboutSocietyReq.setGonghuikey(Key);
		Log.i(" Gonghuikey..................", ""+Key);
		  NetManager.execute(NetManager.CANCEL_CARE_ABOUT_CONCERN_SOCIETY,
				  careaboutSocietyReq,new JsonHttpResponseHandler(){
			  @Override
			public void onSuccess(int statusCode, Header[] headers,
					JSONObject response) {
				// TODO Auto-generated method stub
				super.onSuccess(statusCode, headers, response);
				CareaboutSocietyResponse careaboutSocietyResp = new CareaboutSocietyResponse();
				careaboutSocietyResp.setJsonObject(response);
			try {
				flag = careaboutSocietyResp.getCode();
				info = careaboutSocietyResp.getInfo();
				log();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			  }
		  });
	}
	protected void gaunzhu() {
		// TODO Auto-generated method stub
		CareaboutSocietyRequest careaboutSocietyReq = new CareaboutSocietyRequest();
		careaboutSocietyReq.setGuanzhuzekey(AlUApplication.getMyInfo().getKey());
		careaboutSocietyReq.setGonghuikey(Key);
		  NetManager.execute(NetManager.CARE_ABOUT_CONCERN_SOCIETY,
				  careaboutSocietyReq,new JsonHttpResponseHandler(){
			  @Override
			public void onSuccess(int statusCode, Header[] headers,
					JSONObject response) {
				// TODO Auto-generated method stub
				super.onSuccess(statusCode, headers, response);
				CareaboutSocietyResponse careaboutSocietyResp = new CareaboutSocietyResponse();
				careaboutSocietyResp.setJsonObject(response);
			try {
				flag = careaboutSocietyResp.getCode();
				info = careaboutSocietyResp.getInfo();
				log();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			  }
		  });
		
	}
}
