/**
 * AUTHOR: Jon Pack
 * OCCC - ADVANCED JAVA
 * DATE: 02 27, 2024
 * PROJECT NAME: Midnight.java
 * DESCRIPTION: worked with carlos, luke, trace, nassir
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Midnight {

    private static int GRID_SIZE;

        public static void main(String[] args) {
            String fileName = "";

            // Check if start and stop args are in cmd line
            if (args.length > 0) {
                fileName = args[0];
            } else {
                Scanner get = new Scanner(System.in);

                // ask the user for start and stop v
                System.out.print("Enter file name: ");
                fileName = get.nextLine();
                get.close();
            }

            int[][] board = readBoardFromFile(fileName);

            // error handling
            if (board == null) {
                System.out.println("error reading board from file");
                return;
            }

            // print initial board
            printBoard(board);

            // try to solve board
            if (solveBoard(board)) {
                System.out.println("solved successfully");
            } else {
                System.out.println("unsolvable board");
            }

            // print final board
            printBoardWithLetters(board);
        }

        private static int[][] readBoardFromFile(String fileName) {
            try {
                File file = new File(fileName);
                Scanner scanner = new Scanner(file);

                // Read the first line to determine the size
                String firstLine = scanner.nextLine();
                GRID_SIZE = firstLine.split("\\s+").length;

                int[][] board = new int[GRID_SIZE][GRID_SIZE];
                int row = 0;

                // Process the first line
                processLine(board, row, firstLine);
                row++;

                // Process the remaining lines
                while (scanner.hasNextLine() && row < GRID_SIZE) {
                    String line = scanner.nextLine();
                    processLine(board, row, line);
                    row++;
                }

                scanner.close();
                return board;
            } catch (FileNotFoundException e) {
                System.err.println("File not found: " + fileName);
                e.printStackTrace();
                return null;
            }
        }

        private static void processLine(int[][] board, int row, String line) {
            String[] numbers = line.split("\\s+");

            for (int col = 0; col < GRID_SIZE; col++) {
                if ("-".equals(numbers[col])) {
                    board[row][col] = 0; // or any other value representing an empty cell
                } else {
                    board[row][col] = convertToBase10(numbers[col]);
                }
            }
        }

        private static int convertToBase10(String value) {
            try {
                return Integer.parseInt(value);
            } catch (NumberFormatException e) {
                // Convert letter to base 10 (A=10, B=11, ..., G=16)
                return value.charAt(0) - 'A' + 10;
            }
        }

        private static String convertToOriginalBase(int value) {
            if (value == 0) {
                return "-";
            } else if (value >= 10 && value <= 16) {
                // Convert base 10 to letter (10=A, 11=B, ..., 16=G)
                return Character.toString((char) ('A' + value - 10));
            } else {
                return Integer.toString(value);
            }
        }

        private static void printBoard(int[][] board) {
            for (int row = 0; row < GRID_SIZE; row++) {
                if (row % 4 == 0 && row != 0) {
                    System.out.println("----------------------------");
                }
                for (int column = 0; column < GRID_SIZE; column++) {
                    if (column % 4 == 0 && column != 0) {
                        System.out.print("|");
                    }
                    System.out.print(convertToOriginalBase(board[row][column]));
                }
                System.out.println();
            }
        }

        private static void printBoardWithLetters(int[][] board) {
            for (int row = 0; row < GRID_SIZE; row++) {
                if (row % 4 == 0 && row != 0) {
                    System.out.println("-----------------------------------");
                }
                for (int column = 0; column < GRID_SIZE; column++) {
                    if (column % 4 == 0 && column != 0) {
                        System.out.print("|");
                    }
                    System.out.print(board[row][column] > 9 ? (char) ('A' + board[row][column] - 10) + " " : board[row][column] + " ");
                }
                System.out.println();
            }
        }

        private static boolean isNumberInRow(int[][] board, int number, int row) {
            for (int i = 0; i < GRID_SIZE; i++) {
                if (board[row][i] == number) {
                    return true;
                }
            }
            return false;
        }

        private static boolean isNumberInColumn(int[][] board, int number, int column) {
            for (int i = 0; i < GRID_SIZE; i++) {
                if (board[i][column] == number) {
                    return true;
                }
            }
            return false;
        }

    private static boolean isNumberInBox(int[][] board, int number, int row, int column) {
        int sqrt = (int) Math.sqrt(GRID_SIZE);
        int localBoxRow = row - row % sqrt;
        int localBoxColumn = column - column % sqrt;

        for (int i = localBoxRow; i < localBoxRow + sqrt; i++) {
            for (int j = localBoxColumn; j < localBoxColumn + sqrt; j++) {
                if (board[i][j] == number) {
                    return true;
                }
            }
        }
        return false;
    }

        private static boolean isValidPlacement(int[][] board, int number, int row, int column) {
            return !isNumberInRow(board, number, row) &&
                    !isNumberInColumn(board, number, column) &&
                    !isNumberInBox(board, number, row, column);
        }

        private static boolean solveBoard(int[][] board) {
            for (int row = 0; row < GRID_SIZE; row++) {
                for (int column = 0; column < GRID_SIZE; column++) {
                    if (board[row][column] == 0) {
                        for (int numberToTry = 1; numberToTry <= GRID_SIZE; numberToTry++) {
                            if (isValidPlacement(board, numberToTry, row, column)) {
                                board[row][column] = numberToTry;

                                if (solveBoard(board)) {
                                    return true;
                                } else {
                                    board[row][column] = 0;
                                }
                            }
                        }
                        return false;
                    }
                }
            }
            return true;
        }
    }


