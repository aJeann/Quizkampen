package Server;

import UserInterface.GUI;
import java.io.IOException;
import java.net.ServerSocket;


/**
 * Created by Christoffer Grännby
 * Date: 2020-11-15
 * Time: 12:21
 * Project: Quizkampen
 * Copyright: MIT
 */
public class Server {

    public static void main(String[] args) throws Exception {
        ServerSocket listener = new ServerSocket(1234);
        System.out.println("Quizkampen Server is running");
/*
        try {
            while(true){
                ServerSideGame game = new ServerSideGame();
                //ServerSidePlayer playerOne = new ServerSidePlayer(listener.accept(), .accept(), "NAME1", game);
                ServerSidePlayer playerTwo = new ServerSidePlayer(listener.accept(), "MANE2", game);
                playerOne.setOpponent(playerTwo);
                playerTwo.setOpponent(playerOne);
                game.currentPlayer = playerOne;
                playerOne.start();
                playerTwo.start();}
            } finally{
            listener.close();
        }


        /*

 */
    }

    private int serverPortNumber = 12345;

    public Server() throws Exception{

       /* try (ServerSocket connecting = new ServerSocket(serverPortNumber)){
            while (true){
                GUI game = new GUI();// change name and call from GUI
                if(ui.getRoundParam == 1){
                ServerSidePlayer playerOne = new ServerSidePlayer(connecting.accept(), "NAME1", game);
                ServerSidePlayer playerTwo = new ServerSidePlayer(connecting.accept(), "MANE2", game);
                playerOne.setOpponent(playerTwo);
                playerTwo.setOpponent(playerOne);
                create currentPlayer
                game.currentPlayer = playerOne;
                playerOne.start();
                playerTwo.start();}else {
                    ServerSidePlayer playerTwo = new ServerSidePlayer(connecting.accept(), "MANE2", game);
                    ServerSidePlayer playerOne = new ServerSidePlayer(connecting.accept(), "NAME1", game);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
         */

    }


}
