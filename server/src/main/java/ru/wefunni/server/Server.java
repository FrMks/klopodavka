package ru.wefunni.server;



import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {
    private static int PORT = 12345;
    private List<ClientHandler> clients = new ArrayList<>();


    public static void main(String[] arguments) {
        new Server().start();
    }

    //MARK: создание игрового поля
    public Server() {

    }

    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server Start");
            while(true) {
                Socket clientSocket = serverSocket.accept(); //прослушивает подключение к этому сокету и принимает его
                System.out.println("Connected New Client");
                ClientHandler clientHandler = new ClientHandler(clientSocket, this);
                clients.add(clientHandler);
                new Thread(clientHandler).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public synchronized void broadcastMessage(String message) {
        for (ClientHandler clientHandler : clients) {
            clientHandler.sendMessage(message);
        }
    }

    public synchronized void removeClient(ClientHandler clientHandler) {
        clients.remove(clientHandler);
        System.out.println("Remove Client");
    }

}
