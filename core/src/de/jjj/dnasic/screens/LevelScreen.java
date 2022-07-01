package de.jjj.dnasic.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import de.jjj.dnasic.DNASIC;
import de.jjj.dnasic.screens.level.Level1;

public class  LevelScreen extends ScreenAdapter {

    private Stage stage;
    private Table table;

    private Image lvl1;
    private Image lvl2;
    private Image lvl3;

    float elapsedTime;
    private Label selectText;

    private Animation<TextureRegion> bgAnimation;
    private Music bgMusic;

    private SpriteBatch batch;
    public LevelScreen() {

        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        bgAnimation = DNASIC.INSTANCE.getMenuBackground();

        batch = new SpriteBatch();

        table = new Table();
        table.setFillParent(true);

        lvl1 = new Image(new Texture(Gdx.files.internal("Images/LevelCover/lvl1.png")));
        lvl1.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                DNASIC.INSTANCE.setScreen(new Level1());
            }
        });
        lvl2 = new Image(new Texture(Gdx.files.internal("Images/LevelCover/lvl2.png")));
        lvl2.addListener(new ClickListener() {
        	@Override
        	public void clicked(InputEvent event, float x, float y) {
                // Stop music before leaving the menu
                bgMusic.stop();

                System.out.println("lvl2 was clicked");
        	}
        });
        lvl3 = new Image(new Texture(Gdx.files.internal("Images/LevelCover/lvl3.png")));
        lvl3.addListener(new ClickListener() {
        	@Override
        	public void clicked(InputEvent event, float x, float y) {
                // Stop music before leaving the menu
                bgMusic.stop();

                System.out.println("lvl3 was clicked");
        	}
        });


        selectText = new Label("select level by clicking on it", DNASIC.INSTANCE.getLabelStyle());

        table.add(selectText).padBottom(50).padLeft(50).colspan(3);
        table.row();
        table.add(lvl1).space(50);
        table.add(lvl2).space(50);
        table.add(lvl3).space(50);

        // background Music
        bgMusic = DNASIC.INSTANCE.getMenuMusic();

        stage.addActor(table);
        stage.addActor(DNASIC.INSTANCE.BackB);
    }

    @Override
    public void hide() {
        bgMusic.pause();
        DNASIC.INSTANCE.setMenuMusic(bgMusic);
        this.dispose();
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(new Color(Color.BLACK));

        if(DNASIC.INSTANCE.getSettings().getMusic()){
            bgMusic.play();
        }else {
            bgMusic.stop();
        }

        batch.begin();
        elapsedTime += Gdx.graphics.getDeltaTime();
        batch.draw(bgAnimation.getKeyFrame(elapsedTime, true), 0, 0);
        batch.end();

        stage.act(delta);
        stage.draw();
    }

    @Override
    public void dispose() {
        stage.dispose();
        batch.dispose();
    }

}
