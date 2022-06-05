package de.jjj.dnasic.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import de.jjj.dnasic.DNASIC;

public class  LevelScreen extends ScreenAdapter {

    private Stage stage;
    private Table table;

    private Image lvl1;
    private Image lvl2;
    private Image lvl3;

    private Label selectText;

    private Music bgMusic;

    private SpriteBatch batch;

    public LevelScreen() {

        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        batch = new SpriteBatch();

        table = new Table();
        table.setFillParent(true);

        lvl1 = new Image(new Texture(Gdx.files.internal("Images/LevelCover/lvl1.png")));
        lvl1.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // Stop music before leaving the menu
                bgMusic.stop();

                System.out.println("lvl1 was clicked!");
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

        table.add(selectText).padBottom(50).padLeft(50).colspan(2);
        table.row();
        table.add(lvl1).space(0);
        table.add(lvl2);
        table.add(lvl3);

        // background Music
        bgMusic = DNASIC.INSTANCE.getMenuMusic();



        stage.addActor(table);
        stage.addActor(DNASIC.INSTANCE.BackB);
    }

    @Override
    public void hide() {
        bgMusic.pause();
        DNASIC.INSTANCE.setMenuMusic(bgMusic);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(new Color(Color.BLACK));

        if(DNASIC.INSTANCE.getSettings().getMusic()){
            bgMusic.play();
        }else {
            bgMusic.stop();
        }

        stage.act(delta);
        stage.draw();
    }

    @Override
    public void dispose() {
        stage.dispose();
        batch.dispose();
        bgMusic.dispose();
    }

}
