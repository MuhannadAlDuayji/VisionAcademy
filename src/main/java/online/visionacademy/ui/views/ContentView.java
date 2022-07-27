package online.visionacademy.ui.views;

import javax.swing.*;
import java.awt.*;

public class ContentView extends JPanel {

    private JLabel lbl;

    public ContentView(){
        init();
    }
    private void init(){
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setBackground(Color.YELLOW);
        lbl = new JLabel("This is the footer");
        add(lbl);
        setVisible(true);
    }

}
