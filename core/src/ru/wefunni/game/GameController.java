package ru.wefunni.game;

import ru.wefunni.game.CommunicationClientServer.GameService;
import ru.wefunni.game.network.IGameService;

public class GameController {
    private IGameService gameService;
    private GamePoleState gamePoleState;

    public GameController(IGameService gameService, GamePoleState gamePoleState) {
        this.gameService = gameService;
        this.gamePoleState = gamePoleState;
    }

    public void handleClick(Vector2Int cellPosition) {
        System.out.println(cellPosition.x + " " + cellPosition.y);

        CellState state = gamePoleState.getState(cellPosition);
        if(state.isEmpty()) {
        state.setCross(CellState.PlayerType.PLAYER_1);
        } else if(!state.isShaded()) {
            state.shade();
        } else {
            state.makeEmpty();
        }
    }

    public void startGame() {

    }

    public void stopGame() {

    }
}
