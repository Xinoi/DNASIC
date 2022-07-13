package de.jjj.dnasic.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import de.jjj.dnasic.DNASIC;

public class DeathScreen extends ScreenAdapter {

    private Stage stage;
    private Label deathMessage;
    private TextButton returnToMenu;
    private TextButton restart;
    private Sound clickSound;
    private Music bgMusic;


    public DeathScreen() {
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        deathMessage = new Label("You are dead!", new Label.LabelStyle(DNASIC.INSTANCE.getFont(), Color.RED));
        deathMessage.setPosition(Gdx.graphics.getWidth() / 2 - deathMessage.getWidth() / 2, Gdx.graphics.getHeight() / 2 + 40);

        returnToMenu = new TextButton("return", DNASIC.INSTANCE.getButtonStyle());
        returnToMenu.setPosition((Gdx.graphics.getWidth() / 5), (deathMessage.getY() - 200));
        returnToMenu.setWidth(200);
        restart = new TextButton("restart", DNASIC.INSTANCE.getButtonStyle());
        restart.setWidth(200);
        restart.setPosition((Gdx.graphics.getWidth() / 5 * 3), (deathMessage.getY() - 200));

        returnToMenu.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                DNASIC.INSTANCE.setScreen(new MenuScreen());
            }
        });

        clickSound = Gdx.audio.newSound(Gdx.files.internal("Sounds/Click.wav"));
        returnToMenu.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                clickSound.play();
                return super.touchDown(event, x, y, pointer, button);
            }
        });

        restart.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                DNASIC.INSTANCE.setScreen(new LevelScreen());
            }
        });

        restart.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                clickSound.play();
                return super.touchDown(event, x, y, pointer, button);
            }
        });

        bgMusic = DNASIC.INSTANCE.getDeathMusic();

        stage.addActor(deathMessage);
        stage.addActor(returnToMenu);
        stage.addActor(restart);

    }

    @Override
    public void hide() {
        bgMusic.pause();
        this.dispose();
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
        bgMusic.dispose();
    }

}
