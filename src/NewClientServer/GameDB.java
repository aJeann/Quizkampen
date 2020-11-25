package NewClientServer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import NewClientServer.Question;


public class GameDB {

    private final ArrayList<Question> DBquestions1 = new ArrayList<>();
    //private final List<ArrayList<Question>> DBquestions = new ArrayList<>();
    private final ArrayList<Question> DBteknologi = new ArrayList<>();
    private final ArrayList<Question> DBsamhälle = new ArrayList<>();
    private final ArrayList<Question> DBmänniskan = new ArrayList<>();
    private final ArrayList<Question> DBdatorerointernet = new ArrayList<>();




    public void GameDBquestions() {

        String GameDBquestions;
        String GameDBanswears;
        String GameDBcategory;

        String questionList = "C:\\Users\\S\\Documents\\Nackademin\\Objektorienterad programmering och Java\\Sprint 5\\Quiz\\src\\NewClientServer\\QuestionsList.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(questionList))){

            while((GameDBcategory = reader.readLine()) != null){
                GameDBquestions = reader.readLine();
                GameDBanswears = reader.readLine();
                String[] AnswersArray = GameDBanswears.split(",");
                //Question question = new Question(GameDBcategory, GameDBquestions ,AnswersArray[0], new String[]{AnswersArray[0], AnswersArray[1],AnswersArray[2],AnswersArray[3]});
                DBquestions1.add(new Question(GameDBcategory, GameDBquestions ,AnswersArray[0], new String[]{AnswersArray[0], AnswersArray[1],AnswersArray[2],AnswersArray[3]}));

              /*  if (GameDBcategory.equals("Teknologi")) {
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

               */


            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        /*
        DBquestions.add(DBteknologi);
        DBquestions.add(DBsamhälle);
        DBquestions.add(DBmänniskan);
        DBquestions.add(DBdatorerointernet);

         */

    }


    public List<Question> getQuestionsInGame(){
        List<Question> QuestionsInGame = new ArrayList<>();
        QuestionsInGame.add(DBquestions1.get(0));

        for (int i = 1; i < DBquestions1.size(); i++){
            if(DBquestions1.get(i).getCategory().equals(DBquestions1.get(0).getCategory())){
                QuestionsInGame.add(DBquestions1.get(i));
                DBquestions1.remove(i);

            }
        }
        return QuestionsInGame;
    }



}
