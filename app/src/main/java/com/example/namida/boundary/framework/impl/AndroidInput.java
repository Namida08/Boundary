package com.example.namida.boundary.framework.impl;

import android.content.Context;
import android.view.View;

import com.example.namida.boundary.framework.Input;

import java.util.List;

/**
 * Created by Namida on 2015/05/10.
 */
public class AndroidInput implements Input {
	private AccelerometerHandler accelHandler;
	private KeyboardHandler keyboardHandler;
	private TouchHandler touchHandler;

	public AndroidInput(Context context, View view, float scaleX, float scaleY){
		accelHandler = new AccelerometerHandler(context);
		keyboardHandler = new KeyboardHandler(view);
		touchHandler = new MultiTouchHandler(view, scaleX, scaleY);
	}


	@Override
	public boolean isKeyPressed(int keyCode) {
		return keyboardHandler.isKeyPressed(keyCode);
	}

	@Override
	public boolean isTouchDown(int pointer) {
		return touchHandler.isTouchDown(pointer);
	}

	@Override
	public int getTouchX(int pointer) {
		return touchHandler.getTouchX(pointer);
	}

	@Override
	public int getTouchY(int pointer) {
		return touchHandler.getTouchY(pointer);
	}

	@Override
	public float getAccelX() {
		return accelHandler.getAccelX();
	}

	@Override
	public float getAccelY() {
		return accelHandler.getAccelY();
	}

	@Override
	public float getAccelZ() {
		return accelHandler.getAccelZ();
	}

	@Override
	public List<KeyEvent> getKeyEvents() {
		return keyboardHandler.getKeyEvents();
	}

	@Override
	public List<TouchEvent> getTouchEvents() {
		return touchHandler.getTouchEvents();
	}
}
