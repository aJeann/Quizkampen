package NewClientServer;

import UserInterface.Misc.RoundedBorder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.lang.reflect.Array;
import java.net.Socket;
import java.util.List;
import java.util.ArrayList;
import NewClientServer.Question;
import NewClientServer.GameDB;

/**
 * Created by Axel Jeansson
 * Date: 2020-11-20
 * Time: 12:08
 * Project: SigrunsTicTacToe
 * Copyright: MIT
 */
public class QuizkampenClient implements ActionListener {


    private List<Question> questionsInGame = new ArrayList<>();

    private JFrame frame = new JFrame("QuizkampenClient");
    private JLabel messageLabel = new JLabel("");
    private JTextField question = new JTextField("");
    private JTextField category = new JTextField("");
    private JButton b1 = new JButton();
    private JButton b2 = new JButton();
    private JButton b3 = new JButton();
    private JButton b4 = new JButton();
    private JPanel panelforbButtonCategories;
    private JPanel categoryPanel;
    private JButton buttonCatTeknologi = new JButton();
    private JButton buttonCatDatoreronternet = new JButton();
    private JButton buttonCatMänniskan = new JButton();
    private JButton buttonCatSamhället = new JButton();
    private JPanel cardPanel;
    private JPanel labelPanel;
    private CardLayout cardLayout;
    private JLabel categoryMessage = new JLabel("Välj kategori");
    JPanel boardPanel = new JPanel();
    JPanel postRound = new JPanel();
    JTextField results = new JTextField();
    JTextField result = new JTextField();
    JPanel resultPanel = new JPanel();
    JButton back = new JButton();
    int score;
    private ArrayList<JButton> buttons = new ArrayList<>();

    private String klickad;
    private boolean knappagerande = false;

    private String correctAnswer;
    private JButton[] newButtons = new JButton[4];
    private JButton nextRound = new JButton("Nästa fråga");
    private int roundNr = 0;


    Object fromServer;
    Question questionsDB;
    ObjectInputStream getin;




    //______________________________________
    //Hårdkodade frågor (ersätt med frågor från databas?)

   private String[] questions = {"Vad heter vår lärare i OOP?", "Vad heter skolan?", "Vilken dag är bäst?", "Är java kul?", "Fungerar detta?"};


    private String[][] options = {
            {"Sigrid", "Mahmud", "Jonas", "Carl XVI Gustaf"},
            {"Chalmers", "Nackademin", "Handels", "Harvard"},
            {"Söndag", "Tisdag", "Lördag", "Måndag"},
            {"Nej", "Nej", "Nej", "Ibland"},
            {"Nej", "Kanske", "Ja", "Verkligen inte"}};


    private String[] categories = {"Java OOP", "Skolor", "Dagar", "Skoj", "Test"};
    private String[] answer = {"Sigrid", "Nackademin", "Lördag", "Ibland", "Ja"};


    //___________________________________


    private int correctGuesses;
    private int index = 0;
    private int nmbrOfQs = questions.length;// (Ersätt med längd på array/listan med frågor)

    private static int PORT = 5000;
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    String userID = "";

