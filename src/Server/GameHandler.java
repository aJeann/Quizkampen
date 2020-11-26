package Server;

import Config.Question;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Ashkan Amiri
 * Date:  2020-11-26
 * Time:  19:58
 * Project: Quizkampen
 * Copyright: MIT
 */
public class GameHandler  implements Serializable {

    private String player;
    private String message;
    ArrayList<Question> questions;

    public GameHandler ()
    {
        GameDB database = new GameDB();
        this.questions = database.createQuestionsFromFile();
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

    public ArrayList<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(ArrayList<Question> questions) {
        this.questions = questions;
    }
}
