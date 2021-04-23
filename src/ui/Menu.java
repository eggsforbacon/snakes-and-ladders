package ui;

import model.Board;

public class Menu {
	
	private Board board;
    public void startProgram() {
    	board = new Board(5,4,2,3,4);
    	char a = 32;
    	System.out.println(board.getMatrix());
    	System.out.println(board.getBoxesInformation());
    }
}
