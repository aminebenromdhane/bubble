package com.example.testbubble;

import java.util.ArrayList;
import java.util.List;

import javax.microedition.khronos.opengles.GL10;

public class DemoScreen extends Screen {

	final float WORLD_WIDTH = 2048;
	final float WORLD_HEIGHT = 1536;
	
	GLGraphics g;
	
	//all these can changed to other view to make it better organiziation
	private ImageStatique background;
	
	List<Bubble> bubbles;
	
	public DemoScreen(Game game) {
		super(game);
		g = ((GLGame)game).getGLGraphics();
		
		background = new ImageStatique(game);
		bubbles = new ArrayList<Bubble>();
		
		background.load("bg.png", 0.0f, 0.0f);
		
		for(int i=0;i<3;i++){
			bubbles.add(new BlueBubble(game));
		}
		
		for(int i=0;i<2;i++){
			bubbles.add(new RedBubble(game));
		}
		
		for(Bubble bubble : bubbles){
			bubble.load();
		}
	}

	@Override
	public void update(float deltaTime) {
		for(Bubble bubble : bubbles){		
			bubble.move();
		}
	}

	@Override
	public void present(float deltaTime) {
		/*GL10 gl = g.getGL();
		
		gl.glViewport(0, 0, g.getWidth(), g.getHeight());
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		gl.glMatrixMode(GL10.GL_PROJECTION);
		gl.glLoadIdentity();
		gl.glOrthof(0, WORLD_WIDTH, 0, WORLD_HEIGHT, 1, -1);
		
		ImageStatique.enable_drawing(gl);
		gl.glMatrixMode(GL10.GL_MODELVIEW);
		background.draw();
		/*for(Bubble bubble : bubbles){
			gl.glLoadIdentity();
			//gl.glTranslatef(bubble.getX(), bubble.getY(), 0);			
			bubble.draw();
		}
		gl.glDisableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
		*/
		GL10 gl = g.getGL();
		
		gl.glViewport(0, 0, g.getWidth(), g.getHeight());
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		gl.glMatrixMode(GL10.GL_PROJECTION);
		gl.glLoadIdentity();
		gl.glOrthof(0, WORLD_WIDTH, 0, WORLD_HEIGHT, 1, -1);
		
		ImageStatique.enable_drawing(gl);
		gl.glMatrixMode(GL10.GL_MODELVIEW);
		background.draw();
		for(Bubble bubble : bubbles){
			gl.glLoadIdentity();
			gl.glTranslatef(bubble.getX(), bubble.getY(), 0);			
			bubble.draw();
		}
		gl.glLoadIdentity();
		gl.glDisableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
