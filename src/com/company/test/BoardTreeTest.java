package com.company.test;

import com.company.model.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * Created by Eric on 05/09/2015.
 */
public class BoardTreeTest {

    @Test
    public void testConstructorOneRemainingNoDupes(){
        List<Square> remaining = new ArrayList<Square>();
        List<Square> board = new ArrayList<Square>();

        Square s = new Square(Piece.ATOP, Piece.ABOT, Piece.BTOP, Piece.BBOT);
        Square s90 = new Square(Piece.BBOT, Piece.ATOP, Piece.ABOT, Piece.BTOP);
        Square s180 = new Square(Piece.BTOP, Piece.BBOT, Piece.ATOP, Piece.ABOT);
        Square s270 = new Square(Piece.ABOT, Piece.BTOP, Piece.BBOT, Piece.ATOP);

        remaining.add(s);

        BoardTree bt = new BoardTree(board,remaining);

        assertEquals(new ArrayList<Square>(), bt.getBoard());
        assertEquals(4, bt.getBranches().size());

        Set<BoardTree> trees = bt.getBranches();

        for(BoardTree next : trees){
            assertEquals(0, next.getBranches().size());
        }

        List<Square> board0 = new ArrayList<Square>();
        List<Square> board90 = new ArrayList<Square>();
        List<Square> board180 = new ArrayList<Square>();
        List<Square> board270 = new ArrayList<Square>();

        board0.add(s);
        board90.add(s90);
        board180.add(s180);
        board270.add(s270);

        BoardTree bt0 = new BoardTree(board0, new ArrayList<Square>());
        BoardTree bt90 = new BoardTree(board90, new ArrayList<Square>());
        BoardTree bt180 = new BoardTree(board180, new ArrayList<Square>());
        BoardTree bt270 = new BoardTree(board270, new ArrayList<Square>());

        assertTrue(trees.contains(bt0));
        assertTrue(trees.contains(bt90));
        assertTrue(trees.contains(bt180));
        assertTrue(trees.contains(bt270));
    }

    @Test
    public void testConstructorOneRemainingDupes(){
        List<Square> remaining = new ArrayList<Square>();
        List<Square> board = new ArrayList<Square>();

        Square s = new Square(Piece.ATOP, Piece.ATOP, Piece.ATOP, Piece.ATOP);

        remaining.add(s);

        BoardTree bt = new BoardTree(board,remaining);

        assertEquals(new ArrayList<Square>(), bt.getBoard());
        assertEquals(1, bt.getBranches().size());

        List<Square> lowerBoard = new ArrayList<Square>();
        lowerBoard.add(s);

        for(BoardTree next : bt.getBranches()){
            assertEquals(lowerBoard, next.getBoard());
            assertEquals(0, next.getBranches().size());
        }
    }

    @Test
    public void testConstructorTwoRemaining(){
        List<Square> remaining = new ArrayList<Square>();
        List<Square> board = new ArrayList<Square>();

        Square s = new Square(Piece.ATOP, Piece.ABOT, Piece.ATOP, Piece.ABOT);
        Square s1 = new Square(Piece.ATOP, Piece.ABOT, Piece.BTOP, Piece.BBOT);

        remaining.add(s);
        remaining.add(s1);

        BoardTree bt = new BoardTree(board,remaining);
        Set<BoardTree> branches = bt.getBranches();

        assertEquals(new ArrayList<Square>(), bt.getBoard());
        assertEquals(6, branches.size());

        List<Square> remaining1 = new ArrayList<Square>();
        remaining1.add(s1);
        List<Square> remaining2 = new ArrayList<Square>();
        remaining2.add(s);

        Square s_180 = new Square(Piece.ABOT, Piece.ATOP, Piece.ABOT, Piece.ATOP);
        Square s1_90 = new Square(Piece.BBOT, Piece.ATOP, Piece.ABOT, Piece.BTOP);
        Square s1_180 = new Square(Piece.BTOP, Piece.BBOT, Piece.ATOP, Piece.ABOT);
        Square s1_270 = new Square(Piece.ABOT, Piece.BTOP, Piece.BBOT, Piece.ATOP);

        List<Square> board1 = new ArrayList<Square>();
        board1.add(s);
        List<Square> board2 = new ArrayList<Square>();
        board2.add(s_180);
        List<Square> board3 = new ArrayList<Square>();
        board3.add(s1);
        List<Square> board4 = new ArrayList<Square>();
        board4.add(s1_90);
        List<Square> board5 = new ArrayList<Square>();
        board5.add(s1_180);
        List<Square> board6 = new ArrayList<Square>();
        board6.add(s1_270);

        BoardTree bt1 = new BoardTree(board1, remaining1);
        BoardTree bt2 = new BoardTree(board2, remaining1);
        BoardTree bt3 = new BoardTree(board3, remaining2);
        BoardTree bt4 = new BoardTree(board4, remaining2);
        BoardTree bt5 = new BoardTree(board5, remaining2);
        BoardTree bt6 = new BoardTree(board6, remaining2);

        Set<BoardTree> branchesDupe = new HashSet<BoardTree>();
        branchesDupe.add(bt1);
        branchesDupe.add(bt2);
        branchesDupe.add(bt3);
        branchesDupe.add(bt4);
        branchesDupe.add(bt5);
        branchesDupe.add(bt6);

        assertEquals(branchesDupe, branches);
    }

