package com.example.namida.boundary.boundary.Model;

import android.graphics.Point;

import com.example.namida.boundary.boundary.Assets;
import com.example.namida.boundary.framework.Game;
import com.example.namida.boundary.framework.Pool;
import com.example.namida.boundary.util.Vector2d;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Namida on 2015/05/26.
 */
public class Shot {
	private static final int BULLET_MAX = 100;

	private int count;
	private Point point;

	private List<Bullet> bullets = new ArrayList<Bullet>();
	private Pool<Bullet> bulletPool;

	public Shot(Point point){
		this.count = 0;
		this.point = point;
		Pool.PoolObjectFactory<Bullet> factory = new Pool.PoolObjectFactory<Bullet>() {
			@Override
			public Bullet createObject() {
				return new Bullet();
			}
		};
		this.bulletPool = new Pool<Bullet>(factory, BULLET_MAX);
	}

	public void update(){
		shotPattern1();

		for (Bullet b : bullets) {
			b.update();
			if(b.out()){
				bulletPool.free(b);
			}
		}

		count++;
	}

	public void draw(Game game){
		for (Bullet bullet : bullets) {
			bullet.draw(game);
		}
	}

	public void dispose(){
		for (Bullet bullet : bullets) {
			bulletPool.free(bullet);
		}
	}

	public void shotPattern1(){
		if(count % 10 == 0) {
			Bullet bullet = bulletPool.newObject();
			bullet.setPixmap(Assets.bullet1[1]);
			bullet.setPoint(point.x, point.y);
			bullet.setRadius(20);
			bullet.setMoveVector(new Vector2d(0, 10));
			bullets.add(bullet);
		}
	}

}