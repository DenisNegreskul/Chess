package Chess;

public class ChessBoard {
    private ChessPiece[][] board = new ChessPiece[8][8];
    private String nowPlayer;
    private int[] whiteKingPosition = {0, 4};
    private int[] blackKingPosition = {7, 4};

    public ChessBoard(String nowPlayer) {
        this.nowPlayer = nowPlayer;
    }

    public String nowPlayerColor() {
        return nowPlayer;
    }

    public ChessPiece getPiece(int line, int column) {
        return board[line][column];
    }

    public void setPiece(ChessPiece chessPiece, int line, int column) {
        this.board[line][column] = chessPiece;
    }

    public boolean moveToPosition(int startLine, int startColumn, int endLine, int endColumn) {
        if (checkPos(startLine) && checkPos(startColumn) && board[startLine][startColumn] != null) {

            if (!nowPlayer.equals(board[startLine][startColumn].getColor())) return false;

            if (board[startLine][startColumn].canMoveToPosition(this, startLine, startColumn, endLine, endColumn)) {

                ChessPiece temp = board[endLine][endColumn];//Save the piece in case this move leads to check or mate
                board[endLine][endColumn] = board[startLine][startColumn];//Moving piece
                board[startLine][startColumn] = null;
                //Current player can't leave his King under attack
                int[] kingPosition = this.nowPlayerColor().equalsIgnoreCase("white") ? whiteKingPosition : blackKingPosition;
                if (board[endLine][endColumn].getSymbol().equals("K")) {
                    //If it was the King who moved, save new coordinates
                    kingPosition[0] = endLine;
                    kingPosition[1] = endColumn;
                }

                if (((King) board[kingPosition[0]][kingPosition[1]]).isUnderAttack(this, kingPosition[0], kingPosition[1])) {
                    //If King is under attack reverse move
                    if (board[endLine][endColumn].getSymbol().equals("K")) {
                        kingPosition[0] = startLine;
                        kingPosition[1] = startColumn;
                    }
                    board[startLine][startColumn] = board[endLine][endColumn];
                    board[endLine][endColumn] = temp;
                    return false;
                } else {
                    //After successful move look, whether enemy King is checked
                    //Once checked, King won't be able to castle
                    kingPosition = (kingPosition == whiteKingPosition) ? blackKingPosition : whiteKingPosition;
                    if (((King) board[kingPosition[0]][kingPosition[1]]).isUnderAttack(this, kingPosition[0], kingPosition[1])) {
                        board[kingPosition[0]][kingPosition[1]].check = false;
                    }

                    this.nowPlayer = this.nowPlayerColor().equals("White") ? "Black" : "White";
                    board[endLine][endColumn].check = false;

                    return true;
                }
            }
        }
        return false;
    }

    public void printBoard() {
        System.out.println("Turn " + nowPlayer);
        System.out.println();
        System.out.println("Player 2(Black)");
        System.out.println();
        System.out.println("\t0\t1\t2\t3\t4\t5\t6\t7");

        for (int i = 7; i > -1; i--) {
            System.out.print(i + "\t");
            for (int j = 0; j < 8; j++) {
                if (board[i][j] == null) {
                    System.out.print(".." + "\t");
                } else {
                    System.out.print(board[i][j].getSymbol() + board[i][j].getColor().substring(0, 1).toLowerCase() + "\t");
                }
            }
            System.out.println();
            System.out.println();
        }
        System.out.println("Player 1(White)");
    }

    protected boolean checkPos(int pos) {
        return pos >= 0 && pos <= 7;
    }

    //For moves in straight lines only. Do not use for Horse. And check after all the rest is checked!
    protected boolean checkObstacles(int line, int column, int toLine, int toColumn) {
        int lineIncrement = (int) Math.signum(toLine - line);
        int columnIncrement = (int) Math.signum(toColumn - column);
        line += lineIncrement;
        column += columnIncrement;
        while (line != toLine || column != toColumn) {
            if (board[line][column] != null) {
                return false;
            }
            line += lineIncrement;
            column += columnIncrement;
        }
        return true;
    }

    public boolean castling0() {
        return castling(0);
    }

    public boolean castling7() {
        return castling(7);
    }

    public boolean castling(int rookColumn) { //Rook's column must be either 0 or 7
        int line;
        int[] kingPosition;
        if (nowPlayer.equals("White")) {
            line = 0;
            kingPosition = whiteKingPosition;
        } else {
            line = 7;
            kingPosition = blackKingPosition;
        }

        if (rookColumn != 0 && rookColumn != 7) return false;
        if (board[line][rookColumn] == null || board[line][4] == null) return false;

        if (board[line][rookColumn].getColor().equals(nowPlayer) && board[line][4].getColor().equals(nowPlayer) &&
                board[line][rookColumn].getSymbol().equals("R") && board[line][4].getSymbol().equals("K") &&
                board[line][rookColumn].check && board[line][4].check &&
                this.checkObstacles(line, rookColumn, line, 4)) {
            //Check that every square towards the Rook is not under attack and also Rook is not under attack
            int increment = (int) Math.signum((float) (rookColumn - 4));

            for (int i = 4; i != rookColumn + increment; i += increment) {
                if (((King) board[line][4]).isUnderAttack(this, line, i)) {
                    return false;
                }
            }

            board[line][4].check = false;
            board[line][4 + 2 * increment] = board[line][4];   // move King
            board[line][4] = null;
            board[line][rookColumn].check = false;
            board[line][4 + increment] = board[line][rookColumn];   // move Rook
            board[line][rookColumn] = null;
            kingPosition[1] = 4 + 2 * increment;

            this.nowPlayer = this.nowPlayer.equals("White") ? "Black" : "White";  // next turn
            return true;
        }
        return false;
    }
}
