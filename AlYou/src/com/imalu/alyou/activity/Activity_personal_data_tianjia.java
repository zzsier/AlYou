package com.imalu.alyou.activity;

import com.imalu.alyou.R;

import android.os.Bundle;
public class Activity_personal_data_tianjia  extends BaseActivity{
	//private ArrayList<Friend> friends;
//	private ListView  lv;
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_personal_data_tianjia);
//		fandFriend();
	}

////	private void fandFriend() {
//		// TODO Auto-generated method stub
//		//int  key=AlUApplication.getMyInfo().getId();
//		//friends=new ArrayList<Friend>();
//		AddFriendRequest  AddFriendRqst=new AddFriendRequest();
//		AddFriendRqst.setID(AddFriendRqst.getID());
//		NetManager.execute(NetManager.ADDFRIEND_FRIEND_REQUEST_OPERATION, AddFriendRqst,
//				new JsonHttpResponseHandler(){
//			@Override
//			public void onSuccess(int statusCode, Header[] headers,
//					JSONArray response){
//				// TODO Auto-generated method stub
//				super.onSuccess(statusCode, headers, response);
//				try {
//					getJsonObj(response);
//				//	Log.e("lory.....", ""+friends.toString());
//				} catch (Exception e) {
//					// TODO: handle exception
//					e.printStackTrace();
//				}
//		 
//			}
//		 
//	});
//	
//}
//	//遍历Json数组
//	protected void getJsonObj(JSONArray array) throws JSONException{
//		AddFriendResponse AFRponse=new AddFriendResponse();
//		 
//		//定义一个动态数组
//	///	lv=(ListView) findViewById(R.id.lv1);
//		//在数组中定义hashmap
//		ArrayList<HashMap<String, Object>>  listItem =new ArrayList<HashMap<String,Object>>();
//		for(int i = 0; i <= array.length() ; i++){
//			JSONObject jsonObject= new JSONObject();
//			jsonObject= array.getJSONObject(i);
//			Friend f= new Friend();
//			AFRponse.setJsonObject(jsonObject);
//			HashMap<String, Object> map = new HashMap<String, Object>();
//			map.put("textView2", AFRponse.getUserName()  );
//			Log.i("textView2", AFRponse.getUserName() );
//			map.put("image_personal_data_photo", R.drawable.ico_personal_data_photo);
//			map.put("textView6", AFRponse.getJiFen());
//			map.put("textView8", AFRponse.getLuntanGonghuiID());
//			map.put("textView4",  AFRponse.getId());
//			listItem.add(map);
//			SimpleAdapter mSimpleAdapter =new SimpleAdapter(this, 
//					listItem, //需要绑定的数据    
//					R.layout.item, //每一行的布局
//					//动态数组中的数据源的键对应到定义布局的View
//					new String[] {"textView2","image_personal_data_photo", "textView6","textView8","textView4"},  
//					new int[] {R.id.textView2,R.id.image_personal_data_photo,R.id.textView6,R.id.textView8,R.id.textView4});
//			//为ListView绑定适配器 
//			lv.setAdapter(mSimpleAdapter);
//		}	
//	}
	}
	 