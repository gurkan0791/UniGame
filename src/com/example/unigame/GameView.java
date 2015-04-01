package com.example.unigame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;

public class GameView extends View {

	
	private final int FPS = 30;
	Handler h;
	private  float ballX;
	private float ballY;
	public float getballY() {
		return this.ballX;
	}
	public void setballY(float y) {
		this.ballY = y;
	
		Log.e("GameViewX",String.valueOf(this.ballX));
	}
	public float getballX() {
		return this.ballX;
	}
	public void setballX(float x) {
		this.ballX = x;
	
		Log.e("GameViewX",String.valueOf(this.ballX));
	}
	public GameView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		h = new Handler();
	}
	 private Runnable r = new Runnable() {
         @Override
         public void run() {
        	 if (Looper.myLooper() == null) {
        	        Looper.prepare();
        	    }
                 invalidate();
         }
 };
	
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		Game g = new Game();
		Paint green = new Paint();
		green.setColor(Color.GREEN);
		green.setStyle(Paint.Style.FILL);
		Rect ourRect = new Rect();
		ourRect.set(0,0,canvas.getWidth(),170);
		canvas.drawRect(ourRect, green);
	//	Log.d("Cooridnate", String.valueOf(this.ballX));
		
		canvas.drawCircle(this.ballX, this.ballY, 100, green);
		h.postDelayed(r, FPS);
	
	}
}
