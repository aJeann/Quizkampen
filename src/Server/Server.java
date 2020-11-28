package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Axel Jeansson, Christoffer Grännby, Salem Koldzo, Iryna Gnatenko,
 * Date: 2020-11-12
 * Time: 13:47
 * Project: Quizkampen
 * Copyright: MIT
 */
public class Server {
    /**
     * Runs the application. Pairs up clients that connect.
     */
    public static void main(String[] args)  {
        try (ServerSocket listener = new ServerSocket(23326)) {
            System.out.println("QuizkampenClient Server is Running");
            while (!listener.isClosed()) {
                GameHandler gHandler = new GameHandler();
                ServerSidePlayer playerX
                        = new ServerSidePlayer(listener.accept(),"PlayerOne",gHandler);
                ServerSidePlayer playerO
                        = new ServerSidePlayer(listener.accept(), "PlayerTwo",gHandler);
                playerX.start();
                playerO.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

