package NewClientServer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
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

    private final ArrayList<Question> DBquestions = new ArrayList<>();
    private final ArrayList<Question> DBteknologi = new ArrayList<>();
    private final ArrayList<Question> DBsamhälle = new ArrayList<>();
    private final ArrayList<Question> DBmänniskan = new ArrayList<>();
    private final ArrayList<Question> DBdatorerointernet = new ArrayList<>();




    public void GameDBquestions() {

        String GameDBquestions;
        String GameDBcategory;
        String GameDBanswears;

        //TODO fixa svaren i textfilen
        String questionList = "src/NewClientServer/QuestionsList.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(questionList))){

            while((GameDBcategory = reader.readLine()) != null){
                GameDBquestions = reader.readLine();
                GameDBanswears = reader.readLine();
               // List<String>answers = new ArrayList<>();
//                String correctAnswer = reader.readLine();
//                String answerTwo = reader.readLine();
//                String answerThree = reader.readLine();
//                String answerFour = reader.readLine();
//                answers.add(correctAnswer);
//                answers.add(answerTwo);
//                answers.add(answerThree);
//                answers.add(answerFour);

                //Collections.shuffle(answers);
                String[] AnswersArray = GameDBanswears.split(",");
                Collections.shuffle(Arrays.asList(AnswersArray));
                DBquestions.add(new Question(GameDBcategory, GameDBquestions ,AnswersArray[0], new String[]{AnswersArray[0], AnswersArray[1],AnswersArray[2],AnswersArray[3]}));

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

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
