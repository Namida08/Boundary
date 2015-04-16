package com.example.namida.boundary.framework;

/**
 * Created by Namida on 2015/02/15.
 */
public interface Graphics {
	public static enum PixMapFormat{
		ARGB8888, ARGB4444, RGB565
	}

	public Pixmap newPixMap(String fileName, PixMapFormat format);

	public void clear();

	public void drawPixel(int x, int y, int color);

	public void drawLine(int x, int y, int x2, int y2, int color);

	public void drawRect(int x, int y, int width, int height, int color);

	public void drawPixmap(Pixmap pixmap, int x, int y, int srcX, int srcY, int srcWidth, int srcHeight);

	public void drawPixmap(Pixmap pixmap, int x, int y);

	public int getWidth();

	public int getHeight();
}
