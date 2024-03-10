import java.util.Scanner;

public class TicTacToe {
    private static final char EMPTY = ' ';
    private static final char X_MARK = 'X';
    private static final char O_MARK = 'O';

    private char[][] board;
    private char currentPlayerMark;

    public TicTacToe() {
        board = new char[3][3];
        currentPlayerMark = X_MARK;
        initializeBoard();
    }

    private void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = EMPTY;
            }
        }
    }

    public void printBoard() {
        for (int i = 0; i < 3; i++) {
            System.out.println("-------------");
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println();
        }
        System.out.println("-------------");
    }

    public boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean placeMark(int row, int col) {
        if (row >= 0 && row < 3 && col >= 0 && col < 3 && board[row][col] == EMPTY) {
            board[row][col] = currentPlayerMark;
            return true;
        }
        return false;
    }

    public boolean checkForWin() {
        return checkRowsForWin() || checkColumnsForWin() || checkDiagonalsForWin();
    }

    private boolean checkRowsForWin() {
        for (int i = 0; i < 3; i++) {
            if (checkRowCol(board[i][0], board[i][1], board[i][2])) {
                return true;
            }
        }
        return false;
    }

    private boolean checkColumnsForWin() {
        for (int i = 0; i < 3; i++) {
            if (checkRowCol(board[0][i], board[1][i], board[2][i])) {
                return true;
            }
        }
        return false;
    }

    private boolean checkDiagonalsForWin() {
        return (checkRowCol(board[0][0], board[1][1], board[2][2]) || checkRowCol(board[0][2], board[1][1], board[2][0]));
    }

    private boolean checkRowCol(char c1, char c2, char c3) {
        return (c1 != EMPTY && c1 == c2 && c2 == c3);
    }

    public void changePlayer() {
        currentPlayerMark = (currentPlayerMark == X_MARK) ? O_MARK : X_MARK;
    }

    public static void main(String[] args) {
        TicTacToe game = new TicTacToe();
        Scanner scanner = new Scanner(System.in);
        int row, col;

        System.out.println("Welcome to Tic-Tac-Toe!");

        while (true) {
            System.out.println("\nCurrent board:");
            game.printBoard();

            System.out.println("\nPlayer " + game.currentPlayerMark + ", enter your move (row [1-3] column [1-3]):");
            row = scanner.nextInt() - 1;
            col = scanner.nextInt() - 1;

            if (game.placeMark(row, col)) {
                if (game.checkForWin()) {
                    System.out.println("Congratulations! Player " + game.currentPlayerMark + " wins!");
                    game.printBoard();
                    break;
                } else if (game.isBoardFull()) {
                    System.out.println("It's a draw!");
                    game.printBoard();
                    break;
                } else {
                    game.changePlayer();
                }
            } else {
                System.out.println("Invalid move! Try again.");
            }
        }
    }
}
