package model;

import com.sun.javafx.application.LauncherImpl;

import javafx.application.Preloader;

public class Board {
	private Node matrix;
	private int rows;
	private int columns;
	private int ladders;
	private int players;
	private int snakes;
	private int size;
	private GamePiece gp;
	private int turn;
	private SavedNumber first;
	private String boardString="";
	private String boxesInformation="";
	private Node upper;

	
	public Board(int rows,int columns,int snakes, int ladders, int players) {
		this.rows = rows;
		this.columns = columns;
		this.ladders = ladders;
		this.snakes=snakes;
		this.players = players;
		turn = 1;
		size = rows*columns;
		addToSavedNumbers(size);
		matrix=buildTheBoard(0,0,null);
		upper = upper(matrix);
		putLadders(ladders);
		putSnakes(snakes);
		putPlayers(players);
		
	}
	
	public Node buildTheBoard(int r, int c, Node current) {
		if (r >= rows || c >= columns) {
            return null;
        }
		
		Node aux = new Node(translator(c,r));
		
		aux.setPrev(current);
		aux.setDown(current);
		aux.setNext(buildTheBoard(r, c + 1, aux));
		aux.setUp(buildTheBoard(r+1, c, aux));
		return aux;
	}

	public GamePiece gpAt(int index,GamePiece aux) {
		if (aux.getNext() == null) {
			if (aux.getNumber() == index) {
				return aux;
			} else {
				return null;
			}
		} else {
			if (aux.getNumber() == index) {
				return aux;
			} else {
				return gpAt(index, gp.getNext());
			}
		}
	}

	
	
	public int translator(int r,int c) {
		int first = 0;
		int change = 0;
		if(c % 2 ==0) {
			first = (columns*c);
			change = 1;
		}
		else {
			first = (columns*c)+(columns+1);
			change = -1;
		}
		return first+((r+1)*change);
	}

	public void movePieces(){
		boardString = "";
		int aux = turnToHisBase(turn)-1;
		System.out.println("Es el turno "+aux);
		gpAt(aux,gp).rollTheDice();
		int previousBox=gpAt(aux,gp).getPreviousBox();
		int actualBox = gpAt(aux,gp).getActualBox();
		System.out.println("Se pone en la casilla "+actualBox);
		if(previousBox!=0){
			getANode(previousBox,upper).deletePiece(gpAt(aux,gp),upper.getPiece());
		}
		if(getANode(actualBox,upper).getTypeOfBox()==1){
			useALadder(actualBox,aux);
		}
		else if(getANode(actualBox,upper).getTypeOfBox()==3){
			useASnake(actualBox,aux);
		}
		else{
			getANode(actualBox,upper).addPiece(gpAt(aux,gp));
		}

		turn++;
	}

	private void useALadder(int actualBox,int player){
		getANode(actualBox,matrix).getLadder().addPiece(gpAt(player,gp));
	}

	private void useASnake(int actualBox,int player){
		getANode(actualBox,matrix).getSnake().addPiece(gpAt(player,gp));
	}

	private int turnToHisBase(int number){
		if(number > players){
			return numberToHisBase(number-(players));
		}
		else{
			return number;
		}
	}

	public void putLadders(int ladders) {
		putLadders(0,ladders);
	}
	
	private void putLadders(int i,int ladders) {
		if (i < ladders) {
			int limit = 1; //antes era columns+1
			//System.out.println("El limite es "+limit);
			if (ladders > limit) {
				limit = ladders;
			}
			int random = (int) Math.floor(Math.random() * (size - limit - columns) + 1);
			//System.out.println(random+" es el numero generado");
			int ladderStart = chooseBoxes(limit - columns, 1, random, limit);
			int aux = numberToHisBase(ladderStart);
			//System.out.println(ladderStart+" es el principio");
			//System.out.println(aux+" es el numero base");
			//System.out.println("Empieza desde: "+(ladderStart+((columns+1)-aux)));
			int random2 = (int) Math.floor(Math.random() * (size - (ladderStart + ((columns + 1) - aux))) + (ladderStart + ((columns + 1) - aux)));
			//System.out.println("Originalmente era "+random2);
			int ladderEnd = chooseBoxes(ladderStart + ((columns + 1) - aux), ladderStart + ((columns + 1) - aux), random2, 1);
			//System.out.println(ladderStart);
			//System.out.println(ladderEnd);
			getANode(ladderStart, upper).setTypeOfBox(1);
			getANode(ladderStart, upper).setLadder(getANode(ladderEnd, upper));
			getANode(ladderEnd, upper).setTypeOfBox(2);
			putLadders(i + 1, ladders);
		}
	}

	public void putPlayers(int players){
		putPlayers(players,0);
	}

	private void putPlayers(int players,int i){
		if(i < players){
			char p = (char)(i+33);
			if(gp==null){
				gp = new GamePiece(p,"",i);
			}
			else{
				gp.setNext(new GamePiece(p,"",i));
			}
			putPlayers(players,i+1);
		}
	}

	private int numberToHisBase(int number){
		if(number > columns){
			return numberToHisBase(number-(columns));
		}
		else{
			return number;
		}
	}

	public void putSnakes(int snakes){
		putSnakes(0,snakes);
	}

