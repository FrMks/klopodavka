package ru.wefunni.game.CommunicationClientServer;

import java.util.ArrayList;
import java.util.List;

public class GameService {
    private List<GameMoveListener> listeners = new ArrayList<>();
    public void addGameMoveListener(GameMoveListener listener) {
        listeners.add(listener);
    }

    public void removeGameMoveListener(GameMoveListener listener) {
        listeners.remove(listener);
    }

    public void processPlayerMove(String playerName, String move) {
        //MARK: обработка хода игрока

        for(GameMoveListener listener : listeners) {
            if(!listener.getPlayerName().equals(playerName)) {
                listener.onOpponentMove(playerName, move);
            }
        }
    }

}

