package com.example.namida.boundary.boundary.Model;

import com.example.namida.boundary.boundary.Assets;

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

	public void moveLeft(){
		getPoint().x -= 10;
	}

	public void moveRight(){
		getPoint().x += 10;
	}

	public void moveTop(){
		getPoint().y -= 10;
	}

	public void moveBottom(){
		getPoint().y += 10;
	}

}
