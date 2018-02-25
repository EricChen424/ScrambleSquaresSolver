package com.company.model;

/**
 * Created by Eric on 03/09/2015.
 */
public enum Piece {

    ATOP(0), BTOP(1), CTOP(2), DTOP(3),
    ABOT(4), BBOT(5), CBOT(6), DBOT(7);

    private int val;

    Piece(int val){
        this.val = val;
    }

    public int getVal(){
        return val;
    }

    public boolean isMatching(Piece p){
        if(val < 4){ // it's a top
            return (p.getVal() == (val + 4));
        } else{ // it's a bot
            return (p.getVal() == (val - 4));
        }
    }

    public String toString(){
        switch(val){
            case 0: return "A";
            case 1: return "B";
            case 2: return "C";
            case 3: return "D";
            case 4: return "a";
            case 5: return "b";
            case 6: return "c";
            case 7: return "d";
            default: return "";
        }
    }
}
