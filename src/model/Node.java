package model;

public class Node {
	
	private int position;
	private int ladderStart;
	private int ladderEnd;
	private int snakeHead;
	private int snakeTail;
	private Node next;
    private Node prev;
    private Node up;
    private Node down;
    
    public Node(int position) {
    	this.position = position;
    	ladderStart = 0;
    	ladderEnd = 0;
    	snakeHead = 0;
    	snakeTail = 0;
    	next = null;
    	prev = null;
    	up = null;
    	down = null;
    }

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public int getLadderStart() {
		return ladderStart;
	}

	public void setLadderStart(int ladderStart) {
		this.ladderStart = ladderStart;
	}

	public int getLadderEnd() {
		return ladderEnd;
	}

	public void setLadderEnd(int ladderEnd) {
		this.ladderEnd = ladderEnd;
	}

	public int getSnakeHead() {
		return snakeHead;
	}

	public void setSnakeHead(int snakeHead) {
		this.snakeHead = snakeHead;
	}

	public int getSnakeTail() {
		return snakeTail;
	}

	public void setSnakeTail(int snakeTail) {
		this.snakeTail = snakeTail;
	}

	public Node getNext() {
		return next;
	}

	public void setNext(Node next) {
		this.next = next;
	}

	public Node getPrev() {
		return prev;
	}

	public void setPrev(Node prev) {
		this.prev = prev;
	}

	public Node getUp() {
		return up;
	}

	public void setUp(Node up) {
		this.up = up;
	}

	public Node getDown() {
		return down;
	}

	public void setDown(Node down) {
		this.down = down;
	}
}
