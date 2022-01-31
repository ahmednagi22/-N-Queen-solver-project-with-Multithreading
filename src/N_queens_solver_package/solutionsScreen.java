package N_queens_solver_package;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import javax.swing.*;
//import javax.swing.border.Border;
import java.util.Scanner;  // Import the Scanner class

public class solutionsScreen extends JFrame implements KeyListener {

    int n;
    int solutions_counter = 0;
    Scanner input = new Scanner(System.in);
    JFrame frame = new JFrame();
    ImageIcon image = new ImageIcon("Queen icon.jpg");      //size 66*100

    solutionsScreen(int n) {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// to close the application not only the frame
        frame.setIconImage(image.getImage());
        frame.setSize(700, 700);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.getContentPane().setBackground(Color.LIGHT_GRAY);
        frame.setLayout(new GridLayout(n, n));
        this.n = n;
        frame.addKeyListener(this);
        View_solution(solutions_counter);
        //frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
    }

    public void View_solution(int solutions_counter) {
        frame.setTitle("N Queens solver - " + "Solution NO. " + (solutions_counter+1) + "/" + SolverThread.solutions.size() + " Thread NO. " + SolverThread.solutions.get(solutions_counter).TN);
        int counter = 0;
        boolean Queen;
        int col = 0;
        int row = 0;
        if (n % 2 != 0) {
            for (int i = 0; i < n * n; i++) {
                if (col % n == 0) {
                    col = 0;
                }
                if (row % n == 0) {
                    row = 0;
                }
                if (SolverThread.solutions.get(solutions_counter).board[row][col] == true) {
                    Queen = true;
                    frame.add(new Cell(i % 2, Queen, 700 / n));
                } else {
                    Queen = false;
                    frame.add(new Cell(i % 2, Queen, 700 / n));
                }
                if (col == n - 1) {
                    row++;
                }
                col++;
            }
        } else {
            for (int i = 0; i < n * n; i++) {

                if (col % n == 0) {
                    col = 0;
                }
                if (row % n == 0) {
                    row = 0;
                }
                if (i % n == 0 && i != 0) {
                    counter++;
                }
                if (SolverThread.solutions.get(solutions_counter).board[row][col] == true) {
                    Queen = true;
                    frame.add(new Cell((i + counter) % 2, Queen, 700 / n));
                } else {
                    Queen = false;
                    frame.add(new Cell((i + counter) % 2, Queen, 700 / n));
                }
                if (col == n - 1) {
                    row++;
                }
                col++;
            }
        }
        frame.setVisible(true);
        counter = 0;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT && this.solutions_counter != SolverThread.solutions.size()-1) {
            frame.getContentPane().removeAll();
            this.solutions_counter++;
            this.View_solution(solutions_counter);
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT && this.solutions_counter != 0) {
            frame.getContentPane().removeAll();
            this.solutions_counter--;
            this.View_solution(solutions_counter);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

}
