package ui;

import model.Board;

public class Menu {
	
	private Board board;
    public void startProgram() {
    	board = new Board(5,4,2,3,4);
    	System.out.println(board.getBoardInformation());
    	System.out.println(board.getBoxesInformation());
    	board.movePieces();
		System.out.println(board.getBoardInformation());
		board.movePieces();
		System.out.println(board.getBoardInformation());
    }
}
