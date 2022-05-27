package de.jjj.dnasic.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import de.jjj.dnasic.DNASIC;

public class MenuScreen extends ScreenAdapter {

    private float startButtonWidth;
    private float startButtonHeigth;
    private float startButtonx;
    private float startButtony;

    private float settingsButtonWidth;
    private float settingsButtonHeigth;
    private float settingsButtonx;
    private float settingsButtony;

    private SpriteBatch batch;
    private Music bgMusic;
    private Label titleLabel;
    private Color white;

    private Stage stage;
    private TextButton startB;
    private TextButton settingsB;

    private Sound clickSound;

    public MenuScreen() {

        startButtonWidth = 200;
        startButtonHeigth = 100;
        startButtonx = Gdx.graphics.getWidth() / 2 - startButtonWidth/2;
        startButtony = Gdx.graphics.getHeight() - Gdx.graphics.getHeight() / 4 * 2;

        settingsButtonWidth = 250;
        settingsButtonHeigth = 100;
        settingsButtonx = Gdx.graphics.getWidth() / 2 - settingsButtonWidth/2;
        settingsButtony = Gdx.graphics.getHeight() - Gdx.graphics.getHeight() / 4 * 3;

        batch = new SpriteBatch();

        // set a stage
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        // create title
        createTitle();
        // create Buttons
        createStartButton();
        startB.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                DNASIC.INSTANCE.setScreen(new LevelScreen());
            }
        });
        createSettingsButton();
        settingsB.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                DNASIC.INSTANCE.setScreen(new SettingsScreen());
            }
        });

        // add button clicks
        clickSound = Gdx.audio.newSound(Gdx.files.internal("Sounds/Click.wav"));
        startB.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                clickSound.play();
                return super.touchDown(event, x, y, pointer, button);
            }
        });
        settingsB.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                clickSound.play();
                return super.touchDown(event, x, y, pointer, button);
            }
        });

        // background Music
        bgMusic = Gdx.audio.newMusic(Gdx.files.internal("Music/Rebel.mp3"));
        bgMusic.setLooping(true);
        bgMusic.setVolume(0.3f);

        // add things to stage
        addActors();
    }

    @Override
    public void hide() {
        bgMusic.stop();
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
        batch.end();

        stage.act(delta);
        stage.draw();
    }

    @Override
    public void dispose() {
        stage.dispose();
        batch.dispose();
        bgMusic.dispose();
    }

    private void addActors() {
        Actor[] actors = {startB, settingsB, titleLabel};
        for(int i = 0; i < 3; i++) {
            stage.addActor(actors[i]);
        }
    }

    private void createTitle(){
        white = new Color();
        white.set(Color.WHITE);
        titleLabel = new Label("D N A S I C", DNASIC.INSTANCE.getLabelStyle());
        titleLabel.setPosition(Gdx.graphics.getWidth() / 2 - titleLabel.getWidth() / 2, Gdx.graphics.getHeight() - Gdx.graphics.getHeight() / 4);
    }

    private void createStartButton() {
        startB = new TextButton("play", DNASIC.INSTANCE.getButtonStyle());
        startB.setBounds(startButtonx, startButtony, startButtonWidth, startButtonHeigth);
    }

    private void createSettingsButton() {
        settingsB = new TextButton("settings", DNASIC.INSTANCE.getButtonStyle());
        settingsB.setBounds(settingsButtonx, settingsButtony, settingsButtonWidth, settingsButtonHeigth);
    }

}
