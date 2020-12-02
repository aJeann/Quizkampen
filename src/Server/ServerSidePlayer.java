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
    Socket socket;
    ObjectInputStream input;
    ObjectOutputStream output;
    ServerSideGame game;

    /**
     * Constructs a handler thread for a given socket and mark
     * initializes the stream fields, displays the first two
     * welcoming messages.
     */
    public ServerSidePlayer(Socket socket, String userID, ServerSideGame game) {
        this.socket = socket;
        this.userID = userID;
        this.game = game;
        try {
            input = new ObjectInputStream(socket.getInputStream());
            output = new ObjectOutputStream(socket.getOutputStream());
            output.writeObject("Welcome " + userID);
            output.writeObject(game.getDatabase().getDBquestions());
            output.writeObject("MESSAGE Waiting for opponent to connect");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * The run method of this thread.
     */
    public void run() {
        try {
            // Tell the first player that it is her turn.
            if (userID.equals("playerOne")) {
                output.writeObject("YOUR_TURN");
            }

            if (userID.equals("playerTwo")) {
                output.writeObject("YOUR_TURN");
            }

            while (true) {
                sleep(200);
                String resp = (String) input.readObject();
                if (input == null) {
                    return;
                }
                if (resp.startsWith("ROUND_OVER")) {
                    String res = resp.substring(10);
                    game.addResult(res.trim());
                    output.writeObject("RESULT " + game.getResults());
                } else if (resp.startsWith("ENDROUND")) {
                    output.writeObject("RESULT " + game.getResults());
                }
                else if (resp.startsWith("WAITING")){
                    output.writeObject("MESSAGE Wait for your turn");
                }
            }
        } catch (IOException | ClassNotFoundException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException ignored) {
            }
        }
    }

    public void setOpponent(ServerSidePlayer opponent) {
        this.opponent = opponent;
    }

    public ServerSidePlayer getOpponent() {
        return opponent;
    }
}

