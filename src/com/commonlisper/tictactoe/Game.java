package com.commonlisper.tictactoe;

import java.util.Random;
import java.util.Scanner;

public class Game {
    private char[][] field;

    private final int FIELD_WIDTH = 3;
    private final int FIELD_HEIGHT = 3;
    private final char EMPTY_CELL = '-';
    private final char X_CELL = 'X';
    private final char O_CELL = '0';

    public void start() {
        initField();
        printField();

        while (true) {
            playerTurn();
            printField();

            if (isWin(X_CELL)) {
                System.out.println("Player win!");
                break;
            }

            if (isTie()) {
                System.out.println("It is tie! End.");
                break;
            }

            computerTurn();
            printField();

            if (isWin(O_CELL)) {
                System.out.println("Computer win!");
                break;
            }

            if (isTie()) {
                System.out.println("It is tie! End.");
                break;
            }
        }
    }

    private boolean isWin(char playerField) {
        // check rows
        int fillQuantity;
        for (int i = 0; i < FIELD_WIDTH; i++) {
            fillQuantity = 0;
            for (int j = 0; j < FIELD_HEIGHT; j++) {
                if (field[i][j] == playerField) {
                    fillQuantity++;
                }
            }
            if (fillQuantity == FIELD_WIDTH) return true;
        }

        // check columns
        for (int i = 0; i < FIELD_WIDTH; i++) {
            fillQuantity = 0;
            for (int j = 0; j < FIELD_HEIGHT; j++) {
                if (field[j][i] == playerField) {
                    fillQuantity++;
                }
                if (fillQuantity == FIELD_HEIGHT) return true;
            }
        }

        // check main diagonal
        int diagQuantity = 0;
        for (int i = 0; i < Math.min(FIELD_WIDTH, FIELD_HEIGHT); i++) {
            if (field[i][i] == playerField) {
                diagQuantity++;
            }
        }
        if (diagQuantity == Math.min(FIELD_WIDTH, FIELD_HEIGHT)) return true;

        // check additional diagonal
        diagQuantity = 0;
        for (int i = 0; i < Math.min(FIELD_WIDTH, FIELD_HEIGHT); i++)
            for (int j = Math.min(FIELD_WIDTH, FIELD_HEIGHT) - 1; j >= 0; j--) {
                if (field[i][j] == playerField) {
                    diagQuantity++;
                }
            }
        if (diagQuantity == Math.min(FIELD_WIDTH, FIELD_HEIGHT)) return true;

        return false;
    }

    private boolean isTie() {
        for (int i = 0; i < FIELD_WIDTH; i++) {
            for (int j = 0; j < FIELD_HEIGHT; j++) {
                if (field[i][j] == EMPTY_CELL) return false;
            }
        }

        return true;
    }

    private void initField() {
        field = new char[FIELD_WIDTH][FIELD_HEIGHT];

        for (int i = 0; i < FIELD_WIDTH; i++) {
            for (int j = 0; j < FIELD_HEIGHT; j++) {
                field[i][j] = EMPTY_CELL;
            }
        }
    }

    private void printField() {
        for (int i = 0; i <= FIELD_WIDTH; i++) {
            System.out.print(i + " ");
        }
        System.out.println();

        for (int i = 0; i < FIELD_WIDTH; i++) {
            System.out.print(i + 1 + " ");
            for (int j = 0; j < FIELD_HEIGHT; j++) {
                System.out.print(field[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public void playerTurn() {
        Scanner scanner = new Scanner(System.in);
        int x, y;

        do {
            System.out.println("Player turn. Enter valid [X, Y] cell coords:");
            x = scanner.nextInt() - 1;
            y = scanner.nextInt() - 1;

        } while (!isValidCell(x, y));

        field[x][y] = X_CELL;
    }

    public void computerTurn() {
        Random random = new Random();
        int x, y;
        do {
            x = random.nextInt(1, FIELD_WIDTH + 1) - 1;
            y = random.nextInt(1, FIELD_HEIGHT + 1) - 1;
        } while (!isValidCell(x, y));

        System.out.println("Computer turn.");
        field[x][y] = O_CELL;
    }

    private boolean isValidCell(int x, int y) {
        if (x < 0 || x >= FIELD_WIDTH || y < 0 || y >= FIELD_HEIGHT) {
            return false;
        }

        return field[x][y] == EMPTY_CELL;
    }
}
