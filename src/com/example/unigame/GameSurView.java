package com.example.unigame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;


//Server 
public class GameSurView extends SurfaceView implements Runnable {
	
	 // desired fps
	    private final static int    MAX_FPS = 40;

	    // maximum number of frames to be skipped

	    private final static int    MAX_FRAME_SKIPS = 2;

	    // the frame period

	    private final static int    FRAME_PERIOD = 1000 / MAX_FPS;

	
	
	Bitmap arkaPlan;
	static ServerGame serverGame = new ServerGame();
	int scoreClient;
	int scoreServer;
	String puanClient;
	String puanServer;
	Thread t = null;
	SurfaceHolder holder;
	volatile boolean durum = false;
	float ballX;
	Ball b;
	static int SCREEN_WIDTH ;
	static int SCREEN_HEIGHT;
	// yeni
	static BouncingBall hareketliTop;
	//private RectF ballBounds;
	private Paint renk;
	//yeni
	public void setBallXY(Ball b){
		this.b = b;
	}
	
	public float getBallX() {
		return ballX;
	}

	public void setBallX(float ballX) {
		this.ballX = ballX;
	}

	public float getBallY() {
		return ballY;
	}

	public void setBallY(float ballY) {
		this.ballY = ballY;
	}

	float ballY;
	Game g ;
	public GameSurView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		
		final DisplayMetrics metrics = context.getResources().getDisplayMetrics();
		int width = metrics.widthPixels;
		int height = metrics.heightPixels;
		g = new Game();
		arkaPlan = BitmapFactory.decodeResource(getResources(), R.drawable.background);
		holder =getHolder();
		ballX = ballY = 0;
		b = new Ball(width/2, height-180);
		serverGame.setBallXY(b);
		//yeni
		hareketliTop = new BouncingBall(0, 0, 0, 0, 20, width/2, height/2, (float) 73.25, (float) 73.25);
		//ballBounds = new RectF();
		
