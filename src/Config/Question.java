package Config;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;
/**
 * Created by Axel Jeansson, Christoffer Grännby,
 * Date: 2020-11-12
 * Time: 13:47
 * Project: Quizkampen
 * Copyright: MIT
 */
public class Question implements Serializable {
    private String category;
    private String question;
    private String correctanswear;
    private String[] answers;           // byt ut mot lista med strings,

    public Question(String category, String question, String correctanswear, String[] answers) {
        this.category = category;
        this.question = question;
        this.correctanswear = correctanswear;
        this.answers = answers;
        // Collections.shuffle(this.answers);       shufflar svaren i listan (i knapparna)
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