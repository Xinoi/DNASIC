package de.xisoi;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class MyGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("starter-ship-red.png");
	}

	@Override
	public void render () {
		ScreenUtils.clear(0, 0, 0, 1);
		batch.begin();
		batch.draw(img, Gdx.graphics.getWidth()/2 - img.getWidth(), Gdx.graphics.getHeight()/2 - img.getHeight(), 150, 150);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
