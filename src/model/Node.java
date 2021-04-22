package model;

public class Node {
	
	private int position;
	private Node ladder;
	private boolean isStart;
	private boolean isEnd;
	private boolean isHead;
	private boolean isTail;
	private Node snake;
	private Node next;
    private Node prev;
    private Node up;
    private Node down;
    
    public Node(int position) {
    	this.position = position;
    	ladder=null;
    	snake = null;
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

	public Node getLadder() {
		return ladder;
	}

	public void setLadder(Node ladder) {
		this.ladder = ladder;
	}

	public boolean isStart() {
		return isStart;
	}

	public void setStart(boolean isStart) {
		this.isStart = isStart;
	}

	public boolean isEnd() {
		return isEnd;
	}

	public void setEnd(boolean isEnd) {
		this.isEnd = isEnd;
	}

	public boolean isHead() {
		return isHead;
	}

	public void setHead(boolean isHead) {
		this.isHead = isHead;
	}

	public boolean isTail() {
		return isTail;
	}

	public void setTail(boolean isTail) {
		this.isTail = isTail;
	}

	public Node getSnake() {
		return snake;
	}

	public void setSnake(Node snake) {
		this.snake = snake;
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
