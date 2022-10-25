package other;

import java.util.Random;
import java.util.Scanner;

public class Multiplication {
    public static void main(String args[]) {
        Random r = new Random();
        Scanner scanner = new Scanner(System.in);
        for (; ; ) {
            int a = r.nextInt(8) + 5;
            int b = r.nextInt(3) + 10;
            int res;
            do {
                System.out.print(String.format("%d * %d = ", a, b));
                res = scanner.nextInt();
            } while (res != a * b);
            System.out.println(" - Yes!");
        }
    }
}
