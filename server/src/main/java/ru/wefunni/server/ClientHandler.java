package ru.wefunni.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class ClientHandler implements Runnable { //класс может быть выполнен в качестве отдельного потока
    private Socket socket;
    private Server server;
    private PrintWriter out;
    private BufferedReader in;

    public static ArrayList<ClientHandler> clientHandlers = new ArrayList<>();

    public  ClientHandler(Socket socket, Server server) {
        this.socket = socket;
        this.server = server;
        try { //создаем потоки для обмена данными
            out = new PrintWriter(socket.getOutputStream(), true); //выходно поток, позволяющий клиенту отправить данные серверу
            in = new BufferedReader(new InputStreamReader(socket.getInputStream())); //входной поток для чтения данных, принятых от сервера
            clientHandlers.add(this);
        } catch (IOException e) { //используем для обработки исключения
            e.printStackTrace(); //выводим информацию об ошибке
        }
    }

    @Override
    public void run() {
        try {
            String message;
            while ((message = in.readLine()) != null){
                System.out.println("Have message from client: " + message);

                //MARK: обработка сообщения от клиента


                server.broadcastMessage(message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally { //независимо от exception, выполняем finally
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            server.removeClient(this);
        }
    }

    public void sendMessage(String message) {
        out.println(message);
    }

}
