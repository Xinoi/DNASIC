package de.jjj.dnasic.screens.level;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Intersector;
import de.jjj.dnasic.Bullet;
import de.jjj.dnasic.DNASIC;
import de.jjj.dnasic.ships.Enemy1;
import de.jjj.dnasic.ships.EnemyShip;
import de.jjj.dnasic.ships.PlayerShip;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Level extends ScreenAdapter implements InputProcessor {

    private Sprite background;
    private PlayerShip playerShip;
    TextureAtlas playerAtlas;
    private Music bgMusic;
    protected float ticker;
    
    public List<EnemyShip> enemies;

    private HashMap<String, Boolean> keysPressed;
    private boolean shootRegistered;

    public Level(Sprite BackgroundSprite) {
        background = new Sprite(BackgroundSprite);
        playerAtlas = new TextureAtlas(Gdx.files.internal("TextureAtlas/packed/Player_Ship/Player_Ship.atlas"));
        playerShip = new PlayerShip(playerAtlas.findRegion("Ship_1"),300 , Gdx.graphics.getHeight() / 2 - playerAtlas.findRegion("Ship_2").getRegionHeight(), 500f);
        playerShip.rotate(-90);

        this.keysPressed = new HashMap<String, Boolean>();
        this.shootRegistered = false;

        bgMusic = Gdx.audio.newMusic(Gdx.files.internal("Music/Rebel.mp3"));
        bgMusic.setLooping(true);
        bgMusic.setVolume(0.3f);
        
        enemies = new ArrayList<EnemyShip>();
        
        ticker = 0;

        Gdx.input.setInputProcessor(this);
    }

    public void render(SpriteBatch batch) {
        batch.begin();
        background.draw(batch);
        for(EnemyShip e : enemies) {
            if(e.getAlive()) {
                e.draw(batch);
            }
        }
        for(Bullet b : playerShip.getBullets()){
            b.draw(batch);
        }
        playerShip.draw(batch);
        batch.end();

        if(DNASIC.INSTANCE.getSettings().getMusic()){
            bgMusic.play();
        }else {
            bgMusic.stop();
        }
    }

    public void update(float delta) {
        float moveX = 0;
        float moveY = 0;
        if(this.keysPressed.containsKey("W") && this.keysPressed.get("W")){
            moveY += 1;
        }
        if(this.keysPressed.containsKey("S") && this.keysPressed.get("S")){
            moveY -= 1;
        }
        if(this.keysPressed.containsKey("A") && this.keysPressed.get("A")){
            moveX -= 1;
        }
        if(this.keysPressed.containsKey("D") && this.keysPressed.get("D")){
            moveX += 1;
        }
        
        ticker = ticker + delta;

        playerShip.move(moveX, moveY, delta);
        playerShip.keepInBounds();

        if(this.keysPressed.containsKey("SPACE") && this.keysPressed.get("SPACE") && !this.shootRegistered){
            playerShip.shoot();
            this.shootRegistered = true;
        }

        for(Bullet b : playerShip.getBullets()){
            b.update(delta);
            for(EnemyShip e : enemies) {
                if (Intersector.overlaps(b.getBoundingRectangle(), e.getBoundingRectangle()) && e.getAlive()){
                    e.death();
                }
            }
        }

        // check collisions and remove dead enemies
        for(EnemyShip e : enemies){
            if(Intersector.overlaps(e.getBoundingRectangle(), playerShip.getBoundingRectangle()) && e.getAlive()){
                playerShip.death();
            }
            if(!e.getAlive()){

            }
        }
    }

    
    public void spawnEnemy(int number, float x, float y, SpriteBatch batch) {
    	switch(number) {
    	case 1:
    		enemies.add(new Enemy1(x, y, batch));
    	}
    }

    @Override
    public boolean keyDown(int keycode) {
        if(keycode == Input.Keys.W) {
            this.keysPressed.put("W", true);
        }if(keycode == Input.Keys.S) {
            this.keysPressed.put("S", true);
        }if(keycode == Input.Keys.D) {
            this.keysPressed.put("D", true);
        }if(keycode == Input.Keys.A) {
            this.keysPressed.put("A", true);
        }if(keycode == Input.Keys.SPACE){
            this.keysPressed.put("SPACE", true);
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        if(keycode == Input.Keys.W) {
            this.keysPressed.put("W", false);
        }if(keycode == Input.Keys.S) {
            this.keysPressed.put("S", false);
        }if(keycode == Input.Keys.D) {
            this.keysPressed.put("D", false);
        }if(keycode == Input.Keys.A) {
            this.keysPressed.put("A", false);
        }if(keycode == Input.Keys.SPACE){
            this.keysPressed.put("SPACE", false);
            this.shootRegistered = false;
        }
        return false;
    }
    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }
}
