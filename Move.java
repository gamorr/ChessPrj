package chess;

public class Move {
    public int fromRow, fromCol, toRow, toColumn;

    public Move() {
    }

    public Move(int fromRow, int fromCol, int toRow, int toColumn) {
        this.fromRow = fromRow;
        this.fromCol = fromCol;
        this.toRow = toRow;
        this.toColumn = toColumn;
    }

    @Override
    public String toString() {
        return "Move [fromRow=" + fromRow + ", fromColumn=" + fromCol + ", toRow=" + toRow + ", toColumn=" + toColumn
                + "]";
    }


}