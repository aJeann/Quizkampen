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
    List<ResultHandler> scoreList;

    /**
     * Constructs a handler thread for a given socket and mark
     * initializes the stream fields, displays the first two
     * welcoming messages.
     */

    public ServerSidePlayer(Socket spelare1, Socket spelare2) {
        this.spelare1 = spelare1;
        this.spelare2 = spelare2;
        handler1 = new GameHandler();
        handler2 = new GameHandler();
        handler1.setMessage("Welcome");
        handler1.player = "PlayerOne";
        try {
            output1 = new ObjectOutputStream(spelare1.getOutputStream());

        output1.writeObject(handler1);

        output2 = new ObjectOutputStream(spelare2.getOutputStream());
        handler2.setMessage( "Welcome");
        handler2.player = "PlayerTwo";
        output2.writeObject(handler2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ServerSidePlayer(Socket spelare1, String player) {
        this.spelare1 = spelare1;
        scoreList = new ArrayList<>();

        handler1 = new GameHandler();

        handler1.setMessage("Welcome");
        handler1.player = player;
        try {
            output1 = new ObjectOutputStream(spelare1.getOutputStream());
            output1.writeObject(handler1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

 public void run() {

        try {
            input1 = new ObjectInputStream(spelare1.getInputStream());
          //  input2 = new ObjectInputStream(spelare2.getInputStream());

            while (true)
            {

            handler1 = (GameHandler) input1.readObject();
          // if(handler1.getScore().player == "PlayerOne")
                scoreList.add(handler1.getScore());
                handler1.setScoreList(scoreList);
            System.out.println(handler1.getScoreList().toString());
          //  handler2 = (GameHandler) input2.readObject();
           // System.out.println(handler2.getScoreList().toString());
            output1.writeObject(handler1);
           // output2.writeObject(handler2);

            }
        } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
        }
 }

}

