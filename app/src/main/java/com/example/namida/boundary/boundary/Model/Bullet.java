package com.example.namida.boundary.boundary.Model;

/**
 * Created by Namida on 2015/05/26.
 */
public class Bullet extends Character {

	public Bullet(){
		super();
	}

	public void update(){
		if(getFlag()) {
			moveVector(getMoveVector());
		}
	}

	public boolean out(){
		return getPoint().x < -10 || getPoint().x > 1930 || getPoint().y < -10 || getPoint().y > 1090;
	}

}
