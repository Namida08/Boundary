package com.example.namida.boundary;


import com.example.namida.boundary.boundary.Screen.LoadingScreen;
import com.example.namida.boundary.framework.Screen;
import com.example.namida.boundary.framework.impl.AndroidGame;


public class MyActivity extends AndroidGame {

	@Override
	public Screen getStartScreen() {
		return new LoadingScreen(this);
	}
}
