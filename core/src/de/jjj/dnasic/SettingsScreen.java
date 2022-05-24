package de.jjj.dnasic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class SettingsScreen extends ScreenAdapter{
	
	private Stage stage;
	private Table table;
	
	Label musicL;
	Button musicB;
	
    BitmapFont font = new BitmapFont(Gdx.files.internal("BitmapFonts/MainFont.fnt"));
	
	public SettingsScreen() {
		
		stage = new Stage(new ScreenViewport());
		Gdx.input.setInputProcessor(stage);
		
		musicL = new Label("Music:", new Label.LabelStyle(font, Color.WHITE));
		
		table = new Table();
		
		
		stage.addActor(table);
	}
	
	@Override
	public void render(float delta) {
		stage.act(delta);
		stage.draw();
	}
	
	@Override
	public void dispose() {
		
	}

}
