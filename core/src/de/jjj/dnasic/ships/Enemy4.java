package de.jjj.dnasic.ships;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import de.jjj.dnasic.screens.level.SpawnPoint;
import de.jjj.dnasic.weapons.Weapon;

public class Enemy4 extends EnemyShip {

    public Enemy4(SpawnPoint p, SpriteBatch batch) {
        super(new TextureAtlas(Gdx.files.internal("TextureAtlas/packed/NPC_Ship/NPC_Ship.atlas")).findRegion("Ship_4"), p.getX(), p.getY(), 1, 1, 50, new Weapon[3], batch);

        this.setWeapon(0, new Weapon(20, 2));
        this.setWeapon(1, new Weapon(40, 1));
        this.setWeapon(2, new Weapon(20, 2));
    }
}
