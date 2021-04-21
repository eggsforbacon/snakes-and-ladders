package ui;

import com.sun.javafx.application.LauncherImpl;
import javafx.application.Application;
import javafx.application.Preloader;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainFX extends Application {

    static final int COUNT_LIMIT = 60000;
    MainGUIController controller;

    public MainFX() {
        controller = new MainGUIController();
    }

    public static void main(String[] args) {
        LauncherImpl.launchApplication(MainFX.class, Splash.class, args);
    }

    @Override
    public void init() throws Exception {
        for (int i = 0; i < COUNT_LIMIT; i++) {
            double progress = (100.0 * i) / COUNT_LIMIT;
            LauncherImpl.notifyPreloader(this,new Preloader.ProgressNotification(progress));
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxml/main-pane.fxml"));
        fxmlLoader.setController(controller);
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        scene.getStylesheets().addAll(String.valueOf(getClass().getResource("css/main.css")));
        primaryStage.setTitle("Snakes and Ladders: Start");
        primaryStage.show();
    }

    @Override
    public void stop() throws Exception {
        System.out.println("Closed, gBye");
    }
}
