package Server;

import Config.Question;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by Axel Jeansson, Christoffer Grännby, Salem Koldzo, Iryna Gnatenko,
 * Date: 2020-11-12
 * Time: 13:47
 * Project: Quizkampen
 * Copyright: MIT
 */
public class GameDB {

    private final List<Question> DBquestions = new ArrayList<>();

    public void GameDBquestions() {

        String GameDBquestions;
        String GameDBcategory;
        String GameDBanswears;

        //TODO fixa svaren i textfilen
        String questionList = "src/Server/QuestionsList.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(questionList))){

            while((GameDBcategory = reader.readLine()) != null){
                GameDBquestions = reader.readLine();
                GameDBanswears = reader.readLine();
                String[] AnswersArray = GameDBanswears.split(",");
                Collections.shuffle(Arrays.asList(AnswersArray));
                DBquestions.add(new Question(GameDBcategory, GameDBquestions ,AnswersArray[0], new String[]{AnswersArray[0], AnswersArray[1],AnswersArray[2],AnswersArray[3]}));

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
