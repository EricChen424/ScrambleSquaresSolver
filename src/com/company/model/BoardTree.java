package com.company.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Eric on 05/09/2015.
 */
public class BoardTree {

    private List<Square> board;
    private Set<BoardTree> boards;
    private List<Square> remaining;

    /**
     * Constructor
     * Creates a set of BoardTrees corresponding to all possible boards with 1 or 0 squares or
     * 2 or more correctly connected square if one more square from remaining is added to the board
     * If board is an incorrectly connected board, then the BoardTree terminates (has an empty board and
     * remaining list)
     * @param board the current board
     * @param remaining the current squares remaining
     */
    public BoardTree(List<Square> board, List<Square> remaining){
        if(allConnected(board)) {
            this.board = board;
            this.remaining = remaining;
        }
        else {
            this.board = new ArrayList<Square>();
            this.remaining = new ArrayList<Square>();
        }
        boards = generateBranches();
    }

    /**
     * generates the set of BoardTrees given by using one more Square from remaining
     * BoardTrees with 2 or more Squares on their board with any Square incorrectly connected
     * will not be added
     * @return the set of BoardTrees given by using one more Square from remaining where if
     * the BoardTrees have boards with >=2 Squares, all Squares are connected correctly
     */
    private Set<BoardTree> generateBranches() {
        Set<BoardTree> tree = new HashSet<BoardTree>();
        for(Square next : remaining){
            Square nextDupe = new Square(next.getPieceTop(), next.getPieceRight(),
                    next.getPieceBot(), next.getPieceLeft());
            nextDupe.setIndex(next.getIndex());
            List<Square> newRemaining = getNewRemaining(nextDupe);
            Set<List<Square>> nextBoards = filterBoards(getNextBoards(nextDupe));

            for(List<Square> aBoard : nextBoards){
                BoardTree bt = new BoardTree(aBoard, newRemaining);
                tree.add(bt);
            }
        }
        return tree;
    }

    /**
     * removes all boards with >=2 Squares if there is a pair of Squares that aren't connected
     * correctly (ie. the Pieces touching each other don't match)
     * @param boards the boards to filter
     * @return the filtered set of boards
     */
    private Set<List<Square>> filterBoards(Set<List<Square>> boards) {
        Set<List<Square>> boardDupe = new HashSet<List<Square>>();
        boardDupe.addAll(boards);
        for(List<Square> next : boards){
            if(next.size() < 2){
                continue;
            } else{
                if(!allConnected(next)){
                    boardDupe.remove(next);
                }
            }
        }
        return boardDupe;
    }

    /**
     * Returns true if all Squares in next have Pieces connected correctly
     * @param next the board to test
     * @return true if the board is completely connected correctly, else false
     */
    private boolean allConnected(List<Square> next) {
        try {
            if (next.get(0).getPieceRight().isMatching(next.get(1).getPieceLeft()) &&
                    next.get(1).getPieceRight().isMatching(next.get(2).getPieceLeft()) &&
                    next.get(0).getPieceBot().isMatching(next.get(3).getPieceTop()) &&
                    next.get(1).getPieceBot().isMatching(next.get(4).getPieceTop()) &&
                    next.get(3).getPieceRight().isMatching(next.get(4).getPieceLeft()) &&
                    next.get(2).getPieceBot().isMatching(next.get(5).getPieceTop()) &&
                    next.get(4).getPieceRight().isMatching(next.get(5).getPieceLeft()) &&
                    next.get(3).getPieceBot().isMatching(next.get(6).getPieceTop()) &&
                    next.get(6).getPieceRight().isMatching(next.get(7).getPieceLeft()) &&
                    next.get(4).getPieceBot().isMatching(next.get(7).getPieceTop()) &&
                    next.get(5).getPieceBot().isMatching(next.get(8).getPieceTop()) &&
                    next.get(7).getPieceRight().isMatching(next.get(8).getPieceLeft())) {
                return true;
            } else {
                return false;
            }
        } catch (IndexOutOfBoundsException e){
            return true;
        }
    }

    /**
     * generates a set corresponding to all possible boards if s is added to board
     * @param s the next Square to go on the board
     * @return the set of all possible boards if s is added to board
     */
    private Set<List<Square>> getNextBoards(Square s) {
        Set<List<Square>> boards = new HashSet<List<Square>>();
        for(int i = 0; i < 4; i++){
            List<Square> newBoard = new ArrayList<Square>();
            newBoard.addAll(board);

            Square sRotate = new Square(s.getPieceTop(), s.getPieceRight(), s.getPieceBot(),
                                        s.getPieceLeft());
            sRotate.setIndex(s.getIndex());

            for(int j = 0; j <= i; j++){
                sRotate.rotate();
            }
            newBoard.add(sRotate);

            boards.add(newBoard);
        }
        return boards;
    }

    /**
     * gets the new list of remaining squares if s is removed from remaining
     * @param s the square to remove from remaining
     * @return a list representing remaining with s removed
     */
    private List<Square> getNewRemaining(Square s) {
        List<Square> newRemaining = new ArrayList<Square>();
        newRemaining.addAll(remaining);
        newRemaining.remove(s);
        return newRemaining;
    }

    // getter methods
    public List<Square> getBoard(){
        return board;
    }

    public Set<BoardTree> getBranches(){
        return boards;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BoardTree boardTree = (BoardTree) o;

        if (board != null ? !board.equals(boardTree.board) : boardTree.board != null) return false;
        return !(remaining != null ? !remaining.equals(boardTree.remaining) : boardTree.remaining != null);

    }

    @Override
    public int hashCode() {
        int result = board != null ? board.hashCode() : 0;
        result = 31 * result + (remaining != null ? remaining.hashCode() : 0);
        return result;
    }
}
