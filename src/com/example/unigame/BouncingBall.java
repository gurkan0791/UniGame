package com.example.unigame;

import android.graphics.Paint;
import android.graphics.RectF;

public class BouncingBall {
	 public BouncingBall(int xMin, int xMax, int yMin, int yMax,
			float ballRadius, float ballX, float ballY, float ballSpeedX,
			float ballSpeedY) {
		super();
		this.xMin = xMin;
		this.xMax = xMax;
		this.yMin = yMin;
		this.yMax = yMax;
		this.ballRadius = ballRadius;
		this.ballX = ballX;
		this.ballY = ballY;
		this.ballSpeedX = ballSpeedX;
		this.ballSpeedY = ballSpeedY;
//		this.ballBounds = ballBounds;
//		this.paint = paint;
	}
	 
	 public BouncingBall(float ballX, float ballY, float ballSpeedX){
		 super();
		 this.ballX = ballX;
		 this.ballY = ballY;
		 this.ballSpeedX = ballSpeedX;
	 }
	 
	private int xMin = 0;         
     public int getxMin() {
		return xMin;
	}
	public void setxMin(int xMin) {
		this.xMin = xMin;
	}
	public int getxMax() {
		return xMax;
	}
	public void setxMax(int xMax) {
		this.xMax = xMax;
	}
	public int getyMin() {
		return yMin;
	}
	public void setyMin(int yMin) {
		this.yMin = yMin;
	}
	public int getyMax() {
		return yMax;
	}
	public void setyMax(int yMax) {
		this.yMax = yMax;
	}
	public float getBallRadius() {
		return ballRadius;
	}
	public void setBallRadius(float ballRadius) {
		this.ballRadius = ballRadius;
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
	public float getBallSpeedX() {
		return ballSpeedX;
	}
	public void setBallSpeedX(float ballSpeedX) {
		this.ballSpeedX = ballSpeedX;
	}
	public float getBallSpeedY() {
		return ballSpeedY;
	}
	public void setBallSpeedY(float ballSpeedY) {
		this.ballSpeedY = ballSpeedY;
	}
	public RectF getBallBounds() {
		return ballBounds;
	}
	public void setBallBounds(RectF ballBounds) {
		this.ballBounds = ballBounds;
	}
	public Paint getPaint() {
		return paint;
	}
	public void setPaint(Paint paint) {
		this.paint = paint;
	}
	private int xMax;
     private int yMin = 0;
     private int yMax;
     private float ballRadius = 80; 
     private float ballX = ballRadius + 20; 
     private float ballY = ballRadius + 40;
     private float ballSpeedX = 5; 
     private float ballSpeedY = 3;
     private RectF ballBounds;     
     private Paint paint;          
}
