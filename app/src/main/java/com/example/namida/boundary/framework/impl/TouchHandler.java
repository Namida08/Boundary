package com.example.namida.boundary.framework.impl;

import android.view.View.OnTouchListener;
import com.example.namida.boundary.framework.Input.TouchEvent;

import java.util.List;

/**
 * Created by Namida on 2015/05/10.
 */
public interface TouchHandler extends OnTouchListener{

	public int getTouchX(int pointer);

	public int getTouchY(int pointer);

	public boolean isTouchDown(int pointer);

	public int getTouchCount(int pointer);

	public List<TouchEvent> getTouchEvents();
}
