package ui;

import model.Board;

public class Menu {
	
	private Board board;
    public void startProgram() {
    	board = new Board(3,6,2,3,4);
    	System.out.println(board.getBoardInformation());
    	System.out.println(board.getBoxesInformation());
    	System.out.println(board.gpAt(2).getCharacter());
    	board.movePieces();
		System.out.println(board.getBoardInformation());
		System.out.println(board.gpAt(0));
		board.movePieces();
		System.out.println(board.getBoardInformation());
    }
}
