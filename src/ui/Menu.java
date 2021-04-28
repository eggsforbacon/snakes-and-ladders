package ui;

import exceptions.GameAlreadyWonException;
import model.Board;
import model.Game;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
	private Game game;
	private Scanner sc;
	private final static int  PLAY = 1;
	private final static int SEE_LEADERBOARD = 2;
	private final static int EXIT = 3;

	public void showMenuOptions(){
		System.out.println("******** Snakes and ladders ********");
		System.out.println("Enter 1 to play");
		System.out.println("Enter 2 to see leaderboard");
		System.out.println("Enter 3 to exit");
		System.out.println("");
	}

	public void start(int started) {
		int option = -1;
		if(started != 3){
			showMenuOptions();
			try {
				option = sc.nextInt();
			} catch(InputMismatchException xd) {
				System.out.println("Advertencia: Por favor ingrese unicamente numeros");
				option = 0;
			}
			sc.nextLine();
			doAnOption(option);
			start(option);
		}

	}

	public void doAnOption(int option) {
		switch(option) {
			case PLAY:
				play();
				break;
			case SEE_LEADERBOARD:

				break;
			case EXIT:
				break;
			default: System.out.println("Ingrese una opcion valida");
		}
	}

	public void play(){
		game = new Game();
		System.out.println("First create the board");
		System.out.println("Enter the number of rows, the number of columns, the number of ladders, the number of snakes and the players");
		System.out.println("If you want the game pieces to be distributed randomly, enter the data as in the following example: ");
		System.out.println("5 4 3 2 4");
		System.out.println("If you want to choose the game pieces, enter the data as in the following example: ");
		System.out.println("5 4 3 2 &%#!");
		String[] info = sc.nextLine().split(" ");
		int rows = Integer.parseInt(info[0]);
		int columns = Integer.parseInt(info[1]);
		int ladders = Integer.parseInt(info[2]);
		int snakes = Integer.parseInt(info[3]);
		if((ladders+snakes)*2<(rows*columns)/2){
			if(isInteger(info[4])){
				int players = Integer.parseInt(info[4]);
				if(players > 9){
					System.out.println("The maximum number of players is 9. Try again");
					play();
				}
				else{
					game.startGame(rows,columns,snakes,ladders,players);
				}
			}
			else{
				game.startGame(rows,columns,snakes,ladders,info[4]);
			}
		}
		else{
			System.out.println("The number of squares occupied by snakes and ladders must not exceed half the squares on the board. Try again");
			play();
		}

	}



	public static boolean isInteger(String s) {
		try {
			Integer.parseInt(s);
		} catch(NumberFormatException e) {
			return false;
		} catch(NullPointerException e) {
			return false;
		}
		// only got here if we didn't return false
		return true;
	}



	private Board board;
    public void startProgram() {
    	board = new Board(5,4,2,3,4);
    	char example = 1;
    	System.out.println(example);
    	System.out.println(board.getBoardInformation(true));
    	System.out.println(board.getBoxesInformation());
    	try{
			board.movePieces();
			System.out.println(board.getBoardInformation(false));
			board.movePieces();
			System.out.println(board.getBoardInformation(false));
			board.movePieces();
			System.out.println(board.getBoardInformation(false));
			board.movePieces();
			System.out.println(board.getBoardInformation(false));
			board.movePieces();
			System.out.println(board.getBoardInformation(false));
			board.movePieces();
			System.out.println(board.getBoardInformation(false));
			board.movePieces();
			System.out.println(board.getBoardInformation(false));
			board.movePieces();
			System.out.println(board.getBoardInformation(false));
			board.movePieces();
		} catch (GameAlreadyWonException w){
			System.out.println("Alguien ya gano");
		}

		System.out.println(board.getBoardInformation(false));
		System.out.println(board.getBoxesInformation());
    }
}
