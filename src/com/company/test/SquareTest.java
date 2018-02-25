package com.company.test;

import com.company.model.Piece;
import com.company.model.Square;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Eric on 03/09/2015.
 */
public class SquareTest {

    private Square s1;
    private Square s2;
    private Square s3;

    @Before
    public void setUp() {
        s1 = new Square(Piece.ATOP, Piece.BTOP, Piece.CTOP, Piece.DTOP);
        s2 = new Square(Piece.ABOT, Piece.BBOT, Piece.CBOT, Piece.DBOT);
        s3 = new Square(Piece.ATOP, Piece.BBOT, Piece.CTOP, Piece.DBOT);
    }

    @Test
    public void testRotate() {
        assertEquals(s1.getPieceTop(), Piece.ATOP);
        assertEquals(s1.getPieceRight(), Piece.BTOP);
        assertEquals(s1.getPieceBot(), Piece.CTOP);
        assertEquals(s1.getPieceLeft(), Piece.DTOP);

        s1.rotate();

        assertEquals(s1.getPieceTop(), Piece.DTOP);
        assertEquals(s1.getPieceRight(), Piece.ATOP);
        assertEquals(s1.getPieceBot(), Piece.BTOP);
        assertEquals(s1.getPieceLeft(), Piece.CTOP);

        s1.rotate();

        assertEquals(s1.getPieceTop(), Piece.CTOP);
        assertEquals(s1.getPieceRight(), Piece.DTOP);
        assertEquals(s1.getPieceBot(), Piece.ATOP);
        assertEquals(s1.getPieceLeft(), Piece.BTOP);
    }

    @Test
    public void testConnectTop() {
        assertEquals(null, s1.getSquareBot());
        assertEquals(null, s1.getSquareTop());
        assertEquals(null, s1.getSquareRight());
        assertEquals(null, s1.getSquareLeft());

        assertEquals(null, s2.getSquareBot());
        assertEquals(null, s2.getSquareTop());
        assertEquals(null, s2.getSquareRight());
        assertEquals(null, s2.getSquareLeft());

        assertEquals(null, s3.getSquareBot());
        assertEquals(null, s3.getSquareTop());
        assertEquals(null, s3.getSquareRight());
        assertEquals(null, s3.getSquareLeft());

        s1.connectTop(s2);

        assertEquals(null, s1.getSquareBot());
        assertEquals(s2, s1.getSquareTop());
        assertEquals(null, s1.getSquareRight());
        assertEquals(null, s1.getSquareLeft());

        assertEquals(s1, s2.getSquareBot());
        assertEquals(null, s2.getSquareTop());
        assertEquals(null, s2.getSquareRight());
        assertEquals(null, s2.getSquareLeft());

        assertEquals(null, s3.getSquareBot());
        assertEquals(null, s3.getSquareTop());
        assertEquals(null, s3.getSquareRight());
        assertEquals(null, s3.getSquareLeft());

        s1.connectTop(s3);

        assertEquals(null, s1.getSquareBot());
        assertEquals(s3, s1.getSquareTop());
        assertEquals(null, s1.getSquareRight());
        assertEquals(null, s1.getSquareLeft());

        assertEquals(null, s2.getSquareBot());
        assertEquals(null, s2.getSquareTop());
        assertEquals(null, s2.getSquareRight());
        assertEquals(null, s2.getSquareLeft());

        assertEquals(s1, s3.getSquareBot());
        assertEquals(null, s3.getSquareTop());
        assertEquals(null, s3.getSquareRight());
        assertEquals(null, s3.getSquareLeft());

        s1.connectTop(null);

        assertEquals(null, s1.getSquareBot());
        assertEquals(null, s1.getSquareTop());
        assertEquals(null, s1.getSquareRight());
        assertEquals(null, s1.getSquareLeft());

        assertEquals(null, s2.getSquareBot());
        assertEquals(null, s2.getSquareTop());
        assertEquals(null, s2.getSquareRight());
        assertEquals(null, s2.getSquareLeft());

        assertEquals(null, s3.getSquareBot());
        assertEquals(null, s3.getSquareTop());
        assertEquals(null, s3.getSquareRight());
        assertEquals(null, s3.getSquareLeft());
    }

