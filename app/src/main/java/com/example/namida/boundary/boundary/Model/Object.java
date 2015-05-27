package com.example.namida.boundary.boundary.Model;

import android.graphics.Matrix;

import com.example.namida.boundary.framework.Game;

/**
 * Created by Namida on 2015/05/26.
 */
public class Object extends Image{
	private boolean flag;
	private float radius;

	public Object(){
		super();
		flag = true;
		radius = 1.0f;
	}

	@Override
	public void draw(Game game){
		if(flag){
			Matrix matrix = new Matrix();
			matrix.postScale(getScaleX(), getScaleY());
			matrix.postRotate(getDegree());
			matrix.postTranslate(getPoint().x - getPixmap().getWidth()/2, getPoint().y - getPixmap().getHeight()/2);
			game.getGraphics().drawPixmap(getPixmap(), matrix);
		}
	}

	public boolean getFlag(){
		return flag;
	}

	public float getRadius() {
		return radius;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public void setRadius(float radius) {
		this.radius = radius;
	}

}
