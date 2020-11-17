package Game;

import Config.ColorPicker;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Axel Jeansson
 * Date: 2020-11-12
 * Time: 22:05
 * Project: Quizkampen
 * Copyright: MIT
 */
public class ChangeBackgroundColor extends JFrame implements ActionListener {


    JFrame colorSwitch = new JFrame();
    Color buttonColor = new Color(74, 74, 74);
    Color bg = new Color(20, 154,235);
    JRadioButton blue = new JRadioButton("Blå",false);
    JRadioButton red = new JRadioButton("Röd",false);
    JRadioButton green = new JRadioButton("Grön", false);
    JRadioButton black = new JRadioButton("Svart",false);
    JRadioButton yellow = new JRadioButton("Yellow", false);
    JRadioButton white = new JRadioButton("Vit", false);
    JLabel röd = new JLabel("R");
    JLabel grön = new JLabel("G");
    JLabel blå = new JLabel("B");
    JTextField R = new JTextField();
    JTextField G = new JTextField();
    JTextField B = new JTextField();
    ButtonGroup buttonGroup = new ButtonGroup();

    public ChangeBackgroundColor(){


        colorSwitch.getContentPane().setBackground(bg);
        colorSwitch.setSize(400, 400);
        colorSwitch.setLayout(null);
        colorSwitch.setDefaultCloseOperation(HIDE_ON_CLOSE);
        colorSwitch.setResizable(false);

        blue.setBounds(10, 10, 100, 20);
        blue.setBackground(buttonColor); blue.setForeground(Color.WHITE);
        blue.addActionListener(this::actionPerformed);

        red.setBounds(10, 40, 100, 20);
        red.setBackground(buttonColor);red.setForeground(Color.WHITE);

        green.setBounds(10, 70, 100, 20);
        green.setBackground(buttonColor);green.setForeground(Color.WHITE);

        black.setBounds(10, 100, 100, 20);
        black.setBackground(buttonColor);black.setForeground(Color.WHITE);

        yellow.setBounds(10, 130, 100, 20);
        yellow.setBackground(buttonColor);yellow.setForeground(Color.WHITE);

        white.setBounds(10, 160, 100, 20);
        white.setBackground(buttonColor);white.setForeground(Color.WHITE);

        colorSwitch.add(blue); colorSwitch.add(black); colorSwitch.add(red);
        colorSwitch.add(green); colorSwitch.add(white);colorSwitch.add(yellow);

        buttonGroup.add(blue);buttonGroup.add(black);
        buttonGroup.add(red);buttonGroup.add(green);
        buttonGroup.add(yellow);buttonGroup.add(white);


        colorSwitch.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        ColorPicker c1 = new ColorPicker();
        if (ae.getSource() == blue){
            System.out.println("Du valde blå");
            c1.setValueB(250);
            this.setBackground(Color.blue);
        }
    }
}