    @Test
    public void testConstructorOnePieceBoardOneRemaining(){
        List<Square> remaining = new ArrayList<Square>();
        List<Square> board = new ArrayList<Square>();

        Square s = new Square(Piece.ATOP, Piece.ABOT, Piece.ATOP, Piece.ABOT);
        Square s1 = new Square(Piece.ATOP, Piece.ABOT, Piece.BTOP, Piece.BBOT);

        board.add(s);
        remaining.add(s1);

        BoardTree bt = new BoardTree(board,remaining);
        Set<BoardTree> branches = bt.getBranches();

        assertEquals(board, bt.getBoard());
        assertEquals(1, branches.size());

        for(BoardTree b : branches){
            Square s2 = new Square(Piece.ABOT, Piece.BTOP, Piece.BBOT, Piece.ATOP);
            List<Square> testBoard = new ArrayList<Square>();
            testBoard.add(s);
            testBoard.add(s2);
            assertEquals(testBoard, b.getBoard());
            assertEquals(0, b.getBranches().size());
        }
    }

    @Test
    public void testConstructorNoneRemainingEmptyBoard(){
        List<Square> remaining = new ArrayList<Square>();
        List<Square> board = new ArrayList<Square>();

        BoardTree bt = new BoardTree(board,remaining);

        assertEquals(new ArrayList<Square>(), bt.getBoard());
        assertEquals(0, bt.getBranches().size());
    }

    @Test
    public void testConstructorNoneRemainingNotEmptyBadBoard(){
        List<Square> remaining = new ArrayList<Square>();
        List<Square> board = new ArrayList<Square>();

        Square s1 = new Square(Piece.ATOP, Piece.CTOP, Piece.BBOT, Piece.BBOT);
        Square s2 = new Square(Piece.ATOP, Piece.DBOT, Piece.BBOT, Piece.ABOT);
        Square s3 = new Square(Piece.DTOP, Piece.ABOT, Piece.DBOT, Piece.CTOP);
        Square s4 = new Square(Piece.CBOT, Piece.BTOP, Piece.ABOT, Piece.DBOT);
        Square s5 = new Square(Piece.ATOP, Piece.CBOT, Piece.BTOP, Piece.BTOP);
        Square s6 = new Square(Piece.DTOP, Piece.DTOP, Piece.CBOT, Piece.ATOP);
        Square s7 = new Square(Piece.DBOT, Piece.DBOT, Piece.CTOP, Piece.ABOT);
        Square s8 = new Square(Piece.DTOP, Piece.BTOP, Piece.DTOP, Piece.CBOT);
        Square s9 = new Square(Piece.ABOT, Piece.BTOP, Piece.BBOT, Piece.CTOP);

        board.add(s1);
        board.add(s2);
        board.add(s3);
        board.add(s4);
        board.add(s5);
        board.add(s6);
        board.add(s7);
        board.add(s8);
        board.add(s9);

        BoardTree bt = new BoardTree(board,remaining);
        assertEquals(new ArrayList<Square>(), bt.getBoard());
        assertEquals(0, bt.getBranches().size());
    }

