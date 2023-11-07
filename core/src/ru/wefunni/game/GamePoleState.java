package ru.wefunni.game;

public class GamePoleState {
    private CellState[][] pole;
    private Vector2Int size;

    public GamePoleState(Vector2Int size) {
        this.size = size;
        pole = new CellState[size.x][size.y];
        for (int x = 0; x < size.x; x++) {
            for (int y = 0; y < size.y; y++) {
                pole[x][y] = new CellState();
            }
        }
    }

    public Vector2Int getSize() {
        return size;
    }

    public CellState getState(Vector2Int pos) {
        return pole[pos.x][pos.y];
    }
}
