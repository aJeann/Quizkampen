package Server;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Axel Jeansson, Christoffer Grännby, Salem Koldzo,
 * Date: 2020-11-12
 * Time: 13:47
 * Project: Quizkampen
 * Copyright: MIT
 */
public class ServerSideGame {

    /**
     * A board has nine squares.  Each square is either unowned or
     * it is owned by a player.  So we use a simple array of player
     * references.  If null, the corresponding square is unowned,
     * otherwise the array cell stores a reference to the player that
     * owns it.
     */
    //Ersätt med vad?
    private ServerSidePlayer[] board = {
            null, null, null,
            null, null, null,
            null, null, null};

    /**
     * The current player.
     */
    ServerSidePlayer currentPlayer;

    /**
     * Returns whether the current state of the board is such that one
     * of the players is a winner.
     */
    //Skriv om så att vi har en vinnare om alla rundor spelats och någon spelare har fler poäng
    public boolean hasWinner() {
        return
                (board[0] != null && board[0] == board[1] && board[0] == board[2])
                        ||(board[3] != null && board[3] == board[4] && board[3] == board[5])
                        ||(board[6] != null && board[6] == board[7] && board[6] == board[8])
                        ||(board[0] != null && board[0] == board[3] && board[0] == board[6])
                        ||(board[1] != null && board[1] == board[4] && board[1] == board[7])
                        ||(board[2] != null && board[2] == board[5] && board[2] == board[8])
                        ||(board[0] != null && board[0] == board[4] && board[0] == board[8])
                        ||(board[2] != null && board[2] == board[4] && board[2] == board[6]);
    }

    /**
     * Returns whether there are no more empty squares.
     */
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

