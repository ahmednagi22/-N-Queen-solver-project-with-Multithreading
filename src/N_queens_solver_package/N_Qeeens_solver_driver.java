/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package N_queens_solver_package;

import FirstScreenGUI.InputScreen;
import static FirstScreenGUI.InputScreen.CAN_STOP;
import java.util.Scanner;  // Import the Scanner class
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList; // import the ArrayList class
import java.util.List;
import N_queens_solver_package.SolverThread;
import static N_queens_solver_package.SolverThread.N;

/**
 *
 * @author ahmad
 */
public class N_Qeeens_solver_driver {

    /**
     * @param args the command line arguments
     */
    static int N;

    public static void main(String args[]) {
        InputScreen Input = new InputScreen();
        Input.setVisible(true);
        Input.setTitle("N Queen Solver");
        while (InputScreen.CAN_STOP_window() != true);
        N = InputScreen.N;
        Input.setTitle("N Queen Solver - Processing ... Please Wait..");
        ArrayList<Thread> threadsArray = new ArrayList<Thread>();
        int count = 0;
        for (int j = 0; j < N; j++) {
            for (int k = 0; k < N; k++) {
                boolean board[][] = new boolean[N][N];
                for (int i = 0; i < N; i++) {
                    for (int l = 0; l < N; l++) {
                        board[i][l] = false;
                    }
                }
                board[j][k] = true;
                SolverThread thread = new SolverThread(board, 1, N, count);
                thread.start();
                threadsArray.add(thread);
                count++;
            }
        }
        try {
            for (int i = 0; i < threadsArray.size(); i++) {
                threadsArray.get(i).join();
            }
        } catch (InterruptedException e) {
            System.out.println("Thread interruption!.");
        }
        Input.setVisible(false);
//        System.out.println("Found " + GfG.solutions.size() + " solutions.");
//        for (int i = 0; i < GfG.solutions.size(); i++) {
//            printSolution(GfG.solutions.get(i), i);
//        }
//        System.out.println("════════════════════════════════════════════════");
        new solutionsScreen(N);

    }

    public static void printSolution(SolutionOBJ solution, int number_of_solution) {
        System.out.println("══════" + "Solution NO." + (number_of_solution + 1) + "══════" + "Thread NO." + solution.TN + "══════");
        for (int j = 0; j < N; j++) {
            System.out.print("║");
            for (int k = 0; k < N; k++) {
                if (k < N - 1) {
                    if (solution.board[j][k] == true) {
                        System.out.print('Q' + " ");
                    } else {
                        System.out.print('─' + " ");
                    }
                } else {
                    if (solution.board[j][k] == true) {
                        System.out.print('Q');
                    } else {
                        System.out.print('─');
                    }
                }
            }
            System.out.print("║");
            System.out.println();
        }

    }
}

//        count = 0;
//        for (int j = 0; j < N; j++) {
//            for (int k = 0; k < N; k++) {
//                if (!((GfG) threadsArray.get(count)).getSolutions().isEmpty()) {
//                    Object[] objects = ((GfG) threadsArray.get(count)).getSolutions().toArray();
//                    for (int k = 0; k < objects.length; k++) {
//                        print_solution(objects[k]);
//                    }
//                }
//                System.out.println(count);
//                count++;
//            }
//        }
//        count = 0;
//        for (int j = 0; j < solutions.size(); j++) {
//            for (int k = 0; k < N; k++) {
//                for (int k = 0; k < N; k++) {
//                    System.out.print((solutions.get(j))[k][k] + " ");
//                    count++;
//                }
//                System.out.println("");
//            }
//            System.out.println("-------------");
//        }

