package com.example.namida.boundary.boundary.Model;

import android.graphics.Matrix;
import android.graphics.Point;

import com.example.namida.boundary.framework.Game;
import com.example.namida.boundary.framework.Pixmap;

/**
 * Created by Namida on 2015/05/17.
 */
public class Object{
	private Point point;
	private float degree;
	private float scaleX;
	private float scaleY;
	private Pixmap pixmap;

	public Object(){
		this.point = new Point();
		this.degree = 1.0f;
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

	public void setPoint(Point point) {
		this.point = point;
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

	public void setObject(Object object) {
		this.point = object.point;
		this.degree = object.degree;
		this.scaleX = object.scaleX;
		this.scaleY = object.scaleY;
		this.pixmap = object.pixmap;
	}

}


