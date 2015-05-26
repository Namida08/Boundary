package com.example.namida.boundary.boundary.Model;

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
