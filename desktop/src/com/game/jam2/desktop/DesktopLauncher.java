package com.game.jam2.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.game.jam2.Controller;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Tenders' Escape from Lake Alice";
		config.width = 800;
		config.height = 800;
		new LwjglApplication(new Controller(), config);
	}
}
