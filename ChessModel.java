package chess;

import javax.swing.*;
import java.util.Stack;

/**********************************************************************
 * Chess Project
 * Gavin Morrow (Major: Computer Science)
 * Nicolas Georgescu (Major: Computer Science)
 * Mia Endo (Major: Computer Science)
 * Winter 2022 CIS163
 * This class creates a chess board with moveable pieces
 * that react the same way they should within a real chess game.
 */
public class ChessModel implements IChessModel {
    /**
     * Sets instance variable of type board from ChessPiece interface
     */
    private IChessPiece[][] board;
    /**
     * Sets a instance variable of type player from the Player class
     */
    private Player player;
    /**
     * Sets a instance variable of type integer named saveFromRow
     */
    private int saveFromRow;
    /**
     * Sets a instance variable of type integer named saveToRow
     */
    private int saveToRow;
    /**
     * Sets a instance variable of type integer named saveFromColumn
     */
    private int saveFromColumn;
    /**
     * Sets a instance variable of type integer named saveToColumn
     */
    private int saveToColumn;
    /**
     * Sets a instance variable of type IChessPiece name saveFromPiece
     */
    private IChessPiece saveFromPiece;
    /**
     * Sets a instance variable of type IChessPiece named saveToPiece
     */
    private IChessPiece saveToPiece;
    /**
     * Sets a instance variable of a stack arraylist named deepCopy
     */
    private Stack<IChessPiece[][]> deepCopy = new Stack<>();


    /******************************************************************
     *A method that sets the player of type player to a local
     * variable
     * @param player assigned to the local variable
     */
    public void setPlayer(Player player) {
        this.player = player;
    }

    /******************************************************************
     * Resets the board by instantiating a chess
     * piece of each color on the correct section of the board
     */

    public ChessModel() {

        reset();
    }


    /******************************************************************
     * A method that checks to see if the move is valid for any piece
     * making sure a piece cannot be put on itself, or can't
     * go over the board size, or jump a piece.
     *
     * @param move a object describing the move to be made.
     * @return Checks if move being conducted is valid for each piece
     */
    public boolean isValidMove(Move move) {
        boolean valid = false;
        if (board[move.fromRow][move.fromCol] != null)
            //Checks to see if a piece is present
            if (board[move.fromRow][move.fromCol]
                    .isValidMove(move, board))
                //is a valid move
                if (board[move.fromRow][move.fromCol].player()
                        == this.player)
                    //The player moving is the correct player's turn
                    return true;

        return valid;
    }

    /******************************************************************
     * Method creates a deep copy of an array of the chess pieces
     */

    public void arrayCopy() {
        IChessPiece[][] a = new IChessPiece[8][8];
        //Sets board interface
        for (int row = 0; row < 8; row++)
            for (int col = 0; col < 8; col++) {
                a[row][col] = board[row][col];
                //sets interface to board
            }
        deepCopy.push(a);//adds elements in array
        System.out.println(a);


    }


    /******************************************************************
     * This method creates a function to save movement
     *
     * @param move To find out if our move is being saved
     */

    private void saveMove(Move move) {
        saveFromRow = move.fromRow;
        saveFromPiece = board[move.fromRow][move.fromCol];
        saveToPiece = board[move.toRow][move.toColumn];
        saveFromColumn = move.fromCol;
        saveToRow = move.toRow;
        saveToColumn = move.toColumn;
    }

    /******************************************************************
     * This method allows for undoing of one's previous move
     */
    public void undoPreviousMove() {
        System.out.println();
        if (deepCopy.size() < 1) {//If user clicks undo when no
            //more moves have been completed
            JOptionPane.showMessageDialog(null, "No moves");
        } else {
            board = deepCopy.pop();//Returns element at top of stack
            setNextPlayer();
        }

    }


    /******************************************************************
     * This method sets movement location to starting location,
     * then sets original location to empty
     *
     * @param move a object describing the move to be made.
     */

    public void move(Move move) {
        if (isValidMove(move)) {
            arrayCopy();
            board[move.toRow][move.toColumn] =
                    board[move.fromRow][move.fromCol];

            board[move.fromRow][move.fromCol] = null;

            saveMove(move);
            display();

        }
    }

