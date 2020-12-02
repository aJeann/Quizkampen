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
 * Created by Axel Jeansson, Christoffer Grännby, Salem Koldzo, Iryna Gnatenko, Ashkan Amiri
 * Date: 2020-11-12
 * Time: 13:47
 * Project: Quizkampen
 * Copyright: MIT
 */
public class Client implements ActionListener {

    private JFrame frame = new JFrame("QuizkampenClient");
    //Panel which is used for the game rounds
    private JPanel questionPanel = new JPanel();
    private JPanel gamePanel = new JPanel();
    public JTextArea questionArea = new JTextArea("");
    private JTextField category = new RoundJTextField(15);
    private JButton buttonOption1 = new JButton();
    private JButton buttonOption2 = new JButton();
    private JButton buttonOption3 = new JButton();
    private JButton buttonOption4 = new JButton();
    private JButton changeBG = new JButton("Byt bakgrundsfärg");
    private JPanel cardPanel;
    private CardLayout cardLayout;

    List<Question> questionList;

    int finalScore;
    int round = 1;

    //Panel which is shown in between rounds while waiting for your turn
    JPanel newRound = new JPanel();
    JPanel players = new JPanel();
    JPanel round1Panel = new JPanel();
    JPanel round2Panel = new JPanel();
    JPanel round3Panel = new JPanel();
    JPanel resultPanel = new JPanel();
    ImageIcon reindeer = new ImageIcon("images\\reindeer.png");
    ImageIcon skull = new ImageIcon("images\\skull.png");
    JLabel playerOneIcon = new JLabel();
    JLabel playerOneName = new JLabel();
    JLabel playerTwoIcon = new JLabel();
    JLabel playerTwoName = new JLabel();
    JLabel versusLabel = new JLabel("-");
    JLabel round1Label = new JLabel("Runda 1");
    JLabel round2Label = new JLabel("Runda 2");
    JLabel round3Label = new JLabel("Runda 3");
    JLabel resultLabel = new JLabel("Resultat");
    JButton startNewRoundButton = new JButton("");
    JTextField player1Round1Result = new RoundJTextField(20);
    JTextField player2Round1Result = new RoundJTextField(20);
    JTextField player1Round2Result = new RoundJTextField(20);
    JTextField player2Round2Result = new RoundJTextField(20);
    JTextField player1Round3Result = new RoundJTextField(20);
    JTextField player2Round3Result = new RoundJTextField(20);
    JTextField player1FinalResult = new RoundJTextField(20);
    JTextField player2FinalResult = new RoundJTextField(20);


    //Change Background Color
    JSlider green = new JSlider(JSlider.HORIZONTAL, 0, 250, 0);
    JSlider blue = new JSlider(JSlider.HORIZONTAL, 0, 250, 0);
    JSlider red = new JSlider(JSlider.HORIZONTAL, 0, 250, 0);

    JLabel redLabel = new JLabel("Rött = " + red.getValue());
    JLabel greenLabel = new JLabel("Grönt = " + green.getValue());
    JLabel blueLabel = new JLabel("Blått = " + blue.getValue());
    JPanel colorSwitchPanel = new JPanel();
    BackgroundColor backgroundColor = new BackgroundColor();
    Color bgColor = backgroundColor.getBgColor();
    JButton backToGame = new JButton();

    //___________________________________
    private int correctGuesses;
    private int questionIndexRound1 = 0;
    private int questionIndexRound2 = 5;
    private int questionIndexRound3 = 10;

    private int amountOfRounds;
    private int amountOfQuestions;

    private static int PORT = 23325;
    private Socket socket;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    String userID = "";
    String opponentUserID = "";

    //Variables to keep track of each players score client side
    int player1round1;
    int player2round1;
    int player1round2;
    int player2round2;
    int player1round3;
    int player2round3;

