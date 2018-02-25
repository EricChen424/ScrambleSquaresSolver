package com.company.test;

import com.company.exception.IllegalBoardException;
import com.company.exception.ImpossibleSolutionException;
import com.company.model.Piece;
import com.company.model.ScrambleSquareSolver;
import com.company.model.Square;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Eric on 05/09/2015.
 */
public class ScrambleSquareSolverTest {

    private ScrambleSquareSolver solver;

    @Before
    public void setUp() throws IllegalBoardException {
        List<Square> board = new ArrayList<Square>();

        Square s2 = new Square(Piece.DTOP, Piece.ABOT, Piece.BTOP, Piece.CTOP);
        Square s1 = new Square(Piece.BBOT, Piece.BBOT, Piece.CTOP, Piece.DBOT);
        Square s5 = new Square(Piece.ABOT, Piece.CTOP, Piece.ATOP, Piece.DTOP);
        Square s3 = new Square(Piece.ATOP, Piece.BBOT, Piece.BTOP, Piece.CBOT);
        Square s8 = new Square(Piece.CTOP, Piece.ABOT, Piece.BTOP, Piece.DBOT);
        Square s7 = new Square(Piece.ABOT, Piece.BTOP, Piece.CBOT, Piece.DTOP);
        Square s4 = new Square(Piece.ABOT, Piece.BTOP, Piece.DBOT, Piece.DTOP);
        Square s9 = new Square(Piece.ABOT, Piece.CTOP, Piece.DTOP, Piece.CBOT);
        Square s6 = new Square(Piece.BTOP, Piece.ATOP, Piece.CTOP, Piece.DTOP);

        board.add(s1);
        board.add(s2);
        board.add(s3);
        board.add(s4);
        board.add(s5);
        board.add(s6);
        board.add(s7);
        board.add(s8);
        board.add(s9);

        solver = new ScrambleSquareSolver();
        solver.setBoard(board);
    }

    @Test (expected = IllegalBoardException.class)
    public void testIllegalBoardExceptionEmptyList() throws IllegalBoardException, ImpossibleSolutionException {
        List<Square> empty = new ArrayList<Square>();

        ScrambleSquareSolver illegal = new ScrambleSquareSolver();
        illegal.setBoard(empty);
        illegal.solve();
    }

    @Test (expected = IllegalBoardException.class)
    public void testIllegalBoardExceptionListTooSmall() throws IllegalBoardException, ImpossibleSolutionException {
        List<Square> small = new ArrayList<Square>();
        Square s1 = new Square(Piece.ATOP, Piece.ATOP, Piece.ATOP, Piece.ATOP);
        Square s2 = new Square(Piece.ATOP, Piece.ATOP, Piece.ATOP, Piece.ATOP);
        Square s3 = new Square(Piece.ATOP, Piece.ATOP, Piece.ATOP, Piece.ATOP);

        small.add(s1);
        small.add(s2);
        small.add(s3);

        ScrambleSquareSolver illegal = new ScrambleSquareSolver();
        illegal.setBoard(small);
        illegal.solve();
    }

    @Test (expected = IllegalBoardException.class)
    public void testIllegalBoardExceptionListTooBig() throws IllegalBoardException, ImpossibleSolutionException {
        List<Square> big = new ArrayList<Square>();
        Square s1 = new Square(Piece.ATOP, Piece.ATOP, Piece.ATOP, Piece.ATOP);
        Square s2 = new Square(Piece.ATOP, Piece.ATOP, Piece.ATOP, Piece.ATOP);
        Square s3 = new Square(Piece.ATOP, Piece.ATOP, Piece.ATOP, Piece.ATOP);
        Square s4 = new Square(Piece.ATOP, Piece.ATOP, Piece.ATOP, Piece.ATOP);
        Square s5 = new Square(Piece.ATOP, Piece.ATOP, Piece.ATOP, Piece.ATOP);
        Square s6 = new Square(Piece.ATOP, Piece.ATOP, Piece.ATOP, Piece.ATOP);
        Square s7 = new Square(Piece.ATOP, Piece.ATOP, Piece.ATOP, Piece.ATOP);
        Square s8 = new Square(Piece.ATOP, Piece.ATOP, Piece.ATOP, Piece.ATOP);
        Square s9 = new Square(Piece.ATOP, Piece.ATOP, Piece.ATOP, Piece.ATOP);
        Square s10 = new Square(Piece.ATOP, Piece.ATOP, Piece.ATOP, Piece.ATOP);

        big.add(s1);
        big.add(s2);
        big.add(s3);
        big.add(s4);
        big.add(s5);
        big.add(s6);
        big.add(s7);
        big.add(s8);
        big.add(s9);
        big.add(s10);

        ScrambleSquareSolver illegal = new ScrambleSquareSolver();
        illegal.setBoard(big);
        illegal.solve();
    }

