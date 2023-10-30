package ru.wefunni.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;



public class MyKlopodavkaGame extends ApplicationAdapter implements InputProcessor {

	private ShapeRenderer shapeRenderer;
	private GamePole gamePole;

	private SpriteBatch spriteBatch;


	
	@Override
	public void create () {
		Gdx.input.setInputProcessor(this);
		shapeRenderer = new ShapeRenderer();
		spriteBatch = new SpriteBatch();
		gamePole = new GamePole(shapeRenderer, new Vector2Int(10, 10), spriteBatch);

	}

	@Override
	public void render () {
		ScreenUtils.clear(1,1,1,1);

		gamePole.render();
	}
	
	@Override
	public void dispose () {
		shapeRenderer.dispose();
		spriteBatch.dispose();
	}

	@Override
	public boolean keyDown(int keycode) {
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		System.out.println(screenX + " " + screenY);
		gamePole.handleClick(screenX, Gdx.graphics.getHeight() - screenY, button);
		return true;
	}

	@Override
	public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(float amountX, float amountY) {
		return false;
	}


}
