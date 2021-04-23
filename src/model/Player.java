package model;

public class Player {

    private Player next;
    private String name;
    private int score;
    private Node node;

    public Player(String name, int score) {
        this.name = name;
        this.score = score;
    }

    /*Getters*/

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public Player getNext() {
        return next;
    }

    public Node getNode() {
        return node;
    }

    /*Setters*/

    public void setName(String name) {
        this.name = name;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setNext(Player next) {
        this.next = next;
    }

    public void setNode(Node node) {
        this.node = node;
    }
}
