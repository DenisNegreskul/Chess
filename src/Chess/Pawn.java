package Chess;

public class Pawn extends ChessPiece {
    public Pawn(String color) {
        super(color);
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        if (defaultCheck(chessBoard, toLine, toColumn)) {
            if (this.color.equalsIgnoreCase("white")) {
                if (column == toColumn && chessBoard.checkObstacles(line, column, toLine, toColumn) &&
                        chessBoard.getPiece(toLine, toColumn) == null) {
                    return (toLine - line) == 1 || ((toLine - line) == 2 && line == 1);
                } else if (Math.abs(toColumn - column) == 1){
                    //move diagonally only if there is a piece to eat
                    return (toLine - line) == 1 && chessBoard.getPiece(toLine, toColumn) != null;
                }
            } else {//if black
                if (column == toColumn && chessBoard.checkObstacles(line, column, toLine, toColumn) &&
                        chessBoard.getPiece(toLine, toColumn) == null) {
                    return (toLine - line) == -1 || ((toLine - line) == -2 && line == 6);
                } else if (Math.abs(toColumn - column) == 1){
                    //move diagonally only if there is a piece to eat
                    return (toLine - line) == -1 && chessBoard.getPiece(toLine, toColumn) != null;
                }
            }
        }
        return false;
    }

    @Override
    public String getSymbol() {
        return "P";
    }
}
