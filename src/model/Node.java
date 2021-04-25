package model;

public class Node {
	
	private int position;
	private int realPosition;
	private Node ladder;
	private int typeOfBox; //0 for normal, 1 for start, 2 for end, 3 for head, 4 for tail
	private Node snake;
	private Node next;
    private Node prev;
    private Node up;
    private Node down;
    private GamePiece piece;
    
    public Node(int position,int realPosition) {
    	this.position = position;
    	this.realPosition = realPosition;
    	typeOfBox = 0;
    	ladder=null;
    	snake = null;
    	next = null;
    	prev = null;
    	up = null;
    	down = null;
    	piece = null;
    }

    public void addPiece(GamePiece add){
    	GamePiece aux = new GamePiece(add.getCharacter(), add.getPath(), add.getNumber());
		aux.setNext(piece);
		piece = aux;
	}

    public void deletePiece(GamePiece delete,GamePiece start){
    	if(delete == piece){
    		piece = piece.getNext();
    		return;
		}
    	if(start.getNext()==null){
    		if(delete == start){
				start = start.getNext();
			}
			return;
		}
    	else{
			if(delete == start){
				start = start.getNext();
				return;
			}
			else{
				deletePiece(delete,start.getNext());
			}
		}
	}

	public GamePiece getPiece() {
		return piece;
	}

	public String getPieceString(){
    	return getPieceString(piece);
	}

	public String getPieceString(GamePiece piece){
    	if(piece==null){
    		return "";
		}
    	else{
    		return piece.getCharacter()+getPieceString(piece.getNext());
		}
	}

	public void setPiece(GamePiece piece) {
		this.piece = piece;
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

	public int getTypeOfBox() {
		return typeOfBox;
	}

	public void setTypeOfBox(int typeOfBox) {
		this.typeOfBox = typeOfBox;
	}


	/*
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
	*/

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

	public int getRealPosition() {
		return realPosition;
	}

	public void setRealPosition(int realPosition) {
		this.realPosition = realPosition;
	}
}
