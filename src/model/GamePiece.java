package model;

public class GamePiece {
    private char character;
    private String path;
    private GamePiece next;
    private int number;
    private int previousBox;
    private int actualBox;
    private int movements;

    public GamePiece(char character,String path,int number){
        this.character=character;
        this.path = path;
        this.number = number;
        next = null;
        actualBox = 0;
        movements = 0;
    }

    public int rollTheDice(){
        int dice = (int) Math.floor(Math.random()*(6)+1);
        previousBox = actualBox;
        actualBox += dice;
        movements++;
        return dice;
    }

    public char getCharacter() {
        return character;
    }

    public String getPath() {
        return path;
    }

    public int getNumber() {
        return number;
    }

    public GamePiece getNext() {
        return next;
    }

    public void setNext(GamePiece next) {
        this.next = next;
    }

    public int getPreviousBox() {
        return previousBox;
    }

    public int getActualBox() {
        return actualBox;
    }

    public void setActualBox(int actualBox) {
        this.actualBox = actualBox;
    }

    public int getMovements() {
        return movements;
    }
}
