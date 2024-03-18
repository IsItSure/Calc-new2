import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите выражение (например, 2 + 2 или II + II)");
        String str = scanner.nextLine();
        String[] parts = str.split(" ");
        if (parts.length != 3) {
            throw new Exception("Неправильный формат ввода");
        }
        boolean flag1 = isRomanNumeral(parts[0]);
        boolean flag2 = isRomanNumeral(parts[2]);
        int num1 = defenitionOfNumber(parts[0]);
        int num2 = defenitionOfNumber(parts[2]);
        checkLimits(num1);
        checkLimits(num2);
        if (flag1 ^ flag2) {
            throw new Exception("Разная система записи чисел");
        }
        if (flag1) {
            int x = result(num1, num2, parts[1]);
            if (x <= 0) {
                throw new Exception("Результат операций с римскими числами не может быть меньше единицы");
            }
            System.out.println(intToRoman(x));
        } else {
            System.out.println(result(num1, num2, parts[1]));
        }
    }

    public static int defenitionOfNumber(String number) {
        return switch (number) {
            case ("I") -> 1;
            case ("II") -> 2;
            case ("III") -> 3;
            case ("IV") -> 4;
            case ("V") -> 5;
            case ("VI") -> 6;
            case ("VII") -> 7;
            case ("VIII") -> 8;
            case ("IX") -> 9;
            case ("X") -> 10;
            default -> parseInt(number);
        };
    }

    public static int result(int num1, int num2, String sign) throws Exception {
        return switch (sign) {
            case ("+") -> num1 + num2;
            case ("-") -> num1 - num2;
            case ("/") -> num1 / num2;
            case ("*") -> num1 * num2;
            default -> throw new Exception("Такой операции нет - " + sign);
        };
    }

    private static void checkLimits(int num) throws Exception {
        if (num <= 0 || num > 10) {
            throw new Exception("Введите числа в пределах от 1 до 10");
        }
    }

    public static boolean isRomanNumeral(String str) {
        switch (str) {
            case ("I"):
            case ("II"):
            case ("III"):
            case ("IV"):
            case ("V"):
            case ("VI"):
            case ("VII"):
            case ("VIII"):
            case ("IX"):
            case ("X"):
                return true;
            default:
                return false;
        }
    }

    public static String intToRoman(int number) {
        String[] Romanian = {"M", "L", "X", "IX", "VIII", "VII", "VI", "V", "IV", "III", "II", "I"};
        int[] values = {100, 50, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
        String str = new String();
        for (int i = 0; i < values.length; i++) {
            while (number >= values[i]) {
                str += Romanian[i];
                number -= values[i];
            }
        }
        return str;
    }
}