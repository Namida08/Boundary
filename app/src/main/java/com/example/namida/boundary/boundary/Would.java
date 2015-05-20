package com.example.namida.boundary.boundary;

import com.example.namida.boundary.boundary.Model.Player;
import com.example.namida.boundary.framework.Game;

/**
 * Created by Namida on 2015/05/17.
 */
public class Would {

	boolean gameOver = false;
	float ticktime = 0;

	public Player player;

	public Would(){
		player = new Player();

	}

	public void update(float deltaTime){
		if(gameOver) {
			return;
		}

		ticktime += deltaTime;

		player.update();
	}

	public void draw(Game game){
		player.draw(game);


	}

}
