package de.xisoi;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class MainScreen extends ScreenAdapter {

    SpriteBatch batch;
    Texture img;

    public MainScreen() {
       batch = new SpriteBatch();
       img = new Texture("Ship/1_Triebwerke/Blaues_Schiff/0Rot1Blau.png");
    }

    @Override
    public void render(float render) {
        ScreenUtils.clear(0, 0, 0, 1);

        batch.begin();
        batch.draw(img, Gdx.graphics.getWidth()/2 - img.getWidth(), Gdx.graphics.getHeight()/2 - img.getHeight(), 150, 150);
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        img.dispose();
    }

}
