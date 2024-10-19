import Controller.MainController;
import Model.DataModel;
import View.LoginScreen;
import View.MainView;
import View.SplashScreen;

public class Main {
    public static void main(String[] args) {
        DataModel model = new DataModel();
        MainView view = new MainView();
        MainController controller = new MainController(model, view);
        controller.initApp();
    }
}