    @Test (expected = IllegalBoardException.class)
    public void testIllegalBoardExceptionListHasNull() throws IllegalBoardException, ImpossibleSolutionException {
        List<Square> nullBoard = new ArrayList<Square>();
        Square s1 = new Square(Piece.ATOP, Piece.ATOP, Piece.ATOP, Piece.ATOP);
        Square s2 = new Square(Piece.ATOP, Piece.ATOP, Piece.ATOP, Piece.ATOP);
        Square s3 = new Square(Piece.ATOP, Piece.ATOP, Piece.ATOP, Piece.ATOP);
        Square s4 = new Square(Piece.ATOP, Piece.ATOP, Piece.ATOP, Piece.ATOP);
        Square s5 = null;
        Square s6 = new Square(Piece.ATOP, Piece.ATOP, Piece.ATOP, Piece.ATOP);
        Square s7 = new Square(Piece.ATOP, Piece.ATOP, Piece.ATOP, Piece.ATOP);
        Square s8 = new Square(Piece.ATOP, Piece.ATOP, Piece.ATOP, Piece.ATOP);
        Square s9 = new Square(Piece.ATOP, Piece.ATOP, Piece.ATOP, Piece.ATOP);

        nullBoard.add(s1);
        nullBoard.add(s2);
        nullBoard.add(s3);
        nullBoard.add(s4);
        nullBoard.add(s5);
        nullBoard.add(s6);
        nullBoard.add(s7);
        nullBoard.add(s8);
        nullBoard.add(s9);

        ScrambleSquareSolver illegal = new ScrambleSquareSolver();
        illegal.setBoard(nullBoard);
        illegal.solve();
    }

    @Test
    public void testSolve() throws ImpossibleSolutionException, IllegalBoardException {
        List<Square> solution = new ArrayList<Square>();

        Square s1 = new Square(Piece.ABOT, Piece.BTOP, Piece.CTOP, Piece.DTOP);
        Square s2 = new Square(Piece.CTOP, Piece.DBOT, Piece.BBOT, Piece.BBOT);
        Square s3 = new Square(Piece.ABOT, Piece.CTOP, Piece.ATOP, Piece.DTOP);
        Square s4 = new Square(Piece.CBOT, Piece.ATOP, Piece.BBOT, Piece.BTOP);
        Square s5 = new Square(Piece.BTOP, Piece.DBOT, Piece.CTOP, Piece.ABOT);
        Square s6 = new Square(Piece.ABOT, Piece.BTOP, Piece.CBOT, Piece.DTOP);
        Square s7 = new Square(Piece.BTOP, Piece.DBOT, Piece.DTOP, Piece.ABOT);
        Square s8 = new Square(Piece.CBOT, Piece.ABOT, Piece.CTOP, Piece.DTOP);
        Square s9 = new Square(Piece.CTOP, Piece.DTOP, Piece.BTOP, Piece.ATOP);

        solution.add(s1);
        solution.add(s2);
        solution.add(s3);
        solution.add(s4);
        solution.add(s5);
        solution.add(s6);
        solution.add(s7);
        solution.add(s8);
        solution.add(s9);

        assertTrue(compareBoards(solution, solver.solve()));
    }

