package Server;

import Config.QuizkampenHandler;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ashkan Amiri
 * Date:  2020-11-26
 * Time:  19:58
 * Project: Quizkampen
 * Copyright: MIT
 */
public class GameHandler implements Serializable {

    public String player;
    private String message;
    List<QuizkampenHandler> quizList;
    List<ResultHandler> scoreList;
    ResultHandler score;

    public ResultHandler getScore() {
        return score;
    }

    public void setScore(ResultHandler score) {
        this.score = score;
    }

    public void setScoreList(List<ResultHandler> scoreList) {
        this.scoreList = scoreList;
    }

    public List<ResultHandler> getScoreList() {
        return scoreList;
    }

    public GameHandler() {
        this.quizList = DBHandlerClass.readQuizFromFile();
       // scoreList = new ArrayList<>();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<QuizkampenHandler> getQuizList() {
        return quizList;
    }

    public ResultHandler setScore (int round, int score, String player)
    {
        ResultHandler  h = new ResultHandler();
        h.score = score;
        h.round = round;
        h.player = player;
        return h;
       // scoreList.add(h);
    }

    public static class ResultHandler implements Serializable
    {
        public int score;
        public String player;
        public int round;
        public int finalresult;

        @Override
        public String toString() {
            return "ResultHandler{" +
                    "score=" + score +
                    ", player='" + player + '\'' +
                    ", round=" + round +
                    ", finalresult=" + finalresult +
                    '}';
        }
    }
}
