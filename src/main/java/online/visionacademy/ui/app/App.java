package online.visionacademy.ui.app;

import online.visionacademy.ui.presenter.MainViewPresenter;
import online.visionacademy.ui.views.MainView;

import javax.swing.*;

public class App {

    public App(){
        MainView mv = new MainView();
        MainViewPresenter  presenter = new MainViewPresenter(mv);
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(App::new);

    }

}
