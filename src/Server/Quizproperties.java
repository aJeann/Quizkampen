package Server;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by Axel Jeansson, Christoffer Grännby, Salem Koldzo, Iryna Gnatenko,
 * Date: 2020-11-12
 * Time: 13:47
 * Project: Quizkampen
 * Copyright: MIT
 */
public class Quizproperties {

    private String numberOfRounds;
    private String numberOfQuestions;

    public Quizproperties(){
        Properties propva = new Properties();
        try{
            propva.load(new FileInputStream("source/quizsettings.properties"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.numberOfRounds = String.valueOf(propva.getProperty("rounds", "3"));
        this.numberOfQuestions = String.valueOf(propva.getProperty("questions", "3"));
    }

    public String getNumberOfRounds(){
        return numberOfRounds;
    }

    public String getNumberOfQuestions(){
        return numberOfQuestions;
    }
}
