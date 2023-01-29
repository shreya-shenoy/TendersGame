package com.game.jam2.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;
import com.game.jam2.Tender;
import com.game.jam2.Controller;
import com.game.jam2.Gator;

import java.util.ArrayList;

public class TenderGame implements Screen {
    SpriteBatch batch;
    Sprite back1,back2, menu;
    Texture background1, main;
    Tender tender;
    OrthographicCamera camera;
    public static final int SPEED = 200;
    float distance1, distance2;
    Rectangle home;
    boolean followPlayer = false;
    float timer = 20;
    float frames = 0;
    int score = 0;
    Sound sound, over;
    Music song;
    float volume = 0.3f;
    int highScore = 0;
    BitmapFont points, timing;



    ArrayList<Gator> gators;
    float time = 0;


    Controller myGame;

    public TenderGame(Controller g)
    {
        myGame = g;


    }


    @Override
    public void show() {
        batch = new SpriteBatch();
        background1 = new Texture("LakeAlice.jpg");
        back1 = new Sprite(background1);
        //back2 = new Sprite(background1);
        sound = Gdx.audio.newSound(Gdx.files.internal("Laser.mp3"));
        over = Gdx.audio.newSound(Gdx.files.internal("Over.mp3"));
        main = new Texture("home.png");
        menu = new Sprite(main);
        menu.setSize(72, 72);
        menu.setPosition(80 + 1000 * Gdx.graphics.getDeltaTime(), 700);
        home = new Rectangle(menu.getX(), menu.getY(), menu.getWidth(), menu.getHeight());




        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800,800);
        tender = new Tender(200, 200);
        tender.resize(150,150);
        back1.setSize(1000, 1000);
        back1.setPosition(0, 0);
        //back2.setSize(480, 800);
        //back2.setPosition(480, -12);


        gators = new ArrayList<Gator>();
        song = Gdx.audio.newMusic(Gdx.files.internal("Song2.mp3"));
        song.setVolume(volume);


        song.setLooping(true);
        song.play();
    }

    @Override
    public void render(float delta) {
        camera.zoom = MathUtils.clamp(camera.zoom, 0.5f, 2);

        camera.position.x += 0.1f;

        float pixels= 30 * Gdx.graphics.getDeltaTime();
        float pix = 200 * Gdx.graphics.getDeltaTime();

        if (Gdx.input.isKeyJustPressed(Input.Keys.Y))
            followPlayer = !followPlayer;

        ScreenUtils.clear(0, 0, 0, 1);
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        back1.draw(batch);
        //back2.draw(batch);


        for(Gator e: gators)
        {
            e.draw(batch);
        }

        tender.draw(batch);
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("Sans.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();

        parameter.size = 16;
        points = generator.generateFont(parameter);
        points.setColor(0,0,0,1);
        points.getData().setScale(2);
        points.setColor(0,0,0,1);
        points.getData().scale(2);
        points.draw(batch, "SCORE: " + score,500,660);


        timing = generator.generateFont(parameter);
        timing.getData().setScale(3);
        timing.setColor(0,0,0,1);

        timing.draw(batch, "TIME: " + timer,500,600);
        menu.draw(batch);
        //back1.setPosition(back1.getX() - SPEED * delta, back1.getY());
        // back2.setPosition(back2.getX() - SPEED * delta, back2.getY());

       /* if (back1.getX() <= -480) {
            back1.setPosition(back2.getX()+480, back1.getY());
        }

        if (back2.getX() <= -480) {
            back2.setPosition(back1.getX()+480, back2.getY());
        }*/
        batch.end();

        frames++;
        if(frames % 60 == 0)
        {
            timer--;
            score += 100;
        }

        if(timer == 0)
        {
            myGame.setScreen(new GameOver(myGame,score));
            song.stop();
            over.play();
        }
        if (Gdx.input.justTouched()) {
            Vector3 touchPos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touchPos);

            Rectangle touchBox = new Rectangle(touchPos.x, touchPos.y, 0.5f, 0.5f);
            if (touchBox.overlaps(home)) {
                myGame.setScreen(new Menu(myGame));
                song.stop();

            }
        }


        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            if(tender.posY() + pix < 450)
                tender.setPos(tender.posX(), tender.posY() + pix);

            distance1 = 0;
            distance2 = 600 * Gdx.graphics.getDeltaTime();

        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            if(tender.posY() + pix > 10)
                tender.setPos(tender.posX(), tender.posY() - pix);

            distance1 = 0;
            distance2 = 600 * Gdx.graphics.getDeltaTime();

        }
        tender.setPos(tender.posX() + pixels, tender.posY());

        for (int i = 0; i  < gators.size(); i++) {
            gators.get(i).update(delta);
            gators.get(i).isHit(tender);

            if (gators.get(i).isDead()) {
                gators.remove(i);
                sound.play();
                score -= 50;

            }
        }
        time += delta;

        if (time > .5) {
            time-=.5f;
            gators.add(new Gator((int)(Math.random() * 1000 + tender.posX()), (int)(Math.random() * 490), (int) (Math.random() * 90 + 50) ));
        }



    }

    @Override
    public void resize(int width, int height) {
        camera.viewportWidth = width;
        camera.viewportHeight = height;

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

    public void dispose () {
        batch.dispose();
        background1.dispose();
        tender.dispose();
        main.dispose();
    }







}