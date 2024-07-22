import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * AUTHOR: Jon Pack
 * OCCC - ADVANCED JAVA
 * DATE: 02 16, 2024
 * PROJECT NAME: Sudoku.java
 * DESCRIPTION: Sudoku
 * worked with luke,trace,carlos,nassir,kierra
 */
public class Sudoku {

    private static final int GRID_SIZE = 9;

        public static void main(String[] args) {
            System.out.println("hello and welcome to sudoku :)");


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

            //error handling
           if(board == null){
               System.out.println("error reading board from file");
               return;
           }

           //print initial board
           printBoard(board);

           //try to solve board
           if(solveBoard(board)){
               System.out.println("solved successfully");
           }else{
               System.out.println("unsolvable board");
           }

           //print final board
            printBoard(board);





        }

    private static int[][] readBoardFromFile(String fileName) {
        try {
            File file = new File(fileName);
            Scanner scanner = new Scanner(file);

            int[][] board = new int[GRID_SIZE][GRID_SIZE];
            int row = 0;

            while (scanner.hasNextLine() && row < GRID_SIZE) {
                String line = scanner.nextLine();
                String[] numbers = line.split("\\s+");

                for (int col = 0; col < GRID_SIZE; col++) {
                    if ("-".equals(numbers[col])) {
                        board[row][col] = 0; // or any other value representing an empty cell
                    } else {
                        board[row][col] = Integer.parseInt(numbers[col]);
                    }
                }
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


        private static void printBoard(int[][] board) {
            for (int row = 0; row < GRID_SIZE; row++) {
                if (row % 3 == 0 && row != 0) {
                    System.out.println("-----------");
                }
                for (int column = 0; column < GRID_SIZE; column++) {
                    if (column % 3 == 0 && column != 0) {
                        System.out.print("|");
                    }
                    System.out.print(board[row][column]);
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
            int localBoxRow = row - row % 3;
            int localBoxColumn = column - column % 3;

            for (int i = localBoxRow; i < localBoxRow + 3; i++) {
                for (int j = localBoxColumn; j < localBoxColumn + 3; j++) {
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
                                }
                                else {
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




