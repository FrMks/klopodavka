package ru.wefunni.game.domain;

import ru.wefunni.game.CellState;
import ru.wefunni.game.Vector2Int;

public interface GameServiceEventListener {

    void onLobbyConnected(CellState.PlayerType playerType);
    void onGameStarted();

    void onCellChanged(Vector2Int cellPosition, CellState cellState);

}
