package de.jjj.dnasic;

public class GameData {
    private int highscore;
    private int shipEngine = 1;
    private int shipArmor = 10;
    private int shipDamage = 10;

    public int getHighscore() {
        return this.highscore;
    }

    public void setHighscore(int highscore){
        this.highscore = highscore;
    }

    public int getShipEngine(){
        return this.shipEngine;
    }

    public void setShipEngine(int engine){
        this.shipEngine = engine;
    }

    public int getShipArmor(){
        return this.shipArmor;
    }

    public void setShipArmor(int armor){
        this.shipArmor = armor;
    }

    public int getShipDamage(){
        return this.shipDamage;
    }

    public void setShipDamage(int damage){
        this.shipDamage = damage;
    }
}
