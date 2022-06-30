package de.jjj.dnasic.ships;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import de.jjj.dnasic.screens.level.SpawnPoint;
import de.jjj.dnasic.weapons.Weapon;

public class Enemy8 extends EnemyShip {

    public Enemy8(SpawnPoint p, SpriteBatch batch) {
        super(new TextureAtlas(Gdx.files.internal("TextureAtlas/packed/NPC_Ship/NPC_Ship.atlas")).findRegion("Ship_8"), p.getX(), p.getY(), 1, 1, 100, new Weapon[1], batch);

        this.setWeapon(0, new Weapon(20, 0.75f));
        this.setWeapon(1, new Weapon(5, 2));
        this.setWeapon(2, new Weapon(5, 2));
        this.setWeapon(3, new Weapon(20, 0.75f));
    }
}
