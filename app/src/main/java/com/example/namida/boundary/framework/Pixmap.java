package com.example.namida.boundary.framework;

import com.example.namida.boundary.framework.Graphics.PixMapFormat;

/**
 * Created by Namida on 2015/02/15.
 */
public interface Pixmap {
	public int getWidth();

	public int getHeight();

	public PixMapFormat getFormat();

	public void dispose();
}
