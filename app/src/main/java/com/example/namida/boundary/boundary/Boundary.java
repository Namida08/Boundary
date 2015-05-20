package com.example.namida.boundary.boundary;

import com.example.namida.boundary.boundary.Screen.LoadingScreen;
import com.example.namida.boundary.framework.Screen;
import com.example.namida.boundary.framework.impl.AndroidGame;

/**
 * Created by Namida on 2015/05/10.
 */
public class Boundary extends AndroidGame {
	private static final String TAG = Boundary.class.getSimpleName();
	private final Boundary self = this;

	@Override
	public Screen getStartScreen(){
		return new LoadingScreen(this);
	}

}
