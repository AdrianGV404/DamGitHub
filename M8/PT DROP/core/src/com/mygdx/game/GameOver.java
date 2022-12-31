package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Timer;

public class GameOver implements Screen {

    final Game game;
    Texture Background;

    Music gameOver;

    OrthographicCamera camera;

    public GameOver(final Game game){
        this.game= game;

        Background = new Texture(Gdx.files.internal("game_over.jpg"));
        gameOver = Gdx.audio.newMusic(Gdx.files.internal("gameOver.wav"));
        gameOver.play();

        camera = new OrthographicCamera();
        camera.setToOrtho(false,800,400);

        float delay = 4;

        Timer.schedule(new Timer.Task(){
            @Override
            public void run() {
                game.setScreen(new Menu(game));
                dispose();
            }
        }, delay);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();

        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();

        game.batch.draw(Background, 0, 0);

        game.batch.end();
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
