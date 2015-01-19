package com.imalu.alyou.activity;

import java.util.ArrayList;

import org.apache.http.Header;
import org.ice4j.attribute.FingerprintAttribute;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.Toast;

import com.imalu.alyou.AlUApplication;
import com.imalu.alyou.R;
import com.imalu.alyou.adapter.CircleOfFriendsAdapter;
import com.imalu.alyou.adapter.MoodCommentAdapter;
import com.imalu.alyou.domain.PingLunLM;
import com.imalu.alyou.domain.XinqingLM;
import com.imalu.alyou.net.JsonHttpResponseHandler;
import com.imalu.alyou.net.NetManager;
import com.imalu.alyou.net.request.CircleOfFriendRequest;
import com.imalu.alyou.net.request.MoodCommentRequest;
import com.imalu.alyou.net.request.SignedPersonalityRequest;
import com.imalu.alyou.net.response.CircleOfFriendResponse;
import com.imalu.alyou.net.response.FindFriendResponse;
import com.imalu.alyou.net.response.MoodCommentResponse;
import com.imalu.alyou.net.response.SignedPersonalityResponse;

public class Activity_Circle_Friends_homepage extends BaseActivity {
	private Handler handler;
	private ListView xinqingshuoList;
	private CircleOfFriendsAdapter adapter;
	private MoodCommentAdapter madapter;
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
	private EditText qianming_et;
	private String xinqingkey;
	private ListView pinglun_lv;
	private String str;
	private Boolean flag;
	private String info;
	private CircleOfFriendResponse circleOfFriendResponse;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_circle_friends_homepage);
		lms = new ArrayList<XinqingLM>(); 
		fundXinQingShuo();
		initViews();
		setText(); 
	}
	private ArrayList<PingLunLM> fundPinglun() {
		// TODO Auto-generated method stub
final ArrayList<PingLunLM> pingLunLMs = new ArrayList<PingLunLM>();
		MoodCommentRequest moodCommentReq = new MoodCommentRequest();
		moodCommentReq.setXinqingkey(circleOfFriendResponse.getKey());
		Log.i("key值》》》》》》》》》》", ""+circleOfFriendResponse.getKey());
		NetManager.execute(NetManager.MOOD_COMMENT, moodCommentReq, new JsonHttpResponseHandler(){
			@Override
			public void onSuccess(int statusCode, Header[] headers,
					JSONArray response) {
				super.onSuccess(statusCode, headers, response);
				// TODO Auto-generated method stub
				try {
					getPingLunJsonObj(response,pingLunLMs);
					Log.e("评论..............................", ""+response.toString());
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		});
		return pingLunLMs;	
	}
	protected void getPingLunJsonObj(JSONArray array,ArrayList<PingLunLM> pingLunLMs) throws JSONException {
		// TODO Auto-generated method stub
		MoodCommentResponse moodCommentResponse = new MoodCommentResponse();
		for (int i = 0; i < array.length(); i++) {
			JSONObject objt = new JSONObject();
			objt = array.getJSONObject(i);
			PingLunLM pingLunLM = new PingLunLM();
			moodCommentResponse.setJsonObject(objt);
			pingLunLM.setHuifurenName(moodCommentResponse.getHuifurenName());
			Log.i("名字。。。。。。。。。。", ""+moodCommentResponse.getHuifurenName());
			pingLunLM.setContent(moodCommentResponse.getContent());
			Log.i("内容。。。。。。。。。。", ""+moodCommentResponse.getContent());
			pingLunLMs.add(pingLunLM);
			Log.i("数组。。。。。。。。。。", ""+pingLunLMs.toString());
		}
	}
	private void setText() {
		// TODO Auto-generated method stub
		zhanghu_nicheng.setText(AlUApplication.getMyInfo().getUsername());
		zhanghu_app.setText( String.valueOf(AlUApplication.getMyInfo().getId()));
		zhanghu_jifen.setText(String.valueOf(AlUApplication.getMyInfo().getJifen()));
		zhanghu_suoshugonghui.setText(AlUApplication.getMyInfo().getSocietyname());
	}
	private void initViews() {
		zhanghu_nicheng =(TextView) findViewById(R.id.zhanghu_nicheng);
		zhanghu_app =(TextView) findViewById(R.id.zhanghu_app);
		zhanghu_jifen =(TextView) findViewById(R.id.zhanghu_jifen);
		zhanghu_suoshugonghui=(TextView) findViewById(R.id.zhanghu_suozaigonghui);
		 qianming_et = (EditText) findViewById(R.id.qianming_et);
		xinqingshuoList = (ListView)findViewById(R.id.homepage_lv);
		bottomView=getLayoutInflater().inflate(R.layout.item_circle_friend_button, null);
		adapter = new CircleOfFriendsAdapter(lms, Activity_Circle_Friends_homepage.this);
		xinqingshuoList.setAdapter(adapter);
		xinqingshuoList.addFooterView(bottomView);
		
		qianming_et.setOnEditorActionListener(new OnEditorActionListener() {
			
			public boolean onEditorAction(TextView arg0, int arg1, KeyEvent arg2) {
				// TODO Auto-generated method stub
		        str = qianming_et.getText().toString();
				Log.i("取值》》》》》》》》》》", ""+str);
				qianming();
				Toast.makeText(Activity_Circle_Friends_homepage.this,info, Toast.LENGTH_SHORT).show(); 
				qianming_et.setText(str);
				return false;
			}
		});
		qianming_et.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			qianming_et.setText("");	
			}
		})	;
		 
		
		//添加底部按钮
		jiazaiMore=(Button)bottomView.findViewById(R.id.bt11);
		jiazaiMore.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				s+=1;	
				fundXinQingShuo();
				Log.e("***4444", lms.toString());
		 
			}
		});
		//给list设置事件
		xinqingshuoList.setOnItemClickListener(new OnItemListener());
		xinqingshuoList.setOnScrollListener(new OnScrollListener());
	}
	  
	protected void qianming() {
		// TODO Auto-generated method stub
		SignedPersonalityRequest signedPersonalityReq = new SignedPersonalityRequest();
		signedPersonalityReq.setUserkey(AlUApplication.getMyInfo().getKey());
		Log.i("用户的key值》》》》》》》", AlUApplication.getMyInfo().getKey());
		signedPersonalityReq.setGexingqianming(str);
		Log.i("输入的内容》》》》》》》", str);
		NetManager.execute(NetManager.SIGNED_PERSONALITY, signedPersonalityReq, new JsonHttpResponseHandler(){
			@Override
			public void onSuccess(int statusCode, Header[] headers,
					JSONObject response) {
				// TODO Auto-generated method stub
				super.onSuccess(statusCode, headers, response);
				 SignedPersonalityResponse signedPersonalityResp = new SignedPersonalityResponse();
				 signedPersonalityResp.setJsonObject(response);
				 try {
					flag = signedPersonalityResp.getCode();
					info = signedPersonalityResp.getInfo();
					log();
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		});	
	}
	protected void log() {
		// TODO Auto-generated method stub
		Log.i("看看真假---------------",""+flag);
		Log.i("是否成功---------------",""+ info);
	}

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
	}
	//遍历Json数组
	protected void getJsonObj(JSONArray array)throws JSONException {
		// TODO Auto-generated method stub
	circleOfFriendResponse = new CircleOfFriendResponse();
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
			xinqingLM.setXingqingkey(circleOfFriendResponse.getKey());
			Log.i("key................................", ""+circleOfFriendResponse.getKey());
		ArrayList<PingLunLM> pinglunLMs=fundPinglun();	
		Log.e("-------------", pinglunLMs.toString());
			xinqingLM.setPinglunLMs(pinglunLMs);
			//	xinqingLM.setpinglunLMs(circleOfFriendResponse.getpinglunLMs());
			lms.add(xinqingLM);			 
		}		
	}
	public void back(View view) {
		finish();
	}
}
