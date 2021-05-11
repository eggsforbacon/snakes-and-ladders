package model;

import exceptions.GameAlreadyWonException;

import java.io.Serializable;

public class Game implements Serializable {
    private Board gameBoard;
    private Player actualWinner;
    private Player bestScores;
    private int dice;
    private static final long serialVersionUID = 1;

    public Game(){
        gameBoard=null;
        bestScores=null;
        actualWinner=null;
        dice = 0;
    }

    public void startGame(int rows,int columns,int snakes, int ladders, int players){
        gameBoard = new Board(rows,columns,snakes,ladders,players);
    }
    public void startGame(int rows,int columns,int snakes,int ladders,String players){
        gameBoard = new Board(rows,columns,snakes,ladders,players);
    }
    public void restartGame(){
        gameBoard = null;
    }
    public String move(){
        if(gameBoard != null){
            try{
                dice = gameBoard.movePieces();
                return gameBoard.getMovementInformation();
            } catch(GameAlreadyWonException w){
                String message;
                message = gameBoard.getMovementInformation()+"\n"+"Player "+gameBoard.getWinnerGP().getCharacter()+" has won the game! Congratulations"+"\n";
                double score = (double) gameBoard.getWinnerGP().getMovements()/gameBoard.getSize();
                int rows = gameBoard.getRows();
                int columns = gameBoard.getColumns();
                int ladders = gameBoard.getLadders();
                int snakes = gameBoard.getSnakes();
                int players = gameBoard.getPlayers();
                String characters = gameBoard.getCharacters();
                char wChar = gameBoard.getWinnerPiece();
                actualWinner = new Player("",score,rows,columns,ladders,snakes,players,characters,wChar);
                message += w.getMessage();
                return message;
            }
        }
        else{
            return "No board created";
        }
    }

    public void createWinner(String name){
        Player newPlayer = new Player(name,actualWinner.getScore(), actualWinner.getRows(), actualWinner.getColumns(), actualWinner.getLadders(), actualWinner.getSnakes(), actualWinner.getPlayers(), actualWinner.getCharacters(), actualWinner.getWinner());
        bestScores = insertPlayer(bestScores,newPlayer);
    }
    public Player insertPlayer(Player r, Player newPlayer)
    {
        if (r == null) {
            r = newPlayer;
            return r;
        }
        if (newPlayer.getScore() <= r.getScore())
            r.setLeft(insertPlayer(r.getLeft(),newPlayer));
        else if (newPlayer.getScore() > r.getScore())
            r.setRight(insertPlayer(r.getRight(),newPlayer));

        return r;
    }



    public Board getBoard() {
        return gameBoard;
    }

    public Player getActualWinner() {
        return actualWinner;
    }

    public void setActualWinner(Player actualWinner) {
        this.actualWinner = actualWinner;
    }

    public Player getBestScores() {
        return bestScores;
    }

    public void setBestScores(Player bestScores) {
        this.bestScores = bestScores;
    }

    public int getDice() {
        return dice;
    }

    public void setDice(int dice) {
        this.dice = dice;
    }
}
