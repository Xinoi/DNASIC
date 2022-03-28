package de.xisoi;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;

public class MainScreen extends ScreenAdapter {

    SpriteBatch batch;
    Texture img;
    ShapeRenderer sr;
    Vector2 posShip;
    float maxVelocityShip;

    public MainScreen() {
       maxVelocityShip = 500.0f;
       batch = new SpriteBatch();
       img = new Texture("Ship/1_Triebwerke/Blaues_Schiff/0Rot1Blau.png");
       sr = new ShapeRenderer();
       posShip = new Vector2(Gdx.graphics.getWidth()/2 - img.getWidth(), Gdx.graphics.getHeight()/2 - img.getHeight());
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);

        //get input and move ship
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            posShip.y += maxVelocityShip * delta;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            posShip.y -= maxVelocityShip * delta;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            posShip.x -= maxVelocityShip * delta;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            posShip.x += maxVelocityShip * delta;
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            Gdx.app.exit();
        }

        //draw the ship
        batch.begin();
        batch.draw(img, posShip.x, posShip.y - img.getHeight(), 150, 150);
        batch.end();

        //draw a rectangle
        sr.begin(ShapeRenderer.ShapeType.Filled);
        sr.setColor(Color.WHITE);
        sr.rect(Gdx.graphics.getWidth() / 2 - 350, Gdx.graphics.getHeight() -40, 700, 20);
        sr.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        img.dispose();
    }

}
