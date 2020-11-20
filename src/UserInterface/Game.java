package UserInterface;

import UserInterface.Misc.RoundJTextField;
import UserInterface.Misc.RoundedBorder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Axel Jeansson
 * Date: 2020-11-18
 * Time: 15:33
 * Project: Quizkampen
 * Copyright: MIT
 */
public class Game extends JFrame implements ActionListener {
    //----------------------------------------------------------

    //Värdelöst

    private String[] questions = {"Vad heter vår lärare i OOP?", "Vad heter skolan?", "Vilken dag är bäst?", "Är java kul?"};


    private String[][] options = {
            {"Sigrid","Mahmud","Jonas","Carl XVI Gustaf"},
            {"Chalmers","Nackademin","Handels","Harvard"},
            {"Söndag","Tisdag","Lördag","Måndag"},
            {"Nej", "Nej","Nej","Ibland"}};



    private String[] category= {"Java OOP", "Skolor", "Dagar", "Skoj"};

    private String[] answer = {"Sigrid", "Nackademin", "Lördag", "Ibland"};


    //-----------------------------------------------------------

    private int seconds=15;
    private int index;
    private int correctGuesses;
    private int nmbrOfQs = questions.length;

//------------------------------------------------------------

    private JPanel game = new JPanel();
    private JPanel postRound = new JPanel();
    private JPanel newRound = new JPanel();

    Dimension size = new Dimension(500, 700);
    JTextField qArea = new RoundJTextField(15);
    Color textfieldColor = new Color(223, 228, 228);
    JTextField subject = new JTextField("Ämne");
    JTextField results = new RoundJTextField(20);

    //Buttons
    JButton button1 = new JButton();
    JButton button2 = new JButton();
    JButton button3 = new JButton();
    JButton button4 = new JButton();
    JButton resign = new JButton("Ge upp");
    JButton nextQ = new JButton("Nästa fråga");

    //Label for time remaining
    JLabel timeLeft = new JLabel();

    Font font = new Font("Dialog", Font.BOLD, 20);
    Font font2 = new Font("Dialog", Font.PLAIN, 10);


    Color buttonColor = new Color(74, 74, 74);

    JButton back = new JButton();

    GameTest gt;

    private CardLayout cardLayout;
    private JPanel cardPanel;

    public Game() {

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        cardPanel.setOpaque(false);

        cardPanel.add(game, "game");
        cardPanel.add(postRound, "postGame");
        cardPanel.add(newRound, "newRound");

        game.setSize(size);
        game.setLayout(null);
        game.setFocusable(true);
        game.setOpaque(false);

        qArea.setBounds(25, 10, 425, 300);
        qArea.setBackground(textfieldColor);
        qArea.setForeground(Color.BLACK);
        qArea.setFont(font);
        qArea.setEditable(false);
        qArea.add(subject);

        subject.setBounds(100, 0, 225, 60);
        subject.setBackground(Color.RED);
        subject.setBorder(new RoundedBorder(10));
        subject.setFont(font);

        button1.setBounds(25, 325, 200, 100);
        button1.setBorder(new RoundedBorder(10));
        button1.setFont(font);
        button1.setFocusable(false);
        button1.addActionListener(this);

        button2.setBounds(250, 325, 200, 100);
        button2.setBorder(new RoundedBorder(10));
        button2.setFont(font);
        button2.setFocusable(false);
        button2.addActionListener(this);

        button3.setBounds(25, 450, 200, 100);
        button3.setBorder(new RoundedBorder(10));
        button3.setFont(font);
        button3.setFocusable(false);
        button3.addActionListener(this);

        button4.setBounds(250, 450, 200, 100);
        button4.setBorder(new RoundedBorder(10));
        button4.setFont(font);
        button4.setFocusable(false);
        button4.addActionListener(this);

        nextQ.setBounds(25, 575, 100, 50);
        nextQ.setFont(font2);
        nextQ.setEnabled(false);
        nextQ.setFocusable(false);
        nextQ.addActionListener(e -> nextQ());

        resign.setBounds(150, 575, 100, 50);
        resign.setFont(font2);
        resign.addActionListener(e -> postRound());

        timeLeft.setBounds(400, 575, 50, 50);
        timeLeft.setBackground(textfieldColor);
        timeLeft.setForeground(Color.BLACK);
        timeLeft.setFont(font2);
        timeLeft.setBorder(new RoundedBorder(10));
        timeLeft.setOpaque(true);
        timeLeft.setHorizontalAlignment(JTextField.CENTER);
        timeLeft.setText(String.valueOf(seconds));


        game.add(timeLeft);
        game.add(button1);
        game.add(button2);
        game.add(button3);
        game.add(button4);
        game.add(qArea);
        game.add(nextQ);
        game.add(resign);

        newRoundPanel();


        createQuestions();


    }

