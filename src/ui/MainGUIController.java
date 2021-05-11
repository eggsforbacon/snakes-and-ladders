package ui;

import exceptions.GameAlreadyWonException;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.concurrent.Task;
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
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Rectangle;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import model.Colors;
import model.Game;
import model.Player;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

public class MainGUIController implements Initializable {

    /****************************************FIELDS****************************************/

    /*Splash Screen*/
    @FXML
    private BorderPane preloaderPane = new BorderPane();

    @FXML
    private Label progress = new Label();

    public static Label label;

    /*Dialogue Pane*/

    @FXML
    private BorderPane dialoguePane = new BorderPane();

    @FXML
    private Label dialogueLBL = new Label();

    @FXML
    private Button dialogueButton = new Button();

    @FXML
    private HBox bBarHBOX = new HBox();

    @FXML
    private Button confirmDialogueBTN = new Button();


    /*Main Pane*/

    @FXML
    private BorderPane mainPane = new BorderPane();

    @FXML
    private Button newGameBTN = new Button();

    @FXML
    private Button scoreBoardBTN = new Button();

    @FXML
    private Button closeGameBTN = new Button();

    @FXML
    private Label mainTitleLBL = new Label();

    //New Game

    @FXML
    private TextField columnsTF = new TextField();

    @FXML
    private TextField rowsTF = new TextField();

    @FXML
    private TextField snakesTF = new TextField();

    @FXML
    private TextField laddersTF =  new TextField();

    Tooltip colRowTT = new Tooltip("Please use integer numbers between 4 and 10.\nGoing above 10 is discouraged to avoid\nmemory shortage.");

    Tooltip snakeLadderTT = new Tooltip("Keep the sum of snakes and ladders to a\nquarter of the size of the board to avoid\novercrowding.");

    @FXML
    private RadioButton redRB = new RadioButton();

    @FXML
    private RadioButton orangeRB = new RadioButton();

    @FXML
    private RadioButton cyanRB = new RadioButton();

    @FXML
    private RadioButton darkBlueRB = new RadioButton();

    @FXML
    private RadioButton yellowRB = new RadioButton();

    @FXML
    private RadioButton greenRB = new RadioButton();

    @FXML
    private RadioButton pinkRB = new RadioButton();

    @FXML
    private RadioButton purpleRB = new RadioButton();

    @FXML
    private RadioButton limeRB = new RadioButton();

    /*Game Pane*/

    @FXML
    private BorderPane boardPane = new BorderPane();

    @FXML
    private ImageView diceIMV = new ImageView();

    @FXML
    private Rectangle diceBorder = new Rectangle();

    @FXML
    private Label timerLBL = new Label();

    //Board

    @FXML
    private GridPane boardGP = new GridPane();

    @FXML
    private ListView<String> playersLV = new ListView<>();

    private Timer timer = new Timer();

    private int secs = 0, min = 0, hour = 0;

    @FXML
    private Label turnLBL = new Label();

    //Winning

    @FXML
    private TextField winnerTF = new TextField();

    //Tiles

    @FXML
    private BorderPane tileBP = new BorderPane();

    @FXML
    private Label numberLBL = new Label();

    @FXML
    private Label player1 = new Label();

    @FXML
    private Label player2 = new Label();

    @FXML
    private Label player3 = new Label();

    @FXML
    private Label player4 = new Label();

    @FXML
    private Label player5 = new Label();

    @FXML
    private Label player6 = new Label();

    @FXML
    private Label player7 = new Label();

    @FXML
    private Label player8 = new Label();

    @FXML
    private Label player9 = new Label();

    @FXML
    private Label specialLBL = new Label();

    /*Scoreboard*/

    @FXML
    private ListView<String> leaderboardLV = new ListView<>();

    @FXML
    private Label playerNameLBL = new Label();

    @FXML
    private Label scoreLBL = new Label();

    @FXML
    private Button winnerBTN = new Button();

    /*Fields*/

    Game game;

    private boolean endgameFlag = false;

    private boolean restartGameFlag = false;

