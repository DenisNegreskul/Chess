package Chess;

public class King extends ChessPiece {
    public King(String color) {
        super(color);
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        if (defaultCheck(chessBoard, toLine, toColumn)) {
            return Math.abs((toLine - line) * (toColumn - column)) == 1 ||
                    (Math.abs(toLine - line) + Math.abs(toColumn - column)) == 1;
        }
        return false;
    }

    @Override
    public String getSymbol() {
        return "K";
    }

    public boolean isUnderAttack(ChessBoard chessBoard, int line, int column) {
        //This King is temporarily placed in specified coordinates
        ChessPiece temp = chessBoard.getPiece(line, column);
        chessBoard.setPiece(this, line, column);
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (chessBoard.getPiece(i, j) != null &&
                        chessBoard.getPiece(i, j).canMoveToPosition(chessBoard, i, j, line, column)) {
                    chessBoard.setPiece(temp, line, column);
                    return true;
                }
            }
        }
        chessBoard.setPiece(temp, line, column);
        return false;
    }
}
