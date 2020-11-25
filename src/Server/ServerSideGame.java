package Server;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Axel Jeansson, Christoffer Grännby, Salem Koldzo, Iryna Gnatenko,
 * Date: 2020-11-12
 * Time: 13:47
 * Project: Quizkampen
 * Copyright: MIT
 */
public class ServerSideGame {

    public ServerSidePlayer currentPlayer;
    private GameDB database = new GameDB();
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
/*
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
        }*/

        private boolean gameIsOver(){
            return currentRound == totalRounds;
        }

    //Kolla om alla rundor spelats?
    public boolean endRound() {
            if (resultList.size() == 2)
                return true;
            else
        return false;
    }

    private static List<String> resultList = new ArrayList<String>();

    public void addResult(String p) {
        resultList.add(p);
    }

    public List<String> getResults() {
        return resultList;
    }

}