    private void newRoundPanel(){
        cardLayout.show(cardPanel, "newRound");
        JButton startNewRound = new JButton("Starta nästa runda");
        newRound.setLayout(null);
        newRound.setSize(500, 700);
        newRound.setOpaque(false);

        startNewRound.setSize(200, 100);
        startNewRound.addActionListener(e -> {
            index = 0;
            correctGuesses = 0;
            nextQ();
        });
        startNewRound.setOpaque(false);

        newRound.add(startNewRound);
    }


    public void createQuestions(){
        gt = new GameTest();
        gt.setCategory("Matte");
        gt.setQuestion("Vad blir 1+1?");
        gt.setOption1("1");
        gt.setOption2("2");
        gt.setOption3("3");
        gt.setOption4("11");
        gt.setAnswer("2");
    }

    public void nextQ() {
        cardLayout.show(cardPanel,"game");

        if(index>= nmbrOfQs) {
            System.out.println("Slut");
            postRound();
        }

        else {

            subject.setText(gt.getCategory());
            qArea.setText(gt.getQuestion());
            //qArea.setText(questions[index]);
            qArea.setHorizontalAlignment(JTextField.CENTER);
            subject.setHorizontalAlignment(JTextField.CENTER);

            button1.setBackground(buttonColor); button1.setForeground(Color.WHITE);
            button2.setBackground(buttonColor); button2.setForeground(Color.WHITE);
            button3.setBackground(buttonColor); button3.setForeground(Color.WHITE);
            button4.setBackground(buttonColor); button4.setForeground(Color.WHITE);



            button1.setText(gt.getOption1());
            button2.setText(gt.getOption2());
            button3.setText(gt.getOption3());
            button4.setText(gt.getOption4());

             /*
            button1.setText(options[index][0]);
            button2.setText(options[index][1]);
            button3.setText(options[index][2]);
            button4.setText(options[index][3]);

              */

            timer.start();

        }

    }

    public void postRound(){
        int score = correctGuesses;
        cardLayout.show(cardPanel, "postGame");


        postRound.setLayout(null);
        postRound.setSize(500, 700);
        postRound.setOpaque(false);

        results.setBounds(15, 10, 450, 200);
        results.setText("Din score:\n " + score );
        results.setEditable(false);
        results.setHorizontalAlignment(JTextField.CENTER);
        results.setFont(new Font("Dialog", Font.BOLD, 30));
        results.setBorder(new RoundedBorder(10));

        back.setBounds(15, 580, 100, 50);
        back.setText("Tillbaka");
        back.addActionListener(e -> {
            index = 0;
            correctGuesses = 0;
            newRoundPanel();
        });


        postRound.add(results);
        postRound.add(back);
    }


    Timer timer = new Timer(1500, e -> {

        seconds--;

        timeLeft.setText(String.valueOf(seconds));

        if(seconds<=0) {

            showAnswer();

        }

    });


    @Override

    public void actionPerformed(ActionEvent e) {

        System.out.println(index);
        System.out.println(correctGuesses);

        button1.setEnabled(false);
        button2.setEnabled(false);
        button3.setEnabled(false);
        button4.setEnabled(false);

        JButton src = (JButton) e.getSource();
        System.out.println(src);
        String svar = src.getText();
        String rättSvar = gt.getAnswer();

        if (svar.equals(rättSvar)){
            System.out.println("Rätt");
            src.setBackground(Color.GREEN);
            correctGuesses++;
        }
        /*if (svar.equals(answer[index])){
            System.out.println("Rätt!");
            src.setBackground(Color.GREEN);
            correctGuesses++;
        }

         */

        else
        {
            System.out.println("Fel!");
            src.setBackground(Color.RED);
        }


        showAnswer();

    }

    public void showAnswer() {

        timer.stop();

        button1.setEnabled(false);
        button2.setEnabled(false);
        button3.setEnabled(false);
        button4.setEnabled(false);
        nextQ.setEnabled(true);



        Timer pause = new Timer(500, e -> {

            seconds=15;
            timeLeft.setText(String.valueOf(seconds));
            button1.setEnabled(true);
            button2.setEnabled(true);
            button3.setEnabled(true);
            button4.setEnabled(true);
            nextQ.setEnabled(false);


            index++;

            nextQ();

        });

        pause.setRepeats(false);

        pause.start();

    }

    public JPanel getGamePanel(){
        return cardPanel;
    }

}