package com.game.jam2;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.game.jam2.scenes.Menu;

public class Controller extends Game {
	@Override
	public void create() {
		this.setScreen(new Menu(this));
	}
}