    /******************************************************************
     * Promotes the pawn to a queen at the end of the board
     *
     * @param move that finds the pawn that is moving to the end of the
     *             board to promote it
     *****************************************************************/
    public void promotion(Move move) {
        if (board[move.fromRow][move.fromCol] == null)
            if (board[move.toRow][move.toColumn]
                    .type().equals("Pawn")) {
                if (move.toRow == 0 && currentPlayer() ==
                        Player.BLACK) {

                    board[move.toRow][move.toColumn] =
                            new Queen(Player.WHITE);

                }
                if (move.toRow == 7 && currentPlayer()
                        == Player.WHITE) {

                    board[move.toRow][move.toColumn] =
                            new Queen(Player.BLACK);
                }
            }
    }


    /******************************************************************
     * This method checks to see if black or white's king is
     * in check
     *
     * @param  p the Player being checked
     * @return returns true if in check, otherwise return false
     */

    public boolean inCheck(Player p) {
        for (int row = 0; row < 8; row++)
            for (int col = 0; col < 8; col++)
                if (board[row][col] != null)//Checks for piece
                    if (board[row][col].player() != p)
                        for (int r = 0; r < 8; r++)
                            for (int c = 0; c < 8; c++) {
                                Move m1 = new Move(row, col, r, c);
                                if (board[row][col].isValidMove
                                        (m1, board)
                                        && board[r][c] != null)
                                    if (board[r][c].player() == p)
                                        if (board[r][c].type()
                                                //Piece is king
                                                .equals("King")) {
                                            return true;
                                        }
                            }
        return false;
    }

    /******************************************************************
     * A method that checks if the game is over
     *
     * @return false if the game is not over, true otherwise.
     */

    private boolean gameOver() {
        for (int row = 0; row <= 7; row++) {
            for (int col = 0; col <= 7; col++) {
                if (player == Player.WHITE) {
                    if (board[row][col] != null && board[row][col]
                            .type().equals("King") &&
                            board[row][col].player() == Player.WHITE) {
                        return false;
                    }
                }
                if (player == Player.BLACK) {
                    if (board[row][col] != null && board[row][col]
                            .type().equals("King") &&
                            board[row][col].player() == Player.BLACK) {

                        return false;
                    }
                }
            }
        }
        return true;
    }


    /******************************************************************
     * Checks if the game is complete. Complete if gameOver, or there
     * are no other ways for a player to win.
     *
     * @return Checks to see if game is complete
     */

