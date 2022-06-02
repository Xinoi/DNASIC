package de.jjj.dnasic.screens.level;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import de.jjj.dnasic.ships.PlayerShip;

public class Level extends ScreenAdapter implements InputProcessor {

    float speedx = 0;
    float speedy = 0;
    private Sprite background;
    private PlayerShip playerShip;
    TextureAtlas playerAtlas;

    public Level(Sprite BackgroundSprite) {
        background = new Sprite(BackgroundSprite);
        playerAtlas = new TextureAtlas(Gdx.files.internal("TextureAtlas/packed/Player_Ship/Player_Ship.atlas"));
        playerShip = new PlayerShip(playerAtlas.findRegion("Ship_1"),300 , Gdx.graphics.getHeight() / 2 - playerAtlas.findRegion("Ship_1").getRegionHeight());
        playerShip.scale(2); playerShip.rotate(-90);

        Gdx.input.setInputProcessor(this);
    }

    public void render(SpriteBatch batch) {
        batch.begin();
        background.draw(batch);
        playerShip.draw(batch);
        batch.end();


    }

    public void update(float delta) {
        playerShip.move(speedx, speedy, delta);
    }

    @Override
    public void hide(){

    }

    @Override
    public void render(float delta) {

    }


    @Override
    public boolean keyDown(int keycode) {
        if(keycode == Input.Keys.W) {
            speedy = 500;
        }if(keycode == Input.Keys.S) {
            speedy = -500;
        }if(keycode == Input.Keys.D) {
            speedx = 500;
        }if(keycode == Input.Keys.A) {
            speedx = -500;
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        if(keycode == Input.Keys.W) {
            speedy = 0;
        }if(keycode == Input.Keys.S) {
            speedy = 0;
        }if(keycode == Input.Keys.D) {
            speedx = 0;
        }if(keycode == Input.Keys.A) {
            speedx = 0;
        }
        return false;
    }
    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }
}
