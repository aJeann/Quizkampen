package Server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Created by Axel Jeansson, Christoffer Grännby, Salem Koldzo, Iryna Gnatenko,
 * Date: 2020-11-12
 * Time: 13:47
 * Project: Quizkampen
 * Copyright: MIT
 */
public class ServerSidePlayer extends Thread {
    String userID;
    ServerSidePlayer opponent;
    Socket spelare1;
    Socket spelare2;
    ObjectInputStream input2;
    ObjectInputStream input1;
    ObjectOutputStream output1;
    ObjectOutputStream output2;
    ServerSideGame game;
    GameHandler handler1;
    GameHandler handler2;
    GameResult gameResult;

    /**
     * Constructs a handler thread for a given socket and mark
     * initializes the stream fields, displays the first two
     * welcoming messages.
     */

    public ServerSidePlayer(Socket spelare1, Socket spelare2, GameResult gameResult) {
        this.spelare1 = spelare1;
        this.spelare1 = spelare2;
        handler1 = new GameHandler();
        handler1.setMessage("Welcome");
        handler1.player1 = "PlayerOne";
        handler1.player2 = "PlayerTwo";
        handler2 = new GameHandler();
        handler2.setMessage("Welcome");
        handler2.player2 = "PlayerOne";
        handler2.player1 = "PlayerTwo";
        this.gameResult = gameResult;

        try {
            output1 = new ObjectOutputStream(spelare1.getOutputStream());
            output1.writeObject(handler1);
            input1 = new ObjectInputStream(spelare1.getInputStream());

            output2 = new ObjectOutputStream(spelare2.getOutputStream());
            output2.writeObject(handler2);
            input2 = new ObjectInputStream(spelare2.getInputStream());

            // output1.writeObject("MESSAGE Waiting for opponent to connect");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {

        try {
            while (true) {
                handler1 = (GameHandler) input1.readObject();
                handler2 = (GameHandler) input2.readObject();

                System.out.println("size1--> " + handler1.getScoreList().toString());
                System.out.println("gameResult.getMessage(1) --> " + handler1.getMessage());
                System.out.println("size2--> " + handler2.getScoreList().toString());
                System.out.println("gameResult.getMessage(2) --> " + handler2.getMessage());

                if (handler1.getMessage().startsWith("ROUND_OVER")) {
                    gameResult.setScoreList(handler1.getScore());
                    System.out.println("game result--> " + handler1.getScoreList().toString());
                    System.out.println("size--> " + gameResult.getSizeByRound(1));
                    handler1.setScoreList(gameResult.getScoreList());
                    System.out.println("handler1 result--> " + handler1.getScoreList().toString());
                    System.out.println("ENDROUND");
                    handler1.setMessage("ENDROUND");
                    output1.writeObject(handler1);
                }
                if (handler2.getMessage().startsWith("ROUND_OVER")) {
                    gameResult.setScoreList(handler2.getScore());
                    System.out.println("game result2--> " + handler2.getScoreList().toString());
                    System.out.println("size2--> " + gameResult.getSizeByRound(1));
                    handler1.setScoreList(gameResult.getScoreList());
                    System.out.println("handler2 result--> " + handler2.getScoreList().toString());

                    System.out.println("2---ENDROUND");
                    handler2.setMessage("2---ENDROUND");

                    output2.writeObject(handler1);
                }
                if (handler1.getMessage().startsWith("ENDROUND")) {
                    handler1.setMessage("ENDROUND ");
                    output1.writeObject(handler1);
                }

                if (handler2.getMessage().startsWith("ENDROUND")) {
                    System.out.println("2 ENDROUND 2");
                    handler2.setMessage("ENDROUND 2");
                    output1.writeObject(handler2);
                }


            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                spelare1.close();
                spelare2.close();
            } catch (IOException e) {
            }
        }
    }

}

