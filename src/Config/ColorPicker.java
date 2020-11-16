package Config;

import java.awt.*;

/**
 * Created by Axel Jeansson
 * Date: 2020-11-12
 * Time: 22:12
 * Project: Quizkampen
 * Copyright: MIT
 */
public class ColorPicker {
    private int valueR = 0;
    private int valueG = 0;
    private int valueB = 0;
    private Color color;

    public int getValueR(){
        return valueR;
    }

    public void setValueR(int valueR){
        this.valueR = valueR;
    }

    public int getValueG() {
        return valueG;
    }

    public void setValueG(int valueG) {
        this.valueG = valueG;
    }

    public int getValueB() {
        return valueB;
    }

    public void setValueB(int valueB) {
        this.valueB = valueB;
    }

    public void setColor(){
        color = new Color(getValueR(), getValueG(), getValueB());
    }

    public Color getColor() {
        return color;
    }
}
