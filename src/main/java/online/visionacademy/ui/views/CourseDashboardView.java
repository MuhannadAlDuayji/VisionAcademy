package online.visionacademy.ui.views;

import online.visionacademy.ui.components.Text;
import online.visionacademy.ui.util.AppColor;

import javax.swing.*;
import java.awt.*;

public class CourseDashboardView extends JPanel {

    public CourseDashboardView(){
        init();
    }

    private void init(){

        setName("Course Dashboard");
        setBackground(Color.decode(AppColor.BACKGROUND));
        add(new Text("Course Dashboard"));

    }
}
