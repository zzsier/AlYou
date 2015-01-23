package com.imalu.alyou.activity;

import org.apache.http.Header;
import org.json.JSONObject;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.imalu.alyou.AlUApplication;
import com.imalu.alyou.R;
import com.imalu.alyou.net.JsonHttpResponseHandler;
import com.imalu.alyou.net.NetManager;
import com.imalu.alyou.net.request.AdviceRequest;
import com.imalu.alyou.net.response.AdviceResponse;

public class AdviceActivity extends BaseActivity{
	private TextView tvSubmit;
	private EditText etAdvice;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_advice);	
		etAdvice = (EditText)findViewById(R.id.tv_advice);
		tvSubmit = (TextView)findViewById(R.id.advice_tv_submit);
		tvSubmit.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				String content = etAdvice.getText().toString();
				if(content != null)
				{
					AdviceRequest adviceRequest = new AdviceRequest();
					adviceRequest.setUserKey(AlUApplication.getMyInfo().getKey());
					adviceRequest.setPhone(AlUApplication.getMyInfo().getPhoneNum());
					adviceRequest.setPingjiaNeirong(content);
					NetManager.execute(NetManager.ADVICE, adviceRequest, new JsonHttpResponseHandler(){
						@Override
						public void onSuccess(int statusCode, Header[] headers,
								JSONObject response) {
							// TODO Auto-generated method stub
							super.onSuccess(statusCode, headers, response);
							AdviceResponse adviceResponse = new AdviceResponse();
							adviceResponse.setJsonObject(response);
							Toast.makeText(AdviceActivity.this, "留言成功", 1).show();
							
						}
					});
				}
				etAdvice.setText("");
			}
		});
	}
}
