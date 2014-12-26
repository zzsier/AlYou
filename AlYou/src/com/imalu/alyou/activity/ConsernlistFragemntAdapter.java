package com.imalu.alyou.activity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.imalu.alyou.R;
import com.imalu.alyou.domain.ConsernPerson;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class ConsernlistFragemntAdapter extends BaseAdapter{
private ArrayList< ConsernPerson> persons= new ArrayList<ConsernPerson>();
private Context context;
private LayoutInflater inflater;
	public ConsernlistFragemntAdapter(Context context,ArrayList<ConsernPerson>persons) {
		// TODO Auto-generated constructor stub
		this.context=context;
		this.persons=persons;
		inflater=LayoutInflater.from(context);
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return persons.size();
	}
	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return persons.get(position);
	}
	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return persons.get(position).getId();
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder holder = null;
		if(convertView==null){
			holder= new ViewHolder();
			if(position%2==0){
				convertView=inflater.inflate(R.layout.concern_item_1, null);
			}else if(position%2!=0){
				convertView=inflater.inflate(R.layout.concern_item_2, null);
			}
			holder.imageView=(ImageView) convertView.findViewById(R.id.user_photo_image);
			holder.textView=(TextView) convertView.findViewById(R.id.username_text);
			convertView.setTag(holder);
		}
	
		holder=(ViewHolder) convertView.getTag();
		if(persons.get(position).getUsername().equals("null")){
			holder.textView.setText("未设置");
		}else{
		holder.textView.setText(persons.get(position).getUsername());
		}
		return convertView;
	}
	
	public class ViewHolder{
		ImageView imageView;
		TextView textView;
	}
}