    /**
     * Constructs the client by connecting to a server, laying out the
     * GUI and registering GUI listeners.
     */
    public QuizkampenClient(String serverAddress) throws Exception {

        socket = new Socket(serverAddress, PORT);
        in = new BufferedReader(new InputStreamReader(
                socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        labelPanel = new JPanel();
        panelforbButtonCategories = new JPanel();
        categoryPanel = new JPanel();

        // Layout GUI
        frame.setLayout(new BorderLayout());
        labelPanel.setBackground(Color.lightGray);
        labelPanel.setSize(500, 150);
        messageLabel.setBackground(Color.lightGray);
        labelPanel.add(messageLabel);

        //Category panel
        categoryPanel.setBackground(Color.LIGHT_GRAY);
        categoryPanel.setLayout(new BorderLayout());

        categoryPanel.add(categoryMessage);
        categoryMessage.setFont(new Font("Dialog", Font.BOLD, 20));
        categoryMessage.setForeground(Color.WHITE);

        panelforbButtonCategories = new JPanel();
        categoryPanel.add(panelforbButtonCategories, BorderLayout.SOUTH);
        panelforbButtonCategories.setLayout(new GridLayout(2, 2));
        panelforbButtonCategories.setBackground(Color.lightGray);
        panelforbButtonCategories.setBounds(100, 0, 225, 60);


        //Category buttons
        panelforbButtonCategories.add(buttonCatTeknologi);
        buttonCatTeknologi.setForeground(Color.white);
        buttonCatTeknologi.setBackground(Color.darkGray);
        buttonCatTeknologi.addActionListener(e -> {
            knappagerande = true;
            klickad = buttonCatTeknologi.getText();
        });

        panelforbButtonCategories.add(buttonCatDatoreronternet);
        buttonCatDatoreronternet.setForeground(Color.white);
        buttonCatDatoreronternet.setBackground(Color.darkGray);
        buttonCatDatoreronternet.addActionListener(e -> {
            knappagerande = true;
            klickad = buttonCatDatoreronternet.getText();
        });

        panelforbButtonCategories.add(buttonCatMänniskan);
        buttonCatMänniskan.setForeground(Color.white);
        buttonCatMänniskan.setBackground(Color.darkGray);
        buttonCatMänniskan.addActionListener(e -> {
            knappagerande = true;
            klickad = buttonCatMänniskan.getText();
        });

        panelforbButtonCategories.add(buttonCatSamhället);
        buttonCatSamhället.setForeground(Color.white);
        buttonCatSamhället.setBackground(Color.darkGray);
        buttonCatSamhället.addActionListener(e -> {
            knappagerande = true;
            klickad = buttonCatSamhället.getText();
        });


        cardPanel.add(boardPanel, "board");
        cardPanel.add(postRound, "postGame");
        cardPanel.add(resultPanel, "result");
        boardPanel.setBackground(Color.black);
        boardPanel.setLayout(null);

        question.setBounds(25, 10, 425, 300);
        question.setBackground(Color.GREEN);
        question.setForeground(Color.BLACK);
        question.setFont(new Font("Dialog", Font.BOLD, 20));
        question.setEditable(false);
        question.add(category);

        category.setBounds(100, 0, 225, 60);
        category.setBackground(Color.RED);
        category.setFont(new Font("Dialog", Font.BOLD, 20));

        b1.setBounds(25, 325, 200, 100);
        b1.setFont(new Font("Dialog", Font.BOLD, 20));
        b1.setFocusable(false);

        b1.addActionListener(this);
        b1.setEnabled(false);

        b2.setBounds(250, 325, 200, 100);
        b2.setFont(new Font("Dialog", Font.BOLD, 20));
        b2.setFocusable(false);
        b2.setEnabled(false);
        b2.addActionListener(this);

        b3.setBounds(25, 450, 200, 100);
        b3.setFont(new Font("Dialog", Font.BOLD, 20));
        b3.setFocusable(false);
        b3.setEnabled(false);
        b3.addActionListener(this);

        b4.setBounds(250, 450, 200, 100);
        b4.setFont(new Font("Dialog", Font.BOLD, 20));
        b4.setFocusable(false);
        b4.setEnabled(false);
        b4.addActionListener(this);



        boardPanel.add(question);
        boardPanel.add(b1);
        boardPanel.add(b2);
        boardPanel.add(b3);
        boardPanel.add(b4);

        frame.getContentPane().add(labelPanel, BorderLayout.SOUTH);
        frame.getContentPane().add(cardPanel, BorderLayout.CENTER);
    }

    /**
     * The main thread of the client will listen for messages
     * from the server.  The first message will be a "WELCOME"
     * message in which we receive our mark.  Then we go into a
     * loop listening for "VALID_MOVE", "OPPONENT_MOVED", "VICTORY",
     * "DEFEAT", "TIE", "OPPONENT_QUIT or "MESSAGE" messages,
     * and handling each message appropriately.  The "VICTORY",
     * "DEFEAT" and "TIE" ask the user whether or not to play
     * another game.  If the answer is no, the loop is exited and
     * the server is sent a "QUIT" message.  If an OPPONENT_QUIT
     * message is recevied then the loop will exit and the server
     * will be sent a "QUIT" message also.
     */

    public void play() throws Exception {
        String response;



        //String opponentUserID = "P";
        in = new BufferedReader(new InputStreamReader(
                socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);

        response = in.readLine();
        System.out.println(response);

        if (response.startsWith("WELCOME")) {
            question.setText("Welcome!... Waiting for opponent");
            userID = response.substring(8, response.length());
            // opponentUserID = (userID == "playerOne" ? "playerTwo" : "playerOne");
            frame.setTitle("QuizkampenClient - Player " + userID);
        }
        while (true) {

            /*
            GameDB t1 = new GameDB();
            t1.GameDBquestions();
            t1.getQuestionsInGame();
            questionsInGame = new ArrayList<>();
            questionsInGame = t1.getQuestionsInGame();

            String[] questions = questionsInGame;
            System.out.println(questionsInGame.get(1));



             */




            response = in.readLine();
            System.out.println("Testa");
            if (response == null)
                break;

            if (response.startsWith("YOUR_TURN")) {
                System.out.println(userID + " startar");
                System.out.println(response);
                nextQ();

            } else if (response.startsWith("ROUND_OVER")) {
                System.out.println("Båda har spelat.");
            } else if (response.startsWith("MESSAGE")) {
                messageLabel.setText(response.substring(8));
            } else if (response.startsWith("RESULT")) {
                System.out.println("Båda har spelat.");
                System.out.println("response-->" + response);
                out.println("ENDROUND");
                response = response.substring(6);
                response = response.replace("[", "");
                response = response.replace("]", "");
                System.out.println("list" + response);

                String[] resultList = response.split(",");
                System.out.println("resultList" + resultList);
                if (resultList.length == 2) {
                    int player1 = Integer.parseInt(resultList[0].trim());
                    int player2 = Integer.parseInt(resultList[1].trim());
                    if (player1 > player2) {
                        if (player1 == correctGuesses) {
                            System.out.println("WON");
                            displayResult("Congrats... You WON");
                            frame.setTitle("WON");
                        } else {
                            System.out.println("Lose!");
                            displayResult("Sorry... You lose!");
                            frame.setTitle("LOSE");
                        }
                    } else if (player1 < player2) {
                        if (player1 == correctGuesses) {
                            System.out.println("Lose");
                            displayResult("Sorry... You lose!");
                            frame.setTitle("LOSE");
                        } else {
                            System.out.println("WON");
                            displayResult("Congrats... You're WON");
                            frame.setTitle("WON");
                        }
                    } else {
                        System.out.println("Draw");
                        displayResult("It's a Draw!");
                    }

                    break;

                }


            }

        }

        System.out.println("Testa");
    }

    private void showCategortyButtons(){
        buttons.clear();
        buttons.add(buttonCatTeknologi);
        buttons.add(buttonCatMänniskan);
        buttons.add(buttonCatDatoreronternet);
        buttons.add(buttonCatSamhället);

    }

    private void checkButton(JButton pressedButton){
        knappagerande = true;
        klickad = pressedButton.getText();
        if(klickad.equals(correctAnswer)){
            pressedButton.setBackground(Color.green);
        } else {
            pressedButton.setBackground(Color.red);
        }
    }

    private void displayCorrectAnswerONE(){
        for(JButton button : buttons){
            if(button.getText().equals(correctAnswer)){
                button.setBackground(Color.green);
            }
        }
    }

    public void setCategories1(ArrayList <String> answers) {


        showCategortyButtons();

        for(String alts : answers){
            buttons.get(index).setText(alts);
            index++;
        }
    }

    public void setCorrectAnswer1(String answer1){
        correctAnswer = answer1;
    }




    private void displayResult(String message) {
        cardLayout.show(cardPanel, "result");
        resultPanel.setLayout(null);
        resultPanel.setSize(300, 200);
        resultPanel.setOpaque(false);
        result.setBounds(15, 10, 450, 200);
        result.setText(message);
        result.setEditable(false);
        result.setHorizontalAlignment(JTextField.CENTER);
        result.setFont(new Font("Dialog", Font.BOLD, 30));
        result.setBorder(new RoundedBorder(10));
        resultPanel.add(result);

    }

    public void nextQ() throws IOException {


        for (int i = 0; i <4; i++){
            newButtons[i] = new JButton("");
            newButtons[i].addActionListener(this);
            newButtons[i].setOpaque(true);
            newButtons[i].setVisible(true);
        }

        nextRound.addActionListener(ae -> {
            if(nextRound.getText().equals("Nytt spel")){
                out.println("Nytt spel");
                roundNr = 1;
            }
        });


        cardLayout.show(cardPanel, "board");

        if (index == questionsInGame.length) {
            System.out.println("Slut " + correctGuesses);
            out.println("ROUND_OVER " + correctGuesses);
            postRound();
        }

        //category.setText("Matte");
        if (index < categories.length) {
            category.setText(categories[index]);
            question.setText(questions[index]);


            //question.setText("4+4/0?");

            question.setHorizontalAlignment(JTextField.CENTER);
            category.setHorizontalAlignment(JTextField.CENTER);

            b1.setBackground(Color.DARK_GRAY);
            b1.setForeground(Color.WHITE);
            b2.setBackground(Color.DARK_GRAY);
            b2.setForeground(Color.WHITE);
            b3.setBackground(Color.DARK_GRAY);
            b3.setForeground(Color.WHITE);
            b4.setBackground(Color.DARK_GRAY);
            b4.setForeground(Color.WHITE);

        /*b1.setText("8");
        b2.setText("0");
        b3.setText("4");
        b4.setText("16");
         */
            b1.setText(options[index][0]);
            b1.setEnabled(true);
            b2.setText(options[index][1]);
            b2.setEnabled(true);
            b3.setText(options[index][2]);
            b3.setEnabled(true);
            b4.setText(options[index][3]);
            b4.setEnabled(true);
        }
    }

    public void showAnswer() {
        b1.setEnabled(false);
        b2.setEnabled(false);
        b3.setEnabled(false);
        b4.setEnabled(false);

        Timer pause = new Timer(500, e -> {

            b1.setEnabled(true);
            b2.setEnabled(true);
            b3.setEnabled(true);
            b4.setEnabled(true);

            index++;
            try {
                nextQ();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });

        pause.setRepeats(false);
        pause.start();
    }

    public void postRound() {
        score = correctGuesses;
        cardLayout.show(cardPanel, "postGame");
        postRound.setLayout(null);
        postRound.setSize(500, 700);
        postRound.setOpaque(false);

        results.setBounds(15, 10, 450, 200);
        results.setText("Din score:\n " + score);
        results.setEditable(false);
        results.setHorizontalAlignment(JTextField.CENTER);
        results.setFont(new Font("Dialog", Font.BOLD, 30));
        results.setBorder(new RoundedBorder(10));
        back.setBounds(15, 580, 100, 50);
        back.setText("Tillbaka");
        back.addActionListener(e -> {
            index = 0;
            correctGuesses = 0;
            try {
                play();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });

        postRound.add(results);
        postRound.add(back);
    }

/*
    public boolean CorrectAnswer(JButton newButtons, Question answers){
        boolean result;
        if (newButtons.getText().equals(questionsDB.getCorrectanswear())){
            result = true;
        }else {
            result = false;
        }

    }

 */

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(index);
        b1.setEnabled(false);
        b2.setEnabled(false);
        b3.setEnabled(false);
        b4.setEnabled(false);

        JButton src = (JButton) e.getSource();

        String svar = src.getText();
        String rättSvar = "4";

        /*if (svar.equals(rättSvar)){
            System.out.println("Rätt");
            src.setBackground(Color.GREEN);
            correctGuesses++;
        }
         */
        if (svar.equals(answer[index])) {
            System.out.println("Rätt!");
            src.setBackground(Color.ORANGE);
            correctGuesses++;
        } else {
            System.out.println("Fel!");
            src.setBackground(Color.RED);
        }
        showAnswer();
    }


    /**
     * Runs the client as an application.
     */
    public static void main(String[] args) throws Exception {


        //while (true) {
        String serverAddress = (args.length == 0) ? "localhost" : args[1];
        QuizkampenClient client = new QuizkampenClient(serverAddress);
        client.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        client.frame.setSize(500, 700);
        client.frame.setVisible(true);
        client.frame.setResizable(false);
        client.play();
            /*if (!client.wantsToPlayAgain()) {
                break;
            }
             */
        //}


    }
}