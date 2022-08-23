package online.visionacademy.ui.views;

import online.visionacademy.ui.common.UI;
import online.visionacademy.ui.presenter.MainViewListener;
import online.visionacademy.ui.presenter.MainViewPresenter;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MainView extends JFrame {

    private final ArrayList<MainViewListener> listeners = new ArrayList<>();
    private HeaderView headerView = new HeaderView();
    private ContentView contentView = new ContentView();
    private FooterView footerView = new FooterView();
    public MainView(){
        init();
    }

    private void init(){
        setName("Main Frame");
        setTitle("iStudent");
        getContentPane().setBackground(Color.WHITE);
        setSize(UI.getScreenSize());
        setLayout(new BorderLayout( ));


        add(headerView, BorderLayout.NORTH);
        add(contentView, BorderLayout.CENTER);
        add(footerView, BorderLayout.SOUTH);
        this.setSize(new Dimension(1420,720));
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        setVisible(true);
    }

    public void addListener(MainViewListener listener){
        this.listeners.add(listener);
    }

    public ContentView getContentView(){
       return (ContentView) this.getContentPane().getComponent(1);
    }

    public void setTitleLbl(String titleLbl){
        ContentView contentView = getContentView();
        NavigationHeader navigationHeader = contentView.getNavigationHeader();
        navigationHeader.setTitleLbl(titleLbl);


    }

    public void setNavigationPath(String[] path){
        ContentView contentView =  getContentView();
        NavigationHeader navigationHeader = contentView.getNavigationHeader();
        navigationHeader.setNavigationPath(path);


    }

}
