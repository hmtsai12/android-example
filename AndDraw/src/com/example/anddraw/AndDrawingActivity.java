package com.example.anddraw;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;


public class AndDrawingActivity extends Activity implements OnClickListener{
	
	private Button btnGo;
	private Button btnClear;
	private LinearLayout freeDrawLayout;
	private DrawView freeDraw;
	//private DrawView freeDraw;
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		btnGo = (Button) findViewById(R.id.btnGo);
		btnClear = (Button) findViewById(R.id.btnClear);
		
		btnGo.setOnClickListener((OnClickListener) this);
		btnClear.setOnClickListener((OnClickListener) this);
		
		freeDrawLayout = (LinearLayout) findViewById(R.id.drawLayout);
		
		freeDraw = new DrawView(AndDrawingActivity.this);
		freeDrawLayout.addView(freeDraw);
	}
	
	public void onClick(View v){
		if (v.equals(btnGo))
		{
			Log.d("AndDrawingActivity", "btnGo button press");
			freeDraw.drawExt();
		}
		else if(v.equals(btnClear))
		{
			Log.d("AndDrawingActivity", "btnClear button press");
			freeDraw.clearCS();
		}
		else
		{
			Log.d("AndDrawingActivity", "no button press");
		}
	}

}