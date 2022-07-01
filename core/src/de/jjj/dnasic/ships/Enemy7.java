package de.jjj.dnasic.ships;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import de.jjj.dnasic.screens.level.SpawnPoint;
import de.jjj.dnasic.weapons.Weapon;

public class Enemy7 extends EnemyShip {

    public Enemy7(SpawnPoint p, SpriteBatch batch) {
        super(new TextureAtlas(Gdx.files.internal("TextureAtlas/packed/NPC_Ship/NPC_Ship.atlas")).findRegion("Ship_7"), p.getX(), p.getY(), 0.5f, 0.5f, 150, new Weapon[1], batch);

        this.setWeapon(0, new Weapon(20, 0.75f, 2000));
        this.setWeapon(1, new Weapon(20, 0.75f, 2000));
    }
}
