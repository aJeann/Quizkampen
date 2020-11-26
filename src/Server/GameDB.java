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



    public ArrayList<Question> createQuestionsFromFile() {

        String GameDBquestions;
        String GameDBcategory;
        ArrayList<Question> questionsList = new ArrayList<>();

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
                questionsList.add(new Question(GameDBcategory, GameDBquestions ,correctAnswer, answers));

            }
            return questionsList;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }

    public static void main(String[] args) {
        GameDB database = new GameDB();
        for (Question q: database.createQuestionsFromFile()) {
            System.out.println("Kategori: " + q.getCategory());
            System.out.println("Fråga: " + q.getQuestion());
            System.out.println("Rätt svar: " + q.getCorrectanswear());
            for (String s:q.getAnswers()) {
                System.out.println("Svarsalternativ: " + s);
            }
        }
    }
}
