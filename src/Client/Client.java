package Client;

import Config.Question;
import Server.Quizproperties;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;
import java.util.List;

import UserInterface.*;

/**
 * Created by Axel Jeansson, Christoffer Grännby, Salem Koldzo, Iryna Gnatenko,
 * Date: 2020-11-12
 * Time: 13:47
 * Project: Quizkampen
 * Copyright: MIT
 */
public class Client implements ActionListener {

    private JFrame frame = new JFrame("QuizkampenClient");
    private JPanel questionPanel = new JPanel();
    public JTextArea questionArea = new JTextArea("");
    private JTextField category = new JTextField("");
    private JButton b1 = new JButton();
    private JButton b2 = new JButton();
    private JButton b3 = new JButton();
    private JButton b4 = new JButton();
    private JButton changeBG = new JButton("Byt bakgrundsfärg");
    private JPanel cardPanel;
    private CardLayout cardLayout;
    JPanel gamePanel = new JPanel();
    JPanel resultPanel = new JPanel();
    List<Question> q;

    int score;
    int round = 1;

    //ROUNDS
    JPanel newRound = new JPanel();
    JPanel players = new JPanel();
    JPanel round1 = new JPanel();
    JPanel round2 = new JPanel();
    JPanel round3 = new JPanel();
    JPanel result = new JPanel();
    ImageIcon reindeer = new ImageIcon("images\\reindeer.png");
    ImageIcon skull = new ImageIcon("images\\skull.png");
    JLabel playerOneIcon = new JLabel();
    JLabel playerOneName = new JLabel();
    JLabel playerTwoIcon = new JLabel();
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


    JSlider g = new JSlider(JSlider.HORIZONTAL, 0, 250, 0);
    JSlider b = new JSlider(JSlider.HORIZONTAL, 0, 250, 0);
    JSlider r = new JSlider(JSlider.HORIZONTAL, 0, 250, 0);

    JLabel rod = new JLabel("Rött = " +r.getValue());
    JLabel gron = new JLabel("Grönt = "+g.getValue());
    JLabel bla = new JLabel("Blått = "+b.getValue());
    JPanel colorSwitch = new JPanel();
    BackgroundColor bg = new BackgroundColor();
    Color bgColor = bg.getBgColor();
    JButton backToGame = new JButton();

    //___________________________________
    private int correctGuesses;
    private int index = 0;
    private int index2 = 5;
    private int index3 = 10;

    private int amountOfRounds;
    private int amountOfQuestions;

    private static int PORT = 23325;
    private Socket socket;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    String userID = "";
    String opponentUserID = "";
    int player1round1;
    int player2round1;
    int player1round2;
    int player2round2;
    int player1round3;
    int player2round3;

