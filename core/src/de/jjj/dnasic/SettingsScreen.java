package de.jjj.dnasic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class SettingsScreen extends ScreenAdapter{
	
	private Stage stage;
	private Table table;
	
	Label musicL;
	TextButton musicB;
	TextButton.TextButtonStyle bStyle;

	TextureAtlas atlas;
	Skin skin;
	
    BitmapFont font = new BitmapFont(Gdx.files.internal("BitmapFonts/MainFont.fnt"));
	
	public SettingsScreen() {
		
		stage = new Stage(new ScreenViewport());
		Gdx.input.setInputProcessor(stage);
		
		musicL = new Label("Music:", new Label.LabelStyle(font, Color.WHITE));

		createButtonStyles();
		musicB = new TextButton("On", bStyle);

		table = new Table();
		table.setFillParent(true);
		table.add(musicL).spaceRight(20);
		table.add(musicB).pad(10);

		
		
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

}
