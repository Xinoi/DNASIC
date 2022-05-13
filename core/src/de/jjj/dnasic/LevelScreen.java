package de.jjj.dnasic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class LevelScreen extends ScreenAdapter {

    private Stage stage;
    private Table table;

    private Image lvl1;
    private Image lvl2;
    private Image lvl3;

    public LevelScreen() {

        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        table = new Table();
        table.setWidth(stage.getWidth());
        table.align(Align.center|Align.top);
        table.setPosition(0, Gdx.graphics.getHeight());

        lvl1 = new Image(new Texture(Gdx.files.internal("Images/LevelCover/lvl1.png")));
        lvl2 = new Image(new Texture(Gdx.files.internal("Images/LevelCover/lvl2.png")));
        lvl3 = new Image(new Texture(Gdx.files.internal("Images/LevelCover/lvl3.png")));

        addActorsToTable();
        stage.addActor(table);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(new Color(Color.BLACK));

        stage.act(delta);
        stage.draw();
    }

    @Override
    public void dispose() {
        stage.dispose();
    }

    private void addActorsToTable() {
        Actor[] actors = {lvl1, lvl2, lvl3};
        for(int i = 0; i < 3; i++) {
            table.add(actors[i]);
        }
    }
}
