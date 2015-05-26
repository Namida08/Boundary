package com.example.namida.boundary.boundary.Screen;

import android.graphics.Color;

import com.example.namida.boundary.boundary.Assets;
import com.example.namida.boundary.boundary.Would;
import com.example.namida.boundary.framework.Game;
import com.example.namida.boundary.framework.Graphics;
import com.example.namida.boundary.framework.Input;
import com.example.namida.boundary.framework.Screen;

import java.util.List;

/**
 * Created by Namida on 2015/05/16.
 */
public class GameScreen extends Screen{
	enum GameState {
		Ready,
		Running,
		Paused,
		GameOver
	}

	private GameState gameState = GameState.Ready;
	private Would would;



	public GameScreen(Game game) {
		super(game);
		would = new Would();

	}

	@Override
	public void update(float deltaTime) {
		List<Input.TouchEvent> touchEvents = game.getInput().getTouchEvents();

		if (gameState == GameState.Ready){
			updateReader(touchEvents);
		}
		if (gameState == GameState.Running){
			updateRunning(touchEvents, deltaTime);
		}
		if (gameState == GameState.Paused){

		}
		if (gameState == GameState.GameOver){

		}
	}

	private void updateReader(List<Input.TouchEvent> touchEvents){
		if(touchEvents.size() > 0){
			gameState = GameState.Running;
		}
	}

	private void updateRunning(List<Input.TouchEvent> touchEvents, float deltaTime){
		would.update(touchEvents, deltaTime);
	}

	@Override
	public void present(float deltaTime) {
		Graphics graphics = game.getGraphics();
		//graphics.clear(Color.BLACK);

		graphics.drawPixmap(Assets.background, 0, 0);

		would.draw(game);
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
