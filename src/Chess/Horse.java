package Chess;

public class Horse extends ChessPiece{
    public Horse(String color) {
        super(color);
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        if (defaultCheck(chessBoard, toLine, toColumn)) {
            return Math.abs((toLine - line) * (toColumn - column)) == 2;
        }
        return false;
    }

    @Override
    public String getSymbol() {
        return "H";
    }
}
