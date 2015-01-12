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

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.imalu.alyou.AlUApplication;
import com.imalu.alyou.R;
import com.imalu.alyou.adapter.SociatylistMainFragmentAdapter;
import com.imalu.alyou.domain.Sociaty;
import com.imalu.alyou.net.JsonHttpResponseHandler;
import com.imalu.alyou.net.NetManager;
import com.imalu.alyou.net.request.ConcernSocaityRequest;
import com.imalu.alyou.net.request.GetSocaityRequest;
import com.imalu.alyou.net.request.UserKeyRequest;
import com.imalu.alyou.net.response.SociatyResponse;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;



/**
 * 通讯录------公会列表
 * 
 */
public class ConsortialistFragment extends Fragment {
	private ArrayList<Sociaty> popularsociaties;
	private ArrayList<Sociaty> concernsociaties;
	private ArrayList<Sociaty> bindingsociaties;
	private ListView popularlist;
	private ListView concernlist;
	private ListView bindinglist;
	private String key;
	private String socaitykey;
	private UserKeyRequest keyRequest;
	private ConcernSocaityRequest   socaityRequest;
	private GetSocaityRequest getSocaityRequest;
	private Sociaty  sociaty1;
	private Sociaty  sociaty2;
	private Sociaty  sociaty3;
	private Sociaty  sociaty4;
	private Button search_bt;
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		return inflater.inflate(R.layout.contact_consortia_list, container, false);
	}
	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		key=AlUApplication.getMyInfo().getKey();
		socaitykey=AlUApplication.getMyInfo().getSocietykey();
		Log.e("socaitykey", ""+socaitykey);

		keyRequest= new UserKeyRequest();
		socaityRequest=new ConcernSocaityRequest();
		getSocaityRequest= new GetSocaityRequest();

		getSocaityRequest.setSocietyKey(socaitykey);
		socaityRequest.setYonghuKey(key);
		keyRequest.setUserKey(key);


		search_bt = (Button) getActivity().findViewById(R.id.search_bt);
		popularlist=(ListView) getActivity().findViewById(R.id.popular_association_list);
		//	concernlist=(ListView) getActivity().findViewById(R.id.concerned_association_list);
		//bindinglist=(ListView) getActivity().findViewById(R.id.binding_association_list);

		Popular();
		//	Concern();
		//	Binding();

		Log.e("--------","xxxxxxxxx");
		SociatylistMainFragmentAdapter adapter= new SociatylistMainFragmentAdapter(getActivity(), popularsociaties);
		popularlist.setAdapter(adapter);

		//SociatylistFragmentAdapter adapter2= new SociatylistFragmentAdapter(getActivity(), concernsociaties);
		//	concernlist.setAdapter(adapter2);

		/*SociatylistFragmentAdapter adapter3= new SociatylistFragmentAdapter(getActivity(), bindingsociaties);
		bindinglist.setAdapter(adapter3);*/

		//给listview设置监听事件
		setListviewListener();


	}



	//设置监听事件
	private void setListviewListener() {
		// TODO Auto-generated method stub
		popularlist.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				Toast.makeText(getActivity(),""+popularsociaties.get(arg2).getType() , Toast.LENGTH_SHORT).show();
				Intent intent = new Intent();
				intent.setClass(
						getActivity(), 
						AssocationDataActivity.class);
				intent.putExtra("Id", popularsociaties.get(arg2).getId());
				intent.putExtra("SocietyName", popularsociaties.get(arg2).getSocietyname());
				intent.putExtra("SocietySummary", popularsociaties.get(arg2).getSocietysummary());
				intent.putExtra("SocietyKey", popularsociaties.get(arg2).getKey());
				intent.putExtra("Key", key);
				intent.putExtra("JiFen", popularsociaties.get(arg2).getJifen()) ;
				startActivity(intent);
			
			
			}
		});

		search_bt.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent =new Intent();
				intent.setClass(getActivity(), AssociationSearchActivity.class);
				startActivity(intent);

			}
		});

	}
	//热门公会请求
	public void  Popular(){

		popularsociaties= new ArrayList<Sociaty>();
		NetManager.execute(NetManager.POPULAR_ASSOCIATION_REQUEST_OPERATION, keyRequest, new JsonHttpResponseHandler(){
			@Override
			public void onSuccess(int statusCode, Header[] headers,
					JSONArray response) {
				// TODO Auto-generated method stub
				super.onSuccess(statusCode, headers, response);
				try {
					getPopularJsonObj(response,popularsociaties);
					Log.e("popularsociaties",""+ popularsociaties.toString());
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});


		/*	concernsociaties= new ArrayList<Sociaty>();
		NetManager.execute(NetManager.CONCERN_ASSOCIATION_REQUEST_OPERATION, keyRequest, new JsonHttpResponseHandler(){
			@Override
			public void onSuccess(int statusCode, Header[] headers,
					JSONArray response) {
				// TODO Auto-generated method stub
				super.onSuccess(statusCode, headers, response);
				try {
					getPopularJsonObj(response,concernsociaties);
					Log.e("concernsociaties",""+ concernsociaties.toString());
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});*/

	}



	//关注公会请求
	public void  Concern(){
		Log.e("++++", "aaaaaaaaaaa");
		concernsociaties= new ArrayList<Sociaty>();
		NetManager.execute(NetManager.CONCERN_ASSOCIATION_REQUEST_OPERATION, socaityRequest, new JsonHttpResponseHandler(){
			@Override
			public void onSuccess(int statusCode, Header[] headers,
					JSONArray response) {
				// TODO Auto-generated method stub
				super.onSuccess(statusCode, headers, response);
				try {
					getPopularJsonObj(response,concernsociaties);
					Log.e("concernsociaties",""+ concernsociaties.toString());
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}
	//绑定公会请求
	public void  Binding(){
		Log.e("++++", "aaaaaaaaaaa");

		bindingsociaties= new ArrayList<Sociaty>();
		NetManager.execute(NetManager.BINDING_ASSOCIATION_REQUEST_OPERATION, getSocaityRequest, new JsonHttpResponseHandler(){
			@Override
			public void onSuccess(int statusCode, Header[] headers,
					JSONObject response) {
				// TODO Auto-generated method stub
				super.onSuccess(statusCode, headers, response);
				SociatyResponse soc= new SociatyResponse();
				soc.setJsonObject(response);
				Sociaty sociaty= new Sociaty();
				sociaty.setId(soc.getId());
				sociaty.setJifen(soc.getJifen());
				sociaty.setKey(soc.getKey());
				sociaty.setSocietyname(soc.getSocietyName());
				sociaty.setSocietysummary(soc.getSocietySummary());
				Log.i("sociaty", ""+sociaty.toString());
				bindingsociaties.add(sociaty);
				Log.e("bindingsociaties",""+ bindingsociaties.toString());
			}
		});
	}
	//遍历jsonarray
	/*	public void getPopularJsonObj(JSONArray array,ArrayList<Sociaty> sociaties ) throws JSONException{

		SociatyResponse soc= new SociatyResponse();
		for(int i=0;i<array.length();i++){
			JSONObject jsonObject= new JSONObject();
			jsonObject= array.getJSONObject(i);
			soc.setJsonObject(jsonObject);
			Sociaty sociaty= new Sociaty();
			sociaty.setId(soc.getId());
			sociaty.setType(soc.getType());
			sociaty.setJifen(soc.getJifen());
			sociaty.setKey(soc.getKey());
			sociaty.setSocietyname(soc.getSocietyName());
			sociaty.setSocietysummary(soc.getSocietySummary());
			sociaties.add(sociaty);
		}

	}
	 */


	//遍历jsonarray
	public void getPopularJsonObj(JSONArray array,ArrayList<Sociaty> sociaties ) throws JSONException{

		SociatyResponse soc= new SociatyResponse();
		for(int i=0;i<array.length();i++){
			JSONObject jsonObject= new JSONObject();
			jsonObject= array.getJSONObject(i);
			soc.setJsonObject(jsonObject);

			if(soc.getType()==1){
				if(sociaty1==null){

					sociaty1= new Sociaty();
					sociaty1.setType(5);
					sociaty1.setSocietyname("加入公会");
					sociaties.add(sociaty1);
					/*	Sociaty sociaty= new Sociaty();
					sociaty.setId(soc.getId());
					sociaty.setType(soc.getType());
					sociaty.setJifen(soc.getJifen());
					sociaty.setKey(soc.getKey());
					sociaty.setSocietyname(soc.getSocietyName());
					sociaty.setSocietysummary(soc.getSocietySummary());
					sociaties.add(sociaty);*/
					addSociaty(sociaties, soc);
				}else{

					/*Sociaty sociaty= new Sociaty();
						sociaty.setId(soc.getId());
						sociaty.setType(soc.getType());
						sociaty.setJifen(soc.getJifen());
						sociaty.setKey(soc.getKey());
						sociaty.setSocietyname(soc.getSocietyName());
						sociaty.setSocietysummary(soc.getSocietySummary());
						sociaties.add(sociaty);*/
					addSociaty(sociaties, soc);

				}



			}else if(soc.getType()==2){

				if(sociaty2==null){
					sociaty2= new Sociaty();
					sociaty2.setType(6);
					sociaty2.setSocietyname("绑定公会");
					sociaties.add(sociaty2);
					Sociaty sociaty= new Sociaty();
					sociaty.setId(soc.getId());
					sociaty.setType(soc.getType());
					sociaty.setJifen(soc.getJifen());
					sociaty.setKey(soc.getKey());
					sociaty.setSocietyname(soc.getSocietyName());
					sociaty.setSocietysummary(soc.getSocietySummary());
					AlUApplication.getMyInfo().setHuanxinid(soc.getHuanxinID());
					sociaties.add(sociaty);

					//	addSociaty(sociaties, soc);
				}else{

					Sociaty sociaty= new Sociaty();
					sociaty.setId(soc.getId());
					sociaty.setType(soc.getType());
					sociaty.setJifen(soc.getJifen());
					sociaty.setKey(soc.getKey());
					sociaty.setSocietyname(soc.getSocietyName());
					sociaty.setSocietysummary(soc.getSocietySummary());
					AlUApplication.getMyInfo().setHuanxinid(soc.getHuanxinID());
					sociaties.add(sociaty);

					//	addSociaty(sociaties, soc);
				}


			}else if(soc.getType()==3){
				if(sociaty3==null){
					sociaty3= new Sociaty();
					sociaty3.setType(7);
					sociaty3.setSocietyname("关注公会");
					sociaties.add(sociaty3);
					/*Sociaty sociaty= new Sociaty();
					sociaty.setId(soc.getId());
					sociaty.setType(soc.getType());
					sociaty.setJifen(soc.getJifen());
					sociaty.setKey(soc.getKey());
					sociaty.setSocietyname(soc.getSocietyName());
					sociaty.setSocietysummary(soc.getSocietySummary());
					sociaties.add(sociaty);*/
					addSociaty(sociaties, soc);
				}else{

					/*	Sociaty sociaty= new Sociaty();
						sociaty.setId(soc.getId());
						sociaty.setType(soc.getType());
						sociaty.setJifen(soc.getJifen());
						sociaty.setKey(soc.getKey());
						sociaty.setSocietyname(soc.getSocietyName());
						sociaty.setSocietysummary(soc.getSocietySummary());
						sociaties.add(sociaty);*/
					addSociaty(sociaties, soc);

				}

			}else if(soc.getType()==4){
				if(sociaty4==null){
					sociaty4= new Sociaty();
					sociaty4.setType(8);
					sociaty4.setSocietyname("热门公会");
					sociaties.add(sociaty4);
					/*Sociaty sociaty= new Sociaty();
					sociaty.setId(soc.getId());
					sociaty.setType(soc.getType());
					sociaty.setJifen(soc.getJifen());
					sociaty.setKey(soc.getKey());
					sociaty.setSocietyname(soc.getSocietyName());
					sociaty.setSocietysummary(soc.getSocietySummary());
					sociaties.add(sociaty);*/
					addSociaty(sociaties, soc);
				}else{

					/*Sociaty sociaty= new Sociaty();
						sociaty.setId(soc.getId());
						sociaty.setType(soc.getType());
						sociaty.setJifen(soc.getJifen());
						sociaty.setKey(soc.getKey());
						sociaty.setSocietyname(soc.getSocietyName());
						sociaty.setSocietysummary(soc.getSocietySummary());
						sociaties.add(sociaty);*/
					addSociaty(sociaties, soc);

				}


			}
		}

	}

	public void addSociaty(ArrayList<Sociaty> sociaties,SociatyResponse soc){
		Sociaty sociaty= new Sociaty();
		sociaty.setId(soc.getId());
		sociaty.setType(soc.getType());
		sociaty.setJifen(soc.getJifen());
		sociaty.setKey(soc.getKey());
		sociaty.setSocietyname(soc.getSocietyName());
		sociaty.setSocietysummary(soc.getSocietySummary());
		sociaty.setHuanxinid(soc.getHuanxinID());
		sociaties.add(sociaty);
	}



	@Override
	public void onSaveInstanceState(Bundle outState) {
		if(((MainActivity)getActivity()).isConflict)
			outState.putBoolean("isConflict", true);
		super.onSaveInstanceState(outState);

	}
}
