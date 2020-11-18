package UserInterface;

import UserInterface.Misc.RoundedBorder;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Axel Jeansson
 * Date: 2020-11-18
 * Time: 15:14
 * Project: Quizkampen
 * Copyright: MIT
 */
public class MainMenu extends JFrame{

    private JPanel menu = new JPanel();
    ImageIcon header = new ImageIcon("images\\qh.png");
    JLabel quizkampen = new JLabel(header);
    JButton newGame = new JButton();
    JButton player = new JButton();
    JButton settings = new JButton();
    public Dimension size = new Dimension(500, 700);
    Font font = new Font("Dialog", Font.BOLD, 20);
    Color bColor = new Color(237, 228, 223);


    public MainMenu() {
        menu.setSize(size);
        menu.setLayout(null);

        quizkampen.setBounds(-8, 0, 500, 100);
        quizkampen.setFocusable(false);

        newGame.setText("Ny match");
        newGame.setBounds(125, 150, 250, 80);
        newGame.setBorder(new RoundedBorder(10));
        newGame.setFont(font);
        newGame.setBackground(bColor);
        newGame.addActionListener(e -> {
            Game g = new Game();
            g.getGamePanel();
        });

        player.setText("Spelarstatistik");
        player.setBounds(125, 250, 250, 80);
        player.setBorder(new RoundedBorder(10));
        player.setFont(font);
        player.setBackground(bColor);
        player.addActionListener(e -> {

        });

        settings.setText("Inställningar");
        settings.setBounds(125, 350, 250, 80);
        settings.setBorder(new RoundedBorder(10));
        settings.setFont(font);
        settings.setBackground(bColor);
        settings.addActionListener(e -> {

        });


        menu.add(quizkampen);
        menu.add(newGame);
        menu.add(player);
        menu.add(settings);

        menu.setVisible(true);
    }

    public JPanel getMenuPanel(){
        return menu;
    }


}
