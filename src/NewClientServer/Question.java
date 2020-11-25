package NewClientServer;

import java.io.Serializable;
import java.util.Arrays;

public class Question implements Serializable {
    private String category;
    private String question;
    private String correctanswear;
    private String[] answers;

    public Question(String category, String question, String correctanswear, String[] answers) {
        this.category = category;
        this.question = question;
        this.correctanswear = correctanswear;
        this.answers = answers;
    }

    public String getQuestion(){
        return question;
    }

    public String[] getAnswers(){
        return answers;
    }

    public String getCategory(){
        return category;
    }

    public String getCorrectanswear(){
        return correctanswear;
    }















}