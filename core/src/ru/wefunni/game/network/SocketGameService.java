package ru.wefunni.game.network;

import ru.wefunni.game.CellState;
import ru.wefunni.game.Vector2Int;
import ru.wefunni.game.domain.GameServiceEventListener;
import ru.wefunni.game.domain.IGameService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.EventListener;

public class SocketGameService implements IGameService, SocketMessageListener {

    private Socket socket;
    private BufferedReader input;
    private PrintWriter output;

    private static final String SERVER_IP = "localhost";
    private static final int SERVER_PORT = 12345;

    private SocketObservable observable;

    private GameServiceEventListener eventListener;

    public SocketGameService() {
        try {
            socket = new Socket(SERVER_IP, SERVER_PORT);
            input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            output = new PrintWriter(socket.getOutputStream(), true);
            observable = new SocketObservable(socket);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void startGame() {
        output.println("Start");
        observable.observe(this);
    }

    @Override
    public void stopGame() {
        output.println("Stop");
        try {
            socket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void subscribe(GameServiceEventListener eventListener) {
        this.eventListener = eventListener;
    }


    @Override
    public void setCellState(Vector2Int cellPosition, CellState cellState) {
        //TODO сделать строку из параметров этой функции и отправить эту строку на сервер
    }

    @Override
    public void onMessage(String message) {
        System.out.println(message);
        if(message.equals("Start")) {
            eventListener.onGameStarted();
        }
        //TODO нужно спарсить message, понять что за событие нам пришло и вызвать нужную функцию у eventListener
        //eventListener.onCellChanged();
    }
}
