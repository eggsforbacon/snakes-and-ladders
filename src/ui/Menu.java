package ui;

import model.Board;

public class Menu {
	
	private Board board;
    public void startProgram() {
    	board = new Board(3,7,1,5,4);
    	System.out.println(board.getMatrix());
    }
}
