package de.jjj.dnasic;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
// prepared import
//import com.badlogic.gdx.graphics.Texture;
//import com.badlogic.

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setTitle("D N A S I C");
		config.setForegroundFPS(60);
		config.setWindowedMode(1200, 800);
		config.useVsync(true);
		config.setWindowIcon(Files.FileType.Internal, new String[]{"Images/Icons/Icon16.png", "Images/Icons/Icon32.png", "Images/Icons/Icon128.png"});
		new Lwjgl3Application(new DNASIC(), config);
	}
}
