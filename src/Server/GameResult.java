package Server;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import Server.GameHandler;

/**
 * Created by Ashkan Amiri
 * Date:  2020-11-29
 * Time:  13:10
 * Project: Quizkampen
 * Copyright: MIT
 */
public class GameResult implements Serializable {
    List<GameHandler.ResultHandler> scoreList = new ArrayList<>();

    public List<GameHandler.ResultHandler> getScoreList() {
        return scoreList;
    }

    public void setScoreList(GameHandler.ResultHandler scoreObject) {
        this.scoreList.add(scoreObject);
    }
}
