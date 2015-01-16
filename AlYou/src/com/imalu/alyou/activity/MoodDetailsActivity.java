package com.imalu.alyou.activity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.imalu.alyou.AlUApplication;
import com.imalu.alyou.R;
import com.imalu.alyou.domain.MoodDetails;
import com.imalu.alyou.net.JsonHttpResponseHandler;
import com.imalu.alyou.net.NetManager;
import com.imalu.alyou.net.request.MoodDetailsRequest;
import com.imalu.alyou.net.response.MoodDetailsResponse;
import com.imalu.alyou.net.response.PersonalInfoResponse;

public class MoodDetailsActivity extends BaseActivity{
	private ArrayList<MoodDetails> details;
	private ImageView headPicture;
	private TextView nickName,content,time;
	private ImageView photo1,photo2,photo3;
	MoodDetails moodDetails;
	private String name;
	private String contents;
	private int number;
	private TextView num;
	private String date;
	private String own;
	private String other;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mood_details);
		Intent intent =getIntent();
		other = intent.getStringExtra("other");
		own = intent.getStringExtra("own");
		//Log.i(">>>>>>>>>>>>>>>>>>>>>>>",""+key);
		//		userKey = intent.getExtras().get(userKey);
		details = new ArrayList<MoodDetails>();
		moodDetails = new MoodDetails();
		MoodDetailsFound();
		initViews();
		setText();
		
	}
	private void setText() {
		//FriendListAdapter adapter = new FriendListAdapter(details,this);
		//headPicture.setImageResource(moodDetails.getHeadPicture()));
		if(name==null){
			nickName.setText("未设置");
			
		}else{
			nickName.setText(name);
		}
		if(contents==null){
			content.setText("");
			
		}else{
			content.setText(contents);
		}
		
		int fromIndex = date.indexOf("(")+1;
		int endIndex = date.indexOf("+");
		String createTime = date.substring(fromIndex, endIndex);
		time.setText(new SimpleDateFormat("yyyy-MM-dd").format(Long.parseLong(createTime)));
		if(number == 0)
		{
			num.setText("还没有");
		}else
		{
			num.setText(number);
		}
		
	}
	private void initViews() {
		headPicture = (ImageView)findViewById(R.id.image_personal_data_photo);
		nickName = (TextView)findViewById(R.id.nickname_personal_data_tv);
		content = (TextView)findViewById(R.id.content_personal_data_tv);
		photo1 = (ImageView)findViewById(R.id.photo1_personal_data_iv);
		photo2 = (ImageView)findViewById(R.id.photo2_personal_data_iv);
		photo3 = (ImageView)findViewById(R.id.photo3_personal_data_iv);
		time = (TextView)findViewById(R.id.time_personal_data_tv);
		num = (TextView)findViewById(R.id.num_zan_tv);		
	}
	protected void MoodDetailsFound() {
		
		MoodDetailsRequest moodDetailsRequest = new MoodDetailsRequest();
		moodDetailsRequest.setOwn(own);
		moodDetailsRequest.setOther(other);
		moodDetailsRequest.setPageindex(1);
		moodDetailsRequest.setPagesize(3);
		NetManager.execute(NetManager.DETAILS_OF_ALBUM, moodDetailsRequest, new JsonHttpResponseHandler(){
			@Override
			public void onSuccess(int statusCode,
					org.apache.http.Header[] headers, JSONArray response) {
				// TODO Auto-generated method stub
				super.onSuccess(statusCode, headers, response);
				try {
					getJsonObject(response);
					//Log.e("photoAlbum...", ""+response.toString());
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		});
	}
	//遍历Json数组
	protected void getJsonObject(JSONArray array)throws JSONException{
		// TODO Auto-generated method stub
		MoodDetailsResponse  moodDetailsResponse = new MoodDetailsResponse();
		for(int i=0;i<array.length();i++){
			JSONObject Obj = new JSONObject();
			Obj = array.getJSONObject(i);
			
			moodDetailsResponse.setJsonObject(Obj);
			
			moodDetails.setUserName(moodDetailsResponse.getUser());
			moodDetails.setDianZan(moodDetailsResponse.getDianzan());
			moodDetails.setContent(moodDetailsResponse.getContent());
			moodDetails.setCreateTime(moodDetailsResponse.getCreateTime());
			moodDetails.setHeadPicture(moodDetailsResponse.getHeadPicture());
			moodDetails.setPhotoUrl1(moodDetailsResponse.getPhotoUrl1());
			moodDetails.setPhotoUrl2(moodDetailsResponse.getPhotoUrl2());
			moodDetails.setPhotoUrl3(moodDetailsResponse.getPhotoUrl3());
			moodDetails.setPhotoUrl4(moodDetailsResponse.getPhotoUrl4());
			moodDetails.setPhotoUrl5(moodDetailsResponse.getPhotoUrl5());
			details.add(moodDetails);
			name = moodDetailsResponse.getUser();
			contents = moodDetailsResponse.getContent();
			number = moodDetailsResponse.getDianzan();
			date = moodDetailsResponse.getCreateTime();
		}
	}
	public void back(View view) {
		finish();
	}
}