    private boolean simulFlag = false;

    int turn = 0;

    private int unnamed = 1;

    public MainGUIController(Game game) {
        this.game = game;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        label = progress;
    }

    /***************************************METHODS***************************************/

    /*General*/

    private void launchWindow(String fxml, String title, Modality modality, StageStyle style, String stylesheet) {
        try {
            Parent loadedPane = loadFxml(fxml);
            Stage stage = new Stage();
            stage.setScene(new Scene(loadedPane));
            Image icon = new Image(String.valueOf(getClass().getResource("resources/snl-logo.png")));
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

    private void updateFlags(int key) {
        switch (key) {
            case 0:
                endgameFlag = false;
                restartGameFlag = false;
                simulFlag = false;
            case 1:
                endgameFlag = true;
                restartGameFlag = false;
                simulFlag = false;
                break;
            case 2:
                endgameFlag = false;
                restartGameFlag = true;
                simulFlag = false;
                break;
            case 3:
                endgameFlag = false;
                restartGameFlag = false;
                simulFlag = true;
                break;
            default:
                throw new IllegalStateException("Invalid key: " + key);
        }
    }

    /*Dialogue Pane*/

    @FXML
    void dismissDialogue(ActionEvent event) {
        ((Stage) dialoguePane.getScene().getWindow()).close();
    }

    @FXML
    void dismissInfo(ActionEvent event) {
        ((Stage) winnerBTN.getScene().getWindow()).close();
    }

    @FXML
    void confirmDialogue(ActionEvent event) {
        if (endgameFlag) {
            secs = 0;
            min = 0;
            hour = 0;
            timer.cancel();
            turn = 0;
            playersLV.getItems().clear();
            ((Stage) confirmDialogueBTN.getScene().getWindow()).close();
            ((Stage) playersLV.getScene().getWindow()).close();
            launchWindow("fxml/main-pane.fxml", "Snakes and Ladders: Start", Modality.NONE, StageStyle.DECORATED, "css/main.css");
        } else if (restartGameFlag) {
            secs = 0;
            min = 0;
            hour = 0;
            timer.cancel();
            turn = 0;
            playersLV.getItems().clear();
            ((Stage) confirmDialogueBTN.getScene().getWindow()).close();
            ((Stage) playersLV.getScene().getWindow()).close();
            launchWindow("fxml/board/create-board.fxml","Create new game", Modality.APPLICATION_MODAL, StageStyle.DECORATED, "css/create-game.css");
        } else if (simulFlag) {
            ((Stage) dialoguePane.getScene().getWindow()).close();
            runSimulation();
        }
    }

    void runSimulation() {
        if (!turnLBL.getText().contains("won")) {
            rollDice(null);
            Task<Void> sleeper = new Task<Void>() {
                @Override
                protected Void call() {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ignore) {}
                    return null;
                }
            };
            sleeper.setOnSucceeded(event -> runSimulation());
            new Thread(sleeper).start();
        }
    }

    /*Main Pane*/

    @FXML
    void closeGame(ActionEvent event) {
        ((Stage) mainPane.getScene().getWindow()).close();
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
            if (specialTiles * 2 > size)
                throw new ArithmeticException("There are too many snakes and ladders. Make sure you don't exceed a quarter of the tiles");
            if (!restartGameFlag) ((Stage) mainPane.getScene().getWindow()).close();
            else ((Stage) turnLBL.getScene().getWindow()).close();
            launchWindow("fxml/board/board-pane.fxml", "Now playing!", Modality.NONE, StageStyle.DECORATED, "css/game.css");

            String playerSymbols = "";
            if (redRB.isSelected()) {
                playersLV.getItems().add(Colors.getName(0));
                playerSymbols += Colors.getChar(0);
            }
            if (orangeRB.isSelected()) {
                playersLV.getItems().add(Colors.getName(1));
                playerSymbols += Colors.getChar(1);
            }
            if (cyanRB.isSelected()) {
                playersLV.getItems().add(Colors.getName(2));
                playerSymbols += Colors.getChar(2);
            }
            if (darkBlueRB.isSelected()) {
                playersLV.getItems().add(Colors.getName(3));
                playerSymbols += Colors.getChar(3);
            }
            if (yellowRB.isSelected()) {
                playersLV.getItems().add(Colors.getName(4));
                playerSymbols += Colors.getChar(4);
            }
            if (greenRB.isSelected()) {
                playersLV.getItems().add(Colors.getName(5));
                playerSymbols += Colors.getChar(5);
            }
            if (pinkRB.isSelected()) {
                playersLV.getItems().add(Colors.getName(6));
                playerSymbols += Colors.getChar(6);
            }
            if (purpleRB.isSelected()) {
                playersLV.getItems().add(Colors.getName(7));
                playerSymbols += Colors.getChar(7);
            }
            if (limeRB.isSelected()) {
                playersLV.getItems().add(Colors.getName(8));
                playerSymbols += Colors.getChar(8);
            }

            if (playersLV.getItems().size() < 2)
                throw new ArithmeticException("No player selection or not enough players selected. Try again");

            ((Stage) redRB.getScene().getWindow()).close();
            game.startGame(rows, columns, snakes, ladders, playerSymbols);
            boardGP = new GridPane();
            GridPane board = new GridPane();
            board = gridProperties(board);
            board = initializeBoard(0, board, 0, 0);
            boardGP = board;
            boardPane.setCenter(boardGP);
            hour = 0;
            min = 0;
            secs = 0;
            timer = new Timer();
            TimerTask task = new TimerTask() {
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

            timer.scheduleAtFixedRate(task, 1000, 1000);
            Image face1 = new Image(String.valueOf(getClass().getResource("resources/dice/1.png")));
            diceIMV.setImage(face1);
            turn = 0;
            turnLBL.setText("It's " + playersLV.getItems().get(turn) + "'s turn!");
            turn++;
        } catch (NumberFormatException nfe) {
            wrong = "Make sure you enter an integer number and try again.";
        } catch (ArithmeticException | IllegalStateException aise) {
            wrong = aise.getMessage();
        } finally {
            if (!wrong.isEmpty()) {
                launchWindow("fxml/dialogue.fxml", "Error", Modality.APPLICATION_MODAL, StageStyle.DECORATED, "css/dialogue-blue.css");
                dialogueLBL.setText(wrong);
            }
        }
    }

    GridPane gridProperties(GridPane grid) {
        grid.setAlignment(Pos.CENTER);
        tileBP.setMinSize(850.0 / game.getBoard().getColumns(), 850.0 / game.getBoard().getRows());
        tileBP.setMaxSize(850.0 / game.getBoard().getColumns(), 850.0 / game.getBoard().getRows());
        tileBP.setPrefSize(850.0 / game.getBoard().getColumns(), 850.0 / game.getBoard().getRows());
        grid.setPrefSize(850, 850);
        grid.setMinSize(850, 850);
        grid.setMaxSize(850, 850);
        grid.setHgap(5);
        grid.setVgap(5);
        return grid;
    }

    GridPane initializeBoard(int i, GridPane board, int y, int x) {

        if (game.getBoard().getABox(i) != null) {
            numberLBL.setText(String.valueOf(game.getBoard().getABox(i).getPosition()));
            numberLBL.setId("tile-numbers");
            specialLBL.setText(String.valueOf(game.getBoard().getABox(i).getBoxInformation()));
            if (Character.isDigit(game.getBoard().getABox(i).getBoxInformation().charAt(0))) specialLBL.setId("ladder");
            else specialLBL.setId("snake");
            //The following lines of code are a homage to senior C# developer, yanderedev <- for legal reasons that's a joke
            if (game.getBoard().getABox(i).getPieceString().contains("*")) {
                player1.setText("O");
                player1.setStyle("\n-fx-text-fill: " + Colors.getHexWithChar('*') + ";\n-fx-font-weight: bold;\n-fx-font-size: 17px;");
            } if (game.getBoard().getABox(i).getPieceString().contains("!")) {
                player2.setText("O");
                player2.setStyle("\n-fx-text-fill: " + Colors.getHexWithChar('!') + ";\n-fx-font-weight: bold;\n-fx-font-size: 17px;");
            } if (game.getBoard().getABox(i).getPieceString().contains("O")) {
                player3.setText("O");
                player3.setStyle("\n-fx-text-fill: " + Colors.getHexWithChar('O') + ";\n-fx-font-weight: bold;\n-fx-font-size: 17px;");
            } if (game.getBoard().getABox(i).getPieceString().contains("X")) {
                player4.setText("O");
                player4.setStyle("\n-fx-text-fill: " + Colors.getHexWithChar('X') + ";\n-fx-font-weight: bold;\n-fx-font-size: 17px;");
            } if (game.getBoard().getABox(i).getPieceString().contains("%")) {
                player5.setText("O");
                player5.setStyle("\n-fx-text-fill: " + Colors.getHexWithChar('%') + ";\n-fx-font-weight: bold;\n-fx-font-size: 17px;");
            } if (game.getBoard().getABox(i).getPieceString().contains("$")) {
                player6.setText("O");
                player6.setStyle("\n-fx-text-fill: " + Colors.getHexWithChar('$') + ";\n-fx-font-weight: bold;\n-fx-font-size: 17px;");
            } if (game.getBoard().getABox(i).getPieceString().contains("#")) {
                player7.setText("O");
                player7.setStyle("\n-fx-text-fill: " + Colors.getHexWithChar('#') + ";\n-fx-font-weight: bold;\n-fx-font-size: 17px;");
            } if (game.getBoard().getABox(i).getPieceString().contains("+")) {
                player8.setText("O");
                player8.setStyle("\n-fx-text-fill: " + Colors.getHexWithChar('+') + ";\n-fx-font-weight: bold;\n-fx-font-size: 17px;");
            } if (game.getBoard().getABox(i).getPieceString().contains("&")) {
                player9.setText("O");
                player9.setStyle("\n-fx-text-fill: " + Colors.getHexWithChar('&') + ";\n-fx-font-weight: bold;\n-fx-font-size: 17px;");
            } // Optimization is for the weak minded. Optimization is nothing but a mere moral construct
        }
        if (i < game.getBoard().getSize()) {
            Parent tile;
            if (x == game.getBoard().getColumns()) {
                x = 0;
                y++;
            }
            tile = loadFxml("fxml/board/tile.fxml");
            tile.setId(pickId(x, y));
            board.add(tile, x, y);
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

    /*Game Pane*/

    @FXML
    void restartGame(ActionEvent event) {
        launchWindow("fxml/dialogue.fxml","Restart game",Modality.APPLICATION_MODAL,StageStyle.DECORATED,"css/dialogue-orange.css");
        dialogueLBL.setText("Are you sure you want to restart the game?");
        bBarHBOX.setSpacing(10.0);
        confirmDialogueBTN.setId("close-button");
        confirmDialogueBTN.setMinHeight(Button.USE_COMPUTED_SIZE);
        confirmDialogueBTN.setText("Restart");
        dialogueButton.setText("Cancel");
        updateFlags(2);
    }

    @FXML
    void simulation(ActionEvent event) {
        launchWindow("fxml/dialogue.fxml","Run Simulation", Modality.APPLICATION_MODAL,StageStyle.DECORATED,"css/dialogue-orange.css");
        dialogueLBL.setText("Are you sure you want to run the game on AutoPilot? (Start simulation");
        bBarHBOX.setSpacing(10.0);
        confirmDialogueBTN.setId("close-button");
        confirmDialogueBTN.setMinHeight(Button.USE_COMPUTED_SIZE);
        confirmDialogueBTN.setText("Run");
        dialogueButton.setText("Cancel");
        updateFlags(3);
    }

    @FXML
    void rollDice(MouseEvent event) {
        GameAlreadyWonException e = new GameAlreadyWonException();
        int dice;
        String message = game.move();
        dice = game.getDice();
        GridPane board = new GridPane();
        board = gridProperties(board);
        initializeBoard(0, board, 0, 0);
        boardPane.setCenter(board);
        FadeTransition pop = new FadeTransition();
        pop.setDuration(Duration.millis(1000));
        pop.setFromValue(1.0);
        pop.setToValue(0.0);
        pop.setNode(diceBorder);
        diceIMV.setImage(new Image(String.valueOf(getClass().getResource("resources/dice/" + dice + ".png"))));
        pop.play();
        turnLBL.setText("It's " + playersLV.getItems().get(turn) + "'s turn!");
        int prev = turn - 1;
        turn++;
        if (turn == game.getBoard().getPlayers()) turn = 0;
        if (prev < 0) prev = game.getBoard().getPlayers() - 1;
        if (message.contains(e.getMessage())) {
            board = gridProperties(board);
            initializeBoard(0, board, 0, 0);
            boardPane.setCenter(board);
            turnLBL.setText(playersLV.getItems().get(prev) + " has won!");
            turnLBL.setId("turn-lbl-won");
            timerLBL.setId("timer-lbl-won");
            launchWindow("fxml/board/game-won.fxml", "We have a winner!", Modality.APPLICATION_MODAL, StageStyle.UNDECORATED, "css/create-game.css");
        }
    }

    @FXML
    void endGame(ActionEvent event) {
        launchWindow("fxml/dialogue.fxml", "Exit", Modality.APPLICATION_MODAL, StageStyle.DECORATED, "css/dialogue-orange.css");
        dialogueLBL.setText("Are you sure you want to end the game?");
        bBarHBOX.setSpacing(10.0);
        confirmDialogueBTN.setId("close-button");
        confirmDialogueBTN.setMinHeight(Button.USE_COMPUTED_SIZE);
        confirmDialogueBTN.setText("End Game");
        dialogueButton.setText("Cancel");
        updateFlags(1);
    }

    @FXML
    void exitAfterWin(ActionEvent event) {
        if (winnerTF.getText().isEmpty()) {
            winnerTF.setText("Unnamed Winner " + unnamed);
            unnamed += 1;
        }
        game.createWinner(winnerTF.getText());
        ((Stage) winnerTF.getScene().getWindow()).close();
        ((Stage) timerLBL.getScene().getWindow()).close();
        launchWindow("fxml/main-pane.fxml", "Snakes and Ladders: Start", Modality.NONE, StageStyle.DECORATED, "css/main.css");
        timer.cancel();
        updateFlags(0);
    }

    /*Scoreboard*/

    @FXML
    void scoreBoard(ActionEvent event) {
        ((Stage) mainPane.getScene().getWindow()).close();
        launchWindow("fxml/leaderboard.fxml", "Leaderboard", Modality.NONE, StageStyle.DECORATED, "css/leaderboard.css");
        fillLB(game.getBestScores());
    }

    void fillLB(Player bestScores) {
        if (bestScores != null) {
            leaderboardLV.getItems().add(bestScores.getName());
            fillLB(bestScores.getRight());
        }
    }

    @FXML
    void showPlayerInfo(ContextMenuEvent event) {
        try {
            String desired = leaderboardLV.getSelectionModel().getSelectedItem();
            Player current = advanceSearch(desired, game.getBestScores());
            launchWindow("fxml/winner-info.fxml","Winner info",Modality.APPLICATION_MODAL,StageStyle.DECORATED,"css/leaderboard.css");
            playerNameLBL.setText(current.getName());
            scoreLBL.setText("" + current.getScore());
        } catch (NullPointerException ignored) {}
    }

    Player advanceSearch(String desired, Player current) {
        if (desired.equals(current.getName())) return current;
        else return advanceSearch(desired, current.getRight());
    }

    @FXML
    void goBackHome(ActionEvent event) {
        ((Stage) leaderboardLV.getScene().getWindow()).close();
        launchWindow("fxml/main-pane.fxml", "Snakes and Ladders: Start", Modality.NONE, StageStyle.DECORATED, "css/main.css");
    }

    public Timer getTimer() {
        return timer;
    }
}