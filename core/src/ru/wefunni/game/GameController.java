package ru.wefunni.game;

import ru.wefunni.game.domain.GameServiceEventListener;
import ru.wefunni.game.domain.IGameService;

public class GameController implements GameServiceEventListener {
    private IGameService gameService;
    private GamePoleState gamePoleState;

    private CellState.PlayerType currentPlayer = CellState.PlayerType.PLAYER_1;

    public GameController(IGameService gameService, GamePoleState gamePoleState) {
        this.gameService = gameService;
        this.gamePoleState = gamePoleState;
        gameService.subscribe(this);
    }

    public void handleClick(Vector2Int cellPosition) {
        System.out.println(cellPosition.x + " " + cellPosition.y);
        CellState state = gamePoleState.getState(cellPosition).copy();

        if(gamePoleState.isEmpty()) {
            if(currentPlayer == CellState.PlayerType.PLAYER_1) {
                if(cellPosition.equals(new Vector2Int(0,9))) {
                    state.setCross(CellState.PlayerType.PLAYER_1);
                } else {
                    return;
                }
            } else {
                if(cellPosition.equals(new Vector2Int(9,0))) {
                    state.setCross(CellState.PlayerType.PLAYER_2);
                } else  {
                    return;
                }
            }
            gameService.setCellState(cellPosition, state);
            return;
        }


        if(state.isEmpty() || (!state.isPlayer(currentPlayer) && !state.isShaded())) {
            if(!gamePoleState.startWave(cellPosition, currentPlayer)) return;

            if(state.isEmpty()) {
                state.setCross(currentPlayer);
            } else {
                state.shade();
            }

            gameService.setCellState(cellPosition, state);
        }
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
