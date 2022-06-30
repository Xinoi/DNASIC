package de.jjj.dnasic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Bullet extends Sprite {
    private float speed_x;
    private float speed_y;

    private float x;
    private float y;

    private int damage;

    public Bullet(float x, float y, float speed_x, int damage){
        super(new Texture(Gdx.files.internal("Images/Laser/LaserRot.png")));

        this.speed_x = speed_x;
        this.speed_y = 0;

        this.x = x;
        this.y = y;

        this.damage = damage;

        super.setPosition(this.x, this.y);
        super.setSize(25,4);
    }

    public void move(float dx, float dy){
        this.x += dx;
        this.y += dy;

        super.setPosition(x, y);
    }

    public int getDamage(){
        return this.damage;
    }

    public void update(float delta){
        move(speed_x * delta, speed_y * delta);
    }
}
