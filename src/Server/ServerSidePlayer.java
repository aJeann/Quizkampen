package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by Axel Jeansson, Christoffer Grännby, Salem Koldzo,
 * Date: 2020-11-12
 * Time: 13:47
 * Project: Quizkampen
 * Copyright: MIT
 */
public class ServerSidePlayer extends Thread {
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
     * The run method of this thread.
     */
    //Skriv om så att den fortsätter tills båda spelarna spelat alla sina rundor/alternativt så att den körs varje gång en ny runda spelas
    public void run() {

        try {

            // Tell the first player that it is her turn.
            if (userID.equals("playerOne")) {
                output.println("YOUR_TURN");
            }

            if (userID.equals("playerTwo")) {
                output.println("YOUR_TURN");
                //output.println("MESSAGE Wait for your turn");
            }

            while (true) {
                String resp = input.readLine();
                if (input == null) {
                    return;
                }
                if (resp.startsWith("ROUND_OVER")) {
                    String res = resp.substring(10);
                    System.out.println(res);
                    game.addResult(res.trim());
                    output.println("RESULT " + game.getResults());
                } else if (resp.startsWith("ENDROUND")) {
                    output.println("RESULT " + game.getResults());
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
            }
        }
    }
}

