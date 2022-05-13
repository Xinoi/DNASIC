package de.jjj.dnasic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import java.awt.event.InputEvent;

public class LevelScreen extends ScreenAdapter {

    private Stage stage;
    private Table table;

    private Image lvl1;
    private Image lvl2;
    private Image lvl3;

    private Label selectText;

    BitmapFont font = new BitmapFont(Gdx.files.internal("BitmapFonts/MainFont.fnt"));

    private SpriteBatch batch;

    public LevelScreen() {

        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        batch = new SpriteBatch();

        table = new Table();
        table.setFillParent(true);

        lvl1 = new Image(new Texture(Gdx.files.internal("Images/LevelCover/lvl1.png")));
        lvl2 = new Image(new Texture(Gdx.files.internal("Images/LevelCover/lvl2.png")));
        lvl3 = new Image(new Texture(Gdx.files.internal("Images/LevelCover/lvl3.png")));

        selectText = new Label("select level with the arrow keys", new Label.LabelStyle(new BitmapFont(Gdx.files.internal("BitmapFonts/MainFont.fnt")), Color.WHITE));

        table.add(selectText).padBottom(50).padLeft(50).colspan(2);
        table.row();
        table.add(lvl1).space(0);
        table.add(lvl2);
        table.add(lvl3);


        stage.addActor(table);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(new Color(Color.BLACK));

        if(Gdx.input.getX() >= lvl1.getX() && Gdx.input.getX() <= lvl1.getX() + lvl1.getImageWidth() && Gdx.input.getY() >= lvl1.getY() && Gdx.input.getY() <= lvl1.getY() + lvl1.getImageHeight()) {
            if(Gdx.input.isTouched()) {
                System.out.println("lvl 1 is touiched");
            }
        }

        stage.act(delta);
        stage.draw();
    }

    @Override
    public void dispose() {
        stage.dispose();
    }

}
