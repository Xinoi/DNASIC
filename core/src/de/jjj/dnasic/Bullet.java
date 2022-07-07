package de.jjj.dnasic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;

public class Bullet extends Sprite {
    private float speed_x;
    private float speed_y;

    private float x;
    private float y;

    private int damage;

    public Bullet(float x, float y, float speed, int damage, float rotation, String texture){
        super(new Texture(Gdx.files.internal(texture)));

        this.speed_x = speed * MathUtils.cosDeg(rotation + 90);
        this.speed_y = speed * MathUtils.sinDeg(rotation + 90);
        this.setRotation(rotation + 90);

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
