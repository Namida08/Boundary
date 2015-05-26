package com.example.namida.boundary.boundary.View;

import android.graphics.Color;

import com.example.namida.boundary.framework.Game;
import com.example.namida.boundary.framework.Graphics;
import com.example.namida.boundary.util.Vector2d;
import com.example.namida.boundary.framework.Input;

import java.util.EventListener;
import java.util.List;

/**
 * Created by Namida on 2015/05/21.
 */
public class VectorController{

	private Vector2d start;
	private Vector2d end;
	private int pointer;

	private TouchListener touchListener = null;

	public interface TouchListener extends EventListener {
		public void down();
		public void dragged();
		public void up();
	}

	public VectorController(){
		this.start = null;
		this.end = null;
		this.pointer = -1;
	}

	public void setTouchListener(TouchListener touchListener){
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
						start = new Vector2d(event.x, event.y);
						end = new Vector2d(event.x, event.y);
						pointer = event.pointer;
						event.setUsed();

						if(touchListener != null) {
							touchListener.down();
						}
					}
					break;
				case Input.TouchEvent.TOUCH_DRAGGED:
					if(pointer == event.pointer) {
						end = new Vector2d(event.x, event.y);
						event.setUsed();

						if(touchListener != null) {
							touchListener.dragged();
						}
					}
					break;
				case Input.TouchEvent.TOUCH_UP:
					if(pointer == event.pointer) {
						if(touchListener != null) {
							touchListener.up();
						}

						start = null;
						end = null;
						pointer = -1;
						event.setUsed();
					}
					break;
			}
		}
	}

	public void draw(Game game){
		if(pointer != -1) {
			Graphics graphics = game.getGraphics();
			if(getVector().getX() > 0) {
				graphics.drawCircle((int) start.getX(), (int) start.getY(), 20, Color.RED);
				graphics.drawCircle((int) end.getX(), (int) end.getY(), 20, Color.RED);
			}else{
				graphics.drawCircle((int) start.getX(), (int) start.getY(), 20, Color.BLUE);
				graphics.drawCircle((int) end.getX(), (int) end.getY(), 20, Color.BLUE);
			}
			graphics.drawLine((int) start.getX(), (int) start.getY(), (int) end.getX(), (int) end.getY(), 10, Color.WHITE);
		}
	}

	public Vector2d getVector(){
		if(pointer != -1){
			return end.subtract(start);
		}
		return new Vector2d(0, 0);
	}

	public Vector2d getStart() {
		return start;
	}

	public Vector2d getEnd() {
		return end;
	}

}
