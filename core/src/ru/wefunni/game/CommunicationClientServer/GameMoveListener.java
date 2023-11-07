package ru.wefunni.game.CommunicationClientServer;

public interface GameMoveListener {
    String getPlayerName();

    void onOpponentMove(String playerName, String move);
}
