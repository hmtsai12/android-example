package com.example.viewdemo1;

import android.app.Activity;
import android.os.Bundle;
import android.widget.RelativeLayout;

public class ViewDemo extends Activity {
	private GameView viewer = null;
	private RelativeLayout testlayout = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mainlayout);
		
		viewer = new GameView(this);
		testlayout = (RelativeLayout)findViewById(R.id.mainlayout);
		testlayout.addView(viewer);
	}

}
