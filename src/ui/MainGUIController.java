package ui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class MainGUIController implements Initializable {

    /****************************************FIELDS****************************************/

    /*Splash Screen*/

    @FXML
    private Label progress;

    public static Label label;

    private final String[] QUIRKY_LOADING_TEXTS = {"Trimming snakes...", "Cleaning ladders...", "Rinsing spots...", "Calculating rage levels...","Generating null spaces...", "Tuning the flute..."};

    /*Main Pane*/

    @FXML
    private BorderPane mainPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        label = progress;
    }

    /***************************************METHODS***************************************/

    /*Splash Screen*/

}
