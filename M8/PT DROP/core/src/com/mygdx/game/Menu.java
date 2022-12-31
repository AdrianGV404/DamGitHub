package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;

public class Menu implements Screen {

    final Game game;
    Texture Background;

    OrthographicCamera camera;

    public Menu(final Game game){
        this.game= game;

        Background = new Texture(Gdx.files.internal("menu.jpg"));

        camera = new OrthographicCamera();
        camera.setToOrtho(false,800,400);
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

        game.font.draw(game.batch, "Pt2. Variant Drop 35%", 150, 250);
        game.font.draw(game.batch, "Pulsar per jugar", 150,200);

        game.batch.end();

        if(Gdx.input.isTouched()){
            game.setScreen(new Pantalla(game));
            dispose();
        }
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
