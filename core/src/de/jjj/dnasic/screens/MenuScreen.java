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
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import de.jjj.dnasic.DNASIC;

public class MenuScreen extends ScreenAdapter {

    private float buttonHeigth;
    private float startButtonWidth;

    private float settingsButtonWidth;

    private float exitButtonWidth;

    private Table table;

    private SpriteBatch batch;
    private Music bgMusic;
    private Label titleLabel;
    private Color white;

    private Stage stage;
    private TextButton startB;
    private TextButton settingsB;
    private TextButton exitButton;

    private Sound clickSound;

    public MenuScreen() {

        buttonHeigth = 80;

        startButtonWidth = 200;
        settingsButtonWidth = 250;
        exitButtonWidth = 200;

        batch = new SpriteBatch();

        // set a stage
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        table = new Table();
        table.setFillParent(true);
        // create title
        createTitle();
        // create Buttons
        createButtons();

        startB.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                DNASIC.INSTANCE.setScreen(new LevelScreen());
            }
        });
        settingsB.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                DNASIC.INSTANCE.setScreen(new SettingsScreen());
            }
        });
        exitButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.exit();
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

        table.add(titleLabel).space(100);
        table.row();
        table.add(startB).space(50).size(startButtonWidth, buttonHeigth);
        table.row();
        table.add(settingsB).space(50).size(settingsButtonWidth, buttonHeigth);
        table.row();
        table.add(exitButton).space(50).size(exitButtonWidth, buttonHeigth);
        // add things to stage
        stage.addActor(table);
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

    private void createTitle(){
        white = new Color();
        white.set(Color.WHITE);
        titleLabel = new Label("D N A S I C", DNASIC.INSTANCE.getLabelStyle());
        titleLabel.setPosition(Gdx.graphics.getWidth() / 2 - titleLabel.getWidth() / 2, Gdx.graphics.getHeight() - Gdx.graphics.getHeight() / 4);
    }
    private void createButtons() {
        startB = new TextButton("Play", DNASIC.INSTANCE.getButtonStyle());
        settingsB = new TextButton("Settings", DNASIC.INSTANCE.getButtonStyle());
        exitButton = new TextButton("Exit", DNASIC.INSTANCE.getButtonStyle());
    }

}
