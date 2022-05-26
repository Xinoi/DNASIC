package de.jjj.dnasic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;

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

    private BitmapFont titleFont;

    private Label titleLabel;
    private Label.LabelStyle titleStyle;
    private Color white;

    private Stage stage;

    private TextureAtlas atlas;
    private Skin skin;

    private TextButton startB;
    private TextButton settingsB;
    private TextButton exitB;
    private TextButton.TextButtonStyle bStyle;

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

        titleFont = new BitmapFont(Gdx.files.internal("BitmapFonts/MainFont.fnt"));

        // set a stage
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        // create title
        createTitle();

        // create button Styles
        createButtonStyles();

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
        titleFont.dispose();
        atlas.dispose();
        skin.dispose();
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
        titleStyle = new Label.LabelStyle(titleFont, white);
        titleLabel = new Label("D N A S I C", titleStyle);
        titleLabel.setPosition(Gdx.graphics.getWidth() / 2 - titleLabel.getWidth() / 2, Gdx.graphics.getHeight() - Gdx.graphics.getHeight() / 4);
    }

    private void createStartButton() {
        startB = new TextButton("play", bStyle);
        startB.setBounds(startButtonx, startButtony, startButtonWidth, startButtonHeigth);
    }

    private void createSettingsButton() {
        settingsB = new TextButton("settings", bStyle);
        settingsB.setBounds(settingsButtonx, settingsButtony, settingsButtonWidth, settingsButtonHeigth);
    }

    private void createButtonStyles() {
        atlas = new TextureAtlas(Gdx.files.internal("TextureAtlas/packed/Button/buttons.atlas"));
        skin = new Skin();
        skin.addRegions(atlas);
        bStyle = new TextButton.TextButtonStyle();
        bStyle.font = titleFont;
        bStyle.fontColor = Color.GRAY;
        bStyle.up = skin.getDrawable("button_up");
        bStyle.down = skin.getDrawable("button_down");
        bStyle.pressedOffsetX = 1;
        bStyle.pressedOffsetY = -1;
    }

}
