package online.visionacademy.ui.views;

import online.visionacademy.ui.common.UI;
import online.visionacademy.ui.routes.NavigationItem;
import online.visionacademy.ui.util.AppColor;
import org.kordamp.ikonli.materialdesign.MaterialDesign;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Dashboard extends JPanel {

    public Dashboard(){
        init();
    }

    private void init(){
        setName("Dashboard");
        setLayout(new FlowLayout(FlowLayout.LEADING,20,20));
        setBackground(Color.decode(AppColor.BACKGROUND));

        int width = UI.getScreenSize().width;
        int height = UI.getScreenSize().height;

        setPreferredSize(new Dimension(width-50,height));

        NavigatablePanel studentCard = new NavigatablePanel("Student","Add , remove and edit students", Color.white, MaterialDesign.MDI_ACCOUNT_MULTIPLE, NavigationItem.STUDENT_DASHBOARD);
        NavigatablePanel courseCard =  new NavigatablePanel("Courses","Add , remove and edit courses", Color.white, MaterialDesign.MDI_LAPTOP_CHROMEBOOK,NavigationItem.COURSE_DASHBOARD);
        NavigatablePanel registerCard = new NavigatablePanel("Registration","Add , remove and edit registration", Color.white, MaterialDesign.MDI_FILE_DOCUMENT,NavigationItem.COMING_SOON_DASHBOARD);
        NavigatablePanel reportCard = new NavigatablePanel("Reports","Export and print report", Color.white, MaterialDesign.MDI_FILE_PDF,NavigationItem.COMING_SOON_DASHBOARD);
        NavigatablePanel settingsCard = new NavigatablePanel("Settings","Customize and control settings", Color.white, MaterialDesign.MDI_SETTINGS,NavigationItem.COMING_SOON_DASHBOARD);
        add(studentCard);
        add(courseCard);
        add(registerCard);
        add(reportCard);
        add(settingsCard);


        setVisible(true);
    }
}
