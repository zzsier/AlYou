package com.imalu.alyou.activity;

import java.util.ArrayList;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.imalu.alyou.AlUApplication;
import com.imalu.alyou.R;
import com.imalu.alyou.adapter.SociatylistFragmentAdapter;
import com.imalu.alyou.domain.Sociaty;
import com.imalu.alyou.net.JsonHttpResponseHandler;
import com.imalu.alyou.net.NetManager;
import com.imalu.alyou.net.request.AssociationSearchRequest;
import com.imalu.alyou.net.response.AssociationSearchResponse;

public class SearchResultsActivity extends BaseActivity{
	private String AssociationName;
	private ArrayList<Sociaty> sociatys;
	private  ListView listView;
	private String SocietyName;
	private String SocietySummary;
	private String SocietyKey;
	private String Key;
	private int Id;
	private int JiFen;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_searchresults);
		Intent intent = getIntent();
		AssociationName =  intent.getStringExtra("AssociationName");
		Log.e("AssociationName...................", ""+AssociationName);
		listView=(ListView)findViewById(R.id.resultslV);
		//Log.e("Association",""+sociatys.toString());
		AssociationFound();
		SociatylistFragmentAdapter adapter=new SociatylistFragmentAdapter(this, sociatys);
		listView.setAdapter(adapter);
		setListener();
		}
	private void setListener() {
		// TODO Auto-generated method stub
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(
						SearchResultsActivity.this, 
						AssocationDataActivity.class);
				intent.putExtra("Id", Id);
				intent.putExtra("SocietyName", SocietyName);
				intent.putExtra("SocietySummary", SocietySummary);
				intent.putExtra("SocietyKey", SocietyKey);
				intent.putExtra("Key", Key);
				intent.putExtra("JiFen", JiFen) ;
				startActivity(intent);
				finish();
			}
		});
	}
	//公会资料查询
		protected void AssociationFound() {
			// TODO Auto-generated method stub
			sociatys = new ArrayList<Sociaty>();
			AssociationSearchRequest associationSearchReq = new AssociationSearchRequest();
			associationSearchReq.setSocietyName(AssociationName);
			 NetManager.execute(NetManager.ASSOCIATION_SEARCH, associationSearchReq, new JsonHttpResponseHandler(){
				 @Override
				public void onSuccess(int statusCode, Header[] headers,
						JSONArray response) {
					// TODO Auto-generated method stub
					super.onSuccess(statusCode, headers, response);
					try {
						Log.e("Json.....................", ""+sociatys.toString());
						getJsonObj(response);
					} catch (Exception e) {
						// TODO: handle exception
					}
				}
			 });
		}
		//遍历Json数组
		protected void getJsonObj(JSONArray array)throws JSONException{
			// TODO Auto-generated method stub
			AssociationSearchResponse  associationSearchResp = new AssociationSearchResponse();
			for(int i=0;i<array.length();i++){
				JSONObject Obj = new JSONObject();
				Obj = array.getJSONObject(i);
				Sociaty  sociaty= new Sociaty();
				associationSearchResp.setJsonObject(Obj);
				sociaty.setJifen(associationSearchResp.getJifen());
				sociaty.setSocietyname (associationSearchResp.getSocietyName());
				sociaty.setSocietysummary (associationSearchResp.getSocietySummary());
				 sociatys.add(sociaty);
				 SocietyName = associationSearchResp.getSocietyName();
				 SocietySummary=associationSearchResp.getSocietySummary();
				 SocietyKey=AlUApplication.getMyInfo().getSocietykey();
				 Key=associationSearchResp.getKey();
				 Id=associationSearchResp.getId();
				 JiFen=associationSearchResp.getJifen();
			}
			Log.e("Json...........................", ""+sociatys.toString());		
		}
}
