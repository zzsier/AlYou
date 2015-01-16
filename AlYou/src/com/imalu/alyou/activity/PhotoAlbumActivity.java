package com.imalu.alyou.activity;

import java.util.ArrayList;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.imalu.alyou.AlUApplication;
import com.imalu.alyou.R;
import com.imalu.alyou.adapter.FriendListAdapter;
import com.imalu.alyou.domain.PhotoAlbum;
import com.imalu.alyou.net.JsonHttpResponseHandler;
import com.imalu.alyou.net.NetManager;

import com.imalu.alyou.net.request.PhotoAlbumRequest;
import com.imalu.alyou.net.response.PhotoAlbumResponse;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class PhotoAlbumActivity extends BaseActivity{
	private TextView photoOwner;
	private ListView photoList;
	//ArrayList<PhotoAlbum> list = new ArrayList<PhotoAlbum>();
	private PhotoAlbumRequest photoAlbumRequest;
	private ArrayList<PhotoAlbum> photoAlbums;
	private Button loadMore;
	private FriendListAdapter adapter;
	PhotoAlbum photoAlbum;
	//private Handler handler;
	View bottomView;
	private int s = 1;
	public String photoUrl;
	public String content;
	private String date;
	private String own;
	private String other;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.photoslist);
		photoAlbums=new ArrayList<PhotoAlbum>();
		parseFriendAlbum();
		//handler = new Handler();
		initViews();
	}
	private void initViews() {
		photoOwner = (TextView)findViewById(R.id.friendcircle_photo_owner_tv);
		photoList = (ListView)findViewById(R.id.friendcircle_photolist_lv);
		adapter = new FriendListAdapter(photoAlbums, this);
		photoOwner.setText(AlUApplication.getMyInfo().getUsername());
		photoList.setAdapter(adapter);
		//添加底部按钮
		bottomView=getLayoutInflater().inflate(R.layout.item_more_photo, null);
		loadMore=(Button)bottomView.findViewById(R.id.getmore_photo);
		loadMore.setOnClickListener(new ButtonClickListener());

		photoList.addFooterView(bottomView);
		//给listView设置事件
		photoList.setOnItemClickListener(new OnItemListener());
		photoList.setOnScrollListener(new OnScrollListener());
	}
	//加载数据
	public void loadData(){
		int count=adapter.getCount()+1;
		if(photoAlbums.size()%10 == 0)
		{
			for(int i=count;i<count+10;i++){
				adapter.addItem(photoAlbum);
			}
		}else
		{
			for(int i=count;i<photoAlbums.size()%8;i++){
			adapter.addItem(photoAlbum);
			}
		}
	}
	//private ArrayList<MoodDetails> details;
	class ButtonClickListener implements OnClickListener{
		@Override
		public void onClick(View v) {			
			s+=1;
			loadMore.setText("数据加载中");
			//handler.postDelayed(new Runnable() {
			//	@Override
			//	public void run() {
					parseFriendAlbum();
					loadData();
					adapter.notifyDataSetChanged();
					//listView.setSelection(5);
					loadMore.setText("加载更多");
			//	}
		//	}, 2000);
		}
	}
	class OnItemListener implements OnItemClickListener{  
		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int position,
				long id) {
			Intent intent = new Intent();
			intent.setClass(
					PhotoAlbumActivity.this, 
					MoodDetailsActivity.class);
			intent.putExtra("own", own);
			intent.putExtra("other", other);
			startActivity(intent);
			finish();
		}
	}
			
	//滚动加载事件：
	//是否到达ListView底部
	boolean isLastRow=false;
	/**
	 * 滚动时产生的事件
	 * @author dell
	 *
	 */
	class OnScrollListener implements android.widget.AbsListView.OnScrollListener{

		//滚动的时候一直回调，直到停止滚动时才停止回调，单击时回调一次
		//firstVisibleItem:当前嫩看见的第一个列表项ID(从0开始,小半个也算)
		//visibleItemCount：当前能看见的列表项个数(小半个也算)
		//totalItemCount：列表项总共数
		@Override
		public void onScroll(AbsListView view, int firstVisibleItem,
				int visibleItemCount, int totalItemCount) {
			if(photoAlbums.size()==10*s)
			{
				bottomView.setVisibility(View.VISIBLE);
			}else
			{
				bottomView.setVisibility(View.INVISIBLE);
			}
			//判断是否滚动到最后一行
			if(firstVisibleItem+visibleItemCount==totalItemCount&&totalItemCount>0){
				//System.out.println("已经到最后一行了");
				isLastRow=true;
			}
		}

		//正在滚动时回调，回调2-3次，手指没抛则回调2次，scrollState=2的这次不回调
		//回调顺序如下：
		//第一次：scrollState=SCROLL_STATE_TOUCH_SCROLL(1)正在滚动
		//第二次：scrollState = SCROLL_STATE_FLING(2)手指做了抛的动作（手指离开屏幕前，用力滑了一下）
		//第三次：scrollState = SCROLL_STATE_IDLE(0) 停止滚动	   

		//当屏幕停止滚动时为0；当屏幕滚动且用户使用的触碰或手指还在屏幕上时为1；  
		//由于用户的操作，屏幕产生惯性滑动时为2  
		@Override
		public void onScrollStateChanged(AbsListView view, int scrollState) {
			//当滚动到最后一行并且停止滚动时，执行加载
			if(isLastRow&&scrollState==OnScrollListener.SCROLL_STATE_IDLE){
				//执行加载代码
				isLastRow=false;
			}
		}
	}
	public void parseFriendAlbum(){	
		photoAlbumRequest = new PhotoAlbumRequest();
		//photoAlbumRequest.setUserKey(key);
		photoAlbumRequest.setUserKey(AlUApplication.getMyInfo().getKey());
		photoAlbumRequest.setPageIndex(s);
		photoAlbumRequest.setPageSize(10);
		NetManager.execute(NetManager.PARSE_FRIEND_ALBUM, photoAlbumRequest, new JsonHttpResponseHandler(){
			@Override
			public void onSuccess(int statusCode, Header[] headers,
					JSONArray response) {
				// TODO Auto-generated method stub
				super.onSuccess(statusCode, headers, response);
				try {
					getJsonObj(response);
					//Log.e("photoAlbum...", ""+response.toString());
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		});
	}
	//遍历Json数组
	protected void getJsonObj(JSONArray array) throws JSONException{
		PhotoAlbumResponse photoAlbumResponse=new PhotoAlbumResponse();
		for(int i=0;i<array.length();i++){
			JSONObject jsonObject= new JSONObject();
			jsonObject= array.getJSONObject(i);
			PhotoAlbum photoAlbum= new PhotoAlbum();
			photoAlbumResponse.setJsonObject(jsonObject);
			photoAlbum.setDate(photoAlbumResponse.getDate());
			photoAlbum.setPhotoUrl(photoAlbumResponse.getPhotoUrl());
			photoAlbum.setContent(photoAlbumResponse.getContent());
			photoAlbum.setKey(photoAlbumResponse.getKey());
			photoAlbum.setUserkey(photoAlbumResponse.getUserKey());
			//Log.i("jsonobj", ""+photoAlbumResponse.getDate()+"/"+photoAlbumResponse.getPhotoUrl()+"/"+photoAlbumResponse.getContent());
			photoAlbums.add(photoAlbum);
			
			own = photoAlbumResponse.getUserKey();
			other = photoAlbumResponse.getKey();
		}			
	}
	public void back(View view) {
		finish();
	}
}
