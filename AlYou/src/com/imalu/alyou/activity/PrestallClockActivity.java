package com.imalu.alyou.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

import com.imalu.alyou.R;
import com.imalu.alyou.db.ClockDbService;
import com.imalu.alyou.db.gen.Clock;

public class PrestallClockActivity extends BaseActivity{
	private Button cancel;
	int duration = 360;
	private Button savePreClock;
	private EditText preClockname;
	private TextView preClocktimer;
	private TextView preClocktimer1;
	private TextView preClocktimer2;
	private SeekBar preSeekBar1;
	private SeekBar preSeekBar2;
	private SeekBar preSeekBar3;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_preinstall_timer);

		initViews();
		
		setListeners();
		preSeekBar1.setMax(duration);
	}


	private void setListeners() {
		preSeekBar1.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

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
				
				preClocktimer.setText(String.valueOf(progress));
				preSeekBar2.setMax(progress);
				preSeekBar3.setMax(progress);
			}
		});
		
		preSeekBar2.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			
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
				
				preClocktimer1.setText(String.valueOf(progress));
			}
		});
		
		preSeekBar3.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			
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
				
				preClocktimer2.setText(String.valueOf(progress));
			}
		});
		cancel.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		
		savePreClock.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Clock clock = new Clock();
				short type = 1;
				short order = 0;
				clock.setType(type);
				String time1 = preClocktimer1.getText().toString();
				String time2 = preClocktimer2.getText().toString();
				String time = preClocktimer.getText().toString();
				String name = preClockname.getText().toString();
				Intent intent = new Intent();
				intent.putExtra("name", name);
				//intent.putExtra("time2",time2);
				intent.setClass(PrestallClockActivity.this,TimerListActivity.class);
				startActivity(intent);
				clock.setClock1(Integer.parseInt(time1));
				clock.setClock2(Integer.parseInt(time2));
				clock.setClock3(Integer.parseInt(time));
				clock.setDescription(name);
				clock.setOrder(order);
				clock.setReadonly(false);
				ClockDbService.getInstance(PrestallClockActivity.this).saveClock(clock);
				finish();
				
			}
		});
	}

	private void initViews() {
		savePreClock = (Button) findViewById(R.id.pre_saveClock);
		cancel = (Button) findViewById(R.id.cancel);
		
		preClockname = (EditText) findViewById(R.id.preclockname);
		preClocktimer = (TextView) findViewById(R.id.pre_time_big_create_timer);
		preClocktimer1 = (TextView) findViewById(R.id.pre_time_big_create_timer_1);
		preClocktimer2 = (TextView) findViewById(R.id.pre_time_big_create_timer_2);
		preSeekBar1 = (SeekBar)findViewById(R.id.pre_seekBar1);
		preSeekBar2 = (SeekBar)findViewById(R.id.pre_seekBar2);
		preSeekBar3 = (SeekBar)findViewById(R.id.pre_seekBar3);
		
	}


	public void back(View v) {
		finish();
	}

}
