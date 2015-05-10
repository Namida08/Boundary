package com.example.namida.boundary.framework.impl;

import android.media.SoundPool;

import com.example.namida.boundary.framework.Sound;

/**
 * Created by Namida on 2015/05/10.
 */
public class AndroidSound implements Sound {
	private int soundId;
	private SoundPool soundPool;

	public AndroidSound(SoundPool soundPool, int soundId){
		this.soundId = soundId;
		this.soundPool = soundPool;
	}

	@Override
	public void play(float volume) {
		soundPool.play(soundId, volume, volume, 0, 0, 1);
	}

	@Override
	public void dispose() {
		soundPool.unload(soundId);
	}
}
