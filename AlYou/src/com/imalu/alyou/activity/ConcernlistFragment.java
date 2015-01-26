/**
 * Copyright (C) 2013-2014 EaseMob Technologies. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *     http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.imalu.alyou.activity;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.imalu.alyou.AlUApplication;
import com.imalu.alyou.R;

import com.imalu.alyou.adapter.ConsernlistFragemntAdapter;
import com.imalu.alyou.domain.ConsernPerson;
import com.imalu.alyou.net.JsonHttpResponseHandler;
import com.imalu.alyou.net.NetManager;
import com.imalu.alyou.net.request.ConcernRequest;
import com.imalu.alyou.net.response.ConsernResponse;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;



/**
 * 通讯录------关注列表
 * 
 */
public class ConcernlistFragment extends Fragment {
	private ArrayList<ConsernPerson> concerns;
	private  ListView listView;



	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		return inflater.inflate(R.layout.contact_concern_list, container, false);
	}
	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		listView=(ListView) getActivity().findViewById(R.id.consern_listview);
		ConcernFound();
		Log.e("CONCERN",""+concerns.toString());
	ConsernlistFragemntAdapter adapter= new ConsernlistFragemntAdapter(getActivity(), concerns);
	listView.setAdapter(adapter);
listView.setOnItemClickListener(new OnItemClickListener() {

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
		// TODO Auto-generated method stub
		Intent intent= new Intent(getActivity(), PersonInfoActivity.class);
		intent.putExtra("username",concerns.get(position).getUsername());
		intent.putExtra("jifen", concerns.get(position).getJifen());
		intent.putExtra("id", concerns.get(position).getId());
		intent.putExtra("flag", 1);
		intent.putExtra("key", concerns.get(position).getKey());
		intent.putExtra("societykey",concerns.get(position).getSocietykey() );
		startActivity(intent);
	}
});


	}

	/*
	 [{"CreatedTime":"\/Date(1418803572393+0800)\/","ID":94,"Key":"67860e5b-3afb-432f-89f1-0f1a443abce3","Age":0,"ErWeiMa":"http:\/\/114.112.98.148\/upload\/7e716648-efc5-4ce4-b136-51d28babd6e0.jpg","GameID":0,"GexingQianming":null,"HeadPicture":null,"HxName":"67860e5b3afb432f89f10f1a443abce3","Jifen":0,"Locus":null,"LuntanDengluming":"18612115693","LuntanGonghuiID":0,"PengyouquanZuihouchakanShijian":"2014-12-25 20:43:42","Pwd":"e10adc3949ba59abbe56e057f20f883e","RealName":null,"Server":null,"SetFriCheck":1,"Sex":0,"ShifouJieshouTuisong":0,"SocietyKey":"b2287b55-e8ad-4f56-80d5-4b46803cb9a1","Type":"user","UseRole":null,"UserName":null,"UserPhone":"18612115693","UserStatus":1,"Uuid":"874c77da-85c3-11e4-85c3-d1c208844308","YinsiShezhi":0,"ZuobiaoX":0.000,"ZuobiaoY":0.000,"activated":1}
	 ,{"CreatedTime":"\/Date(1418137000923+0800)\/","ID":68,"Key":"c2e27901-c214-4a80-87f2-6ab7d0662f9b","Age":0,"ErWeiMa":"http:\/\/114.112.98.148\/upload\/ae027d98-d550-4340-b169-165df705782a.jpg","GameID":0,"GexingQianming":null,"HeadPicture":null,"HxName":"c2e27901c2144a8087f26ab7d0662f9b","Jifen":0,"Locus":null,"LuntanDengluming":"1234567890123","LuntanGonghuiID":0,"PengyouquanZuihouchakanShijian":null,"Pwd":"96e79218965eb72c92a549dd5a330112","RealName":null,"Server":null,"SetFriCheck":1,"Sex":0,"ShifouJieshouTuisong":1,"SocietyKey":null,"Type":"user","UseRole":null,"UserName":null,"UserPhone":"1234567890123","UserStatus":1,"Uuid":"966236da-7fb3-11e4-b3ff-896a41105ed5","YinsiShezhi":0,"ZuobiaoX":0.000,"ZuobiaoY":0.000,"activated":1}]
	 */

	//发送请求，查询关注数据
	public void  ConcernFound(){
		String  key=AlUApplication.getMyInfo().getKey();
		concerns= new ArrayList<ConsernPerson>();
		ConcernRequest ConcernReq= new ConcernRequest();
		ConcernReq.setUserkey(key);
		NetManager.execute(NetManager.CONCERN_REQUEST_OPERATION, ConcernReq, new JsonHttpResponseHandler(){
			@Override
			public void onSuccess(int statusCode, Header[] headers,
					JSONArray response) {
				// TODO Auto-generated method stub
				super.onSuccess(statusCode, headers, response);
				try {
				getJsonObj(response);
				Log.e("AAAAAAAAA", ""+concerns.toString());
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

	}
	//遍历jsonarray
	public void getJsonObj(JSONArray array) throws JSONException{
		
		ConsernResponse con= new ConsernResponse();
		for(int i=0;i<array.length();i++){
			JSONObject jsonObject= new JSONObject();
			jsonObject= array.getJSONObject(i);
			ConsernPerson person= new ConsernPerson();
			con.setJsonObject(jsonObject);
			person.setId(con.getId());
			person.setJifen(con.getJifen());
			person.setKey(con.getKey());
			person.setUsername(con.getUserName());
			person.setSocietykey(con.getSocietyKey());
			person.setHeadpicture(con.getHeadPicture());
			concerns.add(person);
		}
		
	}
	@Override
	public void onSaveInstanceState(Bundle outState) {
		if(((MainActivity)getActivity()).isConflict)
			outState.putBoolean("isConflict", true);
		super.onSaveInstanceState(outState);
	}
}
