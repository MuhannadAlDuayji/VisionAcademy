package online.visionacademy.ui.routes;

import online.visionacademy.ui.views.ComingSoonView;
import online.visionacademy.ui.views.Dashboard;
import online.visionacademy.ui.views.StudentDashboardView;

import javax.swing.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class Router {

    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
    private static Router instance;

    private Router(){}

    public JPanel getActiveView(){
        return Navigator.getInstance().top();
    }

    public void back(){
        Navigator.getInstance().pop();
    }

    public boolean isBackBtnActive(){
        return Navigator.getInstance().size() > 1;
    }

    public void navigation(NavigationItem des){

        switch (des){

            case DASHBOARD:
                Navigator.getInstance().push(new Dashboard());
                break;
            case STUDENT_DASHBOARD:
                Navigator.getInstance().push(new StudentDashboardView());
                break;
            case COMING_SOON_DASHBOARD:
                Navigator.getInstance().push(new ComingSoonView());
                break;
            default:
                Navigator.getInstance().push(new ComingSoonView());
        }
    }

    public String[] getPath(){
        return Navigator.getInstance().getNavigationPath();
    }

    public static Router getInstance() {

        if(instance == null)
            instance = new Router();
        return instance;
    }

    public void addPropertyChangeListener(PropertyChangeListener listener){
        pcs.addPropertyChangeListener(listener);
    }
    public void removePropertyChangeListener(PropertyChangeListener listener){
        pcs.removePropertyChangeListener(listener);
    }
}
