package Server;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Config.Question;
/**
 * Created by Axel Jeansson, Christoffer Grännby,
 * Date: 2020-11-20
 * Time: 12:08
 * Project: SigrunsTicTacToe
 * Copyright: MIT
 */
public class GameDB {

    private final List<ArrayList<Question>> DBquestions = new ArrayList<>();
    private final ArrayList<Question> DBteknologi = new ArrayList<>();
    private final ArrayList<Question> DBsamhälle = new ArrayList<>();
    private final ArrayList<Question> DBmänniskan = new ArrayList<>();
    private final ArrayList<Question> DBdatorerointernet = new ArrayList<>();

    public void GameDBquestions() {

        String GameDBquestions;
        String GameDBanswears;
        String GameDBcategory;

        String questionList = "C:\\Users\\S\\Documents\\Nackademin\\Objektorienterad programmering och Java\\Sprint 5\\Quiz\\src\\Server\\QuestionsList.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(questionList))){

            while((GameDBcategory = reader.readLine()) != null){
                GameDBquestions = reader.readLine();
                GameDBanswears = reader.readLine();
                String[] AnswersArray = GameDBanswears.split(",");
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
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        DBquestions.add(DBteknologi);
        DBquestions.add(DBsamhälle);
        DBquestions.add(DBmänniskan);
        DBquestions.add(DBdatorerointernet);

        questionList = "C:\\Users\\S\\Documents\\Nackademin\\Objektorienterad programmering och Java\\Sprint 5\\Quiz\\src\\Server\\QuestionsList.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(questionList))){

            while((GameDBcategory = reader.readLine()) != null){
                GameDBquestions = reader.readLine();
                GameDBanswears = reader.readLine();
                String[] AnswersArray = GameDBanswears.split(",");
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
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        DBquestions.add(DBteknologi);
        DBquestions.add(DBsamhälle);
        DBquestions.add(DBmänniskan);
        DBquestions.add(DBdatorerointernet);

    }
}
