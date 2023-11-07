package ru.wefunni.game.CommunicationClientServer;

public class Player implements GameMoveListener {
    private String name;

    public Player(String name) {
        this.name = name;
    }

    @Override
    public String getPlayerName() {
        return name;
    }

    @Override
    public void onOpponentMove(String playerName, String move) {
        System.out.println(name + " move: " + move);
        //MARK: обработка хода противника
    }

    public void makeMove(GameService gameService, String move) {
        gameService.processPlayerMove(name, move);
    }

//    public class Main {
//        public static void main(String[] arguments) {
//            GameService gameService = new GameService();
//            Player player1 = new Player("Player1");
//            Player player2 = new Player("Player2");
//
//            gameService.addGameMoveListener(player1);
//            gameService.addGameMoveListener(player2);
//
//            player1.makeMove(gameService, "Move1");
//            player2.makeMove(gameService, "Move2");
//        }
//    }
}
