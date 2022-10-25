package codility.triangles;

/*
In the sorted list of edges find the number of the possible triangles.
*/

import java.util.Arrays;
import java.util.stream.Collectors;

public class Solution {
    private static void sort(int[] array, int a, int b) {
        if (b - a > 1) {
            sort(array, a, a + (b - a) / 2);
            sort(array, a + (b - a) / 2, b);
            merge(array, a, a + (b - a) / 2, b);
        }
    }

    private static void merge(int[] array, final int a, final int m, final int b) {
//        System.out.print("" + a + " - " + m + " - " + b +
//        Arrays.stream(array).skip(a).limit(b-a).boxed().collect(Collectors.toList()));
        int[] out = new int[b - a];
        int n = m;
        int i = 0;
        int start = a;
        while (start < m && n < b) {
            if (array[start] > array[n]) {
                out[i++] = array[n++];
            } else {
                out[i++] = array[start++];
            }
        }
        while (start < m) {
            out[i++] = array[start++];
        }
        while (n < b) {
            out[i++] = array[n++];
        }
//        System.out.println(" - " + Arrays.stream(out).boxed().collect(Collectors.toList()));
        System.arraycopy(out, 0, array, a, out.length);
    }

    public static void sort(int[] array) {
        sort(array, 0, array.length / 2);
        sort(array, array.length / 2, array.length);
        merge(array, 0, array.length / 2, array.length);
    }

    public static int findTriangles(int[] edges) {
        int count = 0;
        for (int i = 0; i < edges.length - 2; i++) {
            for (int j = i + 1; j < edges.length - 1; j++) {
                int k = j + 1;
                while (k < edges.length && edges[i] + edges[j] > edges[k]) {
                    k++;
                }
                count += k - j - 1;
            }
        }
        return count;
    }
}
