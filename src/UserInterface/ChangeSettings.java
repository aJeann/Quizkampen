package UserInterface;

import UserInterface.Misc.BackgroundColor;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Axel Jeansson
 * Date: 2020-11-18
 * Time: 19:04
 * Project: Quizkampen
 * Copyright: MIT
 */

public class ChangeSettings {
    private JPanel settings = new JPanel();
    JButton changeBG = new JButton("Byt bakgrundsfärg");
    Font font = new Font("Dialog", Font.BOLD, 20);
    BackgroundColor bg = new BackgroundColor();
    Color bColor = new Color(237, 228, 223);
    Dimension size = new Dimension(500, 700);

    public ChangeSettings(){
        ImageIcon arrow = new ImageIcon("Images/arrow.jpg");
        int width = arrow.getIconWidth();
        int height = arrow.getIconHeight();
        JButton bSettings = new JButton(arrow);
        JTextField tSettings = new JTextField("Inställningar");

        settings.setSize(size);
        settings.setLayout(null);
        settings.setOpaque(false);


        bSettings.setBounds(0, 0, width, height);
        bSettings.addActionListener(e -> {
            new MainMenu();

        });

        tSettings.setBounds(width, 0, size.width-width, height);
        tSettings.setFont(new Font("Dialog", Font.BOLD, 40));
        tSettings.setBackground(Color.DARK_GRAY);
        tSettings.setForeground(Color.WHITE);
        tSettings.setHorizontalAlignment(JTextField.CENTER);
        tSettings.setEditable(false);

        changeBG.setBounds(125, 150, 250, 80);
        changeBG.setFont(font);
        changeBG.setBackground(bColor);
        changeBG.addActionListener(e -> {
            new ChangeBackground();


        });

        settings.add(changeBG);
        settings.add(bSettings);
        settings.add(tSettings);

        settings.setVisible(true);

    }

    public JPanel getSettingsPanel(){
        return settings;
    }

}
