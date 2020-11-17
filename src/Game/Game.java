package Game;

import Config.Player;
import java.net.Socket;

/**
 * Created by Christoffer Grännby
 * Date: 2020-11-15
 * Time: 12:31
 * Project: Quizkampen
 * Copyright: MIT
 */
public class Game implements Runnable{

    private Player playerOne;
    private Player playerTwo;

    private int countingRounds = 1;
    private int numberOfQuestions;
    private int totalGames;

    public Game (Socket playerOne, Socket playerTwo){
          this.playerOne = new Player(playerOne);
          this.playerTwo = new Player(playerTwo);

    }

    @Override
    public void run() {
// test
    }
}
