package summingnums;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MyTest {
    public static void main(String[] args) {
        int [] a = {1,5,23,45};
        System.out.println(getTargetSumIndexes(a, 28));
    }
    static List<Integer> getTargetSumIndexes(int a[], int target) {
        List<Integer> subArray = getTargetSumIndexes(a, target, 0);
        return subArray;
    }
    static List<Integer> getTargetSumIndexes(int a[], int target, int start) {
        List<Integer> subArray = new ArrayList<>();
        if (start == a.length) {
            return Collections.emptyList();
        }
        for (int i=start; i<a.length; i++) {
            if (target - a[i] > 0) {
                List<Integer> targetSumIndexes = getTargetSumIndexes(a, target - a[i], i + 1);
                if (targetSumIndexes.size() > 0) {
                    subArray.add(i);
                    subArray.addAll(targetSumIndexes);
                    break;
                }
            } else if (target - a[i] == 0) {
                subArray.add(i);
                break;
            }
        }
        return subArray;
    }
}