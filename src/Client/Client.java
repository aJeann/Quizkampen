package Client;

import Config.Question;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;

/**
 * Created by Axel Jeansson, Christoffer Grännby, Salem Koldzo, Iryna Gnatenko,
 * Date: 2020-11-12
 * Time: 13:47
 * Project: Quizkampen
 * Copyright: MIT
 */
public class Client implements ActionListener {

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
    JPanel gamePanel = new JPanel();
    JTextField resultText = new JTextField();
    JPanel resultPanel = new JPanel();

    int score;
    int round = 1;

    //ROUNDS
    JPanel newRound = new JPanel();
    JPanel players = new JPanel();
    JPanel round1 = new JPanel();
    JPanel round2 = new JPanel();
    JPanel round3 = new JPanel();
    JPanel result = new JPanel();
    JLabel playerOneIcon = new JLabel(new ImageIcon("images\\reindeer.png"));
    JLabel playerOneName = new JLabel();
    JLabel playerTwoIcon = new JLabel(new ImageIcon("images\\skull.png"));
    JLabel playerTwoName = new JLabel();
    JLabel versus = new JLabel("-");
    JLabel r1 = new JLabel("Runda 1");
    JLabel r2 = new JLabel("Runda 2");
    JLabel r3 = new JLabel("Runda 3");
    JLabel resultLabel = new JLabel("Resultat");
    JButton startNewRound = new JButton("");
    JTextField p1r1 = new JTextField("Resultat runda 1");
    JTextField p2r1 = new JTextField("Resultat runda 1");
    JTextField p1r2 = new JTextField("Resultat runda 2");
    JTextField p2r2 = new JTextField("Resultat runda 2");
    JTextField p1r3 = new JTextField("Resultat runda 3");
    JTextField p2r3 = new JTextField("Resultat runda 3");
    JTextField p1result = new JTextField("Slutresultat p1");
    JTextField p2result = new JTextField("Slutresultat p2");

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

    private static int PORT = 23325;
    private Socket socket;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    String userID = "";
    String opponentUserID = "";

    /**
     * Constructs the client by connecting to a server, laying out the
     * GUI and registering GUI listeners.
     */
    public Client(String serverAddress) throws Exception {

        socket = new Socket(serverAddress, PORT);
        in = new ObjectInputStream(socket.getInputStream());
        out = new ObjectOutputStream(socket.getOutputStream());

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        labelPanel = new JPanel();

        // Layout GUI
        frame.setLayout(new BorderLayout());
        labelPanel.setBackground(Color.lightGray);
        labelPanel.setSize(500, 150);
        messageLabel.setBackground(Color.lightGray);
        labelPanel.add(messageLabel);

        cardPanel.add(gamePanel, "game");
        cardPanel.add(resultPanel, "result");
        cardPanel.add(newRound, "newRound");
        gamePanel.setBackground(Color.black);
        gamePanel.setLayout(null);

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


        b2.setBounds(250, 325, 200, 100);
        b2.setFont(new Font("Dialog", Font.BOLD, 20));
        b2.setFocusable(false);
        b2.addActionListener(this);

        b3.setBounds(25, 450, 200, 100);
        b3.setFont(new Font("Dialog", Font.BOLD, 20));
        b3.setFocusable(false);
        b3.addActionListener(this);

        b4.setBounds(250, 450, 200, 100);
        b4.setFont(new Font("Dialog", Font.BOLD, 20));
        b4.setFocusable(false);
        b4.addActionListener(this);

        gamePanel.add(question);
        gamePanel.add(b1);
        gamePanel.add(b2);
        gamePanel.add(b3);
        gamePanel.add(b4);

        frame.getContentPane().add(labelPanel, BorderLayout.SOUTH);
        frame.getContentPane().add(cardPanel, BorderLayout.CENTER);

    }

