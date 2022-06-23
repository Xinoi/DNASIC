package de.jjj.dnasic.screens.level;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import de.jjj.dnasic.Bullet;

public class Level1 extends Level{

    private SpriteBatch batch;
    Bullet b;

    public Level1() {
        super(new Sprite(new Texture(Gdx.files.internal("Images/Background/Background_1.jpg"))));
        batch = new SpriteBatch();
        b=new Bullet();
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(new Color(Color.BLACK));
        super.render(new Sprite[]{b}, batch);
        super.update(new Bullet[]{b}, delta);
    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

}
