package com.example.namida.boundary.boundary.Model;

import android.graphics.Point;

import com.example.namida.boundary.boundary.Assets;
import com.example.namida.boundary.framework.Game;
import com.example.namida.boundary.util.Vector2d;

/**
 * Created by Namida on 2015/05/17.
 */
public class Player extends Character {

	public Player(){
		super();
		setPixmap(Assets.player);
		setPoint(1080/2, 1920/2);
		setRadius(100.0f);
	}

	public void update(){

	}

}
