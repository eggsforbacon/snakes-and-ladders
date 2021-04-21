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

    /*Main Pane*/

    @FXML
    private BorderPane mainPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        label = progress;
    }

    /***************************************METHODS***************************************/

}
