package Server;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Quizproperties {

    private String numberOfRounds;

    public Quizproperties(){
        Properties propva = new Properties();
        try{
            propva.load(new FileInputStream("src/Server/quizsettings.properties"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.numberOfRounds = String.valueOf(propva.getProperty("rounds", "3"));
    }

    public String getNumberOfRounds(){
        return numberOfRounds;
    }


}
