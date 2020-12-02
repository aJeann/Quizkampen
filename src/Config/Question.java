package Config;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Axel Jeansson, Christoffer Grännby, Salem Koldzo, Iryna Gnatenko, Ashkan Amiri
 * Date: 2020-11-12
 * Time: 13:47
 * Project: Quizkampen
 * Copyright: MIT
 */
public class Question implements Serializable {
    private String category;
    private String question;
    private String correctAnswer;
    private List<String> answers;


    public Question(String category, String question, String correctAnswer, List<String> answers) {
        this.category = category;
        this.question = question;
        this.correctAnswer = correctAnswer;
        this.answers = answers;
    }

    public String getQuestion(){
        return question;
    }

    public List<String> getAnswers(){
        return answers;
    }

    public String getCategory(){
        return category;
    }

    public String getCorrectAnswer(){
        return correctAnswer;
    }
}