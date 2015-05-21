package com.example.namida.boundary.boundary.View;

import android.graphics.Color;

import com.example.namida.boundary.framework.Game;
import com.example.namida.boundary.framework.Graphics;
import com.example.namida.boundary.util.Vector2d;
import com.example.namida.boundary.framework.Input;

import java.util.List;

/**
 * Created by Namida on 2015/05/21.
 */
public class VectorController {

	private Vector2d start;
	private Vector2d end;
	private int pointer;

	public VectorController(){
		this.start = null;
		this.end = null;
		this.pointer = -1;
	}

	public void update(List<Input.TouchEvent> touchEvents){
		for(Input.TouchEvent event : touchEvents) {
			switch (event.type){
				case Input.TouchEvent.TOUCH_DOWN:
					if(pointer == -1) {
						start = new Vector2d(event.x, event.y);
						end = new Vector2d(event.x, event.y);
						pointer = event.pointer;
					}
					break;
				case Input.TouchEvent.TOUCH_DRAGGED:
					if(pointer == event.pointer) {
						end = new Vector2d(event.x, event.y);
					}
					break;
				case Input.TouchEvent.TOUCH_UP:
					if(pointer == event.pointer) {
						start = null;
						end = null;
						pointer = -1;
					}
					break;
			}
		}
	}

	public Vector2d getVector(){
		if(start != null && end != null){
			return end.subtract(start);
		}
		return new Vector2d(0, 0);
	}

	public void draw(Game game){
		if(start != null && end != null) {
			Graphics graphics = game.getGraphics();
			graphics.drawCircle((int) start.getX(), (int) start.getY(), 10, Color.RED);
			graphics.drawCircle((int) end.getX(), (int) end.getY(), 10, Color.BLUE);
			graphics.drawLine((int) start.getX(), (int) start.getY(), (int) end.getX(), (int) end.getY(), Color.GREEN);
		}
	}

}
