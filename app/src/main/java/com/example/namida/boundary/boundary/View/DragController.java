package com.example.namida.boundary.boundary.View;

import android.graphics.Color;
import android.graphics.Point;

import com.example.namida.boundary.framework.Game;
import com.example.namida.boundary.framework.Graphics;
import com.example.namida.boundary.framework.Input;

import java.util.EventListener;
import java.util.List;

/**
 * Created by Namida on 2015/05/26.
 */
public class DragController{

	private Point point;
	private int pointer;

	private TouchListener touchListener = null;

	public interface TouchListener extends EventListener {
		public boolean down(int x, int y);
		public boolean dragged(int x, int y);
		public boolean up(int x, int y);
	}

	public DragController (){
		this.point = new Point();
		this.pointer = -1;
	}

	public void setTouchListener(TouchListener touchListener) {
		this.touchListener = touchListener;
	}

	public void update(List<Input.TouchEvent> touchEvents){
		for(Input.TouchEvent event : touchEvents) {
			if(event.isUsed()){
				continue;
			}
			switch (event.type){
				case Input.TouchEvent.TOUCH_DOWN:
					if(pointer == -1) {
						if(touchListener != null) {
							if(touchListener.down(event.x, event.y)){
								point.x = event.x;
								point.y = event.y;
								pointer = event.pointer;
								event.setUsed();
							}
						}
					}
					break;
				case Input.TouchEvent.TOUCH_DRAGGED:
					if(point != null && pointer == event.pointer) {
						if(touchListener != null) {
							if(touchListener.dragged(event.x, event.y)){
								point.x = event.x;
								point.y = event.y;
								event.setUsed();
							}
						}
					}
					break;
				case Input.TouchEvent.TOUCH_UP:
					if(pointer == event.pointer) {
						pointer = -1;
						event.setUsed();

						if(touchListener != null) {
							touchListener.up(event.x, event.y);
						}
					}
					break;
			}
		}
	}

	public void draw(Game game){
		if(pointer != -1) {
			Graphics graphics = game.getGraphics();
			graphics.drawCircle(point.x, point.y, 100, Color.RED);
		}
	}

}
