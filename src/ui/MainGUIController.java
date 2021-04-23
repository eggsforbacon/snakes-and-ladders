package ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.*;

import java.net.URL;
import java.util.ResourceBundle;

public class MainGUIController implements Initializable, CSSIDs {

    /****************************************FIELDS****************************************/

    /*Splash Screen*/
    @FXML
    private BorderPane preloaderPane;

    @FXML
    private Label progress;

    public static Label label;

    /*Main Pane*/

    @FXML
    private BorderPane mainPane;

    @FXML
    private Button newGameBTN;

    @FXML
    private Button scoreBoardBTN;

    @FXML
    private Button eraseAllDataBTN;

    @FXML
    private Label mainTitleLBL;

    //New Game

    @FXML
    private ListView<String> playersLV;

    @FXML
    private TextField columnsTF;

    @FXML
    private TextField rowsTF;

    @FXML
    private TextField snakesTF;

    @FXML
    private TextField laddersTF;

    /*Game Pane*/

    @FXML
    private BorderPane boardPane;

    @FXML
    private ImageView diceIMV;

    @FXML
    private TableView<Player> localLeaderboardTBV;

    @FXML
    private TableColumn<Player, String> nameColBoard;

    @FXML
    private TableColumn<Player, Integer> scoreColBoard;

    @FXML
    private Label timerLBL;

    //Tiles

    @FXML
    private Label tileLBL;

    @FXML
    private ImageView specialTileLBL;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        label = progress;
        initializeMainMenu();
    }

    /***************************************METHODS***************************************/

    /*General*/

    private void initIDs() {
        mainTitleLBL.setId(titleLBLId);
        mainPane.setId(mainPaneID);
        newGameBTN.setId(mainPaneButtonsID);
        scoreBoardBTN.setId(mainPaneButtonsID);
        eraseAllDataBTN.setId(mainPaneButtonsID);
    }

    private void launchWindow(String fxml, String title, Modality modality) {
        try {
            Parent loadedPane = loadFxml(fxml);
            Stage stage = new Stage();
            stage.setScene(new Scene(loadedPane));
            Image icon = new Image(String.valueOf(getClass().getResource("resources/snl-logo.png")));
            stage.getIcons().add(icon);
            stage.getScene().getStylesheets().addAll(String.valueOf(getClass().getResource("css/main.css")));
            stage.setTitle(title);
            stage.initModality(modality);
            stage.setResizable(false);
            stage.show();
        } catch (NullPointerException npe) {
            System.out.println("Can't load requested window right now.\nRequested window: \"" + fxml + "\"");
            System.err.println(npe.getMessage());
        }
    }

    private Parent loadFxml(String fxml) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxml));
            fxmlLoader.setController(this);
            System.out.println("is it working?");
            return fxmlLoader.load();
        } catch (Exception e) {
            System.out.println("Can't load requested document right now.\nRequested document: \"" + fxml + "\"");
            throw new NullPointerException("Document is null");
        }
    }

    /*Main Pane*/

    private void initializeMainMenu() {
        initIDs();
    }

    @FXML
    void eraseAllData(ActionEvent event) {

    }

    @FXML
    void newGame(ActionEvent event) {
        launchWindow("fxml/board/create-board.fxml","Create new game", Modality.APPLICATION_MODAL);
    }

    //Pre Game

    @FXML
    void addPlayer(ActionEvent event) {

    }

    @FXML
    void startGame(ActionEvent event) {
        ((Stage)playersLV.getScene().getWindow()).close();
        ((Stage)mainPane.getScene().getWindow()).close();
        launchWindow("fxml/board/board-pane.fxml", "Now playing!", Modality.NONE);
        Parent board = loadFxml("fxml/board/board.fxml");
        boardPane.setCenter(board);
        initializeBoard();
    }

    void initializeBoard() {

    }

    @FXML
    void scoreBoard(ActionEvent event) {

    }

    /*Game Pane*/

    @FXML
    void restartGame(ActionEvent event) {

    }

    @FXML
    void rollDice(MouseEvent event) {

    }

    @FXML
    void endGame(ActionEvent event) {

    }

    @FXML
    void skipTurn(ActionEvent event) {

    }
}
