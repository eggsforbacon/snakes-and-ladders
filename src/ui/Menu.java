package ui;

import model.Board;

public class Menu {
	
	private Board board;
    public void startProgram() {
    	board = new Board(4,5,4,4,4);
    	System.out.println(board.getMatrix());
    	System.out.println("Hola?");
    }
}
