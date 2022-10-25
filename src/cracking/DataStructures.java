package cracking;

import java.util.*;

public class DataStructures {
    public static boolean allUnique(String s) {
        char[] a = s.toCharArray();
        Arrays.sort(a);
        for(int i=1; i<a.length; i++) {
            if(a[i] == a[i-1]) {
                return false;
            }
        }
        return true;
    }

    public static boolean isPermutation(String reference, String maybePermutation) {
        Map<Character, Integer> refHist = new HashMap<>();
        Map<Character, Integer> maybeHist = new HashMap<>();

        for(char c : reference.toCharArray()) {
            refHist.merge(c, 1, (v1, v2) -> v1 + 1);
        }
        for(char c : maybePermutation.toCharArray()) {
            maybeHist.merge(c, 1, (v1, v2) -> v1 + 1);
        }

        return refHist.equals(maybeHist);
    }

    public static boolean isSubstring(String s1, String s2) {
        return (s1 + s1).contains(s2);
    }

    public static void rotateMatrix(byte[][][] image) {
        int N = image.length;
        for(int i=0; i<N/2; i++) {
            for(int j=i;j<N-i-1;j++) {
                byte[] pixel1 = image[i][j];
                image[i][j] = image[N - j - 1][i];
                byte[] pixel2 = image[j][N - i - 1];
                image[j][N - i - 1] = pixel1;
                pixel1 = image[N - i - 1][N - j - 1];
                image[N - i - 1][N - j - 1] = pixel2;
                image[N - j - 1][i] = pixel1;
            }
        }
    }

    public static void zeroMatrix(byte[][] matrix) {
        Set<Integer> r = new HashSet<>();
        Set<Integer> c = new HashSet<>();
        for(int i=0; i<matrix.length; i++) {
            for(int j=0; j<matrix[0].length; j++) {
                if(matrix[i][j] == 0){
                    r.add(i);c.add(j);
                }
            }
        }
        r.forEach(r1 -> {
            for(int k1=0; k1<matrix[0].length; k1++) {
                matrix[r1][k1] = 0;
            }});
        c.forEach(c1 -> {
            for(int k1=0; k1<matrix.length; k1++) {
                matrix[k1][c1] = 0;
            }});
    }

    public static boolean oneAway(String one, String two) {
        if(one.equals(two)) {return true;}
        if(Math.abs(one.length() - two.length()) > 1) {return false;}
        boolean change = false;
        int j = 0;
        for(int i=0; i<Math.min(one.length(), two.length()); i++) {
            if(one.charAt(i) == two.charAt(i + j)) {continue;}
            if(change) return false;
            // 1
            if(one.length()==two.length()) {
                change = true;
            } else
                // 2
                if(two.length() < one.length() && (one.length() > i+1 || one.charAt(i+1)==two.charAt(i))) {
                    change = true;
                    j = -1;
                } else
                    // 3
                    if(two.length() > one.length() && (two.length() > i+1 || one.charAt(i)==two.charAt(i+1))) {
                        change = true;
                        j = 1;
                    }
        }
        return true;
    }
}
