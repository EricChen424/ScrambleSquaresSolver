package com.company.model;

import com.company.exception.ImpossibleSolutionException;
import com.company.exception.IllegalBoardException;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by Eric on 03/09/2015.
 */

// TODO: not rotating correctly, use http://www.b-dazzle.com/ScramblePuzzle.asp to test
    // TODO: might be rotating correctly but there's a bug where random squares that were never input appear
public class ScrambleSquareSolver {

    List<Square> squares;

    /**
     * Constructor
     * Creates the Solver with an empty puzzle
     */
    public ScrambleSquareSolver() {
        squares = new ArrayList<Square>();
    }

    // setter method
    public void setBoard(List<Square> board){
        squares = board;
    }

    /**
     * solves the scramble square puzzle
     * throws ImpossibleSolutionException if a solution is not possible
     * throws IllegalBoardException if called when the board does not have 9 non-null squares
     * @return a list of squares representing the solved scramble puzzle
     * @throws ImpossibleSolutionException
     * @throws ImpossibleSolutionException
     */
    public List<Square> solve() throws ImpossibleSolutionException, IllegalBoardException {
        if(squares.size() != 9){
            throw new IllegalBoardException("IllegalBoardException: puzzle must have 9 valid squares");
        }
        for(Square next : squares){
            if(next == null){
                throw new IllegalBoardException("IllegalBoardException: puzzle must have 9 valid squares");
            }
        }

        BoardTree bt = new BoardTree(new ArrayList<Square>(), squares);

        List<Square> solution = findSolution(bt);
        
        return solution;
    }

    /**
     * determines if there is a solution in bt to the puzzle and returns this solution
     * if no solution exists, throws ImpossibleSolutionException
     * @param bt the BoardTree containing all possible puzzle arrangements
     * @return the solution to the given puzzle
     * @throws ImpossibleSolutionException
     */
    private List<Square> findSolution(BoardTree bt) throws ImpossibleSolutionException{
        List<List<Square>> todo = getAllPossibilities(bt);
        for(List<Square> next : todo){
            if(next.size() == 9) {
                if (isSolved(next)) {
                    return next;
                }
            }
        }
        throw new ImpossibleSolutionException("ImpossibleSolutionException: no solution exists to this puzzle");
    }

    /**
     * returns a list of all filled 3x3 boards contained in bt
     * @param bt the BoardTree to obtain all filled 3x3 boards from
     * @return a list of all filled 3x3 boards in bt
     */
    private List<List<Square>> getAllPossibilities(BoardTree bt) {
        List<List<Square>> boards = new ArrayList<List<Square>>();
        List<BoardTree> allTrees = getAllTrees(bt);
        for(BoardTree next : allTrees){
            boards.add(next.getBoard());
        }
        return boards;
    }

    /**
     * returns all BoardTrees in bt
     * @param bt the BoardTree to obtain all BoardTrees from
     * @return a list of all BoardTrees in bt
     */
    private List<BoardTree> getAllTrees(BoardTree bt) {
        List<BoardTree> allTrees = new ArrayList<BoardTree>();
        allTrees.add(bt);

        int index = 0;
        while(index < allTrees.size()){
            BoardTree treeAtIndex = allTrees.get(index);
            Set<BoardTree> currTree = treeAtIndex.getBranches();
            if(currTree.size() > 0){
                allTrees.addAll(currTree);
                index++;
            } else{
                index++;
            }
        }
        return allTrees;
    }

    /**
     * Returns true if solution has all square connections being valid connections
     * A valid square connection is one in which the two pieces joined to each other
     * match each other (eg. ATOP and ABOT)
     * @param solution the solution to see if it's solved
     * @return true if solution has all square connections being valid
     * false otherwise
     */
    private boolean isSolved(List<Square> solution){
        Square s1 = solution.get(0);
        Square s2 = solution.get(1);
        Square s3 = solution.get(2);
        Square s4 = solution.get(3);
        Square s5 = solution.get(4);
        Square s6 = solution.get(5);
        Square s7 = solution.get(6);
        Square s8 = solution.get(7);
        Square s9 = solution.get(8);

        if(s1.getPieceRight().isMatching(s2.getPieceLeft()) &&
                s1.getPieceBot().isMatching(s4.getPieceTop()) &&
                s2.getPieceRight().isMatching(s3.getPieceLeft()) &&
                s2.getPieceBot().isMatching(s5.getPieceTop()) &&
                s3.getPieceBot().isMatching(s6.getPieceTop()) &&
                s4.getPieceRight().isMatching(s5.getPieceLeft()) &&
                s4.getPieceBot().isMatching(s7.getPieceTop()) &&
                s5.getPieceRight().isMatching(s6.getPieceLeft()) &&
                s5.getPieceBot().isMatching(s8.getPieceTop()) &&
                s6.getPieceBot().isMatching(s9.getPieceTop()) &&
                s7.getPieceRight().isMatching(s8.getPieceLeft()) &&
                s8.getPieceRight().isMatching(s9.getPieceLeft())){
            return true;
        } else {
            return false;
        }
    }

}
