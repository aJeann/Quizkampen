package Server;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ashkan Amiri
 * Date:  2020-11-29
 * Time:  13:10
 * Project: Quizkampen
 * Copyright: MIT
 */
public class GameResult implements Serializable {
    List<GameHandler.ResultHandler> scoreList = new ArrayList<>();
    private String message = "";
    GameHandler.ResultHandler score;

    public String getMessage() {
        return message;
    }

    public void setScore(GameHandler.ResultHandler score) {
        scoreList.add(score);
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<GameHandler.ResultHandler> getScoreList() {
        return scoreList;
    }

    public void setScoreList(GameHandler.ResultHandler scoreObject) {
        this.scoreList.add(scoreObject);
    }

    public int getSizeByRound(int round)
    {
        int index = 0;
        for (GameHandler.ResultHandler res: scoreList)
        {
            if (res.round == round)
            {
                index++;
            }
        }
        return index;
    }

    public int getScoreByRound(int round, String player)
    {
        int score = 0;
        for (GameHandler.ResultHandler res: scoreList)
        {
            if (res.round == round && res.player.equals(player))
            {
                score = res.score;
            }
        }
        return score;
    }

    public int getSize()
    {
        return this.scoreList.size();
    }


    }