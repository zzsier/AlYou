package com.imalu.alyou.activity;

import java.util.ArrayList;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.imalu.alyou.AlUApplication;
import com.imalu.alyou.R;
import com.imalu.alyou.adapter.CircleOfFriendsAdapter;
//import com.imalu.alyou.adapter.MoodCommentAdapter;
//import com.imalu.alyou.domain.PingLunLM;
import com.imalu.alyou.domain.XinqingLM;
import com.imalu.alyou.net.JsonHttpResponseHandler;
import com.imalu.alyou.net.NetManager;
import com.imalu.alyou.net.request.CircleOfFriendRequest;
//import com.imalu.alyou.net.request.MoodCommentRequest;
import com.imalu.alyou.net.response.CircleOfFriendResponse;
//import com.imalu.alyou.net.response.MoodCommentResponse;

public class Activity_Circle_Friends_homepage extends BaseActivity {
	private Handler handler;
	private ListView xinqingshuoList;
	private CircleOfFriendsAdapter adapter;
	//private MoodCommentAdapter madapter;
	private ArrayList<XinqingLM> lms;
	private  View  bottomView;
	private Button jiazaiMore;
	private XinqingLM xinqingLM;
	private CircleOfFriendRequest circleOfFriendRequest;
	private TextView zhanghu_nicheng;
	private TextView zhanghu_app;
	private TextView zhanghu_jifen;
	private TextView zhanghu_suoshugonghui;
	private int s = 1;
	//private ArrayList<PingLunLM>pingLunLMs;
	private String xinqingkey;
	private ListView pinglun_lv;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_circle_friends_homepage);
		//	handler = new Handler();
		lms = new ArrayList<XinqingLM>();
		//initData();
		fundXinQingShuo();
		//fundPinglun();
		initViews();
		setText();
		//	findgonghui();
	}

