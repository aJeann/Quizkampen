package Server;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import Config.Question;
/**
 * Created by Axel Jeansson, Christoffer Grännby, Salem Koldzo, Iryna Gnatenko,
 * Date: 2020-11-12
 * Time: 13:47
 * Project: Quizkampen
 * Copyright: MIT
 */
public class GameDB {

    private final ArrayList<Question> DBquestions = new ArrayList<>();
    private final ArrayList<Question> DBteknologi = new ArrayList<>();
    private final ArrayList<Question> DBsamhälle = new ArrayList<>();
    private final ArrayList<Question> DBmänniskan = new ArrayList<>();
    private final ArrayList<Question> DBdatorerointernet = new ArrayList<>();




    public void GameDBquestions() {

        String GameDBquestions;
        String GameDBanswears;
        String GameDBcategory;

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
