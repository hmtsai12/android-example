package com.example.viewdemo2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
//import android.os.Handler;
//import android.os.Message;
import android.view.MotionEvent;
import android.view.View;

public class GameView extends View {

	private int cx;
	private int cy;
	private Paint p;
	/*private Handler mHandler = new Handler() {
		public void handleMessage(Message msg) {
			changePosition(msg.arg1, msg.arg2);
		}
	};*/

	public GameView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		this.cx = 20;
		this.cy = 20;
		this.p = new Paint();
		p.setColor(Color.RED);
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
			GameThread gameThread = new GameThread(x, y);
			gameThread.start();
			//changePosition(x, y);
			return true;
		}
		return super.onTouchEvent(event);
	}

	public void changePosition(int x, int y) {
		// TODO Auto-generated method stub
		this.cx = x;
		this.cy = y;
		//this.invalidate();
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		canvas.drawCircle(cx, cy, 10, p);
	}
	
	private class GameThread extends Thread {
		private int x;
		private int y;
		
		public GameThread(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			super.run();
			/*Message msg = mHandler.obtainMessage();
			msg.arg1 = x;
			msg.arg2 = y;
			msg.sendToTarget();*/
			changePosition(x, y);
			postInvalidate();
		}
	}
}
