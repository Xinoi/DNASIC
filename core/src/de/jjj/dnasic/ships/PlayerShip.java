package de.jjj.dnasic.ships;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import de.jjj.dnasic.DNASIC;
import de.jjj.dnasic.screens.DeathScreen;
import de.jjj.dnasic.weapons.Weapon;

public class PlayerShip extends Ship {
  
    public PlayerShip(TextureRegion texture, float x, float y, float speed) {
        super(texture, x, y, speed, new Weapon[]{new Weapon(20, 700)}, 100);
        super.scale(1);
    }

    @Override
    public void death(){
        //DNASIC.INSTANCE.setScreen(new DeathScreen());
    }

    public void update(float ticker) {
        Vector2 P = new Vector2(this.x, this.y);
        Vector2 M = new Vector2(Gdx.input.getX(), Gdx.input.getY());
        Vector2 L = new Vector2(this.getX() - 10, this.getY());
        Vector2 PL = new Vector2(P.x - L.x, P.y - L.y);
        Vector2 PM = new Vector2(P.x - M.x, P.y - M.y);

        float angleToMouse = PM.angleDeg(PL) * -1;
        System.out.println("angle: "+ angleToMouse);

        this.setRotation(angleToMouse + 90);
    }

}
