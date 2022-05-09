package de.jjj.spacegame;

import com.badlogic.gdx.Game;

public class SpaceGame extends Game {

	public static SpaceGame INSTANCE = new SpaceGame();

	public SpaceGame() {
		INSTANCE = this;
	}

	@Override
	public void create() {
		this.setScreen(new MainScreen());
	}

}
