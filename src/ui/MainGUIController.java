package ui;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.*;

import javax.swing.event.ChangeListener;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MainGUIController implements Initializable, CSSIDs {

    /****************************************FIELDS****************************************/

    /*Splash Screen*/
    @FXML
    private BorderPane preloaderPane;

    @FXML
    private Label progress;

    public static Label label;

    /*Dialogue Pane*/

    @FXML
    private BorderPane dialoguePane;

    @FXML
    private Label dialogueLBL = new Label();

    @FXML
    private Button dialogueButton;

    @FXML
    private HBox bBarHBOX;

    @FXML
    private Button confirmDialogueBTN;


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
    private Button closeGameBTN;

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

    Tooltip colRowTT = new Tooltip("Please use integer numbers between 4 and 10.\nGoing above 10 is discouraged to avoid\nmemory shortage.");

    Tooltip snakeLadderTT = new Tooltip("Keep the sum of snakes and ladders to a\nquarter of the size of the board to avoid\novercrowding.");

    //Add Player

    @FXML
    private TextField playernameTF;

    @FXML
    private ToggleGroup colors;

    @FXML
    private RadioButton redRB;

    @FXML
    private RadioButton orangeRB;

    @FXML
    private RadioButton cyanRB;

    @FXML
    private RadioButton darkBlueRB;

    @FXML
    private RadioButton yellowRB;

    @FXML
    private RadioButton greenRB;

    @FXML
    private RadioButton pinkRB;

    @FXML
    private RadioButton purpleRB;

    @FXML
    private RadioButton limeRB;

    /*Game Pane*/

    @FXML
    private BorderPane boardPane = new BorderPane();

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

    //Board

    @FXML
    private GridPane boardGP = new GridPane();

    //Tiles

    @FXML
    private Label tileLBL = new Label();

    @FXML
    private AnchorPane tileAP = new AnchorPane();

    @FXML
    private ImageView specialTileLBL;

    /*Fields*/

    Game game;

    public MainGUIController(Game game) {
        this.game = game;
    }

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

    private void launchWindow(String fxml, String title, Modality modality, String stylesheet) {
        try {
            Parent loadedPane = loadFxml(fxml);
            Stage stage = new Stage();
            stage.setScene(new Scene(loadedPane));
            Image icon = new Image(String.valueOf(getClass().getResource("resources/snl-logo.png")));
            stage.getIcons().add(icon);
            stage.getScene().getStylesheets().addAll(String.valueOf(getClass().getResource(stylesheet)));
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
            return fxmlLoader.load();
        } catch (Exception e) {
            System.out.println("Can't load requested document right now.\nRequested document: \"" + fxml + "\"");
            throw new NullPointerException("Document is null");
        }
    }

    /*Dialogue Pane*/

    @FXML
    void dismissDialogue(ActionEvent event) {
        ((Stage)dialoguePane.getScene().getWindow()).close();
    }

    @FXML
    void confirmDialogue(ActionEvent event) {
        ((Stage)confirmDialogueBTN.getScene().getWindow()).close();
        ((Stage)boardGP.getScene().getWindow()).close();
        launchWindow("fxml/main-pane.fxml","Snakes and Ladders: Start",Modality.NONE,"css/main.css");
    }

    /*Main Pane*/

    private void initializeMainMenu() {
        initIDs();
    }

    @FXML
    void eraseAllData(ActionEvent event) {

    }

    @FXML
    void closeGame(ActionEvent event) {
        ((Stage)mainPane.getScene().getWindow()).close();
    }

    @FXML
    void newGame(ActionEvent event) {
        launchWindow("fxml/board/create-board.fxml", "Create new game", Modality.APPLICATION_MODAL,"css/create-game.css");
        rowsTF.setTooltip(colRowTT);
        columnsTF.setTooltip(colRowTT);
        snakesTF.setTooltip(snakeLadderTT);
        laddersTF.setTooltip(snakeLadderTT);
    }

    //Pre Game

    @FXML
    void addPlayer(ActionEvent event) {
        launchWindow("fxml/board/add-player.fxml","Add Player",Modality.APPLICATION_MODAL,"css/create-game.css");
    }

    @FXML
    void cancelPlayer(ActionEvent event) {
        ((Stage)playernameTF.getScene().getWindow()).close();
    }

    @FXML
    void confirmPlayer(ActionEvent event) {
        String wrong = "";
        try {
            String playerName = playernameTF.getText();
            if (playerName.isEmpty()) throw new IllegalStateException("Please introduce a valid player name. Try again");
            String color;
            if (redRB.isSelected()) {
                redRB.setDisable(true);
                color = Colors.getHexValue(0);
            }
            else if (orangeRB.isSelected()) {
                orangeRB.setDisable(true);
                color = Colors.getHexValue(1);
            }
            else if (cyanRB.isSelected()) {
                cyanRB.setDisable(true);
                color = Colors.getHexValue(2);
            }
            else if (darkBlueRB.isSelected()) {
                darkBlueRB.setDisable(true);
                color = Colors.getHexValue(3);
            }
            else if (yellowRB.isSelected()) {
                yellowRB.setDisable(true);
                color = Colors.getHexValue(4);
            }
            else if (greenRB.isSelected()) {
                greenRB.setDisable(true);
                color = Colors.getHexValue(5);
            }
            else if (pinkRB.isSelected()) {
                pinkRB.setDisable(true);
                color = Colors.getHexValue(6);
            }
            else if (purpleRB.isSelected()) {
                purpleRB.setDisable(true);
                color = Colors.getHexValue(7);
            }
            else if (limeRB.isSelected()) {
                limeRB.setDisable(true);
                color = Colors.getHexValue(8);
            }
            else throw new IllegalStateException("No color selection. Try again");

            playersLV.getItems().add(playerName);
            //game.getBoard().addG() <- Pending

        } catch (IllegalStateException ise) {
            wrong = ise.getMessage();
        } finally {
            if (!wrong.isEmpty()) {
                launchWindow("fxml/dialogue.fxml","Error",Modality.APPLICATION_MODAL,"css/dialogue-green.css");
                dialogueLBL.setText(wrong);
            } else {
                ((Stage)playernameTF.getScene().getWindow()).close();
            }
        }
    }

    @FXML
    void startGame(ActionEvent event) {
        String wrong = "";
        try {
            int rows = Integer.parseInt(rowsTF.getText());
            int columns = Integer.parseInt(columnsTF.getText());
            int snakes = Integer.parseInt(snakesTF.getText());
            int ladders = Integer.parseInt(laddersTF.getText());
            int players = playersLV.getItems().size();
            int size = rows * columns;
            int specialTiles = (2 * snakes) + (2 * ladders);

            if (rowsTF.getText().isEmpty() || columnsTF.getText().isEmpty() || snakesTF.getText().isEmpty() || laddersTF.getText().isEmpty()) {
                wrong = "Some fields are empty. Try again";
                throw new IllegalStateException();
            }

            if (size > 144) throw new ArithmeticException("The size is too big. Please remain between 4 and 10");
            else if (size < 16) throw new ArithmeticException("The size is too small. Please remain between 4 and 10");
            if (specialTiles * 2 > size) throw new ArithmeticException("There are too many snakes and ladders. Make sure you don't exceed a quarter of the tiles");
            if (players < 2) throw new ArithmeticException("No players detected. Please try again");

            game.startGame(rows,columns,snakes,ladders,players);
            ((Stage) playersLV.getScene().getWindow()).close();
            ((Stage) mainPane.getScene().getWindow()).close();
            GridPane board = boardGP;
            board = gridProperties(board);
            board = initializeBoard(0, board, 0, 0);
            launchWindow("fxml/board/board-pane.fxml", "Now playing!", Modality.NONE,"css/game.css");
            boardPane.setCenter(board);
        } catch (NumberFormatException nfe) {
            wrong = "Make sure you enter an integer number and try again.";
        } catch (ArithmeticException | IllegalStateException aise) {
            wrong = aise.getMessage();
            System.out.println(wrong);
        }
        finally {
            if (!wrong.isEmpty()) {
                launchWindow("fxml/dialogue.fxml","Error",Modality.APPLICATION_MODAL,"css/dialogue-blue.css");
                dialogueLBL.setText(wrong);
            }
        }
    }

    GridPane gridProperties(GridPane grid) {
        grid.setAlignment(Pos.CENTER);
        tileAP.setMinSize(750.0/game.getBoard().getColumns(),750.0/game.getBoard().getRows());
        tileAP.setMaxSize(750.0/game.getBoard().getColumns(),750.0/game.getBoard().getRows());
        tileAP.setPrefSize(750.0/game.getBoard().getColumns(),750.0/game.getBoard().getRows());
        grid.setPrefSize(750, 750);
        grid.setMinSize(750, 750);
        grid.setMaxSize(750, 750);
        grid.setHgap(5);
        grid.setVgap(5);
        return grid;
    }

    GridPane initializeBoard(int i, GridPane board, int y, int x) {
        System.out.println(" i es "+i);
        if(game.getBoard().getABox(i) != null){
            Label number = new Label(String.valueOf(game.getBoard().getABox(i).getPosition()));
            number.setId("tile-numbers");
            tileAP.getChildren().add(number);
        }
        if (i < game.getBoard().getSize()) {
            Parent tile;
            if (x == game.getBoard().getColumns()) {
                x = 0;
                y++;
            }
            System.out.println("-Column: " + (x + 1) + ", Row: " + (y + 1));
            tile = loadFxml("fxml/board/tile.fxml");
            tile.setId(pickId(x,y));
            board.add(tile,x,y);
            return initializeBoard(i + 1, board, y, x + 1);

        }

        return board;
    }

    String pickId(int x, int y) { //<-- Wouln't have to do this with loops + arrays (:
        String id;
        String polarity = ((x % 2 == 0) ? "1" : "0")
                + ((y % 2 == 0) ? "1" : "0");
        switch (polarity) {
            case "10":
            case "01":
                id = "even-tile";
                break;
            case "00":
            case "11":
                id = "odd-tile";
                break;
            default:
                throw new NullPointerException("Out of range (???): " + polarity);
        }
        return id;
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
        launchWindow("fxml/dialogue.fxml","Exit",Modality.APPLICATION_MODAL,"css/dialogue-orange.css");
        dialogueLBL.setText("Are you sure you want to end the game?");
        bBarHBOX.setSpacing(10.0);
        confirmDialogueBTN.setId("close-button");
        confirmDialogueBTN.setMinHeight(Button.USE_COMPUTED_SIZE);
        confirmDialogueBTN.setText("End Game");
        dialogueButton.setText("Cancel");
    }
}