package ru.wefunni.game;

import java.util.Objects;

public class Vector2Int {
    public int x;
    public int y;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vector2Int that = (Vector2Int) o;
        return x == that.x && y == that.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    public Vector2Int(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
