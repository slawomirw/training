package merge;

import java.util.Arrays;
import java.util.stream.Collectors;

public class MyTest {
    public static void main(String[] args) {
        System.out.println(Arrays.stream(merge(new int[] {1,2,3,0,0,0}, new int[] {2,5,6})).boxed().collect(Collectors.toList()));
        System.out.println(Arrays.stream(merge(new int[] {1}, new int[] {})).boxed().collect(Collectors.toList()));
    }
    public static int[] merge(int[] num1, int[] num2) {
        if (num2.length == 0) {
            return num1;
        }

        int i=num1.length - num2.length - 1, j=num2.length - 1, pos1 = num1.length - 1;
        while (pos1 >= 0 && j>=0) {
            if(i<0) {
                num1[pos1--] = num2[j--];
            } else if(num1[i] < num2[j]) {
                num1[pos1--] = num2[j--];
            } else if(num1[i] > num2[j]) {
                num1[pos1--] = num1[i--];
            } else {
                i--;
            }
        }

        return num1;
    }
}
