package com.company.model;

/**
 * Created by Eric on 03/09/2015.
 */
public class Square {

    private static int counter = 0;
    private int index;
    private Piece topPiece;
    private Piece botPiece;
    private Piece leftPiece;
    private Piece rightPiece;
    private Square topSquare;
    private Square botSquare;
    private Square leftSquare;
    private Square rightSquare;

    /**
     * Constructor
     * Creates a solitary Square with top, bot, left, and right as the Pieces corresponding to each side
     * @param top Top Piece
     * @param right Right Piece
     * @param bot Bottom Piece
     * @param left Left Piece
     */
    public Square(Piece top, Piece right, Piece bot, Piece left){
        topPiece = top;
        rightPiece = right;
        botPiece = bot;
        leftPiece = left;
        topSquare = null;
        botSquare = null;
        leftSquare = null;
        rightSquare = null;
        index = counter;
        counter++;
    }

    public static void setCounter(int num){
        counter = num;
    }

    public void setIndex(int num){
        index = num;
    }

    // getter methods
    public Piece getPieceTop(){
        return topPiece;
    }

    public Piece getPieceBot(){
        return botPiece;
    }

    public Piece getPieceRight(){
        return rightPiece;
    }

    public Piece getPieceLeft(){
        return leftPiece;
    }

    public Square getSquareTop(){
        return topSquare;
    }

    public Square getSquareBot(){
        return botSquare;
    }

    public Square getSquareRight(){
        return rightSquare;
    }

    public Square getSquareLeft(){
        return leftSquare;
    }

    public int getIndex(){
        return index;
    }

    public static int getCounter(){
        return counter;
    }

    /**
     * Rotates the Square 90 degrees clockwise
     */
    public void rotate(){
        Piece top = topPiece;
        Piece right = rightPiece;
        Piece bot = botPiece;
        Piece left = leftPiece;

        topPiece = left;
        rightPiece = top;
        botPiece = right;
        leftPiece = bot;
    }

    /**
     * Joins the top side of this with the bottom side of s
     * If this is already connected, removes associations between this and the original Square
     * @param s the Square to connect
     */
    public void connectTop(Square s){
        if(topSquare != null) {
            Square temp = topSquare;
            topSquare = null;
            temp.connectBot(null);
        }
        topSquare = s;
        if(s != null && !this.equals(s.getSquareBot())) {
            s.connectBot(this);
        }
    }

    /**
     * Joins the bottom side of this with the top side of s
     * If this is already connected, removes associations between this and the original Square
     * @param s the Square to connect
     */
    public void connectBot(Square s){
        if(botSquare != null) {
            Square temp = botSquare;
            botSquare = null;
            temp.connectTop(null);
        }
        botSquare = s;
        if(s != null && !this.equals(s.getSquareTop())){
            s.connectTop(this);
        }
    }

    /**
     * Joins the right side of this with the left side of s
     * If this is already connected, removes associations between this and the original Square
     * @param s the Square to connect
     */
    public void connectRight(Square s){
        if(rightSquare != null) {
            Square temp = rightSquare;
            rightSquare = null;
            temp.connectLeft(null);
        }
        rightSquare = s;
        if(s != null && !this.equals(s.getSquareLeft())){
            s.connectLeft(this);
        }
    }

    /**
     * Joins the left side of this with the right side of s
     * If this is already connected, removes associations between this and the original Square
     * @param s the Square to connect
     */
    public void connectLeft(Square s){
        if(leftSquare != null) {
            Square temp = leftSquare;
            leftSquare = null;
            temp.connectRight(null);
        }
        leftSquare = s;
        if(s != null && !this.equals(s.getSquareRight())){
            s.connectRight(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Square square = (Square) o;

        if (topPiece != square.topPiece) return false;
        if (botPiece != square.botPiece) return false;
        if (leftPiece != square.leftPiece) return false;
        return rightPiece == square.rightPiece;

    }

    @Override
    public int hashCode() {
        int result = topPiece != null ? topPiece.hashCode() : 0;
        result = 31 * result + (botPiece != null ? botPiece.hashCode() : 0);
        result = 31 * result + (leftPiece != null ? leftPiece.hashCode() : 0);
        result = 31 * result + (rightPiece != null ? rightPiece.hashCode() : 0);
        return result;
    }

    public void print(){
        System.out.println(" " + topPiece + " ");
        System.out.println(leftPiece + "" + index + "" + rightPiece);
        System.out.println(" " + botPiece + " ");
    }
}
