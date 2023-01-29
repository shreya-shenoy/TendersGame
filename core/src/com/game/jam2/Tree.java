package com.game.jam2;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Tree {
    Texture texture;
    Sprite sprite;

    public Tree(int x, int y, int x1, int y1) {
        texture = new Texture("Plant.png");
        sprite = new Sprite(texture);
        sprite.setSize(x1,y1);
        sprite.setPosition(x,y);

    }



    public void draw(SpriteBatch batch) {
        sprite.draw(batch);
    }


    public void changeSize(int x, int y)
    {
        sprite.setSize(x,y);
    }
    public void setPos(int x, int y)
    {
        sprite.setPosition(x,y);
    }
    public void dispose()
    {
        texture.dispose();

    }


}

