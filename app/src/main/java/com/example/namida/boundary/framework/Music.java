package com.example.namida.boundary.framework;

/**
 * Created by Namida on 2015/02/15.
 */
public interface Music {
	public void play();

	public void stop();

	public void pause();

	public void setLooping(boolean looping);

	public void setVolume(float volume);

	public boolean isPlaying();

	public boolean isStopped();

	public boolean isLooping();

	public void dispose();
}
