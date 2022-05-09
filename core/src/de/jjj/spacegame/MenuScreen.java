package de.jjj.spacegame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.ScreenUtils;

public class MenuScreen extends ScreenAdapter {

    private SpriteBatch batch;
    private Music bgMusic;
    private BitmapFont titleFont;

    private Stage stage;

    private TextureAtlas atlas;
    private Skin skin;

    private TextButton startB;
    private TextButton settingsB;
    private TextButton exitB;
    private TextButton.TextButtonStyle bStyle;



    public MenuScreen() {
       batch = new SpriteBatch();

       titleFont = new BitmapFont(Gdx.files.internal("BitmapFonts/TitleFont.fnt"));

       //set a stage
       stage = new Stage();
       Gdx.input.setInputProcessor(stage);

       //create a button
       atlas = new TextureAtlas("TextureAtlas/packed/Button/buttons.atlas");
       skin = new Skin(atlas);
       bStyle = new TextButton.TextButtonStyle();
       bStyle.font = titleFont;
       bStyle.up = skin.getDrawable("button_up");
       bStyle.down = skin.getDrawable("button_down");
       bStyle.pressedOffsetX = 1;
       bStyle.pressedOffsetY = -1;

       startB = new TextButton("play", bStyle);

       //background Music
       bgMusic = Gdx.audio.newMusic(Gdx.files.internal("Music/Startmenu.wav"));
       bgMusic.setLooping(true);
       bgMusic.play();

       //add things to stage
       stage.addActor(startB);

    }

    @Override
    public void render(float render) {
        ScreenUtils.clear(0, 0, 0, 1);

        batch.begin();
        // dont know how to get the width of the text
        titleFont.draw(batch, "D N A S I C", Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() - 200);
        batch.end();

        stage.act(render);
        stage.draw();
    }

    @Override
    public void dispose() {
        batch.dispose();
        titleFont.dispose();
        atlas.dispose();
        skin.dispose();
    }

}
