package com.imalu.alyou.adapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import com.imalu.alyou.R;
import com.imalu.alyou.domain.PhotoAlbum;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class FriendListAdapter extends BaseAdapter{
	ArrayList<PhotoAlbum> photoList;
	Context context;
	private ViewHolder holder;
	private LayoutInflater inflater;
	public FriendListAdapter(ArrayList<PhotoAlbum> photoList, Context context) {
		super();
		this.context = context;
		inflater = LayoutInflater.from(context);
		if(photoList == null)
		{
			photoList = new ArrayList<PhotoAlbum>();
		}else
		{
			this.photoList = photoList;
		}
	}

	@Override
	public int getCount() {
		return photoList.size();
	}

	@Override
	public Object getItem(int position) {
		return photoList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		holder = null;
		if(convertView == null)
		{
			holder = new ViewHolder();
			convertView = inflater.inflate(R.layout.item_photoslist, null);
			holder.date = (TextView)convertView.findViewById(R.id.friendscicle_date_tv);
			//holder.photo = (ImageView)convertView.findViewById(R.id.friendscircle_photo_iv);
			holder.content = (TextView)convertView .findViewById(R.id.friendscircle_content_tv);
			convertView.setTag(holder);
			
		}
		//  /Date(1421142096577+0800)\
			holder = (ViewHolder) convertView.getTag();
			PhotoAlbum photoAlbum = photoList.get(position);
			String str = photoAlbum.getDate();
			int fromIndex = str.indexOf("(")+1;
			int endIndex = str.indexOf("+");
			String date = str.substring(fromIndex, endIndex);
			holder.date.setText(new SimpleDateFormat("yyyy-MM-dd").format(Long.parseLong(date)));
			//holder.photo.setImageResource(Integer.parseInt(photoAlbum.getPhotoUrl()));
			holder.content.setText(photoAlbum.getContent());
			
		return convertView;
	}
	class ViewHolder
	{
		TextView date,content;
		ImageView photo;
	}
	//添加数据
	  public void addItem(PhotoAlbum photoAlbum){
		  photoList.add(photoAlbum);
	  }
}
