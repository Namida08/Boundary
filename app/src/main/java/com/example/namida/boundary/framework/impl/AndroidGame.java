package com.example.namida.boundary.framework.impl;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Point;
import android.os.Bundle;
import android.os.PowerManager;
import android.view.Window;
import android.view.WindowManager;

import com.example.namida.boundary.framework.Audio;
import com.example.namida.boundary.framework.FileIO;
import com.example.namida.boundary.framework.Game;
import com.example.namida.boundary.framework.Graphics;
import com.example.namida.boundary.framework.Input;
import com.example.namida.boundary.framework.Screen;

/**
 * Created by Namida on 2015/05/10.
 */
public class AndroidGame extends Activity implements Game{
	private AndroidFastRenderView renderView;
	private Graphics graphics;
	private Audio audio;
	private Input input;
	private FileIO fileIO;
	private Screen screen;

	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);

		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

		boolean isLandscape = getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
		int frameBufferWidth = isLandscape ? 480 : 320;
		int frameBufferHeight = isLandscape ? 320 : 480;
		Bitmap frameBuffer = Bitmap.createBitmap(frameBufferWidth, frameBufferHeight, Bitmap.Config.RGB_565);

		Point size = new Point();
		getWindowManager().getDefaultDisplay().getSize(size);
		float scaleX = (float) frameBufferWidth / size.x;
		float scaleY = (float) frameBufferHeight / size.y;

		renderView = new AndroidFastRenderView(this, frameBuffer);
		graphics = new AndroidGraphics(getAssets(), frameBuffer);
		fileIO = new AndroidFileIO(getAssets());
		audio = new AndroidAudio(this);
		input = new AndroidInput(this, renderView, scaleX, scaleY);
		screen = getStartScreen();
		setContentView(renderView);
	}

	@Override
	public void onResume(){
		super.onResume();
		screen.resume();
		renderView.resume();
	}

	@Override
	public void onPause(){
		super.onPause();
		renderView.pause();
		screen.pause();

		if(isFinishing()){
			screen.dispose();
		}
	}

	@Override
	public Input getInput() {
		return input;
	}

	@Override
	public FileIO getFileIO() {
		return fileIO;
	}

	@Override
	public Graphics getGraphics() {
		return graphics;
	}

	@Override
	public Audio getAudio() {
		return audio;
	}

	@Override
	public void setScreen(Screen screen) {
		if(screen == null){
			throw new IllegalArgumentException("Screen must not be null");
		}

		this.screen.pause();
		this.screen.dispose();
		screen.resume();
		screen.update(0);
		this.screen = screen;
	}

	@Override
	public Screen getCurrentScreen() {
		return screen;
	}

	@Override
	public Screen getStartScreen() {
		return null;
	}
}
