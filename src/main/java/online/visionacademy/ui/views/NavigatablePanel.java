package online.visionacademy.ui.views;

import online.visionacademy.ui.common.PanelFactory;
import online.visionacademy.ui.common.UI;
import online.visionacademy.ui.components.Text;
import online.visionacademy.ui.components.TextWithIcon;
import online.visionacademy.ui.routes.NavigationItem;
import online.visionacademy.ui.routes.Router;
import online.visionacademy.ui.util.AppColor;
import org.kordamp.ikonli.Ikon;
import org.kordamp.ikonli.swing.FontIcon;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class NavigatablePanel extends JPanel {

    // from user
    private String title;
    private String subTitle;

    // assign to it
    private Text titleLbl;
    private Text subTitleLbl;

    private Color color;
    private Ikon icon;

    private NavigationItem destination;

    public NavigatablePanel(String title, String subTitle, Color color, Ikon icon, NavigationItem destination){
        this.title = title;
        this.subTitle = subTitle;
        this.color = color;
        this.icon = icon;
        this.destination = destination;

        init();
    }

    private void init(){

        setBackground(color);
        setLayout(new BorderLayout());

        int width = UI.getScreenSize().width;
        setPreferredSize(new Dimension(width/4,125));
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        setPadding();
        titleLbl = new Text(this.title,Color.decode(AppColor.TITLE));
        subTitleLbl = new Text(this.subTitle,Color.decode(AppColor.SUBTITLE),16,Font.BOLD);

        JPanel row = PanelFactory.createBoxPanel(BoxLayout.X_AXIS,color);
        row.add(titleLbl);
        row.add(Box.createHorizontalGlue());
        row.add(getIcon());

        setMouseListener();

        add(row,BorderLayout.NORTH);
        add(subTitleLbl,BorderLayout.CENTER);
        setVisible(true);
    }

    private void setMouseListener() {
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Router.getInstance().navigation(destination);
            }
        });
    }

    public void setPadding(){
        //padding and border
        setBorder(new EmptyBorder(30,30,30,30));
        Border padding = getBorder();
        Border leftBorder = BorderFactory.createMatteBorder(0,5,0,0,Color.decode("#CCCCCC"));
        setBorder(new CompoundBorder(leftBorder,padding));
    }

    private JLabel getIcon(){

        FontIcon icon = FontIcon.of(this.icon);
        icon.setIconColor(Color.decode(AppColor.TITLE));
        icon.setIconSize(40);
        JLabel lbl = new JLabel();
        lbl.setIcon(icon);
        return lbl;
    }

}
