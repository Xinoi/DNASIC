package de.jjj.dnasic.ships;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.TimeUtils;
import de.jjj.dnasic.Bullet;
import de.jjj.dnasic.screens.level.SpawnPoint;
import de.jjj.dnasic.weapons.Weapon;

import java.util.ArrayList;
import java.util.List;

public class Ship extends Sprite {
    protected int health;

    protected float x;
    protected float y;

    private float speed;

    Sound shootSound;
    private Weapon[] weapons;
    private List<Bullet> bullets;

    private long lastShot;
    private String bulletTexture;

    public Ship(TextureRegion texture, float x, float y, float speed, Weapon[] weapons, int health, String bulletTexture) {
        super(texture);

        this.x = x;
        this.y = y;

        this.speed = speed;
        this.bulletTexture = bulletTexture;

        shootSound = Gdx.audio.newSound(Gdx.files.internal("Sounds/Shot.wav"));

        this.weapons = weapons;
        this.bullets = new ArrayList<>();

        this.health = health;

        super.setPosition(x, y);

        this.lastShot = TimeUtils.millis();
    }

    public Ship(TextureRegion texture, SpawnPoint p, float speed, Weapon[] weapons, int health) {
        super(texture);

        this.x = p.getX();
        this.y = p.getY();

        this.speed = speed;

        this.weapons = weapons;
        this.bullets = new ArrayList<>();

        this.health = health;

        super.setPosition(x, y);
    }

    @Override
    public void setPosition(float x, float y) {
        super.setPosition(x, y);

        this.x = x;
        this.y = y;
    }

    public void update() {
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
        if(TimeUtils.millis() - this.lastShot > this.weapons[0].getReloadTime()) {
            bullets.add(new Bullet(this.x, this.y, this.weapons[0].getBulletSpeed(), this.weapons[0].getDamage(), this.getRotation(), this.bulletTexture));
            shootSound.play();
            this.lastShot = TimeUtils.millis();
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

    public int getHealth(){
        return this.health;
    }

    public void inflictDamage(int amount){
        this.health -= amount;

        if(this.health <= 0){
            this.death();
        }
    }
}
