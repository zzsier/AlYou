package com.imalu.alyou.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.imalu.alyou.R;

public class Activity_add_findhaoma extends BaseActivity{
	private  EditText  et; 
	private Button bt;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_findhaoma);
	setview();
	setListener();
	}

	private void setview() {
		// TODO Auto-generated method stub
		et = (EditText) findViewById(R.id.editText_souhaoma);
		bt = (Button) findViewById(R.id.button_sousuo);
	}

	private void setListener() {
		 bt.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
					Intent intent = new Intent(
							Activity_add_findhaoma.this,
							Activity_personal_data_tianjia.class);
					startActivity(intent);
			}
		});
}
	}
