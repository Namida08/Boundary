package com.example.namida.boundary.boundary.Model;

import com.example.namida.boundary.framework.Game;

/**
 * Created by Namida on 2015/05/17.
 */
public class Character extends Object {
	private int id;
	private boolean flag;

	Character(){
		super();
		id = -1;
		flag = true;
	}

	@Override
	public void draw(Game game){
		if(flag){
			super.draw(game);
		}
	}

	public int getId() {
		return id;
	}

	public boolean getFlag(){
		return flag;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public void setCharacter(Character character){
		this.id = character.id;
		this.flag = character.flag;
		setObject(character);
	}

}
