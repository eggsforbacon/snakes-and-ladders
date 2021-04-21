package model;

public class SavedNumber {
	private int data;
	private SavedNumber next;
	
	public SavedNumber(int data) {
		this.data=data;
	}
	public int getData() {
		return data;
	}
	public SavedNumber getNext() {
		return next;
	}

	public void setNext(SavedNumber next) {
		this.next = next;
	}
}
