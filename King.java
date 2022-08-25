package chess;

public class King extends ChessPiece {

    public King(Player player) {
        super(player);
    }

    public String type() {
        return "King";
    }

    public boolean isValidMove(Move move, IChessPiece[][] board) {
        boolean valid = true;
        if (super.isValidMove(move, board) != true) {
            return false;
        }
        if (Math.abs(move.toRow - move.fromRow) > 1 || Math.abs(move.toColumn - move.fromCol) > 1) {
            return false;
        }
        return valid;
    }


}