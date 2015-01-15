package com.imalu.alyou.adapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import com.imalu.alyou.R;
import com.imalu.alyou.adapter.ChooseVideoAdapter.ViewHolder;
import com.imalu.alyou.domain.XinqingLM;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;




public class CircleOfFriendsAdapter extends BaseAdapter{
	ArrayList<XinqingLM> lms;
	Context mcontext;
	private ViewHolder holder;
	private LayoutInflater flater;

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
			this.lms = lms;
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
	}
	//添加数据
	public void addItem(XinqingLM xinqingLM){
		lms.add(xinqingLM);
	}
}
