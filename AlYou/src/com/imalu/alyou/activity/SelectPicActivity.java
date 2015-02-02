package com.imalu.alyou.activity;

import java.io.ByteArrayOutputStream;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;

import com.imalu.alyou.R;

public class SelectPicActivity extends BaseActivity{
	private ImageButton selectPic;
	int[] img = {R.drawable.clock_photo_1,

			R.drawable.actionbar_camera_icon};
	private Button saveClock;
	private Button cancel;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_select_timer_pic);

		initViews();
		setLiteners();
	}

	private void setLiteners() {
		cancel.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});

		saveClock.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				Bitmap bitmap = BitmapFactory.decodeResource(getResources(), img[0]);
				byte[] pic = bitmapToBytes(bitmap);
				intent.putExtra("data", pic);
				//intent.putExtra("id",img[0]);
				intent.setClass(SelectPicActivity.this,AddClockActivity.class);
				startActivity(intent);
			
			}
		});

	}
	
	// 将图片转换为二进制数组的方法  
	public byte[] bitmapToBytes(Bitmap bitmap) {  
		if (bitmap == null) {  
			return null;  
		}  
		final ByteArrayOutputStream os = new ByteArrayOutputStream();  
		// 将Bitmap压缩成PNG编码，质量为100%存储  
		bitmap.compress(Bitmap.CompressFormat.PNG, 100, os);// 除了PNG还有很多常见格式，如jpeg等。  
		return os.toByteArray();  
	}  

	public void onClick(View v)
	{
		switch (v.getId()) {
		case R.id.iv1:
		case R.id.iv2:
		case R.id.iv3:
		case R.id.iv4:
		case R.id.iv5:
		case R.id.iv6:
		case R.id.iv7:
		case R.id.iv8:
			selectPic.setImageResource(img[0]);
			break;
		}
	}
	private void initViews() {
		selectPic = (ImageButton)findViewById(R.id.add_timer_select_pic);
		saveClock = (Button) findViewById(R.id.pic_saveClock);
		cancel = (Button) findViewById(R.id.pic_cancel);
	}
	
	public void back(View v) {
		finish();
	}
}
