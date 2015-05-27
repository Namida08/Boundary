package com.example.namida.boundary.boundary.Model;

import android.graphics.Matrix;
import android.graphics.Point;

import com.example.namida.boundary.boundary.Assets;
import com.example.namida.boundary.framework.Game;
import com.example.namida.boundary.framework.Pixmap;

/**
 * Created by Namida on 2015/05/17.
 */
public class Image {
	private Point point;
	private float degree;
	private float scaleX;
	private float scaleY;
	private Pixmap pixmap;

	public Image(){
		this.point = new Point();
		this.degree = 0.0f;
		this.scaleX = 1.0f;
		this.scaleY = 1.0f;
		this.pixmap = null;
	}

	public void draw(Game game){
		Matrix matrix = new Matrix();
		matrix.postScale(scaleX, scaleY);
		matrix.postRotate(degree);
		matrix.postTranslate(point.x, point.y);
		game.getGraphics().drawPixmap(pixmap, matrix);
	}

	public Point getPoint() {
		return point;
	}

	public float getDegree() {
		return degree;
	}

	public float getScaleX() {
		return scaleX;
	}

	public float getScaleY() {
		return scaleY;
	}

	public Pixmap getPixmap() {
		return pixmap;
	}


	public void setPoint(int x, int y) {
		this.point.x = x;
		this.point.y = y;
	}

	public void setPoint(Point point) {
		this.point.x = point.x;
		this.point.y = point.y;
	}

	public void setDegree(float degree) {
		this.degree = degree;
	}

	public void setScaleX(float scaleX) {
		this.scaleX = scaleX;
	}

	public void setScaleY(float scaleY) {
		this.scaleY = scaleY;
	}

	public void setPixmap(Pixmap pixmap) {
		this.pixmap = pixmap;
	}

	public void setImage(Image image) {
		point = image.point;
		degree = image.degree;
		scaleX = image.scaleX;
		scaleY = image.scaleY;
		pixmap = image.pixmap;
	}

}


