package com.imalu.alyou.activity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.imalu.alyou.AlUApplication;
import com.imalu.alyou.R;

public class PersonInfoActivity extends BaseActivity{
	private Button btBack;
	private Button btSendMsg;
	private Button btGuanzhu;
	private TextView tvUserName;
	private TextView tvApp;
	private TextView tvJiFen;
	private TextView tvGongHui;
	private TextView tvGame1;
	private TextView tvGame2;
	private ImageView imgHeadImg;
	private ImageView imgEnter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_personal_data);
		initView();
		setListener();
		String app_id=String.valueOf(AlUApplication.getUserfans().getId());
		String name=AlUApplication.getUserfans().getUserName();
		String gonghui=AlUApplication.getUserfans().getSocietyName();
		String jifen=String.valueOf(AlUApplication.getUserfans().getJifen());
		if(name==null){
			tvUserName.setText("未设置");
			
		}else{
			
			tvUserName.setText(name);
		}
		tvApp.setText(app_id);
		tvJiFen.setText(jifen);
		if(gonghui==null){
			tvGongHui.setText("未设置");
			
		}else{
			
			tvGongHui.setText(gonghui);
		}
		
	}
	private void setListener() {
		// TODO Auto-generated method stub
		btGuanzhu.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
			
			String  btText = btGuanzhu.getText().toString();
			if ("关注".equals(btText)) {
				btGuanzhu.setText("取消关注");
			} else if("取消关注".equals(btText)){
				btGuanzhu.setText("关注");
			}
			
			
			}
		});
	}
	private void initView() {
		 btBack=(Button) findViewById(R.id.back_bt);
		 btGuanzhu=(Button) findViewById(R.id.guanzhu_bt);
		 btSendMsg=(Button) findViewById(R.id.button1);
		 tvUserName=(TextView) findViewById(R.id.textView2);
		 tvApp=(TextView) findViewById(R.id.textView4);
		 tvJiFen=(TextView) findViewById(R.id.textView6);
		 tvGongHui=(TextView) findViewById(R.id.textView8);
		 tvGame1=(TextView) findViewById(R.id.textView12);
		 tvGame2=(TextView) findViewById(R.id.textView11);
		 imgHeadImg=(ImageView) findViewById(R.id.image_personal_data_photo);
		 imgEnter=(ImageView) findViewById(R.id.imageView3);
		
	}
	
	public void back(View view) {
		finish();
	}
	
}
