import Chess.*;

import java.util.Scanner;

public class Main {

    public static ChessBoard buildBoard() {
        ChessBoard board = new ChessBoard("White");

        board.setPiece(new Rook("White"), 0, 0);
        board.setPiece(new Horse("White"), 0, 1);
        board.setPiece(new Bishop("White"), 0, 2);
        board.setPiece(new Queen("White"), 0, 3);
        board.setPiece(new King("White"), 0, 4);
        board.setPiece(new Bishop("White"), 0, 5);
        board.setPiece(new Horse("White"), 0, 6);
        board.setPiece(new Rook("White"), 0, 7);
        board.setPiece(new Pawn("White"), 1, 0);
        board.setPiece(new Pawn("White"), 1, 1);
        board.setPiece(new Pawn("White"), 1, 2);
        board.setPiece(new Pawn("White"), 1, 3);
        board.setPiece(new Pawn("White"), 1, 4);
        board.setPiece(new Pawn("White"), 1, 5);
        board.setPiece(new Pawn("White"), 1, 6);
        board.setPiece(new Pawn("White"), 1, 7);

        board.setPiece(new Rook("Black"), 7, 0);
        board.setPiece(new Horse("Black"), 7, 1);
        board.setPiece(new Bishop("Black"), 7, 2);
        board.setPiece(new Queen("Black"), 7, 3);
        board.setPiece(new King("Black"), 7, 4);
        board.setPiece(new Bishop("Black"), 7, 5);
        board.setPiece(new Horse("Black"), 7, 6);
        board.setPiece(new Rook("Black"), 7, 7);
        board.setPiece(new Pawn("Black"), 6, 0);
        board.setPiece(new Pawn("Black"), 6, 1);
        board.setPiece(new Pawn("Black"), 6, 2);
        board.setPiece(new Pawn("Black"), 6, 3);
        board.setPiece(new Pawn("Black"), 6, 4);
        board.setPiece(new Pawn("Black"), 6, 5);
        board.setPiece(new Pawn("Black"), 6, 6);
        board.setPiece(new Pawn("Black"), 6, 7);
        return board;
    }

    public static void main(String[] args) {

        ChessBoard board = buildBoard();
        Scanner scanner = new Scanner(System.in);
        System.out.println("""
                Чтобы проверить игру надо вводить команды:
                'exit' - для выхода
                'replay' - для перезапуска игры
                'castling0' или 'castling7' - для рокировки по соответствующей линии
                'move 1 1 2 3' - для передвижения фигуры с позиции 1 1 на 2 3(поле это двумерный массив от 0 до 7)
                Проверьте могут ли фигуры ходить друг сквозь друга, корректно ли съедают друг друга, можно ли поставить шах и сделать рокировку?""");
        System.out.println();
        board.printBoard();
        while (true) {
            String s = scanner.nextLine();
            if (s.equals("exit")) break;
            else if (s.equals("replay")) {
                System.out.println("Заново");
                board = buildBoard();
                board.printBoard();
            } else {
                if (s.equals("castling0")) {
                    if (board.castling0()) {
                        System.out.println("Рокировка удалась");
                        board.printBoard();
                    } else {
                        System.out.println("Рокировка не удалась");
                    }
                } else if (s.equals("castling7")) {
                    if (board.castling7()) {
                        System.out.println("Рокировка удалась");
                        board.printBoard();
                    } else {
                        System.out.println("Рокировка не удалась");
                    }
                } else if (s.contains("move")) {
                    String[] a = s.split(" ");
                    try {
                        int line = Integer.parseInt(a[1]);
                        int column = Integer.parseInt(a[2]);
                        int toLine = Integer.parseInt(a[3]);
                        int toColumn = Integer.parseInt(a[4]);
                        if (board.moveToPosition(line, column, toLine, toColumn)) {
                            System.out.println("Успешно передвинулись");
                            board.printBoard();
                        } else System.out.println("Передвижение не удалось");
                    } catch (Exception e) {
                        System.out.println("Вы что-то ввели не так, попробуйте ещё раз");
                    }

                }
            }
        }
    }
}