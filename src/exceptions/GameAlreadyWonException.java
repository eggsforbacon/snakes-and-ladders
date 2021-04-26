package exceptions;

public class GameAlreadyWonException extends Exception{
    public GameAlreadyWonException(){
        super("You cannot continue playing on this board as the game has already been won.");
    }
}
