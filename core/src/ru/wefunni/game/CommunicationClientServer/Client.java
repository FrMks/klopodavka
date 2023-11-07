package ru.wefunni.game.CommunicationClientServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {

    private Socket socket;
    private BufferedReader input;
    private PrintWriter output;

    private static final String SERVER_IP = "localhost";
    private static final int SERVER_PORT = 12345;

    public static void main(String[] arguments) {
        new Client().start();
    }

    public Client() {
        try {
            socket = new Socket(SERVER_IP, SERVER_PORT);
            input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            output = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start() {
        try {
            //MARK: взаимодействие с сервером (отправка и прием сообщений)
            output.println("Hello");
//            String response = input.readLine();
//            System.out.println(response + " - message from server");
            String serverText;
            while((serverText = input.readLine()) != null) {
                System.out.println(serverText + " - message from server");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void sendMessage(String message) {
        output.println(message);
    }

}
