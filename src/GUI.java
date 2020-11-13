import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * Created by Axel Jeansson
 * Date: 2020-11-12
 * Time: 13:39
 * Project: Quizkampen
 * Copyright: MIT
 */
public class GUI extends JFrame implements ActionListener {


    //----------------------------------------------------------

    String[] questions = {"Vad heter vår lärare i OOP?", "Vad heter skolan?", "Vilken dag är bäst?", "Är java kul?"};

    String[][] options = {
            {"Sigrid","Mahmud","Jonas","Carl XVI Gustaf"},
            {"Chalmers","Nackademin","Handels","Harvard"},
            {"Söndag","Tisdag","Lördag","Måndag"},
            {"Nej", "Nej","Nej","Nej"}};

    char[] answers = {'A', 'B', 'C', 'D'};

    //-----------------------------------------------------------

    int seconds=15;
    char answer;
    int index;
    int correctGuesses =0;
    int nmbrOfQs = questions.length;


//------------------------------------------------------------

    JFrame frame = new JFrame();
    JTextField qArea = new RoundJTextField(15);
    JButton button1 = new JButton();
    JButton button2 = new JButton();
    JButton button3 = new JButton();
    JButton button4 = new JButton();
    JLabel timeLeft = new JLabel();
    JButton resign = new JButton("Ge upp");
    JButton nextQ = new JButton("Nästa fråga");
    Font font = new Font("Dialog", Font.BOLD, 20);
    Font font2 = new Font("Dialog", Font.PLAIN, 10);
    Color bg = new Color(20, 154,235);
    Color textfieldColor = new Color(223, 228, 228);
    Color buttonColor = new Color(74, 74, 74);
    JButton changeBG = new JButton("Byt bakgrundsfärg");


    //----------------------------------------




    public GUI(){

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500,700);
        frame.getContentPane().setBackground(bg);
        frame.setTitle("Quizkampen");
        frame.setLayout(null);
        frame.setResizable(false);

        qArea.setBounds(25,10,425,300);
        qArea.setBackground(textfieldColor);
        qArea.setForeground(Color.BLACK);
        qArea.setFont(font);
        qArea.setBorder(BorderFactory.createBevelBorder(1));
        qArea.setEditable(false);

        button1.setBounds(25,325,200,100);
        button1.setBorder(new RoundedBorder(10));
        button1.setFont(font);
        button1.setFocusable(false);
        button1.addActionListener(this);


        button2.setBounds(250,325,200,100);
        button2.setBorder(new RoundedBorder(10));
        button2.setFont(font);
        button2.setFocusable(false);
        button2.addActionListener(this);


        button3.setBounds(25,450,200,100);
        button3.setBorder(new RoundedBorder(10));
        button3.setFont(font);
        button3.setFocusable(false);
        button3.addActionListener(this);


        button4.setBounds(250,450,200,100);
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
        resign.setFocusable(false);
        resign.addActionListener(this);

        changeBG.setBounds(275, 575, 100, 50);
        changeBG.setFont(font2);
        changeBG.setFocusable(false);
        changeBG.addActionListener(e -> changeBackground());

        timeLeft.setBounds(400,575,50,50);
        timeLeft.setBackground(textfieldColor);
        timeLeft.setForeground(Color.BLACK);
        timeLeft.setFont(font2);
        timeLeft.setBorder(new RoundedBorder(10));
        timeLeft.setOpaque(true);
        timeLeft.setHorizontalAlignment(JTextField.CENTER);
        timeLeft.setText(String.valueOf(seconds));



        frame.add(timeLeft);
        frame.add(button1);
        frame.add(button2);
        frame.add(button3);
        frame.add(button4);
        frame.add(qArea);
        frame.add(nextQ);
        frame.add(resign);
        frame.add(changeBG);
        frame.setVisible(true);

        nextQ();


    }

    public void changeBackground(){
        ChangeBackgroundColor c1 =new ChangeBackgroundColor();
    }



    public void nextQ() {

        if(index>= nmbrOfQs) {
            JOptionPane.showMessageDialog(null, "Du hade " +correctGuesses + " rätt av " + questions.length + ".");
            int input = JOptionPane.showConfirmDialog(null, "Vill du start om?");
            if (input == 0){
                index = 0;
                nextQ();
            }
            else
                System.exit(0);


        }

        else {

            qArea.setText(questions[index]);
            qArea.setHorizontalAlignment(JTextField.CENTER);

            button1.setBackground(buttonColor); button1.setForeground(Color.WHITE);
            button2.setBackground(buttonColor); button2.setForeground(Color.WHITE);
            button3.setBackground(buttonColor); button3.setForeground(Color.WHITE);
            button4.setBackground(buttonColor); button4.setForeground(Color.WHITE);

            button1.setText(options[index][0]);
            button2.setText(options[index][1]);
            button3.setText(options[index][2]);
            button4.setText(options[index][3]);

            timer.start();

        }

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



        if(e.getSource() == button1) {
            answer= 'A';
            if(answer == answers[index]) {
                correctGuesses++;
            }

        }

        if(e.getSource() == button2) {
            answer= 'B';
            if(answer == answers[index]) {
                correctGuesses++;
            }

        }

        if(e.getSource() == button3) {
            answer= 'C';
            if(answer == answers[index]) {
                correctGuesses++;
            }

        }

        if(e.getSource() == button4) {
            answer= 'D';
            if(answer == answers[index]) {
                correctGuesses++;
            }

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



        if(answers[index] == 'A')
            button1.setBackground(Color.GREEN);

        if(answers[index] == 'B')
            button2.setBackground(Color.GREEN);

        if(answers[index] == 'C')
            button3.setBackground(Color.GREEN);

        if(answers[index] == 'D')
            button4.setBackground(Color.GREEN);


        Timer pause = new Timer(5000, new ActionListener() {



            @Override

            public void actionPerformed(ActionEvent e) {


                answer = ' ';
                seconds=15;
                timeLeft.setText(String.valueOf(seconds));
                button1.setEnabled(true);
                button2.setEnabled(true);
                button3.setEnabled(true);
                button4.setEnabled(true);
                nextQ.setEnabled(false);

                index++;


                nextQ();

            }

        });

        pause.setRepeats(false);

        pause.start();

    }



    public static void main(String[] args) {
        GUI g = new GUI();
    }

}
