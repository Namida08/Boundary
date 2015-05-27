package com.example.namida.boundary.boundary.Model;

import com.example.namida.boundary.framework.Game;

/**
 * Created by Namida on 2015/05/17.
 */
public class Enemy extends Character{

	private Shot shot;

	public Enemy(){
		super();
		shot = new Shot(getPoint());
	}

	public void update(){
		if(getFlag()) {
			moveVector(getMoveVector());
			shot.update();
		}
	}

	public void draw(Game game){
		super.draw(game);
		shot.draw(game);
	}

}
