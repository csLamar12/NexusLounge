package Controller;

import Model.DataModel;
import View.LoginScreen;
import View.MainView;
import View.SplashScreen;

import javax.swing.*;
import java.awt.event.ActionListener;

public class MainController {
    private DataModel model;
    private MainView view;

    public MainController(DataModel model, MainView view) {
        this.model = model;
        this.view = view;
    }

    public void initApp(){
        view.showSplashScreen();
        view.showLoginScreen();
        bindLoginScreenButtonEvents();
        System.out.println("Next Screen");
    }

    public void handleLogin() {
        String username = view.getLoginScreen().getUsername();
        String password = view.getLoginScreen().getPassword();
        if (model.checkLogin(username, password)){
            view.getLoginScreen().displayMessage("Login Successful");
        view.getLoginScreen().dispose();
        } else
            view.getLoginScreen().displayMessage("Login Failed");
    }

    public void guestLogin(){
        view.getLoginScreen().dispose();
    }

    public void bindLoginScreenButtonEvents(){
        LoginScreen loginScreen = view.getLoginScreen();
        loginScreen.setLoginButtonLister(e -> handleLogin());
        loginScreen.setGuestButtonLister(e -> guestLogin());
//        loginScreen.getLoginButton().addActionListener(new ActionListener() {
//
//        });
    }

}
