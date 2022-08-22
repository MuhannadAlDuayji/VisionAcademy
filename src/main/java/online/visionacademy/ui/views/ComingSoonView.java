package online.visionacademy.ui.views;

import online.visionacademy.ui.components.Text;
import online.visionacademy.ui.util.AppColor;

import javax.swing.*;
import java.awt.*;

public class ComingSoonView extends JPanel {

    public ComingSoonView(){
        init();
    }

    private void init(){
        setName("Coming Soon Dashboard");
        setBackground(Color.decode(AppColor.BACKGROUND));
        add(new Text("Coming Soon Dashboard"));
    }
}
