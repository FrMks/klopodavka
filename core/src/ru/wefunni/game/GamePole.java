package ru.wefunni.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class GamePole {
    private ShapeRenderer shapeRenderer;
    private Vector2Int size; // размер поля в клетках 10 10

    private GamePoleState gamePoleState;

    private SpriteBatch spriteBatch;

    private Texture emptyTexture;
    private Texture redBugTexture;
    private Texture greenBugTexture;
    private Texture greenShadeTexture;
    private Texture redShadeTexture;

    public GamePole(ShapeRenderer shapeRenderer, Vector2Int size, SpriteBatch spriteBatch) {
        this.shapeRenderer = shapeRenderer;
        this.size = size;
        this.gamePoleState = new GamePoleState(size);
        this.spriteBatch = spriteBatch;
        emptyTexture = new Texture(Gdx.files.internal("dirt.png"));
        redBugTexture = new Texture(Gdx.files.internal("red_bug.png"));
        greenBugTexture = new Texture(Gdx.files.internal("green_bug.png"));
        greenShadeTexture = new Texture(Gdx.files.internal("green_shade.png"));
        redShadeTexture = new Texture(Gdx.files.internal("red_shade.png"));

    }

    public void render() {
        Vector2Int cellSize = getCellSize(); //пиксели


        spriteBatch.begin();
        for (int x = 0; x < size.x; x++) {
            for (int y = 0; y < size.y; y++) {
                spriteBatch.draw(emptyTexture, x * cellSize.x, y * cellSize.y, cellSize.x, cellSize.y);
                CellState state = gamePoleState.getState(new Vector2Int(x, y));
                if(state.isPlayer_1()) {
                    spriteBatch.draw(redBugTexture, x * cellSize.x, y * cellSize.y, cellSize.x, cellSize.y);
                    if(state.isShaded()) {
                        spriteBatch.draw(greenShadeTexture, x * cellSize.x, y * cellSize.y, cellSize.x, cellSize.y);
                    }
                } else if(state.isPlayer_2()) {
                    spriteBatch.draw(greenBugTexture, x * cellSize.x, y * cellSize.y, cellSize.x, cellSize.y);
                    if(state.isShaded()) {
                        spriteBatch.draw(redShadeTexture, x * cellSize.x, y * cellSize.y, cellSize.x, cellSize.y);
                    }
                }
            }
        }
        spriteBatch.end();


        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(Color.WHITE);

        for (int x = 0; x < size.x; x++) {
            for (int y = 0; y < size.y; y++) {
                shapeRenderer.rect(x * cellSize.x, y * cellSize.y, cellSize.x, cellSize.y);

            }
        }

        shapeRenderer.end();
    }

    public void handleClick(int xPx, int yPx, int button) {
        Vector2Int cellSize = getCellSize();
        int cellX = xPx / cellSize.x;
        int cellY = yPx / cellSize.y;
        CellState state = gamePoleState.getState(new Vector2Int(cellX, cellY));
        if(state.isEmpty()) {
            if(button == 0) {
                state.setCross(CellState.PlayerType.PLAYER_1);
            } else {
                state.setCross(CellState.PlayerType.PLAYER_2);
            }

        } else if(!state.isShaded()) {
            state.shade();
        } else {
            state.makeEmpty();
        }
    }

    private Vector2Int getCellSize() {
        return new Vector2Int( Gdx.graphics.getWidth() / size.x, Gdx.graphics.getHeight() / size.y);
    }
}
