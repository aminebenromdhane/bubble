package com.example.testbubble;


import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import javax.microedition.khronos.opengles.GL10;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLUtils;

public class ImageStatique {
	
	public float x, y;
	private final int VERTEX_SIZE = (2 + 2) * 4;
	public static final int DIRECTION_LEFT = -1;
	public static final int DIRECTION_RIGHT = 1;
	FloatBuffer vertices;
	ShortBuffer indices;
	public int textureId;
	GLGraphics g;
	Game game;
	GL10 gl;
	String filename;
	public float width, height;

	public ImageStatique(Game game) {
		this.game = game;
		this.g = ((GLGame) game).getGLGraphics();
		gl = g.getGL();

	}

	public void load_bitmap(String filename) {

		try {
			Bitmap bitmap = BitmapFactory.decodeStream(game.getFileIO()
					.readAsset(filename));
			width = (float) bitmap.getWidth();
			height = (float) bitmap.getHeight();
			int[] textureIds = new int[1];
			gl.glGenTextures(1, textureIds, 0);
			textureId = textureIds[0];
			gl.glBindTexture(GL10.GL_TEXTURE_2D, textureId);
			GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, bitmap, 0);
			gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER,
					GL10.GL_LINEAR);
			gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER,
					GL10.GL_LINEAR);
			bitmap.recycle();
		} catch (IOException e) {

		}

	}

	public void load(float x, float y, float width, float height) {

		ByteBuffer byteBuffer = ByteBuffer.allocateDirect(4 * VERTEX_SIZE);
		byteBuffer.order(ByteOrder.nativeOrder());
		vertices = byteBuffer.asFloatBuffer();
		vertices.put(new float[] { x, y, 0.0f, 1.0f, x + width, y, 1.0f, 1.0f,
				x + width, y + height, 1.0f, 0.0f, x, y + height, 0.0f, 0.0f });
		vertices.flip();
		byteBuffer = ByteBuffer.allocateDirect(6 * 2);
		byteBuffer.order(ByteOrder.nativeOrder());
		indices = byteBuffer.asShortBuffer();
		indices.put(new short[] { 0, 1, 2, 2, 3, 0 });
		indices.flip();

	}

	public void load(float x, float y, float srcX, float srcY, float width,
			float height, int textureId, float bitmap_width,
			float bitmap_height, int direction) {
		this.textureId = textureId;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		ByteBuffer byteBuffer = ByteBuffer.allocateDirect(4 * VERTEX_SIZE);
		byteBuffer.order(ByteOrder.nativeOrder());
		vertices = byteBuffer.asFloatBuffer();
		vertices.put(new float[] { x, y, srcX / bitmap_width,
				(srcY + height) / bitmap_height, x + (direction * width), y,
				(srcX + width) / bitmap_width, (srcY + height) / bitmap_height,
				x + (direction * width), y + height,
				(srcX + width) / bitmap_width, srcY / bitmap_height, x,
				y + height, srcX / bitmap_width, srcY / bitmap_height });
		vertices.flip();
		byteBuffer = ByteBuffer.allocateDirect(6 * 2);
		byteBuffer.order(ByteOrder.nativeOrder());
		indices = byteBuffer.asShortBuffer();
		indices.put(new short[] { 0, 1, 2, 2, 3, 0 });
		indices.flip();

	}

	public void load(String filename, float x, float y) {

		this.x = x;
		this.y = y;
		this.load_bitmap(filename);
		ByteBuffer byteBuffer = ByteBuffer.allocateDirect(4 * VERTEX_SIZE);
		byteBuffer.order(ByteOrder.nativeOrder());
		vertices = byteBuffer.asFloatBuffer();
		vertices.put(new float[] { x, y, 0.0f, 1.0f, x + this.width, y, 1.0f,
				1.0f, x + this.width, y + this.height, 1.0f, 0.0f, x,
				y + this.height, 0.0f, 0.0f });
		vertices.flip();
		byteBuffer = ByteBuffer.allocateDirect(6 * 2);
		byteBuffer.order(ByteOrder.nativeOrder());
		indices = byteBuffer.asShortBuffer();
		indices.put(new short[] { 0, 1, 2, 2, 3, 0 });
		indices.flip();

	}

	public void draw() {
		gl.glBindTexture(GL10.GL_TEXTURE_2D, textureId);
		vertices.position(0);
		gl.glVertexPointer(2, GL10.GL_FLOAT, VERTEX_SIZE, vertices);
		vertices.position(2);
		gl.glTexCoordPointer(2, GL10.GL_FLOAT, VERTEX_SIZE, vertices);

		gl.glDrawElements(GL10.GL_TRIANGLES, 6, GL10.GL_UNSIGNED_SHORT, indices);
		gl.glBindTexture(GL10.GL_TEXTURE_2D, 0);

	}

	public void dispose() {

	}

	public static void enable_drawing(GL10 gl) {
		gl.glEnable(GL10.GL_TEXTURE_2D);
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
		gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);
		gl.glEnable(GL10.GL_BLEND);

	}

}
