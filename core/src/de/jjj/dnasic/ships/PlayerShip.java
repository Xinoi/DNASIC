package de.jjj.dnasic.ships;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import de.jjj.dnasic.weapons.Weapon;

public class PlayerShip extends Ship {
  
    public PlayerShip(TextureRegion texture, float x, float y, float speed) {
        super(texture, x, y, speed, new Weapon[]{new Weapon(20, 700)});
        super.scale(2);
    }

    @Override
    public void death(){
        System.out.println("Player died!");
    }
}
