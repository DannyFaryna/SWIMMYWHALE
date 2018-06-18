package com.dannyfaryna.swimmywhale;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Ellipse;

import java.awt.Color;
import java.util.Random;

public class SwimmyWhale extends ApplicationAdapter {
	SpriteBatch batch;
	Texture background;
	Texture whale;
	Texture lose;
	Texture iceTop;
	Texture iceBottom;
	//ShapeRenderer shapeRenderer;
	Ellipse whaleEllipse;

	float whaleY = 0;
	float iceX = 0;
	float speed = 0;
	float gravity = 0;
	int state = 0;
	float clearance = 0;
	float endscreen = -1000;
	float offsetTop = 0;
	float offsetBottom = 0;
	public int[] change = {400, 600, 700, 800, 900, 1000};
	Random rand = new Random();
	int random = rand.nextInt((5 - 1) + 1) + 1;
	int random2 = rand.nextInt((5 - 1) + 1) + 1;


	@Override
	public void create () {
		batch = new SpriteBatch();
		background = new Texture("background.jpg");
		lose = new Texture("lose.png");
		whale = new Texture("whale.png");

		iceTop = new Texture("icetopbig.png");
		iceBottom = new Texture("icebottombig.png");

		//shapeRenderer = new ShapeRenderer();
		//whaleEllipse = new Ellipse();

		whaleY = Gdx.graphics.getHeight()/2 - whale.getHeight()/2;
		iceX = Gdx.graphics.getWidth() + 300;
		offsetTop = change[random];
		offsetBottom = change[random];

	}

	@Override
	public void render () {

		if (Gdx.input.justTouched()){
			state = 1;
			random = rand.nextInt((5 - 1) + 1) + 1;
			random2 = rand.nextInt((5 - 1) + 1) + 1;
		}

		if (state == 1) {
			gravity++;
			whaleY = whaleY - gravity;
			speed++;
			iceX = iceX - speed;
			clearance = (Gdx.graphics.getHeight() - iceTop.getHeight() + offsetTop) -offsetBottom;

			if (Gdx.input.justTouched()){
				gravity = -20;
			}
			if (whaleY < 20){
				state = 2;
				Gdx.app.log("test", "test");
			}
			if (iceX <= -200){
				iceX = Gdx.graphics.getWidth() + 300;
				speed = 0;
				offsetTop = change[random];
				offsetBottom = change[random2];
			}
			/*if (whale.getWidth() < iceX + 10 && whale.getWidth() > iceX -10 ) {
				state = 2;
			}
			*/

		}
		if (state == 2) {
			endscreen = Gdx.graphics.getHeight() / 2 - lose.getHeight() / 2;
			iceX = Gdx.graphics.getWidth() + 300;
			if (Gdx.input.justTouched()){
				state = 0;
			}
		}
		if (state == 0) {
			whaleY = Gdx.graphics.getHeight()/2 - whale.getHeight()/2;
			endscreen = -1000;
		}


 		batch.begin();
		batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		batch.draw(whale, Gdx.graphics.getWidth() / 3 - whale.getWidth() / 2, whaleY);
		batch.draw(lose,Gdx.graphics.getWidth() / 2 - lose.getWidth() / 2, endscreen);
		batch.draw(iceTop, iceX, Gdx.graphics.getHeight() - iceTop.getHeight() + offsetTop);
		batch.draw(iceBottom, iceX, -offsetBottom);
		batch.end();

		//whaleEllipse.set(Gdx.graphics.getWidth() / 3 - whale.getWidth() / 2, whaleY, whale.getWidth(), whale.getHeight());
		//shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
		//shapeRenderer.setColor(com.badlogic.gdx.graphics.Color.RED);
		//shapeRenderer.ellipse(whaleEllipse.x, whaleEllipse.y, whaleEllipse.width, whaleEllipse.height);
		//shapeRenderer.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		background.dispose();
	}
}
