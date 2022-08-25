package chess;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.event.ActionListener;

public class ChessPanel extends JPanel {
    private JButton undoButton;
    private JButton[][] board;
    private ChessModel model;

    private ImageIcon wRook;
    private ImageIcon wBishop;
    private ImageIcon wQueen;
    private ImageIcon wKing;
    private ImageIcon wPawn;
    private ImageIcon wKnight;
    private ImageIcon bRook;
    private ImageIcon bBishop;
    private ImageIcon bQueen;
    private ImageIcon bKing;
    private ImageIcon bPawn;
    private ImageIcon bKnight;
    private Move move;

    private boolean firstTurnFlag;
    private int fromRow;
    private int toRow;
    private int fromCol;
    private int toCol;
    private boolean gameOver = false;


    private listener listener;

    public ChessPanel() {
        model = new ChessModel();
        board = new JButton[model.numRows()][model.numColumns()];
        listener = new listener();
        createIcons();

        JPanel boardpanel = new JPanel();
        JPanel buttonpanel = new JPanel();
        boardpanel.setLayout(new GridLayout
                (model.numRows(), model.numColumns(), 1, 1));

        for (int r = 0; r < model.numRows(); r++) {
            for (int c = 0; c < model.numColumns(); c++) {
                if (model.pieceAt(r, c) == null) {
                    board[r][c] = new JButton("", null);
                    board[r][c].addActionListener(listener);
                } else if (model.pieceAt(r, c).player() == Player.WHITE) {
                    placeWhitePieces(r, c);
                } else if (model.pieceAt(r, c).player() == Player.BLACK) {
                    placeBlackPieces(r, c);
                }
                setBackGroundColor(r, c);
                boardpanel.add(board[r][c]);

            }
        }

        undoButton = new JButton("Undo", null);
        buttonpanel.add(undoButton);
        undoButton.addActionListener(listener);


        add(boardpanel, BorderLayout.WEST);
        boardpanel.setPreferredSize(new Dimension(600, 600));
        //dimensions for actual chessboard
        add(buttonpanel);
        firstTurnFlag = true;
    }

    private void setBackGroundColor(int r, int c) {
        if ((c % 2 == 1 && r % 2 == 0) ||
                (c % 2 == 0 && r % 2 == 1)) {

            board[r][c].setBackground(Color.LIGHT_GRAY);
        } else if ((c % 2 == 0 && r % 2 == 0) ||
                (c % 2 == 1 && r % 2 == 1)) {

            board[r][c].setBackground(Color.WHITE);
        }

    }


    private void placeWhitePieces(int r, int c) {
        if (model.pieceAt(r, c).type().equals("Pawn")) {
            board[r][c] = new JButton(null, wPawn);
            board[r][c].addActionListener(listener);
        }
        if (model.pieceAt(r, c).type().equals("Rook")) {
            board[r][c] = new JButton(null, wRook);
            board[r][c].addActionListener(listener);
        }
        if (model.pieceAt(r, c).type().equals("Knight")) {
            board[r][c] = new JButton(null, wKnight);
            board[r][c].addActionListener(listener);
        }
        if (model.pieceAt(r, c).type().equals("Bishop")) {
            board[r][c] = new JButton(null, wBishop);
            board[r][c].addActionListener(listener);
        }
        if (model.pieceAt(r, c).type().equals("Queen")) {
            board[r][c] = new JButton(null, wQueen);
            board[r][c].addActionListener(listener);
        }
        if (model.pieceAt(r, c).type().equals("King")) {
            board[r][c] = new JButton(null, wKing);
            board[r][c].addActionListener(listener);
        }
    }

    private void placeBlackPieces(int r, int c) {
        if (model.pieceAt(r, c).type().equals("Pawn")) {
            board[r][c] = new JButton(null, bPawn);
            board[r][c].addActionListener(listener);
        }
        if (model.pieceAt(r, c).type().equals("Rook")) {
            board[r][c] = new JButton(null, bRook);
            board[r][c].addActionListener(listener);
        }
        if (model.pieceAt(r, c).type().equals("Knight")) {
            board[r][c] = new JButton(null, bKnight);
            board[r][c].addActionListener(listener);
        }
        if (model.pieceAt(r, c).type().equals("Bishop")) {
            board[r][c] = new JButton(null, bBishop);
            board[r][c].addActionListener(listener);
        }
        if (model.pieceAt(r, c).type().equals("Queen")) {
            board[r][c] = new JButton(null, bQueen);
            board[r][c].addActionListener(listener);
        }
        if (model.pieceAt(r, c).type().equals("King")) {
            board[r][c] = new JButton(null, bKing);
            board[r][c].addActionListener(listener);
        }
    }