    public boolean isComplete() {
        if (gameOver()) {
            return true;
        }
        if (inCheck(currentPlayer())) {
            for (int row = 0; row < 8; row++) {
                for (int col = 0; col < 8; col++) {
                    if (board[row][col] != null) {
                        //Checks if piece is present
                        if (board[row][col].player() ==
                                currentPlayer()) {
                            for (int r = 0; r < 8; r++) {
                                for (int c = 0; c < 8; c++) {
                                    Move m = new Move(row, col, r, c);
                                    IChessPiece piece;
                                    IChessPiece tPiece;
                                    tPiece = board[r][c];
                                    piece = board[row][col];
                                    if (board[row][col]
                                            .isValidMove(m, board)) {

                                        board[row][col] = null;
                                        //Checks if piece is not there
                                        board[r][c] = piece;
                                        if (!inCheck(currentPlayer())) {
                                            board[row][col] = piece;
                                            board[r][c] = tPiece;
                                            return false;
                                        } else {
                                            board[row][col] = piece;
                                            board[r][c] = tPiece;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }

            return true;
        }

        //Checks if there is a last king left, if so game is finished
        boolean kingWhite = false;
        boolean kingBlack = false;
        for (int row = 0; row < 8; row++)
            for (int col = 0; col < 8; col++)
                if (board[row][col] != null)
                    if (board[row][col].player() == Player.WHITE) {
                        //Checks if it is white king
                        if (!board[row][col].type().equals("King")) {
                            kingWhite = true;
                        }
                        if (board[row][col].player() == Player.BLACK) {
                            //Checks if it is black king
                            if (!board[row][col].type().equals
                                    ("King")) {
                                kingBlack = true;
                            }
                        }
                    }

        return ((!kingWhite) || (!kingBlack));
    }


    /******************************************************************
     * A method that resets the board
     */

    void reset() {
        board = new IChessPiece[8][8];
        player = Player.WHITE;
        //Sets all white pieces
        board[7][0] = new Rook(Player.WHITE);
        board[7][1] = new Knight(Player.WHITE);
        board[7][2] = new Bishop(Player.WHITE);
        board[7][3] = new Queen(Player.WHITE);
        board[7][4] = new King(Player.WHITE);
        board[7][5] = new Bishop(Player.WHITE);
        board[7][6] = new Knight(Player.WHITE);
        board[7][7] = new Rook(Player.WHITE);

        for (int i = 0; i < 8; i++) {
            board[6][i] = new Pawn(Player.WHITE);
        }

        for (int i = 0; i < 8; i++) {
            board[1][i] = new Pawn(Player.BLACK);
        }
        //Sets all black pieces
        board[0][0] = new Rook(Player.BLACK);
        board[0][1] = new Knight(Player.BLACK);
        board[0][2] = new Bishop(Player.BLACK);
        board[0][3] = new Queen(Player.BLACK);
        board[0][4] = new King(Player.BLACK);
        board[0][5] = new Bishop(Player.BLACK);
        board[0][6] = new Knight(Player.BLACK);
        board[0][7] = new Rook(Player.BLACK);

    }


    /******************************************************************
     * Method that gets board
     *
     * @return Checks to see if IChessPiece[][] returns a board
     */

    public IChessPiece[][] getBoard() {
        return board;
    }

    /******************************************************************
     * This method creates the indexes for the columns and rows of
     * the board, and then displays them.
     */

    public void display() {
        IChessPiece[][] board = getBoard();

        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                if (board[r][c] != null)
                    //Checks for piece
                    System.out.print(board[r][c].type() + "\t");
                else
                    System.out.print("null\t");
            }
            System.out.println();
        }
    }

    /******************************************************************
     *This method gets a current player
     *
     * @return Checks to see if the current player is
     * equal to our variable player
     */

    public Player currentPlayer() {
        return player;
    }

    /******************************************************************
     *This method gives the amount of rows on the board.
     *
     * @return Checks to see if the correct number of
     * rows on the chess board is 8
     */

    public int numRows() {
        return 8;
    }


    /******************************************************************
     * This method gives the amount of columns on the board.
     *
     * @return Checks to see if the correct number of
     * columns on the chess board is 8
     */

    public int numColumns() {
        return 8;
    }

    /******************************************************************
     *A method that returns the piece at a specified spot
     * on the game board.
     *
     * @param row row of board
     * @param column column of board
     * @return board[row][column] row and col of spot on board
     */

    public IChessPiece pieceAt(int row, int column) {
        return board[row][column];
    }

    /******************************************************************
     * Initializes player to the next person playing
     */

    public void setNextPlayer() {
        player = player.next();
    }


    /******************************************************************
     *This method creates an AI as the other player than the user
     */

    public Move AI_1() {
        if (player == Player.BLACK) {
            if (inCheck(Player.BLACK)) {
                return getOutOfCheck();
            } else if (!inDanger(Player.WHITE)
                    && !inDanger(Player.BLACK)
                    && tryToWin() != null) {
                return tryToWin();

            } else if (inDanger(Player.WHITE)) {
                return attemptToTakePiece();


            } else {
                for (int fromRow = 0; fromRow < 8; fromRow++) {
                    for (int fromCol = 0; fromCol < 8; fromCol++) {
                        IChessPiece chessPiece = board[fromRow][fromCol];
                        if (chessPiece != null
                                && chessPiece.player() == Player.BLACK) {
                            Move move = PieceMoveUp();
                            if (move != null) {
                                return move;
                            }
                        }
                    }
                }
            }
        }
        return null;
    }


    /******************************************************************
     * Determines if there is a in danger piece
     *
     * @param p player is in danger
     * @return true or false if player in danger or not
     */

    private boolean inDanger(Player p) {
        for (int row = 0; row < 8; row++)
            for (int col = 0; col < 8; col++)
                if (board[row][col] != null)
                    //Checks if piece is in the way
                    if (board[row][col].player() != p)
                        for (int r = 0; r < 8; r++)
                            for (int c = 0; c < 8; c++) {
                                Move move = new Move(row, col, r, c);
                                if (board[row][col].isValidMove
                                        (move, board)
                                        && board[r][c] != null) {
                                    if (board[r][c].player() == p) {
                                        return true;
                                    }
                                }
                            }

        return false;
    }

    /******************************************************************
     * Method for the AI that puts opponent into check
     *
     * @return Move that attempts to put opponent into check.
     */

    private Move tryToWin() {
        IChessPiece t1;
        IChessPiece t2;

        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                if (board[row][col] != null) {
                    //Checks if piece is present
                    if (board[row][col].player() == Player.BLACK) {
                        for (int r = 0; r < 8; r++) {
                            for (int c = 0; c < 8; c++) {
                                Move move = new Move(row, col, r, c);
                                if (board[row][col].
                                        isValidMove(move, board)) {
                                    t1 = board[row][col];
                                    board[row][col] = null;
                                    t2 = board[r][c];
                                    board[r][c] = t1;
                                    if (inCheck(Player.WHITE)) {
                                        return move;
                                    } else {
                                        board[row][col] = t1;
                                        board[r][c] = t2;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return null;
    }


    /******************************************************************
     *Checks each spot to see if king is in check,
     * if so, it will move anything to get out of check
     *
     * @return Move that will get out of check
     */

    private Move getOutOfCheck() {
        IChessPiece t1;
        IChessPiece t2;
        for (int row = 0; row < 8; row++)
            for (int col = 0; col < 8; col++)
                if (board[row][col] != null)
                    //Checks if piece is present
                    if (board[row][col].player() == Player.WHITE)
                        for (int r = 0; r < 8; r++)
                            for (int c = 0; c < 8; c++) {
                                Move m2 = new Move(row, col, r, c);
                                if (board[row][col].
                                        isValidMove(m2, board)) {
                                    t1 = board[row][col];
                                    board[row][col] = null;
                                    t2 = board[r][c];
                                    board[r][c] = t1;
                                    if (!inCheck(Player.WHITE)) {
                                        //If white is not in check
                                        //return move
                                        return m2;
                                    } else {
                                        board[row][col] = t1;
                                        board[r][c] = t2;
                                    }
                                }
                            }

        return null;
    }


    /******************************************************************
     * Method that allows AI to take piece if in danger of being taken
     *
     * @return takes piece if in danger
     */

    private Move attemptToTakePiece() {
        IChessPiece t1;
        for (int row = 0; row < 8; row++)
            for (int col = 0; col < 8; col++)
                if (board[row][col] != null)
                    if (board[row][col].player() == Player.WHITE)
                        for (int r = 0; r < 8; r++)
                            for (int c = 0; c < 8; c++) {
                                Move move = new Move(row, col, r, c);
                                if (board[row][col]
                                        .isValidMove(move, board)
                                        && board[r][c] != null) {
                                    t1 = board[row][col];
                                    board[row][col] = null;
                                    board[r][c] = t1;
                                    if (inDanger(Player.WHITE)) {
                                        return move;
                                    } else {
                                        return move;
                                    }
                                }
                            }

        return null;
    }

    private Move PieceMoveUp() {

        for (int row = 0; row < 8; row++)
            for (int col = 0; col < 8; col++)
                if (board[row][col] != null)
                    if (board[row][col].player() == Player.WHITE)
                        for (int r = 0; r < 8; r++)
                            for (int c = 0; c < 8; c++) {
                                Move move = new Move(row, col, r, c);
                                if (board[row][col]
                                        .isValidMove(move, board)
                                        && board[r][c] == null) {
                                    if (board[move.toRow][move.toColumn]
                                            .type().equals("Pawn")) {
                                        if (row < 7) {
                                            if (row == 1
                                                    &&
                                                    board[row + 1][row]
                                                            == null) {

                                                move = new Move(row, col,
                                                        row + 1, col);
                                                return move;

                                            } else {
                                                move = new Move(row, col,
                                                        row + 1, col);

                                                return move;


                                            }
                                        }

                                    }

                                }
                            }
        return null;
    }
}