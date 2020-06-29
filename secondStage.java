package tictactoe;


import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // write your code here
        // сейчас получу строку с исходным состоянием поля
        char[][] myGameFiled = new char[3][3];
        // сейчас буду заполнять массив значений
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; ++j) { // заполняем в циклах поле пробелами
                myGameFiled[i][j] = ' ';
            }
        }
        boolean whoIsMoving = true; // Если хоидт человек, то true, если машина - ложь
        printField(myGameFiled);
        while (printGameState(myGameFiled)) {
            if (whoIsMoving) {
                readValue(myGameFiled);
                whoIsMoving = false;
            } else {
                MakingMoveLvlEasy(myGameFiled);
                whoIsMoving = true;
            }
            printField(myGameFiled);
        }
    }

    private static void printField(char[][] field) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (i == 0 && j == 0) {
                    System.out.println("---------");
                }
                if (j == 0) {
                    System.out.print("| ");
                }
                if (field[i][j] == ' ') {
                    System.out.printf("%c ", ' ');
                } else {
                    System.out.printf("%c ", field[i][j]);
                }
                if (j == 2) {
                    System.out.println("|");
                }
                if (i == 2 && j == 2) {
                    System.out.println("---------");
                }
            }
        }

    }

    private static boolean printGameState(char[][] field) {

        //Теперь напишем тоже самое только для двумерного массива
        String horizon1 = String.valueOf(field[0][0]) + String.valueOf(field[0][1]) +
                String.valueOf(field[0][2]);
        String horizon2 = String.valueOf(field[1][0]) + String.valueOf(field[1][1]) +
                String.valueOf(field[1][2]);
        String horizon3 = String.valueOf(field[2][0]) + String.valueOf(field[2][1]) +
                String.valueOf(field[2][2]);
        String vertical1 = String.valueOf(field[0][0]) + String.valueOf(field[1][0]) +
                String.valueOf(field[2][0]);
        String vertical2 = String.valueOf(field[0][1]) + String.valueOf(field[1][1]) +
                String.valueOf(field[2][1]);
        String vertical3 = String.valueOf(field[0][2]) + String.valueOf(field[1][2]) +
                String.valueOf(field[2][2]);
        String trueDiagonal = String.valueOf(field[0][0]) + String.valueOf(field[1][1]) +
                String.valueOf(field[2][2]);
        String falseDiagonal = String.valueOf(field[0][2]) + String.valueOf(field[1][1]) +
                String.valueOf(field[2][0]);
        boolean availablePlace = false;
        for (char[] array : field) {
            for (char val : array) {
                if (val == ' ') {
                    availablePlace = true;
                    break;
                }
            }
        }
        if ("XXX".equals(horizon1) || "XXX".equals(horizon2) || "XXX".equals(horizon3) ||
                "XXX".equals(vertical1) || "XXX".equals(vertical2) || "XXX".equals(vertical3)
                || "XXX".equals(falseDiagonal) || "XXX".equals(trueDiagonal)) {
            System.out.println("X wins");
            return false;
        } else if ("OOO".equals(horizon1) || "OOO".equals(horizon2) || "OOO".equals(horizon3) ||
                "OOO".equals(vertical1) || "OOO".equals(vertical2) || "OOO".equals(vertical3)
                || "OOO".equals(falseDiagonal) || "OOO".equals(trueDiagonal)) {
            System.out.println("O wins");
            return false;
        } else {
            if (availablePlace) {
                return true;
            } else {
                System.out.println("Draw");
                return false;
            }
        }

    }

    static void MakingMoveLvlEasy(char[][] field) {
        // здесь будет ход машины буквой О уровня легко
        System.out.println("Making move level \"easy\"");
        Random random = new Random();
        int upper = 2;
        int x;
        int y;
        do {
            x = random.nextInt(upper + 1);
            y = random.nextInt(upper + 1);
        } while (field[2 - y][x] != ' '); // подбираем свободное место
        field[2 - y][x] = 'O'; // машина сделала ход
    }

    static void readValue(char[][] field) { // метод, отвечюащий за ход игрока
        Scanner scanner = new Scanner(System.in);
        boolean doSomething = true;
        do {
            System.out.print("Enter the coordinates: ");
            int x; // my coordinates
            int y;
            if (scanner.hasNextInt()) {
                x = scanner.nextInt();
                if (scanner.hasNextInt()) {
                    y = scanner.nextInt();
                    if (x > 0 && x < 4 && y > 0 && y < 4) {
                        if (field[2 - --y][--x] == ' ') {
                            field[2 - y][x] = 'X';
                            doSomething = false;
                        } else {
                            System.out.println("This cell is occupied! Choose another one!");
                        }
                    } else {
                        System.out.println("Coordinates should be from 1 to 3!");
                    }
                } else {
                    String trash1 = scanner.nextLine();
                    System.out.println("You should enter numbers!");
                }

            } else {
                String trash0 = scanner.next();
                if (scanner.hasNextLine()) {
                    String trash = scanner.nextLine();
                }
                System.out.println("You should enter numbers!");
            }

        } while (doSomething);
    }
}
