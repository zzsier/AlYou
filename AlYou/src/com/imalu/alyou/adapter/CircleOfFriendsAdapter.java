package com.imalu.alyou.adapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.R.layout;
import android.content.Context;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;


import com.imalu.alyou.AlUApplication;
import com.imalu.alyou.R;
import com.imalu.alyou.activity.ActionItem;
import com.imalu.alyou.activity.TitlePopup;
import com.imalu.alyou.activity.TitlePopup.OnItemOnClickListener;
import com.imalu.alyou.domain.PingLunLM;
import com.imalu.alyou.domain.XinqingLM;
import com.imalu.alyou.net.JsonHttpResponseHandler;
import com.imalu.alyou.net.NetManager;
import com.imalu.alyou.net.request.ZanRequest;
import com.imalu.alyou.net.response.BingGonghuiResponse;
import com.imalu.alyou.net.response.ZanResponse;
import com.imalu.alyou.utils.TanKuangUtil;




public class CircleOfFriendsAdapter extends BaseAdapter{
	ArrayList<XinqingLM> lms;
	ArrayList<PingLunLM>pls;
	Context mcontext;
	private ViewHolder holder;
	private LayoutInflater flater;
	private TitlePopup titlePopup;
	private View layout_pinglunbuju;
	private EditText shuru_et;
	private Button fasong_bt;
	private String xinqingkey;
	private String zhanghukey;
	private String faxinqingkey;
	private Boolean flag;
	private String info;
	
	 
	public CircleOfFriendsAdapter(ArrayList<XinqingLM> lms, Context context){
		
		
		
		super();
		this.mcontext = context;
		flater = LayoutInflater.from(mcontext);
		if(this.lms == null)
		{
			this.lms = new ArrayList<XinqingLM>();
	
			this.lms=lms;
		
		}else
		{

			this.lms =lms;
	}
		
		Log.e("适配器", ""+lms.toString());
		 
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return lms.size();
	}
 
