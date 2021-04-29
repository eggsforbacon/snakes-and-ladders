package model;

import java.io.Serializable;

public class Player implements Serializable {

    private static final long serialVersionUID = 1;

    private Player right;
    private Player left;
    private String name;
    private int rows;
    private int columns;
    private int ladders;
    private int snakes;
    private int players;
    private String characters;
    private char winner;
    private double score;

    public Player(String name, double score,int rows,int columns,int ladders,int snakes,int players,String characters,char winner) {
        this.name = name;
        this.rows = rows;
        this.columns = columns;
        this.ladders = ladders;
        this.snakes = snakes;
        this.players = players;
        this.characters = characters;
        this.winner = winner;
        this.score = score;
        this.right = null;
        this.left = null;
    }

    /*Getters*/

    public String getName() {
        return name;
    }

    public double getScore() {
        return score;
    }



    /*Setters*/

    public void setName(String name) {
        this.name = name;
    }

    public void setScore(double score) {
        this.score = score;
    }



    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getColumns() {
        return columns;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

    public int getLadders() {
        return ladders;
    }

    public void setLadders(int ladders) {
        this.ladders = ladders;
    }

    public int getSnakes() {
        return snakes;
    }

    public void setSnakes(int snakes) {
        this.snakes = snakes;
    }

    public int getPlayers() {
        return players;
    }

    public void setPlayers(int players) {
        this.players = players;
    }

    public String getCharacters() {
        return characters;
    }

    public void setCharacters(String characters) {
        this.characters = characters;
    }

    public char getWinner() {
        return winner;
    }

    public void setWinner(char winner) {
        this.winner = winner;
    }

    public Player getRight() {
        return right;
    }

    public void setRight(Player right) {
        this.right = right;
    }

    public Player getLeft() {
        return left;
    }

    public void setLeft(Player left) {
        this.left = left;
    }
}
