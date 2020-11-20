package UserInterface;

import Config.Player;
import UserInterface.Misc.RoundJTextField;
import UserInterface.Misc.RoundedBorder;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Axel Jeansson
 * Date: 2020-11-18
 * Time: 18:58
 * Project: Quizkampen
 * Copyright: MIT
 */
public class PlayerInfo extends JFrame {


    private JPanel player = new JPanel();
    Dimension size = new Dimension(500, 700);
    Font font2 = new Font("Dialog", Font.PLAIN, 10);
    Color bgColor = new Color(20, 154,235);
    JButton pPic = new JButton();
    JButton back = new JButton();

    ImageIcon avatar = new ImageIcon("images\\traveler.png");
    ImageIcon avatar2 = new ImageIcon("images\\hiker.png");
    ImageIcon avatar3 = new ImageIcon("images\\skull.png");
    ImageIcon avatar4 = new ImageIcon("images\\reindeer.png");

    //Test
    Player p1 = new Player("Axel", 0, avatar);
    JFrame avi = new JFrame();

    public PlayerInfo(){

        JTextField name = new RoundJTextField(15);
        JTextField score = new RoundJTextField(15);
        JTextField games = new RoundJTextField(15);
        JTextField wins = new RoundJTextField(15);

        player.setSize(size);
        player.setLayout(null);


        pPic.setBounds(10, 10, 200, 200);
        pPic.setBorder(new RoundedBorder(10));
        pPic.setIcon(p1.getAvatar());
        pPic.addActionListener(e -> {
            changeAvi();
        });

        name.setBounds(220, 10, 200, 30);
        name.setFont(font2);
        name.setText("Användarnamn: ");
        name.setEditable(false);

        back.setBounds(10, 580, 150, 50);
        back.addActionListener(e -> {
            new MainMenu();
        });
        back.setText("Huvudmeny");

        score.setBounds(220, 60, 200, 30);
        score.setFont(font2);
        score.setText("User score: ");
        score.setEditable(false);

        games.setBounds(220, 110, 200, 30);
        games.setFont(font2);
        games.setText("Antal matcher spelade: ");
        games.setEditable(false);

        wins.setBounds(220, 160, 200, 30);
        wins.setFont(font2);
        wins.setText("Antal vinster: ");
        wins.setEditable(false);

        player.add(pPic);
        player.add(name);
        player.add(back);
        player.add(score);
        player.add(games);
        player.add(wins);

        player.setVisible(true);
    }

    public JPanel getPlayerPanel(){
        return player;
    }

    public void changeAvi(){
        JButton avi1 = new JButton(avatar);
        JButton avi2 = new JButton(avatar2);
        JButton avi3 = new JButton(avatar3);
        JButton avi4 = new JButton(avatar4);
        JButton back3  = new JButton("Tillbaka");

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
        back3.addActionListener(e -> {
            new PlayerInfo();
            avi.dispose();
        });

        avi.add(avi1); avi.add(avi2); avi.add(avi3); avi.add(avi4);
        avi.add(back3);

        avi.setVisible(true);

    }


}
