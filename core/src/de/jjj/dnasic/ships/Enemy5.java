package de.jjj.dnasic.ships;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import de.jjj.dnasic.screens.level.SpawnPoint;
import de.jjj.dnasic.weapons.Weapon;

public class Enemy5 extends EnemyShip {

    public Enemy5(SpawnPoint p, SpriteBatch batch) {
        super(new TextureAtlas(Gdx.files.internal("TextureAtlas/packed/NPC_Ship/NPC_Ship.atlas")).findRegion("Ship_5"), p.getX(), p.getY(), 3, 1, 25, new Weapon[1], batch);

        this.setWeapon(0, new Weapon(10, 2));
    }
}
