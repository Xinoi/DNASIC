package de.jjj.dnasic.ships;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import de.jjj.dnasic.weapons.Weapon;

public class Enemy1 extends EnemyShip {

    public Enemy1(float x, float y) {
        super(new TextureAtlas(Gdx.files.internal("TextureAtlas/packed/NPC_Ship/NPC_Ship.atlas")).findRegion("Ship_1"), x, y, 1, 1, 50, new Weapon[2]);

        this.setWeapon(0, new Weapon(10, 2));
        this.setWeapon(1, new Weapon(10, 2));
    }
}
