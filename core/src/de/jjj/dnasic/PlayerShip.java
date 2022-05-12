package de.jjj.dnasic;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class PlayerShip extends Sprite {

    private float x;
    private float y;

    public PlayerShip(Texture shipSkin, float xN, float yN) {
        super(shipSkin);
        x = xN;
        y = yN;
        super.setPosition(x, y);
    }

    public float getX() {
        return x;
    }
    public float getY() {
        return y;
    }

    public void setCoords(int xN, int yN) {
        x = xN;
        y = yN;
    }

}
