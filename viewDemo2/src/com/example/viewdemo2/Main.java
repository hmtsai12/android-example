package com.example.viewdemo2;

import android.app.Activity;
import android.os.Bundle;
import android.widget.RelativeLayout;

public class Main extends Activity {
	public GameView viewer = null;
	public RelativeLayout rlayout = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_layout);
		viewer = new GameView(this);
		rlayout = (RelativeLayout)findViewById(R.id.rLayout);
		rlayout.addView(viewer);
	}

}
