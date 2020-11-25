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




    ServerSidePlayer currentPlayer;

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

