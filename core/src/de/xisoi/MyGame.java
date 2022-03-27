package de.xisoi;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.ScreenAdapter;

public class MyGame extends Game {

	public static MyGame INSTANCE = new MyGame();

	public MyGame() {
		INSTANCE = this;
	}

	@Override
	public void create() {
		this.setScreen(new MainScreen());
	}

}
