package com.example.viewdemo3;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

public class MyView extends View {

	private Paint mPaint;
	private static final String mString = "Welcome to view demo";
	public MyView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public MyView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		
		mPaint = new Paint();
		mPaint.setColor(Color.RED);
		mPaint.setStyle(Style.FILL);
		canvas.drawRect(new Rect(10,10, 100, 100), mPaint);
		mPaint.setColor(Color.BLUE);
		canvas.drawText(mString, 10, 10, mPaint);
	}

}
