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
import com.imalu.alyou.net.response.SociatyResponse;

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
		 SocietyKey=AlUApplication.getMyInfo().getSocietykey();
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
				intent.putExtra("Id", sociatys.get(arg2).getId());
				intent.putExtra("SocietyName", sociatys.get(arg2).getSocietyname());
				intent.putExtra("SocietySummary", sociatys.get(arg2).getSocietysummary());
				intent.putExtra("SocietyKey", SocietyKey);
				intent.putExtra("Key", sociatys.get(arg2).getKey());
				intent.putExtra("JiFen", sociatys.get(arg2).getJifen()) ;
				intent.putExtra("Gonghuirenshu",sociatys.get(arg2).getGonghuirenshu());
				intent.putExtra("Type", sociatys.get(arg2).getType());
				startActivity(intent);
			//	finish();
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
			SociatyResponse  sociatyResponse = new SociatyResponse();
			for(int i=0;i<array.length();i++){
				JSONObject Obj = new JSONObject();
				Obj = array.getJSONObject(i);
				Sociaty  sociaty= new Sociaty();
				sociatyResponse.setJsonObject(Obj);
				sociaty.setJifen(sociatyResponse.getJifen());
				sociaty.setSocietyname (sociatyResponse.getSocietyName());
				sociaty.setSocietysummary (sociatyResponse.getSocietySummary());
				sociaty.setGonghuirenshu(sociatyResponse.getGonghuirenshu());
				sociaty.setId(sociatyResponse.getId());
				sociaty.setType(sociatyResponse.getSocietyType());
				sociatys.add(sociaty);
				/* SocietyName = sociatyResponse.getSocietyName();
				 SocietySummary=sociatyResponse.getSocietySummary();
				 SocietyKey=AlUApplication.getMyInfo().getSocietykey();
				 Key=sociatyResponse.getKey();
				 Id=sociatyResponse.getId();
				 JiFen=sociatyResponse.getJifen();*/
			}
			Log.e("Json...........................", ""+sociatys.toString());		
		}
}
