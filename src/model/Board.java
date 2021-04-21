package model;

import com.sun.javafx.application.LauncherImpl;

import javafx.application.Preloader;

public class Board {
	private Node matrix;
	private int rows;
	private int columns;
	private SavedNumber first;
	private String boardString="";
	
	public Board(int rows,int columns,int snakes, int ladders, int players) {
		this.rows = rows;
		this.columns = columns;
		matrix=buildTheBoard(0,0,null);
		
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

			putLadders(i + 1);
		}
	}
	
	public int chooseBoxes() {
		int box = (int) Math.floor(Math.random()*(rows*columns)+1);
		if(!cycleThroughSavedNumbers(box, first)) {
			return box;
		}
		else {
			return chooseBoxes();
		}
		
	}
	
	public String getMatrix() {
		Node last = upper(matrix);
		toDown(last);
		return boardString;
		
		
		
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
			boardString += "[ "+right.getPosition()+" ] "+"\n";
			return right;
		}
		else {
			boardString += "[ "+right.getPosition()+" ] ";
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
}
