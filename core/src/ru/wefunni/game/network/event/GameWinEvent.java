package ru.wefunni.game.network.event;

import com.google.gson.annotations.SerializedName;
import ru.wefunni.game.CellState;

public class GameWinEvent extends GameEvent {

    public GameWinEvent(CellState.PlayerType playerType) {
        super(EventType.GAME_WIN);
        this.playerType = playerType;
    }

    @SerializedName("player_type")
    private CellState.PlayerType playerType;
    public GameWinEvent() {
        super(EventType.GAME_WIN);
    }

    public CellState.PlayerType getPlayerType() {
        return playerType;
    }
}