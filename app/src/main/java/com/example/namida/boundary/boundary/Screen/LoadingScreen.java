package com.example.namida.boundary.boundary.Screen;

import com.example.namida.boundary.boundary.Assets;
import com.example.namida.boundary.boundary.Settings;
import com.example.namida.boundary.framework.Game;
import com.example.namida.boundary.framework.Graphics;
import com.example.namida.boundary.framework.Screen;

/**
 * Created by Namida on 2015/05/16.
 */
public class LoadingScreen extends Screen {

	public LoadingScreen(Game game) {
		super(game);
	}

	@Override
	public void update(float deltaTime) {
		Graphics graphics = game.getGraphics();

		Assets.background = graphics.newPixMap("background.jpg", Graphics.PixMapFormat.RGB565);
		Assets.player = graphics.newPixMap("player.png", Graphics.PixMapFormat.ARGB4444);

		Assets.bullet1 = graphics.newPixMap("bullet1.png", Graphics.PixMapFormat.ARGB4444, 2);

		Settings.load(game.getFileIO());
		game.setScreen(new MainMenuScreen(game));
	}

	@Override
	public void present(float deltaTime) {

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
