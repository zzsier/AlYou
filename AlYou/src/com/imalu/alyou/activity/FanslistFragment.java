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
import com.imalu.alyou.adapter.FansAdapter;
import com.imalu.alyou.domain.UserLM;
import com.imalu.alyou.net.JsonHttpResponseHandler;
import com.imalu.alyou.net.NetManager;
import com.imalu.alyou.net.request.FansRequest;
import com.imalu.alyou.net.response.FansResponse;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;



/**
 * 通讯录------粉丝列表
 * 
 */
public class FanslistFragment extends Fragment {
	private ArrayList<UserLM> fans;
	private  ListView listView;
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		return inflater.inflate(R.layout.contact_fans_list, container, false);
	}
	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		listView=(ListView)getActivity().findViewById(R.id.lv_fans_list);
		fansFund();
		Log.e("FANS",""+fans.toString());
		FansAdapter adapter=new FansAdapter(getActivity(), fans);
		listView.setAdapter(adapter);
	}
	//发送请求，查询粉丝数据
		private void fansFund() {
			String  key=AlUApplication.getMyInfo().getKey();
			fans=new ArrayList<UserLM>();
			FansRequest fansRqst=new FansRequest();
			fansRqst.setUserkey(key);
			NetManager.execute(NetManager.FANS_REQUEST_OPERATION, fansRqst, new JsonHttpResponseHandler(){
				@Override
				public void onSuccess(int statusCode, Header[] headers,
						JSONArray response) {
					// TODO Auto-generated method stub
					super.onSuccess(statusCode, headers, response);
					try {
						getJsonObj(response);
						Log.e("lory.....", ""+fans.toString());
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				}
			});
			
		}
		//遍历Json数组
		protected void getJsonObj(JSONArray array) throws JSONException{
			FansResponse fansRponse=new FansResponse();
			for(int i=0;i<array.length();i++){
				JSONObject jsonObject= new JSONObject();
				jsonObject= array.getJSONObject(i);
				UserLM fan= new UserLM();
				fansRponse.setJsonObject(jsonObject);
				fan.setId(fansRponse.getId());
				fan.setUserName(fansRponse.getUserName());
				fan.setGexingQianming(fansRponse.getGexingQianming());
				fans.add(fan);
			}
			
		}
	@Override
	public void onSaveInstanceState(Bundle outState) {
	    if(((MainActivity)getActivity()).isConflict)
	        outState.putBoolean("isConflict", true);
	    super.onSaveInstanceState(outState);
	    
	}
}
