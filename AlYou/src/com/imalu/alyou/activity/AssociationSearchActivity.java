package com.imalu.alyou.activity;

import java.util.ArrayList;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.R.array;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.imalu.alyou.R;
 
import com.imalu.alyou.domain.UserLM;
import com.imalu.alyou.net.JsonHttpResponseHandler;
import com.imalu.alyou.net.NetManager;
import com.imalu.alyou.net.request.AssociationSearchRequest;
import com.imalu.alyou.net.response.AssociationSearchResponse;

public class AssociationSearchActivity extends BaseActivity {
	private Button bt;
	private EditText et;
	private String  AssociationName; 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_associationsearch);
		setView();
		setListener();
		}
	private void setListener() {
		// TODO Auto-generated method stub
		bt.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				AssociationName = et.getText().toString();
				Log.e("cccccccccccccccccccccccccccccccccccccc", ""+AssociationName);
				Intent  intent=  new  Intent();
				intent.setClass(
						AssociationSearchActivity.this,
						SearchResultsActivity.class);
				intent.putExtra("AssociationName", AssociationName);
				startActivity( intent);
				finish();
			}
		});
	}
 
	private void setView() {
		// TODO Auto-generated method stub
		et = (EditText) findViewById(R.id.et_souname);
		bt = (Button) findViewById(R.id.button_sousuo);
	}
}
