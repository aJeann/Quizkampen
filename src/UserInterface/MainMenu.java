package UserInterface;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Axel Jeansson, Christoffer Grännby,
 * Date: 2020-11-18
 * Time: 15:14
 * Project: Quizkampen
 * Copyright: MIT
 */
public class MainMenu extends JFrame{

    private JPanel menu = new JPanel();
    ImageIcon header = new ImageIcon("images\\qh.png");
    JLabel quizkampen = new JLabel(header);
    public Dimension size = new Dimension(500, 700);
    Font font = new Font("Dialog", Font.BOLD, 20);
    Color bColor = new Color(237, 228, 223);

    public MainMenu() {
        menu.setSize(size);
        menu.setLayout(null);
        menu.setOpaque(false);

        quizkampen.setBounds(-8, 0, 500, 100);
        quizkampen.setFocusable(false);

        menu.add(quizkampen);

        menu.setVisible(true);
    }

    public JPanel getMenuPanel(){
        return menu;
    }
}
