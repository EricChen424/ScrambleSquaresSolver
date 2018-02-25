package com.company.gui;

import com.company.exception.IllegalBoardException;
import com.company.exception.ImpossibleSolutionException;
import com.company.model.Piece;
import com.company.model.ScrambleSquareSolver;
import com.company.model.Square;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Eric on 06/09/2015.
 */
public class Mainframe extends JFrame {

    private final int BIG_SQUARE_SIDE = 75;
    private final int SMALL_SQUARE_SIDE = 50;
    private List<Square> solution;
    private int isSolved = 0;

    // class to display a Square
    private class SquareGraphics extends JPanel{
        JLabel top;
        JLabel right;
        JLabel bot;
        JLabel left;
        JLabel index;

        public SquareGraphics(Square s, int sideLength){
            super(new BorderLayout());
            setPreferredSize(new Dimension(sideLength, sideLength));
            setBorder(BorderFactory.createLineBorder(Color.BLACK));

            top = new JLabel();
            top.setHorizontalAlignment(SwingConstants.CENTER);
            right = new JLabel();
            bot = new JLabel();
            bot.setHorizontalAlignment(SwingConstants.CENTER);
            left = new JLabel();
            index = new JLabel();
            index.setHorizontalAlignment(SwingConstants.CENTER);

            add(top, BorderLayout.NORTH);
            add(right, BorderLayout.EAST);
            add(bot, BorderLayout.SOUTH);
            add(left, BorderLayout.WEST);
            add(index, BorderLayout.CENTER);

            setSquare(s);
        }

        /**
         * if s is null, then this is an empty SquareGraphics
         * else set the top, right, bottom, and left sides of the SquareGraphics to show
         * corresponding String representation of s's Pieces
         * @param s
         */
        private void setSquare(Square s) {
            if(s == null){
                top.setText("");
                right.setText("");
                bot.setText("");
                left.setText("");
                index.setText("");
            } else {
                top.setText(s.getPieceTop().toString());
                right.setText(s.getPieceRight().toString() + "  ");
                bot.setText(s.getPieceBot().toString());
                left.setText("  " + s.getPieceLeft().toString());
                index.setText("Index = " + s.getIndex());
            }
        }
    }

    // panel to input a puzzle
    private class InputPanel extends JPanel{
        private List<Observer> observers = new ArrayList<Observer>();
        private List<Square> board = new ArrayList<Square>();
        private ScrambleSquareSolver solver = new ScrambleSquareSolver();
        private PuzzleDisplay displayPanel = new PuzzleDisplay();

        private class PuzzleDisplay extends JPanel implements Observer{

            private JPanel topRow;
            private JPanel midRow;
            private JPanel botRow;
            private JPanel mainDisplay;
            private List<SquareGraphics> puzzleSquares;

            public PuzzleDisplay(){
                super(new BorderLayout());
                setBorder(BorderFactory.createTitledBorder("Puzzle"));
                setPreferredSize(new Dimension(450, 400));

                topRow = new JPanel();
                midRow = new JPanel();
                botRow = new JPanel();
                mainDisplay = new JPanel();

                puzzleSquares = new ArrayList<SquareGraphics>();

                initializePuzzleSquares(puzzleSquares);
                topRow.setLayout(new BoxLayout(topRow, BoxLayout.X_AXIS));
                midRow.setLayout(new BoxLayout(midRow, BoxLayout.X_AXIS));
                botRow.setLayout(new BoxLayout(botRow, BoxLayout.X_AXIS));
                mainDisplay.setLayout(new BoxLayout(mainDisplay, BoxLayout.Y_AXIS));

                setUpRows();

                mainDisplay.add(topRow);
                mainDisplay.add(midRow);
                mainDisplay.add(botRow);

                add(mainDisplay, BorderLayout.CENTER);

                drawBoard();
            }

            /**
             * adds the 9 scramble puzzle squares in puzzleSquares to their respective rows
             */
            private void setUpRows() {
                for(int i = 0; i < 3; i++){
                    topRow.add(puzzleSquares.get(i));
                }
                for(int i = 3; i < 6; i++){
                    midRow.add(puzzleSquares.get(i));
                }
                for(int i = 6; i < 9; i++){
                    botRow.add(puzzleSquares.get(i));
                }
            }

