package ru.wefunni.game;

import ru.wefunni.game.domain.GameServiceEventListener;
import ru.wefunni.game.domain.IGameService;

public class GameController implements GameServiceEventListener {
    private IGameService gameService;
    private GamePoleState gamePoleState;

    public GameController(IGameService gameService, GamePoleState gamePoleState) {
        this.gameService = gameService;
        this.gamePoleState = gamePoleState;
        gameService.subscribe(this);
    }

    public void handleClick(Vector2Int cellPosition) {
        System.out.println(cellPosition.x + " " + cellPosition.y);

        CellState state = gamePoleState.getState(cellPosition).copy();
        if(state.isEmpty()) {
        state.setCross(CellState.PlayerType.PLAYER_1);
        } else if(!state.isShaded()) {
            state.shade();
        } else {
            state.makeEmpty();
        }
        gameService.setCellState(cellPosition, state);
    }

    public void startGame() {
        gameService.startGame();
    }

    public void stopGame() {

    }

    @Override
    public void onLobbyConnected(CellState.PlayerType playerType) {

    }

    @Override
    public void onGameStarted() {
        System.out.println("Game Start!");
    }

    @Override
    public void onCellChanged(Vector2Int cellPosition, CellState cellState) {
        //TODO применить новое состояние клетки к gamePoleState
        gamePoleState.setState(cellPosition, cellState);
    }

}
