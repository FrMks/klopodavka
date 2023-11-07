package ru.wefunni.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class GamePoleRenderer {
    private ShapeRenderer shapeRenderer;

    private GamePoleState gamePoleState;

    private SpriteBatch spriteBatch;

    private Texture emptyTexture;
    private Texture redBugTexture;
    private Texture greenBugTexture;
    private Texture greenShadeTexture;
    private Texture redShadeTexture;

    public GamePoleRenderer(ShapeRenderer shapeRenderer, SpriteBatch spriteBatch, GamePoleState gamePoleState) {
        this.shapeRenderer = shapeRenderer;
        this.gamePoleState = gamePoleState;
        this.spriteBatch = spriteBatch;
        emptyTexture = new Texture(Gdx.files.internal("dirt.png"));
        redBugTexture = new Texture(Gdx.files.internal("red_bug.png"));
        greenBugTexture = new Texture(Gdx.files.internal("green_bug.png"));
        greenShadeTexture = new Texture(Gdx.files.internal("green_shade.png"));
        redShadeTexture = new Texture(Gdx.files.internal("red_shade.png"));

    }

    public void render() {
        Vector2Int cellSize = getCellSize(); //пиксели


        Vector2Int poleSize = gamePoleState.getSize();

        spriteBatch.begin();
        for (int x = 0; x < poleSize.x; x++) {
            for (int y = 0; y < poleSize.y; y++) {
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

        for (int x = 0; x < poleSize.x; x++) {
            for (int y = 0; y < poleSize.y; y++) {
                shapeRenderer.rect(x * cellSize.x, y * cellSize.y, cellSize.x, cellSize.y);

            }
        }

        shapeRenderer.end();
    }


    public Vector2Int getCell(int xPx, int yPx) {
        Vector2Int cellSize = getCellSize();
        int cellX = xPx / cellSize.x;
        int cellY = yPx / cellSize.y;
        return new Vector2Int(cellX, cellY);
    }

    private Vector2Int getCellSize() {
        Vector2Int poleSize = gamePoleState.getSize();
        return new Vector2Int( Gdx.graphics.getWidth() / poleSize.x, Gdx.graphics.getHeight() / poleSize.y);
    }
}
