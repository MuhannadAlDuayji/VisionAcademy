package online.visionacademy.ui.components;

import javax.swing.*;
import java.awt.*;

public class Text extends JLabel {

    private String text;
    protected Color color;
    protected int size;
    private int style;

    public Text(String text) {
        this(text,Color.BLACK,18,Font.BOLD);
    }

    public Text(String text, Color color) {
        this(text,color,18,Font.BOLD);

    }

    public Text(String text, Color color, int size) {
        this(text,color,size,Font.BOLD);
    }

    public Text(String text, Color color, int size, int style) {
        this.text = text;
        this.color = color;
        this.size = size;
        this.style = style;

        init();
    }


    private void init(){

        setText(text);
        setForeground(color);
        setFont(new Font("ITALIC",style,size));

    }
}
