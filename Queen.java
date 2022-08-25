package chess;

public class Queen extends ChessPiece {

    public Queen(Player player) {
        super(player);

    }

    public String type() {
        return "Queen";

    }

    public boolean isValidMove(Move move, IChessPiece[][] board) {
        boolean valid = true;
        valid = super.isValidMove(move, board);

        Bishop move1 = new Bishop(board[move.fromRow][move.fromCol].player());
        Rook move2 = new Rook(board[move.fromRow][move.fromCol].player());
        return ((move1.isValidMove(move, board) || move2.isValidMove(move, board)) && valid);
    }
}