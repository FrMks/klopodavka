package ru.wefunni.game;

import org.graalvm.compiler.loop.InductionVariable;

import java.util.*;

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

    public void setState(Vector2Int position, CellState state) {
        pole[position.x][position.y] = state;
    }

    public boolean isValidPosition(Vector2Int position) {
        return position.x >= 0 && position.x < size.x && position.y >= 0 && position.y < size.y;
    }

    public boolean startWave(Vector2Int startPosition, CellState.PlayerType currentPlayer) {
        if(!isValidPosition(startPosition)) {
            return false;
        }

        Queue<Vector2Int> queue = new ArrayDeque<>();

        queue.offer(startPosition);

        int[][] directions = {
                {-1, 0}, {1, 0}, {0, -1}, {0, 1},
                {-1, -1}, {-1, 1}, {1, -1}, {1, 1}
        };

        HashSet<Vector2Int> visitedPositions = new HashSet<>();

        while (!queue.isEmpty()) {
            int queueSize = queue.size();

            for (int i = 0; i < queueSize; i++) {
                Vector2Int currentPosition = queue.poll();

                for (int[] direction : directions) {
                    int nextX = currentPosition.x + direction[0];
                    int nextY = currentPosition.y + direction[1];
                    Vector2Int nextPosition = new Vector2Int(nextX, nextY);
                    //TODO проверить были ли мы уже в nextPosition
                    if(visitedPositions.contains(nextPosition)) {
                        continue;
                     } else {
                        visitedPositions.add(nextPosition);
                    }

                    if(!isValidPosition(nextPosition)) {
                        continue;
                    }
                    CellState toCell = getState(nextPosition);
                    if(toCell.isEmpty()) {
                        continue;
                    }
                    if(toCell.isPlayer(currentPlayer)) {
                        if(toCell.isShaded()) {
                            continue;
                        } else {
                            return true;
                        }

                    } else {
                        if(toCell.isShaded()) {

                            queue.offer(nextPosition);
                        } else {
                            continue;
                        }
                    }
                }
            }
        }
        return false;
    }

    public boolean isEmpty() {
        for (int x = 0; x < size.x; x++) {
            for (int y = 0; y < size.y; y++) {
                if(!pole[x][y].isEmpty()) return false;
            }
        }
        return true;
    }
}
