package chess;

public abstract class ChessPiece implements IChessPiece {

    private Player owner;

    protected ChessPiece(Player player) {
        this.owner = player;
    }

    public abstract String type();

    public Player player() {
        return owner;
    }

    private boolean onBoard(Move move) {
        if ((move.fromRow >= 0 && move.fromRow <= 7) &&
                (move.toRow >= 0 && move.toRow <= 7) &&
                (move.fromCol >= 0 && move.fromCol <= 7) &&
                (move.toColumn >= 0 && move.toColumn <= 7)) {
            return true;
        }
        return false;
    }

    private boolean hasPositionChanged(Move move) {
        if ((move.fromRow == move.toRow) && (move.fromCol == move.toColumn)) {
            return false;
        }
        return true;
    }

    private boolean isPositionAtPiece(Move move, IChessPiece[][] board) {
        if (this == board[move.fromRow][move.fromCol]) { //Rook == Queen
            return true;
        } else if (this.type().equals("Rook") || this.type().equals("Bishop")) {
            return true;
        }
        return false;
    }

    private boolean isPositionOnBoard(Move move, IChessPiece[][] board) {
        if (board[move.toRow][move.toColumn] != null) {
            if ((board[move.toRow][move.toColumn].player() == this.player())) {
                return false;
            }

        }
        return true;
    }

    public boolean isValidMove(Move move, IChessPiece[][] board) {
        boolean valid = false;

        if (onBoard(move) && hasPositionChanged(move) && isPositionAtPiece(move, board) && isPositionOnBoard(move, board)) {
            return true;
        }

        return valid;


    }
}