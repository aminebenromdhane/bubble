package com.example.testbubble;

public abstract class Bubble {

	protected String imageName;
	private float x;
	private float y;
	private float speedX;
	private float speedY;
	private ImageStatique image;
	private Game game;
	
	public Bubble(Game game){
		this.game = game;
		image = new ImageStatique(this.game);
		x = ((float)Math.random())*50;
		y = ((float)Math.random())*30;
		speedX = ((float)Math.random())*20;
		speedY = ((float)Math.random())*10;
	}
	
	public void load(){
		image.load(imageName, x, y);
	}
	
	public void draw(){
		image.draw();
	}
	
	public void move(){
		x += speedX;
		y += speedY;
	}
	
	public float getX(){
		return x;
	}
	
	public float getY(){
		return y;
	}
}
