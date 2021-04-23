package model;

public class GamePiece {
    private String character;
    private String path;
    private GamePiece next;
    private int number;
    private int previousBox;
    private int actualBox;
    private int movements;

    public GamePiece(String character,String path){
        this.character=character;
        this.path = path;
        next = null;
        actualBox = 0;
        movements = 0;
    }

    public void rollTheDice(){
        int dice = (int) Math.floor(Math.random()*(5)+1);
        previousBox = actualBox;
        actualBox += dice;
        movements++;
    }

    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
        this.character = character;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
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

    public void setPreviousBox(int previousBox) {
        this.previousBox = previousBox;
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

    public void setMovements(int movements) {
        this.movements = movements;
    }


}
