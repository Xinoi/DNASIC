package de.jjj.dnasic.ships;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class PlayerShip extends Ship {

    public PlayerShip(TextureRegion texture, float x, float y, float speed) {
        super(texture, x, y, speed);

        super.scale(2);
    }


}
