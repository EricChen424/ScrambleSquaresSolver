package com.company.test;

import com.company.model.Piece;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Eric on 03/09/2015.
 */
public class PieceTest {

    private Piece aTop = Piece.ATOP;
    private Piece bTop = Piece.BTOP;
    private Piece cTop = Piece.CTOP;
    private Piece dTop = Piece.DTOP;
    private Piece aBot = Piece.ABOT;
    private Piece bBot = Piece.BBOT;
    private Piece cBot = Piece.CBOT;
    private Piece dBot = Piece.DBOT;

    @Test
    public void testIsMatching(){
        assertTrue(aTop.isMatching(aBot));
        assertTrue(aBot.isMatching(aTop));
        assertTrue(bTop.isMatching(bBot));
        assertTrue(bBot.isMatching(bTop));
        assertTrue(cTop.isMatching(cBot));
        assertTrue(cBot.isMatching(cTop));
        assertTrue(dTop.isMatching(dBot));
        assertTrue(dBot.isMatching(dTop));
    }

    @Test
    public void testIsNotMatchingSamePiece(){
        assertFalse(aTop.isMatching(aTop));
        assertFalse(aBot.isMatching(aBot));
        assertFalse(bTop.isMatching(bTop));
        assertFalse(bBot.isMatching(bBot));
        assertFalse(cTop.isMatching(cTop));
        assertFalse(cBot.isMatching(cBot));
        assertFalse(dTop.isMatching(dTop));
        assertFalse(dBot.isMatching(dBot));
    }

    @Test
    public void testIsNotMatchingSameTypeDifferentLetter(){
        assertFalse(aTop.isMatching(bTop));
        assertFalse(aTop.isMatching(cTop));
        assertFalse(aTop.isMatching(dTop));

        assertFalse(aBot.isMatching(bBot));
        assertFalse(aBot.isMatching(cBot));
        assertFalse(aBot.isMatching(dBot));

        assertFalse(bTop.isMatching(aTop));
        assertFalse(bTop.isMatching(cTop));
        assertFalse(bTop.isMatching(dTop));

        assertFalse(bBot.isMatching(aBot));
        assertFalse(bBot.isMatching(cBot));
        assertFalse(bBot.isMatching(dBot));

        assertFalse(cTop.isMatching(aTop));
        assertFalse(cTop.isMatching(bTop));
        assertFalse(cTop.isMatching(dTop));

        assertFalse(cBot.isMatching(aBot));
        assertFalse(cBot.isMatching(bBot));
        assertFalse(cBot.isMatching(dBot));

        assertFalse(dTop.isMatching(aTop));
        assertFalse(dTop.isMatching(bTop));
        assertFalse(dTop.isMatching(cTop));

        assertFalse(dBot.isMatching(aBot));
        assertFalse(dBot.isMatching(bBot));
        assertFalse(dBot.isMatching(cBot));
    }

    @Test
    public void testIsNotMatchingDifferentType(){
        assertFalse(aTop.isMatching(bBot));
        assertFalse(aTop.isMatching(cBot));
        assertFalse(aTop.isMatching(dBot));

        assertFalse(aBot.isMatching(bTop));
        assertFalse(aBot.isMatching(cTop));
        assertFalse(aBot.isMatching(dTop));

        assertFalse(bTop.isMatching(aBot));
        assertFalse(bTop.isMatching(cBot));
        assertFalse(bTop.isMatching(dBot));

        assertFalse(bBot.isMatching(aTop));
        assertFalse(bBot.isMatching(cTop));
        assertFalse(bBot.isMatching(dTop));

        assertFalse(cTop.isMatching(aBot));
        assertFalse(cTop.isMatching(bBot));
        assertFalse(cTop.isMatching(dBot));

        assertFalse(cBot.isMatching(aTop));
        assertFalse(cBot.isMatching(bTop));
        assertFalse(cBot.isMatching(dTop));

        assertFalse(dTop.isMatching(aBot));
        assertFalse(dTop.isMatching(bBot));
        assertFalse(dTop.isMatching(cBot));

        assertFalse(dBot.isMatching(aTop));
        assertFalse(dBot.isMatching(bTop));
        assertFalse(dBot.isMatching(cTop));
    }

}