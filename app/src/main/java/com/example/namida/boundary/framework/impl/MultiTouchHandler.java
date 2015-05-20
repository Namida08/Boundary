package com.example.namida.boundary.framework.impl;

import android.view.MotionEvent;
import android.view.View;

import com.example.namida.boundary.framework.Input.TouchEvent;
import com.example.namida.boundary.framework.Pool;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Namida on 2015/05/10.
 */
public class MultiTouchHandler implements TouchHandler {
	static final int TOUCH_MAX = 20;

	private boolean[] isTouched = new boolean[TOUCH_MAX];
	private int[] touchCount = new int[TOUCH_MAX];
	private int[] touchX = new int[TOUCH_MAX];
	private int[] touchY = new int[TOUCH_MAX];
	private Pool<TouchEvent> touchEventPool;
	private List<TouchEvent> touchEvents = new ArrayList<TouchEvent>();
	private List<TouchEvent> touchEventsBuffer = new ArrayList<TouchEvent>();
	private float scaleX;
	private float scaleY;

	public MultiTouchHandler(View view, float scaleX, float scaleY){
		Pool.PoolObjectFactory<TouchEvent> factory = new Pool.PoolObjectFactory<TouchEvent>() {
			@Override
			public TouchEvent createObject() {
				return new TouchEvent();
			}
		};
		touchEventPool = new Pool<TouchEvent>(factory, 100);
		view.setOnTouchListener(this);

		this.scaleX = scaleX;
		this.scaleY = scaleY;
	}

	public void update(){
		for(int i = 0; i < TOUCH_MAX; i++){
			if(isTouchDown(i)){
				touchCount[i]++;
			}
		}
	}

	@Override
	public boolean isTouchDown(int pointer) {
		synchronized (this){
			if(pointer < 0 || pointer >= TOUCH_MAX){
				return false;
			}else{
				return isTouched[pointer];
			}
		}
	}

	public int getTouchCount(int pointer) {
		synchronized (this){
			if(pointer < 0 || pointer >= TOUCH_MAX){
				return 0;
			}else{
				return touchCount[pointer];
			}
		}
	}

	@Override
	public int getTouchX(int pointer) {
		synchronized (this){
			if(pointer < 0 || pointer >= TOUCH_MAX){
				return 0;
			}else{
				return touchX[pointer];
			}
		}
	}

	@Override
	public int getTouchY(int pointer) {
		synchronized (this){
			if(pointer < 0 || pointer >= TOUCH_MAX){
				return 0;
			}else{
				return touchY[pointer];
			}
		}
	}

	@Override
	public List<TouchEvent> getTouchEvents() {
		synchronized (this){
			for(int i = 0; i < touchEvents.size(); i++){
				touchEventPool.free(touchEvents.get(i));
			}
			touchEvents.clear();
			touchEvents.addAll(touchEventsBuffer);
			touchEventsBuffer.clear();
			return touchEvents;
		}
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		synchronized (this){
			int action = event.getAction() & MotionEvent.ACTION_MASK;
			int pointerIndex = (event.getAction() & MotionEvent.ACTION_POINTER_INDEX_MASK) >> MotionEvent.ACTION_POINTER_INDEX_SHIFT;
			int pointerId = event.getPointerId(pointerIndex);
			TouchEvent touchEvent;

			switch (action){
				case MotionEvent.ACTION_DOWN:
				case MotionEvent.ACTION_POINTER_DOWN:
					touchEvent = touchEventPool.newObject();
					touchEvent.type = TouchEvent.TOUCH_DOWN;
					touchEvent.pointer = pointerId;
					touchEvent.x = touchX[pointerId] = (int) (event.getX(pointerIndex) * scaleX);
					touchEvent.y = touchY[pointerId] = (int) (event.getY(pointerIndex) * scaleY);
					isTouched[pointerId] = true;
					touchCount[pointerId] = 0;
					touchEventsBuffer.add(touchEvent);
					break;
				case MotionEvent.ACTION_UP:
				case MotionEvent.ACTION_POINTER_UP:
				case MotionEvent.ACTION_CANCEL:
					touchEvent = touchEventPool.newObject();
					touchEvent.type = TouchEvent.TOUCH_UP;
					touchEvent.pointer = pointerId;
					touchEvent.x = touchX[pointerId] = (int) (event.getX(pointerIndex) * scaleX);
					touchEvent.y = touchY[pointerId] = (int) (event.getY(pointerIndex) * scaleY);
					isTouched[pointerId] = false;
					touchEventsBuffer.add(touchEvent);
					break;

				case MotionEvent.ACTION_MOVE:
				case MotionEvent.ACTION_SCROLL:
					for(int i = 0; i < event.getPointerCount(); i++){
						pointerId = event.getPointerId(i);

						touchEvent = touchEventPool.newObject();
						touchEvent.type = TouchEvent.TOUCH_DRAGGED;
						touchEvent.pointer = pointerId;
						touchEvent.x = touchX[pointerId] = (int) (event.getX(i) * scaleX);
						touchEvent.y = touchY[pointerId] = (int) (event.getY(i) * scaleY);
						touchEventsBuffer.add(touchEvent);
					}
					break;
			}

			return true;
		}
	}

}
