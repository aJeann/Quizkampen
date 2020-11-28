package Server;

import Config.QuizkampenHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Axel Jeansson, Christoffer Grännby, Salem Koldzo, Iryna Gnatenko,
 * Date: 2020-11-12
 * Time: 13:47
 * Project: Quizkampen
 * Copyright: MIT
 */
public class DBHandlerClass {

    public static List<QuizkampenHandler> readQuizFromFile() {

        List<QuizkampenHandler> quizList = new ArrayList<>();

        Path pathToFile = Paths.get("src/Server/QuizParametersnew.csv");

        try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.UTF_8)) {
            String line = br.readLine();
            while (line != null) {
                String[] attributes = line.split(";");
                quizList.add(createQuiz(attributes));
                line = br.readLine();
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return quizList;
    }

    private static QuizkampenHandler createQuiz(String[] metadata) {
        int id = Integer.parseInt(metadata[0]);
        String category = metadata[1];
        String question = metadata[2];
        List<String> options = Arrays.asList(metadata[3].split(":"));
        String answer = metadata[4];
        QuizkampenHandler quiz = new QuizkampenHandler();
        quiz.setId(id);
        quiz.setQuestion(question);
        quiz.setOptions(options);
        quiz.setCategory(category);
        quiz.setCorrectAnswer(answer);
        return quiz;
    }
}

