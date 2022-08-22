package online.visionacademy.ui.views;

import javafx.scene.paint.Material;
import online.visionacademy.ui.common.PanelFactory;
import online.visionacademy.ui.components.TextWithIcon;
import online.visionacademy.ui.routes.Router;
import online.visionacademy.ui.util.AppColor;
import org.kordamp.ikonli.Ikon;
import org.kordamp.ikonli.materialdesign.MaterialDesign;
import org.kordamp.ikonli.swing.FontIcon;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class NavigationHeader extends JPanel implements PropertyChangeListener {

    private String text = "Dashboard";
    private TextWithIcon navigationTitle;
    private JLabel navigationPath;

    private JButton backBtn;

    public NavigationHeader() {

        init();
        Router.getInstance().addPropertyChangeListener(this);

    }
    public void init(){

        setLayout(new FlowLayout(FlowLayout.LEADING));
        setBackground(Color.decode(AppColor.BACKGROUND));
        navigationTitle = new TextWithIcon(text
                ,Color.decode(AppColor.TITLE),24,Font.BOLD, MaterialDesign.MDI_GRID);

        navigationPath = new JLabel(" D --> X --> U");

        JPanel panel= PanelFactory.createBoxPanel(BoxLayout.Y_AXIS,Color.decode(AppColor.BACKGROUND));
        panel.add(navigationTitle);
        panel.add(Box.createRigidArea(new Dimension(0,8)));
        panel.add(navigationPath);
        panel.add(Box.createRigidArea(new Dimension(0,8)));

        backBtn = createButton("Back",Color.WHITE,Color.decode(AppColor.SEC_BACKGROUND),MaterialDesign.MDI_ARROW_LEFT_BOLD_CIRCLE_OUTLINE);

        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Router.getInstance().back();
            }
        });

        panel.add(backBtn);



        setBorder(new EmptyBorder(0,17,0,0));
        add(panel);

    }

    private void setMouseListener() {
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("You have clicked the back button.");
            }
        });
    }

    public JButton createButton(String text, Color foreground, Color bg, Ikon icon){
        JButton btn = new JButton(text);
        btn.setForeground(foreground);
        btn.setBackground(bg);
        btn.setBorderPainted(false);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        FontIcon ic = FontIcon.of(icon);
        ic.setIconColor(foreground);
        btn.setIcon(ic);
        return btn;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

        if (Router.getInstance().isBackBtnActive()){
            backBtn.setVisible(true);

        }else {
            backBtn.setVisible(false);
        }
    }

    public void setNavigationPath(String path[]){

        String str = "";

        for (int i = 0; i < path.length; i++) {

            if(i==path.length-1){
                str+=path[i];
            }else {
                str+=" >> ";
            }
        }
        navigationPath.setText(str);
    }

    public void setTitleLbl(String title){
      navigationTitle.setText(title);
    }
}
