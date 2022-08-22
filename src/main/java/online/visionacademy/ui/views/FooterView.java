package online.visionacademy.ui.views;

import online.visionacademy.ui.components.TextWithIcon;
import online.visionacademy.ui.util.AppColor;
import org.kordamp.ikonli.materialdesign.MaterialDesign;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class FooterView extends JPanel {

    private JLabel lbl;

    public FooterView(){
        init();
    }
    private void init(){

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.decode(AppColor.BACKGROUND));
        setBorder(new EmptyBorder(20,20,20,20));
        Border padding = getBorder();
        Border bottomBorder = BorderFactory.createMatteBorder(1,0,0,0, Color.decode("#DDDDDD"));
        setBorder(new CompoundBorder(bottomBorder,padding));

        add(new TextWithIcon("Develop by Eng.Muhannad",Color.decode(AppColor.SUBTITLE),14,Font.PLAIN, MaterialDesign.MDI_LANGUAGE_JAVASCRIPT));
        add(new TextWithIcon("Vision Academy | 2022 ",Color.decode(AppColor.SUBTITLE),14,Font.PLAIN,MaterialDesign.MDI_BEER));
        setVisible(true);

    }

}
