package Server;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Axel Jeansson, Christoffer Grännby, Salem Koldzo, Iryna Gnatenko, Ashkan Amiri
 * Date: 2020-11-12
 * Time: 13:47
 * Project: Quizkampen
 * Copyright: MIT
 */
public class ServerSideGame {

    public ServerSidePlayer currentPlayer;
    private GameDB database = Server.database;

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

    private List<String> resultList = new ArrayList<String>();

    public void addResult(String p) {
        resultList.add(p);
    }

    public List<String> getResults() {
        return resultList;
    }

    public GameDB getDatabase() {
        return database;
    }
}

