package com.example.namida.boundary.boundary.Model;

import com.example.namida.boundary.boundary.Assets;
import com.example.namida.boundary.util.Vector2d;

/**
 * Created by Namida on 2015/05/17.
 */
public class Player extends Character {

	public Player(){
		super();
		setPixmap(Assets.player);
	}

	public void update(){

	}

	public void moveX(int move){
		getPoint().x += move;
		if(getPoint().x < 0){
			getPoint().x = 0;
		}
		if(getPoint().x > 300){
			getPoint().x = 300;
		}
	}

	public void moveY(int move){
		getPoint().y += move;
		if(getPoint().y < 0){
			getPoint().y = 0;
		}
		if(getPoint().y > 400){
			getPoint().y = 400;
		}
	}

	public void moveVector(Vector2d vector){
		moveX((int) vector.getX());
		moveY((int) vector.getY());
	}

}
