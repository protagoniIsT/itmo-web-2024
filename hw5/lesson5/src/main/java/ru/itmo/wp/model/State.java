package ru.itmo.wp.model;

import java.util.Arrays;

public class State {
    private static final int DEFAULT_SIZE = 3;
    private static final String EMPTY_CELL = "";
    private static final String CROSS = "X";
    private static final String ZERO = "O";

    private final int size;
    private final String[][] cells;
    private boolean crossesMove;
    private GameState phase;
    private int movesMade;

    public State() {
        this.size = DEFAULT_SIZE;
        this.cells = new String[size][size];
        for (String[] row : cells) {
            Arrays.fill(row, EMPTY_CELL);
        }
        this.crossesMove = true;
        this.phase = GameState.RUNNING;
        this.movesMade = 0;
    }

    public int getSize() {
        return size;
    }

    public String[][] getCells() {
        return cells;
    }

    public boolean isCrossesMove() {
        return crossesMove;
    }

    public GameState getPhase() {
        return phase;
    }

    public void makeMove(int row, int col) {
        if (phase == GameState.RUNNING && cells[row][col].equals(EMPTY_CELL)) {
            movesMade++;
            cells[row][col] = crossesMove ? CROSS : ZERO;
            if (!updatePhase()) {
                crossesMove = !crossesMove;
            }
        }
    }

    private boolean updatePhase() {
        if (checkPlayerWon(CROSS)) {
            phase = GameState.WON_X;
            return true;
        } else if (checkPlayerWon(ZERO)) {
            phase = GameState.WON_O;
            return true;
        } else if (movesMade == size * size) {
            phase = GameState.DRAW;
            return true;
        }
        return false;
    }

    private boolean checkPlayerWon(String v) {
        for (int i = 0; i < this.size; i++) {
            if (cells[i][0].equals(v) && cells[i][1].equals(v) && cells[i][2].equals(v)) {
                return true;
            }
        }

        for (int i = 0; i < this.size; i++) {
            if (cells[0][i].equals(v) && cells[1][i].equals(v) && cells[2][i].equals(v)) {
                return true;
            }
        }

        return cells[0][0].equals(v) && cells[1][1].equals(v) && cells[2][2].equals(v)
                || cells[0][2].equals(v) && cells[1][1].equals(v) && cells[2][0].equals(v);
    }
}
