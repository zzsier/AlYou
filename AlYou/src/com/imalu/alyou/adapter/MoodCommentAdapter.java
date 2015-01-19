package com.imalu.alyou.adapter;

import java.util.ArrayList;

import com.imalu.alyou.R;
import com.imalu.alyou.domain.PingLunLM;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;




public class MoodCommentAdapter extends BaseAdapter{
	ArrayList<PingLunLM> pls;
	Context context;
	private ViewHolder holder;
	private LayoutInflater flater;

	public MoodCommentAdapter(ArrayList<PingLunLM> pls, Context context){
		super();
		this.context = context;
		flater = LayoutInflater.from(context);
		if(this.pls == null)
		{
			this.pls = new ArrayList<PingLunLM>();
  		    this.pls=pls;
			Log.i("评论的适配器》》》》》》》》》》", ""+pls);
		}else
		{
			 
			this.pls = pls;
	}
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		if (pls==null) {
			return 0;
		}else {
			return pls.size();
		}	
	}

	@Override
	public Object getItem(int  position) {
		// TODO Auto-generated method stub
		return pls.get( position);
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
			convertView=flater.inflate(R.layout.item_pinglun, null);
			//	将convertView中的相关组件赋给ViewHolder中的成员变量
			holder = new ViewHolder();
			holder.HuifurenName =  (TextView) convertView.findViewById(R.id.pinglunnicheng_tv);
			holder.Content = (TextView) convertView.findViewById(R.id.pinglunneirong_tv);
			holder.diantv=(TextView) convertView.findViewById(R.id.dian_tv);
			convertView.setTag(holder);
		}
		holder = (ViewHolder) convertView.getTag();
		//显示内容
		//Date(1421142096577+0800)\
		holder.Content.setText(pls.get(position).getContent());
		holder.HuifurenName.setText(pls.get(position).getHuifurenName()); 
		return convertView;
	}
	class ViewHolder
	{ 
		TextView Content;
		TextView HuifurenName; 
		TextView diantv;
	}
	//添加数据
	public void addItem(PingLunLM pingLunLM){
		pls.add(pingLunLM);
	}
}