	private void putSnakes(int i,int snakes){
		if(i<snakes){
			int limit = columns+1;

			if(snakes>limit){
				limit = snakes;
				//System.out.println("El limite son las serpientes");
			}
			int random = (int) Math.floor(Math.random()*(size-limit-2)+limit);
			//System.out.println("El limite es "+limit);
			//System.out.println(random+" El primer random");
			int snakeHead = chooseBoxesForSnakes(limit-2,limit,random);
			//System.out.println(snakeHead+" nuevo valor");
			int aux = numberToHisBase(snakeHead);
			int random2 = (int) Math.floor(Math.random()*(snakeHead-aux)+1);
			//System.out.println("El segundo random es "+random2);
			int snakeTail = chooseBoxesForSnakes(snakeHead-aux,1,random2);
			//System.out.println(snakeTail+" nuevo valor");
			getANode(snakeHead, upper).setTypeOfBox(3);
			getANode(snakeHead, upper).setSnake(getANode(snakeTail, upper));
			getANode(snakeTail, upper).setTypeOfBox(4);
			putSnakes(i+1,snakes);
		}
	}
	public int chooseBoxesForSnakes(int a,int b,int number){
		if(!cycleThroughSavedNumbers(number, first)) {
			addToSavedNumbers(number);
			return number;
		}
		else{
			//System.out.println(number+" esta ocupado");
			if(number-1<b){
				number = (int)Math.floor(Math.random()*(size-a)+b);
			}
			return chooseBoxesForSnakes(a,b,number-1);
		}
	}
	public int chooseBoxes(int a,int b,int number,int limit) {
			if(!cycleThroughSavedNumbers(number, first)) {
				addToSavedNumbers(number);
				return number;
			}
			else {
				if(number >= size-limit) {
					number = (int)Math.floor(Math.random()*(size-a)+b);
					//System.out.println("se propone "+number);
				}
				return chooseBoxes(a,b,number+1,limit);
			}	
		}
		
		
	
	public void addToSavedNumbers(int number) {
		SavedNumber newNumber = new SavedNumber(number);
		newNumber.setNext(first);
		first = newNumber;
	}
	
	public boolean cycleThroughSavedNumbers(int number,SavedNumber toFind) {
		if(toFind==null) {
			return false;
		}
		if(number == toFind.getData()) {
			//System.out.println(" Si lo encontro");
			return true;
		}
		else{
			return cycleThroughSavedNumbers(number,toFind.getNext());
		}
	}
	
	public String getBoardInformation() {
		Node last = upper;
		toDown(last);
		return boardString;
	}

	public Node getMatrix(){
		return matrix;
	}

	public String getBoxesInformation(){
		Node last = upper;
		seeInformation(last);
		return boxesInformation;
	}

	public boolean seeInformation(Node down) {
		if(down.getDown()==null) {
			anotherToTheRight(down);
			return true;
		}
		else {
			anotherToTheRight(down);
			return seeInformation(down.getDown());
		}
	}
	
	public Node getANode(int number,Node node) {
		if(node.getDown()==null) {
			return advance(node,number);
		}
		else {
			if(advance(node,number)!=null){
				//System.out.println(" entra ");
				return advance(node,number);
			}
			else {
				return getANode(number,node.getDown());
			}
			
		}
	}
	
	public Node advance(Node right,int number) {
		if(right.getNext()==null) {
			if(right.getPosition()==number) {
				return right;
			}
			else {
				//System.out.println("aca esta el error");
				return null;
			}
					
		}
		else {
			if(right.getPosition()==number) {
				return right;
			}
			else {
				return advance(right.getNext(),number);
			}
		}
	}
	
	
	
	public boolean toDown(Node down) {
		if(down.getDown()==null) {
			toTheRight(down);
			return true;
		}
		else {
			toTheRight(down);
			return toDown(down.getDown());
		}
	}
	
	public Node toTheRight(Node right) {
		if(right.getNext()==null) {
			boardString += "[ "+right.getPosition();
			if(right.getPiece()!=null){
				boardString += " "+right.getPieceString();
			}
			if(right.getTypeOfBox() == 1 || right.getTypeOfBox() == 2) {
				boardString += " H ]"+"\n";
			}
			else if(right.getTypeOfBox() == 3|| right.getTypeOfBox() == 4){
				boardString += " S ] "+"\n";
			}
			else {
				boardString += " ] "+"\n";
			}

					
			return right;
		}
		else {
			boardString += "[ "+right.getPosition();
			if(right.getPiece()!=null){
				boardString += " "+right.getPieceString();
			}
			if(right.getTypeOfBox() == 1 || right.getTypeOfBox() == 2) {
				boardString += " H ]";
			}
			else if(right.getTypeOfBox() == 3 || right.getTypeOfBox() == 4){
				boardString += " S ] ";
			}
			else {
				boardString += " ] ";
			}
			return toTheRight(right.getNext());
		}
	}

	public Node anotherToTheRight(Node right) {
		if(right.getNext()==null) {
			if(right.getTypeOfBox()==1) {
				boxesInformation += "LADDER: "+right.getPosition()+" -> "+right.getLadder().getPosition()+"\n";
			}
			else if(right.getTypeOfBox()==3){
				boxesInformation += "SNAKE: "+right.getPosition()+" -> "+right.getSnake().getPosition()+"\n";
			}
			return right;
		}
		else {
			if(right.getTypeOfBox()==1) {
				boxesInformation += "LADDER: "+right.getPosition()+" -> "+right.getLadder().getPosition()+"\n";
			}
			else if(right.getTypeOfBox()==3){
				boxesInformation += "SNAKE: "+right.getPosition()+" -> "+right.getSnake().getPosition()+"\n";
			}
			return anotherToTheRight(right.getNext());
		}
	}
	
	public Node upper(Node up) {
		if(up.getUp()==null) {
			return up;
		}
		else {
			return upper(up.getUp());
		}
	}

	/*Getters*/

	public int getSize() {
		return size;
	}

	public int getSnakes() {
		return snakes;
	}

	public int getLadders() {
		return ladders;
	}

	public int getRows() {
		return rows;
	}

	public int getColumns() {
		return columns;
	}
}
