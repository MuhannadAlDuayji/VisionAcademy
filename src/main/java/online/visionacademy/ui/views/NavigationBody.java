package online.visionacademy.ui.views;

import online.visionacademy.ui.util.AppColor;

import javax.swing.*;
import java.awt.*;

public class NavigationBody extends JPanel {

    public NavigationBody(){
        init();
    }

    public void init(){

        setBackground(Color.decode(AppColor.BACKGROUND));

        add(new Dashboard());
        setVisible(true);
    }
}
