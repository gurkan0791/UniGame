package com.example.unigame;

public class Ball {

	
	private float x = 0;
	private float y = 0;
	
	public Ball(){}
	public Ball(float x){
		this.x = x;
	}
	public Ball(float x, float y){
		this.x = x;
		this.y = y;
	}
	public float getX() {
		return x;
	}
	public void setX(float x1) {
		this.x = x1;
	}
	public  float getY() {
		return y;
	}
	public void setY(float y1) {
		this.y = y1;
	}
	
}
