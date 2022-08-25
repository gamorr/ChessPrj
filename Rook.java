package chess;

public class Rook extends ChessPiece {

    public Rook(Player player) {

        super(player);

    }

    public String type() {

        return "Rook";

    }

    // determines if the move is valid for a rook piece
    public boolean isValidMove(Move move, IChessPiece[][] board) {


//        boolean valid = true;
        if (super.isValidMove(move, board)) {

            if ((move.toRow == move.fromRow) && (move.toColumn < move.fromCol)) { //If the rook moves to a block
                // which is in the same row and it’s trying to move backwards
                for (int i = 1; i < (move.fromCol - move.toColumn); ++i) { //essentially check if empty
                    if (board[move.toRow][move.fromCol - i] != null) { //If the place the rook tries to move backwards is not empty
                        return false; //block is not empty, rook cannot move there
                    }
                }
                return true;
            }

            if ((move.toRow == move.fromRow) && (move.toColumn > move.fromCol)) { //If the rook moves to a block
                // which is in the same row and it’s trying to move forwards
                for (int i = 1; i < (move.toColumn - move.fromCol); ++i) {
                    if (board[move.fromRow][move.fromCol + i] != null) {
                        return false;
                    }
                }
                return true;
            }


            if ((move.toRow > move.fromRow) && (move.toColumn == move.fromCol)) {
                //if the rook tries to move to the right which is in the same column
                for (int i = 1; i < (move.toRow - move.fromRow); ++i) {
                    if (board[move.fromRow + i][move.fromCol] != null) {
                        return false;
                    }
                }
                return true;
            }


            if ((move.toRow < move.fromRow) && (move.toColumn == move.fromCol)) {
                //if the rook tries to move to the left which is in the same column
                for (int i = 1; i < (move.fromRow - move.toRow); ++i) {
                    if (board[move.fromRow - i][move.fromCol] != null) {
                        return false;
                    }
                }
                return true;
            }


        }

        return false;
    } //END BRACE FOR METHOD

} //END BRACE FOR CLASS