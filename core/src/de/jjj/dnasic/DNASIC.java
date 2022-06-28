package de.jjj.dnasic;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Json;
import de.jjj.dnasic.screens.MenuScreen;

public class DNASIC extends Game {
	private Json json;
    private Settings settings;
    private GameData data;

    public TextButton BackB;
    private TextureAtlas atlas;
    private Skin skin;
    private TextButton.TextButtonStyle bStyle;

    private Sound clickSound;

    private BitmapFont font;


    private Music menuMusic;

    private Animation<TextureRegion> menuBackground;


	public static DNASIC INSTANCE = new DNASIC();

	public DNASIC() {
		INSTANCE = this;

        this.json = new Json();
	}

	@Override
	public void create() {
        this.settings = this.loadSettings();
        this.data = this.loadGameData();

        font = new BitmapFont(Gdx.files.internal("BitmapFonts/MainFont.fnt"));

        createButtonStyles();
        BackB = new TextButton("  Back  ", bStyle);
        BackB.setPosition(50, 50);
        BackB.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                setScreen(new MenuScreen());
            }
        });

        clickSound = Gdx.audio.newSound(Gdx.files.internal("Sounds/Click.wav"));
        BackB.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                clickSound.play();
                return super.touchDown(event, x, y, pointer, button);
            }
        });


        // Initialize music for menus
        this.menuMusic = Gdx.audio.newMusic(Gdx.files.internal("Music/Rebel.mp3"));
        this.menuMusic.setLooping(true);
        this.menuMusic.setVolume(0.3f);

        // Create background for menus
        TextureAtlas menuBackgrouondAtlas = new TextureAtlas(Gdx.files.internal("TextureAtlas/packed/erde/erde.atlas"));
        this.menuBackground = new Animation<TextureRegion>(1/5f, menuBackgrouondAtlas.getRegions());

		this.setScreen(new MenuScreen());
	}

    @Override
    public void dispose() {
        super.dispose();
        System.exit(0);
    }

    @Override
    public void pause() {
        this.saveSettings(this.settings);
        this.saveGameData(this.data);

        super.pause();
    }

    public BitmapFont getFont() {
        return font;
    }

    public TextButton.TextButtonStyle getButtonStyle() {
        return bStyle;
    }

    public Label.LabelStyle getLabelStyle() {
        return new Label.LabelStyle(font, Color.WHITE);
    }

    private void createButtonStyles() {
        atlas = new TextureAtlas(Gdx.files.internal("TextureAtlas/packed/Button/buttons.atlas"));
        skin = new Skin();
        skin.addRegions(atlas);
        bStyle = new TextButton.TextButtonStyle();
        bStyle.font = font;
        bStyle.fontColor = Color.GRAY;
        bStyle.up = skin.getDrawable("button_up");
        bStyle.down = skin.getDrawable("button_down");
        bStyle.pressedOffsetX = 1;
        bStyle.pressedOffsetY = -1;
    }

    // Save game data to disk
	public void saveGameData(GameData data){
		String dataString = this.json.toJson(data);
		FileHandle file = com.badlogic.gdx.Gdx.files.local("assets/GameData/save.json");
		file.writeString(dataString, false);
	}

	// Load game data from disk
	public GameData loadGameData(){
        try{
            FileHandle file = com.badlogic.gdx.Gdx.files.local("assets/GameData/save.json");
            String dataString = file.readString();
            return this.json.fromJson(GameData.class, dataString);
        }catch(Exception e){
            return new GameData();
        }
	}

    // Save settings to disk
    public void saveSettings(Settings data){
        String dataString = this.json.toJson(data);
        FileHandle file = com.badlogic.gdx.Gdx.files.local("assets/settings.json");
        file.writeString(dataString, false);
    }

    // Load settings from disk
    private Settings loadSettings(){
        try{
            FileHandle file = com.badlogic.gdx.Gdx.files.local("assets/settings.json");
            String dataString = file.readString();
            return this.json.fromJson(Settings.class, dataString);
        }catch(Exception e){
            return new Settings();
        }
    }

    // Get current settings
    public Settings getSettings(){
        return this.settings;
    }

    // Get menu music
    public Music getMenuMusic(){
        return this.menuMusic;
    }

    // Set menu music
    public void setMenuMusic(Music music) {
        this.menuMusic = music;
    }

    // Get background animation for menus
    public Animation getMenuBackground(){
        return this.menuBackground;
    }
}