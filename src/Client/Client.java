package Client;

import UserInterface.GUI;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Christoffer Grännby
 * Date: 2020-11-15
 * Time: 12:31
 * Project: Quizkampen
 * Copyright: MIT
 */
public class Client implements Runnable {

    private String LOCATION = "127.0.0.1";
    Socket socket;
    private static int PORT = 8901;


    public Client(Socket playerOne, Socket playerTwo) {
//          this.playerOne = new Player(playerOne);
//          this.playerTwo = new Player(playerTwo);

    }

    public Client() {
    }

    public Client(String LOCATION) {
        this.LOCATION = LOCATION;
    }


    @Override
    public void run() {

    }


    public void play(String answer) throws Exception {
        socket = new Socket(LOCATION, PORT);
        BufferedReader in = new BufferedReader(new InputStreamReader(
                socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        GUI game = new GUI();
        String response;
        String round="";
        List<String> answerList = new ArrayList<>();


            response = in.readLine();

            if (game.selectedCat == "DatorerOInternet") {
                for (int i = 0; i <= answerList.size(); i++) {
                    String question = listquesionFile.get[i]; //each question per answer
                    answerList = listquesionFile.get[i++]; //all 4 answers of above quesion
                    //todo: send them to UI
                    break;
                }
                if (answer.equals("Green")) score++;
            }

            if (game.selectedCat == "människan") { // second category
                for (int i = 0; i <= answerList.size(); i++) {
                    String question = listquesionFile.get[i]; //each question per answer
                    answerList = listquesionFile.get[i++]; //all 4 answers of above quesion
                    //todo: send them to UI
                    break;
                }
                if (answer.equals("Green")) int score = score++;
            }
            if (game.selectedCat == "samhället") { // third category
                for (int i = 0; i <= answerList.size(); i++) {
                    String question = listquesionFile.get[i]; //each question per answer
                    answerList = listquesionFile.get[i++]; //all 4 answers of above quesion
                    //todo: send them to UI
                    break;
                }
                if (answer.equals("Green")) int score = score++;
            }
            if (game.selectedCat == "teknologi") { // four category
                for (int i = 0; i <= answerList.size(); i++) {
                    String question = listquesionFile.get[i]; //each question per answer
                    answerList = listquesionFile.get[i++]; //all 4 answers of above quesion
                    //todo: send them to UI
                    break;
                }
                if (answer.equals("Green")) int score = score++;
            }
        socket.close();
        // send total score to UI
    }


    public static void main(String[] args) throws Exception {
        while (true) {
            String serverAddress = (args.length == 0) ? "localhost" : args[1];
            Client client = new Client(serverAddress);
//            client.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//            client.frame.setSize(240, 160);
//            client.frame.setVisible(true);
//            client.frame.setResizable(true);
            client.play();
            if (!client.wantsToPlayAgain()) {
                break;
            }
        }