// Nechemia Seif

package ticTacToeGUI;

import java.awt.*;

public class TicTacToeModel {

    public enum Cell {
        NONE, O, X
    };

    private boolean isPlayerOneMove;
    private Cell[][] grid;
    private Point move;

    public TicTacToeModel() {
        grid = new Cell[3][3];
        move = new Point();

        initialize();
    }

    void initialize() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                grid[row][col] = Cell.NONE;
            }
        }

        isPlayerOneMove = true;
    }

    boolean setCell(int row, int col, Cell cell) {
        if (grid[row][col] == Cell.NONE) {
            move.setLocation(col, row);
            grid[row][col] = cell;
            return true;
        }
        return false;
    }

    Cell currentPlayer() {
        return isPlayerOneMove ? Cell.X : Cell.O;
    }

    void togglePlayer() {
        isPlayerOneMove = !isPlayerOneMove;
    }

    boolean isGameOver() {
        return hasWinner() || isDraw();
    }

    boolean hasWinner() {
        return (grid[move.y][0] == grid[move.y][1]
                && grid[move.y][1] == grid[move.y][2])
                || (grid[0][move.x] == grid[1][move.x]
                && grid[1][move.x] == grid[2][move.x])
                || (grid[0][0] != Cell.NONE
                && grid[0][0] == grid[1][1]
                && grid[1][1] == grid[2][2])
                || (grid[0][2] != Cell.NONE
                && grid[0][2] == grid[1][1]
                && grid[1][1] == grid[2][0]);
    }

    boolean isDraw() {
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                if (grid[row][col] == Cell.NONE) {
                    return false;
                }
            }
        }
        return (!hasWinner());
    }
    
}
