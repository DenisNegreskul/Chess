package Chess;

abstract public class ChessPiece {
    protected String color;
    protected boolean check = true;

    public ChessPiece(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    abstract public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn);

    abstract public String getSymbol();

    protected boolean canPlaceInPosition(ChessBoard chessBoard, int toLine, int toColumn) {
        return !isSameColor(chessBoard, toLine, toColumn);//true if opposite color or null
    }

    protected boolean isSameColor(ChessBoard chessBoard, int line, int column) {
        return chessBoard.getPiece(line, column) != null &&
                chessBoard.getPiece(line, column).getColor().equalsIgnoreCase(this.color);
    }

    protected boolean defaultCheck(ChessBoard chessBoard, int toLine, int toColumn) {
        return chessBoard.checkPos(toLine) && chessBoard.checkPos(toColumn) &&
                canPlaceInPosition(chessBoard, toLine, toColumn);
    }
}
