package online.visionacademy.ui.views;

import online.visionacademy.ui.components.Text;
import online.visionacademy.ui.util.AppColor;

import javax.swing.*;
import java.awt.*;

public class StudentDashboardView extends JPanel {

    public StudentDashboardView(){
        init();
    }

    private void init(){

        setName("Student Dashboard");
        setBackground(Color.decode(AppColor.BACKGROUND));
        add(new Text("Student Dashboard"));

    }
}
