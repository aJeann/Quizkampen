package UserInterface;

import Config.Player;

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

    //Värdelöst

    String[] questions = {"Vad heter vår lärare i OOP?", "Vad heter skolan?", "Vilken dag är bäst?", "Är java kul?"};

    String[][] options = {
            {"Sigrid","Mahmud","Jonas","Carl XVI Gustaf"},
            {"Chalmers","Nackademin","Handels","Harvard"},
            {"Söndag","Tisdag","Lördag","Måndag"},
            {"Nej", "Nej","Nej","Ibland"}};

    String[] categories = {"Java OOP", "Skolor", "Dagar", "Skoj"};

    String[] answers = {"Sigrid", "Nackademin", "Lördag", "Ibland"};

    char[] buttonOptions = {'A', 'B', 'C', 'D'};

    //-----------------------------------------------------------

    int seconds=15;
    char buttonOption;
    int index;
    int correctGuesses =0;
    int nmbrOfQs = questions.length;

//------------------------------------------------------------

    JFrame menu = new JFrame();
    JFrame game = new JFrame();
    JFrame settings = new JFrame();
    Dimension size = new Dimension(500, 700);
    JTextField qArea = new RoundJTextField(15);
    JTextField subject = new JTextField("Ämne");
    JButton button1 = new JButton();
    JButton button2 = new JButton();
    JButton button3 = new JButton();
    JButton button4 = new JButton();
    JLabel timeLeft = new JLabel();
    JButton resign = new JButton("Ge upp");
    JButton nextQ = new JButton("Nästa fråga");
    Font font = new Font("Dialog", Font.BOLD, 20);
    Font font2 = new Font("Dialog", Font.PLAIN, 10);
    Color bgColor = new Color(20, 154,235);
    Color bColor = new Color(237, 228, 223);
    Color textfieldColor = new Color(223, 228, 228);
    Color buttonColor = new Color(74, 74, 74);
    JButton changeBG = new JButton("Byt bakgrundsfärg");

    //----------------------------------------

    JSlider g = new JSlider(JSlider.HORIZONTAL, 0, 250, 0);
    JSlider b = new JSlider(JSlider.HORIZONTAL, 0, 250, 0);
    JSlider r = new JSlider(JSlider.HORIZONTAL, 0, 250, 0);

    JLabel rod = new JLabel("Rött = " +r.getValue());
    JLabel gron = new JLabel("Grönt = "+g.getValue());
    JLabel bla = new JLabel("Blått = "+b.getValue());

    //----------------------------------------------------

    JFrame result = new JFrame("Resultat");
    JButton back = new JButton();
    JFrame colorSwitch = new JFrame();

    //------------------------------------------------
    JFrame player = new JFrame("Spelarinformation");
    ImageIcon avatar = new ImageIcon("images\\traveler.png");
    ImageIcon avatar2 = new ImageIcon("images\\hiker.png");
    ImageIcon avatar3 = new ImageIcon("images\\skull.png");
    ImageIcon avatar4 = new ImageIcon("images\\reindeer.png");
    //Test
    Player p1 = new Player("Axel", 0, avatar);
    JFrame avi = new JFrame();
    JButton pPic = new JButton();

    public GUI(){
        mainMenu();
    }

    public void mainMenu(){
        result.dispose();
        game.dispose();
        settings.dispose();
        player.dispose();
        ImageIcon header = new ImageIcon("images\\qh.png");
        JLabel quizkampen = new JLabel(header);
        JButton newGame = new JButton();
        JButton player = new JButton();
        JButton settings = new JButton();

        menu.setDefaultCloseOperation(EXIT_ON_CLOSE);
        menu.setSize(size);
        menu.getContentPane().setBackground(bgColor);
        menu.setTitle("Qwizkampen!");
        menu.setLayout(null);
        menu.setLocationRelativeTo(null);
        menu.setResizable(false);

        quizkampen.setBounds(-8, 0, 500, 100);
        quizkampen.setFocusable(false);

        newGame.setText("Ny match");
        newGame.setBounds(125, 150, 250, 80);
        newGame.setBorder(new RoundedBorder(10));
        newGame.setFont(font);
        newGame.setBackground(bColor);
        newGame.addActionListener(e -> game());

        player.setText("Spelarstatistik");
        player.setBounds(125, 250, 250, 80 );
        player.setBorder(new RoundedBorder(10));
        player.setFont(font);
        player.setBackground(bColor);
        player.addActionListener(e -> playerInfo());

        settings.setText("Inställningar");
        settings.setBounds(125, 350, 250, 80);
        settings.setBorder(new RoundedBorder(10));
        settings.setFont(font);
        settings.setBackground(bColor);
        settings.addActionListener(e -> changeSettings());


        menu.add(quizkampen);
        menu.add(newGame); menu.add(player); menu.add(settings);

        menu.setVisible(true);

    }

    public void game() {
        menu.dispose();

        game.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        game.setSize(size);
        game.getContentPane().setBackground(bgColor);
        game.setTitle("Quizkampen");
        game.setLayout(null);
        game.setFocusable(true);
        game.setLocationRelativeTo(null);
        game.setResizable(false);

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

    public void playerInfo(){
        menu.dispose();
        avi.dispose();


        JTextField name = new RoundJTextField(15);
        JTextField score = new RoundJTextField(15);
        JTextField games = new RoundJTextField(15);
        JTextField wins = new RoundJTextField(15);

        player.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        player.setSize(size);
        player.getContentPane().setBackground(bgColor);
        player.setLayout(null);
        player.setLocationRelativeTo(null);
        player.setResizable(false);

        pPic.setBounds(10, 10, 200, 200);
        pPic.setIcon(p1.getAvatar());
        pPic.setBorder(new RoundedBorder(10));
        pPic.addActionListener(e -> changeAvi());

        name.setBounds(220, 10, 200, 30);
        name.setFont(font2);
        name.setText("Användarnamn: " + p1.getUsername());
        name.setEditable(false);

        back.setBounds(10, 580, 150, 50);
        back.addActionListener(e -> mainMenu());
        back.setText("Huvudmeny");

        score.setBounds(220, 60, 200, 30);
        score.setFont(font2);
        score.setText("User score: " + p1.getUserScore());
        score.setEditable(false);

        games.setBounds(220, 110, 200, 30);
        games.setFont(font2);
        games.setText("Antal matcher spelade: " + p1.getMatchesPlayed());
        games.setEditable(false);

        wins.setBounds(220, 160, 200, 30);
        wins.setFont(font2);
        wins.setText("Antal vinster: " + p1.getWins());
        wins.setEditable(false);


        player.add(pPic);
        player.add(name);
        player.add(back);
        player.add(score);
        player.add(games);
        player.add(wins);

        player.setVisible(true);
    }

    public void changeSettings(){
        colorSwitch.dispose();
        ImageIcon arrow = new ImageIcon("Images/arrow.jpg");
        int width = arrow.getIconWidth();
        int height = arrow.getIconHeight();
        JButton bSettings = new JButton(arrow);
        JTextField tSettings = new JTextField("Inställningar");
        menu.dispose();

        settings.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        settings.setSize(size);
        settings.getContentPane().setBackground(bgColor);
        settings.setLayout(null);
        settings.setLocationRelativeTo(null);
        settings.setResizable(false);

        bSettings.setBounds(0, 0, width, height);
        bSettings.addActionListener(e -> mainMenu());

        tSettings.setBounds(width, 0, size.width-width, height);
        tSettings.setFont(new Font("Dialog", Font.BOLD, 40));
        tSettings.setBackground(Color.DARK_GRAY);
        tSettings.setForeground(Color.WHITE);
        tSettings.setHorizontalAlignment(JTextField.CENTER);
        tSettings.setEditable(false);

        changeBG.setBounds(125, 150, 250, 80);
        changeBG.setFont(font);
        changeBG.setBackground(bColor);
        changeBG.addActionListener(e -> changeBackground());


        settings.add(changeBG);
        settings.add(bSettings);
        settings.add(tSettings);

        settings.setVisible(true);

    }


    public void changeBackground(){
        JButton back2 = new JButton();
        settings.dispose();

        colorSwitch.getContentPane().setBackground(bgColor);
        colorSwitch.setSize(400, 300);
        colorSwitch.setLayout(null);
        colorSwitch.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        colorSwitch.setResizable(false);
        colorSwitch.setLocationRelativeTo(null);


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

        back2.setBounds(10, 200, 150, 50);
        back2.addActionListener(e -> changeSettings());
        back2.setText("Tillbaka");

        colorSwitch.add(rod); colorSwitch.add(gron); colorSwitch.add(bla);
        colorSwitch.add(r); colorSwitch.add(g);colorSwitch.add(b);
        colorSwitch.add(back2);


        colorSwitch.setVisible(true);
    }

    public void changeColour(){

        int valueR = r.getValue();
        int valueG = g.getValue();
        int valueB = b.getValue();

        rod.setText("Rött: "+valueR);
        gron.setText("Grönt: "+valueG);
        bla.setText("Blått: "+valueB);

        bgColor = new Color(valueR, valueG, valueB);

        colorSwitch.getContentPane().setBackground(bgColor);


    }

    public void changeAvi(){
        JButton avi1 = new JButton(avatar);
        JButton avi2 = new JButton(avatar2);
        JButton avi3 = new JButton(avatar3);
        JButton avi4 = new JButton(avatar4);
        JButton back3  = new JButton("Tillbaka");
        player.dispose();

        avi.setUndecorated(true);
        avi.setSize(500, 500);
        avi.setLayout(null);
        avi.setSize(440, 500);
        avi.setLocationRelativeTo(null);
        avi.getContentPane().setBackground(bgColor);
        avi.setResizable(false);
        avi.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        avi1.setBounds(10,10, 200, 200);
        avi1.addActionListener(ae -> {
            try {
                p1.setAvatar(avatar);
                pPic.setIcon(p1.getAvatar());
            } catch (IllegalComponentStateException e) {
                e.printStackTrace();
            }
        });

        avi2.setBounds(220, 10, 200, 200);
        avi2.addActionListener(ae -> {
            try {
                p1.setAvatar(avatar2);
                pPic.setIcon(p1.getAvatar());
            } catch (IllegalComponentStateException e) {
                e.printStackTrace();
            }
        });

        avi3.setBounds(10, 220, 200, 200);
        avi3.addActionListener(ae -> {
            try {
                p1.setAvatar(avatar3);
                pPic.setIcon(p1.getAvatar());
            } catch (IllegalComponentStateException e) {
                e.printStackTrace();
            }
        });


                avi4.setBounds(220, 220, 200, 200);
        avi4.addActionListener(ae -> {
            try{
                p1.setAvatar(avatar4);
                pPic.setIcon(p1.getAvatar());
            }catch (IllegalComponentStateException e){
                e.printStackTrace();
            }
        });

                back3.setBounds(10, 430, 150, 50);
        back3.addActionListener(e -> playerInfo());

        avi.add(avi1); avi.add(avi2); avi.add(avi3); avi.add(avi4);
        avi.add(back3);

        int valueR = r.getValue();
        int valueG = g.getValue();
        int valueB = b.getValue();

        avi.setVisible(true);

    }



    public void nextQ() {

        result.dispose();
        if(index>= nmbrOfQs) {
            postRound();
        }

        else {

            subject.setText(categories[index]);
            qArea.setText(questions[index]);
            qArea.setHorizontalAlignment(JTextField.CENTER);
            subject.setHorizontalAlignment(JTextField.CENTER);

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

    public void postRound(){
        game.dispose();

        JTextField results = new RoundJTextField(20);

        result.setSize(300, 400);
        result.setLayout(null);
        result.setLocationRelativeTo(null);
        result.getContentPane().setBackground(bgColor);
        result.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        result.setResizable(false); result.setFocusable(false);


        results.setBounds(10, 10, 260, 200);
        results.setText("Din score:\n " + correctGuesses );
        results.setEditable(false);
        results.setHorizontalAlignment(JTextField.CENTER);
        results.setFont(new Font("Dialog", Font.BOLD, 30));
        results.setBorder(new RoundedBorder(10));

        index = 0;
        correctGuesses = 0;

        back.setBounds(10, 220, 260, 100);
        back.addActionListener(e -> mainMenu());
        back.setText("Huvudmeny");

        result.add(results);
        result.add(back);

        result.setVisible(true);

        //Test
        p1.setMatchesPlayed(p1.getMatchesPlayed()+1);
        p1.setWins(p1.getWins()+1);
        p1.setUserScore(p1.getUserScore()+10);

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
        String svar = src.getText();

        if (svar.equals(answers[index])){
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

        if(buttonOptions[index] == 'B')
            button2.setBackground(Color.GREEN);

        if(buttonOptions[index] == 'C')
            button3.setBackground(Color.GREEN);

        if(buttonOptions[index] == 'D')
            button4.setBackground(Color.GREEN);


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



    public static void main(String[] args) {
        GUI g = new GUI();
    }

}
