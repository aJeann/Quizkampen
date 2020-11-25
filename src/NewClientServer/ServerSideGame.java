package NewClientServer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Axel Jeansson
 * Date: 2020-11-20
 * Time: 12:08
 * Project: SigrunsTicTacToe
 * Copyright: MIT
 */
public class ServerSideGame {


    ServerSidePlayer currentPlayer;



    //Skriv om så att vi har en vinnare om alla rundor spelats och någon spelare har fler poäng


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

