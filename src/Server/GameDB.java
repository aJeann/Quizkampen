package Server;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
        String GameDBcategory;

        //TODO fixa svaren i textfilen
        String questionList = "src/Server/QuestionsList.txt";
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
                //String[] AnswersArray = GameDBanswears.split(",");
                DBquestions.add(new Question(GameDBcategory, GameDBquestions ,correctAnswer, answers));

/*
               Question question = new Question(GameDBcategory, GameDBquestions ,AnswersArray[0], new String[]{AnswersArray[1],AnswersArray[2],AnswersArray[3]});

                 if (GameDBcategory.equals("Teknologi")) {
                    DBteknologi.add(question);
                }
                if (GameDBcategory.equals("Samhälle")) {
                    DBsamhälle.add(question);
                }
                if (GameDBcategory.equals("Människan")) {
                    DBmänniskan.add(question);
                }
                if (GameDBcategory.equals("Datorer och Internet")) {
                    DBdatorerointernet.add(question);
                }*/
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        /*DBquestions.add(DBteknologi);
        DBquestions.add(DBsamhälle);
        DBquestions.add(DBmänniskan);
        DBquestions.add(DBdatorerointernet);*/

    }

    public static void main(String[] args) {
        GameDB database = new GameDB();
        database.GameDBquestions();
        for (Question q:database.DBquestions) {
            System.out.println("Kategori: " + q.getCategory());
            System.out.println("Fråga: " + q.getQuestion());
            System.out.println("Rätt svar: " + q.getCorrectanswear());
            for (String s:q.getAnswers()) {
                System.out.println("Svarsalternativ: " + s);
            }
        }
    }
}
