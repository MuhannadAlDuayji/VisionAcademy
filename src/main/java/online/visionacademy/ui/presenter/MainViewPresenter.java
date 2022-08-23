package online.visionacademy.ui.presenter;

import online.visionacademy.ui.routes.NavigationItem;
import online.visionacademy.ui.routes.Router;
import online.visionacademy.ui.views.ContentView;
import online.visionacademy.ui.views.MainView;
import online.visionacademy.ui.views.NavigationBody;

import javax.swing.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class MainViewPresenter implements MainViewListener , PropertyChangeListener {

    private MainView view;

    public MainViewPresenter(MainView view){
        this.view = view;
        Router.getInstance().addPropertyChangeListener(this);
        Router.getInstance().   navigate(NavigationItem.DASHBOARD);

        view.addListener(this);
    }

    @Override
    public void back() {
        Router.getInstance().back();
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

        JPanel activeView = Router.getInstance().getActiveView();

        ContentView contentView =  view.getContentView();
        NavigationBody navigationBody = contentView.getNavigationBody();
        navigationBody.removeAll();

        navigationBody.add(activeView);
        navigationBody.repaint();

        String [] path = Router.getInstance().getNavigationPath();

        view.setTitleLbl(path[path.length-1]);
        view.setNavigationPath(path);


    }


}
