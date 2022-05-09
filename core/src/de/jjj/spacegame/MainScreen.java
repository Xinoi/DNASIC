package de.jjj.spacegame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class MainScreen extends ScreenAdapter {

    private SpriteBatch batch;
    private Texture img;
    private Music bgMusic;

    public MainScreen() {
       batch = new SpriteBatch();
       img = new Texture("Ship/1_Triebwerke/Blaues_Schiff/0Rot1Blau.png");

       bgMusic = Gdx.audio.newMusic(Gdx.files.internal("Music/Startmenu.wav"));
       bgMusic.setLooping(true);
       bgMusic.play();
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