//	private void fundPinglun() {
//		// TODO Auto-generated method stub
//		pingLunLMs = new ArrayList<PingLunLM>();
//		MoodCommentRequest moodCommentReq = new MoodCommentRequest();
//		moodCommentReq.setXinqingkey(xinqingkey);
//		NetManager.execute(NetManager.MOOD_COMMENT, moodCommentReq, new JsonHttpResponseHandler(){
//			@Override
//			public void onSuccess(int statusCode, Header[] headers,
//					JSONArray response) {
//				super.onSuccess(statusCode, headers, response);
//				// TODO Auto-generated method stub
//				try {
//					getPingLunJsonObj(response);
//				} catch (Exception e) {
//					// TODO: handle exception
//				}
//			}
//		});	
//	}
//	protected void getPingLunJsonObj(JSONArray array) throws JSONException {
//		// TODO Auto-generated method stub
//		MoodCommentResponse moodCommentResponse = new MoodCommentResponse();
//		for (int i = 0; i < array.length(); i++) {
//			JSONObject objt = new JSONObject();
//			objt = array.getJSONObject(i);
//			PingLunLM pingLunLM = new PingLunLM();
//			moodCommentResponse.setJsonObject(objt);
//			pingLunLM.setHuifurenName(moodCommentResponse.getHuifurenName());
//			pingLunLM.setContent(moodCommentResponse.getContent());
//			pingLunLMs.add(pingLunLM);
//		}
//	}

	private void setText() {
		// TODO Auto-generated method stub
		zhanghu_nicheng.setText(AlUApplication.getMyInfo().getUsername());
		zhanghu_app.setText( String.valueOf(AlUApplication.getMyInfo().getId()));
		zhanghu_jifen.setText(String.valueOf(AlUApplication.getMyInfo().getJifen()));
		zhanghu_suoshugonghui.setText(AlUApplication.getMyInfo().getSocietyName());

	}

	private void initViews() {
		zhanghu_nicheng =(TextView) findViewById(R.id.zhanghu_nicheng);
		zhanghu_app =(TextView) findViewById(R.id.zhanghu_app);
		zhanghu_jifen =(TextView) findViewById(R.id.zhanghu_jifen);
		zhanghu_suoshugonghui=(TextView) findViewById(R.id.zhanghu_suozaigonghui);
		// TODO Auto-generated method stub
		 
		xinqingshuoList = (ListView)findViewById(R.id.homepage_lv);
		
		
		bottomView=getLayoutInflater().inflate(R.layout.item_circle_friend_button, null);
		adapter = new CircleOfFriendsAdapter(lms,Activity_Circle_Friends_homepage.this);
		xinqingshuoList.setAdapter(adapter);
//		 pinglun_lv.setAdapter(madapter);
		xinqingshuoList.addFooterView(bottomView);

		//添加底部按钮
		jiazaiMore=(Button)bottomView.findViewById(R.id.bt11);
		jiazaiMore.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Log.e("****11111", "**************");
				s+=1;	
				//	jiazaiMore.setText("数据加载中");
				//	handler.postDelayed(new Runnable() {
				//	@Override
				//	public void run() {

				fundXinQingShuo();
				Log.e("****222", "**************");
				loadData();
				Log.e("****3333", "**************");
				Log.e("***4444", lms.toString());
				
			//	Log.e("****55555", "**************");
				///	jiazaiMore.setText("加载更多");

				//listView.setSelection(5);
				//	}
				//	}, 2000);

			}
		});

		//给list设置事件

		xinqingshuoList.setOnItemClickListener(new OnItemListener());
		xinqingshuoList.setOnScrollListener(new OnScrollListener());

	}
	//加载数据
	public void loadData() {

		int count=adapter.getCount()+1;
		for(int i=count;i<count+4;i++){
			adapter.addItem(xinqingLM);
		}

	}
	//	public void initData(){
	//				for(int i=0;i<10;i++){
	//					list.add(photoAlbum);
	//				}
	//				adapter=new FriendListAdapter(list, this);
	//			}


	/*	class ButtonClickListener implements OnClickListener{
		@Override
		public void onClick(View v) {			
			jiazaiMore.setText("数据加载中");
		//	handler.postDelayed(new Runnable() {
			//	@Override
			//	public void run() {
				fundXinQingShuo();
					loadData();
					adapter.notifyDataSetChanged();
					jiazaiMore.setText("加载更多");

					//listView.setSelection(5);
			//	}
		//	}, 2000);
			s+=1;
		}
	}*/
	/**
	 * 单击ListView中某一项触发的事件
	 * @author dell
	 *
	 */
	class OnItemListener implements OnItemClickListener{
		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int position,
				long id) {
			// TODO Auto-generated method stub
			System.out.println("123");
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

			if(lms.size()==s*4 )
			{
				bottomView.setVisibility(View.VISIBLE);
			}else
			{
				bottomView.setVisibility(View.INVISIBLE);
			}
			//判断是否滚动到最后一行
			if(firstVisibleItem+visibleItemCount==totalItemCount&&totalItemCount>0){
				System.out.println("已经到最后一行了");
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
	private void fundXinQingShuo() {
		// TODO Auto-generated method stub
		//String  key="5e1df8c0-721e-4cf9-abc0-6a1f6907f4b4";
		//AlUApplication.getMyInfo().getKey();
		circleOfFriendRequest = new CircleOfFriendRequest();
		circleOfFriendRequest.setUserKey(AlUApplication.getMyInfo().getKey());
		Log.i(">>>>>>>>>>>>>>>>>>>>>", ""+AlUApplication.getMyInfo().getKey());
		circleOfFriendRequest.setPageIndex(s);
		circleOfFriendRequest.setPageSize(4);
		NetManager.execute(NetManager.CIRCLR_OF_FRIEND, circleOfFriendRequest, new JsonHttpResponseHandler(){
			@Override
			public void onSuccess(int statusCode, Header[] headers,
					JSONArray response) {
				// TODO Auto-generated method stub
				super.onSuccess(statusCode, headers, response);
				try {
					getJsonObj(response);
					Log.e("***66666", lms.toString());
					Log.e("xinqingLM..............................", ""+response.toString());
					adapter.notifyDataSetChanged();
				} catch (JSONException e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
		});	
		//fundPinglun();
	}
	//遍历Json数组
	protected void getJsonObj(JSONArray array)throws JSONException {
		// TODO Auto-generated method stub
		CircleOfFriendResponse circleOfFriendResponse = new CircleOfFriendResponse();
		for(int i=0;i<array.length();i++){
			JSONObject jsonObject= new JSONObject();
			jsonObject= array.getJSONObject(i);
			XinqingLM xinqingLM= new XinqingLM();
			circleOfFriendResponse.setJsonObject(jsonObject);
			xinqingLM.setCreatedTime(circleOfFriendResponse.getCreatedTime());
			Log.i("shijian.......................", ""+circleOfFriendResponse.getCreatedTime());
			xinqingLM.setContent(circleOfFriendResponse.getContent());
			Log.i("neirong......................", ""+circleOfFriendResponse.getContent());
			xinqingLM.setDianzan(Integer.valueOf(circleOfFriendResponse.getDianzan()));
			Log.i("dianzan.......................", ""+circleOfFriendResponse.getDianzan());
			xinqingLM.setHeadPicture(circleOfFriendResponse.getHeadPicture());
			Log.i("touxiang........................", ""+circleOfFriendResponse.getHeadPicture());
			xinqingLM.setPhotoUrl1(circleOfFriendResponse.getPhotoUrl1());
			Log.i("toupian1..............................", ""+circleOfFriendResponse.getPhotoUrl1());
			xinqingLM.setPhotoUrl2(circleOfFriendResponse.getPhotoUrl2());
			Log.i("toupian2..............................", ""+circleOfFriendResponse.getPhotoUrl2());
			xinqingLM.setPhotoUrl3(circleOfFriendResponse.getPhotoUrl3());
			Log.i("toupian3..............................", ""+circleOfFriendResponse.getPhotoUrl3());
			xinqingLM.setUserName(circleOfFriendResponse.getUserName());
			Log.i("nicheng................................", ""+circleOfFriendResponse.getUserName());
			//xinqingLM.setXingqingkey(circleOfFriendResponse.getKey());
			//Log.i("key................................", ""+circleOfFriendResponse.getKey());
			//	xinqingLM.setpinglunLMs(circleOfFriendResponse.getpinglunLMs());
			lms.add(xinqingLM);	
			//xinqingkey = circleOfFriendResponse.getKey();
		}		
	}
	public void back(View view) {
		finish();
	}
}
