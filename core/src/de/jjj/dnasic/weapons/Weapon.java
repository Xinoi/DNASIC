package de.jjj.dnasic.weapons;

public class Weapon {
    private int damage;
    private float bulletSpeed;
    private float reloadTime;

    public Weapon(int damage, float bulletSpeed, float reloadTime){
        this.damage = damage;
        this.bulletSpeed = bulletSpeed;
        this.reloadTime = reloadTime;
    }

    public int getDamage(){
        return this.damage;
    }

    public float getBulletSpeed(){
        return this.bulletSpeed;
    }

    public float getReloadTime(){
        return this.reloadTime;
    }
    public void setReloadTime(float reloadTime) {
        this.reloadTime = reloadTime;
    }
    public void decreaseReloadTime(float amount) {
        this.reloadTime -= amount;
    }
}
