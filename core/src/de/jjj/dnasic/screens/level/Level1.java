package de.jjj.dnasic.screens.level;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

import de.jjj.dnasic.ships.Enemy1;
import de.jjj.dnasic.ships.EnemyShip;

public class Level1 extends Level{

    private SpriteBatch batch;
    private int enemyCount = 0;


    public Level1() {
        super(new Sprite(new Texture(Gdx.files.internal("Images/Background/Background_1.jpg"))));
        batch = new SpriteBatch();
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(new Color(Color.BLACK));
        super.render(batch);
        super.update(delta);
        System.out.println(ticker);
        batch.begin();
        if(ticker >= 5 && enemyCount < 1) {
        	spawnEnemy(1, 10, 10, batch);
        	enemyCount ++;
        	System.out.println("enemy");
        }
        batch.end();
    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
    	batch.dispose();
    	this.dispose();
    }
    

}
