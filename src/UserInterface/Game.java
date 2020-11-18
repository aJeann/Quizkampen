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

    private String question; // = {"Vad heter vår lärare i OOP?", "Vad heter skolan?", "Vilken dag är bäst?", "Är java kul?"};

    private String option1;
    private String option2;
    private String option3;
    private String option4;

    /*= {
            {"Sigrid","Mahmud","Jonas","Carl XVI Gustaf"},
            {"Chalmers","Nackademin","Handels","Harvard"},
            {"Söndag","Tisdag","Lördag","Måndag"},
            {"Nej", "Nej","Nej","Ibland"}};
            */


    private String category;
            //= {"Java OOP", "Skolor", "Dagar", "Skoj"};

    private String answer;
            //= {"Sigrid", "Nackademin", "Lördag", "Ibland"};

    char[] buttonOptions = {'A', 'B', 'C', 'D'};

    //-----------------------------------------------------------

    int seconds=15;
    char buttonOption;
    int index = 0;
    int correctGuesses =0;
    int nmbrOfQs = 4;

//------------------------------------------------------------

    private JPanel game = new JPanel();
    JFrame result = new JFrame();

    Dimension size = new Dimension(500, 700);
    JTextField qArea = new RoundJTextField(15);
    Color textfieldColor = new Color(223, 228, 228);
    JTextField subject = new JTextField("Ämne");

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

    Color bgColor = new Color(20, 154,235);
    Color buttonColor = new Color(74, 74, 74);

    JButton back = new JButton();



    public Game() {

        game.setSize(size);
        game.setLayout(null);
        game.setFocusable(true);

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
        game.setVisible(true);

        nextQ();

    }

    public void nextQ() {

        if(index>= nmbrOfQs) {
            postRound();
        }

        else {

            subject.setText(category);
            qArea.setText(question);
            qArea.setHorizontalAlignment(JTextField.CENTER);
            subject.setHorizontalAlignment(JTextField.CENTER);

            button1.setBackground(buttonColor); button1.setForeground(Color.WHITE);
            button2.setBackground(buttonColor); button2.setForeground(Color.WHITE);
            button3.setBackground(buttonColor); button3.setForeground(Color.WHITE);
            button4.setBackground(buttonColor); button4.setForeground(Color.WHITE);

            button1.setText(option1);
            button2.setText(option2);
            button3.setText(option3);
            button4.setText(option4);

            timer.start();

        }

    }

    public void postRound(){

        JTextField results = new RoundJTextField(20);

        result.setSize(size);
        result.setLayout(null);
        result.setSize(size);



        results.setBounds(10, 10, 260, 200);
        results.setText("Din score:\n " + correctGuesses );
        results.setEditable(false);
        results.setHorizontalAlignment(JTextField.CENTER);
        results.setFont(new Font("Dialog", Font.BOLD, 30));
        results.setBorder(new RoundedBorder(10));

        index = 0;
        correctGuesses = 0;

        back.setBounds(10, 220, 260, 100);
        back.addActionListener(e -> {
            new MainMenu();
        });
        back.setText("Huvudmeny");

        result.add(results);
        result.add(back);

        result.setVisible(true);

        //Test

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

        button1.setEnabled(false);
        button2.setEnabled(false);
        button3.setEnabled(false);
        button4.setEnabled(false);

        JButton src = (JButton) e.getSource();
        System.out.println(src);
        String svar = src.getText();

        if (svar.equals(answer)){
            correctGuesses++;
        }

        if(e.getSource() == button1) {
            buttonOption = 'A';
        }

        if(e.getSource() == button2) {
            buttonOption = 'B';
        }

        if(e.getSource() == button3) {
            buttonOption = 'C';
        }

        if(e.getSource() == button4) {
            buttonOption = 'D';
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



        if(buttonOptions[index] == 'A')
            button1.setBackground(Color.GREEN);
        else if (buttonOptions[index] != 'A')
            button1.setBackground(Color.RED);

        if(buttonOptions[index] == 'B')
            button2.setBackground(Color.GREEN);
        else if (buttonOptions[index] != 'B')
            button1.setBackground(Color.RED);

        if(buttonOptions[index] == 'C')
            button3.setBackground(Color.GREEN);
        else if (buttonOptions[index] != 'C')
            button1.setBackground(Color.RED);

        if(buttonOptions[index] == 'D')
            button4.setBackground(Color.GREEN);
        else if (buttonOptions[index] != 'D')
            button1.setBackground(Color.RED);

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
        return game;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getOption1() {
        return option1;
    }

    public void setOption1(String option1) {
        this.option1 = option1;
    }

    public String getOption2() {
        return option2;
    }

    public void setOption2(String option2) {
        this.option2 = option2;
    }

    public String getOption3() {
        return option3;
    }

    public void setOption3(String option3) {
        this.option3 = option3;
    }

    public String getOption4() {
        return option4;
    }

    public void setOption4(String option4) {
        this.option4 = option4;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}