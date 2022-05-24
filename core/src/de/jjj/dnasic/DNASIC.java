package de.jjj.dnasic;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;

public class DNASIC extends Game {
	private Json json;
    private Settings settings;
    private GameData data;

	public static DNASIC INSTANCE = new DNASIC();

	public DNASIC() {
		INSTANCE = this;

		this.json = new Json();
	}

	@Override
	public void create() {
        this.settings = this.loadSettings();
        this.data = this.loadGameData();

		this.setScreen(new MenuScreen());
	}

    @Override
    public void dispose() {
        super.dispose();
    }

    @Override
    public void pause() {
        this.saveSettings(this.settings);

        super.pause();
    }

    // Save game data to disk
	public void saveGameData(GameData data){
		String dataString = this.json.toJson(data);
		FileHandle file = com.badlogic.gdx.Gdx.files.local("assets/GameData/save.json");
		file.writeString(dataString, false);
	}

	// Load game data from disk
	public GameData loadGameData(){
        try{
            FileHandle file = com.badlogic.gdx.Gdx.files.local("assets/GameData/save.json");
            String dataString = file.readString();
            return this.json.fromJson(GameData.class, dataString);
        }catch(Exception e){
            return new GameData();
        }
	}

    // Save settings to disk
    public void saveSettings(Settings data){
        String dataString = this.json.toJson(data);
        FileHandle file = com.badlogic.gdx.Gdx.files.local("assets/settings.json");
        file.writeString(dataString, false);
    }

    // Load settings from disk
    private Settings loadSettings(){
        try{
            FileHandle file = com.badlogic.gdx.Gdx.files.local("assets/settings.json");
            String dataString = file.readString();
            return this.json.fromJson(Settings.class, dataString);
        }catch(Exception e){
            return new Settings();
        }
    }
}