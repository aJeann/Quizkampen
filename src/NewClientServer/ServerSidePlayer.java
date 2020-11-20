package NewClientServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by Axel Jeansson
 * Date: 2020-11-20
 * Time: 12:08
 * Project: SigrunsTicTacToe
 * Copyright: MIT
 */
public class ServerSidePlayer extends Thread{
    String userID;
    ServerSidePlayer opponent;
    Socket socket;
    BufferedReader input;
    PrintWriter output;
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
            input = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            output = new PrintWriter(socket.getOutputStream(), true);
            output.println("WELCOME " + userID);
            output.println("MESSAGE Waiting for opponent to connect");
        } catch (IOException e) {
            System.out.println("Player died: " + e);
        }
    }

    /**
     * Accepts notification of who the opponent is.
     */
    public void setOpponent(ServerSidePlayer opponent) {
        this.opponent = opponent;
    }

    /**
     * Returns the opponent.
     */
    public ServerSidePlayer getOpponent() {
        return opponent;
    }

    /**
     * Handles the otherPlayerMoved message.
     */
    public void otherPlayerMoved(int location) {
        output.println("OPPONENT_MOVED " + location);

        if (game.hasWinner()){
            output.println("DEFEAT");
        }
        else{
            if (game.boardFilledUp()){
                output.println("TIE");
            }
            else{
                output.println("");
            }
        }
    }

    /**
     * The run method of this thread.
     */
    public void run() {
        try {
            // The thread is only started after everyone connects.
            output.println("MESSAGE All players connected");

            // Tell the first player that it is her turn.
            if (userID == "playerOne") {
                output.println("MESSAGE Your move");
            }

            // Repeatedly get commands from the client and process them.
            while (true) {
                String command = input.readLine();
                if (command.startsWith("MOVE")) {

                } else if (command.startsWith("QUIT")) {
                    return;
                }
            }
        } catch (IOException e) {
            System.out.println("Player died: " + e);
        } finally {
            try {socket.close();} catch (IOException e) {}
        }
    }
}

