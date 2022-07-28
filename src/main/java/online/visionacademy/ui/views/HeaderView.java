package online.visionacademy.ui.views;

import online.visionacademy.ui.common.PanelFactory;
import online.visionacademy.ui.components.CircleImage;
import online.visionacademy.ui.components.Text;
import online.visionacademy.ui.util.AppColor;


import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.net.MalformedURLException;

public class HeaderView extends JPanel {

    private JLabel lbl;

    public HeaderView(){
        init();
    }
    private void init(){

        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setBackground(Color.decode(AppColor.SEC_BACKGROUND));

        //padding and border
        setBorder(new EmptyBorder(20,20,20,20));
        Border padding = getBorder();
        Border bottomBorder = BorderFactory.createMatteBorder(0,0,1,0, Color.RED);
        setBorder(new CompoundBorder(bottomBorder,padding));

        JPanel basicInfoPanel = PanelFactory.createPanel(Color.decode(AppColor.SEC_BACKGROUND),5,FlowLayout.LEADING,10,10);

        CircleImage userAvatar = createCircleImage(
          "C:\\Workspaces\\vision_projects\\student_management_system\\StudentManagement\\src\\main\\java\\online\\visionacademy\\ui\\images\\weasel.png",70
          ,3,Color.WHITE
        );
        basicInfoPanel.add(userAvatar);

        JPanel welcomeUserPanel = createWelcomeUserPanel();
        basicInfoPanel.add(welcomeUserPanel);

        add(basicInfoPanel);

        setVisible(true);
    }

    public CircleImage createCircleImage(
            String src
            , int wh
            , int borderSize
            , Color borderColor){

        CircleImage img = null;
        try {
            img = new CircleImage(src
                ,borderSize,borderColor);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        img.setPreferredSize(new Dimension(wh,wh));

        return img;
    }

    public JPanel createWelcomeUserPanel(){

        JPanel welcomeUserPanel = PanelFactory.createBoxPanel(BoxLayout.Y_AXIS,Color.decode(AppColor.SEC_BACKGROUND));

        welcomeUserPanel.add(new Text("",Color.decode(AppColor.WHITE)));

        return welcomeUserPanel;
    }

}
