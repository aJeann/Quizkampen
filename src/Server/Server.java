package Server;

import java.net.ServerSocket;

/**
 * Created by Axel Jeansson
 * Date: 2020-11-20
 * Time: 12:09
 * Project: SigrunsTicTacToe
 * Copyright: MIT
 */
public class Server {
    /**
     * Runs the application. Pairs up clients that connect.
     */
    public static void main(String[] args) throws Exception {
        try (ServerSocket listener = new ServerSocket(23325)) {
            System.out.println("QuizkampenClient Server is Running");
            while (true) {
                ServerSideGame game = new ServerSideGame();
                ServerSidePlayer playerX
                        = new ServerSidePlayer(listener.accept(), "playerOne", game);
                ServerSidePlayer playerO
                        = new ServerSidePlayer(listener.accept(), "playerTwo", game);
                playerX.setOpponent(playerO);
                playerO.setOpponent(playerX);
                game.currentPlayer = playerX;
                playerX.start();
                playerO.start();
            }
        }
    }
}

