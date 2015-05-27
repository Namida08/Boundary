package com.example.namida.boundary.boundary.Model;

import android.graphics.Matrix;

import com.example.namida.boundary.framework.Game;
import com.example.namida.boundary.util.Vector2d;

/**
 * Created by Namida on 2015/05/17.
 */
public class Character extends Object {

	private int hp;


	private Vector2d moveVector;

	Character(){
		super();
	}

	public int getHp() {
		return hp;
	}

	public Vector2d getMoveVector() {
		return moveVector;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public void setMoveVector(Vector2d vector) {
		this.moveVector = vector;
	}

	public void moveX(int move){
		getPoint().x += move;
		if(getPoint().x < 0){
			getPoint().x = 0;
		}
		if(getPoint().x > 1080){
			getPoint().x =1080;
		}
	}

	public void moveY(int move){
		getPoint().y += move;
		if(getPoint().y < 0){
			getPoint().y = 0;
		}
		if(getPoint().y > 1920){
			getPoint().y = 1920;
		}
	}

	public void moveVector(Vector2d vector){
		moveX((int) vector.getX());
		moveY((int) vector.getY());
	}

}
