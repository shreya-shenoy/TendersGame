package com.game.jam2;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Tender implements Screen {
    private Texture img;
    private final Sprite sprite;

    public Tender(int x, int y) {
        img = new Texture("tender.png");
        sprite = new Sprite(img);
        sprite.setSize(100, 100);
        sprite.setPosition(x, y);

    }



    public void draw(SpriteBatch batch)
    {
        sprite.draw(batch);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

    }

    @Override
    public void resize(int width, int height) {
        sprite.setSize(width, height);

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    public void dispose() {
        img.dispose();
    }


    public float posX()
    {
        return sprite.getX();
    }
    public float posY()
    {
        return sprite.getY();
    }
    public float getW()
    {
        return sprite.getWidth();
    }
    public float getH()
    {
        return sprite.getHeight();
    }

    public void setPos(float x, float y)
    {
        sprite.setPosition(x,y);
        if(sprite.getX() < 0)
        {
            sprite.setPosition(0, y);
        }
        if(sprite.getX() > 700)
        {
            sprite.setPosition(700, y);
        }

    }
    public float hitBoxX()
    {
        return sprite.getX();
    }
    public float hitBoxY()
    {
        return sprite.getY();
    }

}
