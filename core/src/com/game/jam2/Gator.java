package com.game.jam2;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.ArrayList;

public class Gator {
    float x, y, speed;
    static Texture texture,texture1, texture2, texture3, texture4;
    Sprite sprite, sprite1,sprite2,sprite3,sprite4;
    boolean isDead = false;
    Rectangle hitbox;
    int val;

    public Gator(float x, float y, int speed) {
        this.x = x;
        this.y = y;
        this.speed = speed;

        if (texture1 == null)
        {
            texture = new Texture("gator.png");
            texture1 = new Texture("gator.png");
            texture2 = new Texture("gator.png");
            texture3 = new Texture("gator.png");
            texture4 = new Texture("gator.png");
        }

        sprite = new Sprite(texture1);
        sprite.setPosition(x, y);
        sprite.setSize(35, 35);
        sprite1 = new Sprite(texture1);
        sprite1.setPosition(x, y);
        sprite1.setSize(35, 35);

        sprite2 = new Sprite(texture2);
        sprite2.setPosition(x, y);
        sprite2.setSize(35, 35);

        sprite3 = new Sprite(texture3);
        sprite3.setPosition(x, y);
        sprite3.setSize(35, 35);

        sprite4 = new Sprite(texture4);
        sprite4.setPosition(x, y);
        sprite4.setSize(35, 35);

        hitbox = new Rectangle(x, y, 35, 35);
        val = (int)(Math.random() * 4 + 1);
    }

    public void update(float delta) {
        x -= speed * delta;
        if (y < -50) {
            isDead = true;
        }

        if(val == 1)
        {
            sprite = sprite1;
        }
        else if(val == 2)
        {
            sprite = sprite2;;
        }
        else if(val == 3)
        {
            sprite = sprite3;
        }
        else if(val == 4)
        {
            sprite = sprite4;
        }
        else
            sprite = sprite1;
        sprite.setPosition(x, y);
        hitbox.x = x;
        hitbox.y = y;
    }

    public void draw(SpriteBatch batch) {
        sprite.draw(batch);
    }

    public boolean isDead() {
        return isDead;
    }

    public void isHit(Tender d) {
        Rectangle h = new Rectangle(d.posX(), d.posY(),d.getW(),d.getH());

        if (h.overlaps(hitbox)) {
            isDead = true;
            kill();
        }
    }
    public void kill()
    {
        isDead = true;
    }
    public void changeSize(int x, int y)
    {
        sprite.setSize(x,y);
    }
    public void dispose()
    {
        texture.dispose();
        texture1.dispose();
        texture2.dispose();
        texture3.dispose();
        texture4.dispose();
    }

}
