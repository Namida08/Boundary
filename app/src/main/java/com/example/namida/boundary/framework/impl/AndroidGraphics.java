package com.example.namida.boundary.framework.impl;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapRegionDecoder;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;

import com.example.namida.boundary.framework.Graphics;
import com.example.namida.boundary.framework.Pixmap;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Namida on 2015/05/10.
 */
public class AndroidGraphics implements Graphics{
	AssetManager assets;
	Bitmap frameBuffer;
	Canvas canvas;
	Paint paint;
	Rect srcRect = new Rect();
	Rect dstRect = new Rect();

	public AndroidGraphics(AssetManager assets, Bitmap frameBuffer){
		this.assets = assets;
		this.frameBuffer = frameBuffer;
		this.canvas = new Canvas(frameBuffer);
		this.paint = new Paint(Paint.ANTI_ALIAS_FLAG);
	}


	@Override
	public Pixmap newPixMap(String fileName, PixMapFormat format) {
		Bitmap.Config config = null;
		if(format == PixMapFormat.RGB565){
			config = Bitmap.Config.RGB_565;
		}else if(format == PixMapFormat.ARGB4444){
			config = Bitmap.Config.ARGB_4444;
		}else{
			config = Bitmap.Config.ARGB_8888;
		}

		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inPreferredConfig = config;

		InputStream in = null;
		Bitmap bitmap = null;
		try{
			in = assets.open(fileName);
			bitmap = BitmapFactory.decodeStream(in);
			if(bitmap == null){
				throw new RuntimeException("Couldn't load bitmap from asset '" + fileName + "'");
			}
		} catch (IOException e){
			throw new RuntimeException("Couldn't load bitmap from asset '" + fileName + "'");
		} finally {
			if(in != null){
				try{
					in.close();
				} catch (IOException e){
				}
			}
		}

		if(bitmap.getConfig() == Bitmap.Config.RGB_565){
			format = PixMapFormat.RGB565;
		}else if(bitmap.getConfig() == Bitmap.Config.ARGB_4444){
			format = PixMapFormat.ARGB4444;
		}else{
			format = PixMapFormat.ARGB8888;
		}

		return new AndroidPixmap(bitmap, format);
	}


	@Override
	public Pixmap[] newPixMap(String fileName, PixMapFormat format, int split) {
		Bitmap.Config config = null;
		if(format == PixMapFormat.RGB565){
			config = Bitmap.Config.RGB_565;
		}else if(format == PixMapFormat.ARGB4444){
			config = Bitmap.Config.ARGB_4444;
		}else{
			config = Bitmap.Config.ARGB_8888;
		}

		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inPreferredConfig = config;

		InputStream in = null;
		Bitmap[] bitmap = new Bitmap[split];
		try{
			in = assets.open(fileName);
			BitmapRegionDecoder regionDecoder = BitmapRegionDecoder.newInstance(in, true);
			int width = 70;
			for (int i = 0; i < split; i++) {
				bitmap[i] = regionDecoder.decodeRegion(new Rect(i * width, 0,(i + 1) * width, regionDecoder.getHeight()), null);
				if(bitmap[i] == null){
					throw new RuntimeException("Couldn't load bitmap from asset '" + fileName + "'");
				}
			};
		} catch (IOException e){
			throw new RuntimeException("Couldn't load bitmap from asset '" + fileName + "'");
		} finally {
			if(in != null){
				try{
					in.close();
				} catch (IOException e){
				}
			}
		}

		if(bitmap[0].getConfig() == Bitmap.Config.RGB_565){
			format = PixMapFormat.RGB565;
		}else if(bitmap[0].getConfig() == Bitmap.Config.ARGB_4444){
			format = PixMapFormat.ARGB4444;
		}else{
			format = PixMapFormat.ARGB8888;
		}

		Pixmap[] result = new Pixmap[bitmap.length];
		for (int i = 0; i < bitmap.length; i++) {
			result[i] = new AndroidPixmap(bitmap[i], format);
		}

		return result;
	}

	@Override
	public void clear(int color) {
		canvas.drawRGB((color & 0xff0000) >> 16, (color & 0xff00) >> 8, (color & 0xff));
	}

	@Override
	public void drawPixel(int x, int y, int color) {
		paint.setColor(color);
		canvas.drawPoint(x, y, paint);
	}

	@Override
	public void drawCircle(int x, int y, int radius, int color){
		paint.setColor(color);
		canvas.drawCircle(x, y, radius, paint);
	}

	@Override
	public void drawLine(int x, int y, int x2, int y2, int width, int color) {
		paint.setColor(color);
		paint.setStrokeWidth(width);
		canvas.drawLine(x, y, x2, y2, paint);
		paint.setStrokeWidth(1);
	}

	@Override
	public void drawRect(int x, int y, int width, int height, int color) {
		paint.setColor(color);
		paint.setStyle(Paint.Style.FILL);
		canvas.drawRect(x, y, x + width - 1, y + height - 1, paint);
	}

	@Override
	public void drawPixmap(Pixmap pixmap, int x, int y, int srcX, int srcY, int srcWidth, int srcHeight) {
		srcRect.left = srcX;
		srcRect.top = srcY;
		srcRect.right = srcX + srcWidth - 1;
		srcRect.bottom = srcY + srcHeight - 1;

		dstRect.left = x;
		dstRect.top = y;
		dstRect.right = x + srcWidth - 1;
		dstRect.bottom = y + srcHeight - 1;

		canvas.drawBitmap(((AndroidPixmap) pixmap).bitmap, srcRect, dstRect, null);
	}

	@Override
	public void drawPixmap(Pixmap pixmap, int x, int y) {
		canvas.drawBitmap(((AndroidPixmap) pixmap).bitmap, x, y, null);
	}

	@Override
	public void drawPixmap(Pixmap pixmap, Matrix matrix) {
		canvas.drawBitmap(((AndroidPixmap) pixmap).bitmap, matrix, null);
	}

	@Override
	public int getWidth() {
		return frameBuffer.getWidth();
	}

	@Override
	public int getHeight() {
		return frameBuffer.getHeight();
	}
}
