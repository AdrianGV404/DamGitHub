package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

public class Game extends com.badlogic.gdx.Game {

	public FreeTypeFontGenerator fontGenerator;
	public FreeTypeFontGenerator.FreeTypeFontParameter fontParameter;
	public BitmapFont font;

	SpriteBatch batch;
	Texture img;
	
	@Override
	public void create () {
		batch = new SpriteBatch();

		fontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("Sansation-Regular.ttf"));
		fontParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
		fontParameter.size = 30;
		fontParameter.borderWidth = 2;
		fontParameter.borderColor = Color.BLACK;
		fontParameter.color = Color.WHITE;
		font = fontGenerator.generateFont(fontParameter);

		this.setScreen(new Menu(this));
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
