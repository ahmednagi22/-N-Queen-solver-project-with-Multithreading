package N_queens_solver_package;

import java.util.Arrays;
import java.util.ArrayList; // import the ArrayList class
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import N_queens_solver_package.SolutionOBJ;

public class SolverThread extends Thread {

    public static List<SolutionOBJ> solutions = Collections.synchronizedList(new ArrayList<SolutionOBJ>());
    boolean[][] board;
    int col;
    static int N;
    int TN;

    public SolverThread(boolean[][] board, int col, int N, int TN) {
        this.board = board;
        this.col = col;
        this.N = N;
        this.TN = TN;
    }

    private static boolean isSafe(boolean[][] board, int row, int col) {
        for (int i = 0; i < row; i++) {
            if (board[i][col] == true) {
                return false;
            }
        }

        for (int i = row, j = col; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == true) {
                return false;
            }
        }

        for (int i = row, j = col; i >= 0 && j < board.length; i--, j++) {
            if (board[i][j] == true) {
                return false;
            }
        }

        return true;
    }

    private static void addSolution(SolutionOBJ solution) {
        if (TrueBoard(solution.board)) {
            boolean[][] copiped_board = new boolean[N][N];
            for (int i = 0; i < solution.board.length; i++) {
                boolean[] aMatrix = solution.board[i];
                int aLength = solution.board.length;
                copiped_board[i] = new boolean[N];
                System.arraycopy(aMatrix, 0, copiped_board[i], 0, aLength);
            }
            solutions.add(new SolutionOBJ(copiped_board, solution.TN));
        }
    }

//    private synchronized static void printSolution(boolean[][] board) {
//        if (TrueBoard(board)) {
//            System.out.println("═════════════════" + "Solution NO." + counter.incrementAndGet() + "════════════════");
//            addSolution(board);
//            if (true) {
//                return;
//            }
//            for (int i = 0; i < N; i++) {
//                System.out.print("║");
//                for (int j = 0; j < N; j++) {
//                    if (j < N - 1) {
//                        if (board[i][j] == true) {
//                            System.out.print('Q' + " ");
//                        } else {
//                            System.out.print('─' + " ");
//                        }
//                    } else {
//                        if (board[i][j] == true) {
//                            System.out.print('Q');
//                        } else {
//                            System.out.print('─');
//                        }
//                    }
//                }
//                System.out.print("║");
//                System.out.println();
//            }
//        }
//    }
//    public ArrayList<char[][]> getSolutions() {
//        return solutions;
//    }
    private static void nQueen(boolean[][] board, int col, int TN) {
        if (col == board.length) {
            addSolution(new SolutionOBJ(board, TN));
            return;
        }

        for (int i = 0; i < board.length; i++) {
            if (isSafe(board, col, i)) {
                board[col][i] = true;

                nQueen(board, col + 1, TN);

                board[col][i] = false;
            }
        }
    }

    @Override
    public void run() {
        nQueen(board, col, TN);
    }

    private static boolean hasN(boolean[][] board) {
        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j] == true) {
                    count++;
                }
            }
        }
        return (count == N);
    }

    private static boolean row_emtpy(boolean[][] board) {
        for (int i = 0; i < N; i++) {
            if (board[0][i] == true) {
                return false;
            }
        }
        return true;
    }

    private static boolean TrueBoard(boolean[][] board) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j] == true) {
                    board[i][j] = false;
                    if (!isSafe(board, i, j)) {
                        return false;
                    } else {
                        board[i][j] = true;
                    }
                }
            }
        }
        return (hasN(board) && !row_emtpy(board));
    }
}
