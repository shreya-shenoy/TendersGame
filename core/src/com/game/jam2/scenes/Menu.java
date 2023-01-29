package com.game.jam2.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;
import com.game.jam2.Controller;

public class Menu implements Screen {
    SpriteBatch batch;
    OrthographicCamera camera;
    Controller game;
    Texture playButton, back, pauseButton, resumeButton;
    Sprite button, background, pause, resume;
    Rectangle play, pause1, resume1;
    BitmapFont title;
    Music song;
    float volume = 0.3f;



    public Menu(Controller g)
    {
        game = g;
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        camera.setToOrtho(false,1000,1000);

        batch = new SpriteBatch();

        playButton = new Texture("Button23.png");
        back = new Texture("LakeAlice.jpg");

        background = new Sprite(back);
        button = new Sprite(playButton);
        button.setSize(300, 155);
        button.setPosition(370, 200);

        play = new Rectangle(button.getX(), button.getY(), button.getWidth(), button.getHeight());
        pauseButton = new Texture("pause.png");
        pause = new Sprite(pauseButton);
        pause.setSize(70, 70);
        pause.setPosition(840, 930);
        pause1 = new Rectangle(pause.getX(), pause.getY(), pause.getWidth(), pause.getHeight());

        resumeButton = new Texture("playMusic.png");
        resume = new Sprite(resumeButton);
        resume.setSize(70, 65);
        resume.setPosition(910, 933);
        resume1 = new Rectangle(resume.getX(), resume.getY(), resume.getWidth(), resume.getHeight());


        song = Gdx.audio.newMusic(Gdx.files.internal("Montauk Point.mp3"));
        song.setVolume(volume);


        song.setLooping(true);
        song.play();


        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1000, 1000);
        background.setSize(camera.viewportWidth,camera.viewportHeight);
        background.setPosition(0.5f,0.5f);



    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0f, 0f, 0f, 1f);

        if (Gdx.input.justTouched())
        {
            Vector3 touchPos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touchPos);

            Rectangle touchBox = new Rectangle(touchPos.x, touchPos.y, 1, 1);
            if (touchBox.overlaps(play)) {

                game.setScreen(new TenderGame(game));
                song.stop();
            }
           else if(touchBox.overlaps(pause1) && song.isPlaying())
            song.pause();
            else if(touchBox.overlaps(resume1)&& !song.isPlaying())
            song.play();


        }

        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        background.draw(batch);
        button.draw(batch);
        pause.draw(batch);
        resume.draw(batch);
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("Sans.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 60;
        title = generator.generateFont(parameter);



        //title.getData().setScale(0.15f);

        title.setColor(Color.WHITE);
        title.draw(batch, "TENDERS' ESCAPE FROM LAKE ALICE", 180, 800);
        parameter.size = 70;
        title = generator.generateFont(parameter);
        title.draw(batch, "PLAY", button.getX() + 78, button.getY() + 100);
        title.getData().setScale(1);



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
    public void dispose() {

    }
}
