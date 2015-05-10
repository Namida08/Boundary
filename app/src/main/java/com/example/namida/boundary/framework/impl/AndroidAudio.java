package com.example.namida.boundary.framework.impl;

import android.app.Activity;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.SoundPool;

import com.example.namida.boundary.framework.Audio;
import com.example.namida.boundary.framework.Music;
import com.example.namida.boundary.framework.Sound;

import java.io.IOException;

/**
 * Created by Namida on 2015/05/10.
 */
public class AndroidAudio implements Audio {
	private AssetManager assets;
	private SoundPool soundPool;

	public AndroidAudio(Activity activity){
		activity.setVolumeControlStream(AudioManager.STREAM_MUSIC);
		this.assets = activity.getAssets();
		this.soundPool = new SoundPool(20, AudioManager.STREAM_MUSIC, 0);
	}

	@Override
	public Music newMusic(String fileName) {
		try{
			AssetFileDescriptor assetFileDescriptor = assets.openFd(fileName);
			return new AndroidMusic(assetFileDescriptor);
		} catch(IOException e){
			throw new RuntimeException("Couldn't load music '" + fileName + "'");
		}
	}

	@Override
	public Sound newSound(String fileName) {
		try{
			AssetFileDescriptor assetFileDescriptor = assets.openFd(fileName);
			int soundId = soundPool.load(assetFileDescriptor, 0);
			return new AndroidSound(soundPool, soundId);
		} catch(IOException e){
			throw new RuntimeException("Couldn't load sound '" + fileName + "'");
		}
	}
}