	@Override
	public Object getItem(int  position) {
		// TODO Auto-generated method stub
		return lms.get( position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return  position;
	}
	
	public void getvalue(int position) {
		// TODO Auto-generated method stub
        xinqingkey = lms.get(position).getXingqingkey();
        faxinqingkey =lms.get(position).getUserKey();
        zhanghukey = AlUApplication.getMyInfo().getKey();
	}
	protected void log() {
		// TODO Auto-generated method stub
		Log.i("FLAG---------------",""+flag);
		Log.i("INFO---------------",""+ info);
		}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		holder = null;
		if(convertView == null)
		{
			//加载布局
			convertView=flater.inflate(R.layout.item_circle_friend, null);
			//	将convertView中的相关组件赋给ViewHolder中的成员变量
			holder = new ViewHolder();
			holder.HeadPicture = (ImageView) convertView.findViewById(R.id.touxiang_image);
			holder.UserName =  (TextView) convertView.findViewById(R.id.nicheng_tv);
			holder.Content = (TextView) convertView.findViewById(R.id.xinqing_tv);
			holder.PhotoUrl1 = (ImageView) convertView.findViewById(R.id.xinqing_image1);
			holder.PhotoUrl2 = (ImageView) convertView.findViewById(R.id.xinqing_image2);
			holder.PhotoUrl3= (ImageView) convertView.findViewById(R.id.xinqing_image3);
			holder.CreatedTime = (TextView) convertView.findViewById(R.id.riqi_tv);
			holder.Dianzan = (TextView) convertView.findViewById(R.id.renshu_tv);
			holder.pinglunLMs = (ListView) convertView.findViewById(R.id.pinglun_lv);
			holder.tankuang =  (ImageView) convertView.findViewById(R.id.group_discuss_popup);
			convertView.setTag(holder);

		}
		//显示内容
		//Date(1421142096577+0800)\
		holder = (ViewHolder) convertView.getTag();
		Log.e("适配器2", ""+lms.toString());
		String str = lms.get(position).getCreatedTime();
		int fromIndex = str.indexOf("(")+1;
		int endIndex = str.indexOf("+");
		String date = str.substring(fromIndex, endIndex);
		holder.CreatedTime.setText(new SimpleDateFormat("yyyy-MM-dd").format(Long.parseLong(date)));
		//holder.photo.setImageResource(Integer.parseInt(photoList.get(position).getPhotoUrl()));
		holder.Content.setText(lms.get(position).getContent());
		holder.UserName.setText(lms.get(position).getUserName());
		holder.Dianzan.setText(String.valueOf(lms.get(position).getDianzan()));
		
		//为防止layout界面上的EditText在进入页面时就弹出输入法,隐藏软键盘  
//		getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);  
		  
		holder.tankuang.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				titlePopup = new TitlePopup(mcontext, TanKuangUtil.dip2px(mcontext, 165), TanKuangUtil.dip2px(
						mcontext, 40));
				titlePopup
						.addAction(new ActionItem(mcontext, "赞", R.drawable.circle_praise));
				titlePopup.addAction(new ActionItem(mcontext, "评论",
						R.drawable.circle_comment));
				titlePopup.setItemOnClickListener(new OnItemOnClickListener() {
					
					@Override
					public void onItemClick(ActionItem item, int position) {
						// TODO Auto-generated method stub
						 
						//写监听事件的是实现就行
					switch (position) {
					case 0:
						//赞
						Log.i("这个是》》》》》》》》》", String.valueOf(position));
						getvalue(position);
						Zan();
						
						break;
					case 1:
						//评论
						Log.i("这个是》》》》》》》》》", String.valueOf(position));
						//为防止layout界面上的EditText在进入页面时就弹出输入法,隐藏软键盘  
					//	getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);  
						
						
					//	按下popupwindow评论按钮后先让输入法弹出， 让评论popupwindow隐藏，显示EditText
						layout_pinglunbuju = flater.inflate(R.layout.activity_circle_friends_homepage, null);
						LinearLayout	loyout=	(LinearLayout) layout_pinglunbuju.findViewById(R.id.tankuang_buju);
						shuru_et = (EditText) loyout.findViewById(R.id.shuru_et);
						fasong_bt = (Button) loyout.findViewById(R.id.fasong_bt);
						Log.i(">>>>>>>>>>>>>>>>>", "已经执行到这里1");
//						EditText disInputText = (EditText) convertView.findViewById(R.id.popu_comment); 
//				        //disInputText.requestFocus();  
						InputMethodManager imm = (InputMethodManager)mcontext.getSystemService(Context.INPUT_METHOD_SERVICE);       
                        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
                        titlePopup.dismiss();
                        Log.i(">>>>>>>>>>>>>>>>>", "已经执行到这里2");
                        loyout.setVisibility(View.VISIBLE);
                        Log.i(">>>>>>>>>>>>>>>>>", "已经执行到这里3");
                        shuru_et.setFocusableInTouchMode(true);
                        shuru_et.requestFocus();	
                        Log.i(">>>>>>>>>>>>>>>>>", "已经执行到这里4");  
                        break;

					default:
						break;
					}	
					 		
					}

					private void Zan() {
						// TODO Auto-generated method stub
						ZanRequest zanReq = new ZanRequest();
						zanReq.setDianzanrenkey(zhanghukey);
						Log.i("账户key", ""+zhanghukey);
						zanReq.setXinqingkey(xinqingkey);
						Log.i("心情key", ""+xinqingkey);
						zanReq.setFaburenkey(faxinqingkey);
						Log.i("发心情key", ""+faxinqingkey);
						  NetManager.execute(NetManager.DIANZAN,
								  zanReq,new JsonHttpResponseHandler(){
							  @Override
							public void onSuccess(int statusCode, Header[] headers,
									JSONObject response) {
								// TODO Auto-generated method stub
								super.onSuccess(statusCode, headers, response);
							ZanResponse zanResp=new ZanResponse();
							zanResp.setJsonObject(response);
							try {
								flag = zanResp.getCode();
								info = zanResp.getInfo();
								log();
							} catch (Exception e) {
								// TODO: handle exception
								e.printStackTrace();
							}
							  }
						  });
						
						
						
						
						
						
					}
				});
				titlePopup.setAnimationStyle(R.style.cricleBottomAnimation);
				titlePopup.show(arg0);
			}
		});
		holder.pinglunLMs.setAdapter(new MoodCommentAdapter(lms.get(position).getPinglunLMs(), mcontext)); 
		return convertView;
	}
	
	class ViewHolder
	{
		ImageView HeadPicture;
		TextView UserName;
		TextView Content;
		ImageView PhotoUrl1;
		ImageView PhotoUrl2;
		ImageView PhotoUrl3;
		TextView CreatedTime;
		TextView Dianzan;
		ListView pinglunLMs;
		ImageView tankuang; 
	}
	//添加数据
	public void addItem(XinqingLM xinqingLM){
		lms.add(xinqingLM);
	}
	 
			
}
