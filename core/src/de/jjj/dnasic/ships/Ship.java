package de.jjj.dnasic.ships;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Ship extends Sprite {
    private float x;
    private float y;

    private float speed;

    public Ship(TextureRegion texture, float x, float y, float speed) {
        super(texture);

        this.x = x;
        this.y = y;

        this.speed = speed;

        super.setPosition(x, y);
    }

    public float getX(){
        return this.x;
    }

    public float getY(){
        return this.y;
    }

    public float getSpeed(){
        return this.speed;
    }

    public void increaseSpeed(float amount){
        this.speed += amount;
    }

    public void move(float x, float y, float delta){
        this.x = this.x + (x * this.speed * delta);
        this.y = this.y + (y * this.speed * delta);

        this.setPosition(this.x, this.y);
    }

    public void detectEdge(float delta) {
        if (this.getX() <= 0) {
            move(1, 0, delta);
        }
        if(this.getX() >= Gdx.graphics.getWidth()) {
            move(-1, 0, delta);
        }
        if(this.getY() <= 0) {
            move(0, 1, delta);
        }
        if(this.getY() >= Gdx.graphics.getHeight() - this.getHeight()) {
            move(0, -1, delta);
        }
    }
}
