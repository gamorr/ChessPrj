package chess;

public class Knight extends ChessPiece {

    public Knight(Player player) {
        super(player);
    }

    public String type() {
        return "Knight";
    }

    public boolean isValidMove(Move move, IChessPiece[][] board) {

        boolean valid = true;


        valid = super.isValidMove(move, board);


        if (super.isValidMove(move, board)) {
            if (move.fromRow == move.toRow - 2 && move.fromCol == move.toColumn - 1) {//bottom right1
                return true;
            }
            if (move.fromRow == move.toRow - 1 && move.fromCol == move.toColumn - 2) {//bottom right2
                return true;
            }
            if (move.fromRow == move.toRow - 1 && move.fromCol == move.toColumn + 2) {//bottom left1
                return true;
            }
            if (move.fromRow == move.toRow - 2 && move.fromCol == move.toColumn + 1) {//bottom left2
                return true;
            }
            if (move.fromRow == move.toRow + 1 && move.fromCol == move.toColumn - 2) {//top right1
                return true;
            }
            if (move.fromRow == move.toRow + 2 && move.fromCol == move.toColumn - 1) {//top right2
                return true;
            }
            if (move.fromRow == move.toRow + 2 && move.fromCol == move.toColumn + 1) {//top left1
                return true;
            }
            if (move.fromRow == move.toRow + 1 && move.fromCol == move.toColumn + 2) {//top left2
                return true;
            }
        }
        return false;


    }

}