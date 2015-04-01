package com.example.unigame;


import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.Window;

public class Game extends Activity implements OnTouchListener{
//	static GameView gameView ;
	static GameSurView gameSurView;
//	public Game(){
//		gameView = new GameView(this);
//		
//	}
	static ServerGame s;
	static Ball b;
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		gameSurView = new GameSurView(this);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		 s = new ServerGame();
		x = y = 0;
		b = new Ball(0,0);
		gameSurView.setOnTouchListener(this);
		setContentView(gameSurView);
		
	}
	
	private float x;
	private float y;
	float getY() {
		return y;
	}
	void setY(float y) {
		this.y = y;
		if (gameSurView != null) {
			gameSurView.setBallY(this.y);
		}
	}
	public float getX() {
		return this.x;
	}
	public void setX(float x) {
		this.x = x;
		if (gameSurView != null) {
			gameSurView.setBallX(this.x);
		}
		
		Log.e("SetGameX",String.valueOf(this.x));
	}
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		gameSurView.pause();
	}
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		gameSurView.resume();
	}
	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		gameSurView.stop();
	}
	
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		
		try {
	        Thread.sleep(16);
	    } catch (InterruptedException e) {
	        e.printStackTrace();
	    }
		switch (event.getAction()) {
//		case MotionEvent.ACTION_DOWN:
//			x = event.getX();
//			y = event.getY();
//			b = new Ball(event.getX(), event.getY());
//			break;
//		case MotionEvent.ACTION_UP:
//			x = event.getX();
//			y = event.getY();
//			b = new Ball(event.getX(), event.getY());
//			break;
		case MotionEvent.ACTION_MOVE:
//			x = event.getX();
//			y = event.getY();
			b = new Ball(event.getX(), event.getY());
			break;
		}
//		gameSurView.setBalX(x);
//		gameSurView.setBalY(y);
		gameSurView.setBallXY(b);
//		s.setBalx(x);
//		s.setBaly(y);
		s.setBallXY(b);
		return true;
	}
	
//	private  String sonuc;
//	public  void oyunSonu(String gelenSonuc){
//		sonuc = gelenSonuc;
//		this.runOnUiThread(new Runnable() {
//			
//			@Override
//			public void run() {
//				Toast.makeText(getBaseContext(), "Hello", Toast.LENGTH_SHORT).show();
//				
//			}
//		});
//	}
//	
//	
}
