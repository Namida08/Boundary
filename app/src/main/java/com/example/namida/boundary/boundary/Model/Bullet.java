package com.example.namida.boundary.boundary.Model;

/**
 * Created by Namida on 2015/05/26.
 */
public class Bullet extends Object {

	private int count;
	private float baseAndle;
	private float angle;
	private float spd;
	private int power;

	public Bullet(){
		super();
		this.count = 0;
		this.baseAndle = 0;
		this.angle = 0;
		this.spd = 0;
		this.power = 0;
	}

	public void update(){
		if(getFlag()) {
			move();
			out();
			count++;
		}
	}

	private void move(){
		getPoint().x += Math.cos(angle) * spd;
		getPoint().y += Math.sin(angle) * spd;
	}

	private void out(){
		if(getPoint().x < -10 || getPoint().x > 1090 || getPoint().y < -10 || getPoint().y > 1930){
			setFlag(false);
		}
	}

	public void setAngle(float angle) {
		this.angle = angle;
	}

	public void setSpd(float spd) {
		this.spd = spd;
	}
}
