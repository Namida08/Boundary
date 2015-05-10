package com.example.namida.boundary.framework.impl;

import android.graphics.Bitmap;

import com.example.namida.boundary.framework.Graphics.PixMapFormat;
import com.example.namida.boundary.framework.Pixmap;

/**
 * Created by Namida on 2015/05/10.
 */
public class AndroidPixmap implements Pixmap {
	Bitmap bitmap;
	PixMapFormat format;

	public AndroidPixmap(Bitmap bitmap, PixMapFormat format){
		this.bitmap = bitmap;
		this.format = format;
	}

	@Override
	public int getWidth() {
		return bitmap.getWidth();
	}

	@Override
	public int getHeight() {
		return bitmap.getHeight();
	}

	@Override
	public PixMapFormat getFormat() {
		return format;
	}

	@Override
	public void dispose() {
		bitmap.recycle();
	}
}
