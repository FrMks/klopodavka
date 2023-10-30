package ru.wefunni.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;



public class MyKlopodavkaGame extends ApplicationAdapter {

	private ShapeRenderer shapeRenderer;
	private GamePole gamePole;

	
	@Override
	public void create () {
		shapeRenderer = new ShapeRenderer();
		gamePole = new GamePole(shapeRenderer, new Vector2Int(10, 10));

	}

	@Override
	public void render () {
		ScreenUtils.clear(1,1,1,1);

		gamePole.render();
	}
	
	@Override
	public void dispose () {
		shapeRenderer.dispose();
	}
}