    @Test
    public void testConstructorNoneRemainingNotEmptyGoodBoard(){
        List<Square> remaining = new ArrayList<Square>();
        List<Square> board = new ArrayList<Square>();

        Square s1 = new Square(Piece.BBOT, Piece.ABOT, Piece.ATOP, Piece.DBOT);
        Square s2 = new Square(Piece.CTOP, Piece.BBOT, Piece.BBOT, Piece.ATOP);
        Square s3 = new Square(Piece.BTOP, Piece.ATOP, Piece.CBOT, Piece.BTOP);
        Square s4 = new Square(Piece.ABOT, Piece.DBOT, Piece.CTOP, Piece.DTOP);
        Square s5 = new Square(Piece.BTOP, Piece.DTOP, Piece.CBOT, Piece.DTOP);
        Square s6 = new Square(Piece.CTOP, Piece.ABOT, Piece.DBOT, Piece.DBOT);
        Square s7 = new Square(Piece.CBOT, Piece.BTOP, Piece.ABOT, Piece.DBOT);
        Square s8 = new Square(Piece.CTOP, Piece.ABOT, Piece.BTOP, Piece.BBOT);
        Square s9 = new Square(Piece.DTOP, Piece.DTOP, Piece.CBOT, Piece.ATOP);

        board.add(s1);
        board.add(s2);
        board.add(s3);
        board.add(s4);
        board.add(s5);
        board.add(s6);
        board.add(s7);
        board.add(s8);
        board.add(s9);

        BoardTree bt = new BoardTree(board,remaining);
        assertEquals(board, bt.getBoard());
        assertEquals(0, bt.getBranches().size());
    }
}

