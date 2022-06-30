package de.jjj.dnasic.ships;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import de.jjj.dnasic.Bullet;
import de.jjj.dnasic.screens.level.SpawnPoint;
import de.jjj.dnasic.weapons.Weapon;

import java.util.ArrayList;
import java.util.List;

public class Ship extends Sprite {
    protected float x;
    protected float y;

    private float speed;

    private Weapon[] weapons;
    private List<Bullet> bullets;

    public Ship(TextureRegion texture, float x, float y, float speed, Weapon[] weapons) {
        super(texture);

        this.x = x;
        this.y = y;

        this.speed = speed;

        this.weapons = weapons;
        this.bullets = new ArrayList<>();

        super.setPosition(x, y);
    }

    public Ship(TextureRegion texture, SpawnPoint p, float speed, Weapon[] weapons) {
        super(texture);

        this.x = p.getX();
        this.y = p.getY();

        this.speed = speed;

        this.weapons = weapons;
        this.bullets = new ArrayList<>();

        super.setPosition(x, y);
    }

    @Override
    public void setPosition(float x, float y) {
        super.setPosition(x, y);

        this.x = x;
        this.y = y;
    }

    public void updatePosition() {
        this.setPosition(this.x, this.y);
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

    public void keepInBounds() {
        if (this.getX() < 0) {
            this.setPosition(0, this.y);
        }
        if(this.getX() > Gdx.graphics.getWidth()) {
            this.setPosition(Gdx.graphics.getWidth(), this.y);
        }
        if(this.getY() < 0) {
            this.setPosition(this.x, 0);
        }
        if(this.getY() > Gdx.graphics.getHeight() - this.getHeight()) {
            this.setPosition(this.x, Gdx.graphics.getHeight() - this.getHeight());
        }
    }

    public Weapon[] getWeapons(){
        return this.weapons;
    }

    public void setWeapon(int index, Weapon weapon){
        if(index < this.weapons.length){
            this.weapons[index] = weapon;
        }
    }

    public void shoot(){
        if(this.getRotation() == -90) {
            bullets.add(new Bullet(this.x, this.y, this.weapons[0].getBulletSpeed(), this.weapons[0].getDamage()));
        } else {
            bullets.add(new Bullet(this.x, this.y, -this.weapons[0].getBulletSpeed(), this.weapons[0].getDamage()));
        }
    }

    public List<Bullet> getBullets(){
        return this.bullets;
    }

    public void death(){
        System.out.println("Ship was destroyed");
    }

    public void removeBullet(Bullet b){
        this.bullets.remove(b);
    }
}
