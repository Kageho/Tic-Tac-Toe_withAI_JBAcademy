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
        // строки trash отлавливают мусор

        /*String firstCommand = scanner.next();
        while (!"exit".equals(firstCommand)) {
            if ("start".equals(firstCommand)) {
                String firstType;
                String secondType;
                if (scanner.hasNext()) {
                    firstType = scanner.next();
                    if (scanner.hasNext()) {
                        secondType = scanner.next();
                        if (("easy".equals(secondType) || "user".equals(secondType))
                                && ("easy".equals(firstType) || "user".equals(firstType))) {
                            // создадим двоих игроков
                            GamerType first = new GamerType('X', firstType);
                            GamerType second = new GamerType('O', secondType);
                            boolean whoseMove = true; // будем определять чья очередь ходить
                            // если тру то 1 игрок, иначе 2
                            printField(myGameFiled);
                            while (printGameState(myGameFiled)) {
                                if (whoseMove) {
                                    DefineTypeOfGame(myGameFiled, first);
                                    whoseMove = false;
                                } else {
                                    DefineTypeOfGame(myGameFiled, second);
                                    whoseMove = true;
                                }
                                printField(myGameFiled);
                            }

                        }
                    } else {
                        String trash = scanner.nextLine();
                        System.out.println("Bad parameters");
                    }

                } else {
                    String trash = scanner.nextLine();
                    System.out.println("Bad parameters");
                }
            } else {
                String trash = scanner.nextLine();
                System.out.println("Bad parameters!");
            }
            System.out.print("Input command: ");
            firstCommand = scanner.next();
        }*/
//        сейчас попробую сделать обрабоку ввода при помощи regex

        String everything; // строка для всего
        do {
            System.out.print("Input command: ");
            everything = scanner.nextLine();
            String arrayForEverything[] = everything.split("\\s");
            if ("start".equals(arrayForEverything[0]) && arrayForEverything.length > 2) {
                String firstType = arrayForEverything[1];
                String secondType = arrayForEverything[2]; // типы перового и второго игроков соответственно
                if (("easy".equals(secondType) || "user".equals(secondType) || "medium".equals(secondType))
                        && ("easy".equals(firstType) || "user".equals(firstType) || "medium".equals(firstType))) {
                    // создадим двоих игроков
                    GamerType first = new GamerType('X', firstType);
                    GamerType second = new GamerType('O', secondType);
                    boolean whoseMove = true; // будем определять чья очередь ходить
                    // если тру то 1 игрок, иначе 2
                    printField(myGameFiled);
                    while (printGameState(myGameFiled)) {
                        if (whoseMove) {
                            DefineTypeOfGame(myGameFiled, first);
                            whoseMove = false;
                        } else {
                            DefineTypeOfGame(myGameFiled, second);
                            whoseMove = true;
                        }
                        printField(myGameFiled);
                    }

                } else {
                    System.out.print("Bad parameters!");
                }
            } else if ("exit".equals(arrayForEverything[0])) {
                break;
            } else {
                System.out.print("Bad parameters!");
            }
        } while (true);
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

    private static void MakingMoveLvlEasy(char[][] field, char character) { // буду передавать поле и Х или О
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
        field[2 - y][x] = character; // машина сделала ход
    }

    private static void MakingMoveLvlMedium(char[][] field, char character) {
        System.out.println("Making move level \"medium\"");
        int[] myArray = returnCoordinates(field, character);
        if (myArray[0] != -1 && myArray[1] != -1) {
            field[myArray[1]][myArray[0]] = character;
            return;
        } else {
            char oppositeChar = character == 'X' ? 'O' : 'X'; // противоположный индекс
            myArray = returnCoordinates(field, oppositeChar);
            if (myArray[0] != -1 && myArray[1] != -1) {
                field[myArray[1]][myArray[0]] = oppositeChar;
                return;
            }
        }
        Random random = new Random();
        int upper = 2;
        int x;
        int y;
        do {
            x = random.nextInt(upper + 1);
            y = random.nextInt(upper + 1);
        } while (field[2 - y][x] != ' '); // подбираем свободное место
        field[2 - y][x] = character; // машина сделала ход

    }

    private static int[] returnCoordinates(char[][] field, char character) {
        int[] coordinates = new int[]{-1, -1};
        int numberOfCoincidences;
        for (int i = 0; i < 2; i++) { // проверка всех горизонтов
            numberOfCoincidences = 0;
            for (int j = 0; j < 2; j++) {
                if (field[i][j] == ' ') {
                    coordinates[0] = j;
                    coordinates[1] = i;
                }
                if (field[i][j] == character) {
                    numberOfCoincidences++;
                }
            }
            if (numberOfCoincidences == 2) {
                return coordinates;
            } else {
                coordinates[0] = -1;
                coordinates[1] = -1;
            }
        }
        // проверка вертикалей
        for (int j = 0; j < 2; j++) {
            numberOfCoincidences = 0;
            for (int i = 0; i < 2; i++) {
                if (field[i][j] == ' ') {
                    coordinates[0] = j;
                    coordinates[1] = i;
                }
                if (field[i][j] == character) {
                    numberOfCoincidences++;
                }
            }
            if (numberOfCoincidences == 2) {
                return coordinates;
            } else {
                coordinates[0] = -1;
                coordinates[1] = -1;
            }
        }
        numberOfCoincidences = 0; // проверка главной диагонали
        for (int i = 0; i < 2; i++) {
            int j = i;
            if (field[i][j] == character) {
                numberOfCoincidences++;
            }
            if (field[i][j] == ' ') {
                coordinates[0] = j;
                coordinates[1] = i;
            }
        }
        if (numberOfCoincidences == 2) {
            return coordinates;
        } else {
            coordinates[0] = -1;
            coordinates[1] = -1;
        }
//        проверка горизонтали
        for (int i = 0; i < 2; i++) {
            int j = 2 - i;
            if (field[i][j] == character) {
                numberOfCoincidences++;
            }
            if (field[i][j] == ' ') {
                coordinates[0] = j;
                coordinates[1] = i;
            }
        }
        if (numberOfCoincidences == 2) {
            return coordinates;
        } else {
            coordinates[0] = -1;
            coordinates[1] = -1;
            return coordinates;
        }

    }

    private static void readValue(char[][] field, char character) { // метод, отвечюащий за ход игрока
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
                            field[2 - y][x] = character;
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
                String trash0 = scanner.next(); // строки для захвата мусора
                if (scanner.hasNextLine()) {
                    String trash = scanner.nextLine(); // строка для сбора мусора
                }
                System.out.println("You should enter numbers!");
            }

        } while (doSomething);
    }

    private static void DefineTypeOfGame(char[][] field, GamerType gamer) {
        if ("easy".equals(gamer.getWhoAreYou())) {
            MakingMoveLvlEasy(field, gamer.getTypeOfMove());
        } else if ("user".equals(gamer.getWhoAreYou())) {
            readValue(field, gamer.getTypeOfMove());
        } else {
            MakingMoveLvlMedium(field, gamer.getTypeOfMove());
        }
    }
}

class GamerType {
    private char typeOfMove;
    private String whoAreYou; // строка, которя хранит тип сущности, т.е. машина или человек

    GamerType(char typeOfMove, String whoAreYou) {
        this.typeOfMove = typeOfMove;
        this.whoAreYou = whoAreYou;
    }

    public char getTypeOfMove() {
        return typeOfMove;
    }

    public String getWhoAreYou() {
        return whoAreYou;
    }
}
