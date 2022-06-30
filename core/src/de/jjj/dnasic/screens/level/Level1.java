package de.jjj.dnasic.screens.level;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class Level1 extends Level{

    private SpriteBatch batch;

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
        if(ticker >= 3 && super.enemies.size() < 5) {
        	spawnEnemy(1, batch);
        	System.out.println("enemy");
        } else if (ticker >= 17 && super.enemies.size() < 10) {
            spawnEnemy(1, batch);
        } else if (ticker >= 30 && super.enemies.size() < 15) {
            spawnEnemy(1, batch);
        } else if (ticker >= 45 && super.enemies.size() < 22) {
            spawnEnemy(1, batch);
        } else if (ticker >= 60 && super.enemies.size() < 30) {
            spawnEnemy(1, batch);
        } else if (ticker >= 75 && super.enemies.size() < 34) {
            spawnEnemy(1, batch);
        } else if (ticker >= 85 && super.enemies.size() < 38) {
            spawnEnemy(1, batch);
        } else if (ticker >= 90 && super.enemies.size() < 40) {
            spawnEnemy(1, batch);
        } else if (ticker >= 95 && super.enemies.size() < 44) {
            spawnEnemy(1, batch);
        } else if (ticker >= 100 && super.enemies.size() < 47) {
            spawnEnemy(1, batch);
        }


        batch.end();
    }

    @Override
    public void hide() {
        super.hide();
    }

    @Override
    public void dispose() {
    	batch.dispose();
    	super.dispose();
    }
    

}
