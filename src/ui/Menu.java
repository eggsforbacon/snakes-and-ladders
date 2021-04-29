package ui;

import exceptions.GameAlreadyWonException;
import model.Board;
import model.Game;
import model.Player;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Menu {
	private Game game;
	private Scanner sc;
	private final static int  PLAY = 1;
	private final static int SEE_LEADERBOARD = 2;
	private final static int EXIT = 3;

	public Menu(){
		game = new Game();
		sc = new Scanner(System.in);
	}

	public void showMenuOptions(){
		System.out.println("******** Snakes and ladders ********");
		System.out.println("Enter 1 to play");
		System.out.println("Enter 2 to see leaderboard");
		System.out.println("Enter 3 to exit");
		System.out.println("************************************");
	}

	public void start(int started) {
		int option = -1;
		if(started != 3){
			showMenuOptions();
			try {
				option = sc.nextInt();
			} catch(InputMismatchException xd) {
				System.out.println("Please enter only integer numbers from 1 to 3");
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
				if(game != null){
					seeLeaderboard(game.getBestScores());
				}else{
					System.out.println("There are no scores yet");
				}
				
				break;
			case EXIT:
				break;
			default: System.out.println("Choose a valid option");
		}
	}
	
	public void seeLeaderboard(Player score){
		if (score != null) {
			seeLeaderboard(score.getLeft());
			System.out.println(score.getName() + " ");
			seeLeaderboard(score.getRight());
		}

		
	}

	public void play(){
		System.out.println("First create the board");
		System.out.println("Enter the number of rows, the number of columns, the number of ladders, the number of snakes and the players");
		System.out.println("If you want the game pieces to be distributed randomly, enter the data as in the following example: ");
		System.out.println("5 4 3 2 4");
		System.out.println("If you want to choose the game pieces, enter the data as in the following example: ");
		System.out.println("5 4 3 2 &%#!");
		String[] info = sc.nextLine().split(" ");
		try{
			int rows = Integer.parseInt(info[0]);
			int columns = Integer.parseInt(info[1]);
			int ladders = Integer.parseInt(info[2]);
			int snakes = Integer.parseInt(info[3]);
			if((ladders+snakes)*2<=(rows*columns)/2){
				if(isInteger(info[4])){
					int players = Integer.parseInt(info[4]);
					if(players > 9 || players < 2){
						System.out.println("The maximum number of players is 9 and the minimum is 2. Try again");
						play();
					}
					else{
						game.startGame(rows,columns,snakes,ladders,players);
						System.out.println(game.getBoard().getBoardInformation(true));
						System.out.println(game.getBoard().getBoxesInformation());
						System.out.println("Enter a line break if you want to go to the next turn");
						System.out.println("Enter num if you want to see the initial board");
						System.out.println("Enter simul if you want the game to play itself");
						System.out.println("Enter menu to return and end the game");
						startGameOptions(false);
					}
				}
				else{
					if(info[4].length() > 9){
						System.out.println("The maximum number of players is 9. Try again");
						play();
					}
					else{
						game.startGame(rows,columns,snakes,ladders,info[4]);
						System.out.println(game.getBoard().getBoardInformation(true));
						System.out.println(game.getBoard().getBoxesInformation());
						System.out.println("Enter a line break if you want to go to the next turn");
						System.out.println("Enter num if you want to see the initial board");
						System.out.println("Enter simul if you want the game to play itself");
						System.out.println("Enter menu to return and end the game");
						startGameOptions(false);
					}
				}
			}
			else{
				System.out.println("The number of squares occupied by snakes and ladders must not exceed half the squares on the board. Try again");
				play();
			}
		}catch (NumberFormatException e){
			System.out.println("Choose a valid option");
		}


	}

	public void startGameOptions(boolean finish) {
		String option = "";
		if(!finish){
			option = sc.nextLine();
			finish=doGameOptions(option);
			startGameOptions(finish);
		}

	}

	public boolean doGameOptions(String option){
		Boolean finish = false;
		GameAlreadyWonException w = new GameAlreadyWonException();
		switch(option) {
			case "":
				String movement = game.move();
				System.out.println(movement);
				if(movement.contains(w.getMessage())){
					System.out.println("Enter the name of the winner");
					String name = sc.nextLine();
					game.createWinner(name);
					finish = true;
				}
				break;
			case "num":
				System.out.println(game.getBoard().getBoardInformation(true));
				System.out.println(game.getBoard().getBoxesInformation());
				sc.nextLine();
				System.out.println(game.getBoard().getBoardInformation(false));
				break;
			case "simul":
				simulation(w.getMessage());
				System.out.println("Enter the name of the winner");
				String name = sc.nextLine();
				game.createWinner(name);
				finish = true;
				break;
			case "menu":
				game.restartGame();
				finish = true;
				break;
			default: System.out.println("Choose a valid option");
		}
		return  finish;
	}

	public void simulation(String message){
		try{
			TimeUnit.SECONDS.sleep(2);
			String movement =game.move();
			System.out.println(movement);
			if(!movement.contains(message)){
				simulation(message);
			}
			return;
		} catch(InterruptedException i){
			System.out.println("Something went wrong. Try again");
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
