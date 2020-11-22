package NewClientServer;

import UserInterface.Misc.RoundedBorder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by Axel Jeansson
 * Date: 2020-11-20
 * Time: 12:08
 * Project: SigrunsTicTacToe
 * Copyright: MIT
 */
public class QuizkampenClient implements ActionListener {

    private JFrame frame = new JFrame("QuizkampenClient");
    private JLabel messageLabel = new JLabel("");
    private JTextField question = new JTextField("");
    private JTextField category = new JTextField("");
    private JButton b1 = new JButton();
    private JButton b2 = new JButton();
    private JButton b3 = new JButton();
    private JButton b4 = new JButton();
    private JPanel cardPanel;
    private JPanel labelPanel;
    private CardLayout cardLayout;
    JPanel boardPanel = new JPanel();
    JPanel postRound = new JPanel();
    JTextField results = new JTextField();
    JTextField result = new JTextField();
    JPanel resultPanel = new JPanel();
    JButton back = new JButton();
    int score;


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

        // Layout GUI
        frame.setLayout(new BorderLayout());
        labelPanel.setBackground(Color.lightGray);
        labelPanel.setSize(500, 150);
        messageLabel.setBackground(Color.lightGray);
        labelPanel.add(messageLabel);

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
            response = in.readLine();
            System.out.println("Testa");
            if (response == null)
                break;

            if (response.startsWith("YOUR_TURN")) {
                System.out.println(userID + " startar");
                System.out.println(response);
                nextQ();
          /*  } else if (response.startsWith("OPPONENT_PLAYED")) {
                System.out.println(opponentUserID + "har avslutat. Din tur att spela");
                messageLabel.setText("Waiting for opponent to finish his/her round");
                nextQ();*/
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
                            displayResult("Congrats... You're WON");
                            frame.setTitle("WON");
                        } else {
                            System.out.println("Lose!");
                            displayResult("Sorry... You're lose!");
                            frame.setTitle("LOSE");
                        }
                    } else if (player1 < player2) {
                        if (player1 == correctGuesses) {
                            System.out.println("Lose");
                            displayResult("Sorry... You're lose!");
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

        cardLayout.show(cardPanel, "board");

        if (index == questions.length) {
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

    private boolean wantsToPlayAgain() {
        int response = JOptionPane.showConfirmDialog(frame,
                "Want to play again?",
                "Tic Tac Toe is Fun Fun Fun",
                JOptionPane.YES_NO_OPTION);
        frame.dispose();
        return response == JOptionPane.YES_OPTION;
    }

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
            src.setBackground(Color.GREEN);
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