    /**
     * Constructs the client by connecting to the server and creates the GUI for the main game. Adds all other panels from other methods
     * to a cardLayout.
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
        cardPanel.add(newRound, "newRound");
        cardPanel.add(colorSwitchPanel, "colorSwitch");
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

        category.setBounds(100, 0, 225, 30);
        category.setBackground(Color.WHITE);
        category.setEditable(false);
        category.setFont(new Font("Dialog", Font.BOLD, 20));

        buttonOption1.setBounds(25, 325, 200, 100);
        buttonOption1.setFont(new Font("Dialog", Font.BOLD, 10));
        buttonOption1.setFocusable(false);
        buttonOption1.setBorder(new RoundedBorder(10));
        buttonOption1.addActionListener(this);

        buttonOption2.setBounds(250, 325, 200, 100);
        buttonOption2.setFont(new Font("Dialog", Font.BOLD, 10));
        buttonOption2.setFocusable(false);
        buttonOption2.setBorder(new RoundedBorder(10));
        buttonOption2.addActionListener(this);

        buttonOption3.setBounds(25, 450, 200, 100);
        buttonOption3.setFont(new Font("Dialog", Font.BOLD, 10));
        buttonOption3.setFocusable(false);
        buttonOption3.setBorder(new RoundedBorder(10));
        buttonOption3.addActionListener(this);

        buttonOption4.setBounds(250, 450, 200, 100);
        buttonOption4.setFont(new Font("Dialog", Font.BOLD, 10));
        buttonOption4.setFocusable(false);
        buttonOption4.setBorder(new RoundedBorder(10));
        buttonOption4.addActionListener(this);

        changeBG.addActionListener(e -> changeBackground());
        changeBG.setEnabled(false);

        gamePanel.add(questionPanel);
        gamePanel.add(buttonOption1);
        gamePanel.add(buttonOption2);
        gamePanel.add(buttonOption3);
        gamePanel.add(buttonOption4);

        frame.getContentPane().add(cardPanel, BorderLayout.CENTER);
        frame.getContentPane().add(changeBG, BorderLayout.SOUTH);
    }

    /**
     * The main mehtod where the game is played. Sets userID, amountOfRounds/Questions based on input from server.
     * Each client keeps track of what round is being played as well as the opponents scored with the help of the resulstList
     * in serverSideGame. The while loop breaks when the endGame method is called.
     * @throws Exception
     */
    public void play() throws Exception {
        cardLayout.show(cardPanel, "newRound");

        Quizproperties quizSettings = new Quizproperties();
        amountOfRounds = Integer.parseInt(quizSettings.getNumberOfRounds());
        amountOfQuestions = Integer.parseInt(quizSettings.getNumberOfQuestions());

        if (amountOfRounds == 2)
            round3Panel.setVisible(false);

        try {
            startNewRoundButton.setEnabled(false);
            startNewRoundButton.setText("Starta nästa runda");
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
                        if (userID.equals("playerTwo")) {
                            startNewRoundButton.setEnabled(false);
                            out.writeObject("ROUND_OVER 0");
                        } else
                            startNewRoundButton.setEnabled(true);

                    } else if (((String) response).startsWith("RESULT")) {
                        out.writeObject("ENDROUND");
                        response = ((String) response).substring(6);
                        response = ((String) response).replace("[", "");
                        response = ((String) response).replace("]", "");

                        String[] resultList = ((String) response).split(",");
                        if (resultList.length > 6) {
                            startNewRoundButton.setText("Slutspelat");
                            startNewRoundButton.setEnabled(false);
                        }
                        if (resultList.length == 1) {
                            if (userID.equals("playerOne")) {
                                startNewRoundButton.setEnabled(true);
                            } else
                                startNewRoundButton.setEnabled(false);
                        }
                        if (resultList.length == 2) {
                            if (userID.equals("playerTwo")) {
                                startNewRoundButton.setEnabled(true);
                            } else startNewRoundButton.setEnabled(false);
                        }
                        if (resultList.length == 3) {
                            player1round1 = Integer.parseInt(resultList[1].trim());
                            player2round1 = Integer.parseInt(resultList[2].trim());

                            if (correctGuesses == player1round1) {
                                player2Round1Result.setText(String.valueOf(player2round1));
                            } else
                                player2Round1Result.setText(String.valueOf(player1round1));
                            if (userID.equals("playerOne")) {
                                startNewRoundButton.setEnabled(false);
                            }
                            if (userID.equals("playerTwo")) {
                                startNewRoundButton.setEnabled(true);
                            }
                        }
                        if (resultList.length == 4) {
                            if (amountOfRounds == 2 && userID.equals("playerTwo")){
                                startNewRoundButton.setText("Slutspelat");
                            }
                            if (userID.equals("playerOne")) {
                                startNewRoundButton.setEnabled(true);
                            }
                            if (userID.equals("playerTwo")) {
                                startNewRoundButton.setEnabled(false);
                            }
                        }
                        if (resultList.length == 5) {
                            player1round2 = Integer.parseInt(resultList[4].trim());
                            player2round2 = Integer.parseInt(resultList[3].trim());
                            if (amountOfRounds == 2) {
                                startNewRoundButton.setText("Slutspelat!");
                                startNewRoundButton.setEnabled(false);
                                if (correctGuesses == player1round2) {
                                    player2Round2Result.setText(String.valueOf(player2round2));
                                } else
                                    player2Round2Result.setText(String.valueOf(player1round2));
                                int endScore1 = player1round1 + player1round2;
                                int endScore2 = player2round1 + player2round2;
                                endGame(endScore1, endScore2);
                                break;
                            }
                            if (correctGuesses == player1round2) {
                                player2Round2Result.setText(String.valueOf(player2round2));
                            } else
                                player2Round2Result.setText(String.valueOf(player1round2));
                            if (userID.equals("playerOne")) {
                                startNewRoundButton.setEnabled(true);
                            }
                            if (userID.equals("playerTwo")) {
                                startNewRoundButton.setEnabled(false);
                            }
                        }
                        if (resultList.length == 6) {
                            if (userID.equals("playerTwo")) {
                                startNewRoundButton.setEnabled(true);
                            } else startNewRoundButton.setEnabled(false);
                        }
                        if (resultList.length == 7) {
                            startNewRoundButton.setEnabled(false);
                            player1round3 = Integer.parseInt(resultList[5].trim());
                            player2round3 = Integer.parseInt(resultList[6].trim());
                            if (correctGuesses == player1round2) {
                                player2Round3Result.setText(String.valueOf(player2round3));
                            } else
                                player2Round3Result.setText(String.valueOf(player1round3));
                            int endScore1 = player1round1 + player1round2 + player1round3;
                            int endScore2 = player2round1 + player2round2 + player2round3;
                            endGame(endScore1, endScore2);
                            break;
                        }
                    }
                }
            }
        } finally {
            socket.close();
        }
    }

    /**
     * endGame-method called when the match is finished. Checks whether the score is equal to the one that's stored locally.
     * Sets the score for the opponent on the GUI and prints out whethe you've won, lost or tied.
     * @param endScore1 score of playerOne
     * @param endScore2 score of playerTwo
     */

    private void endGame(int endScore1, int endScore2) {
        if (endScore1 > endScore2) {
            if (endScore1 == finalScore) {
                player1FinalResult.setText(String.valueOf(endScore1));
                player2FinalResult.setText(String.valueOf(endScore2));
                frame.setTitle("WON");
                startNewRoundButton.setText("You've won");
            } else {
                player1FinalResult.setText(String.valueOf(endScore2));
                player2FinalResult.setText(String.valueOf(endScore1));
                frame.setTitle("LOST");
                startNewRoundButton.setText("You've lost");
            }
        } else if (endScore1 < endScore2) {
            if (endScore1 == finalScore) {
                player1FinalResult.setText(String.valueOf(endScore1));
                player2FinalResult.setText(String.valueOf(endScore2));
                frame.setTitle("LOST");
                startNewRoundButton.setText("You've lost'");
            } else {
                player1FinalResult.setText(String.valueOf(endScore2));
                player2FinalResult.setText(String.valueOf(endScore1));
                frame.setTitle("WON");
                startNewRoundButton.setText("You've won!");
            }
        } else {
                player1FinalResult.setText(String.valueOf(endScore1));
                player2FinalResult.setText(String.valueOf(endScore2));
                frame.setTitle("DRAW");
                startNewRoundButton.setText("It's a Draw");
        }
    }


    /**
     * The screen which each player sees while they're inbetween rounds waiting for the other player to finish as well
     * as the starting screen and postgame screen. Starting newRound starts a new round when it's clicked.
     */
    private void newRound() {
        cardLayout.show(cardPanel, "newRound");
        changeBG.setEnabled(true);

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

        versusLabel.setBounds(230, 80, 40, 20);
        versusLabel.setFont(new Font("Dialog", Font.BOLD, 70));

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
        players.add(versusLabel);
        players.add(playerTwoIcon);
        players.add(playerTwoName);

        round1Panel.setBounds(0, 220, 500, 100);
        GUIPerRound(round1Panel, round1Label, player1Round1Result, player2Round1Result);
        round1Panel.setOpaque(false);
        round1Panel.add(round1Label);
        round1Panel.add(player1Round1Result);
        round1Panel.add(player2Round1Result);

        round2Panel.setBounds(0, 300, 500, 100);
        GUIPerRound(round2Panel, round2Label, player1Round2Result, player2Round2Result);
        round2Panel.add(round2Label);
        round2Panel.add(player1Round2Result);
        round2Panel.add(player2Round2Result);
        round2Panel.setOpaque(false);

        round3Panel.setBounds(0, 380, 500, 100);
        GUIPerRound(round3Panel, round3Label, player1Round3Result, player2Round3Result);
        round3Panel.add(round3Label);
        round3Panel.add(player1Round3Result);
        round3Panel.add(player2Round3Result);
        round3Panel.setOpaque(false);

        resultPanel.setBounds(0, 520, 500, 180);
        resultPanel.setLayout(null);
        resultPanel.setOpaque(false);

        startNewRoundButton.setBounds(165, 80, 150, 50);
        startNewRoundButton.setBackground(Color.WHITE);
        startNewRoundButton.setEnabled(false);
        startNewRoundButton.addActionListener(e -> {
            questionIndexRound1 = 0;
            questionIndexRound2 = 5;
            questionIndexRound3 = 10;
            correctGuesses = 0;
            try {
                nextQuestion();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });

        resultLabel.setBounds(185, 20, 115, 50);
        resultLabel.setFont(new Font("Dialog", Font.BOLD, 20));
        resultLabel.setHorizontalAlignment(JLabel.CENTER);
        resultLabel.setForeground(Color.WHITE);
        player1FinalResult.setBounds(35, 20, 150, 50);
        player1FinalResult.setEditable(false);
        player1FinalResult.setHorizontalAlignment(JTextField.CENTER);
        player2FinalResult.setBounds(300, 20, 150, 50);
        player2FinalResult.setEditable(false);
        player2FinalResult.setHorizontalAlignment(JTextField.CENTER);
        resultPanel.add(resultLabel);
        resultPanel.add(player1FinalResult);
        resultPanel.add(player2FinalResult);
        resultPanel.add(startNewRoundButton);

        newRound.add(players);
        newRound.add(round1Panel);
        newRound.add(round2Panel);
        newRound.add(round3Panel);
        newRound.add(resultPanel);
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

    /**
     * Method to create questions
     * @param questionList
     */
    private void createQuestions(List<Question> questionList) {
        this.questionList = questionList;
    }

    /**
     * Method that shows the next question for each round. Shows a new question as long as the current questionIndex
     * isn't equal to the amountOfQuestions specified in the quizsettings.properties-file.
     * It also updates the GUI for the player client-side with the score from the current round.
     */
    public void nextQuestion() throws IOException {

        cardLayout.show(cardPanel, "game");
        changeBG.setEnabled(false);

        if (questionIndexRound1 == amountOfQuestions) {
            out.writeObject("ROUND_OVER " + correctGuesses);
            startNewRoundButton.setEnabled(false);
            if (round == 1) {
                finalScore = correctGuesses;
                player1Round1Result.setText(String.valueOf(correctGuesses));
                round++;
                newRound();
            } else if (round == 2) {
                finalScore += correctGuesses;
                player1Round2Result.setText(String.valueOf(correctGuesses));
                round++;
                newRound();
            } else if (round == 3) {
                startNewRoundButton.setText("Slutspelat");
                startNewRoundButton.setEnabled(false);
                finalScore += correctGuesses;
                player1Round3Result.setText(String.valueOf(correctGuesses));
                round++;
                newRound();
            } else if (round > 3) {
                player1FinalResult.setText(String.valueOf(finalScore));
            }
        }

        if (round == 1) {
            if (questionIndexRound1 < 5) {
                roundGUISetting(questionIndexRound1);
            }
        } else if (round == 2) {
            if (questionIndexRound2 < 10) {
                roundGUISetting(questionIndexRound2);
            }

        } else if (round == 3) {
            if (questionIndexRound3 < 15) {
                roundGUISetting(questionIndexRound3);
            }
        }
    }

    /**
     * Sets one to twenty category, question and answer based on current index
     * And adds background and forground color to four answer option buttons
     */

    private void roundGUISetting(int index) {
        category.setText(questionList.get(index).getCategory());
        questionArea.setText(questionList.get(index).getQuestion());

        questionArea.setAlignmentX(JTextArea.CENTER_ALIGNMENT);
        category.setHorizontalAlignment(JTextField.CENTER);

        buttonOption1.setBackground(Color.DARK_GRAY);
        buttonOption1.setForeground(Color.WHITE);
        buttonOption2.setBackground(Color.DARK_GRAY);
        buttonOption2.setForeground(Color.WHITE);
        buttonOption3.setBackground(Color.DARK_GRAY);
        buttonOption3.setForeground(Color.WHITE);
        buttonOption4.setBackground(Color.DARK_GRAY);
        buttonOption4.setForeground(Color.WHITE);

        buttonOption1.setText(questionList.get(index).getAnswers().get(0));
        buttonOption1.setEnabled(true);
        buttonOption2.setText(questionList.get(index).getAnswers().get(1));
        buttonOption2.setEnabled(true);
        buttonOption3.setText(questionList.get(index).getAnswers().get(2));
        buttonOption3.setEnabled(true);
        buttonOption4.setText(questionList.get(index).getAnswers().get(3));
        buttonOption4.setEnabled(true);
    }


    /**
     * Method with its own panel where you can change sliders to set values for red,green and blue.
     * Depending on the values, it sets the background color of the frame to the RGB-values.
     */
    public void changeBackground() {
        cardLayout.show(cardPanel, "colorSwitch");

        colorSwitchPanel.setLayout(null);
        colorSwitchPanel.setSize(500, 750);
        colorSwitchPanel.setOpaque(false);

        redLabel.setBounds(10, 10, 100, 20);
        red.setBounds(5, 30, 380, 20);
        red.setMajorTickSpacing(50);
        red.setPaintTicks(true);
        red.addChangeListener(e -> changeColour());

        greenLabel.setBounds(10, 60, 100, 20);
        green.setBounds(5, 80, 380, 20);
        green.setMajorTickSpacing(50);
        green.setPaintTicks(true);
        green.addChangeListener(e -> changeColour());

        blueLabel.setBounds(10, 110, 100, 20);
        blue.setBounds(5, 130, 380, 20);
        blue.setMajorTickSpacing(50);
        blue.setPaintTicks(true);
        blue.addChangeListener(e -> changeColour());

        backToGame.setBounds(10, 200, 150, 50);
        backToGame.addActionListener(e -> {
            cardLayout.show(cardPanel, "newRound");
        });
        backToGame.setText("Tillbaka");

        colorSwitchPanel.add(redLabel);
        colorSwitchPanel.add(greenLabel);
        colorSwitchPanel.add(blueLabel);
        colorSwitchPanel.add(red);
        colorSwitchPanel.add(green);
        colorSwitchPanel.add(blue);
        colorSwitchPanel.add(backToGame);

    }

    /**
     * Method that changes the values of red, green and blue depending on the postion of the JSliders in changeBackground.
     * Changes background of frame.
     */

    public void changeColour() {

        int valueR = red.getValue();
        int valueG = green.getValue();
        int valueB = blue.getValue();

        redLabel.setText("Rött: " + valueR);
        greenLabel.setText("Grönt: " + valueG);
        blueLabel.setText("Blått: " + valueB);

        bgColor = new Color(valueR, valueG, valueB);

        frame.getContentPane().setBackground(bgColor);

        backgroundColor.setBgColor(bgColor);
    }

    /**
     * Activates everytime the user clicks a button. Disables the buttons as well as
     * displays whether the answer was correct or not by changing the color of the button to green or red.
     * If the answer was correct it adds one point to correctGuesses.
     * The timer tells the program to wait(sleep) for a short while so that it doesnt instantly jump to the next question.
     * After a short delay it re-enables the buttons and starts nextQuestion-method.
     * @param e
     */

    @Override
    public void actionPerformed(ActionEvent e) {
        buttonOption1.setEnabled(false);
        buttonOption2.setEnabled(false);
        buttonOption3.setEnabled(false);
        buttonOption4.setEnabled(false);

        JButton src = (JButton) e.getSource();

        String svar = src.getText();

        if (svar.equals(questionList.get(questionIndexRound1).getCorrectAnswer()) || svar.equals(questionList.get(questionIndexRound2).getCorrectAnswer())
                || svar.equals(questionList.get(questionIndexRound3).getCorrectAnswer())) {
            src.setBackground(Color.GREEN);
            correctGuesses++;
        } else {
            src.setBackground(Color.RED);
        }
        //Waits for 300ms before loading next question
        Timer wait = new Timer(300, ae -> {

            //Enables buttons again for nextQ
            buttonOption1.setEnabled(true);
            buttonOption2.setEnabled(true);
            buttonOption3.setEnabled(true);
            buttonOption4.setEnabled(true);

            questionIndexRound1++;
            questionIndexRound2++;
            questionIndexRound3++;

            try {
                nextQuestion();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });

        //Makes it so that the timer doesn't try nextQ every 300ms, but rather just once
        wait.setRepeats(false);
        wait.start();
    }

    /**
     * Runs the client as an application.
     */
    public static void main(String[] args) throws Exception {

        String serverAddress = (args.length == 0) ? "localhost" : args[1];
        Client client = new Client(serverAddress);
        client.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        client.frame.setSize(500, 750);
        client.frame.getContentPane().setBackground(new Color(34, 139, 34));
        client.frame.setVisible(true);
        client.frame.setResizable(false);
        client.play();
    }
}
