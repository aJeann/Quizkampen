package Server;

import Config.QuizkampenHandler;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Ashkan Amiri
 * Date:  2020-11-26
 * Time:  19:58
 * Project: Quizkampen
 * Copyright: MIT
 */
public class GameHandler implements Serializable {

    private String player;
    private String message;
    List<QuizkampenHandler> quizList;

    public GameHandler() {
        this.quizList = DBHandlerClass.readQuizFromFile();
    }

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
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

}
