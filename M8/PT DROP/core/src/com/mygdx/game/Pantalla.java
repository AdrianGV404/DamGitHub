package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

import java.util.Iterator;

public class Pantalla implements Screen {

    final Game game;

    Texture Background;

    Texture dropImageStar;
    Texture dropImageSun;
    Texture dropImageBH;
    Texture dropImageAsteroid;
    Texture spaceshipImage;
    Sound dropSound;
    Sound error;
    Music rainMusic;
    OrthographicCamera camera;
    Rectangle spaceship;
    Array<Rectangle> raindrops;
    Array<Rectangle> sundrops;
    Array<Rectangle> BHdrops;
    Array<Rectangle> asteroiddrops;
    long lastDropTime;
    long lastDropTime2;
    long lastDropTime3;
    long lastDropTime4;
    int dropsGathered;

    int contadorFallos = 0;

    public Pantalla (final Game game){
        this.game = game;

        Background = new Texture(Gdx.files.internal("fondo.jpg"));

        dropImageStar = new Texture(Gdx.files.internal("star.png"));
        dropImageSun = new Texture(Gdx.files.internal("sun.png"));
        dropImageBH = new Texture(Gdx.files.internal("black_hole.png"));
        dropImageAsteroid = new Texture(Gdx.files.internal("asteroid.png"));



        spaceshipImage = new Texture(Gdx.files.internal("spaceship.png"));

        rainMusic = Gdx.audio.newMusic(Gdx.files.internal("BackgroundMusic.mp3"));
        dropSound = Gdx.audio.newSound(Gdx.files.internal("punto.wav"));
        error = Gdx.audio.newSound(Gdx.files.internal("error.mp3"));
        rainMusic.setLooping(true);
        rainMusic.play();

        Music start = Gdx.audio.newMusic(Gdx.files.internal("start.ogg"));
        start.play();

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);

        spaceship = new Rectangle();
        spaceship.x = 800 / 2 - 64 / 2;
        spaceship.y = 20;
        spaceship.width = 64;
        spaceship.height = 64;

        raindrops = new Array<Rectangle>();
        sundrops = new Array<Rectangle>();
        BHdrops = new Array<Rectangle>();
        asteroiddrops = new Array<Rectangle>();
        spawnRaindrop();
    }
    private void spawnAsteroid() {
        Rectangle asteroiddrop = new Rectangle();
        asteroiddrop.x = MathUtils.random(0,800 - 64);
        asteroiddrop.y = 480;
        asteroiddrop.width = 64;
        asteroiddrop.height = 64;
        asteroiddrops.add(asteroiddrop);
        lastDropTime4 = TimeUtils.millis();
    }

    private void spawnBH() {
        Rectangle BHdrop = new Rectangle();
        BHdrop.x = MathUtils.random(0,800 - 64);
        BHdrop.y = 480;
        BHdrop.width = 64;
        BHdrop.height = 64;
        BHdrops.add(BHdrop);
        lastDropTime3 = TimeUtils.millis();
    }

    private void spawnRaindrop() {
        Rectangle raindrop = new Rectangle();
        raindrop.x = MathUtils.random(0,800 - 64);
        raindrop.y = 480;
        raindrop.width = 64;
        raindrop.height = 64;
        raindrops.add(raindrop);
        lastDropTime = TimeUtils.millis();
    }

    private void spawnSun() {
        Rectangle sundrop = new Rectangle();
        sundrop.x = MathUtils.random(0,800 - 64);
        sundrop.y = 480;
        sundrop.width = 64;
        sundrop.height = 64;
        sundrops.add(sundrop);
        lastDropTime2 = TimeUtils.millis();
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

        game.font.draw(game.batch, "Puntuación:  " + dropsGathered+" Estellas",20,450);
        game.font.draw(game.batch, "Fallos:  " + contadorFallos,600,450);

        game.batch.draw(spaceshipImage, spaceship.x, spaceship.y, spaceship.width, spaceship.height);

        for (Rectangle raindrop : raindrops) {
            game.batch.draw(dropImageStar, raindrop.x, raindrop.y);
        }
        for (Rectangle sundrop : sundrops) {
            game.batch.draw(dropImageSun, sundrop.x, sundrop.y);
        }
        for (Rectangle BHdrop : BHdrops) {
            game.batch.draw(dropImageBH, BHdrop.x, BHdrop.y);
        }
        for (Rectangle asteroiddrop : asteroiddrops) {
            game.batch.draw(dropImageAsteroid, asteroiddrop.x, asteroiddrop.y);
        }

        game.batch.end();

        if(Gdx.input.isTouched()){
            Vector3 touchPos = new Vector3();
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touchPos);
            spaceship.x = (int) (touchPos.x - 64 / 2);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT))
            spaceship.x -= 200 * Gdx.graphics.getDeltaTime();
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT))
            spaceship.x += 200 * Gdx.graphics.getDeltaTime();

        if (spaceship.x < 0)
            spaceship.x = 0;
        if (spaceship.x > 800 - 64)
            spaceship.x = 800 -64;

        if(TimeUtils.millis() - lastDropTime > 1000){
            spawnRaindrop();
        }
        if(TimeUtils.millis() - lastDropTime2 > 10000){
            spawnSun();
        }
        if(TimeUtils.millis() - lastDropTime3 > 13000){
            spawnBH();
        }
        if(TimeUtils.millis() - lastDropTime4 > 4000){
            spawnAsteroid();
        }

        Iterator<Rectangle> iter = raindrops.iterator();
        while (iter.hasNext()){
            Rectangle raindrop = iter.next();
            raindrop.y -= 200 * Gdx.graphics.getDeltaTime();
            if (raindrop.y + 64 < 0){
                contadorFallos++;
                iter.remove();
            }
            if (raindrop.overlaps(spaceship)){
                dropsGathered++;
                dropSound.play();
                iter.remove();
            }
            if (contadorFallos==3){
                rainMusic.setLooping(false);
                rainMusic.stop();

                game.setScreen(new GameOver(game));
                dispose();
            }
        }

        Iterator<Rectangle> iter2 = sundrops.iterator();
        while (iter2.hasNext()){
            Rectangle sundrop = iter2.next();
            sundrop.y -= 200 * Gdx.graphics.getDeltaTime();
            if (sundrop.y + 64 < 0){
                iter2.remove();
            }
            if (sundrop.overlaps(spaceship)){
                dropsGathered+=2;
                dropSound.play();
                iter2.remove();
            }
        }

        Iterator<Rectangle> iter3 = BHdrops.iterator();
        while (iter3.hasNext()){
            Rectangle BHdrop = iter3.next();
            BHdrop.y -= 200 * Gdx.graphics.getDeltaTime();
            if (BHdrop.y + 64 < 0){
                iter3.remove();
            }
            if (BHdrop.overlaps(spaceship)){
                iter3.remove();
                rainMusic.setLooping(false);
                rainMusic.stop();

                game.setScreen(new GameOver(game));
                dispose();
            }
        }

        Iterator<Rectangle> iter4 = asteroiddrops.iterator();
        while (iter4.hasNext()){
            Rectangle asteroiddrop = iter4.next();
            asteroiddrop.y -= 200 * Gdx.graphics.getDeltaTime();
            if (asteroiddrop.y + 64 < 0){
                iter4.remove();
            }
            if (asteroiddrop.overlaps(spaceship)){
                error.play();
                dropsGathered-=1;
                iter4.remove();
            }
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