    private void createIcons() {
        // Sets the Image for white and black player pieces
        //relative path used here
        wRook = new ImageIcon("./src/chess/wRook.png");
        wBishop = new ImageIcon("./src/chess/wBishop.png");
        wQueen = new ImageIcon("./src/chess/wQueen.png");
        wKing = new ImageIcon("./src/chess/wKing.png");
        wPawn = new ImageIcon("./src/chess/wPawn.png");
        wKnight = new ImageIcon("./src/chess/wKnight.png");
        bRook = new ImageIcon("./src/chess/bRook.png");
        bBishop = new ImageIcon("./src/chess/bBishop.png");
        bQueen = new ImageIcon("./src/chess/bQueen.png");
        bKing = new ImageIcon("./src/chess/bKing.png");
        bPawn = new ImageIcon("./src/chess/bPawn.png");
        bKnight = new ImageIcon("./src/chess/bKnight.png");
    }

    // method that updates the board
    private void displayBoard() {

        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                if (model.pieceAt(r, c) == null) {
                    board[r][c].setIcon(null);
                } else if (model.pieceAt(r, c).player() == Player.WHITE) {
                    if (model.pieceAt(r, c).type().equals("Pawn")) {
                        board[r][c].setIcon(wPawn);
                    }
                    if (model.pieceAt(r, c).type().equals("Rook")) {
                        board[r][c].setIcon(wRook);
                    }
                    if (model.pieceAt(r, c).type().equals("Knight")) {
                        board[r][c].setIcon(wKnight);
                    }
                    if (model.pieceAt(r, c).type().equals("Bishop")) {
                        board[r][c].setIcon(wBishop);
                    }
                    if (model.pieceAt(r, c).type().equals("Queen")) {
                        board[r][c].setIcon(wQueen);
                    }
                    if (model.pieceAt(r, c).type().equals("King")) {
                        board[r][c].setIcon(wKing);
                    }
                } else if (model.pieceAt(r, c).player() == Player.BLACK) {
                    if (model.pieceAt(r, c).type().equals("Pawn")) {
                        board[r][c].setIcon(bPawn);
                    }
                    if (model.pieceAt(r, c).type().equals("Rook")) {
                        board[r][c].setIcon(bRook);
                    }
                    if (model.pieceAt(r, c).type().equals("Knight")) {
                        board[r][c].setIcon(bKnight);
                    }
                    if (model.pieceAt(r, c).type().equals("Bishop")) {
                        board[r][c].setIcon(bBishop);
                    }
                    if (model.pieceAt(r, c).type().equals("Queen")) {
                        board[r][c].setIcon(bQueen);
                    }
                    if (model.pieceAt(r, c).type().equals("King")) {
                        board[r][c].setIcon(bKing);
                    }
                }
            }
        }
        repaint();
    }


    private void undo() {
        model.undoPreviousMove();
        displayBoard();
    }

    private void resetGame() {
        model.reset();
        gameOver = false;
        displayBoard();
    }


    private void checkGameContext() {


        if (model.isComplete()) {
            gameOver = true;
            if (model.currentPlayer() == Player.WHITE) {
                JOptionPane.showMessageDialog(null, "Game Over!");
            } else {
                JOptionPane.showMessageDialog(null, "Game Over!");
            }
            int message = JOptionPane.showConfirmDialog
                    (null, "GAME OVER!" +
                                    "\nDo you want to play again?" +
                                    "\nIf not, click No to exit.", "GAME OVER",
                            JOptionPane.YES_NO_OPTION);
            if (message == JOptionPane.YES_OPTION) {
                resetGame();
            } else {
                System.exit(0);
            }
        }

    }

    private void switchPlayer() {
        model.setPlayer(model.currentPlayer().next());
    }


    // inner class that represents action listener for buttons
    private class listener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            if (undoButton == event.getSource()) {
                undo();
            }
            for (int r = 0; r < model.numRows(); r++)
                for (int c = 0; c < model.numColumns(); c++)
                    if (board[r][c] == event.getSource())
                        if (firstTurnFlag == true) {
                            fromRow = r;
                            fromCol = c;
                            firstTurnFlag = false;
                        } else {
                            toRow = r;
                            toCol = c;
                            firstTurnFlag = true;
                            Move m = new Move(fromRow, fromCol, toRow, toCol);
                            if ((model.isValidMove(m) == true)) {
                                model.move(m);

                                displayBoard();
                                switchPlayer();

                                if ((model.inCheck(Player.BLACK) == true) ||
                                        (model.inCheck(Player.WHITE) == true)) {

                                    JOptionPane.showMessageDialog(null,
                                            "King is in check");
                                    displayBoard();

                                    if (model.isComplete() == true) {
                                        checkGameContext();
                                    }

                                }
                                model.promotion(m);
                                displayBoard();


                            }


                        }
            model.AI_1();


        } //end of actionPerformed
    } //end of listener
} //End of ChessPanel class