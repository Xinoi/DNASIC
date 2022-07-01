package de.jjj.dnasic.screens.level;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.utils.TimeUtils;
import de.jjj.dnasic.Bullet;
import de.jjj.dnasic.DNASIC;
import de.jjj.dnasic.screens.UpgradeScreen;
import de.jjj.dnasic.ships.Enemy1;
import de.jjj.dnasic.ships.EnemyShip;
import de.jjj.dnasic.ships.PlayerShip;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class Level extends ScreenAdapter implements InputProcessor {

    private Sprite background;
    private Sprite healthImage;
    private BitmapFont healthText;
    private PlayerShip playerShip;
    TextureAtlas playerAtlas;
    private Music bgMusic;
    protected float ticker;
    public static String currentShip = "Ship_1";
    public List<EnemyShip> enemies;
    private HashMap<String, Boolean> keysPressed;
    private boolean shootRegistered;

    SpawnPoint[] spawnPoints1;
    SpawnPoint[] spawnPoints2;

    private long lastHit;

    public Level(Sprite BackgroundSprite) {
        background = new Sprite(BackgroundSprite);
        playerAtlas = new TextureAtlas(Gdx.files.internal("TextureAtlas/packed/Player_Ship/Player_Ship.atlas"));
        playerShip = new PlayerShip(playerAtlas.findRegion(currentShip),300 , Gdx.graphics.getHeight() / 2 - playerAtlas.findRegion(currentShip).getRegionHeight(), 500f);
        playerShip.rotate(-90);

        healthImage = new Sprite(new Texture(Gdx.files.internal("Images/Icons/heart.png")));
        healthImage.setPosition(healthImage.getX() + 10, Gdx.graphics.getHeight() - healthImage.getHeight() - 10);
        healthText = DNASIC.INSTANCE.getFont();

        this.keysPressed = new HashMap<String, Boolean>();
        this.shootRegistered = false;

        bgMusic = DNASIC.INSTANCE.getLevelMusic();
        
        enemies = new ArrayList<EnemyShip>();
        
        ticker = 0;

        spawnPoints1 = new SpawnPoint[5];
        spawnPoints2 = new SpawnPoint[5];
        fillSpawnPoints();

        this.lastHit = TimeUtils.millis();

        Gdx.input.setInputProcessor(this);
    }

    public void render(SpriteBatch batch) {
        batch.begin();
        background.draw(batch);
        healthImage.draw(batch);
        healthText.draw(batch, String.valueOf(playerShip.getHealth()), healthImage.getX() + 7, healthImage.getY() + 45);
        for(EnemyShip e : enemies) {
            if(e.getAlive()) {
                e.draw(batch);
            }
            for(Bullet b : e.getBullets()){
                b.draw(batch);
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
            playerShip.setRotation(90);
        }
        if(this.keysPressed.containsKey("D") && this.keysPressed.get("D")){
            moveX += 1;
            playerShip.setRotation(-90);
        }

        if(this.keysPressed.containsKey("U") && this.keysPressed.get("U")){
            DNASIC.INSTANCE.setScreen(new UpgradeScreen());
        }
        ticker = ticker + delta;

        playerShip.move(moveX, moveY, delta);
        playerShip.keepInBounds();
        playerShip.update(ticker);

        for(EnemyShip e : enemies) {
            if(e.getAlive()) {
                e.update();
                e.control(playerShip.getX(), playerShip.getY(), ticker);
            }

            for(Iterator<Bullet> iterator = e.getBullets().iterator(); iterator.hasNext();){
                Bullet b = iterator.next();
                b.update(delta);
                if(Intersector.overlaps(b.getBoundingRectangle(), playerShip.getBoundingRectangle())){
                    playerShip.inflictDamage(b.getDamage());
                    iterator.remove();
                }
            }

            if(TimeUtils.millis() - this.lastHit > playerShip.getHitCooldown() && Intersector.overlaps(e.getBoundingRectangle(), playerShip.getBoundingRectangle()) && e.getAlive()){
                playerShip.inflictDamage(10);
                this.lastHit = TimeUtils.millis();
            }
        }

        if((!DNASIC.INSTANCE.getSettings().getMouseControl() && this.keysPressed.containsKey("SPACE") && this.keysPressed.get("SPACE")) || (DNASIC.INSTANCE.getSettings().getMouseControl() && this.keysPressed.containsKey("LEFT") && this.keysPressed.get("LEFT"))){
            playerShip.shoot();
            this.shootRegistered = true;
        }

        for(Iterator<Bullet> iterator = playerShip.getBullets().iterator(); iterator.hasNext();){
            Bullet b = iterator.next();
            b.update(delta);
            for(EnemyShip e : enemies) {
                if (Intersector.overlaps(b.getBoundingRectangle(), e.getBoundingRectangle()) && e.getAlive()){
                    e.inflictDamage(b.getDamage());
                    iterator.remove();
                    break;
                }
            }

            if(b.getX() < 0 || b.getX() > Gdx.graphics.getWidth()){
                iterator.remove();
            }
        }
    }
    
    public void spawnEnemy(int nummer, SpriteBatch batch) {
    	switch(nummer) {

    	case 1:
    		enemies.add(new Enemy1(getFreeSpawnPoint(), batch));
    	}
    }

    public SpawnPoint getFreeSpawnPoint() {

        //search for a free spawnPoint
        for (SpawnPoint p : spawnPoints1) {
            if (p.isFree) {
                p.use();
                return p;
            }
        }
        for (SpawnPoint p : spawnPoints2) {
            if (p.isFree) {
                p.use();
                return p;
            }
        }
        return spawnPoints1[3];
    }

    public void fillSpawnPoints() {
        for (int i = 0; i < spawnPoints1.length; i++) {
            spawnPoints1[i] = new SpawnPoint(800, Gdx.graphics.getHeight() / 6 * (i + 1));
        }
        for (int i = 0; i < spawnPoints2.length; i++) {
            spawnPoints2[i] = new SpawnPoint(600, Gdx.graphics.getHeight() / 6 * (i + 1));
        }
    }

    @Override
    public boolean keyDown(int keycode) {
        // check "U" for Upgrade screen
        if(keycode == Input.Keys.U) {
            this.keysPressed.put("U", true);
        }

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
        //same here for Upgrade screen
        if(keycode == Input.Keys.U) {
            this.keysPressed.put("U", false);
        }
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
        if(button == Input.Buttons.LEFT) {
            this.keysPressed.put("LEFT", true);
        }
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if(button == Input.Buttons.LEFT) {
            this.keysPressed.put("LEFT", false);
            this.shootRegistered = false;
        }
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

    public static String getCurrentShip(){
        return currentShip;
    }

    @Override
    public void hide() {
        bgMusic.pause();
        DNASIC.INSTANCE.setLevelMusic(bgMusic);
        this.dispose();
    }

    @Override
    public void show() {
        super.show();

        this.keysPressed = new HashMap<>();
        Gdx.input.setInputProcessor(this);
    }
}


