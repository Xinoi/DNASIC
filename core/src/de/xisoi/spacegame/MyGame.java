package de.xisoi.spacegame;

import com.badlogic.gdx.Game;

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
