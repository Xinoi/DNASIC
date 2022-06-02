package de.jjj.dnasic.ships;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import de.jjj.dnasic.weapons.Weapon;

public class Ship extends Sprite {
    private float x;
    private float y;

    private float speed;

    private Weapon[] weapons;

    public Ship(TextureRegion texture, float x, float y, float speed, Weapon[] weapons) {
        super(texture);

        this.x = x;
        this.y = y;

        this.speed = speed;

        this.weapons = weapons;

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

    public void setCoordinates(float x, float y){
        this.x = x;
        this.y = y;

        super.setPosition(x, y);
    }

    public void move(float x, float y){
        this.setCoordinates(this.x + (x * this.speed), this.y + (y * this.speed));
    }

    public Weapon[] getWeapons(){
        return this.weapons;
    }

    public void setWeapon(int index, Weapon weapon){
        if(index < this.weapons.length){
            this.weapons[index] = weapon;
        }
    }
}
