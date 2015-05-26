package com.example.namida.boundary.boundary.Model;

import android.graphics.Color;

import com.example.namida.boundary.framework.Game;
import com.example.namida.boundary.framework.Graphics;
import com.example.namida.boundary.util.Vector2d;

/**
 * Created by Namida on 2015/05/26.
 */
public class Border extends Character{

	private int type;

	public Vector2d start;
	public Vector2d end;

	public Border(int type){
		super();
		this.type = type;
		setFlag(false);
	}

	@Override
	public void draw(Game game){
		if(getFlag()) {
			Graphics graphics = game.getGraphics();
			graphics.drawLine((int) start.getX(), (int) start.getY(), (int) end.getX(), (int) end.getY(), 10, type == 0 ? Color.RED : Color.BLUE);
		}
	}

}