    @Test
    public void testConnectBot() {
        assertEquals(null, s1.getSquareBot());
        assertEquals(null, s1.getSquareTop());
        assertEquals(null, s1.getSquareRight());
        assertEquals(null, s1.getSquareLeft());

        assertEquals(null, s2.getSquareBot());
        assertEquals(null, s2.getSquareTop());
        assertEquals(null, s2.getSquareRight());
        assertEquals(null, s2.getSquareLeft());

        assertEquals(null, s3.getSquareBot());
        assertEquals(null, s3.getSquareTop());
        assertEquals(null, s3.getSquareRight());
        assertEquals(null, s3.getSquareLeft());

        s1.connectBot(s2);

        assertEquals(s2, s1.getSquareBot());
        assertEquals(null, s1.getSquareTop());
        assertEquals(null, s1.getSquareRight());
        assertEquals(null, s1.getSquareLeft());

        assertEquals(null, s2.getSquareBot());
        assertEquals(s1, s2.getSquareTop());
        assertEquals(null, s2.getSquareRight());
        assertEquals(null, s2.getSquareLeft());

        assertEquals(null, s3.getSquareBot());
        assertEquals(null, s3.getSquareTop());
        assertEquals(null, s3.getSquareRight());
        assertEquals(null, s3.getSquareLeft());

        s1.connectBot(s3);

        assertEquals(s3, s1.getSquareBot());
        assertEquals(null, s1.getSquareTop());
        assertEquals(null, s1.getSquareRight());
        assertEquals(null, s1.getSquareLeft());

        assertEquals(null, s2.getSquareBot());
        assertEquals(null, s2.getSquareTop());
        assertEquals(null, s2.getSquareRight());
        assertEquals(null, s2.getSquareLeft());

        assertEquals(null, s3.getSquareBot());
        assertEquals(s1, s3.getSquareTop());
        assertEquals(null, s3.getSquareRight());
        assertEquals(null, s3.getSquareLeft());

        s1.connectBot(null);

        assertEquals(null, s1.getSquareBot());
        assertEquals(null, s1.getSquareTop());
        assertEquals(null, s1.getSquareRight());
        assertEquals(null, s1.getSquareLeft());

        assertEquals(null, s2.getSquareBot());
        assertEquals(null, s2.getSquareTop());
        assertEquals(null, s2.getSquareRight());
        assertEquals(null, s2.getSquareLeft());

        assertEquals(null, s3.getSquareBot());
        assertEquals(null, s3.getSquareTop());
        assertEquals(null, s3.getSquareRight());
        assertEquals(null, s3.getSquareLeft());
    }

