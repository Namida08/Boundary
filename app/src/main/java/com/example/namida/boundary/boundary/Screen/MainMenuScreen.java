package com.example.namida.boundary.boundary.Screen;

import com.example.namida.boundary.boundary.Assets;
import com.example.namida.boundary.framework.Game;
import com.example.namida.boundary.framework.Graphics;
import com.example.namida.boundary.framework.Input;
import com.example.namida.boundary.framework.Input.TouchEvent;
import com.example.namida.boundary.framework.Screen;

import java.util.List;

/**
 * Created by Namida on 2015/05/16.
 */
public class MainMenuScreen extends Screen{

	public MainMenuScreen(Game game) {
		super(game);
	}

	@Override
	public void update(float deltaTime) {
		Graphics graphics = game.getGraphics();
		List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
		game.getInput().getKeyEvents();

		int len = touchEvents.size();

		for(int i = 0; i < len; i++){
			TouchEvent event = touchEvents.get(i);
			if(event.type == TouchEvent.TOUCH_UP){
				if(inBounds(event, 0, 0, 64, 64)){
					game.setScreen(new GameScreen(game));
					return;
				}
			}
		}

	}

	private boolean inBounds(Input.TouchEvent event, int x, int y, int wight, int height){
		if(event.x > x && event.x < x + wight - 1 && event.y > y && event.y < y + height - 1){
			return true;
		}
		return false;
	}

	@Override
	public void present(float deltaTime) {
		Graphics graphics = game.getGraphics();
		graphics.drawPixmap(Assets.background, 0, 0);
	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void dispose() {

	}
}
