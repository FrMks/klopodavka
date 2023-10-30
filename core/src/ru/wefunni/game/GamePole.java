package ru.wefunni.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class GamePole {
    private ShapeRenderer shapeRenderer;
    private Vector2Int size;

    public GamePole(ShapeRenderer shapeRenderer, Vector2Int size) {
        this.shapeRenderer = shapeRenderer;
        this.size = size;
    }

    public void render() {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(Color.BLACK);
        Vector2Int cellSize = getCellSize();
        for (int x = 0; x < size.x; x++) {
            for (int y = 0; y < size.y; y++) {
                shapeRenderer.rect(x * cellSize.x, y * cellSize.y, cellSize.x, cellSize.y);
            }
        }
        //shapeRenderer.rect(300, 300, Gdx.graphics.getWidth()-300*2, Gdx.graphics.getHeight()-300*2);
        shapeRenderer.end();
    }

    private Vector2Int getCellSize() {
        return new Vector2Int( Gdx.graphics.getWidth() / size.x, Gdx.graphics.getHeight() / size.y);
    }
}
