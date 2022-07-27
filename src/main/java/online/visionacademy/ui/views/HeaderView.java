package online.visionacademy.ui.views;

import online.visionacademy.ui.util.AppColor;


import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class HeaderView extends JPanel {

    private JLabel lbl;

    public HeaderView(){
        init();
    }
    private void init(){

        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setBackground(Color.decode(AppColor.SEC_BACKGROUND));

        //padding
        setBorder(new EmptyBorder(20,20,20,20));

        Border padding = getBorder();

        Border bottomBorder = BorderFactory.createMatteBorder(0,0
                ,1,0
                , Color.decode("#DDDDDD"));

        setBorder(new CompoundBorder(bottomBorder,padding));

        setVisible(true);

    }

}
