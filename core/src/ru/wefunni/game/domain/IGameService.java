package ru.wefunni.game.domain;

import ru.wefunni.game.CellState;
import ru.wefunni.game.Vector2Int;

public interface IGameService {

    void startGame();

    void stopGame();

    void subscribe(GameServiceEventListener eventListener);

    void setCellState(Vector2Int cellPosition, CellState cellState);

}