    @Test
    public void testConnectRight() {
        assertEquals(null, s1.getSquareBot());
        assertEquals(null, s1.getSquareTop());
        assertEquals(null, s1.getSquareRight());
        assertEquals(null, s1.getSquareLeft());

        assertEquals(null, s2.getSquareBot());
        assertEquals(null, s2.getSquareTop());
        assertEquals(null, s2.getSquareRight());
        assertEquals(null, s2.getSquareLeft());

        assertEquals(null, s3.getSquareBot());
        assertEquals(null, s3.getSquareTop());
        assertEquals(null, s3.getSquareRight());
        assertEquals(null, s3.getSquareLeft());

        s1.connectLeft(s2);

        assertEquals(null, s1.getSquareBot());
        assertEquals(null, s1.getSquareTop());
        assertEquals(null, s1.getSquareRight());
        assertEquals(s2, s1.getSquareLeft());

        assertEquals(null, s2.getSquareBot());
        assertEquals(null, s2.getSquareTop());
        assertEquals(s1, s2.getSquareRight());
        assertEquals(null, s2.getSquareLeft());

        assertEquals(null, s3.getSquareBot());
        assertEquals(null, s3.getSquareTop());
        assertEquals(null, s3.getSquareRight());
        assertEquals(null, s3.getSquareLeft());

        s1.connectLeft(s3);

        assertEquals(null, s1.getSquareBot());
        assertEquals(null, s1.getSquareTop());
        assertEquals(null, s1.getSquareRight());
        assertEquals(s3, s1.getSquareLeft());

        assertEquals(null, s2.getSquareBot());
        assertEquals(null, s2.getSquareTop());
        assertEquals(null, s2.getSquareRight());
        assertEquals(null, s2.getSquareLeft());

        assertEquals(null, s3.getSquareBot());
        assertEquals(null, s3.getSquareTop());
        assertEquals(s1, s3.getSquareRight());
        assertEquals(null, s3.getSquareLeft());

        s1.connectLeft(null);

        assertEquals(null, s1.getSquareBot());
        assertEquals(null, s1.getSquareTop());
        assertEquals(null, s1.getSquareRight());
        assertEquals(null, s1.getSquareLeft());

        assertEquals(null, s2.getSquareBot());
        assertEquals(null, s2.getSquareTop());
        assertEquals(null, s2.getSquareRight());
        assertEquals(null, s2.getSquareLeft());

        assertEquals(null, s3.getSquareBot());
        assertEquals(null, s3.getSquareTop());
        assertEquals(null, s3.getSquareRight());
        assertEquals(null, s3.getSquareLeft());
    }

    @Test
    public void testConnectLeft() {
        assertEquals(null, s1.getSquareBot());
        assertEquals(null, s1.getSquareTop());
        assertEquals(null, s1.getSquareRight());
        assertEquals(null, s1.getSquareLeft());

        assertEquals(null, s2.getSquareBot());
        assertEquals(null, s2.getSquareTop());
        assertEquals(null, s2.getSquareRight());
        assertEquals(null, s2.getSquareLeft());

        assertEquals(null, s3.getSquareBot());
        assertEquals(null, s3.getSquareTop());
        assertEquals(null, s3.getSquareRight());
        assertEquals(null, s3.getSquareLeft());

        s1.connectRight(s2);

        assertEquals(null, s1.getSquareBot());
        assertEquals(null, s1.getSquareTop());
        assertEquals(s2, s1.getSquareRight());
        assertEquals(null, s1.getSquareLeft());

        assertEquals(null, s2.getSquareBot());
        assertEquals(null, s2.getSquareTop());
        assertEquals(null, s2.getSquareRight());
        assertEquals(s1, s2.getSquareLeft());

        assertEquals(null, s3.getSquareBot());
        assertEquals(null, s3.getSquareTop());
        assertEquals(null, s3.getSquareRight());
        assertEquals(null, s3.getSquareLeft());

        s1.connectRight(s3);

        assertEquals(null, s1.getSquareBot());
        assertEquals(null, s1.getSquareTop());
        assertEquals(s3, s1.getSquareRight());
        assertEquals(null, s1.getSquareLeft());

        assertEquals(null, s2.getSquareBot());
        assertEquals(null, s2.getSquareTop());
        assertEquals(null, s2.getSquareRight());
        assertEquals(null, s2.getSquareLeft());

        assertEquals(null, s3.getSquareBot());
        assertEquals(null, s3.getSquareTop());
        assertEquals(null, s3.getSquareRight());
        assertEquals(s1, s3.getSquareLeft());

        s1.connectRight(null);

        assertEquals(null, s1.getSquareBot());
        assertEquals(null, s1.getSquareTop());
        assertEquals(null, s1.getSquareRight());
        assertEquals(null, s1.getSquareLeft());

        assertEquals(null, s2.getSquareBot());
        assertEquals(null, s2.getSquareTop());
        assertEquals(null, s2.getSquareRight());
        assertEquals(null, s2.getSquareLeft());

        assertEquals(null, s3.getSquareBot());
        assertEquals(null, s3.getSquareTop());
        assertEquals(null, s3.getSquareRight());
        assertEquals(null, s3.getSquareLeft());
    }
}