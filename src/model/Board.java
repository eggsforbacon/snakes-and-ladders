package model;

public class Board {
	private Node matrix;
	private int rows;
	private int columns;
	
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
		aux.setUp(current);
		aux.setNext(buildTheBoard(position, r, c + 1, aux));
		aux.setDown(buildTheBoard(position, r+1, c, aux));
		return aux;
	}
}
