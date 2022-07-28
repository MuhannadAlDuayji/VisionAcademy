package online.visionacademy.ui.common;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class PanelFactory {


    private PanelFactory(){}

    public static JPanel createPanel(){
        return new JPanel();
    }

    public static JPanel createPanel(Color bg){
        JPanel panel = createPanel();
        panel.setBackground(bg);
        return panel;
    }

    public static JPanel createPanel(Color bg, int padding){
        JPanel panel = createPanel();
        panel.setBackground(bg);
        panel.setBorder(
                new EmptyBorder(padding,padding
                ,padding,padding)
        );
        return panel;
    }

    public static JPanel createPanel(Color bg, int padding, int align
            , int hgap,int vgap){

        JPanel panel = createPanel();
        panel.setLayout(new FlowLayout(align,hgap,vgap));
        panel.setBackground(bg);
        panel.setBorder(
                new EmptyBorder(padding,padding
                        ,padding,padding)
        );
        panel.setAlignmentX(align);

        return panel;
    }


    public static JPanel createBoxPanel(int axis, Color bg){

        JPanel panel = createPanel();
        panel.setBackground(bg);

        BoxLayout layout;

        if(axis == 0){
            layout = new BoxLayout(panel,BoxLayout.X_AXIS);
        }else {
            layout = new BoxLayout(panel,BoxLayout.Y_AXIS);
        }

        panel.setLayout(layout);

        return panel;
    }

}
