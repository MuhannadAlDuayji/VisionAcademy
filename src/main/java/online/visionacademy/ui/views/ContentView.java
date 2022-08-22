package online.visionacademy.ui.views;

import online.visionacademy.ui.util.AppColor;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ContentView extends JPanel {

    private NavigationHeader navigationHeader;
    private NavigationBody navigationBody;


    public ContentView(){
        init();
    }
    private void init(){

        navigationHeader = new NavigationHeader();
        navigationBody = new NavigationBody();

        setName("Content View");
        setLayout(new BorderLayout());
        setBackground(Color.decode(AppColor.BACKGROUND));
        setBorder(new EmptyBorder(20,5,20,20));
        add(navigationHeader,BorderLayout.NORTH);
        add(navigationBody,BorderLayout.CENTER);
        setVisible(true);
    }

    public NavigationHeader getNavigationHeader() {
        return navigationHeader;
    }

    public NavigationBody getNavigationBody() {
        return navigationBody;
    }
}
