package ru.wefunni.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import ru.wefunni.game.network.SocketGameService;


public class MyKlopodavkaGame extends ApplicationAdapter implements InputProcessor {

	private ShapeRenderer shapeRenderer;
	private GamePoleRenderer gamePoleRenderer;

	private SpriteBatch spriteBatch;

	private GameController gameController;






	@Override
	public void create () {
		Gdx.input.setInputProcessor(this);
		shapeRenderer = new ShapeRenderer();
		spriteBatch = new SpriteBatch();
		Vector2Int poleSize = new Vector2Int(10, 10);
		GamePoleState state = new GamePoleState(poleSize);
		gamePoleRenderer = new GamePoleRenderer(shapeRenderer, spriteBatch, state);

		gameController = new GameController(new SocketGameService(), state);
		gameController.startGame();
	}

	@Override
	public void render () {
		ScreenUtils.clear(1,1,1,1);

		gamePoleRenderer.render();
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
		//System.out.println(screenX + " " + screenY);
		//gamePole.handleClick(screenX, Gdx.graphics.getHeight() - screenY, button);

		gameController.handleClick(gamePoleRenderer.getCell(screenX, Gdx.graphics.getHeight() - screenY));

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
