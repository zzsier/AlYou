package com.imalu.alyou.adapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.imalu.alyou.R;
import com.imalu.alyou.domain.ConsernPerson;
import com.imalu.alyou.domain.Sociaty;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class SociatylistFragmentAdapter extends BaseAdapter{
private ArrayList< Sociaty> sociaties= new ArrayList<Sociaty>();
private Context context;
private LayoutInflater inflater;
	public SociatylistFragmentAdapter(Context context,ArrayList<Sociaty>sociaties) {
		// TODO Auto-generated constructor stub
		this.context=context;
		this.sociaties=sociaties;
		inflater=LayoutInflater.from(context);
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return sociaties.size();
	}
	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return sociaties.get(position);
	}
	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return sociaties.get(position).getId();
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder holder = null;
		if(convertView==null){
			holder= new ViewHolder();
			if(position%2==0){
				convertView=inflater.inflate(R.layout.socaity_item_1, null);
			}else if(position%2!=0){
				convertView=inflater.inflate(R.layout.socaity_item_2, null);
			}
			holder.imageView=(ImageView) convertView.findViewById(R.id.socaity_photo_image);
			holder.jifen=(TextView) convertView.findViewById(R.id.socaity_jifen_text);
			holder.socaityname=(TextView) convertView.findViewById(R.id.socaity_name_text);
			holder.summary=(TextView) convertView.findViewById(R.id.socaity_summary_text);
			convertView.setTag(holder);
		}
	
		holder=(ViewHolder) convertView.getTag();
		if(sociaties.get(position).getSocietyname().equals("null")){
			holder.socaityname.setText("未设置");
		}else{
		holder.socaityname.setText(sociaties.get(position).getSocietyname());
		}
		if(sociaties.get(position).getSocietysummary().equals("null")){
			holder.summary.setText("未设置");
		}else{
			holder.summary.setText(sociaties.get(position).getSocietysummary());
			}
		holder.jifen.setText(String.valueOf(sociaties.get(position).getJifen()));
		return convertView;
	}
	
	public class ViewHolder{
		ImageView imageView;
		TextView jifen;
		TextView socaityname;
		TextView summary;
	}
}
