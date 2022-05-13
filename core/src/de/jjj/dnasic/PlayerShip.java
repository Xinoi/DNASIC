package de.jjj.dnasic;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class PlayerShip extends Sprite {

    private float x;
    private float y;

    public PlayerShip(TextureRegion shipSkin, float x, float y) {
        super(shipSkin);
        this.x = x;
        this.y = y;
        super.setPosition(x, y);
    }

    public float getX() {
        return x;
    }
    public float getY() {
        return y;
    }

    public void setCoords(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void move(char direction, float amount) {
        if(direction == 'u') {
            y += amount;
        }else if(direction == 'd') {
            y -= amount;
        }else if(direction == 'r') {
            x += amount;
        }else if(direction == 'l') {
            x -= amount;
        }

        setCoords(x, y);
    }

}