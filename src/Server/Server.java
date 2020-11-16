package Server;

import Game.Game;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Christoffer Grännby
 * Date: 2020-11-15
 * Time: 12:21
 * Project: Quizkampen
 * Copyright: MIT
 */
public class Server {

    private int serverPortNumber = 12345;

    public Server(){
        try (ServerSocket connecting = new ServerSocket(serverPortNumber)){
            while (true){
                Socket playerOne = connecting.accept();
                Socket playerTwo = connecting.accept();
                Thread startingGame = new Thread(new Game(playerOne, playerTwo));
                startingGame.run();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
