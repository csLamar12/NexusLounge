import Controller.MainController;
import Model.DataModel;
import View.LoginScreen;
import View.MainView;
import View.SplashScreen;

public class Main {
    public static void main(String[] args) {
//        SplashScreen splashScreen = new SplashScreen();
        DataModel model = new DataModel();
        MainView view = new MainView();
        MainController controller = new MainController(model, view);
        controller.initApp();
    }
}