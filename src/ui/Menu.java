package ui;

import model.Board;

public class Menu {
	
	private Board board;
    public void startProgram() {
    	board = new Board(6,2,1,3,4);
    	System.out.println(board.getMatrix());
    }
}
