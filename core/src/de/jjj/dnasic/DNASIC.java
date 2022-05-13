package de.jjj.dnasic;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;

public class DNASIC extends Game {
	private Json json;

	public static DNASIC INSTANCE = new DNASIC();

	public DNASIC() {
		INSTANCE = this;

		this.json = new Json();
	}

	@Override
	public void create() {
		this.setScreen(new MenuScreen());
	}

	// Save game data to disk
	public void saveGameData(GameData data){
		String dataString = json.toJson(data);
		FileHandle file = com.badlogic.gdx.Gdx.files.local("assets/GameData/save.json");
		file.writeString(dataString, false);
	}

	// Load game data from disk
	public GameData loadGameData(){
		FileHandle file = com.badlogic.gdx.Gdx.files.local("assets/GameData/save.json");
		String dataString = file.readString();
		return json.fromJson(GameData.class, dataString);
	}
}