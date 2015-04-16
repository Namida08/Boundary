package com.example.namida.boundary.framework;

/**
 * Created by Namida on 2015/02/15.
 */
public interface Game {
	public Input getInput();

	public FileIO getFileIO();

	public Graphics getGraphics();

	public Audio getAudio();

	public void setScreen(Screen screen);

	public Screen getCurrentScreen();

	public Screen getStartScreen();

}
