package Server;

import java.io.*;
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
    ObjectInputStream input;
    ObjectOutputStream output;
    ObjectOutputStream output2;
    ServerSideGame game;
    GameHandler handler;

    /**
     * Constructs a handler thread for a given socket and mark
     * initializes the stream fields, displays the first two
     * welcoming messages.
     */

    public ServerSidePlayer(Socket spelare1, Socket spelare2) {
        this.spelare1 = spelare1;
        this.spelare2 = spelare2;
        handler = new GameHandler();
    }


 public void run() {

        try {


            output = new ObjectOutputStream(spelare1.getOutputStream());
            // input = new ObjectInputStream(spelare1.getInputStream());
            handler.setMessage( "Welcome");
            handler.setPlayer("playerOne");
            output.writeObject(handler);
            output.flush();

            output2 = new ObjectOutputStream(spelare2.getOutputStream());
            // input = new ObjectInputStream(spelare1.getInputStream());
            handler.setMessage( "Welcome");
            handler.setPlayer("playerTwo");
            output2.writeObject(handler);
            output2.flush();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                spelare1.close();
            } catch (IOException e) {
            }
        }
    }
}

