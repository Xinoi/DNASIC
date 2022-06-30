package de.jjj.dnasic.screens.level;

import java.util.Timer;
import java.util.TimerTask;

public class SpawnPoint{

    public boolean isFree;
    float posX;
    float posY;

    public SpawnPoint(float x, float y) {
        isFree = true;
        posX = x;
        posY = y;
    }

    public void use() {
        isFree = false;
        final Timer timer = new Timer();

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        isFree = true;
                    }
                }, 1000);
            }
        };

    }

    public boolean isFree() {
        return this.isFree;
    }

    public float getX() {
        return posX;
    }

    public float getY() {
        return posY;
    }

}
