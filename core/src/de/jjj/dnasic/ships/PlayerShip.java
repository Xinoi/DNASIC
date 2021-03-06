package de.jjj.dnasic.ships;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import de.jjj.dnasic.DNASIC;
import de.jjj.dnasic.GameData;
import de.jjj.dnasic.screens.DeathScreen;
import de.jjj.dnasic.weapons.Weapon;

import java.util.Arrays;

public class PlayerShip extends Ship {
    private long hitCooldown;
    private float reloadUpgrade = DNASIC.INSTANCE.getGameData().getShipReload();
    Sound dieSound;
  
    public PlayerShip(TextureRegion texture, float x, float y, float speed) {
        super(texture, x, y, speed, new Weapon[]{new Weapon(20, 700, 300)}, 100, "Images/Laser/LaserBlau.png");
        super.scale(1);
        Weapon[] weapons = this.getWeapons();
        weapons[0].decreaseReloadTime(reloadUpgrade);
        this.health += DNASIC.INSTANCE.getGameData().getShipArmor();
        dieSound = Gdx.audio.newSound(Gdx.files.internal("Sounds/Death.mp3"));
        // set cooldown after enemy hit (in ms)
        this.hitCooldown = 1000;
    }

    @Override
    public void death(){
        dieSound.play();
        DNASIC.INSTANCE.setScreen(new DeathScreen());
    }

    public void update(float ticker) {
        Vector2 MousePosition = new Vector2(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY());
        Vector2 position = new Vector2(this.getX(), this.getY());
        Vector2 line = new Vector2(this.getX() - 10, this.getY());
        Vector2 toLine = new Vector2(line.x - position.x, line.y - position.y);
        Vector2 toMouse = new Vector2(MousePosition.x - position.x, MousePosition.y - position.y);

        float angleToPlayer = toMouse.angleDeg(toLine);
        System.out.println("angle: "+ angleToPlayer);

        this.setRotation(angleToPlayer + 90);
    }

    public long getHitCooldown(){
        return this.hitCooldown;
    }
}
