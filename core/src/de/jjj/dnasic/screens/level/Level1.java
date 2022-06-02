package de.jjj.dnasic.screens.level;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class Level1 extends ScreenAdapter {

    private SpriteBatch batch;
    private Sprite background;

    public Level1() {
        batch = new SpriteBatch();
        background = new Sprite(new Texture(Gdx.files.internal("Images/Background/Background_1.jpg")));
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(new Color(Color.BLACK));
        batch.begin();
        background.draw(batch);
        batch.end();
    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

}
