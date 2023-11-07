package ru.wefunni.game.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class SocketObservable implements Runnable {

    private Socket socket;
    private SocketMessageListener listener;

    public SocketObservable(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            while(true) {
                String message = input.readLine();
                if(message != null) {
                    listener.onMessage(message);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void observe(SocketMessageListener listener) {
        this.listener = listener;
        new Thread(this).start();
    }
}