            /**
             * creates 9 empty JPanels representing the 9 scramble puzzle squares and adds
             * them to puzzleSquares
             * @param puzzleSquares the empty list of scramble puzzle squares to initialize
             */
            private void initializePuzzleSquares(List<SquareGraphics> puzzleSquares) {
                for(int i = 0; i < 9; i++){
                    SquareGraphics square = new SquareGraphics(null, SMALL_SQUARE_SIDE);
                    puzzleSquares.add(square);
                }
            }

            @Override
            public void update() {
                drawBoard();
            }

            /**
             * draws the current puzzle
             */
            private void drawBoard() {
                setEmptySquares();
                setExistingSquares();
                revalidate();
                repaint();
            }

            /**
             * sets all squares contained in board to represent their respective squares
             */
            private void setExistingSquares() {
                for(int i = 0; i < board.size(); i++){
                    Square currentSquare = board.get(i);
                    SquareGraphics currentSquareGraphics = puzzleSquares.get(i);
                    currentSquareGraphics.setSquare(currentSquare);
                }
            }

            /**
             * sets all squares not contained in board to be empty
             */
            private void setEmptySquares() {
                int firstEmptyIndex = board.size();
                if(firstEmptyIndex < 9){
                    for(int i = firstEmptyIndex ; i < puzzleSquares.size(); i++){
                        SquareGraphics currentSquareGraphics = puzzleSquares.get(i);
                        currentSquareGraphics.setSquare(null);
                    }
                }
            }
        }

        public InputPanel(){
            super(new BorderLayout());
            setPreferredSize(new Dimension(490, 600));
            setBorder(BorderFactory.createLineBorder(Color.GRAY));

            add(displayPanel, BorderLayout.NORTH);
            addObserver(displayPanel);

            JPanel buttonPanel = new JPanel();
            buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));

            JButton addSquare = new JButton("Add Square");
            addSquare.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (board.size() == 9) {
                        errorPopup("Cannot add more than 9 squares to the board");
                        return;
                    }

                    JPanel inputs = new JPanel();
                    inputs.setLayout(new BoxLayout(inputs, BoxLayout.Y_AXIS));

                    JTextField topPiece = new JTextField(1);
                    JTextField rightPiece = new JTextField(1);
                    JTextField botPiece = new JTextField(1);
                    JTextField leftPiece = new JTextField(1);

                    inputs.add(new JLabel("Enter A, B, C, D, a, b, c, or d (remember, 'A' and 'a' match, etc.) for:"));
                    inputs.add(Box.createVerticalStrut(9));
                    inputs.add(new JLabel("The top piece of the square: "));
                    inputs.add(Box.createVerticalStrut(2));
                    inputs.add(topPiece);
                    inputs.add(Box.createVerticalStrut(3));
                    inputs.add(new JLabel("The right piece of the square: "));
                    inputs.add(Box.createVerticalStrut(2));
                    inputs.add(rightPiece);
                    inputs.add(Box.createVerticalStrut(3));
                    inputs.add(new JLabel("The bottom piece of the square: "));
                    inputs.add(Box.createVerticalStrut(2));
                    inputs.add(botPiece);
                    inputs.add(Box.createVerticalStrut(3));
                    inputs.add(new JLabel("The left piece of the square: "));
                    inputs.add(Box.createVerticalStrut(2));
                    inputs.add(leftPiece);

