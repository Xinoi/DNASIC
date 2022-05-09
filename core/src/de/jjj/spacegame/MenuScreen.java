package de.jjj.spacegame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class MenuScreen extends ScreenAdapter {

    private SpriteBatch batch;
    private Music bgMusic;
    private BitmapFont title;

    public MenuScreen() {
       batch = new SpriteBatch();

       title = new BitmapFont(Gdx.files.internal("BitmapFonts/TitleFont.fnt"));

       bgMusic = Gdx.audio.newMusic(Gdx.files.internal("Music/Startmenu.wav"));
       bgMusic.setLooping(true);
       bgMusic.play();
    }

    @Override
    public void render(float render) {
        ScreenUtils.clear(0, 0, 0, 1);

        batch.begin();
        // dont know how to get the width of the text
        title.draw(batch, "D N A S I C", Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() - 200);
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        title.dispose();
    }

}
