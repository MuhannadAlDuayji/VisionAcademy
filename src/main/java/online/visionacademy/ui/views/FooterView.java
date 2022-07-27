package online.visionacademy.ui.views;

import javax.swing.*;
import java.awt.*;

public class FooterView extends JPanel {

    private JLabel lbl;

    public FooterView(){
        init();
    }
    private void init(){

        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setBackground(Color.BLACK);
        lbl = new JLabel("This is the footer");
        add(lbl);
        setVisible(true);

    }

}
