package com.example.namida.boundary.framework.impl;

import android.view.View.OnTouchListener;
import com.example.namida.boundary.framework.Input.TouchEvent;

import java.util.List;

/**
 * Created by Namida on 2015/05/10.
 */
public interface TouchHandler extends OnTouchListener{

	public List<TouchEvent> getTouchEvents();
}
