package ui;

import model.Board;

public class Menu {
	
	private Board board;
    public void startProgram() {
    	board = new Board(4,5,1,4,4);
    	System.out.println(board.getMatrix());
    }
}
