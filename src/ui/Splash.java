package ui;

import javafx.application.Preloader;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Splash extends Preloader {

    private Stage preloaderStage;
    private Scene scene;

    @Override
    public void start(Stage primaryStage) throws Exception {
        preloaderStage = primaryStage;
        preloaderStage.setScene(scene);
        preloaderStage.initStyle(StageStyle.UNDECORATED);
        /* Icon Loading space
        */
        preloaderStage.show();
    }

    @Override
    public void init() throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxml/splash-screen.fxml"));
        fxmlLoader.setController(MainGUIController.class);
        Parent root = fxmlLoader.load();
        scene = new Scene(root);
    }

    @Override
    public void handleStateChangeNotification(StateChangeNotification info) {
        StateChangeNotification.Type type = info.getType();
        if (type == StateChangeNotification.Type.BEFORE_START) preloaderStage.hide();
    }
}