		renk = new Paint();
		//yeni
		scoreClient = 0;
		scoreServer = 0;
	}
 
	@Override
	public void run() {
		Canvas c;
		
		long beginTime;
		long timeDiff;
		int sleepTime;
		int framesSkipped;
		
		sleepTime = 0;
		while (durum== true) {
			c = null;
			if (!holder.getSurface().isValid()) {
				continue;
			}
			try {
				
		    c = holder.lockCanvas();
			synchronized (holder) {
			
			beginTime = System.currentTimeMillis();
			framesSkipped = 0;
			
			c.drawBitmap(arkaPlan, 0, 0,null);
			SCREEN_WIDTH = c.getWidth();
			SCREEN_HEIGHT = c.getHeight();
			Log.d("Yükseklik", String.valueOf(SCREEN_HEIGHT));
			//c.drawARGB(255, 150, 150, 10);
		//	Paint score = new Paint();
			
			renk.setColor(Color.DKGRAY);
			renk.setStyle(Paint.Style.STROKE);
			renk.setStrokeWidth(5);
			renk.setTextSize(80);
			
			puanClient = "PUAN : "+String.valueOf(scoreClient);
			puanServer = "PUAN : "+String.valueOf(scoreServer); 
			c.drawText(puanClient, 40, 80, renk);
			c.drawText(puanServer, 40, SCREEN_HEIGHT-40, renk);
			
			//Paint green = new Paint();
			renk.setColor(Color.GREEN);
			renk.setStyle(Paint.Style.FILL);
			//client top
		//	c.drawCircle(getBallX(), 140, 100, renk);
			c.drawRect(getBallX(), 120, getBallX()+200, 180, renk);
			//Paint red = new Paint();
			renk.setColor(Color.RED);
			renk.setStyle(Paint.Style.FILL);
			//c.drawCircle(b.getX(), b.getY(), 100, red);
			
			//c.drawCircle(b.getX(),c.getHeight()-140, 100, renk);
			c.drawRect(b.getX(), c.getHeight()-180, b.getX()+200, c.getHeight()-120, renk);
			//Paint white = new Paint();
			renk.setColor(Color.WHITE);
			renk.setStrokeWidth(20);
			renk.setStyle(Paint.Style.STROKE);
			
			Rect ourRect = new Rect();
			ourRect.set(0,0,c.getWidth(),c.getHeight());
			c.drawRect(ourRect, renk);
			//Log.d("Cooridnate", String.valueOf(this.ballX));
			
			
			
//			ballBounds.set(hareketliTop.getBallX()-hareketliTop.getBallRadius()
//					, hareketliTop.getBallY()-hareketliTop.getBallRadius()
//					, hareketliTop.getBallX()+hareketliTop.getBallRadius(), hareketliTop.getBallY()+hareketliTop.getBallRadius());
//			
	  //      ballBounds.set(hareketliTop.getBallX(), hareketliTop.getBallY(), hareketliTop.getBallX()+hareketliTop.getBallRadius()
	  //      		, hareketliTop.getBallY()+hareketliTop.getBallRadius());
			renk.setColor(Color.BLACK);
			renk.setStyle(Paint.Style.FILL);
	    //      c.drawOval(ballBounds, paintTop);
			
			c.drawCircle(hareketliTop.getBallX(), hareketliTop.getBallY(), hareketliTop.getBallRadius(), renk);
	          // Update the position of the ball, including collision detection and reaction.
		
			serverGame.setBouncingBallXY(new BouncingBall(hareketliTop.getBallX(), hareketliTop.getBallY(), hareketliTop.getBallSpeedX()));
	        update();
	          timeDiff = System.currentTimeMillis() - beginTime;
	          sleepTime = (int)(FRAME_PERIOD - timeDiff);
	          if (sleepTime > 0) {
				try {
					Thread.sleep(sleepTime);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
	          while (sleepTime < 0 && framesSkipped < MAX_FRAME_SKIPS) {
	        	
	        	  
	        	   //   update();
	        	    //  serverGame.setBouncingBallXY(new BouncingBall(hareketliTop.getBallX(), hareketliTop.getBallY(), hareketliTop.getBallSpeedX()));
	      	        
	        	      sleepTime += FRAME_PERIOD;
	                  framesSkipped++;
	           }

	          
			}
		}finally{
			if (c!=null) {
				holder.unlockCanvasAndPost(c);
			}
			
			//update(); //Burda dene
		}
		}
		
	}
	private int topHýz = 0;
	//private String kazanan;
	private void update() {
		hareketliTop.setBallX(hareketliTop.getBallX()+hareketliTop.getBallSpeedX()); 
		hareketliTop.setBallY(hareketliTop.getBallY()+hareketliTop.getBallSpeedY()); 
		
		//Ölür
		//Puan yaz
      if(hareketliTop.getBallY() > SCREEN_HEIGHT || hareketliTop.getBallY() < 0){
    	  
    	  topHýz = 0;
    	  hareketliTop.setBallSpeedX((float)73.25);
    	  hareketliTop.setBallSpeedY((float)73.25);
    	  if (hareketliTop.getBallY() > SCREEN_HEIGHT ) {
//    		  if (scoreClient==40) {
//    			  kazanan = "Client";
//    			  durum = false;
//				g.oyunSonu(kazanan);
//				
//			}
			scoreClient +=10;
			serverGame.setServerScore(scoreClient);
    	  }else if(hareketliTop.getBallY() < 0){
//    		  if (scoreServer==40) {
//    			  kazanan = "Server";
//    			  durum = false;
//				g.oyunSonu(kazanan);
//			}
    		scoreServer +=10;
    		serverGame.setClientScore(scoreServer);
    	  }
    	  hareketliTop.setBallX(SCREEN_WIDTH/2);
    	  hareketliTop.setBallY(SCREEN_HEIGHT/2);
      }
      //Yan Duvarlar
      if(hareketliTop.getBallX()+hareketliTop.getBallRadius() > SCREEN_WIDTH-20 || hareketliTop.getBallX() -hareketliTop.getBallRadius() < 20){
    	  hareketliTop.setBallSpeedX(hareketliTop.getBallSpeedX()*(-1));
      }
      //Çarpýþma
//      if (hareketliTop.getBallX()+hareketliTop.getBallRadius()>b.getX()-100 && hareketliTop.getBallX()-hareketliTop.getBallRadius()< (b.getX()+ 100) && hareketliTop.getBallY()+hareketliTop.getBallRadius() > SCREEN_HEIGHT-240) {
//		hareketliTop.setBallSpeedY(hareketliTop.getBallSpeedY()*(-1)-(++topHýz));
//      }
      if (hareketliTop.getBallX()+hareketliTop.getBallRadius()>b.getX() && hareketliTop.getBallX()-hareketliTop.getBallRadius()< (b.getX()+ 200) && hareketliTop.getBallY()+hareketliTop.getBallRadius() > SCREEN_HEIGHT-180&& hareketliTop.getBallY()+hareketliTop.getBallRadius() < SCREEN_HEIGHT-130) {
  		hareketliTop.setBallSpeedY(hareketliTop.getBallSpeedY()*(-1));
  		//
        }
      //Çarpýþma Client
      if (hareketliTop.getBallX()+hareketliTop.getBallRadius()>getBallX() && hareketliTop.getBallX()-hareketliTop.getBallRadius()< (getBallX()+ 200) && hareketliTop.getBallY()-hareketliTop.getBallRadius() < 180 && hareketliTop.getBallY()-hareketliTop.getBallRadius() > 130) {
		hareketliTop.setBallSpeedY(hareketliTop.getBallSpeedY()*(-1));
      }
      
//        try {
//			Thread.sleep(5);
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
//         else if (ballX - ballRadius < xMin) {
//           ballSpeedX = -ballSpeedX;
//           ballX = xMin+ballRadius;
//        }
//        if (ballY + ballRadius > yMax) {
//           ballSpeedY = -ballSpeedY;
//           ballY = yMax - ballRadius;
//        } else if (ballY - ballRadius < yMin) {
//           ballSpeedY = -ballSpeedY;
//           ballY = yMin + ballRadius;
//        }
		
	}


	public void pause(){
		durum = false;
		while (true) {
			try {
				t.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}
	
	public void resume(){
		durum = true;
		t = new Thread(this);
		t.start();
		
	}
	public void stop(){
		durum = false;
		if (t!=null) {
			t.interrupt();
		}
	}
	
}
