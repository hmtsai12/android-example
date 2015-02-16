package com.example.viewdemo1;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

public class GameView extends View {

	private int cx;
	private int cy;
	private Paint p;

	public GameView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		this.cx = 20;
		this.cy = 20;
		this.p = new Paint();
		p.setColor(Color.RED);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		canvas.drawCircle(cx, cy, 10, p);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		switch(event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			return true;
		case MotionEvent.ACTION_UP:
			int x = (int)event.getX();
			int y = (int)event.getY();
			changePosition(x, y);
		}
		return super.onTouchEvent(event);
	}

	private void changePosition(int x, int y) {
		// TODO Auto-generated method stub
		this.cx = x;
		this.cy = y;
		this.invalidate();
	}

}