    public void play() throws Exception {
        cardLayout.show(cardPanel, "newRound");
        Object response;

        //String opponentUserID = "P";
        in = new ObjectInputStream(socket.getInputStream());
        out = new ObjectOutputStream(socket.getOutputStream());

        response = in.readObject();
        System.out.println(response);


            if (((String) response).startsWith("WELCOME")) {
                newRound();
                userID = ((String)response).substring(8);
                opponentUserID = (userID == "playerOne" ? "playerTwo" : "playerOne");
                frame.setTitle("QuizkampenClient - Player " + userID);
            }
            while (true) {
                response = in.readObject();
                System.out.println("Testa");
                if (response == null)
                    break;

                if (((String)response).startsWith("YOUR_TURN")) {
                    System.out.println(userID + " startar");
                    System.out.println(response);
                    newRound();
                    startNewRound.setEnabled(true);
          /*  } else if (response.startsWith("OPPONENT_PLAYED")) {
                System.out.println(opponentUserID + "har avslutat. Din tur att spela");
                messageLabel.setText("Waiting for opponent to finish his/her round");
                nextQ();*/
                } else if (((String)response).startsWith("ROUND_OVER")) {
                    System.out.println("Båda har spelat.");
                } else if (((String)response).startsWith("MESSAGE")) {
                    messageLabel.setText(((String)response).substring(8));
                } else if (((String)response).startsWith("RESULT")) {
                    System.out.println("Båda har spelat.");
                    System.out.println("response-->" + response);
                    out.writeObject("ENDROUND");
                    response = ((String)response).substring(6);
                    response = ((String)response).replace("[", "");
                    response = ((String)response).replace("]", "");
                    System.out.println("list" + response);

                    String[] resultList = ((String)response).split(",");
                    System.out.println("resultList" + resultList);
                    if (resultList.length == 2) {
                        int player1 = Integer.parseInt(resultList[0].trim());
                        int player2 = Integer.parseInt(resultList[1].trim());
                        if (player1 > player2) {
                            if (player1 == correctGuesses) {
                                System.out.println("You win");
                                displayResult("Congrats... You've won! Your score was: " + player1 + "\nYour opponents score was: " + player2);
                                frame.setTitle("WON");
                            } else {
                                System.out.println("You lose");
                                displayResult("Sorry... You've lost! Your score was: " + player2 + "\nYour opponents score was: " + player1);
                                frame.setTitle("LOST");
                            }
                        } else if (player1 < player2) {
                            if (player1 == correctGuesses) {
                                System.out.println("You lose");
                                displayResult("Sorry... You've lost!");
                                frame.setTitle("LOST");
                            } else {
                                System.out.println("You win");
                                displayResult("Congrats... You've WON");
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
        JOptionPane.showMessageDialog(null, message);
        /*
        cardLayout.show(cardPanel, "result");
        resultPanel.setLayout(null);
        resultPanel.setSize(300, 200);
        resultPanel.setOpaque(false);
        resultText.setBounds(15, 10, 450, 200);
        resultText.setText(message);
        resultText.setEditable(false);
        resultText.setHorizontalAlignment(JTextField.CENTER);
        resultText.setFont(new Font("Dialog", Font.BOLD, 30));
        resultText.setBorder(new RoundedBorder(10));
        resultPanel.add(result);*/

    }

    private void newRound() {
        cardLayout.show(cardPanel, "newRound");

        newRound.setLayout(null);
        newRound.setSize(500, 750);
        newRound.setOpaque(false);

        players.setBounds(0, 0, 500, 220);
        players.setBackground(Color.GREEN);
        //players.setOpaque(false);
        players.setLayout(null);


        playerOneIcon.setBounds(35, 20, 150, 150);
        playerOneName.setBounds(35, 170, 150, 30);
        playerOneName.setHorizontalAlignment(JLabel.CENTER);
        playerOneName.setText(userID);
        playerOneName.setFont(new Font("Dialog", Font.BOLD, 20));

        versus.setBounds(230, 80, 40, 20);
        versus.setFont(new Font("Dialog", Font.BOLD, 70));

        playerTwoIcon.setBounds(300, 20, 150, 150);
        playerTwoName.setBounds(300, 170, 150, 30);
        playerTwoName.setText(opponentUserID);
        playerTwoName.setHorizontalAlignment(JLabel.CENTER);
        playerTwoName.setFont(new Font("Dialog", Font.BOLD, 20));

        players.add(playerOneIcon);
        players.add(playerOneName);
        players.add(versus);
        players.add(playerTwoIcon);
        players.add(playerTwoName);

        round1.setBounds(0, 220, 500, 100);
        round1.setLayout(null);
        r1.setBounds(200, 35, 100, 50);
        r1.setFont(new Font("Dialog", Font.BOLD, 20));
        r1.setForeground(Color.WHITE);
        p1r1.setBounds(35, 35, 150, 50);
        p1r1.setEditable(false);
        p1r1.setHorizontalAlignment(JTextField.CENTER);
        p2r1.setBounds(300, 35, 150, 50);
        p2r1.setEditable(false);
        p2r1.setHorizontalAlignment(JTextField.CENTER);
        round1.setBackground(Color.BLUE);
        round1.add(r1);
        round1.add(p1r1);
        round1.add(p2r1);

        round2.setBounds(0, 320, 500, 100);
        round2.setLayout(null);
        r2.setBounds(200, 35, 100, 50);
        r2.setFont(new Font("Dialog", Font.BOLD, 20));
        r2.setForeground(Color.WHITE);
        p1r2.setBounds(35, 35, 150, 50);
        p1r2.setEditable(false);
        p1r2.setHorizontalAlignment(JTextField.CENTER);
        p2r2.setBounds(300, 35, 150, 50);
        p2r2.setEditable(false);
        p2r2.setHorizontalAlignment(JTextField.CENTER);
        round2.add(r2);
        round2.add(p1r2);
        round2.add(p2r2);
        round2.setBackground(Color.RED);

        round3.setBounds(0, 420, 500, 100);
        round3.setLayout(null);
        r3.setBounds(200, 35, 100, 50);
        r3.setFont(new Font("Dialog", Font.BOLD, 20));
        r3.setForeground(Color.WHITE);
        p1r3.setBounds(35, 35, 150, 50);
        p1r3.setEditable(false);
        p1r3.setHorizontalAlignment(JTextField.CENTER);
        p2r3.setBounds(300, 35, 150, 50);
        p2r3.setEditable(false);
        p2r3.setHorizontalAlignment(JTextField.CENTER);
        round3.add(r3);
        round3.add(p1r3);
        round3.add(p2r3);
        round3.setBackground(Color.BLUE);

        result.setBounds(0, 520, 500, 180);
        result.setLayout(null);
        result.setBackground(Color.BLACK);

        startNewRound.setBounds(165, 80, 150, 50);
        startNewRound.setBackground(Color.WHITE);
        startNewRound.setText("Starta runda: " + round);
        startNewRound.setEnabled(false);
        startNewRound.addActionListener(e -> {
            index = 0;
            correctGuesses = 0;
            try {
                createQuestions();
                nextQ();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });

        resultLabel.setBounds(185, 20, 115, 50);
        resultLabel.setFont(new Font("Dialog", Font.BOLD, 20));
        resultLabel.setHorizontalAlignment(JLabel.CENTER);
        resultLabel.setForeground(Color.WHITE);
        p1result.setBounds(35, 20, 150, 50);
        p1result.setEditable(false);
        p1result.setHorizontalAlignment(JTextField.CENTER);
        p2result.setBounds(300, 20, 150, 50);
        p2result.setEditable(false);
        p2result.setHorizontalAlignment(JTextField.CENTER);
        result.add(resultLabel);
        result.add(p1result);
        result.add(p2result);
        result.add(startNewRound);

        newRound.add(players);
        newRound.add(round1);
        newRound.add(round2);
        newRound.add(round3);
        newRound.add(result);


    }

    private void createQuestions() {

        //RANDOM KATEGORI
        //LÄGG TILL FEM FRÅGOR FRÅN KATEGORIN I ARRAY
        //LÄGG TILL FEM SVAR I ARRAY
        //SKICKA ALL INFORMATION TILL SERVERN SÅ ATT MOTSTÅNDAREN FÅR SAMMA FRÅGOR
    }


    public void nextQ() throws IOException {


        cardLayout.show(cardPanel, "game");

        if (index == questions.length) {
            System.out.println("Slut " + correctGuesses);
            out.writeObject("ROUND_OVER " + correctGuesses);
            if (round == 1) {
                p1r1.setText(String.valueOf(correctGuesses));
                round++;
                newRound();
            } else if (round == 2) {
                p1r2.setText(String.valueOf(correctGuesses));
                round++;
                newRound();
            } else if (round == 3) {
                p1r3.setText(String.valueOf(correctGuesses));
                round++;
                newRound();
            } else if (round > 3) {
                p1result.setText(p1r1.getText() + p1r2.getText() + p1r3.getText());
                displayResult("Hej");
            }

        }


        if (index < categories.length) {
            category.setText(categories[index]);
            question.setText(questions[index]);


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

        Timer pause = new Timer(700, e -> {

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
        Client client = new Client(serverAddress);
        client.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        client.frame.setSize(500, 750);
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
