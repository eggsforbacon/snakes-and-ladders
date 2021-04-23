package ui;

import model.Board;

public class Menu {
	
	private Board board;
    public void startProgram() {
    	board = new Board(5,4,2,3,4);
    	char a = 34;
    	System.out.println(a+" este caracter");
    	System.out.println(board.getBoardInformation());
    	System.out.println(board.getBoxesInformation());
    }
}
