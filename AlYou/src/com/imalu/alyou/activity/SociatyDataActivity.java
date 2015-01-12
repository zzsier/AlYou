package com.imalu.alyou.activity;

import java.util.ArrayList;

import org.apache.http.Header;
import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.easemob.chat.EMGroupManager;
import com.easemob.exceptions.EaseMobException;
import com.imalu.alyou.AlUApplication;
import com.imalu.alyou.R;
import com.imalu.alyou.domain.Sociaty;
import com.imalu.alyou.net.JsonHttpResponseHandler;
import com.imalu.alyou.net.NetManager;
import com.imalu.alyou.net.request.GetSocaityRequest;
import com.imalu.alyou.net.response.SociatyResponse;

public class SociatyDataActivity  extends BaseActivity{
	private String socaitykey;
	private GetSocaityRequest getSocaityRequest;
	private ArrayList<Sociaty> bindingsociaties;
	private Sociaty sociaty;
	private TextView sociatyName;
	private TextView sociatyId;
	private TextView sociatyJifen;
	private TextView sociatySummary;
	private TextView sociatyType;
	private TextView sociatyTotal;
	private Button join;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sociaty_data);
		init();
		Log.e("++++", "aaaaaaaaaaa");
		socaitykey=AlUApplication.getMyInfo().getSocietykey();
		Log.e("++++",""+ socaitykey);
		getSocaityRequest= new GetSocaityRequest();
		getSocaityRequest.setSocietyKey(socaitykey);
		Binding();
		
	}

	public void init(){
		sociatyName=(TextView) findViewById(R.id.sociatyname_text);
		sociatyId=(TextView) findViewById(R.id.sociaty_id_text);
		sociatyJifen=(TextView) findViewById(R.id.jifen_text);
		sociatySummary=(TextView) findViewById(R.id.jianjie_text);
		sociatyType=(TextView) findViewById(R.id.sociaty_type_text);
		sociatyTotal=(TextView) findViewById(R.id.sociaty_total_text);
		join=(Button) findViewById(R.id.join_bt);
	}

	public void  Binding(){

		NetManager.execute(NetManager.BINDING_ASSOCIATION_REQUEST_OPERATION, 
				getSocaityRequest, new JsonHttpResponseHandler(){
			@Override
			public void onSuccess(int statusCode, Header[] headers,
					JSONObject response) {
				// TODO Auto-generated method stub
				super.onSuccess(statusCode, headers, response);
				SociatyResponse soc= new SociatyResponse();
				Log.e("response", response.toString());
				soc.setJsonObject(response);
				sociaty= new Sociaty();
				sociaty.setId(soc.getId());
				sociaty.setJifen(soc.getJifen());
				sociaty.setKey(soc.getKey());
				sociaty.setSocietyname(soc.getSocietyName());
				sociaty.setSocietysummary(soc.getSocietySummary());
				sociaty.setTotal(soc.getTotal());
				sociaty.setSocietytype(soc.getSocietyType());
				sociatyName.setText(""+sociaty.getSocietyname());
				sociatyId.setText(String.valueOf(sociaty.getId()));
				sociatyJifen.setText(""+String.valueOf(sociaty.getJifen()));
				sociatySummary.setText(""+sociaty.getSocietysummary());
				sociatyType.setText(sociaty.getSocietytype()==1?"英雄联盟":"魔兽世界");
				sociatyTotal.setText(String.valueOf(sociaty.getTotal()));
			}
		});
	}

/**
 * 加入
 */
public void join(View view){
	
	
	
	
	
}
/**
 * 跳转到捐助界面
 */
public void next(View view){
	switch (view.getId()) {
	case R.id.join_bt:
		startActivity(new Intent(SociatyDataActivity.this,AuthenticationActivity.class));
		break;
	case R.id.juanzhu_bt:
		startActivity(new Intent(SociatyDataActivity.this,DonorRecordsActivity.class));
		break;

	}
}





}



