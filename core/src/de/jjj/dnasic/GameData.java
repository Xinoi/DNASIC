package de.jjj.dnasic;

import com.badlogic.gdx.maps.Map;

import java.util.HashMap;

public class GameData {
    private int highscore;
    private HashMap<String, Object> shipStats = new HashMap<String, Object>(){{
        put("speed", 1f);
    }};

    public int getHighscore() {
        return this.highscore;
    }

    public void setHighscore(int highscore){
        this.highscore = highscore;
    }

    public float getShipSpeed(){
        return (float) this.shipStats.get("speed");
    }

    public void setShipSpeed(float speed){
        this.shipStats.put("speed", speed);
    }
}
