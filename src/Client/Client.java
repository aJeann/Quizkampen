package Client;

import Config.Player;

import java.io.IOException;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Christoffer Grännby
 * Date: 2020-11-15
 * Time: 12:31
 * Project: Quizkampen
 * Copyright: MIT
 */
public class Client implements Runnable{

    private Player playerOne;
    private Player playerTwo;

    private int countingRounds = 1;
    private int numberOfQuestions;
    private int totalGames;

    public Client(Socket playerOne, Socket playerTwo){
//          this.playerOne = new Player(playerOne);
//          this.playerTwo = new Player(playerTwo);

    }

    @Override
    public void run() {
        try {
            playGame();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private void playGame () throws  IOException{
        List<Player> playerList = new LinkedList<>();
        playerList.add(playerOne);
        playerList.add(playerTwo);

        // skapa lista med "databasen" innehållande frågorna

        // skapa for-loop som går igenom listorna med spelare och frågor
    }

    // metod för att spela en runda? Behövs detta? Hanteras det i GUI-klassen?
}
