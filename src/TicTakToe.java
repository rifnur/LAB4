import org.w3c.dom.ls.LSOutput;

import javax.lang.model.type.NullType;
import java.util.Random;
import java.util.Scanner;

public class TicTakToe {
    public static void main(String[] args) {
        start();
    }

    static void start() {
        final char playerSign = 'X';
        final char computerSign = 'Y';

        char[][] field = initField();
        String winnerName="";

        drawField(field);

        do {
            // TODO Проверка на ничью

            // Ход игрока
            if (!vsePolyaZapolneni(field)) {
                doPlayerMove(field, playerSign);
                // Перерисовка поля, чтобы увидеть поставленные фишки
                drawField(field);


            // Проверка на победу
            if (checkWin(field, playerSign)) {
                winnerName = "Player";
                break;
            }
            }else {
                System.out.println("Ничья"); break;}
            // Ход компьютера
            if (!vsePolyaZapolneni(field)) {
                doAIMove(field, computerSign);
                // Перерисовка поля, чтобы увидеть поставленные фишки
                drawField(field);


            // Проверка на победу
            if (checkWin(field, computerSign)) {
                winnerName = "Computer";
                break;
            }
           } else {
                System.out.println("Ничья"); break;
            }
        } while (true) ;
        if ((checkWin( field, playerSign) || checkWin(field, computerSign)) ){
            System.out.println("Sir, congratulations!");
            System.out.println("You are winner Mr. " + winnerName);
        }
    }

    static void doAIMove(char[][] field, char sign) {
        Random random = new Random();
        System.out.println("Computer's move...");
        // Вводим координаты Х, Y
        int xVal = random.nextInt(3);
        int yVal = random.nextInt(3);

        // Если значение по координатам занято, то делаем перегенерацию координат, пока не найдем свободные
        while (field[xVal][yVal] != '-') {
            xVal = random.nextInt(3);
            yVal = random.nextInt(3);
        }

        System.out.println(String.format("Computer's X-value: %s", xVal));
        System.out.println(String.format("Computer's Y-value: %s", yVal));

        field[xVal][yVal] = sign;
    }

    static boolean vsePolyaZapolneni (char[][] field){
        for (int i=0;i<3;i++){
            for (int j=0;j<3;j++){
                if (field[i][j]=='-'){
                    return false;
                }
            }
        }return true;
    }

    static void doPlayerMove(char[][] field, char sign) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Sir, you move...");

        // Вводим координаты Х, Y
        System.out.println("Please enter X-value [1-3]");
        int xVal = scanner.nextInt() - 1;
        System.out.println("Please enter Y-value [1-3]");
        int yVal = scanner.nextInt() - 1;

        // TODO проверка на диапазон значений, чтобы не выйти за пределы массива
        if ( checkInt( xVal, xVal,sign)){
            while (field[xVal][yVal] != '-') {
                System.out.println(String.format("Field[%s][%s] is already occupied", xVal + 1, yVal + 1));
                System.out.println("Please enter X-value [1-3]");
                xVal = scanner.nextInt() - 1;

                System.out.println("Please enter Y-value [1-3]");
                yVal = scanner.nextInt() - 1;
            } field[xVal][yVal] = sign;
        } else {  System.out.println("Введите правильные значения!");
            doPlayerMove(field, sign); }
        // Если значение по координатам занято, то повторяем ввод координат, пока не найдем свободные
    }

    static boolean checkInt (int xVal,int yVal,char sign){
        if ((xVal>=0 && xVal<3) ||(yVal>=0 && yVal<3)){
            return true;
        } return  false;
    }
    // Проверка победы
    static boolean checkWin(char[][] field, char sign) {
        // По горизонтали
        for (int i = 0; i < field.length; i++) {
            if (field[i][0] == sign && field[i][1] == sign && field[i][2] == sign) {
                return true;
            }
        }
        // TODO По вертикали
        for (int i = 0; i < field.length; i++) {
            if (field[0][i] == sign && field[1][i] == sign && field[2][i] == sign) {
                return true;
            }
        }
        // TODO По диагонали
        if ((field[0][0] == sign && field[1][1] == sign && field[2][2] == sign) ||
                (field[0][2] == sign && field[1][1] == sign && field[2][0] == sign) ) {
            return true;
        }
        return false;
    }

    // Отрисока пока как матрицы
    static void drawField(char[][] field) {
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                System.out.print(field[i][j]);
            }
            System.out.println();
        }
    }

    static char[][] initField() {
        return new char[][]{
                {'-', '-', '-'},
                {'-', '-', '-'},
                {'-', '-', '-'},
        };
    }
}
