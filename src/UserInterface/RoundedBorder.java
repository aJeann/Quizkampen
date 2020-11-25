package UserInterface;

import javax.swing.border.Border;
import java.awt.*;

/**
 * Created by Axel Jeansson, Christoffer Grännby, Iryna Gnatenko,
 * Date: 2020-11-12
 * Time: 17:33
 * Project: Quizkampen
 * Copyright: MIT
 */
public class RoundedBorder implements Border {

    private int radius;

    public RoundedBorder(int radius) {
        this.radius = radius;
    }

    public Insets getBorderInsets(Component c) {
        return new Insets(this.radius+1, this.radius+1, this.radius+2, this.radius);
    }

    public boolean isBorderOpaque() {
        return false;
    }

    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        g.drawRoundRect(x, y, width-1, height-1, radius, radius);
    }
}