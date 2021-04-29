package ui;

import exceptions.GameAlreadyWonException;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import javafx.stage.StageStyle;
import model.*;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

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
    private TextField columnsTF;

    @FXML
    private TextField rowsTF;

    @FXML
    private TextField snakesTF;

    @FXML
    private TextField laddersTF;

    Tooltip colRowTT = new Tooltip("Please use integer numbers between 4 and 10.\nGoing above 10 is discouraged to avoid\nmemory shortage.");

    Tooltip snakeLadderTT = new Tooltip("Keep the sum of snakes and ladders to a\nquarter of the size of the board to avoid\novercrowding.");

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
    private Rectangle diceBorder;

    @FXML
    private Label timerLBL = new Label();

    //Board

    @FXML
    private GridPane boardGP = new GridPane();

    @FXML
    private ListView<String> playersLV = new ListView<>();

    private Timer timer = new Timer();

    private int secs = 0, min = 0, hour = 0;

    private TimerTask task;

    @FXML
    private Label turnLBL;

    //Winning

    @FXML
    private TextField winnerTF;

    //Tiles

    @FXML
    private BorderPane tileBP = new BorderPane();

    @FXML
    private Label numberLBL = new Label();

    @FXML
    private TilePane tilePane;

    /*Fields*/

    Game game;

    private boolean endgameFlag = false;

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

    private void launchWindow(String fxml, String title, Modality modality, StageStyle style, String stylesheet) {
        try {
            Parent loadedPane = loadFxml(fxml);
            Stage stage = new Stage();
            stage.setScene(new Scene(loadedPane));
            Image icon = new Image(String.valueOf(getClass().getResource("resources/snl-logo.png")));
            stage.setOnCloseRequest(e -> {
                Platform.exit();
                System.exit(0);
            });
            stage.getIcons().add(icon);
            stage.getScene().getStylesheets().addAll(String.valueOf(getClass().getResource(stylesheet)));
            stage.setTitle(title);
            stage.initModality(modality);
            stage.initStyle(style);
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
        if (endgameFlag) {
            playersLV.getItems().clear();
            ((Stage) confirmDialogueBTN.getScene().getWindow()).close();
            timer.cancel();
            secs = 0; min = 0; hour = 0;
            ((Stage) boardGP.getScene().getWindow()).close();
            launchWindow("fxml/main-pane.fxml", "Snakes and Ladders: Start", Modality.NONE, StageStyle.DECORATED, "css/main.css");
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
    void closeGame(ActionEvent event) {
        ((Stage)mainPane.getScene().getWindow()).close();
    }

    @FXML
    void newGame(ActionEvent event) {
        launchWindow("fxml/board/create-board.fxml", "Create new game", Modality.APPLICATION_MODAL, StageStyle.DECORATED, "css/create-game.css");
        rowsTF.setTooltip(colRowTT);
        columnsTF.setTooltip(colRowTT);
        snakesTF.setTooltip(snakeLadderTT);
        laddersTF.setTooltip(snakeLadderTT);
    }

    //Pre Game

    @FXML
    void startGame(ActionEvent event) {
        String wrong = "";
        game.restartGame();
        try {
            int rows = Integer.parseInt(rowsTF.getText());
            int columns = Integer.parseInt(columnsTF.getText());
            int snakes = Integer.parseInt(snakesTF.getText());
            int ladders = Integer.parseInt(laddersTF.getText());
            int size = rows * columns;
            int specialTiles = (2 * snakes) + (2 * ladders);

            if (rowsTF.getText().isEmpty() || columnsTF.getText().isEmpty() || snakesTF.getText().isEmpty() || laddersTF.getText().isEmpty()) {
                wrong = "Some fields are empty. Try again";
                throw new IllegalStateException();
            }

            if (size > 144) throw new ArithmeticException("The size is too big. Please remain between 4 and 10");
            else if (size < 16) throw new ArithmeticException("The size is too small. Please remain between 4 and 10");
            if (specialTiles * 2 > size) throw new ArithmeticException("There are too many snakes and ladders. Make sure you don't exceed a quarter of the tiles");
            ((Stage) mainPane.getScene().getWindow()).close();
            launchWindow("fxml/board/board-pane.fxml", "Now playing!", Modality.NONE, StageStyle.DECORATED, "css/game.css");

            if (redRB.isSelected()) {
                playersLV.getItems().add(Colors.getName(0));

            }
            if (orangeRB.isSelected()) playersLV.getItems().add(Colors.getName(1));
            if (cyanRB.isSelected()) playersLV.getItems().add(Colors.getName(2));
            if (darkBlueRB.isSelected()) playersLV.getItems().add(Colors.getName(3));
            if (yellowRB.isSelected()) playersLV.getItems().add(Colors.getName(4));
            if (greenRB.isSelected()) playersLV.getItems().add(Colors.getName(5));
            if (pinkRB.isSelected()) playersLV.getItems().add(Colors.getName(6));
            if (purpleRB.isSelected()) playersLV.getItems().add(Colors.getName(7));
            if (limeRB.isSelected()) playersLV.getItems().add(Colors.getName(8));

            if (playersLV.getItems().size() < 2) throw new ArithmeticException("No player selection or not enough players selected. Try again");

            ((Stage) redRB.getScene().getWindow()).close();
            int players = playersLV.getItems().size();
            game.startGame(rows,columns,snakes,ladders,players);
            boardGP = new GridPane();
            GridPane board = boardGP;
            board = gridProperties(board);
            board = initializeBoard(0, board, 0, 0);
            boardPane.setCenter(board);
            timer = new Timer();

            task = new TimerTask() {
                @Override
                public void run() {
                    secs++;
                    if (secs > 59) {
                        secs = 0;
                        min++;
                    }
                    if (min > 59) {
                        min = 0;
                        hour++;
                    }
                    Platform.runLater(() -> timerLBL.setText(String.format("%02d:%02d:%02d", hour, min, secs)));
                }
            };

            timer.scheduleAtFixedRate(task,1000,1000);
            Image face1 = new Image(String.valueOf(getClass().getResource("resources/dice/1.png")));
            diceIMV.setImage(face1);
        } catch (NumberFormatException nfe) {
            wrong = "Make sure you enter an integer number and try again.";
        } catch (ArithmeticException | IllegalStateException aise) {
            wrong = aise.getMessage();
            System.out.println(wrong);
        }
        finally {
            if (!wrong.isEmpty()) {
                launchWindow("fxml/dialogue.fxml","Error",Modality.APPLICATION_MODAL, StageStyle.DECORATED, "css/dialogue-blue.css");
                dialogueLBL.setText(wrong);
            }
        }
    }

    GridPane gridProperties(GridPane grid) {
        grid.setAlignment(Pos.CENTER);
        tileBP.setMinSize(750.0/game.getBoard().getColumns(),750.0/game.getBoard().getRows());
        tileBP.setMaxSize(750.0/game.getBoard().getColumns(),750.0/game.getBoard().getRows());
        tileBP.setPrefSize(750.0/game.getBoard().getColumns(),750.0/game.getBoard().getRows());
        grid.setPrefSize(750, 750);
        grid.setMinSize(750, 750);
        grid.setMaxSize(750, 750);
        grid.setHgap(5);
        grid.setVgap(5);
        return grid;
    }

    GridPane initializeBoard(int i, GridPane board, int y, int x) {

        if(game.getBoard().getABox(i) != null){
            numberLBL.setText(String.valueOf(game.getBoard().getABox(i).getPosition()));
            numberLBL.setId("tile-numbers");
            numberLBL.setStyle("\n-fx-text-fill: red;");

        }
        if (i < game.getBoard().getSize()) {
            Parent tile;
            if (x == game.getBoard().getColumns()) {
                x = 0;
                y++;
            }
            tile = loadFxml("fxml/board/tile.fxml");
            tile.setId(pickId(x,y));

            if (game.getBoard().getABox(0) != null){
                GamePiece piece = game.getBoard().getABox(i).getPiece();
                if (piece != null) {
                    tilePane.setStyle("\n-fx-background-color: black;");
                }
            }
            board.add(tile,x,y);
            return initializeBoard(i + 1, board, y, x + 1);

        }

        return board;
    }

    String pickId(int x, int y) {
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
        //THIS IS A TEMPORAL IMPLEMENTATION. REAL IMPLEMENTATION WILL COME WITH DICE THROW FROM GAME PIECES
        int dice = 0;
        try {
            dice = game.getBoard().movePieces();
            GridPane board = new GridPane();
            board = gridProperties(board);
            board = initializeBoard(0, board, 0, 0);
            FadeTransition pop = new FadeTransition();
            pop.setDuration(Duration.millis(1000));
            pop.setFromValue(1.0);
            pop.setToValue(0.0);
            pop.setNode(diceBorder);
            System.out.println(dice);
            diceIMV.setImage(new Image(String.valueOf(getClass().getResource("resources/dice/" + dice + ".png"))));
            pop.play();
        } catch (GameAlreadyWonException ignore) {
            launchWindow("fxml/board/game-won.fxml","We have a winner!",Modality.APPLICATION_MODAL, StageStyle.UNDECORATED,"css/create-game.css");
        }

    }

    @FXML
    void endGame(ActionEvent event) {
        launchWindow("fxml/dialogue.fxml","Exit",Modality.APPLICATION_MODAL, StageStyle.DECORATED, "css/dialogue-orange.css");
        dialogueLBL.setText("Are you sure you want to end the game?");
        bBarHBOX.setSpacing(10.0);
        confirmDialogueBTN.setId("close-button");
        confirmDialogueBTN.setMinHeight(Button.USE_COMPUTED_SIZE);
        confirmDialogueBTN.setText("End Game");
        dialogueButton.setText("Cancel");
        endgameFlag = true;
    }

    @FXML
    void exitAfterWin(ActionEvent event) {
        game.createWinner(winnerTF.getText());
        ((Stage)winnerTF.getScene().getWindow()).close();
        ((Stage) timerLBL.getScene().getWindow()).close();
        launchWindow("fxml/main-pane.fxml","Snakes and Ladders: Start",Modality.NONE,StageStyle.DECORATED,"css/main.css");
    }

    public static void wait(int millis) {
        try {
            Thread.sleep(millis);
        } catch (Exception e) {
            e.fillInStackTrace();
        }
    }

    public Timer getTimer() {
        return timer;
    }
}