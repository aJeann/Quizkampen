package Server;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import Server.GameHandler.*;

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

    public ServerSidePlayer(Socket spelare1, String player, GameHandler handler, GameResult gameResult) {
        this.spelare1 = spelare1;
        handler1 = handler; //new GameHandler();
        handler1.setMessage("Welcome");
        handler1.player = player;
        this.gameResult= gameResult;

        try {
            output1 = new ObjectOutputStream(spelare1.getOutputStream());
            output1.writeObject(handler1);
            input1 = new ObjectInputStream(spelare1.getInputStream());

            // output1.writeObject("MESSAGE Waiting for opponent to connect");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

 public void run() {

        try {
            while (true)
            {
            handler1 = (GameHandler) input1.readObject();
              //  if (input1 == null) {
                //    return;
                //}

             //   if (handler1.getMessage().startsWith("ROUND_OVER")) {
                    gameResult.setScoreList(handler1.getScore());
                    System.out.println(gameResult.getScoreList().toString());
                    //handler1.setMessage("RESULT");
                    output1.writeObject(gameResult);


            //} else if (handler1.getMessage().equals("ENDROUND")) {
              //      handler1.setMessage("RESULT");
                //    output1.writeObject(handler1);

         //   }



            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                spelare1.close();
            } catch (IOException e) {
            }
        }
 }

}

