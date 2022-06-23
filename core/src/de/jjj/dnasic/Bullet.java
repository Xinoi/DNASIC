package de.jjj.dnasic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Bullet extends Sprite {
    private float speed_x;
    private float speed_y;

    private float x;
    private float y;

    public Bullet(){
        super(new Texture(Gdx.files.internal("Images/Icons/Icon32.png")));

        speed_x = 100;
        speed_y = 100;

        x = 100;
        y = 100;

        super.setPosition(x, y);
    }

    public void move(float dx, float dy){
        this.x += dx;
        this.y += dy;

        super.setPosition(x, y);
    }

    public void update(float delta){
        move(speed_x * delta, speed_y * delta);
    }
}
