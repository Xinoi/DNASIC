package de.jjj.dnasic.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import de.jjj.dnasic.DNASIC;
import de.jjj.dnasic.screens.level.Level;

public class UpgradeScreen extends ScreenAdapter {

    private Stage stage;
    private SpriteBatch batch;
    private Animation<TextureRegion> bgAnimation;
    private Music bgMusic;
    private float elapsedTime;
    private TextureAtlas ShipP;
    private TextureAtlas buttonAtlas;
    private String currentShip;
    private String Triebwerk_1_T;
    private String Triebwerk_2_T;
    private String Triebwerk_3_T;
    private Button triebwerkeB;
    private Button hpB;
    private Button gunB;
    private Button Rahmen1;
    private Button Rahmen2;
    private Button Rahmen3;
    private TextButton.TextButtonStyle bStyle;
    private TextButton.TextButtonStyle bStyle1;
    private TextButton backButton;
    private int currentTriebwerk = 1;
    private boolean TribButtonPressed;
    private boolean hpButtonPressed;
    private boolean gunButtonPressed;
    private final Sound clickSound;

    public UpgradeScreen(){
        currentShip = Level.getCurrentShip();
        createButtonStyles();

        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        batch = new SpriteBatch();

        clickSound = Gdx.audio.newSound(Gdx.files.internal("Sounds/Click.wav"));

        //Background Animation
        bgAnimation = DNASIC.INSTANCE.getMenuBackground();

        // background Music
        bgMusic = DNASIC.INSTANCE.getLevelMusic();

        Actor backR = new Image(new Texture(Gdx.files.internal("TextureAtlas/Raw/Button/FensterUpgrade.png")));
        backR.setX(892);

        //set the Ship
        ShipP = new TextureAtlas("TextureAtlas/packed/Player_Ship/Player_Ship.atlas");
        Actor Ship = new Image(ShipP.findRegion(currentShip));
        Ship.setX(200);
        Ship.setY(650);
        Ship.rotateBy(-90);
        Ship.sizeBy(550);

        //Runde Buttons
        triebwerkeB = new TextButton("", bStyle);
        hpB = new TextButton("", bStyle);
        gunB = new TextButton("", bStyle);
        if(currentShip == "Ship_1"){
            triebwerkeB.setBounds(200,320,80,80);
            hpB.setBounds(350,320,80,80);
            gunB.setBounds(780,320,80,80);
        }
        else if(currentShip == "Ship_2"){
            triebwerkeB.setBounds(400,300,80,80);
            hpB.setBounds(550,300,80,80);
            gunB.setBounds(780,300,80,80);
        }
        else if(currentShip == "Ship_3"){
            triebwerkeB.setBounds(250,300,80,80);
            hpB.setBounds(400,300,80,80);
            gunB.setBounds(730,300,80,80);
        }
        else if(currentShip == "Ship_4"){
            triebwerkeB.setBounds(230,300,80,80);
            hpB.setBounds(400,300,80,80);
            gunB.setBounds(780,300,80,80);
        }
        triebwerkeB.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                clickSound.play();
                setStageTriebwerk();
                TribButtonPressed = true;
                hpButtonPressed = false;
                gunButtonPressed = false;
            }
        });
        hpB.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                clickSound.play();
                TribButtonPressed = false;
                hpButtonPressed = true;
                gunButtonPressed = false;

            }
        });
        gunB.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                clickSound.play();
                TribButtonPressed = false;
                hpButtonPressed = false;
                gunButtonPressed = true;

            }
        });

        backButton = new TextButton("  Back  ", DNASIC.INSTANCE.getButtonStyle());
        backButton.setPosition(50, 50);
        backButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                DNASIC.INSTANCE.setScreen(DNASIC.INSTANCE.getLastScreen());
            }
        });

        stage.addActor(backR);
        stage.addActor(Ship);
        stage.addActor(backButton);
        stage.addActor(triebwerkeB);
        stage.addActor(hpB);
        stage.addActor(gunB);
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
    private void createButtonStyles() {
        bStyle = new TextButton.TextButtonStyle();
        buttonAtlas = new TextureAtlas(Gdx.files.internal("TextureAtlas/packed/Button/buttons.atlas"));
        bStyle = new TextButton.TextButtonStyle();
        bStyle.font = DNASIC.INSTANCE.getFont();
        bStyle.fontColor = Color.GRAY;
        bStyle.up = new TextureRegionDrawable(buttonAtlas.findRegion("buttonRound_up"));
        bStyle.down = new TextureRegionDrawable(buttonAtlas.findRegion("buttonRound_down"));
        bStyle.pressedOffsetX = 1;
        bStyle.pressedOffsetY = -1;

        bStyle1 = new TextButton.TextButtonStyle();
        buttonAtlas = new TextureAtlas(Gdx.files.internal("TextureAtlas/packed/Button/UpgradeFenster.atlas"));
        bStyle1 = new TextButton.TextButtonStyle();
        bStyle1.font = DNASIC.INSTANCE.getFont();
        bStyle1.fontColor = Color.GRAY;
        bStyle1.up = new TextureRegionDrawable(buttonAtlas.findRegion("UpgradeFenster_up"));
        bStyle1.down = new TextureRegionDrawable(buttonAtlas.findRegion("UpgradeFenster_down"));
    }
    @Override
    public void dispose(){
        stage.dispose();
        batch.dispose();
    }
    @Override
    public void hide(){
        bgMusic.pause();
        DNASIC.INSTANCE.setLevelMusic(bgMusic);
        this.dispose();
    }
    public void setRahmen1(){
        Rahmen1 = new TextButton("", bStyle1);
        Rahmen1.setPosition(923,627);
    }
    public void setRahmen2(){
        Rahmen2 = new TextButton("", bStyle1);
        Rahmen2.setPosition(923,484);
    }
    public void setRahmen3(){
        Rahmen3 = new TextButton("", bStyle1);
        Rahmen3.setPosition(923,341);
    }
    public void setStageTriebwerk(){
        if(currentTriebwerk == 1){
            Triebwerk_1_T = "Images/Upgrades/TriebwerkUpgrade_1_high.png";
            Triebwerk_2_T = "Images/Upgrades/TriebwerkUpgrade_2.png";
            Triebwerk_3_T = "Images/Upgrades/TriebwerkUpgrade_3.png";
        }
        else if(currentTriebwerk == 2){
            Triebwerk_1_T = "Images/Upgrades/TriebwerkUpgrade_1_low.png";
            Triebwerk_2_T = "Images/Upgrades/TriebwerkUpgrade_2_high.png";
            Triebwerk_3_T = "Images/Upgrades/TriebwerkUpgrade_3.png";
        }
        else if(currentTriebwerk == 3){
            Triebwerk_1_T = "Images/Upgrades/TriebwerkUpgrade_1_low.png";
            Triebwerk_2_T = "Images/Upgrades/TriebwerkUpgrade_2_low.png";
            Triebwerk_3_T = "Images/Upgrades/TriebwerkUpgrade_3_high.png";
        }
        setRahmen1();
        setRahmen2();
        setRahmen3();
        Actor TriebwerkUpgrade_1 = new Image(new Texture(Gdx.files.internal(Triebwerk_1_T)));
        TriebwerkUpgrade_1.setPosition(943,648);
        Actor TriebwerkUpgrade_2 = new Image(new Texture(Gdx.files.internal(Triebwerk_2_T)));
        TriebwerkUpgrade_2.setPosition(943,505);
        Actor TriebwerkUpgrade_3 = new Image(new Texture(Gdx.files.internal(Triebwerk_3_T)));
        TriebwerkUpgrade_3.setPosition(943,362);

        Rahmen1.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                clickSound.play();
                currentTriebwerk = 1;
                setStageTriebwerk();
            }
        });
        Rahmen2.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                clickSound.play();
                currentTriebwerk = 2;
                setStageTriebwerk();
            }
        });
        Rahmen3.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
               clickSound.play();
                currentTriebwerk = 3;
                setStageTriebwerk();
            }
        });



        stage.addActor(TriebwerkUpgrade_1);
        stage.addActor(TriebwerkUpgrade_2);
        stage.addActor(TriebwerkUpgrade_3);
        stage.addActor(Rahmen1);
        stage.addActor(Rahmen2);
        stage.addActor(Rahmen3);

    }
}
