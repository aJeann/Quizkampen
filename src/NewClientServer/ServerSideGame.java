package NewClientServer;

import Server.GameDB;

import java.io.IOException;


/**
 * Created by Axel Jeansson
 * Date: 2020-11-20
 * Time: 12:08
 * Project: SigrunsTicTacToe
 * Copyright: MIT
 */

public class ServerSideGame extends Thread {

    private GameDB database = new GameDB();
    private ServerSidePlayer currentPlayer;
    int questionsPerRound;
    private int totalRounds;
    private int currentRound;

    ServerSideGame (int questionsPerRound, int totalRounds){
        this.questionsPerRound = questionsPerRound;
        this. totalRounds = totalRounds;
    }

    void setPlayer (ServerSidePlayer playerOne, ServerSidePlayer playerTwo) {
        playerOne.setOpponent(playerTwo);
        playerTwo.setOpponent(playerOne);
        currentPlayer = playerOne;
    }

    private ServerSidePlayer getPlayerOne() {
        if (currentPlayer.getName().equalsIgnoreCase("Player 1")) {
            return currentPlayer;
        } else {
            return currentPlayer.getOpponent();
        }
    }

    private ServerSidePlayer getPlayerTwo(){
        return getPlayerOne().getOpponent();
    }

    private void winner () throws IOException {
        if (gameIsOver()) {
            if (currentPlayer.totalPoints > currentPlayer.getOpponent().totalPoints) {
                currentPlayer.outputObject.writeObject("You win!");
                currentPlayer.getOpponent().outputObject.writeObject("You lose!");
            }
            else if (currentPlayer.totalPoints < currentPlayer.getOpponent().totalPoints){
                currentPlayer.outputObject.writeObject("You lose!");
                currentPlayer.getOpponent().outputObject.writeObject("You win!");
            }
            else {
                currentPlayer.outputObject.writeObject("You tied!");
                currentPlayer.getOpponent().outputObject.writeObject("You tied!");
            }
        }
    }
    private boolean gameIsOver(){
        return currentRound == totalRounds;
    }
}

// spelplan
// resultat när båda svarat
// skriver ut poäng
