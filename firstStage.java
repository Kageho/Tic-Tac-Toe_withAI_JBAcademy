package tictactoe;


import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // write your code here
        // сейчас получу строку с исходным состоянием поля
        System.out.print("Enter cells: ");
        String filed = scanner.nextLine();
        char[][] myGameFiled = new char[3][3];
        // сейчас буду заполнять массив значений
        int index = 0;
        boolean currentGame = true;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; ++j) {
                if (filed.charAt(index) != '_') {
                    myGameFiled[i][j] = filed.charAt(index++);
                } else {
                    myGameFiled[i][j] = ' ';
                    index++;
                }

            }
        }
        printField(myGameFiled);
        readValue(myGameFiled);
        printField(myGameFiled);
        printGameState(myGameFiled);


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
        /*for(int i = 0; i < 9; i++){
            if(i == 0){
                System.out.println("---------");
            }
            if(i % 3 == 0) {
                System.out.print("| ");
            }
            if(field[i] == '_'){
                System.out.printf("%c ", ' ');
            } else {
                System.out.printf("%c ",field[i]);
            }
            if(i % 3 == 2){
                System.out.println("|");
            }
            if(i == 8){
                System.out.println("---------");
            }
        }*/
    }

    private static boolean printGameState(char[][] field) {
        // сейчас буду переводить игровые строки/поля/столбцы в строки и затем сравнивать с "ХХХ" или "ООО"
       /* String horizon1 = String.valueOf(field[6]) + String.valueOf(field[7]) +
                String.valueOf(field[8]); // горизонтальная линия с (1, 1) до (3, 1)
        String horizon2 =  String.valueOf(field[3]) + String.valueOf(field[4]) +
                String.valueOf(field[5]); // горизонтальная линия с (1, 2) до (3, 2)
        String horizon3 =  String.valueOf(field[0]) + String.valueOf(field[1]) +
                String.valueOf(field[2]); // горизонтальная линия с (1, 3) до (3, 3)
        // тепрь о вертикальных линиях
        String vertical1 =  String.valueOf(field[0]) + String.valueOf(field[3]) +
                String.valueOf(field[6]); // вертикаль с (1, 3) до (1, 1)
        String vertical2 =  String.valueOf(field[1]) + String.valueOf(field[4]) +
                String.valueOf(field[7]); // вертикль с (2, 3) до (2, 1)
        String vertical3 =  String.valueOf(field[2]) + String.valueOf(field[5]) +
                String.valueOf(field[8]); // вертикаль с (3, 3) до (3, 1)
        String trueDiagonal =  String.valueOf(field[0]) + String.valueOf(field[4]) +
                String.valueOf(field[8]); // главная диагональ
        String falseDiagonal =  String.valueOf(field[2]) + String.valueOf(field[4]) +
                String.valueOf(field[6]);*/
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
                System.out.println("Game not finished");
                return true;
            } else {
                System.out.println("Draw");
                return false;
            }
        }

    }

    static void readValue(char[][] field) {
        Scanner scanner = new Scanner(System.in);
        int countX = 0;
        int countO = 0;
        for (char[] i : field) {
            for (char val : i) {
                if (val == 'X') {
                    ++countX;
                }
                if (val == 'O') {
                    ++countO;
                }
            }

        }
        boolean rightMove = countO == countX ? true : false;
        // если количесто х равно о, то переменная будет равна true
        // и надо будет заполнить массив новым x, иначе o
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
                            if (rightMove) {
                                field[2 - y][x] = 'X';
                                doSomething = false;
                            } else {
                                field[2 - y][x] = 'O';
                                doSomething = false;
                            }
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

    static int getCountAvailablePlace(char[][] field) {
        int count = 0;
        for (char[] array : field) {
            for (char i : array) {
                if (i == ' ') {
                    count++;
                }
            }
        }
        return count;
    }
}
