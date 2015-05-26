package com.example.namida.boundary.framework.impl;

import android.util.Log;
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

	@Override
	public List<TouchEvent> getTouchEvents() {
		synchronized (this){
			for(int i = 0; i < touchEvents.size(); i++){
				touchEventPool.free(touchEvents.get(i));
			}

			//画面から外れた場合
			for (TouchEvent event : touchEvents) {
				if(event.type == TouchEvent.TOUCH_DRAGGED) {
					boolean flag = true;
					for (TouchEvent eventBuffer : touchEventsBuffer) {
						if(event.pointer == eventBuffer.pointer){
							flag = false;
						}
					}
					if(flag){
						TouchEvent touchEvent = touchEventPool.newObject();
						touchEvent.type = TouchEvent.TOUCH_UP;
						touchEvent.pointer = event.pointer;
						touchEvent.x = (int) (event.x * scaleX);
						touchEvent.y = (int) (event.y * scaleY);
						touchEvent.isTouched = false;
						touchEvent.count = 0;
						touchEventsBuffer.add(touchEvent);
					}
				}
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
					touchEvent.x = (int) (event.getX(pointerIndex) * scaleX);
					touchEvent.y = (int) (event.getY(pointerIndex) * scaleY);
					touchEvent.isTouched = true;
					touchEvent.count = 0;
					touchEventsBuffer.add(touchEvent);
					break;
				case MotionEvent.ACTION_UP:
				case MotionEvent.ACTION_POINTER_UP:
				case MotionEvent.ACTION_CANCEL:
					touchEvent = touchEventPool.newObject();
					touchEvent.type = TouchEvent.TOUCH_UP;
					touchEvent.pointer = pointerId;
					touchEvent.x = (int) (event.getX(pointerIndex) * scaleX);
					touchEvent.y = (int) (event.getY(pointerIndex) * scaleY);
					touchEvent.isTouched = false;
					touchEvent.count = 0;
					touchEventsBuffer.add(touchEvent);
					break;
				case MotionEvent.ACTION_MOVE:
				case MotionEvent.ACTION_SCROLL:
					for(int i = 0; i < event.getPointerCount(); i++){
						pointerId = event.getPointerId(i);
						touchEvent = touchEventPool.newObject();
						touchEvent.type = TouchEvent.TOUCH_DRAGGED;
						touchEvent.pointer = pointerId;
						touchEvent.x = (int) (event.getX(i) * scaleX);
						touchEvent.y = (int) (event.getY(i) * scaleY);
						touchEvent.isTouched = true;
						touchEvent.count++;
						touchEventsBuffer.add(touchEvent);
					}
					break;
			}

			return true;
		}
	}

}
