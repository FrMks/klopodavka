package ru.wefunni.game;

public class GamePoleState {
    private CellState[][] pole;

    public GamePoleState(Vector2Int size) {
        pole = new CellState[size.x][size.y];
        for (int x = 0; x < size.x; x++) {
            for (int y = 0; y < size.y; y++) {
                pole[x][y] = new CellState();
            }
        }
    }

    public CellState getState(Vector2Int pos) {
        return pole[pos.x][pos.y];
    }
}
