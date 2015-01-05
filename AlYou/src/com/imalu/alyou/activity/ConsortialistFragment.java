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
import com.imalu.alyou.adapter.SociatylistFragmentAdapter;
import com.imalu.alyou.domain.Sociaty;
import com.imalu.alyou.net.JsonHttpResponseHandler;
import com.imalu.alyou.net.NetManager;
import com.imalu.alyou.net.request.UserKeyRequest;
import com.imalu.alyou.net.response.SociatyResponse;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;



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
	private UserKeyRequest keyRequest;
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
		keyRequest= new UserKeyRequest();
		keyRequest.setUserKey(key);
		popularlist=(ListView) getActivity().findViewById(R.id.popular_association_list);
		concernlist=(ListView) getActivity().findViewById(R.id.concerned_association_list);
		Popular();
		Concern();
		SociatylistFragmentAdapter adapter= new SociatylistFragmentAdapter(getActivity(), popularsociaties);
	popularlist.setAdapter(adapter);
	SociatylistFragmentAdapter adapter2= new SociatylistFragmentAdapter(getActivity(), concernsociaties);
	concernlist.setAdapter(adapter2);
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
	}
	
	
	
	//关注公会请求
	public void  Concern(){
		Log.e("++++", "aaaaaaaaaaa");
		concernsociaties= new ArrayList<Sociaty>();
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
			});
		}
	//绑定公会请求
	
	//遍历jsonarray
	public void getPopularJsonObj(JSONArray array,ArrayList<Sociaty> sociaties ) throws JSONException{
		
		SociatyResponse soc= new SociatyResponse();
		for(int i=0;i<array.length();i++){
			JSONObject jsonObject= new JSONObject();
			jsonObject= array.getJSONObject(i);
			soc.setJsonObject(jsonObject);
			Sociaty sociaty= new Sociaty();
			sociaty.setId(soc.getId());
			sociaty.setJifen(soc.getJifen());
			sociaty.setKey(soc.getKey());
			sociaty.setSocietyname(soc.getSocietyName());
			sociaty.setSocietysummary(soc.getSocietySummary());
			sociaties.add(sociaty);
		}
	
	}
	
	
	@Override
	public void onSaveInstanceState(Bundle outState) {
	    if(((MainActivity)getActivity()).isConflict)
	        outState.putBoolean("isConflict", true);
	    super.onSaveInstanceState(outState);
	    
	}
}
