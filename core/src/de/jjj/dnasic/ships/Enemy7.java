package de.jjj.dnasic.ships;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import de.jjj.dnasic.weapons.Weapon;

public class Enemy7 extends EnemyShip {

    public Enemy7(float x, float y) {
        super(new TextureAtlas(Gdx.files.internal("TextureAtlas/packed/NPC_Ship/NPC_Ship.atlas")).findRegion("Ship_7"), x, y, 0.5f, 0.5f, 150, new Weapon[1]);

        this.setWeapon(0, new Weapon(20, 0.75f));
        this.setWeapon(1, new Weapon(20, 0.75f));
    }
}
