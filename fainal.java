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
        cleanField(myGameFiled);

//        сейчас попробую сделать обрабоку ввода при помощи regex

        String everything; // строка для всего
        do {
            System.out.print("Input command: ");
            everything = scanner.nextLine();
            String arrayForEverything[] = everything.split("\\s");
            if ("start".equals(arrayForEverything[0]) && arrayForEverything.length > 2) {
                String firstType = arrayForEverything[1];
                String secondType = arrayForEverything[2]; // типы перового и второго игроков соответственно
                if (("easy".equals(secondType) || "user".equals(secondType) || "medium".equals(secondType) ||
                        "hard".equals(secondType)) && ("easy".equals(firstType) || "user".equals(firstType) ||
                        "medium".equals(firstType)) || "hard".equals(firstType)) {
                    // создадим двоих игроков
                    GamerType first;
                    if (!"hard".equals(firstType)) {
                        first = new GamerType('X', firstType);
                        first.setAi(false);
                    } else {
                        first = new GamerType('X', 'O', firstType);
                        first.setAi(true);
                    }

                    GamerType second;
                    if (!"hard".equals(secondType)) {
                        second = new GamerType('O', secondType);
                        second.setAi(false);
                    } else {
                        second = new GamerType('O', 'X', secondType);
                        second.setAi(true);

                    }
                    boolean whoseMove = true; // будем определять чья очередь ходить
                    // если тру то 1 игрок, иначе 2
                    printField(myGameFiled);
                    while (true) {
                        if (whoseMove) {
                            if (!first.getAi()) {
                                DefineTypeOfGame(myGameFiled, first);
                            } else {
                                DefineTypeOfGame(myGameFiled, first);
                            }

                            whoseMove = false;
                        } else {
                            if (!second.getAi()) {
                                DefineTypeOfGame(myGameFiled, second);
                            } else {
                                DefineTypeOfGame(myGameFiled, second);
                            }
                            whoseMove = true;
                        }
                        printField(myGameFiled);
                        if (checkWinner(myGameFiled, first.typeOfMove)) {
                            System.out.println(first.typeOfMove + " wins");
                            cleanField(myGameFiled);
                            break;
                        } else if (checkWinner(myGameFiled, second.typeOfMove)) {
                            System.out.println(second.typeOfMove + " wins");
                            cleanField(myGameFiled);
                            break;
                        } else if (DoWeHaveAvailSpots(myGameFiled) == 0) {
                            System.out.println("Draw");
                            cleanField(myGameFiled);
                            break;
                        }
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

    private static void cleanField(char[][] myGameField) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; ++j) { // заполняем в циклах поле пробелами
                myGameField[i][j] = ' ';
            }
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

    static int DoWeHaveAvailSpots(char[][] field) { // проверка поля на свободные места
        int availablePlace = 0;
        for (char[] array : field) {
            for (char val : array) {
                if (val == ' ') {
                    availablePlace++;
                }
            }
        }
        return availablePlace;
    }

    static boolean checkWinner(char[][] field, char gamer) { // здесь будем проверять выйграл ли игрок
        if (gamer == field[0][0] && gamer == field[0][1] && gamer == field[0][2] ||
                gamer == field[1][0] && gamer == field[1][1] && gamer == field[1][2] ||
                gamer == field[2][0] && gamer == field[2][1] && gamer == field[2][2] ||
                gamer == field[0][0] && gamer == field[1][0] && gamer == field[2][0] ||
                gamer == field[0][1] && gamer == field[1][1] && gamer == field[2][1] ||
                gamer == field[0][2] && gamer == field[1][2] && gamer == field[2][2] ||
                gamer == field[0][0] && gamer == field[1][1] && gamer == field[2][2] ||
                gamer == field[0][2] && gamer == field[1][1] && gamer == field[2][0]) {
            return true;
        }
        return false;
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
        } else if ("medium".equals(gamer.getWhoAreYou())) {
            MakingMoveLvlMedium(field, gamer.getTypeOfMove());
        } else {
            System.out.println("Making move level \"hard\"");
            String str = gamer.minimax(field, gamer.getTypeOfMove());
            int first = Integer.parseInt(str.substring(0, 1));
            int second = Integer.parseInt(str.substring(1, 2));
            field[first][second] = gamer.typeOfMove;
        }
    }
}

class GamerType {
    private boolean isAi; // проверка на ии
    protected char typeOfMove;
    protected String whoAreYou; // строка, которя хранит тип сущности, т.е. машина или человек
    private char hyPlayer;

    GamerType(char typeOfMove, String whoAreYou) {
        this.typeOfMove = typeOfMove;
        this.whoAreYou = whoAreYou;
    }

    // конструктор для ии
    GamerType(char aiPlayer, char hyPlayer, String whoAreYou) { // ai player это типо ии
//        ктоТы тоже важная часть программы
        typeOfMove = aiPlayer;
        this.whoAreYou = whoAreYou;
        this.hyPlayer = hyPlayer;
    }

    public void setAi(boolean isAi) {
        this.isAi = isAi;
    }

    public boolean getAi() {
        return isAi;
    }

    public char getTypeOfMove() {
        return typeOfMove;
    }

    public String getWhoAreYou() {
        return whoAreYou;
    }

    // методы для ии
    private int[][] getAvailableSpots(char[][] board) { // получаем свободные ячейки
        int[][] availSpots;
        int countOfSpots = Main.DoWeHaveAvailSpots(board); // количество свободных мест
        if (countOfSpots != 0) {
            availSpots = new int[2][countOfSpots];
            countOfSpots = 0; // счетчик для количства полученых пустых клеток
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board[2 - i][j] == ' ') {
                        availSpots[0][countOfSpots] = 2 - i; // в массиве с индексом
//                        строк 0 будут лежать i координаты, а в массиве
//                        с индексом строк 1 будут лежать j координаты
                        availSpots[1][countOfSpots++] = j;
                    }
                }
            }
        } else {
            availSpots = new int[0][0]; // если свободных ячеек нет, то возвращаем путой массив
        }
        return availSpots;
    }

    protected String minimax(char[][] newBoard, char player) {
        String rightMove = ""; // координаты для правильного хода сначала будет идти строка,
        // а затем столбец
        int[][] availSpots = getAvailableSpots(newBoard);
        if (Main.checkWinner(newBoard, getTypeOfMove())) { // проверяем победителя
            return "10";
        } else if (Main.checkWinner(newBoard, hyPlayer)) {
            return "-10";
        } else if (availSpots.length == 0) {
            return "0";
        }
        int[][] moves = new int[3][availSpots[0].length]; // массив для хранения
//        координат и терминального сотояния т.е. 10 или -10, или 0
        for (int i = 0; i < availSpots[0].length; i++) { // пробегаемся по сободным ячейкам
//            и выясняем терминальное состояние
            newBoard[availSpots[0][i]][availSpots[1][i]] = player;
            moves[0][i] = availSpots[0][i]; // координаты
            moves[1][i] = availSpots[1][i]; // тоже координаты
            String score; // переменная для хранения завершенного терминального состояния
            if (player == hyPlayer) {
                score = minimax(newBoard, getTypeOfMove());
            } else {
                score = minimax(newBoard, hyPlayer);
            }
//            теперь восстанавливаем доску
            newBoard[availSpots[0][i]][availSpots[1][i]] = ' ';
//            теперь в массив moves вставлем score

            moves[2][i] = Integer.parseInt(score); // терминальное состояние
        }
        int iBest = -1;
        int jBest = -1;
        if (player == getTypeOfMove()) {
            int bestScore = -10000; // находим max из терминального состояния
            for (int index = 0; index < moves[0].length; index++) {
                if (moves[2][index] > bestScore) {
                    bestScore = moves[2][index];
                    iBest = moves[0][index];
                    jBest = moves[1][index];
                }
            }
        } else {
            int bestScore = 10000; // мин терминального состояния
            for (int index = 0; index < moves[0].length; index++) {
                if (moves[2][index] < bestScore) {
                    bestScore = moves[2][index];
                    iBest = moves[0][index];
                    jBest = moves[1][index];
                }
            }
        }
        rightMove += iBest;
        rightMove += jBest;
        return rightMove;
    }
}


