package com.example.namida.boundary.boundary.Model;

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

	private List<Bullet> bullets = new ArrayList<Bullet>();
	private Pool<Bullet> bulletPool;

	public Shot(){
		Pool.PoolObjectFactory<Bullet> factory = new Pool.PoolObjectFactory<Bullet>() {
			@Override
			public Bullet createObject() {
				return new Bullet();
			}
		};
		bulletPool = new Pool<Bullet>(factory, BULLET_MAX);
	}

	public void update(){
		Bullet bullet;
		for(int i = 0; i < 1; i++) {
			bullet = bulletPool.newObject();
			bullet.setFlag(true);
			bullet.setPixmap(Assets.bullet1[1]);
			bullet.setPoint(1080/2, 100);
			//bullet.setRadius(1);
			//bullet.setDegree(1);
			bullet.setMoveVector(new Vector2d(0, 5));
			bullets.add(bullet);
		}

		for (Bullet b : bullets) {
			b.update();
		}
	}

	public void draw(Game game){
		for (Bullet bullet : bullets) {
			bullet.draw(game);
		}
	}

}

