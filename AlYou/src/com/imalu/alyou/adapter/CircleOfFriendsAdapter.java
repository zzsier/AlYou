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


import com.easemob.util.LatLng;
import com.imalu.alyou.AlUApplication;
import com.imalu.alyou.R;
import com.imalu.alyou.activity.ActionItem;
import com.imalu.alyou.activity.Activity_Circle_Friends_homepage;
import com.imalu.alyou.activity.TitlePopup;
import com.imalu.alyou.activity.TitlePopup.OnItemOnClickListener;
import com.imalu.alyou.domain.PingLunLM;
import com.imalu.alyou.domain.XinqingLM;
import com.imalu.alyou.net.JsonHttpResponseHandler;
import com.imalu.alyou.net.NetManager;
import com.imalu.alyou.net.request.ShanchuRequest;
import com.imalu.alyou.net.request.XinqingPinglunRequest;
import com.imalu.alyou.net.request.ZanRequest;
import com.imalu.alyou.net.response.BingGonghuiResponse;
import com.imalu.alyou.net.response.ShanchuResponse;
import com.imalu.alyou.net.response.XinqingPinglunResponse;
import com.imalu.alyou.net.response.ZanResponse;
import com.imalu.alyou.utils.TanKuangUtil;
import com.imalu.alyou.widget.PasteEditText;

public class CircleOfFriendsAdapter extends BaseAdapter{
	ArrayList<XinqingLM> lms;
	ArrayList<PingLunLM>pls;
	Activity_Circle_Friends_homepage mcontext;
	private ViewHolder holder;
	private LayoutInflater flater;
	private TitlePopup titlePopup;
	private String xinqingkey;
	private String zhanghukey;
	private String faxinqingkey;
	private Boolean flag;
	private String info;
	private View zhengti_buju_layout;
	private View fasong_buju_layout;
	 
	public CircleOfFriendsAdapter(ArrayList<XinqingLM> lms, Activity_Circle_Friends_homepage context){

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
	public View getView(final int position, View convertView, ViewGroup parent) {
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
			holder.Shanchu = (TextView) convertView.findViewById(R.id.shanchu_tv);
			holder.tankuang.setTag(position);
			holder.Shanchu.setTag(position);	
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
		//holder.Dianzan.setText(String.valueOf(lms.get(position).getDianzan()));
		holder.Shanchu.setText(quzhi(position));	 
		holder.Shanchu.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				 int sahnchupos=(Integer) holder.Shanchu.getTag();
				// TODO Auto-generated method stub
				//删除
				Shnachu(position);
				lms.remove(sahnchupos);			
				mcontext.test();
			}
		}) ;
		holder.tankuang.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				final int pos=(Integer) holder.tankuang.getTag();
				titlePopup = new TitlePopup(mcontext, TanKuangUtil.dip2px(mcontext, 165), 
											TanKuangUtil.dip2px(mcontext, 40));
				
				titlePopup.addAction(new ActionItem(mcontext, "赞", R.drawable.circle_praise));
				titlePopup.addAction(new ActionItem(mcontext, "评论",R.drawable.circle_comment));	
				
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
							titlePopup.dismiss();
							mcontext.showKeyBoard(pos);			 
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
		TextView Shanchu;
	}
	protected void Shnachu(int position) {
		// TODO Auto-generated method stub
		ShanchuRequest shanchuReq = new ShanchuRequest();
		shanchuReq.setUserkey(zhanghukey);
		Log.i("删除的用户key",""+zhanghukey);
		shanchuReq.setXinqingkey(lms.get(position).getXingqingkey());
		Log.i("删除的心情key",""+lms.get(position).getXingqingkey());
		NetManager.execute(NetManager.SHANCHU,
				shanchuReq,new JsonHttpResponseHandler(){
			@Override
			public void onSuccess(int statusCode, Header[] headers,
					JSONObject response) {
				// TODO Auto-generated method stub
				super.onSuccess(statusCode, headers, response);
				ShanchuResponse shanchuResp = new ShanchuResponse();
				shanchuResp.setJsonObject(response);
				try {
					flag = shanchuResp.getCode();
					info = shanchuResp.getInfo();
					log();
					
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
		});	
	}
	private String quzhi(int position) {
		// TODO Auto-generated method stub
		zhanghukey = AlUApplication.getMyInfo().getKey();
		Log.i("用户key............", ""+zhanghukey);
		faxinqingkey = lms.get(position).getUserKey();
		String s="";
		Log.i("发心情的用户key............", ""+faxinqingkey);			
		if (zhanghukey.equals(faxinqingkey)) {
			s="删除";
			Log.i("我>>>>>>>>>>>>>>>>>", "已经执行到这里10");  
		}else{
			s="";
			Log.i("我没>>>>>>>>>>>>>>>>>", "已经执行到这里10");  

		}
		return s;
	}
	//添加数据
	public void addItem(XinqingLM xinqingLM){
		lms.add(xinqingLM);
	}
 
}
