package de.jjj.dnasic.ships;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import de.jjj.dnasic.screens.level.SpawnPoint;
import de.jjj.dnasic.weapons.Weapon;

public class Enemy2 extends EnemyShip {

    public Enemy2(SpawnPoint p, SpriteBatch batch) {
        super(new TextureAtlas(Gdx.files.internal("TextureAtlas/packed/NPC_Ship/NPC_Ship.atlas")).findRegion("Ship_2"), p.getX(), p.getY(), 1, 1, 50, new Weapon[2], batch);

        this.setWeapon(0, new Weapon(20, 2));
        this.setWeapon(1, new Weapon(20, 2));
    }
}
