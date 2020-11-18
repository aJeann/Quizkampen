package Server;

import Config.Player;
import UserInterface.GUI;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Created by Iryna Gnatenko
 * Date 11/18/2020
 * Time 1:58 PM
 * Project Quizkampen
 */
public class ServerSidePlayer extends Thread {

    private String name;
    private ServerSidePlayer opponent;
    private Socket socket;
    private GUI game;

    private ObjectInputStream input;
    private ObjectOutputStream output;

    public ServerSidePlayer (Socket player, String name, GUI game){
        this.socket = socket;
        this.name = name;
        this.game = game;

        try {
            // ändrade till ObjectReader
            input = new ObjectInputStream(socket.getInputStream());
            output = new ObjectOutputStream(socket.getOutputStream());

            output.writeObject("WELCOME " + name);
            output.writeObject("MESSAGE Waiting for opponent to connect");

        } catch (IOException e) {
            System.out.println("Player died: " + e);
        }
    }

    public void setOpponent(ServerSidePlayer opponent) {
        this.opponent = opponent;
    }

    public ServerSidePlayer getOpponent() {
        return opponent;
    }
}
