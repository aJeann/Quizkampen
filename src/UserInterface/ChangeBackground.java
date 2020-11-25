package UserInterface;

import UserInterface.Misc.BackgroundColor;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Axel Jeansson, Christoffer Grännby,
 * Date: 2020-11-18
 * Time: 15:20
 * Project: Quizkampen
 * Copyright: MIT
 */
public class ChangeBackground extends JFrame {

    JSlider g = new JSlider(JSlider.HORIZONTAL, 0, 250, 0);
    JSlider b = new JSlider(JSlider.HORIZONTAL, 0, 250, 0);
    JSlider r = new JSlider(JSlider.HORIZONTAL, 0, 250, 0);

    JLabel rod = new JLabel("Rött = " +r.getValue());
    JLabel gron = new JLabel("Grönt = "+g.getValue());
    JLabel bla = new JLabel("Blått = "+b.getValue());
    private JPanel colorSwitch = new JPanel();
    BackgroundColor bg = new BackgroundColor();
    Color bgColor = bg.getBgColor();

    public ChangeBackground(){
        JButton back2 = new JButton();

        colorSwitch.setSize(400, 300);
        colorSwitch.setLayout(null);

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
        back2.addActionListener(e -> {
            new ChangeSettings();

        });
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


        bg.setBgColor(bgColor);
    }

    public JPanel getBGPanel(){
        return colorSwitch;
    }
}
