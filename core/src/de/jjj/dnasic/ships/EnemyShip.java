package de.jjj.dnasic.ships;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector;
import com.badlogic.gdx.math.Vector2;
import de.jjj.dnasic.screens.level.SpawnPoint;
import de.jjj.dnasic.weapons.Weapon;

import java.util.Random;

public class EnemyShip extends Ship {
    private float turnSpeed;
    private boolean spawned = false;
    private boolean alive;
    private float shootTime;
    private float moveTime;
    private Boolean shot = false;
    private Boolean moved = false;
    private Boolean up;
    private Boolean right;
    private int moveAmount;

    private Random r = new Random();

    public EnemyShip(TextureRegion texture, float x, float y, float speed, float turnSpeed, int health, Weapon[] weapons, SpriteBatch batch){
        super(texture, x, y, speed, weapons, health, "Images/Laser/LaserRot.png");
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

    public void control(float playerShipx, float playerSipy, float ticker) {
        //get Winklel zu player
        Vector2 playerPosition = new Vector2(playerShipx, playerSipy);
        Vector2 position = new Vector2(this.getX(), this.getY());
        Vector2 line = new Vector2(this.getX() - 10, this.getY());
        Vector2 toLine = new Vector2(line.x - position.x, line.y - position.y);
        Vector2 toPlayer = new Vector2(playerPosition.x - position.x, playerPosition.y - position.y);

        float angleToPlayer = toPlayer.angleDeg(toLine);

        //set Rotation to player
        this.setRotation(angleToPlayer + 90);

        //shooting
        int delay = r.nextInt(2) + 1;

        if(shot == false) {
            shootTime = ticker + delay;
            shot = true;
        }
        if(ticker > shootTime && shot) {
            this.shoot();
            shot = false;
        }

        //movement

        if(moved == false) {
            up = r.nextBoolean();
            right = r.nextBoolean();
            int movDelay = r.nextInt(1);

            moveAmount = r.nextInt(400) + 1;
            moveTime = ticker + movDelay;
            moved = true;
        }
        if(ticker > moveTime && moved) {
            if(up) {
                    this.move(0, 1, 1);
                    if(this.y > Gdx.graphics.getHeight() - 100) {
                        up = false;
                    }
            }else {
                    this.move(0, -1, 1);
                    if(this.y <= 100) {
                        up = true;
                    }
            }

            if(right) {
                this.move(1, 0, 1);
                if(this.x > Gdx.graphics.getWidth() - 100) {
                    right = false;
                }
            }else {
                this.move(-1, 0, 1);
                if(this.x <= 100) {
                    right = true;
                }
            }

            moveAmount --;
            if(moveAmount == 0) {
                moved = false;
            }
        }



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


