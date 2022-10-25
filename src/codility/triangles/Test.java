package codility.triangles;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Test {
    public static void main(String[] args) {
        int[] case1 = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
        int[] case2 = new int[]{1, 1, 1, 4, 4, 4, 7, 7, 7};
        int[] case3 = new int[]{10, 11, 13, 14, 25, 26, 27};
        int[] case4 = new int[]{1, 1, 1, 1, 50, 60, 70};
        int[] case5 = new int[]{1, 10, 100, 1000};

        System.out.println(Solution.findTriangles(case1));
        System.out.println(Solution.findTriangles(case2));
        System.out.println(Solution.findTriangles(case3));
        System.out.println(Solution.findTriangles(case4));
        System.out.println(Solution.findTriangles(case5));

        int[] case10 = new int[]{8, 3, 5, 2, 8, 5, 2, 8, 2, 1, 9, 3};
        int[] case11 = new int[]{3, 3, 1, 1, 1, 8, 8, 1, 2, 9};
        int[] case12 = new int[]{5, 11, 22, 12, 17, 10, 13};
        int[] case13 = new int[]{50, 60, 1, 1, 1, 1, 70};
        int[] case14 = new int[]{1000100, 1000, 1, 10, 100};

        Solution.sort(case13);
        System.out.println(Arrays.stream(case13).boxed().collect(Collectors.toList()));
        Solution.sort(case10);
        System.out.println(Arrays.stream(case10).boxed().collect(Collectors.toList()));
        Solution.sort(case11);
        System.out.println(Arrays.stream(case11).boxed().collect(Collectors.toList()));
        Solution.sort(case12);
        System.out.println(Arrays.stream(case12).boxed().collect(Collectors.toList()));
        Solution.sort(case14);
        System.out.println(Arrays.stream(case14).boxed().collect(Collectors.toList()));

    }
}
