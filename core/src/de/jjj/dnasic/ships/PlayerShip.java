package de.jjj.dnasic.ships;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class PlayerShip extends Sprite {

    private float x;
    private float y;

    private float speed;

    public PlayerShip(TextureRegion shipSkin, float x, float y) {
        super(shipSkin);

        this.x = x;
        this.y = y;

        this.speed = 1f;

        super.setPosition(x, y);
    }

    public float getX() {
        return x;
    }
    public float getY() {
        return y;
    }

    public float getSpeed(){
        return this.speed;
    }
    public void increaseSpeed(float amount){
        this.speed += amount;
    }

    public void setCoords(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void move(char direction, float amount) {
        if(direction == 'u') {
            y += amount * this.speed;
        }else if(direction == 'd') {
            y -= amount * this.speed;
        }else if(direction == 'r') {
            x += amount * this.speed;
        }else if(direction == 'l') {
            x -= amount * this.speed;
        }

        setCoords(x, y);
    }

}
