package de.jjj.dnasic;

import com.badlogic.gdx.Game;

public class DNASIC extends Game {

	public static DNASIC INSTANCE = new DNASIC();

	public DNASIC() {
		INSTANCE = this;
	}

	@Override
	public void create() {
		this.setScreen(new MenuScreen());
	}

}
