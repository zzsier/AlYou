package com.imalu.alyou.adapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.imalu.alyou.R;
import com.imalu.alyou.domain.UserLM;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
public class FansAdapter extends BaseAdapter  {
	private ArrayList<UserLM> fans= new ArrayList<UserLM>();
	private Context context;
	private LayoutInflater inflater;
	public FansAdapter(Context context, ArrayList<UserLM> fans) {
		this.context=context;
		this.fans=fans;
		inflater=LayoutInflater.from(context);
	}
	@Override
	public int getCount() {
		return fans.size();
	}
	@Override
	public Object getItem(int position) {
		return fans.get(position);
	}
	@Override
	public long getItemId(int position) {
		return fans.get(position).getId();
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder=null;
		if(convertView==null){
			holder=new ViewHolder();
			if(position%2==0){
				convertView=inflater.inflate(R.layout.fans_item_1,null);
			}else if(position%2!=0){
				convertView=inflater.inflate(R.layout.fans_item_2,null);
			}
			holder.imageView=(ImageView)convertView.findViewById(R.id.user_photo_image);
			holder.textView1=(TextView)convertView.findViewById(R.id.username_text);
			holder.textView2=(TextView)convertView.findViewById(R.id.mood_text);
			convertView.setTag(holder);
		}
		holder=(ViewHolder) convertView.getTag();
		if(fans.get(position).getUserName().equals("null")){
			holder.textView1.setText("未设置");
		}else{
		holder.textView1.setText(fans.get(position).getUserName());
		}
		if(fans.get(position).getGexingQianming().equals("null")){
			holder.textView2.setText("未设置");
		}else{
			holder.textView2.setText(fans.get(position).getGexingQianming());
		}
		
		return convertView;
	}
	public class ViewHolder{
		ImageView imageView;
		TextView textView1;
		TextView textView2;
	}

}
