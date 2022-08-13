package online.visionacademy.ui.components;

import org.kordamp.ikonli.Ikon;
import org.kordamp.ikonli.swing.FontIcon;

import javax.swing.*;
import java.awt.*;

public class TextWithIcon extends Text {

    private Ikon icon;

    public TextWithIcon(String text, Color color, int size, int style, Ikon icon){
        super(text,color,size,style);
        this.icon = icon;
        init();
    }

    private void init(){

        FontIcon icon = FontIcon.of(this.icon);
        icon.setIconSize(size);
        icon.setIconColor(color);
        setIcon(icon);

    }
}
