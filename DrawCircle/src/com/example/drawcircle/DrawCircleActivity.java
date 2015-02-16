package com.example.drawcircle;

import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class DrawCircleActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_draw_circle);
		setContentView(new View(this){
			
			Paint vPaint = new Paint();
			private int i = 0;
			
			protected void onDraw(Canvas canvas){
				super.onDraw(canvas);
				
				vPaint.setColor(0xff00ffff);
				vPaint.setAntiAlias(true);
				vPaint.setStyle(Paint.Style.STROKE);
				
				canvas.drawArc(new RectF(60, 120, 260, 320), 0, i, true, vPaint);
				
				if((i+=10) > 360)
					i = 0;
				
				invalidate();
			}
			
			
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.draw_circle, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
