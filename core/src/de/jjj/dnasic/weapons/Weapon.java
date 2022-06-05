package de.jjj.dnasic.weapons;

public class Weapon {
    private int damage;
    private float bulletSpeed;

    public Weapon(int damage, float bulletSpeed){
        this.damage = damage;
        this.bulletSpeed = bulletSpeed;
    }

    public int getDamage(){
        return this.damage;
    }

    public float getBulletSpeed(){
        return this.bulletSpeed;
    }
}
