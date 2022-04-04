package Chess;

public class Rook extends ChessPiece {
    public Rook(String color) {
        super(color);
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        if (defaultCheck(chessBoard, toLine, toColumn) && (toLine != line || toColumn != column)
                && (toLine == line || toColumn == column)) {
            return chessBoard.checkObstacles(line, column, toLine, toColumn);
        }
        return false;
    }

    @Override
    public String getSymbol() {
        return "R";
    }
}
