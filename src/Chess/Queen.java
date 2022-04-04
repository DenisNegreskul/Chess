package Chess;

public class Queen extends ChessPiece {
    public Queen(String color) {
        super(color);
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        if (defaultCheck(chessBoard, toLine, toColumn) && (toLine != line || toColumn != column) &&
                (toLine == line || toColumn == column || Math.abs(toLine - line) == Math.abs(toColumn - column))) {
            //Moves either like rook or bishop
            return chessBoard.checkObstacles(line, column, toLine, toColumn);
        }
        return false;
    }

    @Override
    public String getSymbol() {
        return "Q";
    }
}
