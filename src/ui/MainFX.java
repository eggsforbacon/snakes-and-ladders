package ui;

import com.sun.javafx.application.LauncherImpl;
import javafx.application.Application;
import javafx.application.Preloader;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class MainFX extends Application {

    static final int COUNT_LIMIT = 5500; //<- Number CAN'T UNDER ANY CIRCUMSTANCES be greater than this
    MainGUIController controller;

    public MainFX() {
        controller = new MainGUIController();
    }

    public static void main(String[] args) {
        LauncherImpl.launchApplication(MainFX.class, Splash.class, args);
    }

    @Override
    public void init() {
        load(0);
    }

    public void load(int i) {
        if (i < COUNT_LIMIT) {
            double progress = (100.0 * i) / COUNT_LIMIT;
            LauncherImpl.notifyPreloader(this,new Preloader.ProgressNotification(progress));
            load(i + 1);
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxml/main-pane.fxml"));
        fxmlLoader.setController(controller);
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        Image icon = new Image(String.valueOf(getClass().getResource("resources/snl-logo.png")));
        primaryStage.getIcons().add(icon);
        scene.getStylesheets().addAll(String.valueOf(getClass().getResource("css/main.css")));
        primaryStage.setTitle("Snakes and Ladders: Start");
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    @Override
    public void stop() {
        System.out.println("Closed, gBye");
    }
}