//package com.company.test;
//
//import com.company.model.*;
//import org.junit.Before;
//import org.junit.Test;
//
//import java.util.ArrayList;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//
//import static org.junit.Assert.*;
//
///**
// * Created by Eric on 05/09/2015.
// */
//public class BoardTreeTest {
//
//    @Test
//    public void testConstructorOneRemainingNoDupes(){
//        List<Square> remaining = new ArrayList<Square>();
//        List<Square> board = new ArrayList<Square>();
//
//        Square s = new Square(Piece.ATOP, Piece.ABOT, Piece.BTOP, Piece.BBOT);
//        Square s90 = new Square(Piece.BBOT, Piece.ATOP, Piece.ABOT, Piece.BTOP);
//        Square s180 = new Square(Piece.BTOP, Piece.BBOT, Piece.ATOP, Piece.ABOT);
//        Square s270 = new Square(Piece.ABOT, Piece.BTOP, Piece.BBOT, Piece.ATOP);
//
//        remaining.add(s);
//
//        BoardTree bt = new BoardTree(board,remaining);
//
//        assertEquals(new ArrayList<Square>(), bt.getBoard());
//        assertEquals(4, bt.getBranches().size());
//
//        Set<BoardTree> trees = bt.getBranches();
//
//        for(BoardTree next : trees){
//            assertEquals(0, next.getBranches().size());
//        }
//
//        List<Square> board0 = new ArrayList<Square>();
//        List<Square> board90 = new ArrayList<Square>();
//        List<Square> board180 = new ArrayList<Square>();
//        List<Square> board270 = new ArrayList<Square>();
//
//        board0.add(s);
//        board90.add(s90);
//        board180.add(s180);
//        board270.add(s270);
//
//        BoardTree bt0 = new BoardTree(board0, new ArrayList<Square>());
//        BoardTree bt90 = new BoardTree(board90, new ArrayList<Square>());
//        BoardTree bt180 = new BoardTree(board180, new ArrayList<Square>());
//        BoardTree bt270 = new BoardTree(board270, new ArrayList<Square>());
//
//        assertTrue(trees.contains(bt0));
//        assertTrue(trees.contains(bt90));
//        assertTrue(trees.contains(bt180));
//        assertTrue(trees.contains(bt270));
//    }
//
//    @Test
//    public void testConstructorOneRemainingDupes(){
//        List<Square> remaining = new ArrayList<Square>();
//        List<Square> board = new ArrayList<Square>();
//
//        Square s = new Square(Piece.ATOP, Piece.ATOP, Piece.ATOP, Piece.ATOP);
//
//        remaining.add(s);
//
//        BoardTree bt = new BoardTree(board,remaining);
//
//        assertEquals(new ArrayList<Square>(), bt.getBoard());
//        assertEquals(1, bt.getBranches().size());
//
//        List<Square> lowerBoard = new ArrayList<Square>();
//        lowerBoard.add(s);
//
//        for(BoardTree next : bt.getBranches()){
//            assertEquals(lowerBoard, next.getBoard());
//            assertEquals(0, next.getBranches().size());
//        }
//    }
//
//    @Test
//    public void testConstructorTwoRemaining(){
//        List<Square> remaining = new ArrayList<Square>();
//        List<Square> board = new ArrayList<Square>();
//
//        Square s = new Square(Piece.ATOP, Piece.ABOT, Piece.ATOP, Piece.ABOT);
//        Square s1 = new Square(Piece.ATOP, Piece.ABOT, Piece.BTOP, Piece.BBOT);
//
//        remaining.add(s);
//        remaining.add(s1);
//
//        BoardTree bt = new BoardTree(board,remaining);
//        Set<BoardTree> branches = bt.getBranches();
//
//        assertEquals(new ArrayList<Square>(), bt.getBoard());
//        assertEquals(6, branches.size());
//
//        List<Square> remaining1 = new ArrayList<Square>();
//        remaining1.add(s1);
//        List<Square> remaining2 = new ArrayList<Square>();
//        remaining2.add(s);
//
//        Square s_180 = new Square(Piece.ABOT, Piece.ATOP, Piece.ABOT, Piece.ATOP);
//        Square s1_90 = new Square(Piece.BBOT, Piece.ATOP, Piece.ABOT, Piece.BTOP);
//        Square s1_180 = new Square(Piece.BTOP, Piece.BBOT, Piece.ATOP, Piece.ABOT);
//        Square s1_270 = new Square(Piece.ABOT, Piece.BTOP, Piece.BBOT, Piece.ATOP);
//
//        List<Square> board1 = new ArrayList<Square>();
//        board1.add(s);
//        List<Square> board2 = new ArrayList<Square>();
//        board2.add(s_180);
//        List<Square> board3 = new ArrayList<Square>();
//        board3.add(s1);
//        List<Square> board4 = new ArrayList<Square>();
//        board4.add(s1_90);
//        List<Square> board5 = new ArrayList<Square>();
//        board5.add(s1_180);
//        List<Square> board6 = new ArrayList<Square>();
//        board6.add(s1_270);
//
//        BoardTree bt1 = new BoardTree(board1, remaining1);
//        BoardTree bt2 = new BoardTree(board2, remaining1);
//        BoardTree bt3 = new BoardTree(board3, remaining2);
//        BoardTree bt4 = new BoardTree(board4, remaining2);
//        BoardTree bt5 = new BoardTree(board5, remaining2);
//        BoardTree bt6 = new BoardTree(board6, remaining2);
//
//        Set<BoardTree> branchesDupe = new HashSet<BoardTree>();
//        branchesDupe.add(bt1);
//        branchesDupe.add(bt2);
//        branchesDupe.add(bt3);
//        branchesDupe.add(bt4);
//        branchesDupe.add(bt5);
//        branchesDupe.add(bt6);
//
//        assertEquals(branchesDupe, branches);
//    }
//
//    @Test
//    public void testConstructorNoneRemainingEmptyBoard(){
//        List<Square> remaining = new ArrayList<Square>();
//        List<Square> board = new ArrayList<Square>();
//
//        BoardTree bt = new BoardTree(board,remaining);
//
//        assertEquals(new ArrayList<Square>(), bt.getBoard());
//        assertEquals(0, bt.getBranches().size());
//    }
//
//    @Test
//    public void testConstructorNoneRemainingNotEmptyBoard(){
//        List<Square> remaining = new ArrayList<Square>();
//        List<Square> board = new ArrayList<Square>();
//
//        Square s1 = new Square(Piece.ATOP, Piece.CTOP, Piece.BBOT, Piece.BBOT);
//        Square s2 = new Square(Piece.ATOP, Piece.DBOT, Piece.BBOT, Piece.ABOT);
//        Square s3 = new Square(Piece.DTOP, Piece.ABOT, Piece.DBOT, Piece.CTOP);
//        Square s4 = new Square(Piece.CBOT, Piece.BTOP, Piece.ABOT, Piece.DBOT);
//        Square s5 = new Square(Piece.ATOP, Piece.CBOT, Piece.BTOP, Piece.BTOP);
//        Square s6 = new Square(Piece.DTOP, Piece.DTOP, Piece.CBOT, Piece.ATOP);
//        Square s7 = new Square(Piece.DBOT, Piece.DBOT, Piece.CTOP, Piece.ABOT);
//        Square s8 = new Square(Piece.DTOP, Piece.BTOP, Piece.DTOP, Piece.CBOT);
//        Square s9 = new Square(Piece.ABOT, Piece.BTOP, Piece.BBOT, Piece.CTOP);
//
//        board.add(s1);
//        board.add(s2);
//        board.add(s3);
//        board.add(s4);
//        board.add(s5);
//        board.add(s6);
//        board.add(s7);
//        board.add(s8);
//        board.add(s9);
//
//        BoardTree bt = new BoardTree(board,remaining);
//        assertEquals(board, bt.getBoard());
//        assertEquals(0, bt.getBranches().size());
//    }
//}