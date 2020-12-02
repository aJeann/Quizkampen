package Server;

import java.net.ServerSocket;
import java.net.SocketException;

/**
 * Created by Axel Jeansson, Christoffer Grännby, Salem Koldzo, Iryna Gnatenko, Ashkan Amiri
 * Date: 2020-11-12
 * Time: 13:47
 * Project: Quizkampen
 * Copyright: MIT
 */
public class Server {
    /**
     * Runs the application. Pairs up clients that connect.
     */
    public static GameDB database = new GameDB();
    public static void main(String[] args) throws Exception {
        try (ServerSocket listener = new ServerSocket(23325)) {
            System.out.println("QuizkampenClient Server is Running");
            while (!listener.isClosed()) {
                ServerSideGame game = new ServerSideGame();
                ServerSidePlayer playerX
                        = new ServerSidePlayer(listener.accept(), "playerOne", game);
                System.out.println("Player 1 connected");
                ServerSidePlayer playerO
                        = new ServerSidePlayer(listener.accept(), "playerTwo", game);
                System.out.println("Player 2 connected");
                playerX.setOpponent(playerO);
                playerO.setOpponent(playerX);
                game.currentPlayer = playerX;
                playerX.start();
                playerO.start();
            }
        }catch (SocketException e){
            System.out.println("Player left the game");
        }
    }
}