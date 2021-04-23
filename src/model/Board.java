package model;

import com.sun.javafx.application.LauncherImpl;

import javafx.application.Preloader;

public class Board {
	private Node matrix;
	private int rows;
	private int columns;
	private int ladders;
	private int snakes;
	private int size;
	private SavedNumber first;
	private String boardString="";

	
	public Board(int rows,int columns,int snakes, int ladders, int players) {
		this.rows = rows;
		this.columns = columns;
		this.ladders = ladders;
		this.snakes=snakes;
		size = rows*columns;
		matrix=buildTheBoard(0,0,null);
		putLadders(ladders);
		
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
	
	public void putLadders(int ladders) {
		putLadders(0,ladders);
	}
	
	private void putLadders(int i,int ladders) {
		if (i < ladders) {
			int limit = rows+1;
			if(ladders>limit){
				limit = ladders;
			}
			int random = (int) Math.floor(Math.random()*(size-limit)+1);
			//System.out.println(random+" es el numero generado");
			int ladderStart = chooseBoxes(limit,1,random,limit);
			int aux = numberToHisBase(ladderStart);
			//System.out.println(ladderStart+" es el principio");
			//System.out.println(aux+" es el numero base");
			//System.out.println("Empieza desde: "+(ladderStart+((rows+1)-aux)));
			int random2 = (int) Math.floor(Math.random()*(size-(ladderStart+((rows+1)-aux)))+(ladderStart+((rows+1)-aux)));
			//System.out.println("Originalmente era "+random2);
			int ladderEnd = chooseBoxes(ladderStart+((rows+1)-aux),ladderStart+((rows+1)-aux),random2,1);
			//System.out.println(ladderStart);
			//System.out.println(ladderEnd);
			getANode(ladderStart, upper(matrix)).setStart(true); 
			getANode(ladderStart, upper(matrix)).setLadder(getANode(ladderEnd, upper(matrix)));
			getANode(ladderEnd, upper(matrix)).setEnd(true);
			putLadders(i + 1,ladders);
		}
		
	}

	private int numberToHisBase(int number){
		if(number > rows){
			return numberToHisBase(number-(rows));
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
			int limit = rows+1;
			if(snakes>limit){
				limit = snakes;
			}
			int random = (int) Math.floor(Math.random()*(size-limit)+1);
			putSnakes(i+1,snakes);
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
					System.out.println("se propone "+number);
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
			return true;
		}
		else{
			return cycleThroughSavedNumbers(number,toFind.getNext());
		}
	}
	
	public String getMatrix() {
		Node last = upper(matrix);
		toDown(last);
		return boardString;
		
		
		
	}
	
	public Node getANode(int number,Node node) {
		if(node.getDown()==null) {
			return advance(node,number);
		}
		else {
			if(advance(node,number)!=null){
				System.out.println(" entra ");
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
			if(right.isStart() || right.isEnd()) {
				boardString += " H ]"+"\n";
			}
			else if(right.isHead() || right.isTail()){
				boardString += " S ] "+"\n";
			}
			else {
				boardString += " ] "+"\n";
			}

					
			return right;
		}
		else {
			boardString += "[ "+right.getPosition();
			if(right.isStart() || right.isEnd()) {
				boardString += " H ]";
			}
			else if(right.isHead() || right.isTail()){
				boardString += " S ] ";
			}
			else {
				boardString += " ] ";
			}
			return toTheRight(right.getNext());
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
}
