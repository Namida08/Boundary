package com.example.namida.boundary.boundary;

import android.graphics.Point;

import com.example.namida.boundary.boundary.Model.Border;
import com.example.namida.boundary.boundary.Model.Enemy;
import com.example.namida.boundary.boundary.Model.Player;
import com.example.namida.boundary.boundary.View.VectorController;
import com.example.namida.boundary.boundary.View.DragController;
import com.example.namida.boundary.framework.Game;
import com.example.namida.boundary.framework.Input;
import com.example.namida.boundary.framework.Pool;
import com.example.namida.boundary.util.Vector2d;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Namida on 2015/05/17.
 */
public class Would {

	boolean gameOver = false;
	float ticktime = 0;

	public Player player;
	public Border borderRight;
	public Border borderLeft;

	private List<Enemy> enemies = new ArrayList<Enemy>();
	private Pool<Enemy> enemyPool;


	private DragController dragController;
	private VectorController vectorController;

	public Would(){
		player = new Player();
		borderRight = new Border(0);
		borderLeft = new Border(1);

		Pool.PoolObjectFactory<Enemy> factory = new Pool.PoolObjectFactory<Enemy>() {
			@Override
			public Enemy createObject() {
				return new Enemy();
			}
		};
		enemyPool = new Pool<Enemy>(factory, 10);

		Enemy enemy = enemyPool.newObject();
		enemy.setFlag(true);
		enemy.setPixmap(Assets.player);
		enemy.setPoint(1080/2, 1920/5);
		enemy.setRadius(100.0f);
		enemy.setMoveVector(new Vector2d(0, 0));
		enemies.add(enemy);


		dragController = new DragController();
		vectorController = new VectorController();

		dragController.setTouchListener(new DragController.TouchListener() {
			@Override
			public boolean down(int x, int y) {
				if(new Vector2d(player.getPoint().x - x, player.getPoint().y - y).getLength() < player.getRadius()){
					player.setPoint(x, y);
					return true;
				}
				return false;
			}

			@Override
			public boolean dragged(int x, int y) {
				player.setPoint(x, y);
				return true;
			}

			@Override
			public boolean up(int x, int y) {
				return true;
			}
		});
		vectorController.setTouchListener(new VectorController.TouchListener() {
			@Override
			public void down() {
			}

			@Override
			public void dragged() {
			}

			@Override
			public void up() {
				if(vectorController.getVector().getLength() > 100) {
					if (vectorController.getVector().getX() > 0) {
						borderRight.start = vectorController.getStart();
						borderRight.end = vectorController.getEnd();
						borderRight.setFlag(true);
					} else {
						borderLeft.start = vectorController.getStart();
						borderLeft.end = vectorController.getEnd();
						borderLeft.setFlag(true);
					}
				}
			}
		});

	}

	public void update(List<Input.TouchEvent> touchEvents, float deltaTime){
		if(gameOver) {
			return;
		}
		ticktime += deltaTime;

		dragController.update(touchEvents);
		vectorController.update(touchEvents);

		player.update();
		for (Enemy enemy : enemies) {
			enemy.update();
		}

	}

	public void draw(Game game){

		player.draw(game);

		for (Enemy enemy : enemies) {
			enemy.draw(game);
		}

		borderRight.draw(game);
		borderLeft.draw(game);

		dragController.draw(game);
		vectorController.draw(game);

	}

}
