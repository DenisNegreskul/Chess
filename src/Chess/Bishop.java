package Chess;

public class Bishop extends ChessPiece {
    public Bishop(String color) {
        super(color);
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        if (defaultCheck(chessBoard, toLine, toColumn) && toLine != line &&
                Math.abs(toLine - line) == Math.abs(toColumn - column)) {
            return chessBoard.checkObstacles(line, column, toLine, toColumn);
        }
        return false;
    }

    @Override
    public String getSymbol() {
        return "B";
    }
}
