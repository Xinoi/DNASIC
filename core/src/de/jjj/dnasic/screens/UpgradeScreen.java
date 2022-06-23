package de.jjj.dnasic.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Music;
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
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
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
    private  TextureAtlas buttonAtlas;
    private String currentShip;
    private Button triebwerkeB;
    private TextButton.TextButtonStyle bStyle;
    //private int[][] Buttonmatrix;



    public UpgradeScreen(){
        currentShip = Level.returncurrentShip();
        createButtonStyles();
        //Buttonmatrix = new int[][];
        //Buttonmatrix[3][0] = 250;

        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        batch = new SpriteBatch();

        //Background Animation
        bgAnimation = DNASIC.INSTANCE.getMenuBackground();
        // background Music
        bgMusic = DNASIC.INSTANCE.getMenuMusic();

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
        triebwerkeB.setBounds(250,300,80,80);


        stage.addActor(backR);
        stage.addActor(Ship);
        stage.addActor(DNASIC.INSTANCE.BackB);
        stage.addActor(triebwerkeB);
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
    }
    @Override
    public void dispose(){
        stage.dispose();
        bgMusic.dispose();
        batch.dispose();
    }

}
