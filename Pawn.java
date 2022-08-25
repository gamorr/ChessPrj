package chess;

/**********************************************************************
 * This class creates a functional Pawn
 * that moves as it should in a real chess game
 */
public class Pawn extends ChessPiece {

    /******************************************************************
     * Constructs the piece and takes in Player as a parameter type
     *
     * @param player This method calls upon the
     * super type to create an object super(player)
     */

    public Pawn(Player player) {
        super(player);
    }

    /******************************************************************
     * This method of type String gets piece
     *
     * @return Returns the type of chess piece that is being used
     */

    public String type() {
        return "Pawn";
    }

    /******************************************************************
     * This method checks to see if the move is valid based
     * on the superclasses isValidMove method and specific move
     *
     * @param move  uses Move class for object of type move
     * @param board uses chess board interface
     * @return returns correct movement of pawn, if it is in fact a valid move
     */

    public boolean isValidMove(Move move, IChessPiece[][] board) {
        if (super.isValidMove(move, board)) {

            //diagonal to the right and left
            if (super.player() == Player.WHITE) {
                if ((move.toColumn == move.fromCol + 1) && (move.toRow == move.fromRow - 1) ||
                        (move.toColumn == move.fromCol - 1) && (move.toRow == move.fromRow - 1)) {
                    //making sure the board is not empty
                    if (board[move.toRow][move.toColumn] == null) {
                        return false;
                    } else {
                        //space is not the same players piece
                        if (board[move.toRow][move.toColumn].player() != this.player()) {
                            return true;
                        } else {
                            return false;
                        }
                    }
                    //rules for forward by 1
                } else if (move.toColumn == move.fromCol && move.toRow == move.fromRow - 1) {
                    //makes sure the to space is not occupied
                    if (board[move.toRow][move.toColumn] == null) {
                        return true;
                    } else {
                        return false;
                    }
                    //rules for forward by 2
                } else if ((move.toColumn == move.fromCol) &&
                        (move.toRow == move.fromRow - 2)) {
                    //makes sure that the pawn is in the starting row(6)
                    if (board[move.fromRow][move.fromCol] == board[6][move.fromCol]) {
                        //checks the middle space if it is empty
                        if (board[5][move.fromCol] == null) {
                            //checks the to space for being empty
                            if (board[move.toRow][move.toColumn] == null &&
                                    board[move.toRow][move.toColumn] == null) {
                                return true;
                            } else {
                                return false;
                            }
                        } else
                            return false;

                    } else
                        return false;

                } else {
                    return false;
                }
            } else {
                //black piece

                if ((move.toColumn == move.fromCol + 1) && (move.toRow == move.fromRow + 1) ||
                        (move.toColumn == move.fromCol - 1) && (move.toRow == move.fromRow + 1)) {
                    //making sure the board is not empty
                    if (board[move.toRow][move.toColumn] == null) {
                        return false;
                    } else {
                        //space is not the same players piece
                        if (board[move.toRow][move.toColumn].player() != this.player()) {
                            return true;
                        } else {
                            return false;
                        }
                    }
                    //rules for forward by 1
                } else if (move.toColumn == move.fromCol && move.toRow == move.fromRow + 1) {
                    //makes sure the to space is not already occupied by another piece
                    if (board[move.toRow][move.toColumn] == null) {
                        return true;
                    } else {
                        return false;
                    }
                    //rules for forward by 2
                } else if ((move.toColumn == move.fromCol) &&
                        (move.toRow == move.fromRow + 2)) {
                    //makes sure that the pawn is in the starting row(6)
                    if (board[move.fromRow][move.fromCol] == board[1][move.fromCol]) {
                        //checks the middle space if it is empty
                        if (board[2][move.fromCol] == null) {
                            //checks movement space for being empty
                            if (board[move.toRow][move.toColumn] == null &&
                                    board[move.toRow][move.toColumn] == null) {
                                return true;
                            } else {
                                return false;
                            }
                        } else {
                            return false;
                        }
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            }
        } else {
            return false;
        }


    }


}