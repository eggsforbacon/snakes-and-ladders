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

    public void rollTheDice(){
        int dice = (int) Math.floor(Math.random()*(6)+1);
        //System.out.println("salio un "+dice);
        previousBox = actualBox;
        actualBox += dice;
        //System.out.println("se mueve a la casilla "+actualBox);
        movements++;
    }

    public char getCharacter() {
        return character;
    }

    public void setCharacter(char character) {
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
