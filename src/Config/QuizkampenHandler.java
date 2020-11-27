package Config;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Ashkan Amiri
 * Date:  2020-11-27
 * Time:  18:41
 * Project: Quizkampen
 * Copyright: MIT
 */
public class QuizkampenHandler implements Serializable {
    private String question;
    private String correctAnswer;
    private List<String> options;
    private String category;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int id;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    @Override
    public String toString() {
        return "QuizkampenHandler{" +
                "question='" + question + '\'' +
                ", correctAnswer='" + correctAnswer + '\'' +
                ", options=" + options +
                ", category='" + category + '\'' +
                ", id=" + id +
                '}';
    }
}
