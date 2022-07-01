package de.jjj.dnasic;

public class Settings {
    private boolean music = true;
    private boolean mouse_control = true;

    public void setMusic(boolean music){
        this.music = music;
    }
    public boolean getMusic(){
        return this.music;
    }

    public void setMouseControl(boolean mouse_control){
        this.mouse_control = mouse_control;
    }
    public boolean getMouseControl(){
        return this.mouse_control;
    }
}
