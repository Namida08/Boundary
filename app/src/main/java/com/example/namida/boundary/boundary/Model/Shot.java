package com.example.namida.boundary.boundary.Model;

import android.graphics.Point;

import com.example.namida.boundary.boundary.Assets;
import com.example.namida.boundary.framework.Game;
import com.example.namida.boundary.framework.Pool;

/**
 * Created by Namida on 2015/05/26.
 */
public class Shot {
	private static final int BULLET_MAX = 100;

	private int count;
	private Point point;

	private Bullet[] bullets;
	private Pool<Bullet> bulletPool;

	public Shot(Point point){
		this.count = 0;
		this.point = point;
		bullets = new Bullet[BULLET_MAX];
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

		for (Bullet bullet : bullets) {
			if(bullet != null) {
				bullet.update();
			}
		}

		count++;
	}

	public void draw(Game game){
		for (Bullet bullet : bullets) {
			if(bullet != null) {
				bullet.draw(game);
			}
		}
	}

	public void dispose(){
		for (Bullet bullet : bullets) {
			if (bullet != null) {
				bulletPool.free(bullet);
			}
		}
	}

	public void shotPattern1(){
		if(count % 1 == 0) {
			for (int i = 0; i < BULLET_MAX; i++) {
				if(bullets[i] == null || !bullets[i].getFlag()){
					Bullet bullet = bulletPool.newObject();
					bullet.setPixmap(Assets.bullet1[1]);
					bullet.setPoint(point.x, point.y);
					bullet.setRadius(20);
					bullet.setAngle(count);
					bullet.setSpd(10);
					bullets[i] = bullet;
					break;
				}
			}
		}
	}

}