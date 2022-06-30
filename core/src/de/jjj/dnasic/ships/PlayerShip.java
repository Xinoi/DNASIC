package de.jjj.dnasic.ships;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import de.jjj.dnasic.DNASIC;
import de.jjj.dnasic.screens.MenuScreen;
import de.jjj.dnasic.weapons.Weapon;

public class PlayerShip extends Ship {
  
    public PlayerShip(TextureRegion texture, float x, float y, float speed) {
        super(texture, x, y, speed, new Weapon[]{new Weapon(20, 700)}, 100);
        super.scale(1);
    }

    @Override
    public void death(){
        DNASIC.INSTANCE.setScreen(new MenuScreen());
    }
}
