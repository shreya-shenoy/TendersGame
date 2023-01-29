package com.game.jam2.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;
import com.game.jam2.Tender;
import com.game.jam2.Controller;
import com.game.jam2.Gator;
import com.game.jam2.Gator;
import com.game.jam2.Tender;
import com.game.jam2.Tree;

import java.util.ArrayList;

public class GameOver implements Screen {
    SpriteBatch batch;
    OrthographicCamera camera;
    Controller game;
    Texture playButton, pauseButton, resumeButton, main, back,plant, stack;
    Sprite button, menu, pause, resume, background, tenders, tree, stack1;
    Rectangle play, pause1, resume1, home;
    int score;
    int x1 = 10;
    BitmapFont title,s;
    Music song;
    float volume = 0.22f;
    ArrayList<Tree> trees;

    Tender tender;
    int highScore = 0;
    ArrayList<Gator> dollars;

    int x = 20;



    public GameOver(Controller g, int finalScore)
    {
        score = finalScore;
        game = g;
    }




    @Override
    public void show() {
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 800);
        //tender = new Tender(100,40);
        //tender.resize(450, 450);
        dollars = new ArrayList<Gator>();
        trees = new ArrayList<Tree>();

        back = new Texture("tendersit.png");
        tenders = new Sprite(back);
        tenders.setSize(400, 500);
        tenders.setPosition(-30, -50);




        playButton = new Texture("Button23.png");
        button = new Sprite(playButton);
        button.setSize(200, 128);
        button.setPosition(310, 350);
        play = new Rectangle(button.getX(), button.getY(), button.getWidth(), button.getHeight());

        back = new Texture("GatorLake.JPG");
        background = new Sprite(back);
        background.setSize(800,800);
        background.setPosition(0.5f,0.5f);


        play = new Rectangle(button.getX(), button.getY(), button.getWidth(), button.getHeight());
        pauseButton = new Texture("pause.png");
        pause = new Sprite(pauseButton);
        pause.setSize(70, 70);
        pause.setPosition(640, 730);
        pause1 = new Rectangle(pause.getX(), pause.getY(), pause.getWidth(), pause.getHeight());

        resumeButton = new Texture("playMusic.png");
        resume = new Sprite(resumeButton);
        resume.setSize(70, 70);
        resume.setPosition(710, 733);
        resume1 = new Rectangle(resume.getX(), resume.getY(), resume.getWidth(), resume.getHeight());

        song = Gdx.audio.newMusic(Gdx.files.internal("Montauk Point.mp3"));
        song.setVolume(volume);


        song.setLooping(true);
        song.play();


        main = new Texture("home.png");
        menu = new Sprite(main);
        menu.setSize(70, 70);
        menu.setPosition(20, 733);
        home = new Rectangle(menu.getX(), menu.getY(), menu.getWidth(), menu.getHeight());






    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0f, 0f, 0f, 1f);




        if (Gdx.input.justTouched())
        {
            Vector3 touchPos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touchPos);

            Rectangle touchBox = new Rectangle(touchPos.x, touchPos.y, 0.5f, 0.5f);
            if (touchBox.overlaps(play))
            {
                game.setScreen(new TenderGame(game));
                song.stop();


            }
            else if(touchBox.overlaps(pause1) && song.isPlaying())
                song.pause();
            else if(touchBox.overlaps(resume1) && !song.isPlaying())
                song.play();


            else if (touchBox.overlaps(home))
            {
                game.setScreen(new Menu(game));
                song.stop();


            }







        }

        batch.setProjectionMatrix(camera.combined);
        batch.begin();

        background.draw(batch);



        for(Gator e: dollars)
        {
            //e.update(delta);
            e.changeSize(50,50);
        }


        for(Gator e: dollars)
        {
            e.draw(batch);

        }

        //tender.draw(batch);
        //stack1.draw(batch);
        button.draw(batch);
        pause.draw(batch);
        resume.draw(batch);
        tenders.draw(batch);

        menu.draw(batch);







        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("Sans.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 100;
        title = generator.generateFont(parameter);


        //title.getData().setScale(4);

        title.draw(batch, "GAME OVER!", 210, 700);
        parameter.size = 70;
        title = generator.generateFont(parameter);

        title.draw(batch, "TOTAL: " + score, 235, 600);

        parameter.size = 33;
        title = generator.generateFont(parameter);


        title.draw(batch, "PLAY AGAIN", play.getX() + 28, play.getY() + 75);




        batch.end();

    }

    @Override
    public void resize(int width, int height) {

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

    @Override
    public void dispose () {
        playButton.dispose();

        main.dispose();
        back.dispose();
        batch.dispose();



    }

}
