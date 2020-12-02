package Server;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.*;

import Config.Question;
/**
 * Created by Axel Jeansson, Christoffer Grännby, Salem Koldzo, Iryna Gnatenko, Ashkan Amiri
 * Date: 2020-11-12
 * Time: 13:47
 * Project: Quizkampen
 * Copyright: MIT
 */
public class GameDB {

    private final ArrayList<Question> DBquestions = new ArrayList<>();

    public GameDB() {

        String GameDBquestions;
        String GameDBcategory;

        String questionList = "source/QuestionsList.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(questionList))){

            while((GameDBcategory = reader.readLine()) != null){
                GameDBquestions = reader.readLine();
                List<String>answers = new ArrayList<>();
                String correctAnswer = reader.readLine();
                String answerTwo = reader.readLine();
                String answerThree = reader.readLine();
                String answerFour = reader.readLine();
                answers.add(correctAnswer);
                answers.add(answerTwo);
                answers.add(answerThree);
                answers.add(answerFour);
                Collections.shuffle(answers);


                DBquestions.add(new Question(GameDBcategory, GameDBquestions ,correctAnswer, answers));

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public ArrayList<Question> getDBquestions() {
        return DBquestions;
    }
}
