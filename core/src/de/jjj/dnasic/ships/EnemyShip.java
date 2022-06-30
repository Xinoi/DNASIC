package de.jjj.dnasic.ships;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector;
import com.badlogic.gdx.math.Vector2;
import de.jjj.dnasic.screens.level.SpawnPoint;
import de.jjj.dnasic.weapons.Weapon;

public class EnemyShip extends Ship {
    private float turnSpeed;
    private boolean spawned = false;
    private boolean alive;

    public EnemyShip(TextureRegion texture, float x, float y, float speed, float turnSpeed, int health, Weapon[] weapons, SpriteBatch batch){
        super(texture, x, y, speed, weapons, health);
        this.x = x;
        this.y = y;

        this.turnSpeed = turnSpeed;

        this.alive = true;
        
        spawned = true;
    }

    public EnemyShip(TextureRegion texture, SpawnPoint p, float speed, float turnSpeed, int health, Weapon[] weapons, SpriteBatch batch) {
        super(texture, p, speed, weapons, health);
        this.x = p.getX();
        this.y = p.getY();

        this.turnSpeed = turnSpeed;

        spawned = true;
    }

    public void update(float playerShipx, float playerSipy) {
        //get Winklel zu player
        Vector2 playerPosition = new Vector2(playerShipx, playerSipy);
        Vector2 position = new Vector2(this.getX(), this.getY());
        Vector2 line = new Vector2(this.getX() - 10, this.getY());
        Vector2 toLine = new Vector2(line.x - position.x, line.y - position.y);
        Vector2 toPlayer = new Vector2(playerPosition.x - position.x, playerPosition.y - position.y);

        float angleToPlayer = toPlayer.angleDeg(toLine);

        //set Rotation to player
        this.setRotation(angleToPlayer + 90);

    }
    
    public boolean isSpawned() {
    	return spawned;
    }

    @Override
    public void death(){
        this.alive = false;
    }

    public boolean getAlive(){
        return this.alive;
    }
}


