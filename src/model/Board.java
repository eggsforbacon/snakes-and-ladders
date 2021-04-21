package model;

import com.sun.javafx.application.LauncherImpl;

import javafx.application.Preloader;

public class Board {
	private Node matrix;
	private int rows;
	private int columns;
	private SavedNumber first;
	
	public Board(int rows,int columns,int snakes, int ladders, int players) {
		this.rows = rows;
		this.columns = columns;
		buildTheBoard(0,0,0,null);
	}
	
	public Node buildTheBoard(int position, int r, int c, Node current) {
		if (r >= rows || c >= columns) {
            return null;
        }
		
		Node aux = new Node(position);
		
		aux.setPrev(current);
		aux.setDown(current);
		aux.setNext(buildTheBoard(position, r, c + 1, aux));
		aux.setUp(buildTheBoard(position, r+1, c, aux));
		return aux;
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
