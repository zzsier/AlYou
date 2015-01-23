package com.imalu.alyou.activity;

import java.util.ArrayList;

import org.apache.http.Header;
import org.ice4j.message.Request;
import org.json.JSONObject;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.imalu.alyou.R;
import com.imalu.alyou.domain.Version;
import com.imalu.alyou.net.JsonHttpResponseHandler;
import com.imalu.alyou.net.NetManager;
import com.imalu.alyou.net.request.NullRequest;
import com.imalu.alyou.net.response.VersionResponse;

public class VersionUpdateActivity extends BaseActivity{
	private ArrayList<Version> versionList;
	private TextView banBenHao,dlUrl,versionDis;
	Version version;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_version_update);	
		versionList = new ArrayList<Version>();
		checkVersion();
		initViews();
	}
	private void initViews() {
		banBenHao = (TextView)findViewById(R.id.tv_banbenhao);
		dlUrl = (TextView)findViewById(R.id.tv_yingyong_address);
		versionDis = (TextView)findViewById(R.id.tv_banben_shuoming);
		if(versionList == null)
		{
			Toast.makeText(this,"当前已是最新版本", 1).show();
		}else{
			banBenHao.setText(version.getBanbenHao());
			dlUrl.setText(version.getAppURL());
			versionDis.setText(version.getBanbenShuoming());
		}
	}
	public void checkVersion()
	{
		NullRequest	nullRequest = new NullRequest();
		NetManager.execute(NetManager.VERSION_UPDATE,nullRequest, new JsonHttpResponseHandler(){
			@Override
			public void onSuccess(int statusCode, Header[] headers,
					JSONObject response) {
				// TODO Auto-generated method stub
				super.onSuccess(statusCode, headers, response);
				VersionResponse versionResponse = new VersionResponse();
				versionResponse.setJsonObject(response);
				version = new Version();
				version.setBanbenHao(versionResponse.getBanbenHao());
				version.setAppURL(versionResponse.getAppURL());
				version.setBanbenShuoming(versionResponse.getBanbenShuoming());
				versionList.add(version);
			}
		});
	}
	public void back(View view) {
		finish();
	}
}
