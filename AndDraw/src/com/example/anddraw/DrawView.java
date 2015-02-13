package com.example.anddraw;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class DrawView extends View {

	private Paint pat1 = new Paint();
	//private Canvas cvs = new Canvas();
	private boolean drawExtFlag = false;
	private boolean drawClearFlag = false;
	private Paint patBitmap;
	private Bitmap pasteImg;
	private boolean startTouch;
	private float nowXPos;
	private float nowYPos;
	private boolean touchMoveFlag;
	public DrawView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		initBitmap();
		setBackgroundColor(Color.rgb(0, 56, 85));
	}
	
	private void initBitmap() {
		// TODO Auto-generated method stub
		patBitmap = new Paint();
		pasteImg = BitmapFactory.decodeResource(getResources(), R.drawable.logo);
	}
	
	public void onDraw(Canvas cs)
	{
		super.onDraw(cs);
		
		if(drawExtFlag)
		{
			pat1.setColor(Color.YELLOW);
			pat1.setStrokeWidth(2);
			pat1.setStyle(Style.STROKE);
			cs.drawCircle(100, 240, 80, pat1);
			
			pat1.setColor(Color.GREEN);
			pat1.setStrokeWidth(2);
			pat1.setStyle(Style.STROKE);
			Rect rectArea = new Rect(50, 50, this.getWidth()/2, this.getHeight()/2);
			cs.drawRect(rectArea, pat1);
			drawExtFlag = false;
			cs.drawBitmap(pasteImg, 200,200, patBitmap);
		}
		if(drawClearFlag)
		{
			Paint clearPat = new Paint();
			clearPat.setColor(Color.rgb(125, 125, 125));
			Rect clearArea = new Rect(0, 0, this.getWidth(), this.getHeight());
			cs.drawRect(clearArea, clearPat);
			drawClearFlag = false;
		}
		if(startTouch)
		{
			Paint pat2 = new Paint();
			pat2.setColor(Color.WHITE);
			pat2.setAntiAlias(true);
			pat2.setTextSize(16);
			cs.drawText("move mouse catch point",30,30, pat2);
			startTouch = false;
		}
		if(touchMoveFlag)
		{
			Paint pat2 = new Paint();
			pat2.setColor(Color.WHITE);
			pat2.setAntiAlias(true);
			cs.drawText("x :" + Float.toString(nowXPos)+"y :" + Float.toString(nowYPos), 30, 30, pat2);
			touchMoveFlag = false;
		}
	}
	
	public boolean onTouchEvent(MotionEvent moe)
	{
		if(moe.getAction() == MotionEvent.ACTION_DOWN)
		{
			startTouch = true;
		}
		else if(moe.getAction() == MotionEvent.ACTION_MOVE)
		{
			nowXPos = moe.getX();
			nowYPos = moe.getY();
			touchMoveFlag = true;
		}
		this.invalidate();
		return true;
	}

	public void drawExt()
	{
		Log.d("AndDrawingActivity", "drawExt");
		drawExtFlag = true;
		this.invalidate();
	}
	public void clearCS()
	{
		Log.d("AndDrawingActivity", "clearCS");
		drawClearFlag = true;
		this.invalidate();
	}

}
