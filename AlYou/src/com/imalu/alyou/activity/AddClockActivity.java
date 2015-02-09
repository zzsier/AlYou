package com.imalu.alyou.activity;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

import com.imalu.alyou.AlUApplication;
import com.imalu.alyou.R;
import com.imalu.alyou.db.ClockDbService;
import com.imalu.alyou.db.gen.Clock;
import com.imalu.alyou.net.Base64;

public class AddClockActivity extends BaseActivity{
	private Button saveClock;
	private Button cancel;
	private EditText clockname;
	private TextView clocktimer;
	private TextView clocktimer1;
	private TextView clocktimer2;
	private SeekBar seekBar1,seekBar2,seekBar3;
	int duration = 360;
	private ImageButton selectPic; 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_timer);

		initViews();

		setListeners();
		seekBar1.setMax(duration);
	}


	private void setListeners() {
		selectPic.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(AddClockActivity.this, SelectPicActivity.class));				
			}
		});
		seekBar1.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {

			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {

				clocktimer.setText(String.valueOf(progress));
				seekBar2.setMax(progress);
				seekBar3.setMax(progress);
			}
		});

		seekBar2.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {

			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {

				clocktimer1.setText(String.valueOf(progress));
			}
		});

		seekBar3.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {

			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {

				clocktimer2.setText(String.valueOf(progress));
			}
		});
		cancel.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});

		saveClock.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Clock clock = new Clock();
				short type = 1;
				short order = 0;
				clock.setType(type);
				clock.setClock1(Integer.parseInt(clocktimer1.getText().toString()));
				clock.setClock2(Integer.parseInt(clocktimer2.getText().toString()));
				clock.setClock3(Integer.parseInt(clocktimer.getText().toString()));
				clock.setDescription(clockname.getText().toString());
				clock.setOrder(order);
				clock.setReadonly(false);
				Intent intent = getIntent();
				byte[] pic = intent.getByteArrayExtra("data");
				clock.setPic(pic);
			//	Bitmap bitmap = BitmapFactory.decodeByteArray(pic,0,pic.length);
			//	selectPic.setImageBitmap(bitmap);
				ClockDbService.getInstance(AddClockActivity.this).saveClock(clock);
				ClockDbService.getInstance(AlUApplication.applicationContext).loadAllClock().add(clock);
				Log.e("CLOCKLIST", ""+ClockDbService.getInstance(AlUApplication.applicationContext).loadAllClock().size());
				finish();
			}
		});
	}

	private void initViews() {
		saveClock = (Button) findViewById(R.id.saveClock);
		cancel = (Button) findViewById(R.id.cancel);

		clockname = (EditText) findViewById(R.id.clockname);
		clocktimer = (TextView) findViewById(R.id.time_big_create_timer);
		clocktimer1 = (TextView) findViewById(R.id.time_big_create_timer_1);
		clocktimer2 = (TextView) findViewById(R.id.time_big_create_timer_2);
		seekBar1 = (SeekBar)findViewById(R.id.seekBar1);
		seekBar2 = (SeekBar)findViewById(R.id.seekBar2);
		seekBar3 = (SeekBar)findViewById(R.id.seekBar3);
		selectPic = (ImageButton)findViewById(R.id.add_timer_create_timer);
	}

	// 将数组转换成字符串   
		public String byteToBase64(byte[] tBytes) {   
			String tString = "";   
			String tSentString = "";   

			tString = Base64.encodeToString(tBytes, 0);   
			try {   
				tSentString = URLEncoder.encode(tString, "UTF-8");   
			} catch (UnsupportedEncodingException e) {   
				e.printStackTrace();   
			}   
			return tSentString;   
		}  
	public void back(View v) {
		finish();
	}

}
