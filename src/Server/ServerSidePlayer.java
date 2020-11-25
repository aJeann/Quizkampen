package Server;

import java.io.*;
import java.net.Socket;

/**
 * Created by Axel Jeansson, Christoffer Grännby,
 * Date: 2020-11-20
 * Time: 12:08
 * Project: SigrunsTicTacToe
 * Copyright: MIT
 */
public class ServerSidePlayer extends Thread{
    String userID;
    ServerSidePlayer opponent;
    Socket socket;

    ServerSideGame game;
    int totalPoints = 0;
    BufferedReader input;
    PrintWriter output;
    ObjectOutputStream outputObject;

    public ServerSidePlayer(Socket socket, String userID, ServerSideGame game) {
        this.socket = socket;
        this.userID = userID;
        this.game = game;
        try {
            outputObject = new ObjectOutputStream(socket.getOutputStream());
            input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            //output = new PrintWriter(socket.getOutputStream(), true);
            outputObject.writeObject("Welcome " + userID);
            outputObject.writeObject("Waiting for opponent to connect");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setOpponent(ServerSidePlayer opponent) {
        this.opponent = opponent;
    }

    public ServerSidePlayer getOpponent() {
        return this.opponent;
    }

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

