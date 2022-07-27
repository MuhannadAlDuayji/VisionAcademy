package online.visionacademy.ui.views;

import online.visionacademy.ui.common.UI;

import javax.swing.*;
import java.awt.*;

public class MainView extends JFrame {

    public MainView(){
        init();
    }

    private void init(){
        setName("Main Frame");
        setTitle("iStudent");
        getContentPane().setBackground(Color.WHITE);
        setSize(UI.getScreenSize());
        setLayout(new BorderLayout( ));


        add(new HeaderView(), BorderLayout.NORTH);
        add(new ContentView(), BorderLayout.CENTER);
        add(new FooterView(), BorderLayout.SOUTH);

        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        setVisible(true);
    }

}