                    int result = JOptionPane.showConfirmDialog(null, inputs, "Input",
                            JOptionPane.OK_CANCEL_OPTION);
                    if (result == JOptionPane.OK_OPTION) {
                        List<String> pieces = new ArrayList<String>();
                        pieces.add(topPiece.getText());
                        pieces.add(rightPiece.getText());
                        pieces.add(botPiece.getText());
                        pieces.add(leftPiece.getText());

                        if (!checkPieces(pieces)) {
                            errorPopup("Only A, B, C, D, a, b, c, or d are accepted inputs");
                            return;
                        }

                        Square newSquare = parsePieces(pieces);
                        board.add(newSquare);
                        displayPanel.update();
                    }
                }
            });

            JButton remove = new JButton("Remove Last Square");
            remove.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(board.size() == 0){
                        return;
                    }
                    board.remove(board.size() - 1);
                    Square.setCounter(Square.getCounter() - 1);
                    displayPanel.update();
                }
            });

            JButton reset = new JButton("Reset Puzzle");
            reset.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    board = new ArrayList<Square>();
                    Square.setCounter(0);
                    isSolved = 0;
                    notifyObservers();
                }
            });

            JButton solve = new JButton("Solve Puzzle");
            solve.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    solver.setBoard(board);
                    try {
                        solution = solver.solve();
                        isSolved = 1;
                        notifyObservers();
                    } catch (ImpossibleSolutionException e1) {
                        isSolved = -1;
                        notifyObservers();
                        return;
                    } catch (IllegalBoardException e1) {
                        errorPopup(e1.getMessage());
                        return;
                    }
                }
            });

            JButton help = new JButton("Help");
            help.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JPanel help = new JPanel();
                    help.setLayout(new BoxLayout(help, BoxLayout.Y_AXIS));
                    JLabel helpLabel = new JLabel("Assign the four pairs of matching pieces " +
                            "in your puzzle to A/a, B/b, C/c, and D/d");
                    JLabel helpLabel2 = new JLabel("Add Square - input a puzzle square with your given lettering assignment; " +
                            "square rotation does not matter");
                    JLabel helpLabel3 = new JLabel("Remove Last Square - removes the most recent square input");
                    JLabel helpLabel4 = new JLabel("Reset Puzzle - clears the input puzzle");
                    JLabel helpLabel5 = new JLabel("Solve Puzzle - attempts to solve the puzzle");
                    help.add(helpLabel);
                    help.add(Box.createVerticalStrut(7));
                    help.add(helpLabel2);
                    help.add(Box.createVerticalStrut(1));
                    help.add(helpLabel3);
                    help.add(Box.createVerticalStrut(1));
                    help.add(helpLabel4);
                    help.add(Box.createVerticalStrut(1));
                    help.add(helpLabel5);
                    JOptionPane.showConfirmDialog(null, help, "Help",
                            JOptionPane.DEFAULT_OPTION);
                }
            });

            buttonPanel.add(addSquare);
            buttonPanel.add(Box.createVerticalStrut(2));
            buttonPanel.add(remove);
            buttonPanel.add(Box.createVerticalStrut(2));
            buttonPanel.add(reset);
            buttonPanel.add(Box.createVerticalStrut(2));
            buttonPanel.add(solve);
            buttonPanel.add(Box.createVerticalStrut(2));
            buttonPanel.add(help);
            buttonPanel.setBorder(BorderFactory.createTitledBorder("Options"));

            for(Component next : buttonPanel.getComponents()){
                ((JComponent) next).setAlignmentX(Component.CENTER_ALIGNMENT);
            }

            add(buttonPanel, BorderLayout.SOUTH);
        }

        /**
         * checks that all Strings in pieces are A, B, C, D, a, b, c, or d
         * @param pieces the list of Strings to check
         * @return true if all Strings in pieces are the above characters, else false
         */
        private boolean checkPieces(List<String> pieces) {
            for(String next : pieces){
                if(next.equals("A") ||
                        next.equals("B") ||
                        next.equals("C") ||
                        next.equals("D") ||
                        next.equals("a") ||
                        next.equals("b") ||
                        next.equals("c") ||
                        next.equals("d")){
                    continue;
                } else{
                    return false;
                }
            }
            return true;
        }

        /**
         * turns the Strings in pieces into their corresponding pieces (eg. A is Piece.ATOP)
         * and uses them to create a Square with the Pieces
         * @param pieces the pieces as Strings to parse
         * @return a Square made up of pieces
         */
        private Square parsePieces(List<String> pieces) {
            Piece top = parsePiece(pieces.get(0));
            Piece right = parsePiece(pieces.get(1));
            Piece bot = parsePiece(pieces.get(2));
            Piece left = parsePiece(pieces.get(3));
            return new Square(top, right, bot, left);
        }

        /**
         * returns the Piece corresponding to s
         * @param s the String representing a Piece
         * @return the Piece corresponding to s
         */
        private Piece parsePiece(String s) {
            if(s.equals("A")){
                return Piece.ATOP;
            } else if(s.equals("B")){
                return Piece.BTOP;
            } else if(s.equals("C")){
                return Piece.CTOP;
            } else if(s.equals("D")){
                return Piece.DTOP;
            } else if(s.equals("a")){
                return Piece.ABOT;
            } else if(s.equals("b")){
                return Piece.BBOT;
            } else if(s.equals("c")){
                return Piece.CBOT;
            } else{
                return Piece.DBOT;
            }
        }

        public void addObserver(Observer o){
            observers.add(o);
        }

        public void notifyObservers(){
            for(Observer o : observers){
                o.update();
            }
        }
    }

    // panel to display solution
    private class DisplayPanel extends JPanel implements Observer {
        private JLabel displayLabel = new JLabel();
        private JPanel solutionDisplay;

        public DisplayPanel() {
            super(new BorderLayout());
            setBorder(BorderFactory.createTitledBorder("Solution"));
            display();
        }

        public void clear(){
            remove(displayLabel);
            if(solutionDisplay != null) {
                remove(solutionDisplay);
            }
            solutionDisplay = null;
        }

        public void display(){
            if(isSolved == 0){
                clear();
                revalidate();
                repaint();
                displayLabel.setText("No puzzle input yet to be solved.");
                displayLabel.setHorizontalAlignment(SwingConstants.CENTER);
                add(displayLabel, BorderLayout.CENTER);
                revalidate();
                repaint();
            } else if(isSolved == 1){
                clear();
                revalidate();
                repaint();
                displaySolution();
            } else {
                clear();
                revalidate();
                repaint();
                displayLabel.setText("Puzzle has no solution!");
                displayLabel.setHorizontalAlignment(SwingConstants.CENTER);
                add(displayLabel, BorderLayout.CENTER);
                revalidate();
                repaint();
            }
        }

        /**
         * Displays the solution to the scramble puzzle
         */
        private void displaySolution() {
            SquareGraphics topLeft = new SquareGraphics(solution.get(0), BIG_SQUARE_SIDE);
            SquareGraphics topCenter = new SquareGraphics(solution.get(1), BIG_SQUARE_SIDE);
            SquareGraphics topRight = new SquareGraphics(solution.get(2), BIG_SQUARE_SIDE);
            SquareGraphics midLeft = new SquareGraphics(solution.get(3), BIG_SQUARE_SIDE);
            SquareGraphics midCenter = new SquareGraphics(solution.get(4), BIG_SQUARE_SIDE);
            SquareGraphics midRight = new SquareGraphics(solution.get(5), BIG_SQUARE_SIDE);
            SquareGraphics botLeft = new SquareGraphics(solution.get(6), BIG_SQUARE_SIDE);
            SquareGraphics botCenter = new SquareGraphics(solution.get(7), BIG_SQUARE_SIDE);
            SquareGraphics botRight = new SquareGraphics(solution.get(8), BIG_SQUARE_SIDE);

            JPanel topRow = new JPanel();
            JPanel midRow = new JPanel();
            JPanel botRow = new JPanel();

            topRow.setLayout(new BoxLayout(topRow, BoxLayout.X_AXIS));
            midRow.setLayout(new BoxLayout(midRow, BoxLayout.X_AXIS));
            botRow.setLayout(new BoxLayout(botRow, BoxLayout.X_AXIS));

            topRow.add(topLeft);
            topRow.add(topCenter);
            topRow.add(topRight);
            midRow.add(midLeft);
            midRow.add(midCenter);
            midRow.add(midRight);
            botRow.add(botLeft);
            botRow.add(botCenter);
            botRow.add(botRight);

            solutionDisplay = new JPanel();

            solutionDisplay.setLayout(new BoxLayout(solutionDisplay, BoxLayout.Y_AXIS));
            solutionDisplay.add(topRow);
            solutionDisplay.add(midRow);
            solutionDisplay.add(botRow);

            add(solutionDisplay, BorderLayout.CENTER);
            revalidate();
            repaint();
        }

        @Override
        public void update() {
            display();
        }
    }

    InputPanel inputs = new InputPanel();
    DisplayPanel display = new DisplayPanel();

    public Mainframe(){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Scramble Square Puzzle Solver");

        inputs.addObserver(display);
        getContentPane().add(inputs, BorderLayout.WEST);

        JPanel soln = new JPanel(new BorderLayout());
        soln.setPreferredSize(new Dimension(490, 600));
        soln.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        soln.add(display, BorderLayout.CENTER);
        getContentPane().add(soln, BorderLayout.EAST);

        setSize(1000, 600);
        setVisible(true);
    }

    private void errorPopup(String message) {
        JOptionPane.showMessageDialog(this, message, "Error",
                JOptionPane.ERROR_MESSAGE);
    }
}
