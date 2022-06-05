package de.jjj.dnasic.ships;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import de.jjj.dnasic.weapons.Weapon;

public class PlayerShip extends Ship {

    public PlayerShip(TextureRegion texture, float x, float y) {
        super(texture, x, y, 1f, new Weapon[3]);
    }


}
