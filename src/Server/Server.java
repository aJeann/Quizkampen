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
        try (ServerSocket listener = new ServerSocket(23328)) {
            System.out.println("QuizkampenClient Server is Running");
            while (!listener.isClosed()) {
                GameResult gameResult = new GameResult();
                ServerSidePlayer playerX
                        = new ServerSidePlayer(listener.accept(),listener.accept(), gameResult);
                //ServerSidePlayer playerO
                 //       = new ServerSidePlayer(listener.accept(), "PlayerTwo", gameResult);
                playerX.start();
               // playerO.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

