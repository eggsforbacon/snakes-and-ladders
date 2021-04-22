package model;

import com.sun.javafx.application.LauncherImpl;

import javafx.application.Preloader;

public class Board {
	private Node matrix;
	private int rows;
	private int columns;
	private int size;
	private SavedNumber first;
	private String boardString="";

	
	public Board(int rows,int columns,int snakes, int ladders, int players) {
		this.rows = rows;
		this.columns = columns;
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
			System.out.println(" does this stop? ");
			int random = (int) Math.floor(Math.random()*(size-1)+1);
			int ladderStart = chooseBoxes(1,random);
			int random2 = (int) Math.floor(Math.random()*(size-ladderStart)+ladderStart);
			System.out.println("originalmente era "+random2);
			int ladderEnd = chooseBoxes(ladderStart,random2);
			addToSavedNumbers(ladderEnd);
			System.out.println(ladderStart);
			System.out.println(ladderEnd);
			getANode(ladderStart, upper(matrix)).setStart(true); 
			getANode(ladderStart, upper(matrix)).setLadder(getANode(ladderEnd, upper(matrix)));
			getANode(ladderEnd, upper(matrix)).setEnd(true);
			putLadders(i + 1,ladders);
		}
		
	}	
	
	public int chooseBoxes(int min,int number) {
			if(!cycleThroughSavedNumbers(number, first)) {
				addToSavedNumbers(number);
				return number;
			}
			else {
				if(number + 1 == size) {
					number = (int)Math.floor(Math.random()*(size-min)+min);
				}
				return chooseBoxes(min,number+1);
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
				boardString += " H ]" +"\n";
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