    /**
     * Constructs the client by connecting to a server, laying out the
     * GUI and registering GUI listeners.
     */
    public Client(String serverAddress) {
        try {
            socket = new Socket(serverAddress, PORT);
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        // Layout GUI
        frame.setLayout(new BorderLayout());
        frame.getContentPane().setBackground(bgColor);

        cardPanel.add(gamePanel, "game");
        cardPanel.add(resultPanel, "result");
        cardPanel.add(newRound, "newRound");
        cardPanel.add(colorSwitch, "colorSwitch");
        cardPanel.setOpaque(false);

        gamePanel.setOpaque(false);
        gamePanel.setLayout(null);

        questionPanel.setBounds(25, 10, 425, 300);
        questionPanel.setLayout(null);
        questionPanel.setBackground(new Color(153, 216, 240));
        questionPanel.add(category);
        questionPanel.add(questionArea);

        questionArea.setBounds(50, 120, 325, 150);
        questionArea.setBackground(new Color(153, 216, 240));
        questionArea.setForeground(Color.BLACK);
        questionArea.setEditable(false);
        questionArea.setLineWrap(true);
        questionArea.setWrapStyleWord(true);
        questionArea.setFont(new Font("Dialog", Font.BOLD, 15));

        category.setBounds(150, 0, 125, 30);
        category.setBackground(Color.WHITE);
        category.setEditable(false);
        category.setFont(new Font("Dialog", Font.BOLD, 20));

        b1.setBounds(25, 325, 200, 100);
        b1.setFont(new Font("Dialog", Font.BOLD, 10));
        b1.setFocusable(false);
        b1.setBorder(new RoundedBorder(10));
        b1.addActionListener(this);

        b2.setBounds(250, 325, 200, 100);
        b2.setFont(new Font("Dialog", Font.BOLD, 10));
        b2.setFocusable(false);
        b2.setBorder(new RoundedBorder(10));
        b2.addActionListener(this);

        b3.setBounds(25, 450, 200, 100);
        b3.setFont(new Font("Dialog", Font.BOLD, 10));
        b3.setFocusable(false);
        b3.setBorder(new RoundedBorder(10));
        b3.addActionListener(this);

        b4.setBounds(250, 450, 200, 100);
        b4.setFont(new Font("Dialog", Font.BOLD, 10));
        b4.setFocusable(false);
        b4.setBorder(new RoundedBorder(10));
        b4.addActionListener(this);

        changeBG.addActionListener(e -> changeBackground());

        gamePanel.add(questionPanel);
        gamePanel.add(b1);
        gamePanel.add(b2);
        gamePanel.add(b3);
        gamePanel.add(b4);

        frame.getContentPane().add(cardPanel, BorderLayout.CENTER);
        frame.getContentPane().add(changeBG, BorderLayout.SOUTH);

    }

    public void play() throws Exception {
        cardLayout.show(cardPanel, "newRound");

        Quizproperties quizSettings = new Quizproperties();
        amountOfRounds = Integer.parseInt(quizSettings.getNumberOfRounds());
        amountOfQuestions = Integer.parseInt(quizSettings.getNumberOfQuestions());

        if (amountOfRounds == 2)
            round3.setVisible(false);

        try {
            startNewRound.setEnabled(false);
            startNewRound.setText("Starta nästa runda");
            Object response;

            response = in.readObject();
            System.out.println(response);

            if (((String) response).startsWith("WELCOME")) {
                newRound();
                userID = ((String) response).substring(8);
                if (userID.equals("playerOne")) {
                    opponentUserID = "playerTwo";
                } else {
                    opponentUserID = "playerOne";
                }
                frame.setTitle("QuizkampenClient - Player " + userID);
            }

            while (true) {
                response = in.readObject();
                if (response instanceof List<?>) {
                    createQuestions((List<Question>) response);
                } else if (response instanceof String) {

                    if (response == null)
                        break;

                    if (((String) response).startsWith("YOUR_TURN")) {
                        newRound();
                        if (userID.equals("playerTwo")){
                            startNewRound.setEnabled(false);
                            out.writeObject("ROUND_OVER 0");
                        }
                        else
                            startNewRound.setEnabled(true);

                    } else if (((String) response).startsWith("RESULT")) {
                        out.writeObject("ENDROUND");
                        response = ((String) response).substring(6);
                        response = ((String) response).replace("[", "");
                        response = ((String) response).replace("]", "");

                        String[] resultList = ((String) response).split(",");
                        System.out.println("resultList" + resultList);
                        if (resultList.length > 6){
                            startNewRound.setText("Slutspelat");
                            startNewRound.setEnabled(false);
                        }
                        if (resultList.length == 1){
                            if (userID.equals("playerOne")){
                                startNewRound.setEnabled(true);
                            }
                            else
                                startNewRound.setEnabled(false);
                        }
                        if (resultList.length == 2){
                            if (userID.equals("playerTwo")){
                                startNewRound.setEnabled(true);
                            }else startNewRound.setEnabled(false);
                        }
                        if (resultList.length == 3) {
                            System.out.println("Runda ett är färdigspelad");
                            player1round1 = Integer.parseInt(resultList[1].trim());
                            player2round1 = Integer.parseInt(resultList[2].trim());

                            if (correctGuesses == player1round1) {
                                p2r1.setText(String.valueOf(player2round1));
                            } else
                                p2r1.setText(String.valueOf(player1round1));
                            if (userID.equals("playerOne")){
                                startNewRound.setEnabled(false);
                            }
                            if (userID.equals("playerTwo")){
                                startNewRound.setEnabled(true);
                            }
                        }
                        if (resultList.length == 4){
                            if (userID.equals("playerOne")){
                                startNewRound.setEnabled(true);
                            }
                            if (userID.equals("playerTwo")){
                                startNewRound.setEnabled(false);
                            }
                        }
                        if (resultList.length == 5) {
                            System.out.println("Runda två är färdigspelad");
                            player1round2 = Integer.parseInt(resultList[4].trim());
                            player2round2 = Integer.parseInt(resultList[3].trim());
                            if (amountOfRounds == 2){
                                int endScore1 = player1round1+player1round2;
                                int endScore2 = player2round1+player2round2;
                                totalScore(endScore1, endScore2, player1round2, p2r2, player2round2);
                                break;
                            }
                            if (correctGuesses == player1round2) {
                                p2r2.setText(String.valueOf(player2round2));
                            } else
                                p2r2.setText(String.valueOf(player1round2));
                            if (userID.equals("playerOne")){
                                startNewRound.setEnabled(true);
                            }
                            if (userID.equals("playerTwo")){
                                startNewRound.setEnabled(false);
                            }
                        }
                        if (resultList.length == 6){
                            if (userID.equals("playerTwo")){
                                startNewRound.setEnabled(true);
                            }
                            else startNewRound.setEnabled(false);
                        }
                        if (resultList.length == 7) {
                            System.out.println("Matchen är färdigspelad!");
                            startNewRound.setEnabled(false);
                            player1round3 = Integer.parseInt(resultList[5].trim());
                            player2round3 = Integer.parseInt(resultList[6].trim());
                            int endScore1 = player1round1+player1round2+player1round3;
                            int endScore2 = player2round1+player2round2+player2round3;
                            totalScore(endScore1, endScore2, player1round3, p2r3, player2round3);
                            break;
                        }
                    }
                }
            }
        }finally {
            socket.close();
        }
    }

    private void totalScore(int endScore1, int endScore2, int player1round2, JTextField p2r2, int player2round2) {
        if (correctGuesses == player1round2) {
            p2r2.setText(String.valueOf(player2round2));
            p2result.setText(String.valueOf(endScore2));
        } else
            p2result.setText(String.valueOf(endScore1));
        p2r2.setText(String.valueOf(player1round2));
        endGame(endScore1, endScore2);
    }

    private void endGame(int endScore1, int endScore2) {
        if (endScore1 > endScore2) {
            if (endScore1 == score) {
                p1result.setText(String.valueOf(score));
                p2result.setText(String.valueOf(endScore2));
                frame.setTitle("WON");
                startNewRound.setText("You've won");
            } else {
                p1result.setText(String.valueOf(endScore2));
                p2result.setText(String.valueOf(endScore1));
                frame.setTitle("LOST");
                startNewRound.setText("You've lost");
            }
        } else if (endScore1 < endScore2) {
            if (endScore1 == score) {
                frame.setTitle("LOST");
                startNewRound.setText("You've lost'");
            } else {
                frame.setTitle("WON");
                startNewRound.setText("You've won!");
            }
        } else {
            System.out.println("Draw");
            startNewRound.setText("It's a Draw");
        }
    }

    private void displayResult(String message) {
        JOptionPane.showMessageDialog(null, message);
    }

    private void newRound() {
        cardLayout.show(cardPanel, "newRound");

        newRound.setLayout(null);
        newRound.setSize(500, 750);
        newRound.setOpaque(false);

        players.setBounds(0, 0, 500, 220);
        players.setOpaque(false);
        players.setLayout(null);

        playerOneIcon.setBounds(35, 20, 150, 150);
        if (userID.equals("playerOne"))
            playerOneIcon.setIcon(reindeer);
        else
            playerOneIcon.setIcon(skull);
        playerOneName.setBounds(35, 170, 150, 30);
        playerOneName.setHorizontalAlignment(JLabel.CENTER);
        playerOneName.setText(userID);
        playerOneName.setFont(new Font("Dialog", Font.BOLD, 20));

        versus.setBounds(230, 80, 40, 20);
        versus.setFont(new Font("Dialog", Font.BOLD, 70));

        playerTwoIcon.setBounds(300, 20, 150, 150);
        if (userID.equals("playerOne"))
            playerTwoIcon.setIcon(skull);
        else
            playerTwoIcon.setIcon(reindeer);
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
        GUIPerRound(round1, r1, p1r1, p2r1);
        round1.setOpaque(false);
        round1.add(r1);
        round1.add(p1r1);
        round1.add(p2r1);

        round2.setBounds(0, 320, 500, 100);
        GUIPerRound(round2, r2, p1r2, p2r2);
        round2.add(r2);
        round2.add(p1r2);
        round2.add(p2r2);
        round2.setOpaque(false);

        round3.setBounds(0, 420, 500, 100);
        GUIPerRound(round3, r3, p1r3, p2r3);
        round3.add(r3);
        round3.add(p1r3);
        round3.add(p2r3);
        round3.setOpaque(false);

        result.setBounds(0, 520, 500, 180);
        result.setLayout(null);
        result.setOpaque(false);

        startNewRound.setBounds(165, 80, 150, 50);
        startNewRound.setBackground(Color.WHITE);
        startNewRound.setEnabled(false);
        startNewRound.addActionListener(e -> {
            index = 0;
            index2 = 5;
            index3 = 10;
            correctGuesses = 0;
            try {
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

    private void GUIPerRound(JPanel round1, JLabel r1, JTextField p1r1, JTextField p2r1) {
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
    }

    private void createQuestions(List<Question> questionList) {
        this.q = questionList;
    }

    public void nextQ() throws IOException {

        cardLayout.show(cardPanel, "game");

        if (index == amountOfQuestions) {
            out.writeObject("ROUND_OVER " + correctGuesses);
            startNewRound.setEnabled(false);
            if (round == 1) {
                score = correctGuesses;
                p1r1.setText(String.valueOf(correctGuesses));
                round++;
                newRound();
            } else if (round == 2) {
                score += correctGuesses;
                p1r2.setText(String.valueOf(correctGuesses));
                round++;
                newRound();
            } else if (round == 3) {
                startNewRound.setText("Slutspelat");
                startNewRound.setEnabled(false);
                score += correctGuesses;
                p1r3.setText(String.valueOf(correctGuesses));
                round++;
                newRound();
            } else if (round > 3) {
                p1result.setText(String.valueOf(score));
                displayResult("Hej");
            }
        }

        if (round == 1) {
            if (index < 5) {
                roundGUISetting(index);
            }
        } else if (round == 2){
            if (index2 < 10) {
                roundGUISetting(index2);
            }

        } else if (round == 3) {
            if (index3 < 15) {
                roundGUISetting(index3);
            }
        }
    }

    private void roundGUISetting(int index) {
        category.setText(q.get(index).getCategory());
        questionArea.setText(q.get(index).getQuestion());

        questionArea.setAlignmentX(JTextArea.CENTER_ALIGNMENT);
        category.setHorizontalAlignment(JTextField.CENTER);

        b1.setBackground(Color.DARK_GRAY);
        b1.setForeground(Color.WHITE);
        b2.setBackground(Color.DARK_GRAY);
        b2.setForeground(Color.WHITE);
        b3.setBackground(Color.DARK_GRAY);
        b3.setForeground(Color.WHITE);
        b4.setBackground(Color.DARK_GRAY);
        b4.setForeground(Color.WHITE);

        b1.setText(q.get(index).getAnswers().get(0));
        b1.setEnabled(true);
        b2.setText(q.get(index).getAnswers().get(1));
        b2.setEnabled(true);
        b3.setText(q.get(index).getAnswers().get(2));
        b3.setEnabled(true);
        b4.setText(q.get(index).getAnswers().get(3));
        b4.setEnabled(true);
    }

    public void showAnswer() {
        b1.setEnabled(false);
        b2.setEnabled(false);
        b3.setEnabled(false);
        b4.setEnabled(false);

        Timer pause = new Timer(200, e -> {

            b1.setEnabled(true);
            b2.setEnabled(true);
            b3.setEnabled(true);
            b4.setEnabled(true);

            index++;
            index2++;
            index3++;

            try {
                nextQ();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });

        pause.setRepeats(false);
        pause.start();
    }


    public void changeBackground(){
        cardLayout.show(cardPanel, "colorSwitch");

        colorSwitch.setLayout(null);
        colorSwitch.setSize(500, 750);
        colorSwitch.setOpaque(false);

        rod.setBounds(10, 10, 100, 20);
        r.setBounds(5, 30, 380, 20);
        r.setMajorTickSpacing(50);
        r.setPaintTicks(true);
        r.addChangeListener(e -> changeColour());

        gron.setBounds(10, 60, 100, 20);
        g.setBounds(5, 80, 380, 20);
        g.setMajorTickSpacing(50);
        g.setPaintTicks(true);
        g.addChangeListener(e -> changeColour());

        bla.setBounds(10, 110, 100, 20);
        b.setBounds(5, 130, 380, 20);
        b.setMajorTickSpacing(50);
        b.setPaintTicks(true);
        b.addChangeListener(e -> changeColour());

        backToGame.setBounds(10, 200, 150, 50);
        backToGame.addActionListener(e -> {
            cardLayout.show(cardPanel, "newRound");
        });
        backToGame.setText("Tillbaka");

        colorSwitch.add(rod); colorSwitch.add(gron); colorSwitch.add(bla);
        colorSwitch.add(r); colorSwitch.add(g);colorSwitch.add(b);
        colorSwitch.add(backToGame);

        }

        public void changeColour(){

            int valueR = r.getValue();
            int valueG = g.getValue();
            int valueB = b.getValue();

            rod.setText("Rött: "+valueR);
            gron.setText("Grönt: "+valueG);
            bla.setText("Blått: "+valueB);

            bgColor = new Color(valueR, valueG, valueB);

            frame.getContentPane().setBackground(bgColor);

            bg.setBgColor(bgColor);
        }


    @Override
    public void actionPerformed(ActionEvent e) {
        b1.setEnabled(false);
        b2.setEnabled(false);
        b3.setEnabled(false);
        b4.setEnabled(false);

        JButton src = (JButton) e.getSource();

        String svar = src.getText();

        if (svar.equals(q.get(index).getCorrectanswear()) || svar.equals(q.get(index2).getCorrectanswear())
                || svar.equals(q.get(index3).getCorrectanswear()) ) {
            src.setBackground(Color.GREEN);
            correctGuesses++;
        } else {
            src.setBackground(Color.RED);
        }
        showAnswer();
    }

    /**
     * Runs the client as an application.
     */
    public static void main(String[] args) throws Exception {

        String serverAddress = (args.length == 0) ? "localhost" : args[1];
        Client client = new Client(serverAddress);
        client.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        client.frame.setSize(500, 750);
        client.frame.getContentPane().setBackground(Color.GRAY);
        client.frame.setVisible(true);
        client.frame.setResizable(false);
        client.play();
    }
}