    @Test (expected = ImpossibleSolutionException.class)
    public void testImpossibleSolutionException() throws IllegalBoardException, ImpossibleSolutionException {
        List<Square> bad = new ArrayList<Square>();
        Square s1 = new Square(Piece.ATOP, Piece.ATOP, Piece.ATOP, Piece.ATOP);
        Square s2 = new Square(Piece.ATOP, Piece.ATOP, Piece.ATOP, Piece.ATOP);
        Square s3 = new Square(Piece.ATOP, Piece.ATOP, Piece.ATOP, Piece.ATOP);
        Square s4 = new Square(Piece.ATOP, Piece.ATOP, Piece.ATOP, Piece.ATOP);
        Square s5 = new Square(Piece.ATOP, Piece.ATOP, Piece.ATOP, Piece.ATOP);
        Square s6 = new Square(Piece.ATOP, Piece.ATOP, Piece.ATOP, Piece.ATOP);
        Square s7 = new Square(Piece.ATOP, Piece.ATOP, Piece.ATOP, Piece.ATOP);
        Square s8 = new Square(Piece.ATOP, Piece.ATOP, Piece.ATOP, Piece.ATOP);
        Square s9 = new Square(Piece.ATOP, Piece.ATOP, Piece.ATOP, Piece.ATOP);

        bad.add(s1);
        bad.add(s2);
        bad.add(s3);
        bad.add(s4);
        bad.add(s5);
        bad.add(s6);
        bad.add(s7);
        bad.add(s8);
        bad.add(s9);

        ScrambleSquareSolver solver = new ScrambleSquareSolver();
        solver.setBoard(bad);

        solver.solve();
    }

    @Test (expected = ImpossibleSolutionException.class)
    public void testImpossibleSolutionException2() throws IllegalBoardException, ImpossibleSolutionException {
        List<Square> bad = new ArrayList<Square>();
        Square s1 = new Square(Piece.ATOP, Piece.BTOP, Piece.ABOT, Piece.CTOP);
        Square s2 = new Square(Piece.CTOP, Piece.DTOP, Piece.BTOP, Piece.ATOP);
        Square s3 = new Square(Piece.BTOP, Piece.ATOP, Piece.CTOP, Piece.ATOP);
        Square s4 = new Square(Piece.ATOP, Piece.BTOP, Piece.ATOP, Piece.CTOP);
        Square s5 = new Square(Piece.CTOP, Piece.DBOT, Piece.BTOP, Piece.ATOP);
        Square s6 = new Square(Piece.DTOP, Piece.BTOP, Piece.ATOP, Piece.BTOP);
        Square s7 = new Square(Piece.ABOT, Piece.ATOP, Piece.ATOP, Piece.CTOP);
        Square s8 = new Square(Piece.ATOP, Piece.CTOP, Piece.BTOP, Piece.ATOP);
        Square s9 = new Square(Piece.BTOP, Piece.ATOP, Piece.DTOP, Piece.CTOP);

        bad.add(s1);
        bad.add(s2);
        bad.add(s3);
        bad.add(s4);
        bad.add(s5);
        bad.add(s6);
        bad.add(s7);
        bad.add(s8);
        bad.add(s9);

        ScrambleSquareSolver solver = new ScrambleSquareSolver();
        solver.setBoard(bad);

        solver.solve();
    }

    /**
     * Returns true if solution and solution2 represent the same board (they may be rotated versions
     * of each other)
     * @param solution the first solution to compare
     * @param solution2 the second solution to compare
     * @return true if solution and solution2 represent the same board
     */
    private boolean compareBoards(List<Square> solution, List<Square> solution2) {
        for(int i = 0; i < 4 ; i++){
            for(int j = 0; j <= i; j++){
                List<Square> rotatedSolution = rotateBoardNumTimes(solution, j);
                if(rotatedSolution.equals(solution2)){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * rotates board 90 degrees clockwise j times
     * @param board the board to rotate
     * @param j the number of times to rotate board 90 degrees clockwise
     * @return board rotated 90 degrees clockwise j times
     */
    private List<Square> rotateBoardNumTimes(List<Square> board, int j) {
        int times = j%4;
        List<Square> dupeBoard = new ArrayList<Square>();
        dupeBoard.addAll(board);
        for(int i = 0; i < times; i++){
            rotateBoard(dupeBoard);
        }
        return dupeBoard;
    }

    /**
     * rotates board 90 degrees clockwise once
     * @param board the board to rotate 90 degrees clockwise
     */
    private void rotateBoard(List<Square> board) {
        for(Square next : board){
            next.rotate();
        }
        Square topLeft = board.get(0);
        Square topCenter = board.get(1);
        Square topRight = board.get(2);
        Square left = board.get(3);
        Square right = board.get(5);
        Square botLeft = board.get(6);
        Square botCenter = board.get(7);
        Square botRight = board.get(8);

        board.set(0, botLeft);
        board.set(1, left);
        board.set(2, topLeft);
        board.set(3, botCenter);
        board.set(5, topCenter);
        board.set(6, botRight);
        board.set(7, right);
        board.set(8, topRight);
    }

}